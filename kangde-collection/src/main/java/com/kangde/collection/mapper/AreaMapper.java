package com.kangde.collection.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.collection.model.AreaModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 区域表mapper
 * 
 * @author lisuo
 *
 */
public interface AreaMapper extends BaseMapper<AreaModel, String> {
	/**
	 * 查询区域,状态为未绑定的
	 * @param includeIds 包含的ID逗号分割（回显使用）
	 * @return
	 */
	List<AreaModel> findAreas(List<String> includeIds);
	
	/**
	 * 查询区域,通过区域名称
	 * @param name 区域名称
	 * @return
	 */
	List<AreaModel> findByName(String name);

	/**
	 * 通过组织机构ID查询区域
	 * @param id
	 * @return
	 */
	List<AreaModel> findByOrganizationId(String organizationId);

	/**
	 * 更新区域对应的机构
	 * @param areaIds 区域ID集合
	 * @param organizationId 机构ID
	 * @return
	 */
	int updateOrganization(Map<String,Object> params);
	
	AreaModel findSize(AreaModel model);
	
	List<AreaModel> findSize1(String name);

	List<AreaModel> findAllArea(String id);
	
	
}