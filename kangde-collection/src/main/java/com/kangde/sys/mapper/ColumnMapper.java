package com.kangde.sys.mapper;

import java.util.List;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.dto.UserColumnRelationDto;
import com.kangde.sys.model.ColumnModel;

/**
 * 动态列 Mapper
 * 
 * @author lisuo
 *
 */
public interface ColumnMapper extends BaseMapper<ColumnModel, String> {
	
	/**
	 * 通过用户ID查询案件动态列
	 * @param userId 用户ID
	 * @return 
	 */
	List<ColumnModel> findCaseColumnsByUserId(String userId);
	
	/**
	 * 查询案件所有导出列
	 * @return 
	 */
	List<ColumnModel> findCaseColumns();
	
	/**
	 * 查询案件所有列
	 * @return 
	 */
	List<ColumnModel> findCaseColumnsNotDC();
	
	
	/**
	 * 查询外放所有列
	 * @return 
	 */
	List<ColumnModel> findVisitRecord();
	
	
	/**
	 * 查询风控所有列
	 * @return 
	 */
	List<ColumnModel> findCheckboxcollection();
	
	
	/**
	 * 查询案件所有列
	 * 给模板管理提供查询方法。
	 * wcy
	 * date 2016年6月16日10:04:31
	 * @return 
	 */
	List<ColumnModel> findPhoneRecordColumns();
	
	/**
	 * 查询案件选中列
	 * 给模板管理提查询选中列方法
	 * wcy
	 * date 2016年6月17日11:16:26
	 * @return 
	 */
	List<ColumnModel> findOk(String SysColumnIds);

	/**
	 * 查询催记选中列
	 * 给模板管理提查询选中列方法
	 * wcy
	 * date 2016年6月17日11:16:26
	 * @return 
	 */
	List<ColumnModel> findOkCollection(String SysColumnIds);
	
	/**
	 * 保存用户动态列--关联关系
	 * @param userColumnRelation
	 * @return
	 */
	int saveUserColumnRelation(UserColumnRelationDto userColumnRelation);
	
}
