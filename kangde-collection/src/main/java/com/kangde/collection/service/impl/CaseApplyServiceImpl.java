package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.constant.CaseApplyStateConst;
import com.kangde.collection.dto.AddressDto;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.mapper.CaseApplyMapper;
import com.kangde.collection.mapper.PhoneRecordMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.CaseApply;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

@Service
public class CaseApplyServiceImpl extends AbstractService<CaseApply, String> implements CaseApplyService {
	@Autowired
	private AddressService addressService;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CaseApplyMapper caseApplyMapper;
	@Autowired
	private PhoneRecordMapper phoneRecordMapper;
	@Autowired
	private HurryRecordService hurryRecordService;

	@Override
	public CaseApply save(CaseApply model) {
		model.setId(UUIDUtil.UUID32());// ID生成
		model.setCreateTime(new Date());// 创建时间
		model.setAppTime(new Date());
		if (model.getStatus() == null) {
			model.setStatus(CoreConst.STATUS_NORMAL);// 系统状态,默认正常
		}
		if (model.getState() == null) {
			model.setState(-2);// 系统状态,默认正常
		}
		model.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
		model.setApplyUser(SecurityUtil.getCurrentUser().getId());
		model.setApplyUserName(SecurityUtil.getCurrentUser().getUserName());
		model.setVersion(0);
		caseApplyMapper.save(model);
		return model;
	}

	@Override
	public void applyToLetter(String addressId, String caseId) throws AddressException {

		CaseApply model = new CaseApply();
		model.setId(UUIDUtil.UUID32());// ID生成
		model.setCaseId(caseId);
		model.setState(CaseApplyStateConst.DAI_SHEN_PI);
		model.setApplyType(1);
		EmployeeInfoModel user = SecurityUtil.getCurrentUser();

		model.setApplyContent(user.getUserName() + "申请信函邮寄");
		AddressModel addressModel = addressService.findById(addressId);

		if (addressModel.getAreaId1() == null || addressModel.getAreaId2() == null
				|| addressModel.getAreaId3() == null) {
			throw new AddressException("请完善省市县地址信息!");
		}

		model.setChCat1(addressModel.getAdrCat());
		model.setAddressId(addressId);
		AddressDto a = addressService.findFullAddress(addressModel.getAreaId1(), addressModel.getAreaId2(),
				addressModel.getAreaId3());
		model.setAddress(a + addressModel.getAddress());
		model.setContactUser(addressModel.getName());
		model.setMsgState(1);
		model.setCreateEmpId(user.getId());
		Date date = new Date();
		model.setAppTime(date);
		model.setApplyUser(user.getId());
		model.setApplyUserName(user.getUserName());
		model.setCreateTime(date);// 创建时间
		model.setModifyTime(date);
		caseApplyMapper.save(model);
		// 2 更新地址visApp状态
		addressModel.setMailCount(addressModel.getMailCount() == null ? 1 : addressModel.getMailCount() + 1);
		addressModel.setMailApp(1);
		addressMapper.updateById(addressModel);

		// 保存操作记录 bianbianbian
		// String content="建议信函:name, address [ type]";
		String content = model.getContactUser() + ", " + model.getAddress() + " [ " + addressModel.getAdrCat()
				+ "]";
		hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.apMsg, caseId, "信函", content,addressId, HurryRecordConst.suggest));
	}

	@Override
	public SearchResult<CaseApply> queryLetter(ParamCondition condition) {
		
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachOrgId = currentUser.getAttachOrgId();
		String queryOrgs=currentUser.getOrgId()+",";
		if(StringUtils.isNotBlank(attachOrgId)){
			queryOrgs=queryOrgs+attachOrgId;
		}
		
		String attachEntrustId = currentUser.getAttachEntrustId();
		
		condition.put("queryOrgs", SQLUtil.warpSql(queryOrgs));
		condition.put("loginName", currentUser.getLoginName());
		if(StringUtils.isNotBlank(attachEntrustId)){
			condition.put("attachEntrustId", SQLUtil.warpSql(attachEntrustId));
		}
		
		List<CaseApply> list = caseApplyMapper.queryLetter(condition);
		SearchResult<CaseApply> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public void changeState(Integer state, List<String> ids, String approvalOpinion) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		Map<String, Object> map = new HashMap<>(5);
		Date now = new Date();
		map.put("ids", ids);
		map.put("state", state);
		map.put("approvalOpinion", approvalOpinion);
		map.put("surUser", currentUser.getId());
		map.put("surTime", now);
		map.put("surUserName", currentUser.getUserName());
		// 已完成状态
		if (state == 1) {
			// 更新邮寄时间
			map.put("mailTime", now);
			// 更新地址
			updateAddress(state, ids, approvalOpinion, currentUser);
			// 保存催记
			saveCollectionRecord(state, ids, approvalOpinion, currentUser);
			// 不通过状态
		} else if (state == -1) {
			// 更新地址
			updateAddress(state, ids, approvalOpinion, currentUser);
		}
		caseApplyMapper.changeState(map);
	}

	// 更新地址
	private void updateAddress(Integer state, List<String> ids, String approvalOpinion, EmployeeInfoModel currentUser) {
		for (String id : ids) {
			CaseApply model = caseApplyMapper.findById(id);
			if (model != null) {
				AddressModel addressModel = addressMapper.findById(model.getAddressId());
				if (addressModel != null) {
					addressModel.setMailApp(0);
					addressMapper.updateById(addressModel);
				}
			}
		}
	}

	// 保存催记
	private void saveCollectionRecord(Integer state, List<String> ids, String approvalOpinion,
			EmployeeInfoModel currentUser) {
		for (String id : ids) {
			CaseApply model = caseApplyMapper.findById(id);
			if (model != null) {
				PhoneRecordModel recordModel = new PhoneRecordModel();
				recordModel.setCaseId(model.getCaseId());
				recordModel.setName(currentUser.getUserName());
				recordModel.setCreateTime(new Date());
				recordModel.setId(UUIDUtil.UUID32());
				recordModel.setPrCat("2");
				recordModel.setContact(model.getAddress());
				recordModel.setContent("已邮寄信函");
				// 数据导入问题
				AddressModel addressModel = addressMapper.findById(model.getAddressId());
				if (addressModel != null) {
					recordModel.setRelation(addressModel.getRelation());
				}
				phoneRecordMapper.save(recordModel);
			}
		}
	}

	@Override
	public List<VisitShowView> findVisitShowViewByIds(List<String> ids) {
		return caseApplyMapper.findVisitShowViewByIds(ids);
	}

	@Override
	public List<CaseApply> queryExcelAll(ParamCondition condition) {
		// TODO Auto-generated method stub
		
		return caseApplyMapper.query(condition);
	}
	
	@Override
	public List<CaseApply> queryForIds(List<String> ids) {
		return caseApplyMapper.queryForIds(ids);
	}

	@Override
	public void batchSave(String[] ids, String applyContent,Integer applyType) {
		if(StringUtils.isBlank(applyContent)){
			throw new ServiceException("申请内容不能为空");
		}
		if(applyType==null){
			throw new ServiceException("查资类型不能为空");
		}
		for(String caseId: ids){
			CaseApply model=new CaseApply();
			model.setCaseId(caseId);
			model.setApplyType(applyType);
			model.setApplyContent(applyContent);
			this.save(model);
		}
	}

	public SearchResult<CaseApply> queryMaterialByCaseId(ParamCondition condition) {
		List<CaseApply> list = caseApplyMapper.queryMaterialByCaseId(condition);
		SearchResult<CaseApply> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public SearchResult<CaseApply> querycasedetailxiecui(ParamCondition condition) {
		List<CaseApply> list = caseApplyMapper.querycasedetailxiecui(condition);
		SearchResult<CaseApply> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public SearchResult<CaseApply> queryLetterByCaseId(ParamCondition condition) {
		List<CaseApply> list = caseApplyMapper.queryLetterByCaseId(condition);
		SearchResult<CaseApply> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public int queryLetterCount(int applyState) {
		
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		return caseApplyMapper.queryLetterCount(attach,SQLUtil.warpSql(getQueryOrgs()), currentUser.getOrgId(), currentUser.getLoginName(),applyState);
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

	@Override
	public int queryXiecuiTasklistCount() {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		return caseApplyMapper.xiecuiTasklistCount(attach,SQLUtil.warpSql(getQueryOrgs()), currentUser.getOrgId(), currentUser.getLoginName());
	}
	
}
