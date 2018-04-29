package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.StatisticsEmployeeDto;
import com.kangde.collection.dto.StatisticsEmployeeDto;
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
public interface StatisticsEmployeeService extends BaseService<StatisticsEmployeeDto,String> {
	
	/**
	 * 统计列表
	 * @param condition
	 * @return
	 */
	public SearchResult<StatisticsEmployeeDto> queryEmployee(ParamCondition condition);
	
	/** 导出所选  */
	List<StatisticsEmployeeDto> queryExport(List<String> ids);
	/** 导出所查  */
	List<StatisticsEmployeeDto> exportQueryExcel(ParamCondition condition);

	public SearchResult<StatisticsEmployeeDto> queryEmployeeCaseState(ParamCondition condition);

	public SearchResult<StatisticsEmployeeDto> queryEmployeeOrganization(ParamCondition condition);

	public SearchResult<StatisticsEmployeeDto> queryEmployeePaid(ParamCondition condition);
	
}
