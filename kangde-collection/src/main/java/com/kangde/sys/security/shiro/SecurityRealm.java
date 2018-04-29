package com.kangde.sys.security.shiro;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.kangde.commons.CoreConst;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.PositionModel;
import com.kangde.sys.model.ResourceModel;
import com.kangde.sys.model.RoleModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.PositionService;
import com.kangde.sys.service.ResourceService;
import com.kangde.sys.service.RoleService;

/**
 * Shiro自定义realm实现登录和权限（资源）授权
 * 
 * @author lisuo
 *
 */
public class SecurityRealm extends AuthorizingRealm {

	@Lazy
	@Autowired
	private EmployeeInfoService employeeInfoService;
	
	@Lazy
	@Autowired
	private ResourceService resourceService;
	
	@Lazy
	@Autowired
	private RoleService roleService;
	
	@Lazy
	@Autowired
	private PositionService positionService;
	/**
	 * 授权,登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中取出用户名
		String username = (String) token.getPrincipal();
		//判断是否是超级管理员登录
		if(SecurityUtil.isSuperAdminLoginName(username)){
			EmployeeInfoModel admin = new EmployeeInfoModel();
			admin.setId(username);
			admin.setUserName(CoreConst.SYSTEM_ADMIN_NAME);
			admin.setLoginName(username);
			admin.setPassword(CoreConst.SYSTEM_ADMIN_PASSWORD);
			admin.setSalt(CoreConst.SYSTEM_ADMIN_SALT);
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(),
					ByteSource.Util.bytes(admin.getSalt()), getName());
			return simpleAuthenticationInfo;
		}else{
			// 根据用户输入的username从数据库查询
			EmployeeInfoModel employee = employeeInfoService.findByLoginName(username);
			if (employee == null) {
				return null;
			}
			if(CoreConst.STATUS_DELETE.equals(employee.getStatus())){
				throw new LockedAccountException();
			}
			PositionModel position = positionService.findById(employee.getPositionId());
			employee.setPositionModel(position);
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(employee, employee.getPassword(),
					ByteSource.Util.bytes(employee.getSalt()), getName());
			return simpleAuthenticationInfo;
		}
	}

	/**
	 * 认证,权限分配
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal = principals.getPrimaryPrincipal();
		EmployeeInfoModel employee = (EmployeeInfoModel) principal;
		String userId = employee.getId();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		//超级管理员判断
		if(SecurityUtil.isSuperAdmin(employee)){
			auth.addStringPermission("*");//为管理员添加所有权限
		}else{
			//查询用户所有权限
			List<ResourceModel> resources = resourceService.findResourceByUserId(userId);
			if(CollectionUtils.isNotEmpty(resources)){
				for(ResourceModel r:resources){
					if(StringUtils.isNotBlank(r.getCode())){
						auth.addStringPermission(r.getCode());
					}
				}
			}
		}
		return auth;
	}
	
	@Override
	public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
		Object principal = principals.getPrimaryPrincipal();
		EmployeeInfoModel employee = (EmployeeInfoModel) principal;
		if(SecurityUtil.isSuperAdmin(employee)) return false;
		String userId = employee.getId();
		List<RoleModel> roles = roleService.findRolesByUserId(userId);
		if(CollectionUtils.isNotEmpty(roles)){
			for(RoleModel r:roles){
				if(StringUtils.isNotBlank(r.getRoleCode())){
					if(r.getRoleCode().equals(roleIdentifier)){
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * 清除缓存
	 */
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
