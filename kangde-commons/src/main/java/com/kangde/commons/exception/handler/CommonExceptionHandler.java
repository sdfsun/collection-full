package com.kangde.commons.exception.handler;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.util.TypeUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.exception.BaseException;
import com.kangde.commons.vo.AjaxResult;

/**
 * 共通异常处理器
 * @author lisuo
 *
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
	
	public static final ModelAndView EMPTY_VIEW = new ModelAndView();
	
	private static final String UTF8 = "UTF-8";

	/** 日志 */
	protected Logger log = Logger.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        //非Ajax请求 将跳转到500错误页面
//        if(!WEBUtil.isAjaxRequest(request)){
//        	//判断是否是表单提交
//        	String method = request.getParameter("_method");
//			if(method==null)
//				throw new RuntimeException(e);
//        }
    	if (handler instanceof HandlerMethod){
    		HandlerMethod mh = (HandlerMethod) handler;
    		Method method = mh.getMethod();
    		if(method!=null){
    			Class<?> retType = method.getReturnType();
    			//判断返回结果类型是否是AjaxResult
    			if(TypeUtils.isAssignable(AjaxResult.class,retType)){
    				response.setCharacterEncoding(UTF8);
    		    	response.setContentType("text/javascript;charset="+UTF8);
    		    	//response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    		    	AjaxResult result = new AjaxResult();
    		    	result.setCode(AjaxResult.CODE_FAILURE);
    		    	if(e instanceof BaseException){
    		    		result.setMsg(e.getMessage());
    		    	}else{
    		    		if(e instanceof AuthorizationException){
    		    			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    		    			result.setCode(AjaxResult.CODE_DENIED);
    		    			result.setMsg("没有对应的权限,如有疑问,请与管理员联系");
    		    		}else{
    		    			log.error(e);
    		    			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    		    			if(e instanceof NullPointerException){
    		    				result.setMsg("发生空指针错误,请联系管理员");
    		    			}else{
    							result.setMsg(e.getMessage());
    							//result.setMsg("系统发生错误,请联系管理员");
    		    			}
    		    		}
    		    	}
    		    	print(response, result.toString());
    			}else{
    				if(e instanceof AuthorizationException){
    					return new ModelAndView("403");
    				}
    				log.error(e);
    				return new ModelAndView("500");
    			}
    		}
    	}
        return EMPTY_VIEW;
    }
    
    public void print(HttpServletResponse response,Object obj){
    	try {
    		if(!response.isCommitted()){
				response.getWriter().println(obj);
				response.getWriter().flush();
				response.getWriter().close();
    		}
		} catch (IOException ex) {
			ex.printStackTrace();
			log.error(ex);
		}
    }
    
    
}
