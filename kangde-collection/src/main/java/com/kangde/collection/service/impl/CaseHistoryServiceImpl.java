package com.kangde.collection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CaseHistoryModelMapper;
import com.kangde.collection.model.CaseHistoryModel;
import com.kangde.collection.service.CaseHistoryService;
import com.kangde.commons.service.AbstractService;

@Service
public class CaseHistoryServiceImpl extends AbstractService<CaseHistoryModel, String> implements CaseHistoryService {

	@Autowired
	private CaseHistoryModelMapper caseHistoryModelMapper;
	@Override
	public int saveCaseHistory(CaseHistoryModel model) {
		// TODO Auto-generated method stub
		return caseHistoryModelMapper.save(model);
	}

}
