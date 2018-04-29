package com.kangde.collection.service.impl;

import java.math.BigDecimal;	
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.CaseDivisionConst;
import com.kangde.collection.CollectionConst;
import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.CaseCarDto;
import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.mapper.CaseCarLoanMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.CaseAssignModel;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseCarLoanModel;
import com.kangde.collection.model.CaseHistoryModel;
import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.CaseAssignService;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CaseHistoryService;
import com.kangde.collection.service.CaseLinkmanService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.collection.util.SnUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.NumberUtil;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.OrganizationService;

/**
 * 案件service实现类
 * 
 * @author lisuo
 *
 */
@Service("caseService")
public class CaseServiceImpl extends AbstractService<CaseModel, String> implements CaseService {

	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private CaseLinkmanService caseLinkmanService;
	@Autowired
	private CaseBatchService caseBatchService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CaseAssignService caseAssignService;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private CaseHistoryService caseHistoryService;
	@Autowired
	private HurryRecordService hurryRecordService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CaseCarLoanMapper caseCarMapper;

	@Autowired
	private CaseStatisticsService caseStatisticsService;
	@Autowired
	private MessageReminderService messageReminderService;
	
	/**
	 * 导入案件
	 */
	@Override
	public CaseBatchModel importExcelData(List<CaseExcelDto> caseExcels, CaseBatchModel caseBatch, String type) {
		Connection connection = null;
		try {
			if (CollectionUtils.isNotEmpty(caseExcels)) {
				connection = SQLUtil.getConnection();
				caseBatch.setIsUpload(1);
				caseBatch.setUploadTime(new Date());
				int size = caseExcels.size();
				caseBatch.setTotalNumber(size);
				caseBatch.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
				BigDecimal totalMoney = new BigDecimal("0");
				// 计算批次委案总金额
				for (CaseExcelDto cs : caseExcels) {
					totalMoney = totalMoney.add(cs.getCaseMoney());
				}
				caseBatch.setTotalMoney(totalMoney.doubleValue());
				caseBatchService.save(caseBatch);

				List<AddressModel> addressList = new ArrayList<>();
				List<CaseLinkmanModel> caseLinkmanList = new ArrayList<>();
				int index = 1;
				for (CaseExcelDto cs : caseExcels) {
					String id = UUIDUtil.UUID32();
					cs.setId(id);
					// 生成批次号
					cs.setBatchId(caseBatch.getId());
					String caseSn = SnUtil.getCaseSn(caseBatch.getBatchCode(), index);
					cs.setCaseCode(caseSn);
					// 批次信息添加给案件
					cs.setCaseBackdate(caseBatch.getEndDate());
					cs.setEndDate(caseBatch.getEndDate());
					cs.setCaseDate(caseBatch.getBeginDate());
					cs.setCreateTime(new Date());
					setAddressAndLinkman(addressList, caseLinkmanList, cs);
					index++;
				}
				// 保存案件信息
				super.batchSave(caseExcels);
				if (CollectionUtils.isNotEmpty(addressList)) {
					addressService.batchSave(addressList);
				}
				if (CollectionUtils.isNotEmpty(caseLinkmanList)) {
					caseLinkmanService.batchSave(caseLinkmanList);
				}
				SQLUtil.commit(connection);
			} else {
				throw new ServiceException("没有可导入的案件信息");
			}
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw e;
			}
			SQLUtil.rollback(connection);
			throw new RuntimeException(e);
		} finally {
			SQLUtil.close(connection);
		}
		return caseBatch;
	}

	private void setAddressAndLinkman(List<AddressModel> addressList, List<CaseLinkmanModel> caseLinkmanList,
			CaseExcelDto cs) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		cs.setState(CaseModel.STATE_NORMAL);
		cs.setCreateEmpId(currentUser.getId());
		cs.setCollecStateId("0");
		// 向地址表中添加数据
		if (StringUtils.isNotBlank(cs.getCompanyAddress())   || StringUtils.isNotBlank(cs.getCompanyName())) {
			AddressModel addressModel = new AddressModel();
			addressModel.setId(UUIDUtil.UUID32());
			addressModel.setVisApp(0);
			addressModel.setCheckApp(0);
			addressModel.setMailApp(0);
			addressModel.setMailCount(0);
			addressModel.setCreateEmpId(currentUser.getId());
			addressModel.setCreateTime(new Date());
			addressModel.setStatus(1);
			addressModel.setCaseId(cs.getId());
			addressModel.setName(cs.getCaseName());
			addressModel.setSex(cs.getCaseSex());
			addressModel.setAge(cs.getCaseAge());
			addressModel.setSource("0");
			addressModel.setAdrCat("单位地址");
			if (StringUtils.isBlank(cs.getCompanyName())) {
				cs.setCompanyName("");
			}
			if (StringUtils.isBlank( cs.getCompanyAddress())) {
				cs.setCompanyAddress("");
			}
			addressModel.setAddress(cs.getCompanyName() + "/" + cs.getCompanyAddress());
			addressModel.setRelation("本人");
			addressList.add(addressModel);
		}
		if (StringUtils.isNotBlank(cs.getDomicile())) {
			AddressModel addressModel = new AddressModel();
			addressModel.setId(UUIDUtil.UUID32());
			addressModel.setVisApp(0);
			addressModel.setCheckApp(0);
			addressModel.setMailApp(0);
			addressModel.setMailCount(0);
			addressModel.setCreateEmpId(currentUser.getId());
			addressModel.setCreateTime(new Date());
			addressModel.setStatus(1);
			addressModel.setCaseId(cs.getId());
			addressModel.setName(cs.getCaseName());
			addressModel.setSex(cs.getCaseSex());
			addressModel.setAge(cs.getCaseAge());
			addressModel.setAdrCat("户籍地址");
			addressModel.setSource("0");
			addressModel.setAddress(cs.getDomicile());
			addressModel.setRelation("本人");
			addressList.add(addressModel);
		}
		if (StringUtils.isNotBlank(cs.getFamilyAddress())) {
			AddressModel addressModel = new AddressModel();
			addressModel.setId(UUIDUtil.UUID32());
			addressModel.setVisApp(0);
			addressModel.setCheckApp(0);
			addressModel.setMailApp(0);
			addressModel.setMailCount(0);
			addressModel.setCreateEmpId(currentUser.getId());
			addressModel.setCreateTime(new Date());
			addressModel.setStatus(1);
			addressModel.setCaseId(cs.getId());
			addressModel.setName(cs.getCaseName());
			addressModel.setSex(cs.getCaseSex());
			addressModel.setAge(cs.getCaseAge());
			addressModel.setAdrCat("家庭地址");
			addressModel.setSource("0");
			addressModel.setAddress(cs.getFamilyAddress());
			addressModel.setRelation("本人");
			addressList.add(addressModel);
		}

		if (StringUtils.isNotBlank(cs.getMobile1())) {
			CaseLinkmanModel linkman = new CaseLinkmanModel();
			linkman.setRelation("本人");
			linkman.setId(UUIDUtil.UUID32());
			linkman.setCreateEmpId(currentUser.getId());
			linkman.setCaseId(cs.getId());
			linkman.setName(cs.getCaseName());
			linkman.setPhone(cs.getMobile1());
			linkman.setPhoneType("手机");
			linkman.setCreateTime(new Date());
			linkman.setAddress(cs.getFamilyAddress());
			linkman.setSource("2");
			caseLinkmanList.add(linkman);
		}

		if (StringUtils.isNotBlank(cs.getMobile2())) {
			CaseLinkmanModel linkman = new CaseLinkmanModel();
			linkman.setRelation("本人");
			linkman.setId(UUIDUtil.UUID32());
			linkman.setCreateEmpId(currentUser.getId());
			linkman.setCaseId(cs.getId());
			linkman.setName(cs.getCaseName());
			linkman.setPhone(cs.getMobile2());
			linkman.setPhoneType("手机");
			linkman.setCreateTime(new Date());
			linkman.setAddress(cs.getCompanyAddress());
			linkman.setSource("3");
			caseLinkmanList.add(linkman);
		}
		if (StringUtils.isNotBlank(cs.getCompanyPhone())) {
			CaseLinkmanModel linkman = new CaseLinkmanModel();
			linkman.setRelation("本人");
			linkman.setId(UUIDUtil.UUID32());
			linkman.setCreateEmpId(currentUser.getId());
			linkman.setCaseId(cs.getId());
			linkman.setName(cs.getCaseName());
			linkman.setPhone(cs.getCompanyPhone());
			linkman.setPhoneType("单位电话");
			linkman.setCreateTime(new Date());
			linkman.setAddress(cs.getCompanyAddress());
			linkman.setSource("0");
			caseLinkmanList.add(linkman);
		}
		if (StringUtils.isNotBlank(cs.getFamilyPhone())) {
			CaseLinkmanModel linkman = new CaseLinkmanModel();
			linkman.setRelation("本人");
			linkman.setId(UUIDUtil.UUID32());
			linkman.setCreateEmpId(currentUser.getId());
			linkman.setCaseId(cs.getId());
			linkman.setName(cs.getCaseName());
			linkman.setPhone(cs.getFamilyPhone());
			linkman.setPhoneType("家庭电话");
			linkman.setCreateTime(new Date());
			linkman.setAddress(cs.getFamilyAddress());
			linkman.setSource("0");
			caseLinkmanList.add(linkman);
		}
		// 添加联系人信息集合
		caseLinkmanList.addAll(getLinkmans(cs));

		if (null != cs.getCaseCar()) {
			// 添加车贷信息
			CaseCarLoanModel ccl = new CaseCarLoanModel();
			ccl.setId(UUIDUtil.UUID32());
			ccl.setCaseId(cs.getId());
			ccl.setDealer(cs.getCaseCar().getDealer());
			ccl.setPrice(cs.getCaseCar().getPrice());
			ccl.setLiceNo(cs.getCaseCar().getLiceNo());
			ccl.setBrand(cs.getCaseCar().getBrand());
			ccl.setNumber(cs.getCaseCar().getNumber());
			ccl.setVersion(cs.getCaseCar().getVersion());
			ccl.setEngineNo(cs.getCaseCar().getEngineNo());
			ccl.setVinNo(cs.getCaseCar().getVinNo());
			caseCarMapper.save(ccl);

		}
	}

	/**
	 * 更新案件
	 */
	@Override
	public void updateExcelData(List<CaseExcelDto> caseExcels, String caseBatchId, String type) {
		CaseBatchModel caseBatch = caseBatchService.findById(caseBatchId);
		Connection connection = null;
		try {
			if (caseBatch == null) {
				throw new ServiceException("没有找到对应的批次信息:" + caseBatchId);
			}
			caseBatch.setModifyTime(new Date());
			CaseBatchModel model = new CaseBatchModel();
			ReflectUtil.copyProps(caseBatch, model, "id");
			model.setId(caseBatchId);
			model.setModifyTime(new Date());
			caseBatchService.update(model);
			List<CaseHistoryModel> historyList = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(caseExcels)) {
				for (CaseExcelDto cs : caseExcels) {

					String id = UUIDUtil.UUID32(); // 操作记录表id
					CaseHistoryModel historyModel = new CaseHistoryModel();
					ReflectUtil.copyProps(cs, historyModel, "id");
					historyModel.setId(id);
					historyModel.setCaseId(cs.getId());
					// 获取当前的用户
					EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
					cs.setModifyEmpId(currentUser.getId());
					historyModel.setModifyEmpId(cs.getModifyEmpId());
					historyModel.setOperateTime(new Date());
					historyList.add(historyModel);
					super.update(cs);// 更新案件表信息
					caseHistoryService.saveCaseHistory(historyModel);
					
					List<CaseLinkmanModel> linkManAll = caseLinkmanService.findByCaseIds(cs.getId());
					CaseModel caseMode= caseMapper.findById(cs.getId());
					if(StringUtils.isNotBlank(caseMode.getCaseAssignedId())){
						messageReminderService.saveReminder("案件["+caseMode.getCaseCode()+"]"+"已被更新", 0, caseMode.getCaseAssignedId(), "/collection/casedetail?caseId="+caseMode.getId(),"案件详情");
					}
			
					List<CaseLinkmanModel> iPhone = caseLinkmanService.iPhone(cs.getId());
					
					List<AddressModel> AddressAll=addressService.AddressAll(cs.getId());
					if (StringUtils.isNotBlank(cs.getDomicile())) {
						AddressModel address= isinAddress("户籍地址",AddressAll);
						if(null==address){
							AddressModel addressModel =new AddressModel();
							addressModel.setId(UUIDUtil.UUID32());
							addressModel.setCaseId(cs.getId());
							addressModel.setAddress(cs.getDomicile());
							if(null!=caseMode){
								addressModel.setName(caseMode.getCaseName());
							}
							addressModel.setAdrCat("户籍地址");
							addressModel.setRelation("本人");
							addressModel.setCreateEmpId(currentUser.getId());
							addressModel.setCreateTime(new Date());
							addressModel.setSource("0");
							addressService.save(addressModel);
						}else if(!address.getAddress().equals(cs.getDomicile())){
							addressMapper.updateForAddress(address.getId(),cs.getDomicile());
						}
					}
					
					if (StringUtils.isNotBlank(cs.getFamilyAddress())) {
						AddressModel address= isinAddress("家庭地址",AddressAll);
						if(null==address){
							AddressModel addressModel =new AddressModel();
							addressModel.setId(UUIDUtil.UUID32());
							addressModel.setCaseId(cs.getId());
							addressModel.setAddress(cs.getFamilyAddress());
							if(null!=caseMode){
								addressModel.setName(caseMode.getCaseName());
							}
							addressModel.setAdrCat("家庭地址");
							addressModel.setRelation("本人");
							addressModel.setCreateEmpId(currentUser.getId());
							addressModel.setCreateTime(new Date());
							addressModel.setSource("0");
							addressService.save(addressModel);
						}else if(!address.getAddress().equals(cs.getFamilyAddress())){
							addressMapper.updateForAddress(address.getId(),cs.getFamilyAddress());
						}
					}
					
					if (StringUtils.isNotBlank(cs.getCompanyAddress())) {
						AddressModel address= isinAddress("单位地址",AddressAll);
						if(null==address){
							AddressModel addressModel =new AddressModel();
							addressModel.setId(UUIDUtil.UUID32());
							addressModel.setCaseId(cs.getId());
							if(StringUtils.isNotBlank(cs.getCompanyName())){
								addressModel.setAddress(cs.getCompanyName()+"/"+cs.getCompanyAddress());
							}else{
								addressModel.setAddress("/"+cs.getCompanyAddress());
							}
							if(null!=caseMode){
								addressModel.setName(caseMode.getCaseName());
							}
							addressModel.setAdrCat("单位地址");
							addressModel.setRelation("本人");
							addressModel.setCreateEmpId(currentUser.getId());
							addressModel.setCreateTime(new Date());
							addressModel.setSource("0");
							addressService.save(addressModel);
						}else if(!address.getAddress().equals(cs.getCompanyAddress())){
							String addressName=null;
							AddressModel addmodel=addressMapper.findById(address.getId());
							if(StringUtils.isNotBlank(cs.getCompanyName()) && StringUtils.isNotBlank(cs.getCompanyAddress())){
								addressName=cs.getCompanyName()+"/"+cs.getCompanyAddress();
							}else if(StringUtils.isNotBlank(cs.getCompanyName()) && StringUtils.isBlank(cs.getCompanyAddress())){
								String a=addmodel.getAddress().substring(0, addmodel.getAddress().lastIndexOf("/"));
								addressName=cs.getCompanyName()+"/"+a;
								
							}else if(StringUtils.isBlank(cs.getCompanyName()) && StringUtils.isNotBlank(cs.getCompanyAddress())){
								String a=addmodel.getAddress().substring(0, addmodel.getAddress().indexOf("/"));
								addressName=a+"/"+cs.getCompanyAddress();
							}
						
							addressMapper.updateForAddress(address.getId(),addressName);
						}
					}
					
					
					
					if (StringUtils.isNotBlank(cs.getCompanyPhone())) {
						CaseLinkmanModel link= isin("单位电话",linkManAll);
						if(null==link){
							CaseLinkmanModel linkModel=new CaseLinkmanModel();
							linkModel.setId(UUIDUtil.UUID32());
							linkModel.setCaseId(cs.getId());
						
							linkModel.setPhone(cs.getCompanyPhone());
							linkModel.setPhoneType("单位电话");
							linkModel.setCreateEmpId(currentUser.getId());
							linkModel.setRelation("本人");
							linkModel.setSource("0");
							linkModel.setCreateTime(new Date());
							if(null!=caseMode){
								linkModel.setName(caseMode.getCaseName());
							}
							caseLinkmanService.save(linkModel);
						}else if(!link.getPhone().equals(cs.getCompanyPhone())){
							caseLinkmanService.updateForPhone(link.getId(),cs.getCompanyPhone());
						}
					}
					if (StringUtils.isNotBlank(cs.getFamilyPhone())) {
						CaseLinkmanModel link= isin("家庭电话",linkManAll);
						if(null==link){
							CaseLinkmanModel linkModel=new CaseLinkmanModel();
							linkModel.setId(UUIDUtil.UUID32());
							linkModel.setCaseId(cs.getId());
							linkModel.setPhone(cs.getFamilyPhone());
							linkModel.setPhoneType("家庭电话");
							linkModel.setCreateEmpId(currentUser.getId());
							linkModel.setRelation("本人");
							linkModel.setSource("0");
							linkModel.setCreateTime(new Date());
							if(null!=caseMode){
								linkModel.setName(caseMode.getCaseName());
							}
							caseLinkmanService.save(linkModel);
						}else if(!link.getPhone().equals(cs.getFamilyPhone())){
							caseLinkmanService.updateForPhone(link.getId(),cs.getFamilyPhone());
						}
					}
					
					if (StringUtils.isNotBlank(cs.getMobile1())) {
						CaseLinkmanModel link= isinPhone(iPhone);
						if(null==link){
							CaseLinkmanModel linkModel=new CaseLinkmanModel();
							linkModel.setId(UUIDUtil.UUID32());
							linkModel.setCaseId(cs.getId());
							linkModel.setPhone(cs.getMobile1());
							linkModel.setPhoneType("手机");
							linkModel.setCreateEmpId(currentUser.getId());
							linkModel.setRelation("本人");
							linkModel.setSource("0");
							linkModel.setCreateTime(new Date());
							if(null!=caseMode){
								linkModel.setName(caseMode.getCaseName());
							}
							caseLinkmanService.save(linkModel);
						}else if(!link.getPhone().equals(cs.getMobile1())){
							caseLinkmanService.updateForPhone(link.getId(),cs.getMobile1());
						}
					}
					
					if (StringUtils.isNotBlank(cs.getMobile2())) {
						CaseLinkmanModel link= isinPhone1(iPhone);
						if(null==link){
							CaseLinkmanModel linkModel=new CaseLinkmanModel();
							linkModel.setId(UUIDUtil.UUID32());
							linkModel.setCaseId(cs.getId());
							linkModel.setPhone(cs.getMobile2());
							linkModel.setPhoneType("手机");
							linkModel.setCreateEmpId(currentUser.getId());
							linkModel.setRelation("本人");
							linkModel.setSource("0");
							linkModel.setCreateTime(new Date());
							if(null!=caseMode){
								linkModel.setName(caseMode.getCaseName());
							}
							caseLinkmanService.save(linkModel);
						}else if(!link.getPhone().equals(cs.getMobile2())){
							caseLinkmanService.updateForPhone(link.getId(),cs.getMobile2());
						}
					}
					
				}
				/*if (CollectionUtils.isNotEmpty(historyList)) {
					caseHistoryService.batchSave(historyList);
				}*/
				SQLUtil.commit(connection);
			} else {
				throw new ServiceException("没有可更新的案件信息");
			}
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw e;
			}
			SQLUtil.rollback(connection);
			throw new RuntimeException(e);
		} finally {
			SQLUtil.close(connection);
		}
	}

	
	private CaseLinkmanModel isin(String phoneType ,List<CaseLinkmanModel> model){
		for (CaseLinkmanModel m : model) {
			if(m.getPhoneType().equals(phoneType)){
				return m;
			}
		}
		return null;
	}
	
	private AddressModel isinAddress(String phoneType ,List<AddressModel> model){
		for (AddressModel m : model) {
			if(m.getAdrCat().equals(phoneType)){
				return m;
			}
		}
		return null;
	}
	
	private CaseLinkmanModel isinPhone(List<CaseLinkmanModel> model){
		for (CaseLinkmanModel m : model) {
			if(m.getSource()!=null){
				if(m.getSource().equals("2")){
					return m;
				}
			}
		}
		return null;
	}
	
	private CaseLinkmanModel isinPhone1(List<CaseLinkmanModel> model){
		for (CaseLinkmanModel m : model) {
			if(m.getSource()!=null){
				if(m.getSource().equals("3")){
					return m;
				}
			}
		}
		return null;
	}
	
	/**
	 * 保存联系人信息
	 * 
	 * @param caseExcelDto
	 */
	private List<CaseLinkmanModel> getLinkmans(CaseExcelDto caseExcelDto) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		List<CaseLinkmanModel> list = new ArrayList<>();
		int size = 10;
		String prefix = "linkman";
		for (int i = 1; i <= size; i++) {
			CaseLinkmanModel linkman = (CaseLinkmanModel) ReflectUtil.getProperty(caseExcelDto, prefix + i);
			CaseLinkmanModel lk = null;
			AddressModel addressModel = null;
			if (linkman != null) {
				if (StringUtils.isNotBlank(linkman.getMobile())) {
					lk = new CaseLinkmanModel();
					ReflectUtil.copyProps(linkman, lk);
					lk.setCaseId(caseExcelDto.getId());
					lk.setId(UUIDUtil.UUID32());
					lk.setCreateEmpId(currentUser.getId());
					lk.setPosition(i);
					lk.setPhone(linkman.getMobile());
					lk.setPhoneType("手机");
					lk.setCreateTime(new Date());
					list.add(lk);
				}
				if (StringUtils.isNotBlank(linkman.getFamilyPhone())) {
					lk = new CaseLinkmanModel();
					ReflectUtil.copyProps(linkman, lk);
					lk.setCaseId(caseExcelDto.getId());
					lk.setId(UUIDUtil.UUID32());
					lk.setCreateEmpId(currentUser.getId());
					lk.setPosition(i);
					lk.setPhone(linkman.getFamilyPhone());
					lk.setPhoneType("家庭电话");
					lk.setCreateTime(new Date());
					list.add(lk);
				}
				if (StringUtils.isNotBlank(linkman.getUnitPhone())) {
					lk = new CaseLinkmanModel();
					ReflectUtil.copyProps(linkman, lk);
					lk.setCaseId(caseExcelDto.getId());
					lk.setId(UUIDUtil.UUID32());
					lk.setCreateEmpId(currentUser.getId());
					lk.setPosition(i);
					lk.setPhone(linkman.getUnitPhone());
					lk.setPhoneType("单位电话");
					lk.setCreateTime(new Date());
					list.add(lk);
				}
				if (StringUtils.isNotBlank(linkman.getAddressJtzz())) {

					addressModel = new AddressModel();
					addressModel.setId(UUIDUtil.UUID32());
					addressModel.setStatus(1);
					addressModel.setName(linkman.getName());
					addressModel.setAddress(linkman.getAddressJtzz());
					addressModel.setCaseId(caseExcelDto.getId());
					addressModel.setAdrCat("家庭地址");
					addressModel.setRelation(linkman.getRelation());
					addressModel.setCreateEmpId(linkman.getCreateEmpId());
					addressModel.setCreateTime(new Date());
					addressMapper.save(addressModel);
				}
				if (StringUtils.isNotBlank(linkman.getAddressHjzz())) {
					addressModel = new AddressModel();
					addressModel.setId(UUIDUtil.UUID32());// id
					addressModel.setStatus(1);// 初始状态
					addressModel.setName(linkman.getName());// 案件人姓名
					addressModel.setAddress(linkman.getAddressHjzz());// 地址
					addressModel.setCaseId(caseExcelDto.getId());// 案件id
					addressModel.setAdrCat("户籍地址");// 地址类型
					addressModel.setRelation(linkman.getRelation());// 关系
					addressModel.setCreateEmpId(linkman.getCreateEmpId());// 创建人id
					addressModel.setCreateTime(new Date());// 创建时间
					addressMapper.save(addressModel);
				}
				if (StringUtils.isNotBlank(linkman.getAddressDwdz()) || StringUtils.isNotBlank(linkman.getAddressDwmc())) {
					if (StringUtils.isNotBlank(linkman.getAddressDwmc())) {
						addressModel = new AddressModel();
						addressModel.setId(UUIDUtil.UUID32());// id
						addressModel.setStatus(1);// 初始状态
						addressModel.setName(linkman.getName());// 案件人姓名
						if(StringUtils.isBlank(linkman.getAddressDwdz())){
							linkman.setAddressDwdz("");
						}
						if(StringUtils.isBlank(linkman.getAddressDwmc())){
							linkman.setAddressDwmc("");
						}
						addressModel.setAddress(linkman.getAddressDwmc()+"/"+linkman.getAddressDwdz());
						addressModel.setCaseId(caseExcelDto.getId());// 案件id
						addressModel.setAdrCat("单位地址");// 地址类型
						addressModel.setRelation(linkman.getRelation());// 关系
						addressModel.setCreateEmpId(linkman.getCreateEmpId());// 创建人id
						addressModel.setCreateTime(new Date());// 创建时间
						addressMapper.save(addressModel);
					} else {
						addressModel = new AddressModel();
						addressModel.setId(UUIDUtil.UUID32());// id
						addressModel.setStatus(1);// 初始状态
						addressModel.setName(linkman.getName());// 案件人姓名
						addressModel.setAddress(linkman.getAddressHjzz());// 地址
						addressModel.setCaseId(caseExcelDto.getId());// 案件id
						addressModel.setAdrCat("单位地址");// 地址类型
						addressModel.setRelation(linkman.getRelation());// 关系
						addressModel.setCreateEmpId(linkman.getCreateEmpId());// 创建人id
						addressModel.setCreateTime(new Date());// 创建时间
						addressMapper.save(addressModel);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<CaseModel> query(ParamCondition condition) {
		condition.clearPager();
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<CaseModel> list = singleMapper.query(condition);
		return list;
	}

	@Override
	public SearchResult<CaseModel> queryForPage(ParamCondition condition) {
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<CaseModel> list = singleMapper.query(condition);
		SearchResult<CaseModel> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/** 分解案件序列号方法 */
	@Override
	public String decompose(String content) {
		String decompose = null;
		if (StringUtils.isNotBlank(content)) {
			if (content.contains("\n")) {
				String[] ary = content.split("\n");
				StringBuffer testStrBuff = new StringBuffer("(");
				for (int i = 0; i < ary.length; i++) {
					testStrBuff.append("'" + ary[i].trim() + "',");
				}
				decompose = testStrBuff.substring(0, testStrBuff.length() - 1);
				decompose = decompose + ")";
			}
		}
		return decompose;
	}

	/**
	 * 生成案件复杂的查询语句
	 * 
	 * @param condition
	 *            检索条件
	 * @return SQL语句
	 */
	private StringBuilder getSql(ParamCondition condition) {

		// 下拉框选中1:caseCode(案件序列号) 2:caseFileNo(档案号) 3：caseNum(证件号) 4:
		// caseCard（卡号）
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String content = condition.get("content");// 内容
		String CodeAll = null;// 多个案件序列号
		String CodeOne = null;// 单个案件序列号
		String caseFileNoAll = null;// 多个档案号
		String caseFileNoOne = null;// 单个档案号
		String caseNumAll = null;// 多个档案号
		String caseNumOne = null;// 单个档案号
		String caseCardAll = null;// 多个卡号
		String caseCardOne = null;// 单个卡号
		String decompose = this.decompose(content);

		String selectNum = condition.get("selectByNum");// 选中的类型
		if (StringUtils.isNotBlank(selectNum)) {
			if ("1".equals(selectNum)) {
				if (StringUtils.isNotBlank(decompose)) {
					if (decompose.contains(",")) {
						CodeAll = decompose;
					}
				} else {
					CodeOne = condition.get("content");
				}
			} else if ("2".equals(selectNum)) {
				if (StringUtils.isNotBlank(decompose)) {
					if (decompose.contains(",")) {
						caseFileNoAll = decompose;
					}
				} else {
					caseFileNoOne = condition.get("content");
				}
			} else if ("3".equals(selectNum)) {
				if (StringUtils.isNotBlank(decompose)) {
					if (decompose.contains(",")) {
						caseNumAll = decompose;
					}
				} else {
					caseNumOne = condition.get("content");
				}
			} else if ("4".equals(selectNum)) {
				if (StringUtils.isNotBlank(decompose)) {
					if (decompose.contains(",")) {
						caseCardAll = decompose;
					}
				} else {
					caseCardOne = condition.get("content");
				}
			}
		}

		String Code = condition.get("batchCode");// 获取批次号
		String batchCode = caseBatchService.BatchCode(Code);// 调用批次号分解方法
		// 案件ID,作为其他业务查询条件,与列表条件无关
		String caseIds = condition.get("caseIds");
		// 批次ID，作为其他业务查询条件,与列表条件无关 作用：用于案件导出-批次导出， 查询批次下所有的案件。 wcy
		List<String> batchIds = condition.get("batchIds");
		// 分案状态
		String caseAssignState = condition.get("caseAssignState");
		// 风控方
		String orgId = condition.get("orgId");
		// 风控员
		String caseAssignedId = condition.get("caseAssignedId");
		// 姓名
		String caseName = condition.get("caseName");
		// 证件号
		/* String caseNum = condition.get("caseNum"); */
		// 手机号
		String phone = condition.get("phone");
		// 卡号
		/* String caseCard = condition.get("caseCard"); */
		// 案件状态
		String casestatus = condition.get("casestatus");
		// 委托方
		String entrustId = condition.get("entrustId");
		// 风控状态
		String collecStateId = condition.get("collecStateId");
		// 逾期账龄
		String overdueAge = condition.get("overdueAge");
		// 案件类型
		String caseType = condition.get("caseType");
		// 委托开始日期,委托结束日期
		String entrustDateStart = condition.get("entrustDateStart");
		String entrustDateEnd = condition.get("entrustDateEnd");
		// 委案金额开始,委案金额结束
		String caseMoneyStart = condition.get("caseMoneyStart");
		String caseMoneyEnd = condition.get("caseMoneyEnd");
		// 还款情况
		String circumstance = condition.get("circumstance");

		// 车架号
		/* String caseFileNo = condition.get("caseFileNo"); */
		// 客户号
		String cusNo = condition.get("cusNo");

		Integer caseState = condition.get("caseState");

		// 委托开始日期,委托结束日期
		String endDate = condition.get("endDate");
		String endDate1 = condition.get("endDate1");

		//字体颜色
		String color = condition.get("color");
		// 开始构建查询语句
		StringBuilder sql = new StringBuilder("select ");

		if (StringUtils.isNotBlank(phone)) {
			sql.append(" distinct ");

		}
		// 案件表所有信息
		sql.append(
				"cinfo.last_phone_time as lastPhone,cinfo.color, cinfo.agent_rate, cinfo.id,cinfo.batch_id, cinfo.case_code,cinfo.org_name,cinfo.org_id,cinfo.case_name,cinfo.case_age,")
				.append("cinfo.case_money,cinfo.state,cinfo.mobile1,cinfo.mobile2,cinfo.case_date,cinfo.case_assigned_id,")
				.append("cinfo.case_card,cinfo.case_num,cinfo.collec_state_id,cinfo.case_sex,")

				.append("cinfo.account_no,cinfo.case_file_no,cinfo.currency,cinfo.loan_contract,cinfo.overdue_days,cinfo.overdue_age,cinfo.entrust_rate,cinfo.loan_startdate,cinfo.remark1,cinfo.sales_dep,cinfo.surplus_principal,cinfo.domicile,cinfo.principal,")
				// 批次号
				.append("cbatch.batch_code,")

				// 批次号
				.append("cinfo.case_backdate,")

				// 风控人
				.append("caseAssigned.user_name as caseAssignedName,")
				// 委托方名称
				.append("entrust.name as entrustName,")

				.append("tb.cp_time,cinfo.case_num_id,cinfo.bank_account,cinfo.overdue_money,cinfo.bill,cinfo.repayment_periods,cinfo.overdue_periods,cinfo.overdue_interest,cinfo.late_fee,cinfo.credit_id,cinfo.social_card_no,cinfo.interest_money,cinfo.month_repayment,cinfo.loan_rate,cinfo.loan_enddate,cinfo.case_app_no,")

				.append("ccl.number,ccl.vin_no,ccl.lice_no,ccl.brand,")

				.append("cinfo.family_address,cinfo.family_phone,cinfo.company_address,cinfo.company_phone,")

				// 已还款（实际还款金额）
				.append("if(cinfo.case_money - ifnull(tb.paid_num,0) < 0,0,cinfo.case_money - ifnull(tb.paid_num,0) - ifnull(cinfo.out_derate,0)) as surplus_money,")
				// PTP承诺还款金额
				.append("ifnull(tb.ptp_money,0) as ptp_money,")
				// CP待确认还款金额
				.append("ifnull(tb.cp_money,0) as cp_money,")
				// 已确认还款金额
				.append("ifnull(tb.paid_num,0) as paid_num ").append("from case_info as cinfo ");
		// 加入批次表
		sql.append("join case_batch as cbatch on cinfo.batch_id = cbatch.id ");
		// 加入风控员信息
		sql.append("left join employee_info as caseAssigned on cinfo.case_assigned_id = caseAssigned.id ");

		sql.append("LEFT JOIN case_car_loan ccl ON ccl.case_id=cinfo.id ");

		if (StringUtils.isNotBlank(phone)) {
			sql.append(" INNER JOIN case_linkman linkman on cinfo.id=linkman.case_id ");
		}

		// 加入委托方表
		sql.append("left join entrust on cbatch.entrust_id = entrust.id ");

		// 还款开始日期,还款结束日期
		String paidTimeStart = condition.get("paidTimeStart");
		String paidTimeEnd = condition.get("paidTimeEnd");
		// 还款日期 筛选
		// 加入临时表1,查询-- PTP承诺还款金额,CP待确认还款金额,已确认还款金额
		sql.append("left join (").append("select ")
				.append("case_paid.cp_time,case_paid.case_id,sum(case_paid.ptp_money) as ptp_money ,")
				.append("sum(case_paid.cp_money) as cp_money ,").append("sum(case_paid.paid_num) paid_num ")
				.append("from (SELECT case_id,cp_time,sum(IF (state = 2,IFNULL(paid_num, 0),0)) AS paid_num,sum(IF (state = 1,IFNULL(cp_money, 0),0)) AS cp_money,sum(IF (state = 0,IFNULL(ptp_money, 0),0)) AS ptp_money FROM case_paid where 1=1 ");

		if (StringUtils.isNotBlank(paidTimeStart) && StringUtils.isNotBlank(paidTimeEnd)) {
			// 加一天
			// String end =
			// DateUtil.date2Str(DateUtil.addDays(DateUtil.str2Date(paidTimeEnd),
			// 1),null);
			String paidTime = " and  DATE_FORMAT(case_paid.paid_time,'%Y-%m-%d') BETWEEN '" + paidTimeStart + "' AND '"
					+ paidTimeEnd + "'";
			sql.append(paidTime);
		}
		sql.append(" GROUP BY case_id) case_paid group by case_id");
		sql.append(" ) as tb on tb.case_id = cinfo.id ")// ;
				.append("where 1 = 1 and cbatch.status!=-1 and cinfo.status!=-1 ");

		// 委托方
		if (StringUtils.isNotBlank(entrustId)) {
			sql.append(" and cbatch.entrust_id in (" + SQLUtil.warpSql(entrustId) + ") ");
		}


		String queryOrgs = getQueryOrgs();
	
		sql.append(" and ( ");
		sql.append(" cinfo.org_id in ("+SQLUtil.warpSql(queryOrgs)+") ");
		String attachEntrustId = currentUser.getAttachEntrustId();
		if(StringUtils.isNotBlank(attachEntrustId)){
			sql.append(" or cbatch.entrust_id in (" + SQLUtil.warpSql(attachEntrustId) + ") ");
		}
		if("admin".equals(currentUser.getLoginName())){
			sql.append(" or 1=1 ");
		}
		sql.append(")");
		


		// 风控方
		if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and cinfo.org_id in (");
			String orgPath = organizationService.findById(orgId).getPath();// 机构路径
			sql.append("select id from sys_organization where path like '" + orgPath + "%'");
			sql.append(")");
		}

		if (StringUtils.isNotBlank(caseIds)) {
			sql.append(" and cinfo.id in (" + SQLUtil.warpSql(caseIds) + ") ");
		}

		if (null == caseState) {
			sql.append(" and cinfo.state in (0,1,4,5) ");
		} else {
			sql.append(" and cinfo.state in (0,1,4,3,5)");
		}

		// 批次id 拼接sql wcy
		if (CollectionUtils.isNotEmpty(batchIds)) {
			StringBuilder temp = new StringBuilder(" and cinfo.batch_id in (");
			for (String batchId : batchIds) {
				temp.append("'" + batchId + "',");
			}
			temp.delete(temp.length() - 1, temp.length());
			temp.append(") ");
			sql.append(temp);
		}

		// 案件分案状态
		if (CollectionConst.CASE_ALREADY_ASSIGN.equals(caseAssignState)) {
			sql.append(" and cinfo.case_assigned_id is not null ");
		} else if (CollectionConst.CASE_NOT_ASSIGN.equals(caseAssignState)) {
			sql.append(" and cinfo.case_assigned_id is null ");
		}

		// 风控员
		if (StringUtils.isNotBlank(caseAssignedId)) {
			sql.append(" and cinfo.case_assigned_id in (" + SQLUtil.warpSql(caseAssignedId) + ")");
		}
		// 案件序列号
		if (StringUtils.isNotBlank(CodeAll)) {
			sql.append(" and cinfo.case_code in " + CodeAll + "");
		}
		if (StringUtils.isNotBlank(CodeOne)) {
			sql.append(" and cinfo.case_code = '" + CodeOne + "'");
		}

		if (StringUtils.isNotBlank(caseFileNoAll)) {
			sql.append(" and cinfo.case_file_no in " + caseFileNoAll + "");
		}
		if (StringUtils.isNotBlank(caseFileNoOne)) {
			sql.append(" and cinfo.case_file_no = '" + caseFileNoOne + "'");
		}

		if (StringUtils.isNotBlank(caseNumAll)) {
			sql.append(" and cinfo.case_num in " + caseNumAll + "");
		}
		if (StringUtils.isNotBlank(caseNumOne)) {
			sql.append(" and cinfo.case_num = '" + caseNumOne + "'");
		}

		if (StringUtils.isNotBlank(caseCardAll)) {
			sql.append(" and cinfo.case_card in " + caseCardAll + "");
		}
		if (StringUtils.isNotBlank(caseCardOne)) {
			sql.append(" and cinfo.case_card = '" + caseCardOne + "'");
		}

		// 姓名
		if (StringUtils.isNotBlank(caseName)) {
			sql.append(" and cinfo.case_name like '" + caseName + "%'");
		}

		if (StringUtils.isNotBlank(phone)) {
			sql.append(" and linkman.phone='" + phone + "'");

		}
		// 案件状态
		if (StringUtils.isNotBlank(casestatus)) {
			sql.append(" and cinfo.state = " + casestatus);
		}

		// 批次号
		if (StringUtils.isNotBlank(batchCode)) {
			sql.append(" and cbatch.id in " + batchCode);
		}
		// 风控状态
		if (StringUtils.isNotBlank(collecStateId)) {
			sql.append(" and cinfo.collec_state_id = '" + collecStateId + "' ");
		}
		// 逾期账龄
		if (StringUtils.isNotBlank(overdueAge)) {
			sql.append(" and cinfo.overdue_age = '" + overdueAge + "' ");
		}
		// 案件类型
		if (StringUtils.isNotBlank(caseType)) {
			sql.append(" and cbatch.type_id in (" + SQLUtil.warpSql(caseType) + ") ");
		}
		// 委托日期
		if (StringUtils.isBlank(entrustDateStart)) {
			entrustDateStart = "1000-01-01";
		}
		if (StringUtils.isBlank(entrustDateEnd)) {
			entrustDateEnd = "9999-12-31";
		}
		// 退案日期
		if (StringUtils.isBlank(endDate)) {
			endDate = "1000-01-01";
		}
		if (StringUtils.isBlank(endDate1)) {
			endDate1 = "9999-12-31";
		}
		sql.append(" and (DATE_FORMAT(cinfo.case_date,'%Y-%m-%d') between '" + entrustDateStart + "' and '"
				+ entrustDateEnd + "' )");
		// 退案日期
		sql.append(
				" and (DATE_FORMAT(cinfo.case_backdate,'%Y-%m-%d') between '" + endDate + "' and '" + endDate1 + "' )");

		// 委案金额
		if (StringUtils.isNotBlank(caseMoneyStart) && StringUtils.isNotBlank(caseMoneyEnd)) {
			sql.append(" and (cinfo.case_money between " + caseMoneyStart + " and " + caseMoneyEnd + ") ");
		}
		// 还款情况
		if (StringUtils.isNotBlank(circumstance)) {
			// 未还款
			if (CollectionConst.NOT_REPAY.equals(circumstance)) {
				sql.append(" and cinfo.id not in(select case_id from case_paid where state = 2) ");
			}
			// 已还款
			else if (CollectionConst.PART_REPAY.equals(circumstance)) {
				sql.append(" and tb.paid_num>0 and tb.paid_num  ");// <
																	// cinfo.case_money
			}
		}
		// 客户号
		if (StringUtils.isNotBlank(cusNo)) {
			sql.append(" and cinfo.cus_no in (" + SQLUtil.warpSql(cusNo) + ") ");
		}
		
		//案件标色
		if (StringUtils.isNotBlank(color)) {
			sql.append(" and cinfo.color='" + color + "'");
		}
		// 排序处理
		if (condition.hasOrder()) {
			sql.append(" order by " + condition.getSort() + " " + condition.getOrder());
		} else {
			sql.append(" order by cinfo.create_time desc,cinfo.batch_id,cinfo.state asc ");
		}
		return sql;
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
	public void divisionCase(String userId, List<String> caseIds, String type) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		// 被分配用户信息
		EmployeeInfoModel emp = employeeInfoService.findById(userId);
		if (emp == null) {
			throw new ServiceException("被分配的用户不存在，无法进行分配");
		}
		Date now = new Date();
		// 1、更新案件分配信息
		for (String cid : caseIds) {
			Map<String, Object> params = new HashMap<>();
			// 被分配人ID
			params.put("caseAssignedId", userId);
			// 修改时间
			params.put("modifyTime", now);
			// 修改人
			params.put("modifyEmpId", currentUser.getId());
			// 分配ID
			params.put("caseAssignId", currentUser.getId());
			// 分配时间
			params.put("caseAssignTime", now);
			// 分配案件ID
			params.put("caseId", cid);
			// 被分配用户机构ID
			params.put("orgId", emp.getOrgId());
			params.put("orgName", emp.getOrgName());
			// 1、更新案件分配信息
			caseMapper.updateCaseAssignedInfo(params);
		}

		// 2、插入案件分配
		for (String caseId : caseIds) {
			CaseAssignModel caseAssign = new CaseAssignModel();
			caseAssign.setCaseAssignId(currentUser.getId());
			caseAssign.setCaseAssignedId(userId);
			caseAssign.setCaseAssignTime(now);
			caseAssign.setCaseId(caseId);
			caseAssign.setCaseAssignName(currentUser.getUserName());
			caseAssign.setCaseAssignedName(emp.getUserName());
			caseAssignService.save(caseAssign);

			// 保存操作记录 bianbianbian
			String content = "分配案件至" + emp.getUserName();
			hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.casM, caseId, type, content, caseId,
					HurryRecordConst.noaction));

		}
		messageReminderService.saveReminder("您有新案件, 请查看", 0, userId,"/collection/casecollect/assigned","我的案件");
	}

	@Override
	public List<Map<String, Object>> autoDivisionCompute(List<CaseModel> list, String divisionWay, String userList) {
		List<JSONObject> jsonArray = JSON.parseArray(userList, JSONObject.class);
		if (DIVISION_WAY_NUM.equals(divisionWay)) {
			return autoDivisionComputeForNum(list, jsonArray);
		} else if (DIVISION_WAY_MONEY.equals(divisionWay)) {
			return autoDivisionComputeForMoney(list, jsonArray);
		} else if (DIVISION_WAY_COMPOSITE.equals(divisionWay)) {

		} else {
			throw new ServiceException("不支持的分配方式");
		}
		return null;
	}

	/**
	 * 案件数量分案
	 * 
	 * @param list
	 *            案件集合
	 * @param jsonArray
	 *            被分配人json数组
	 * @return
	 */
	private List<Map<String, Object>> autoDivisionComputeForNum(List<CaseModel> list, List<JSONObject> jsonArray) {
		// 迭代器
		Iterator<CaseModel> it = list.iterator();
		// 案件总数
		final BigDecimal total = new BigDecimal(list.size());
		// 剩余为分配案件数量,初始化为案件总数,如已分配大小会随之改变
		BigDecimal residue = new BigDecimal(list.size());
		// 返回结果
		List<Map<String, Object>> results = new ArrayList<>();
		// 便利被分配人信息
		for (JSONObject json : jsonArray) {
			Map<String, Object> map = new HashMap<>(jsonArray.size());
			map.put("userName", json.getString("userName"));
			String userId = json.getString("id");
			Integer r = json.getInteger("rate");
			map.put("userId", userId);
			map.put("caseNum", 0);
			map.put("caseMoney", 0);
			map.put("rate", r);
			// 获取实际的利率比,页面的值除以100
			BigDecimal rate = new BigDecimal(r).divide(NumberUtil.ONE_HUNDRED);
			// 计算比例应占的案件数量,四舍五入向上取整
			BigDecimal num = rate.multiply(total).setScale(0, BigDecimal.ROUND_HALF_UP);
			// 计算出的数量与剩余数量进行比较
			int compareTo = num.compareTo(residue);
			// 如果数量等于或小于剩余数量,给对应的用户分配案件
			if (residue.intValue() > 0 && num.intValue() > 0) {
				if (compareTo == 0 || compareTo == -1) {
					populateComputeResult(json, map, num.intValue(), it);
					residue = residue.subtract(num);// 重置剩余数量
				} else {
					populateComputeResult(json, map, residue.intValue(), it);
					residue = BigDecimal.ZERO;
				}
			}
			results.add(map);
		}
		return results;
	}

	/**
	 * 案件金额分案
	 * 
	 * @param list
	 *            案件集合
	 * @param jsonArray
	 *            被分配人json数组
	 * @return
	 */
	private List<Map<String, Object>> autoDivisionComputeForMoney(List<CaseModel> list, List<JSONObject> jsonArray) {
		// 本批次案件总金额
		BigDecimal totalMoney = BigDecimal.ZERO;
		for (CaseModel cs : list) {
			totalMoney = totalMoney.add(cs.getCaseMoney());
		}
		// 按照委案金额,从小到大进行排序
		list.sort(new Comparator<CaseModel>() {
			@Override
			public int compare(CaseModel o1, CaseModel o2) {
				return o1.getCaseMoney().compareTo(o2.getCaseMoney());
			}
		});
		// 迭代器
		Iterator<CaseModel> it = list.iterator();
		// 对用户利率进行排序
		jsonArray.sort(new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				int ret = 0;
				if (o1.getIntValue("rate") > o2.getIntValue("rate")) {
					ret = -1;
				} else if (o1.getIntValue("rate") < o2.getIntValue("rate")) {
					ret = 1;
				}
				return ret;
			}
		});
		// 返回结果
		List<Map<String, Object>> results = new ArrayList<>();
		// 便利被分配人信息
		for (JSONObject json : jsonArray) {
			Map<String, Object> map = new HashMap<>(jsonArray.size());
			map.put("userName", json.getString("userName"));
			String userId = json.getString("id");
			Integer r = json.getInteger("rate");
			map.put("userId", userId);
			map.put("caseNum", 0);
			map.put("caseMoney", 0);
			map.put("rate", r);
			// 获取实际的利率比,页面的值除以100
			BigDecimal rate = new BigDecimal(r).divide(NumberUtil.ONE_HUNDRED);
			// 计算比例应占的案件金额
			BigDecimal percentMoney = rate.multiply(totalMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
			List<CaseModel> ls = new ArrayList<>();
			while (it.hasNext()) {
				CaseModel cs = it.next();
				// 金额不够,把当前便利的案件分给员工
				if (new BigDecimal(getTotalCaseMoney(ls)).compareTo(percentMoney) == -1) {
					ls.add(cs);
				}
				it.remove();// 移除案件
				// 判断用户案件是否分足够
				int compareTo = new BigDecimal(getTotalCaseMoney(ls)).compareTo(percentMoney);
				if (compareTo == 1 || compareTo == 0) {
					break;
				}
			}
			populateComputeResult(json, map, ls.size(), ls.iterator());
			results.add(map);
		}
		return results;
	}

	/**
	 * 计算List中包含案件的总委案金额,如果为空返回0
	 * 
	 * @param ls
	 * @return
	 */
	private double getTotalCaseMoney(List<CaseModel> ls) {
		double result = 0.0;
		if (CollectionUtils.isNotEmpty(ls)) {
			for (CaseModel cs : ls) {
				result += cs.getCaseMoney().doubleValue();
			}
		}
		return result;
	}

	/**
	 * 填充计算结果
	 * 
	 * @param json
	 *            便利的json
	 * @param map
	 *            返回的map
	 * @param num
	 *            便利次数
	 * @param it
	 *            数据迭代器,便利之后会删除便利过的元素
	 * @return
	 */
	private Map<String, Object> populateComputeResult(JSONObject json, Map<String, Object> map, int num,
			Iterator<CaseModel> it) {
		if (!it.hasNext())
			return map;
		Double totalCaseMoney = 0.0;
		StringBuilder caseIds = new StringBuilder();
		for (int i = 0; i < num; i++) {
			CaseModel cm = it.next();
			Double caseMoney = cm.getCaseMoney().doubleValue();
			totalCaseMoney += caseMoney;
			caseIds.append(cm.getId() + ",");
			it.remove();
		}
		caseIds.delete(caseIds.length() - 1, caseIds.length());
		map.put("caseIds", caseIds.toString());
		map.put("caseNum", num);
		map.put("caseMoney", new BigDecimal(totalCaseMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
		return map;
	}

	@Override
	public void autoDivision(String listUserGrop) {
		for (JSONObject json : JSON.parseArray(listUserGrop, JSONObject.class)) {
			String caseIds = json.getString("caseIds");
			if (StringUtils.isNotBlank(caseIds)) {
				divisionCase(json.getString("userId"), Arrays.asList(StringUtils.split(caseIds, ",")),
						CaseDivisionConst.autoDivision);
			}
		}
	}

	@Override
	public void changeState(Integer state, List<String> caseIds, String remark) {
		// 取巧,案件状态0-5
		if (state >= 0 && state <= 5) {
			EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
			Map<String, Object> params = new HashMap<>();
			// 修改时间
			params.put("modifyTime", new Date());
			// 修改人
			params.put("modifyEmpId", currentUser.getId());
			// 案件ID
			params.put("caseIds", caseIds);
			// 案件状态
			params.put("state", state);
			// 更新案件状态
			caseMapper.changeState(params);
			// 插入风控记录
			for (String caseId : caseIds) {
				HurryRecordModel hr = new HurryRecordModel();
				hr.setCaseId(caseId);
				hr.setContent("更新案件状态,caseId=" + caseId);
				hr.setCreateEmpId(currentUser.getId());
				hr.setHurCat("casM");

				hr.setCreateTime(new Date());
				hr.setCreateEmpId(currentUser.getId());
				hr.setOperatorName(currentUser.getUserName());
				hurryRecordService.save(hr);
			}
		} else {
			throw new ServiceException("不存在的案件状态,无法更新");
		}
	}

	@Override
	public Map<String, Object> queryStatistics(ParamCondition condition) {
		condition.clearPager();
		return caseStatisticsService.queryStatistics(getSql(condition).toString());
	}

	@Override
	public List<CaseModel> findCaseByBatchId(String batchId) {

		return caseMapper.findCaseByBatchId(batchId);
	}

	/**
	 * 查询批次下的所有案件
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public List<CaseModel> findBatchCodes(List<String> batchIds, String[] array) {
		// 没勾选字段 提示
		if (array.length == 0) {
			throw new ServiceException("请勾选导出字段，至少一个！");
		}
		// 处理batchIds ，batchIds是数组 .xml里需要他做查询条件
		Map<String, Object> params = new HashMap<>(1);
		params.put("batchIds", batchIds);
		List<CaseModel> list = caseMapper.findBatchCodes(params);
		if (list.size() == 0) {
			throw new ServiceException("当前批次下没有案件，没有可导出数据！");
		}
		return list;
	}

	/**
	 * 查询批次下的所有案件
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public List<CaseCarDto> findBatchCar(List<String> batchIds, String[] array) {
		// 没勾选字段 提示
		if (array.length == 0) {
			throw new ServiceException("请勾选导出字段，至少一个！");
		}
		// 处理batchIds ，batchIds是数组 .xml里需要他做查询条件
		Map<String, Object> params = new HashMap<>(1);
		params.put("batchIds", batchIds);
		List<CaseCarDto> list = caseMapper.findBatchCar(params);
		if (list.size() == 0) {
			throw new ServiceException("当前批次下没有案件，没有可导出数据！");
		}
		return list;
	}

	@Override
	public Map<String, Object> queryStatisticsForCaseCounts(ParamCondition condition) {
		condition.clearPager();
		StringBuilder sql = new StringBuilder("select ")
				// 案件总数
				.append("count(tb.id) AS caseTotalCount,")
				// 案件总金额
				.append("ifnull(sum(tb.case_money), 0) as caseTotalMoney,")
				// 统计已分配
				.append("count(tb.case_assigned_id) as assignedCount,")
				// 统计未分配
				.append("count(tb.id) - count(tb.case_assigned_id) as undistributedCount ")
				// 嵌套之前的查询结果
				.append("from (").append(getSql(condition)).append(") as tb ");

		condition.put("dynamicSql", sql.toString());

		return caseMapper.statistics(condition);
	}

	@Override
	public CaseModel save(CaseModel model) {
		CaseModel m = super.save(model);
		m.setColor("0");
		return m;
	}

	@Override
	public void updateColor(String[] ids, String color) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("color", color);
		params.put("list", ids);// model存放审批内容，list存放选中id
		caseMapper.updateForIds(params);

	}

	@Override
	public List<CaseModel> findCasesByIds(String[] caseIds) {
		return caseMapper.findCasesByIds(caseIds);
	}

	@Override
	public int queryReminderCaseCount(int day) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDateStr=sdf.format(now);
		Date endDate = DateUtil.addDays(now, day);
		String endDateStr=sdf.format(endDate);
		int c=caseMapper.findReminderCaseCount(SecurityUtil.getCurrentUser().getId(),beginDateStr, endDateStr);
		return c;
	}

	@Override
	public List<CaseModel> queryCaseTotoFollow() {
		String userId = SecurityUtil.getCurrentUser().getId();
		List<CaseModel> list=caseMapper.caseToFollow(userId, DateUtil.date2Str(new Date(), null));
		return list;
	}

	@Override
	public int queryDivisionCaseCount() {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		
		String orgPath = organizationService.findById(SecurityUtil.getCurrentUser().getOrgId()).getPath();// 机构路径
		return caseMapper.divisionCaseCount(attach,SQLUtil.warpSql(getQueryOrgs()), currentUser.getOrgId(),orgPath, currentUser.getLoginName());
	}

}
