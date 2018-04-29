package com.kangde.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.service.AreaService;
import com.kangde.commons.CoreConst;
import com.kangde.commons.util.IpUtil;
import com.kangde.commons.util.WEBUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.web.controller.SimpleController;
import com.kangde.log.LogUtil;
import com.kangde.log.model.UserLogModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 用户登录/注销等前端交互入口
 */
@Controller
public class LoginController extends SimpleController {

	@Autowired
	private AreaService areaService;
	/**
	 * 用户登录页面跳转
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(@RequestParam String username, @RequestParam String password,@RequestParam(required=false) String validateCode,HttpSession session) {
		AjaxResult result = new AjaxResult();
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
//			String code = WEBUtil.getSessionAttribute("validateCode");
//			if(validateCode==null || !validateCode.equalsIgnoreCase(code)){
//				result.setMsg("验证码错误");
//				result.setCode(AjaxResult.CODE_FAILURE);
//				return result;
//			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
				String url = getRequest().getContextPath() + "/index";
				result.setCode(AjaxResult.CODE_SUCCESS);
				result.setObj(url);
				EmployeeInfoModel user = SecurityUtil.getCurrentUser();
			
				//放入session
				WEBUtil.setSessionAttribute(CoreConst.CURRENT_USER, user);
				//将当前用户所在分公司催收区域放入session
				List<String> list = areaService.queryAreaByOrgId(user.getOrgId());
				
			
				String attachOrgIds = user.getAttachOrgId();
				if(attachOrgIds!=null){
					String[] split = attachOrgIds.split(",");
					for (String id : split) {
						List<String> attachList = areaService.queryAreaByOrgId(id);
						if(CollectionUtils.isNotEmpty(attachList)){
							list.addAll(attachList);
						}
					}
				}
				//临时处理:深圳分公司 覆盖区域使用广州分公司的覆盖区域
				if(list.contains("1957")){
					list.remove("1957");
					list.add("1954");
				}
				WEBUtil.setSessionAttribute("FGSArea", list);
				UserLogModel model = new UserLogModel();
				model.setLoginName(user.getLoginName());
				model.setUserName(user.getUserName());
				model.setUserId(user.getId());
				model.setType("用户登陆");
				model.setOperateContent("用户"+user.getLoginName()+"，【"+user.getUserName()+"】登陆系统");
				model.setCreateTime(new Date());
				model.setIpAddr(IpUtil.getIpAddr(getRequest()));
				LogUtil.saveUserLog(model);
			} catch (AuthenticationException e) {
				result.setCode(AjaxResult.CODE_FAILURE);
				result.setMsg("用户名或密码错误");
				if (e instanceof UnknownAccountException) {
					log.debug("账号错误");
				} else if (e instanceof IncorrectCredentialsException) {
					log.debug("密码错误");
				} else if (e instanceof LockedAccountException) {
					result.setMsg("账号已被禁用，请与系统管理员联系");
					log.debug("账号已被禁用，请与系统管理员联系");
				} else if (e instanceof AuthenticationException) {
					log.debug("您没有授权!");
				}else{
					e.printStackTrace();
				}
			}
		}else{
			String url = getRequest().getContextPath() + "/index";
			result.setCode(AjaxResult.CODE_SUCCESS);
			result.setObj(url);
		}
		return result;
	}
	
	//注销
	@RequestMapping(value = "/logout")
	public ModelAndView logout(){
		try {
			Subject subject = SecurityUtils.getSubject();
			Object cuser = subject.getPrincipal();
			subject.logout();
			if(cuser!=null){
				EmployeeInfoModel user = (EmployeeInfoModel) cuser;
				UserLogModel model = new UserLogModel();
				model.setLoginName(user.getLoginName());
				model.setUserName(user.getUserName());
				model.setUserId(user.getId());
				model.setType("用户退出");
				model.setOperateContent("用户"+user.getLoginName()+"，【"+user.getUserName()+"】退出系统");
				model.setCreateTime(new Date());
				model.setIpAddr(IpUtil.getIpAddr(getRequest()));
				LogUtil.saveUserLog(model);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("/index");
	}
	
	/**
	 * 登录成功跳转页
	 * @param theme 主题
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(String theme) {
		// 根据客户端指定的参数跳转至 不同的主题 如果未指定 默认:index
		if (StringUtils.isNotBlank(theme) && (theme.equals("app") || theme.equals("index"))) {
			return "layout/" + theme;
		} else {
			return "layout/index";
		}
	}
	
}
