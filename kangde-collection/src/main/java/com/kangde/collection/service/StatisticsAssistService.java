package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsAssistDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * 统计Service
 * 
 * @author zhangyj
 *
 */
public interface StatisticsAssistService extends BaseService<StatisticsAssistDto,String> {
	
	/**
	 * 显示统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsAssistDto> queryAssist(ParamCondition condition);
	
	List<StatisticsAssistDto> queryExport(List<String> ids);
	
	List<StatisticsAssistDto> exportQueryExcel(ParamCondition condition);
}
