package com.kangde.log.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kangde.commons.CoreConst;
import com.kangde.commons.util.CcUtil;
import com.kangde.commons.util.IpUtil;
import com.kangde.log.model.LogModel;
import com.kangde.log.service.LogService;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.ResourceService;

/**
 * 日志拦截器
 * @author lisuo
 */
public class LogInterceptor implements HandlerInterceptor {

	protected Logger logger = Logger.getLogger(this.getClass());
	private static ThreadLocal<Date> startTimeThreadLocal = new NamedThreadLocal<>("StartTimeThreadLocal");
	private static Set<String> requestMethods = new HashSet<>();
	private static Set<String> methodKeys = new HashSet<>();
	private static Boolean enable = CoreConst.getBoolean("log.interceptor.enable")==null?false:CoreConst.getBoolean("log.interceptor.enable");
	/*#如果不在匹配的路径或方法中,是否强制拦截发生异常错误的请求
	log.interceptor.exception.force=true
	#如果发生异常时,强制拦截产生日志,排除哪些异常类型
	log.interceptor.exception.excludeTypes=com.kangde.commons.exception.BaseException*/
	//private static boolean exceptionForce = CoreConst.getBoolean("log.interceptor.exception.force");
	//private static Set<Class<? extends Exception>> excludeTypes = new HashSet<>();
	

	static{
		//初始化配置参数
		String[] reqms = CoreConst.getStringArray("log.interceptor.requestMethods");
		if(ArrayUtils.isNotEmpty(reqms)){
			for(String temp:reqms){
				if(StringUtils.isNotBlank(temp)){
					requestMethods.add(temp.trim().toUpperCase());
				}
			}
		}
		String[] mkeys = CoreConst.getStringArray("log.interceptor.methodKeys");
		if(ArrayUtils.isNotEmpty(mkeys)){
			for(String temp:mkeys){
				if(StringUtils.isNotBlank(temp)){
					methodKeys.add(temp.trim().toUpperCase());
				}
			}
		}
		/*String[] etypes = CoreConst.getStringArray("log.interceptor.exception.excludeTypes");
		if(ArrayUtils.isNotEmpty(etypes)){
			for(String temp:etypes){
				if(StringUtils.isNotBlank(temp)){
					Class<?> clazz = null;
					try {
						clazz = Class.forName(temp.trim());
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(e);
					}
					if(TypeUtils.isAssignable(Exception.class,clazz)){
						excludeTypes.add((Class<? extends Exception>) clazz);
					}else{
						throw new RuntimeException("配置的["+temp+"]不是一个[java.lang.Exception]的子类");
					}
				}
			}
		}*/
	}
	
	@Autowired
	private LogService logService;
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		startTimeThreadLocal.set(new Date());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,final Object handler,final Exception ex)
			throws Exception {
		//处理目标是方法
		if (handler instanceof HandlerMethod){
			String httpMethod = getHttpMethod(request);
			HandlerMethod mh = (HandlerMethod) handler;
			//包含配置请求方式及请求方法关键字
			if(requestMethods.contains(httpMethod) && hasMethodKey(mh.getMethod().getName())){
				saveLog(request, response, mh, ex,httpMethod);
			}
			/*else{
				//没有匹配查看是否强制对异常信息进行日志生成
				if(exceptionForce && ex!=null){
					boolean need = true;
					if(CollectionUtils.isNotEmpty(excludeTypes)){
						Iterator<Class<? extends Exception>> it = excludeTypes.iterator();
						while(it.hasNext()){
							//如果是配置的排除异常类型,不生成日志
							if(TypeUtils.isAssignable(it.next(), ex.getClass())){
								need=false;
								break;
							}
						}
					}
					if(need){
						saveLog(request, response, mh, ex,httpMethod);
					}
				}
			}*/
		}
	}
	
	//保存日志
	private void saveLog(HttpServletRequest request,HttpServletResponse response,final HandlerMethod handler,final Exception ex,String httpMethod){
		if(enable){
			//保存日志
			final Date date = startTimeThreadLocal.get();
			final String url = request.getServletPath();
			String ip = IpUtil.getIpAddr(request);
			String agent = request.getHeader("user-agent");
			Map<String, String[]> parameterMap = request.getParameterMap();
			String params = getParams(parameterMap);
			long timeConsuming = System.currentTimeMillis()-date.getTime();
			int resStatus = response.getStatus();
			
			EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
			LogModel log = new LogModel();
			log.setTimeConsuming(timeConsuming);
			log.setUserId(currentUser.getId());
			log.setUserName(currentUser.getUserName());
			log.setLoginName(currentUser.getLoginName());
			log.setRemoteAddr(ip);
			log.setServerHostName(IpUtil.getHostName());
			log.setServerHostAddr(IpUtil.getHostAddress());
			log.setRequestUrl(url);
			log.setUserAgent(agent);
			log.setParams(params);
			log.setStartTime(date);
			log.setHttpMethod(httpMethod);
			log.setMethodName(getMethodName(handler));
			log.setTitle(url);
			//日志标题设置
			setLogTitle(log, handler,url);
			// 保存日志
			if(ex!=null){
				log.setState(LogModel.STATE_FAILURE);
				//只保存异常的起源信息
				Throwable cause = ExceptionUtils.getRootCause(ex);
				log.setExceptionInfo(ExceptionUtils.getStackTrace(cause));
			}else{
				if(HttpServletResponse.SC_OK != resStatus){
					log.setState(LogModel.STATE_FAILURE);
					log.setExceptionInfo("请求正常响应,但服务器状态码为："+resStatus);
				}
			}
			logService.save(log);
			
			/*CcUtil.asyncRun(new Runnable() {
				@Override
				public void run() {
					try{
						EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
						LogModel log = new LogModel();
						log.setTimeConsuming(timeConsuming);
						log.setUserId(currentUser.getId());
						log.setUserName(currentUser.getUserName());
						log.setLoginName(currentUser.getLoginName());
						log.setRemoteAddr(ip);
						log.setServerHostName(IpUtil.getHostName());
						log.setServerHostAddr(IpUtil.getHostAddress());
						log.setRequestUrl(url);
						log.setUserAgent(agent);
						log.setParams(params);
						log.setStartTime(date);
						log.setHttpMethod(httpMethod);
						log.setMethodName(getMethodName(handler));
						log.setTitle(url);
						//日志标题设置
						setLogTitle(log, handler,url);
						// 保存日志
						if(ex!=null){
							log.setState(LogModel.STATE_FAILURE);
							//只保存异常的起源信息
							Throwable cause = ExceptionUtils.getRootCause(ex);
							log.setExceptionInfo(ExceptionUtils.getStackTrace(cause));
						}else{
							if(HttpServletResponse.SC_OK != resStatus){
								log.setState(LogModel.STATE_FAILURE);
								log.setExceptionInfo("请求正常响应,但服务器状态码为："+resStatus);
							}
						}
						logService.save(log);
					}catch(Exception e){
						e.printStackTrace();
						logger.error(e);
					}
					
				}
			});*/
			
			
		}
	}
	
	//包含配置的方法关键字
	private boolean hasMethodKey(String methodName){
		if(methodKeys.contains("*"))
			return true;
		methodName = methodName.toUpperCase();
		if(CollectionUtils.isNotEmpty(methodKeys)){
			Iterator<String> it = methodKeys.iterator();
			while(it.hasNext()){
				if(methodName.contains(it.next())){
					return true;
				}
			}
		}
		return false;
	}
	
	//直接使用json形式转换参数展示
	private String getParams(Map<String, String[]> paramMap){
//		StringBuilder params = new StringBuilder();
//        Set<Entry<String, String[]>> set = paramMap.entrySet();  
//        Iterator<Entry<String, String[]>> it = set.iterator();  
//        while (it.hasNext()) {  
//            Entry<String, String[]> entry = it.next();
//            String key = entry.getKey();
//            String[] value = entry.getValue();
//        }
		return MapUtils.isEmpty(paramMap)?null:JSON.toJSONString(paramMap);
	}
	
	//获取HTTP请求方式,基于Restful
	private String getHttpMethod(HttpServletRequest request){
		String method = request.getMethod();
		if ("POST".equals(request.getMethod())) {
			String param = request.getParameter(HiddenHttpMethodFilter.DEFAULT_METHOD_PARAM);
			if(StringUtils.isNotBlank(param)){
				method = param.toUpperCase(Locale.ENGLISH);
			}
		}
		return method;
	}
	
	//获取方法名称,全类名+方法名(参数1,参数...)
	private String getMethodName(HandlerMethod handler){
		Method method = handler.getMethod();
		StringBuilder builder = new StringBuilder(handler.getBean().getClass().getName()+".");
		builder.append(method.getName());
		Class<?>[] types = method.getParameterTypes();
		if(ArrayUtils.isNotEmpty(types)){
			builder.append("(");
			for(Class<?> type:types){
				builder.append(type.getSimpleName().trim()+",");
			}
			builder.delete(builder.length()-1, builder.length());
			builder.append(")");
		}
		
		return builder.toString();
	}
	
	//获取shiro标签注解,操作标识码,查询数据库获取对应的操作名称
	private void setLogTitle(LogModel log,HandlerMethod handler,String url){
		RequiresPermissions permissions = handler.getMethodAnnotation(RequiresPermissions.class);
		if(permissions!=null){
			if(permissions.value().length == 1){
				String code = permissions.value()[0];
				String title = resourceService.getTitle("code",code);
				if(title!=null){
					log.setTitle(title);
				}
			}else{
				StringBuilder titles = new StringBuilder("如下操作的其中一项：");
				for(String code:permissions.value()){
					String title = resourceService.getTitle("code",code);
					if(title!=null){
						titles.append("【"+title+"】");
					}
				}
				log.setTitle(titles.toString());
			}
		}else{
			String title = resourceService.getTitle("url",url);
			if(title!=null){
				log.setTitle(title);
			}
		}
	}
	
}
