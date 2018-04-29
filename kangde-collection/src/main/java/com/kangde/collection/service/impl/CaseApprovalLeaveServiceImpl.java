package com.kangde.collection.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.mapper.CaseApprovalLeaveMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.service.CaseApprovalLeaveService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 审批 Service实现类
 * 
 * @author wcy
 * @date 2016年5月24日17:40:26
 */
@Service("caseApprovalLeaveService")
public class CaseApprovalLeaveServiceImpl extends AbstractService<ApproveDto, String> implements CaseApprovalLeaveService {

	@Autowired
	private CaseApprovalLeaveMapper caseApprovalLeaveMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CaseService caseService;
	@Autowired
	private MessageReminderService messageReminderService;

	/** 查询申请外访数据 */
	@Override
	public SearchResult<ApproveDto> queryforLeave(ParamCondition condition) {
		String getid = condition.get("caseAssignedId");
		String attachEntrustId = SecurityUtil.getCurrentUser().getAttachEntrustId();
		//附加区域
		if(StringUtils.isNotBlank(attachEntrustId)){
			condition.put("attachEntrustId", SQLUtil.warpSql(attachEntrustId));
		}
		//员工id
		if (StringUtils.isNotBlank(getid)) {
			String caseAssignedIds = SQLUtil.warpSql(getid);
			condition.put("caseAssignedIds", caseAssignedIds);
		}
		// 案件序列号
		// 需要算出剩余金额，
		// 公式： 委案金额减去已还款
		
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachOrgId = currentUser.getAttachOrgId();
		String queryOrgs=currentUser.getOrgId()+",";
		if(StringUtils.isNotBlank(attachOrgId)){
			queryOrgs=queryOrgs+attachOrgId;
		}
		
		condition.put("queryOrgs", SQLUtil.warpSql(queryOrgs));
		condition.put("loginName", currentUser.getLoginName());
		if(StringUtils.isNotBlank(currentUser.getAttachEntrustId())){
			condition.put("attachEntrustId", SQLUtil.warpSql(currentUser.getAttachEntrustId()));
		}
		
		List<ApproveDto> list = caseApprovalLeaveMapper.queryforLeave(condition);
		SearchResult<ApproveDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	
	@Override
	public List<ApproveDto> queryforLeaveList(ParamCondition condition) {
		return caseApprovalLeaveMapper.queryforLeave(condition);
	}
	
	/** 批量修改外访数据 */
	@Override
	public int approvalYes(ApproveRecordModel model) {
		Map<String, Object> params = new HashMap<>(2);
		String[] ids = model.getId().split(",");
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		int upstate = caseApprovalLeaveMapper.approvalYes(params);// 执行批量修改
		if (upstate > 0) {// 如果修改成功，添加操作记录。
			/** 根据外访Id查询案件id */
			List<ApproveRecordModel> caseIds = caseApprovalLeaveMapper.findIds(Arrays.asList(ids));//审批勾选的数据信息
			if (caseIds.size() != 0) {
				for (int i = 0; i < caseIds.size(); i++) {
					ApproveRecordModel m = caseIds.get(i);
					CaseModel caseModel = caseService.findById(m.getCaseId());//去查询案件
					if(model.getApproveState().equals(1)){//留案成功后修改案件时间，不通过的话 就不让进了。
					caseModel.setCaseBackdate(model.getStayPeriod());
					caseService.update(caseModel);//留案通过修改案件的预计退案日期
					}
					 // 操作记录表id
					String state = "";// 审批状态
					if (1 == model.getApproveState()) {
						state = "审批通过";
					} else if (2 == model.getApproveState()) {
						state = "审批不通过";
					} 
					String content = "[留案审批]案件序列号：" + caseModel.getCaseCode() + ",审批动作:" + state;
				
					
					HurryRecordModel hr=OperationUtil.Operation("casM",m.getCaseId(),content,m.getId());
					hurryRecordMapper.save(hr);
					String message = "";
					if (1 == model.getApproveState()) {
						message = "已通过留案审批";
					} else if (2 == model.getApproveState()) {
						message = "未通过留案审批";
					} 
					messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
					
				}
			}
		}
		return upstate;
	}
	

}
