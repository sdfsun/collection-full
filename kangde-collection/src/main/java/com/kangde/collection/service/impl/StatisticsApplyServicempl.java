package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.mapper.StatisticsApplyMapper;
import com.kangde.collection.service.StatisticsApplyService;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;


@Service
public class StatisticsApplyServicempl extends AbstractService<StatisticsApplyDto,String> implements StatisticsApplyService {

	
	
	@Autowired
	private StatisticsApplyMapper statisticsMapper;

	@Override
	public SearchResult<StatisticsApplyDto> queryApply(ParamCondition condition) {
		List<StatisticsApplyDto> list =statisticsMapper.queryApply(condition);
		SearchResult<StatisticsApplyDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	

	@Override
	public List<StatisticsApplyDto> queryExport(List<String> ids) {
		List<StatisticsApplyDto> list = statisticsMapper.queryExport(ids);
		return list;
	}
	
	@Override
	public List<StatisticsApplyDto> exportQueryExcel(ParamCondition condition) {
		List<StatisticsApplyDto> list =statisticsMapper.queryApply(condition);
		return list;
	}

}
