package com.kangde.collection.service;

import com.kangde.collection.vo.CaseJointDebtVo;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
public interface CaseJointDebtService {
	public SearchResult<CaseJointDebtVo> query(ParamCondition condition);
}
