package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.PhoneRecordMapper;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.service.PhoneRecordService;
import com.kangde.collection.vo.PhoneRecordVo;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

@Service
public class PhoneRecordServiceImpl implements PhoneRecordService {

	@Autowired
	private PhoneRecordMapper phoneRecordMapper;

	@Override
	public List<PhoneRecordModel> query(String caseId) {
		return phoneRecordMapper.selectByCaseId(caseId);
	}
	
	@Override
	public List<PhoneRecordModel> queryByCaseId(String caseId) {
		return phoneRecordMapper.queryByCaseId(caseId);
	}

	

	@Override
	public SearchResult<PhoneRecordVo> queryForPage(ParamCondition condition) {
		List<PhoneRecordVo> list = phoneRecordMapper.queryPhoneRecordByCondition(condition);
		SearchResult<PhoneRecordVo> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
		
	}

}
