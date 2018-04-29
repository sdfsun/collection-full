package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.vo.PhoneRecordVo;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

public interface PhoneRecordService {
	List<PhoneRecordModel> query(String caseId);
	List<PhoneRecordModel> queryByCaseId(String caseId);
	SearchResult<PhoneRecordVo> queryForPage(ParamCondition condition);
}
