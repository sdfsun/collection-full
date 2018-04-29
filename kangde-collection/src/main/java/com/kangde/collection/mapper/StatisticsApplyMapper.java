package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 统计mapper
 * 
 * @author zhangyj
 *
 */
public interface StatisticsApplyMapper extends BaseMapper<StatisticsApplyDto, String> {
	
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	List<StatisticsApplyDto> queryApply(ParamCondition condition);
	
	
	List<StatisticsApplyDto> queryExport(List<String> list);
	
}