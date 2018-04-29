package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 *  审批Service  
 * @author wcy
 * @date 2016年5月24日17:39:06
 */
public interface CaseApprovalService extends BaseService<CaseDto,String>{
	
	SearchResult<CaseDto> queryforGo(ParamCondition condition);
	
	SearchResult<CaseDto> queryforLeave(ParamCondition condition);
	
	public int updatefoApp(ApproveRecordModel model);
	
	public int updatefoVi(VisitRecordModel model);
	
	public int saveApproveRecord(ApproveRecordModel model);

	SearchResult<ApproveRecordModel> queryStayByCaseId(ParamCondition condition);

	List<ApproveRecordModel> findByCaseId(String caseId);
}
