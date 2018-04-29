package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsVisitDto;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * 统计Service
 * 
 * @author zhangyj
 *
 */
public interface StatisticsApplyService extends BaseService<StatisticsApplyDto,String> {
	
	/**
	 * 显示外访统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsApplyDto> queryApply(ParamCondition condition);
	
	/** 导出所选协催  */
	List<StatisticsApplyDto> queryExport(List<String> ids);
	
	/** 导出所选  */
	List<StatisticsApplyDto> exportQueryExcel(ParamCondition condition);
	
}
