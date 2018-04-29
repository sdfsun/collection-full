package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
/**
 * 审批mapper
 * @author wcy
 * @date 2016年5月24日17:40:42
 *
 */
public interface CaseApprovalMapper extends BaseMapper<CaseDto,String> {
	
	List<CaseDto> queryforGo(ParamCondition condition);
	List<CaseDto> queryforLeave(ParamCondition condition);
	int updatefoApp(ApproveRecordModel model);
	int updatefoVi(VisitRecordModel model);
	int saveOperate(HurryRecordModel model);
	int insert(ApproveRecordModel record);
	CaseDto findStayTime(String caseId);
	List<ApproveRecordModel> queryStay(ParamCondition condition);
	List<ApproveRecordModel> findByCaseId(String caseId);
	
}