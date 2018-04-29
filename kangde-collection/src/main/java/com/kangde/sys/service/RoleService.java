package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.model.RoleModel;

/**
 * 角色Service
 * @author lisuo
 *
 */
public interface RoleService extends BaseService<RoleModel,String>{
	/**
	 * 通过角色ID,查询资源Id
	 * @param roleId
	 * @return List 资源ID
	 */
	public List<String> findResourceIdsByRoleId(String roleId);

	/**
	 * 通过用户ID,查询角色信息
	 * @param userId
	 * @return List 资源ID
	 */
	List<RoleModel> findRolesByUserId(String userId);
	
	/**
	 * 获取角色编码
	 * @param userId：用户id  condition：过滤条件
	 */
	ParamCondition getOrgCode(String userId,ParamCondition condition);
	
	/**
	 * 获取角色编码
	 * @param userId：用户id 
	 */
	boolean getOrgCodeTF(String userId );
}
