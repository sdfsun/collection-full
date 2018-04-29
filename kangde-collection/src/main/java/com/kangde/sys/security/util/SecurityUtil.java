package com.kangde.sys.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.kangde.commons.CoreConst;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 安全工具类
 * @author lisuo
 *
 */
public abstract class SecurityUtil {
	
	/**
	 * 判断用户是否是超级管理员（true是：false：不是）
	 * @return 判断用户是否是超级管理员（true是：false：不是）
	 */
	public static final boolean isSuperAdmin(){
		EmployeeInfoModel currentUser = getCurrentUser();
		if(currentUser!=null){
			return CoreConst.SUPER_ADMIN_LOGIN_NAME.equals(currentUser.getLoginName());
		}
		return false;
	}
	
	/**
	 * 判断用户是否是超级管理员（true是：false：不是）
	 * @param currentUser 当前用户信息
	 * @return 判断用户是否是超级管理员（true是：false：不是）
	 */
	public static final boolean isSuperAdmin(EmployeeInfoModel currentUser){
		if(currentUser == null) return false;
		return CoreConst.SUPER_ADMIN_LOGIN_NAME.equals(currentUser.getLoginName());
	}
	
	/**
	 * 是否是超级管理员的用户名
	 * @param loginName
	 * @return
	 */
	public static final boolean isSuperAdminLoginName(String loginName){
		return CoreConst.SUPER_ADMIN_LOGIN_NAME.equals(loginName);
	}
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	public static EmployeeInfoModel getCurrentUser(){
		Subject subject = SecurityUtils.getSubject();
		Object user = subject.getPrincipal();
		if(user!=null){
			return (EmployeeInfoModel) user;
		}
		return null;
	}
	
	/**
	 * 检查用户是否有指定的权限
	 * @param permission
	 * @return 
	 */
	public static final boolean isPermitted(String permission){
		Subject subject = SecurityUtils.getSubject();
		return subject.isPermitted(permission);
	}
	
	
	/**
	 * 检查用户是指定的角色
	 * @param roleIdentifier
	 * @return 
	 */
	public static final boolean hasRole(String roleIdentifier){
		Subject subject = SecurityUtils.getSubject();
		return subject.hasRole(roleIdentifier);
	}
	
	
}
