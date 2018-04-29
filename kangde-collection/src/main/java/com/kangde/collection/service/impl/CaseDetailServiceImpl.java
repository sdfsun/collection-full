package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.DictionaryConst;
import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.CaseDetailDto;
import com.kangde.collection.mapper.CaseDetailMapper;
import com.kangde.collection.mapper.CaseLinkmanMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.CasePaidMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.mapper.PhoneRecordMapper;
import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.service.CaseDetailService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.DictionaryService;

/**
 * 案件详情Service实现类
 * 
 * @author wcy
 * @date 2016年5月9日11:17:07
 */
@Service("caseMessageService")
public class CaseDetailServiceImpl implements CaseDetailService {

	@Autowired
	private CaseDetailMapper caseDetailMapper;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private PhoneRecordMapper phoneRecordMapper;
	@Autowired
	private CasePaidMapper casePaidMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CaseLinkmanMapper caseLinkmanMapper;
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public CaseDetailDto queryDetailByCaseId(String id) {
		return caseDetailMapper.queryDetailByCaseId(id);
	}

	// 同一个案件两个用户同时保存ptp时， 可能会插入两条, 使用同步控制
	@Override
	public synchronized void saveRecord(String contact, Date createTime, String linkmanId, String prCat, String typeId,
			String collecStateId, Date ptpTime, Double ptpMoney, String content, Date nextFollowTime, String caseId) {
		boolean needSaveCasePaid = ptpMoney != null && ptpTime != null;
		// 保存催记表
		if (needSaveCasePaid) {
			if (CollectionUtils.isNotEmpty(casePaidMapper.queryPTPrecordByCaseId(caseId))) {
				throw new ServiceException("已经存在PTP记录");
			}
			// 还款表
			CasePaid casePaid=saveCasePaid(ptpTime, ptpMoney, caseId);
			//保存操作记录  bianbianbian
			String contentStr="承诺金额:"+ptpMoney+", 还款日期："+DateFormatUtils.format(ptpTime, "yyyy-MM-dd");
			hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.PTP, caseId, "PTP", contentStr,casePaid.getId(), HurryRecordConst.add));
		}
		
		PhoneRecordModel phoneRecordModel = savePhoneRecord(contact, createTime, linkmanId, prCat, typeId, collecStateId, ptpTime, ptpMoney, content, caseId);

		//保存操作记录  bianbianbian
		String stateText="";
		if(StringUtils.isNotBlank(collecStateId)){
			List<DictionaryModel> dicts = dictionaryService.findSubsByPath(DictionaryConst.COLLECTION_STATE);
			for (DictionaryModel dictionaryModel : dicts) {
				if(collecStateId.equals(dictionaryModel.getValue())){
					stateText=dictionaryModel.getName();
					break;
				}
			}
		}
		String name=phoneRecordModel.getName()==null?"":phoneRecordModel.getName();
		String contentStr=name+",电话:"+contact+", 通话内容："+content+" , 催收状态："+stateText;
		hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.phoHur, caseId, "电催记录", contentStr, phoneRecordModel.getId(), HurryRecordConst.noaction));
		;
		
		
		// 更新案件表
		updateCase(nextFollowTime, caseId, collecStateId);
		
	}

	

	private PhoneRecordModel savePhoneRecord(String contact, Date createTime, String linkmanId, String prCat, String typeId, String collecStateId,
			Date ptpTime, Double ptpMoney, String content, String caseId) {
		PhoneRecordModel phoneRecord = new PhoneRecordModel();
		phoneRecord.setId(UUIDUtil.UUID32());
		phoneRecord.setContact(contact);
		if(StringUtils.isNotBlank(linkmanId)){
			CaseLinkmanModel linkman = caseLinkmanMapper.findById(linkmanId);
			if(linkman!=null && contact.equals(linkman.getPhone())){
				phoneRecord.setContact(linkman.getPhone());
				phoneRecord.setName(linkman.getName());
				phoneRecord.setRelation(linkman.getRelation());
			}
		}
		phoneRecord.setPrCat(prCat);
		phoneRecord.setCreateTime(new Date());
		phoneRecord.setCaseId(caseId);//
		phoneRecord.setTypeId(typeId);// 风控结果
		phoneRecord.setCollecStateId(collecStateId);// 风控状态
		phoneRecord.setContent(content);// 风控内容
		phoneRecord.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
		if (ptpTime != null) {
			phoneRecord.setPtpTime(ptpTime);
		}
		phoneRecord.setPtpMoney(ptpMoney);
		phoneRecordMapper.save(phoneRecord);
		return phoneRecord;
	}

	private void updateCase(Date nextFollowTime, String caseId, String collecStateId) {
		CaseModel caseModel = caseMapper.findById(caseId);
		if (nextFollowTime != null) {
			caseModel.setNextFollowTime(nextFollowTime);
		}
		caseModel.setCollecStateId(collecStateId);
		caseModel.setModifyTime(new Date());
		caseMapper.update(caseModel);
	}

	private CasePaid saveCasePaid(Date ptpTime, Double ptpMoney, String caseId) {
		CasePaid casePaid = new CasePaid();
		casePaid.setId(UUIDUtil.UUID32());
		casePaid.setCaseId(caseId);
		casePaid.setPtpMoney(ptpMoney);
		casePaid.setPtpTime(ptpTime);
		String userId = SecurityUtil.getCurrentUser().getId();
		casePaid.setSeNo(userId);
		casePaid.setCreateEmpId(userId);
		casePaid.setCreateTime(new Date());
		casePaid.setState(0);
		casePaidMapper.save(casePaid);
		return casePaid;
	}

}
