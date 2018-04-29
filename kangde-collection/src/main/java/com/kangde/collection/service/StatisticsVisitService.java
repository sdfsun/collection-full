package com.kangde.collection.service;

import java.util.List;

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
public interface StatisticsVisitService extends BaseService<StatisticsVisitDto,String> {
	
	/**
	 * 显示外访统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsVisitDto> queryVisit(ParamCondition condition);
	
	/** 导出所选外放  */
	List<StatisticsVisitDto> queryExport(List<String> ids);
	/** 导出所查外放  */
	List<StatisticsVisitDto> exportQueryExcel(ParamCondition condition);
}
