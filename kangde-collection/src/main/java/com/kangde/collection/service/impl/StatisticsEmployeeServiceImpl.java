package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.StatisticsEmployeeDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.mapper.StatisticsEmployeeMapper;
import com.kangde.collection.mapper.StatisticsMapper;
import com.kangde.collection.service.StatisticsEmployeeService;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;


@Service
public class StatisticsEmployeeServiceImpl extends AbstractService<StatisticsEmployeeDto,String> implements StatisticsEmployeeService {

	
	
	@Autowired
	private StatisticsEmployeeMapper statisticsMapper;

	@Override
	public SearchResult<StatisticsEmployeeDto> queryEmployee(ParamCondition condition) {
		List<StatisticsEmployeeDto> list =statisticsMapper.queryEmployee(condition);
		SearchResult<StatisticsEmployeeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public SearchResult<StatisticsEmployeeDto> queryEmployeeCaseState(ParamCondition condition) {
		List<StatisticsEmployeeDto> list =statisticsMapper.queryEmployeeCaseState(condition);
		SearchResult<StatisticsEmployeeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public SearchResult<StatisticsEmployeeDto> queryEmployeePaid(ParamCondition condition) {
		List<StatisticsEmployeeDto> list =statisticsMapper.queryEmployeePaid(condition);
		SearchResult<StatisticsEmployeeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public SearchResult<StatisticsEmployeeDto> queryEmployeeOrganization(ParamCondition condition) {
		List<StatisticsEmployeeDto> list =statisticsMapper.queryEmployeeOrganization(condition);
		SearchResult<StatisticsEmployeeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	/** 导出所选委托方 */
	@Override
	public List<StatisticsEmployeeDto> queryExport(List<String> ids) {
		List<StatisticsEmployeeDto> list = statisticsMapper.queryExport(ids);
		return list;
	}
	
	/** 导出所查委托方 */
	@Override
	public List<StatisticsEmployeeDto> exportQueryExcel(ParamCondition condition) {
		List<StatisticsEmployeeDto> list =statisticsMapper.queryEmployee(condition);
		return list;
	}


}
