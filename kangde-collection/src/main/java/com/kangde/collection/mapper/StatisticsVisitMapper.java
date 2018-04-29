package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.dto.StatisticsVisitDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 统计mapper
 * 
 * @author zhangyj
 *
 */
public interface StatisticsVisitMapper extends BaseMapper<StatisticsVisitDto, String> {
	
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	List<StatisticsVisitDto> queryVisit(ParamCondition condition);
	
	/** 导出所选委托方*/
	List<StatisticsVisitDto> queryExport(List<String> list);
	
}