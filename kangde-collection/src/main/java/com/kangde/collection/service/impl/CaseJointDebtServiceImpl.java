package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.service.CaseJointDebtService;
import com.kangde.collection.vo.CaseJointDebtVo;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
@Service
public class CaseJointDebtServiceImpl implements CaseJointDebtService{
	@Autowired
	private CaseMapper caseMapper;
	@Override
	public SearchResult<CaseJointDebtVo> query(ParamCondition condition) {
		List<CaseJointDebtVo> list = caseMapper.queryCaseJointDebts(condition);
		SearchResult<CaseJointDebtVo> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	
	
	
	
}
