package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEmployeeDto;
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
public interface StatisticsEmployeeMapper extends BaseMapper<StatisticsEmployeeDto, String> {
	
	/**
	 * 风控y统计
	 * @param condition
	 * @return
	 */
	List<StatisticsEmployeeDto> queryEmployee(ParamCondition condition);
	/** 导出所选批次*/
	List<StatisticsEmployeeDto> queryExport(List<String> list);

	List<StatisticsEmployeeDto> queryEmployeeOrganization(ParamCondition condition);

	List<StatisticsEmployeeDto> queryEmployeePaid(ParamCondition condition);

	List<StatisticsEmployeeDto> queryEmployeeCaseState(ParamCondition condition);
	
	
}