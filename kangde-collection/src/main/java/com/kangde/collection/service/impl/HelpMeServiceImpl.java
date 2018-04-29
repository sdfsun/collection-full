package com.kangde.collection.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.dto.CaseApplyDto;
import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.mapper.HelpMeMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.CaseApply;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.model.ExpressModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.collection.model.TelecomModel;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.CensusRegisterService;
import com.kangde.collection.service.HelpMeService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.service.SocialSecurityService;
import com.kangde.collection.service.TelecomService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 待审批协催 ServiceImpl
 * 
 * @author wcy
 * @date 2016年7月19日10:58:33
 */
@Service("helpMeService")
public class HelpMeServiceImpl extends AbstractService<HelpMeDto, String> implements HelpMeService {

	@Autowired
	private HelpMeMapper helpMeMapper;
	@Autowired
	private CaseService caseService;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CensusRegisterService censusRegisterService;
	@Autowired
	private SocialSecurityService socialSecurityService;
	@Autowired
	private TelecomService telecomService;
	@Autowired
	private MessageReminderService messageReminderService;

	/** 待审批协催审批 */
	@Override
	public int agree(CaseApplyModel model,int bs) {
		Map<String, Object> params = new HashMap<>(2);
		String[] ids = model.getId().split(",");
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		int t=0;
		if(bs==2){
			t=	helpMeMapper.agree1(params);
		}else{
			t=	helpMeMapper.agree(params);
		}
		if(t>0){
			List<CaseApplyModel> caseIds = helpMeMapper.findIds(Arrays.asList(ids));//审批勾选的数据信息
				if(caseIds.size()!=0){
					for (int i = 0; i < caseIds.size(); i++) {
						CaseApplyModel m=caseIds.get(i);
						CaseModel caseModel = caseService.findById(m.getCaseId());//去查询案件
						
						if(bs==1){
						 // 操作记录表id
						String state = "";// 审批状态
						if (0 == model.getState()) {
							state = "审批通过";
						} else if (-1 == model.getState()) {
							state = "审批不通过";
						} else if(3==model.getState()){
							state = "驳回";
						}
					
						String content = "[查资审批]案件序列号：" + caseModel.getCaseCode() + ",审批动作:" + state;
						
						HurryRecordModel hr=OperationUtil.Operation("cHp",m.getCaseId(),content,m.getId());
						hurryRecordMapper.save(hr);
						}
						
						String reminderMessage="";
						if(bs==1){
							if (0 == model.getState()) {
								reminderMessage = "查资审批通过";
							} else if (-1 == model.getState()) {
								reminderMessage = "查资审批不通过";
							} else if(3==model.getState()){
								reminderMessage = "查资驳回";
							}
						}else if(bs==2){
							reminderMessage="已完成查资";
						}
						messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+reminderMessage, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
					}
				}
		}
		return t;
	}
	
	/** 户籍录入添加操作记录 */
	@Override
	public void hurryRecordHj(CensusRegisterModel model){
		CaseApplyModel caseId=helpMeMapper.findByApplyId(model.getCaseApplyId());//为了拿到caseid
		String content = "[导入户籍]";
		HurryRecordModel hr=OperationUtil.Operation("cHp",caseId.getCaseId(),content,model.getId());
		hurryRecordMapper.save(hr);
		
	}
	
	/** 社保录入添加操作记录 */
	@Override
	public void hurryRecordSB(SocialSecurityModel model){
		CaseApplyModel caseId=helpMeMapper.findByApplyId(model.getCaseApplyId());//为了拿到caseid
		String content = "[导入社保]：";
		HurryRecordModel hr=OperationUtil.Operation("cHp",caseId.getCaseId(),content,model.getId());
		hurryRecordMapper.save(hr);
		
	}
	
	/** 社保录入添加操作记录 */
	@Override
	public void hurryRecordKD(ExpressModel model){
		CaseApplyModel caseId=helpMeMapper.findByApplyId(model.getCaseApplyId());//为了拿到caseid
		String content = "[导入社保]：";
		HurryRecordModel hr=OperationUtil.Operation("cHp",caseId.getCaseId(),content,model.getId());
		hurryRecordMapper.save(hr);
		
	}
	
	/** 电信录入添加操作记录 */
	@Override
	public void hurryRecordDX(TelecomModel model){
		CaseApplyModel caseId=helpMeMapper.findByApplyId(model.getCaseApplyId());//为了拿到caseid
		String content = "[导入电信]";
		HurryRecordModel hr=OperationUtil.Operation("cHp",caseId.getCaseId(),content,model.getId());
		hurryRecordMapper.save(hr);
	}
	
	/** 待处理协催审批 */
	@Override
	public SearchResult<HelpMeDto> querydeal(ParamCondition condition) {
		
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
		
		List<HelpMeDto> list = helpMeMapper.querydeal(condition);
		SearchResult<HelpMeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/** 协催记录 */
	@Override
	public SearchResult<HelpMeDto> queryrecord(ParamCondition condition) {
		
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
		List<HelpMeDto> list = helpMeMapper.queryrecord(condition);
		SearchResult<HelpMeDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/** 导出户籍查询方法 */
	@Override
	public List<HelpMeDto> queryExport(List<String> ids) {
		List<HelpMeDto> list = helpMeMapper.queryExport(ids);
		return list;
	}

	@Override
	public CaseApply importExcelData(List<CaseApplyDto> listBean, String caseApplyId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryChaziToApproveCount() {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		return helpMeMapper.chaziToApproveCount(attach,SQLUtil.warpSql(getQueryOrgs()), currentUser.getOrgId(), currentUser.getLoginName());
	}


	private String getQueryOrgs() {
		EmployeeInfoModel currentUser=SecurityUtil.getCurrentUser();
		String attachOrgId = currentUser.getAttachOrgId();
		String queryOrgs=currentUser.getOrgId()+",";
		if(StringUtils.isNotBlank(attachOrgId)){
			queryOrgs=queryOrgs+attachOrgId;
		}
		return queryOrgs;
	}
		/*
		if (CollectionUtils.isNotEmpty(caseApplyExcels)) {
=======
	
//	/** 导入协催记录 
//	 * @return */
//	@Override
//	public CaseApply importExcelData(List<CaseApplyDto> caseApplyExcels, String caseApplyId, String type) {
//
//		CaseApply caseApply = caseApplyService.findById(caseApplyId);
//		if (caseApply == null) {
//			throw new ServiceException("没有找到对应的协催信息:" + caseApplyId);
//		}
//		caseApply.setModifyTime(new Date());
//		CaseApply apply = new CaseApply();
//		ReflectUtil.copyProps(caseApply, apply, "id");
//		apply.setId(caseApplyId);
//		caseApplyService.update(apply);
//		
//		if (CollectionUtils.isNotEmpty(caseApplyExcels)) {
//
//			for (CaseApplyDto cat : caseApplyExcels) {
//				CaseApplyModel caseApply2 = cat.getCaseApply();
//				String applyId = caseApply2.getId();
//				Integer applyType = caseApply.getApplyType();
//				// int applyType = cat.getCaseApply().getApplyType();
//				if (applyType == 4) { // 户籍
//					CensusRegisterModel registerModel = cat.getCensusRegisterModel();
//					registerModel.setCaseApplyId(applyId);
//					censusRegisterService.save(registerModel);
//
//				} else if (applyType == 6) { // 社保
//					SocialSecurityModel securityModel = cat.getSocialSecurityModel();
//					securityModel.setCaseApplyId(applyId);
//					socialSecurityService.save(securityModel);
//
//				} else if (applyType == 12) { // 电信
//					TelecomModel telecomModel = cat.getTelecomModel();
//					telecomModel.setCaseApplyId(applyId);
//					telecomService.save(telecomModel);
//				}
//
//			}
//		} else {
//			throw new ServiceException("没有可更新的协催信息");
//		}
//		return caseApply;
//	}

	@Override
	public int querydealCount() {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		return helpMeMapper.queryDealCount(attach,SQLUtil.warpSql(getQueryOrgs()), currentUser.getOrgId(), currentUser.getLoginName());
	}
			
}
