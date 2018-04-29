package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.HelpMeDto;
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
public interface StatisticsEntrustService extends BaseService<StatisticsEntrustDto,String> {
	
	/**
	 * 显示委托方佣金统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsEntrustDto> queryEntrust(ParamCondition condition);
	public List<StatisticsEntrustDto> exportQueryExcel(ParamCondition condition);
	
	List<StatisticsEntrustDto> excelAll(ParamCondition condition);
	
	/** 导出所选委托方  */
	List<StatisticsEntrustDto> queryExport(List<String> ids);
	/**
	 * 显示委托方基础还款统计列表
	 * @param condition
	 * @return
	 */
   SearchResult<StatisticsEntrustDto> queryEntrustPaid(ParamCondition condition);
	/**
	 * 显示委托方风控投入统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsEntrustDto> queryEntrustOrganization(ParamCondition condition);
	/**
	 * 显示委托方案件状态统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsEntrustDto> queryEntrustCaseState(ParamCondition condition);
}
