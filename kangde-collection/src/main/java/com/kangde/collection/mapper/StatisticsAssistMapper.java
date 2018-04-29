package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.StatisticsAssistDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 统计mapper
 * 
 * @author zhangyj
 *
 */
public interface StatisticsAssistMapper extends BaseMapper<StatisticsAssistDto, String> {
	
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	List<StatisticsAssistDto> queryAssist(ParamCondition condition);
	
	List<StatisticsAssistDto> queryExport(List<String> list);
	
	
}