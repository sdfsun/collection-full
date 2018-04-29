package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * 统计Service
 * 
 * @author zhangyj
 *
 */
public interface StatisticsService extends BaseService<StatisticsBatchDto,String> {
	
	/**
	 * 显示批次统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsBatchDto> queryBatch(ParamCondition condition);
	
	/** 导出所选委托方  */
	List<StatisticsBatchDto> queryExport(List<String> ids);
	/** 导出所查委托方  */
	List<StatisticsBatchDto> exportQueryExcel(ParamCondition condition);
	/**
	 * 显示委托方统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsEntrustDto> queryEntrust(ParamCondition condition);
	
	/**
	 * 显示风控方统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsOrganizationDto> queryCollection(ParamCondition condition);

	public SearchResult<StatisticsBatchDto> queryBatchCaseState(ParamCondition condition);

	public SearchResult<StatisticsBatchDto> queryBatchOrganization(ParamCondition condition);

	public SearchResult<StatisticsBatchDto> queryBatchPaid(ParamCondition condition);
	
}
