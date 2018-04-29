package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.model.AreaModel;
import com.kangde.commons.service.BaseService;

/**
 * 区域service
 * @author lisuo
 *
 */
public interface AreaService extends BaseService<AreaModel,String>{
	
	/**
	 * 查询区域,状态为未绑定的
	 * @param includeIds 包含的ID（回显使用）
	 * @return
	 */
	List<AreaModel> findAreas(List<String> includeIds);

	List<AreaModel> findByOrganizationId(String orgId);
	List<AreaModel> findAllArea(String orgId);
	/**根据机构id查询本机构所属分公司的覆盖区域 */
	List<String>  queryAreaByOrgId(String orgId);

}
