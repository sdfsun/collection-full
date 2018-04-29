package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.dto.UserResourceRelationDto;
import com.kangde.sys.dto.UserRoleRelationDto;
import com.kangde.sys.model.EmployeeInfoModel;
/**
 * 员工信息mapper
 * @author zhangyj
 * @date 2016.5.5
 *
 */
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfoModel,String> {
	
	/**
	 * 保存用户-角色-关系
	 * @param userRole
	 * @return
	 */
	int saveUserRoleRelation(UserRoleRelationDto userRole);
	
	/**
	 * 删除用户-角色-关系
	 * @param userId 用户ID
	 * @return
	 */
	int deleteUserRoleRelation(String userId);
	
	/**
	 * 通过用户ID,查询角色Id
	 * @param userId
	 * @return
	 */
	List<String> findRoleIdsByUserId(String userId);
	
	/**
	 * 保存用户-资源-关系
	 * @param userRole
	 * @return
	 */
	int saveUserResourceRelation(UserResourceRelationDto userResource);
	
	/**
	 * 删除用户-资源-关系
	 * @param userId 用户ID
	 * @return
	 */
	int deleteUserResourceRelation(String userId);
	
	/**
	 * 通过用户ID,查询资源Id
	 * @param userId
	 * @return
	 */
	List<String> findResourceIdsByUserId(String userId);
	

	/**
	 * 获取组织机构负责人可用列表
	 * @param userId 不排除的用户ID
	 * @param orgId 限制的机构ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsForOrg(Map<String, Object> params);

	/**
	 * 通过组织机构查询用户信息
	 * @param orgId 限制的机构ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByOrg(Map<String, Object> params);
	/**
	 * 通过岗位查询用户信息
	 * @param orgId 限制的机构ID
	 * @return
	 */
	List<EmployeeInfoModel> findEmpsByPos(Map<String, Object> params);
	
	/**
	 * 启用 停用 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(Map<String, Object> params);

	/**
	 * 通过登录名称查询用户
	 * @param loginName
	 * @return
	 */
	EmployeeInfoModel findByLoginName(String loginName);

	void updateUserPassword(EmployeeInfoModel model);
}