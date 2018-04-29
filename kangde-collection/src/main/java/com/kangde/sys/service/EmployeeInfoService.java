package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 员工信息 Service
 * @author zhangyj
 * @date 2016.5.5
 */
public interface EmployeeInfoService extends BaseService<EmployeeInfoModel,String>{

	/**
	 * 登录名称查询
	 * @param username
	 * @return
	 */
	EmployeeInfoModel findByLoginName(String loginName);

	/**
	 * 编辑用户角色信息
	 * @param userId 用户ID
	 * @param roleIds 角色ID
	 */
	void editRole(String userId,List<String> roleIds);

	/**
	 * 通过用户ID查询角色ID
	 * @param userId 用户ID
	 * @return List 角色ID
	 */
	List<String> findRoleIdsByUserId(String userId);
	
	/**
	 * 编辑用户角色信息
	 * @param userId 用户ID
	 * @param resourceIds 资源ID
	 */
	void editResource(String userId,List<String> resourceIds);
	
	/**
	 * 通过用户ID查询资源ID
	 * @param userId 用户ID
	 * @return List 资源ID
	 */
	List<String> findResourceIdsByUserId(String userId);
	
	/**
	 * 获取组织机构负责人可用列表
	 * @param userId 不排除的用户ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsForOrg(String userId,EmployeeInfoModel employee);

	/**
	 * 通过组织机构查询用户信息
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByOrg(EmployeeInfoModel employeeInfo);
	/**
	 * 通过组织机构查询用户信息
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByPos(EmployeeInfoModel employeeInfo);

	/**
	 * 获取部门用户信息,以combotree形式展示,如果是超级管理员,获取所有用户,否则返回当前用户机构对应的数据
	 * @return
	 */
	List<TreeNode> orgUserCombotree(EmployeeInfoModel employee);

	/**
	 * 启用 停用
	 * @param model
	 * @return
	 */
	public int updateForStatus(String[] ids);
	
	public int updateForStatusNo(String[] ids);
	
	/**
	 * 通过组织机构查询用户信息
	 * @param orgId 机构ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByOrg(String orgId);
	
	/**
	 * 通过组织机构查询用户信息
	 * @param orgId 机构ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByPos(String posId);

	/**
	 * 更新用户密码
	 * @param currentUser
	 * @param password
	 * @param newPassword
	 * @param ccPwd 
	 */
	void updateUserPassword(EmployeeInfoModel currentUser, String password, String newPassword, String ccPwd);
}
