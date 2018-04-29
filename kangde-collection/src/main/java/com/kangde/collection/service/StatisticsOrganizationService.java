package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 统计Service
 * 
 * @author zhangyj
 *
 */
public interface StatisticsOrganizationService extends BaseService<StatisticsOrganizationDto,String> {

//	List<TreeNode> getOrganizationTreeJoinAttachedOrgs(EmployeeInfoModel currentUser);
	/**
	 * 显示风控方统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsOrganizationDto> queryCollection(ParamCondition condition);
	
	/** 导出所选分公司  */
	List<StatisticsOrganizationDto> queryExport(List<String> ids);
	/** 导出所查分公司  */
	List<StatisticsOrganizationDto> exportQueryExcel(ParamCondition condition);

	public SearchResult<StatisticsOrganizationDto> queryOrganizationOrganization(ParamCondition condition);

	public SearchResult<StatisticsOrganizationDto> queryOrganizationPaid(ParamCondition condition);

	public SearchResult<StatisticsOrganizationDto> queryOrganizationCaseState(ParamCondition condition);

	
}
