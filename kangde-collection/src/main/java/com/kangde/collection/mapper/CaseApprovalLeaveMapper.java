package com.kangde.collection.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.dto.VisitDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
/**
 * 审批mapper
 * @author wcy
 * @date 2016年5月24日17:40:42
 *
 */
public interface CaseApprovalLeaveMapper extends BaseMapper<ApproveDto,String> {
	/** 查询申请外访数据*/
	List<ApproveDto> queryforLeave(ParamCondition condition);
	int approvalYes(Map<String, Object> params);
	List<ApproveRecordModel> findIds(List<String> list);
	
}