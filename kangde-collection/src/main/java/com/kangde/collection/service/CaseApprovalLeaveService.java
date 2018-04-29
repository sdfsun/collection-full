package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 *  审批Service  
 * @author wcy
 * @date 2016年5月24日17:39:06
 */
public interface CaseApprovalLeaveService extends BaseService<ApproveDto,String>{
	
	SearchResult<ApproveDto> queryforLeave(ParamCondition condition);
	List<ApproveDto> queryforLeaveList(ParamCondition condition);
	int approvalYes(ApproveRecordModel model);
	
	
}
