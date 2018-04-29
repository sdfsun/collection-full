package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 统计mapper
 * 
 * @author zhangyj
 *
 */
public interface StatisticsEntrustMapper extends BaseMapper<StatisticsEntrustDto, String> {
	
	/**
	 * 委托方统计
	 * @param condition
	 * @return
	 */
	List<StatisticsEntrustDto> queryEntrust(ParamCondition condition);
	
	/** 导出所选委托方*/
	List<StatisticsEntrustDto> queryExport(List<String> list);
	
	/**
	 * 委托方基础统计
	 * @param condition
	 * @return
	 */
	List<StatisticsEntrustDto> queryEntrustPaid(ParamCondition condition);

	/**
	 * 委托方风控投入统计
	 * @param condition
	 * @return
	 */
	List<StatisticsEntrustDto> queryEntrustOrganization(ParamCondition condition);

	/**
	 * 委托方案件状态统计
	 * @param condition
	 * @return
	 */
	List<StatisticsEntrustDto> queryEntrustCaseState(ParamCondition condition);
	
}