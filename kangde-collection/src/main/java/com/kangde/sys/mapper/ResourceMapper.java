package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.ResourceModel;

/**
 * 资源菜单Mapper
 * @author lisuo
 *
 */
public interface ResourceMapper extends BaseMapper<ResourceModel,String>{
	
	/**
	 * 通过父ID,资源类型查询,仅供超级管理员使用
	 * @param params
	 * @return
	 */
	List<ResourceModel> findParent(Map<String,Object> params);
	
	/**
	 * 查询所有顶级节点资源信息,仅供超级管理员使用
	 * @param type 菜单类型
	 * @return
	 */
	List<ResourceModel> findTops(Integer type);

	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();
	
	
	//------------------------权限相关------------------------//
	/**
	 * 通过用户ID,资源类型:查询顶级菜单
	 * @param userId 用户ID
	 * @return List<菜单>
	 */
	List<ResourceModel> findTopsByUser(Map<String, Object> params);
	
	/**
	 * 通过用户ID和资源ID查询顶级菜单节点
	 * @param params
	 * @return
	 */
	List<ResourceModel> findParentByUser(Map<String,Object> params);

	/**
	 * 通过用户ID,查询所有资源
	 * @param userId
	 * @return
	 */
	List<ResourceModel> findByUserId(String userId);
	
	
}
