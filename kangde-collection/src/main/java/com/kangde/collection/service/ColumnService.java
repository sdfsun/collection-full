package com.kangde.collection.service;

import java.util.List;

import com.kangde.commons.easyui.Column;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.ColumnModel;

/**
 * 动态列service
 * 
 * @author lisuo
 *
 */
public interface ColumnService extends BaseService<ColumnModel, String> {
	/**
	 * 通过用户ID查询案件动态列
	 * @param userId 用户ID
	 * @return 
	 */
	List<Column> findCaseColumnsByUserId(String userId);
	
}
