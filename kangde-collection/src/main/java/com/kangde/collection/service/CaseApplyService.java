package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.model.CaseApply;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

public interface CaseApplyService extends BaseService<CaseApply, String> {

	void applyToLetter(String addressId, String caseId) throws AddressException;
	
	/**
	 * 查询信函
	 * @param condition
	 * @return
	 */
	SearchResult<CaseApply> queryLetter(ParamCondition condition);

	/**
	 * 修改申请状态
	 * @param state 状态
	 * @param ids ID集
	 * @param approvalOpinion 审批意见
	 */
	void changeState(Integer state, List<String> ids, String approvalOpinion);

	List<VisitShowView> findVisitShowViewByIds(List<String> asList);
	
	/**
	 * 导出所查
	 * @param condition
	 * @return
	 */
	List<CaseApply> queryExcelAll(ParamCondition condition);
	
	
	/**
	 * 导出所所选
	 * @param condition
	 * @return
	 */
	List<CaseApply> queryForIds(List<String> ids);

	/**批量保存查询申请*/
	void batchSave(String[] ids, String applyContent,Integer applyType);

	public SearchResult<CaseApply> queryMaterialByCaseId(ParamCondition condition);

	SearchResult<CaseApply> querycasedetailxiecui(ParamCondition condition);

	SearchResult<CaseApply> queryLetterByCaseId(ParamCondition condition);

	int queryLetterCount(int applyState);

	int queryXiecuiTasklistCount();
	
}
