package com.kangde.collection.service;

import java.util.List;
import java.util.Map;

import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.dto.CasePaidDto;
import com.kangde.collection.model.CasePaid;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
public interface CasePaidService extends BaseService<CasePaid,String> {
	
	/**
	 * 显示还款
	 * @param condition
	 * @return
	 */
	public SearchResult<CasePaidDto> queryPaid(ParamCondition condition);
	
	public List<CasePaidDto> queryPaidall(ParamCondition condition);
	
	
	public List<CasePaidDto> queryAll(ParamCondition condition);
	
	CasePaid savezlz(CasePaid model);
	/**
	 * 更改状态
	 * @param model
	 * @return
	 */
	public int updateForState(CasePaid model);

	public void toCP(String id);

	public SearchResult<CasePaid> queryPTPorCPForPage(ParamCondition condition);
	
	List<CasePaidDto> queryForIds(ParamCondition condition);

	public Map<String, Object> queryStatistics(ParamCondition condition);

	public int queryToConfirmPayCount();
}
