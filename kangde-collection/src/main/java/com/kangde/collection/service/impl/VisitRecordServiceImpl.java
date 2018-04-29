package com.kangde.collection.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.AddressDto;
import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.dto.VisitRecordVo;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.PhoneRecordMapper;
import com.kangde.collection.mapper.VisitRecordMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.util.WEBUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;

@Service
public class VisitRecordServiceImpl extends AbstractService<VisitRecordModel, String> implements VisitRecordService {

	@Autowired
	AddressMapper addressMapper;
	@Autowired
	AddressService addressService;
	@Autowired
	private VisitRecordMapper visitRecordMapper;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private PhoneRecordMapper phoneRecordMapper;
	@Autowired
	private HurryRecordService hurryRecordService;
	
	@Autowired
	private EmployeeInfoService employeeInfoService;
	
	@Autowired 
	private CaseBatchService caseBatchService;

	@Override
	public VisitRecordModel save(VisitRecordModel model) {
		model.setId(UUIDUtil.UUID32());// ID生成
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		model.setCreateEmpId(currentUser.getId());
		model.setModifyEmpId(currentUser.getId());
		Date date = new Date();
		model.setModifyTime(date);
		model.setApplyEmpId(currentUser.getUserName());
		model.setApplyTime(date);
		model.setCreateTime(date);// 创建时间
		model.setStatus(CoreConst.STATUS_NORMAL);// 系统状态,默认正常
		visitRecordMapper.save(model);
		return model;
	}

	@Override
	public void applyToVisit(VisitRecordModel model, String addressId) {
		// 1 保存申请外访记录
		model.setId(UUIDUtil.UUID32());// ID生成
		Date time = new Date();
		model.setState(0);
		String userId = SecurityUtil.getCurrentUser().getId();

		model.setAddressId(addressId);
		model.setCreateEmpId(userId);
		model.setApplyEmpId(userId);
		model.setCreateTime(time);// 创建时间
		model.setModifyTime(time);
		model.setApplyTime(time);
		model.setStatus(CoreConst.STATUS_NORMAL);// 系统状态,默认正常
		model.setApproveState(0);
		VisitRecordMapper mapper = (VisitRecordMapper) singleMapper;
		AddressModel address = addressMapper.findById(addressId);
		model.setAreaId1(address.getAreaId1());
		model.setAreaId2(address.getAreaId2());
		model.setAreaId3(address.getAreaId3());
		AddressDto a;
		try {
			a = addressService.findFullAddress(address.getAreaId1(),
					address.getAreaId2(), address.getAreaId3());
			model.setVisitAddress(a + address.getAddress());
			model.setName(address.getName());
			mapper.save(model);
			// 2 更新地址visApp状态
			address.setVisCount(address.getVisCount() == null ? 1 : address.getVisCount() + 1);
			address.setVisApp(1);
			addressMapper.updateApplyToVisitStatusByPrimaryKey(address);
			
			
			//保存操作记录  bianbianbian
			//String content="建议信函:name, address [ type]";
			String content=address.getName()+", "+model.getVisitAddress()+" [ "+address.getAdrCat()+"]";
			hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.oVis, model.getCaseId(), "建议外访", content, addressId, HurryRecordConst.noaction));
			
		} catch (AddressException e) {
			 e.printStackTrace();
			 throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 查询待排程列表
	 * 
	 * @param condition
	 * @return
	 */
	@Override
	public SearchResult<VisitRecordDto> queryToApproveORToAppoint(ParamCondition condition) {
		// 查询当前催收人所在分公司催收区域
		//List<String> areaList = WEBUtil.getSessionAttribute("FGSArea");
		String code=condition.get("batchCode");
		String batchCode=caseBatchService.BatchCode(code);
		condition.put("batchCode", batchCode);
		//condition.put("area", areaList);
		List<VisitRecordDto> list = visitRecordMapper.queryToApproveORToAppoint(condition);
		SearchResult<VisitRecordDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 已排程外访 已完成外访
	 * 
	 * @param condition
	 * @return
	 */
	@Override
	public SearchResult<VisitRecordDto> queryAppointedORFinished(ParamCondition condition) {
		// 模糊查询
		// 已排程和已完成外访：催收员和外访员可以看见外访员是自己的记录, 其他角色可以可见所有的
		if (this.isCollectorOrVisitor()) {
			condition.put("visitUserId", SecurityUtil.getCurrentUser().getId());
		}
		
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<VisitRecordDto> list = visitRecordMapper.queryAppointedORFinished(condition);
		SearchResult<VisitRecordDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	
	
	private StringBuilder getSql(ParamCondition condition) {
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT\n" +
		"		IFNULL(\n" +
		"		(\n" +
		"		SELECT\n" +
		"		SUM(IFNULL(paid_num, 0))\n" +
		"		FROM\n" +
		"		case_paid\n" +
		"		WHERE\n" +
		"		case_id\n" +
		"		= c.id\n" +
		"		AND state = 2\n" +
		"		),\n" +
		"		0\n" +
		"		) AS paidNum,\n" +
		"		vr.id,\n" +
		"		c.collec_state_id,\n" +
		"		vr.case_id,\n" +
		"		c.case_code,\n" +
		"		ad.`name`,\n" +
		"		ad.relation,\n" +
		"		vr.sex,\n" +
		"		vr.age,\n" +
		"		c.case_money,\n" +
		"		vr.visit_user,\n" +
		"		vr.visit_report,\n" +
		"		vr.actual_visit_time,\n" +
		"		vr.area_id1,\n" +
		"		vr.area_id2,\n" +
		"		vr.area_id3,\n" +
		"		vr.visit_time,\n" +
		"		vr.address_id,\n" +
		"		vr.visit_user_id,\n" +
		"		vr.is_effective,\n" +
		"		vr.visit_address,\n" +
		"		vr.visit_state,\n" +
		"		ad.vis_count,\n" +
		"		vr.`require`,\n" +
		"		ei.user_name,\n" +
		"		vr.apply_time,\n" +
		"		so. NAME AS orgName,\n" +
		"		r1.name AS\n" +
		"		caName,\n" +
		"		r2.name AS ccName,\n" +
		"		r3.name AS cpName,\n" +
		"		ei.org_id,\n" +
		"		c.`color`,\n" +
		"		et.`name` AS ename,\n" +
		
		"		(SELECT COUNT(*) FROM case_paid  WHERE case_paid.case_id=vr.`case_id` AND case_paid.state=2 AND paid_time BETWEEN vr.`actual_visit_time` AND DATE_ADD(actual_visit_time, INTERVAL +10 DAY)) AS countSize\n" +

		"		FROM\n" +
		"		visit_record\n" +
		"		vr\n" +
		"		LEFT JOIN address ad ON vr.address_id = ad.id\n" +
		"		LEFT JOIN case_info c\n" +
		"		ON vr.case_id = c.id\n" +
		"		LEFT JOIN case_batch cb ON c.batch_id = cb.id\n" +
		"		LEFT\n" +
		"		JOIN employee_info ei ON c.case_assigned_id = ei.id\n" +
		"		LEFT JOIN\n" +
		"		employee_info e ON vr.visit_user_id = e.id\n" +
		"		LEFT JOIN sys_organization\n" +
		"		so ON ei.org_id = so.id\n" +
		"		LEFT JOIN region r1 ON r1.id = vr.area_id3\n" +
		"		LEFT\n" +
		"		JOIN region r2 ON r2.id = vr.area_id2\n" +
		"		LEFT JOIN region r3 ON r3.id =\n" +
		"		vr.area_id1\n" +
		"		LEFT JOIN entrust et ON et.`id`=cb.`entrust_id`\n" +
		"		WHERE\n" +
		"		1=1 and  c.status!=-1 ");

		String caseName=condition.get("caseName");
		if(StringUtils.isNotBlank(caseName)){
			sql.append(" and c.case_name like '%"+caseName+"%' ");
		}
		
		Integer state=condition.get("state");
		if (state==3) {
			sql.append(" and c.state in ('0','1','4')");
		}else if(state==4) {
			sql.append(" and c.state in ('0','1','4','3')");
		}else{
			sql.append(" and c.state in ('')");
		}
		
		if (state!=null) {
			sql.append(" and vr.state='"+state+"'");
		}
		
		String caseAssignedId=condition.get("caseAssignedId");
		if (StringUtils.isNotBlank(caseAssignedId)) {
			sql.append(" and c.case_assigned_id='"+caseAssignedId+"'");
		}
	
		String visitUserId=condition.get("visitUserId");
		if (StringUtils.isNotBlank(visitUserId)) {
			
			String[] split = visitUserId.split(",");
			sql.append(" and (");
			
			StringBuffer sb=new StringBuffer();
			for (String item : split) {
				String itemWith=","+item+",";
				sb.append("or vr.visit_user_id like '%"+itemWith+"%'  ");
			}
			String s=sb.toString().substring(2);
			sql.append(s+" )");
			
		}
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			sql.append(" and entrust_id ='"+entrustId+"'");
		}
		
		
		String caseCode=condition.get("caseCode");
		if (StringUtils.isNotBlank(caseCode)) {
			sql.append(" and c.case_code like CONCAT('%','"+caseCode+"','%' )");
		}
		
		String areaId1=condition.get("areaId1");
		if (StringUtils.isNotBlank(areaId1)) {
			sql.append(" and vr.area_id1 ='"+areaId1+"'");
		}
		
		String areaId2=condition.get("areaId2");
		if (StringUtils.isNotBlank(areaId2)) {
			sql.append(" and vr.area_id2 ='"+areaId2+"'");
		}
		
		String areaId3=condition.get("areaId3");
		if (StringUtils.isNotBlank(areaId3)) {
			sql.append(" and vr.area_id3 ='"+areaId3+"'");
		}
		
		
		String name=condition.get("name");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and vr.name like CONCAT('%','"+name+"','%' )");
		}
		
		String collecStateId=condition.get("collecStateId");
		if (StringUtils.isNotBlank(collecStateId)) {
			sql.append(" and collec_state_id ='"+collecStateId+"'");
		}
		
		String visitState=condition.get("visitState");
		if (StringUtils.isNotBlank(visitState)) {
			sql.append(" and vr.visit_state ='"+visitState+"'");
		}
		
		String visitTimeStart=condition.get("visitTimeStart");
		String visitTimeEnd=condition.get("visitTimeEnd");
		if (StringUtils.isNotBlank(visitTimeStart)&&StringUtils.isNotBlank(visitTimeEnd)) {
			sql.append(" and (DATE_FORMAT(visit_time,'%Y-%m-%d') between '"+visitTimeStart+"' and '"+visitTimeEnd+"')");
		}
		
		String actualVisitTimeStart=condition.get("actualVisitTimeStart");
		String actualVisitTimeEnd=condition.get("actualVisitTimeEnd");
		if (StringUtils.isNotBlank(actualVisitTimeStart)&&StringUtils.isNotBlank(actualVisitTimeEnd)) {
			sql.append(" and (DATE_FORMAT(actual_visit_time,'%Y-%m-%d') between '"+actualVisitTimeStart+"' and '"+actualVisitTimeEnd+"')");
		}
		// 排序处理
		if (condition.hasOrder()) {
			sql.append(" order by " + condition.getSort() + " " + condition.getOrder());
		} else {
			sql.append(" order by visit_time desc ");
		}
		return sql;
	}

	
	/**
	 * 外访排程 完成外访 返回排程
	 * 
	 * @param state
	 * @param model
	 * @return
	 */
	@Override
	public void appointVisitor(VisitRecordModel model) {
		visitRecordMapper.update(model);
		addressMapper.updateVisApp(model.getAddressId());
	}
	@Override
	public void appointVisitorBatch(List<VisitRecordModel> list) {
		for (VisitRecordModel model : list) {
			if(StringUtils.isNotBlank(model.getVisitUserId())){
				model.setVisitUserId(","+model.getVisitUserId()+",");
			}
			visitRecordMapper.update(model);
			addressMapper.updateVisApp(model.getAddressId());
			// 保存操作记录 bianbianbian
			String content ="外访员:"+model.getVisitUser();
			hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.oVis, model.getCaseId(),
					"外访排程", content, model.getId(), HurryRecordConst.noaction));
			
		}
	}

	/**
	 * 撤销外访
	 * 
	 * @return
	 */
	@Override
	public void cancelVisit(String[] ids, String approveOpinion) {
		for (String id : ids) {
			VisitRecordModel visitModel = visitRecordMapper.findById(id);
			visitModel.setState(1);
			visitModel.setModifyTime(new Date());
			visitModel.setApproveOpinion(approveOpinion);
			visitRecordMapper.update(visitModel);
			addressMapper.resetAddressVisApp(Lists.newArrayList(visitModel.getAddressId()));
			
			// 保存操作记录 bianbianbian
			String content ="撤销意见:"+approveOpinion;
			hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.oVis, visitModel.getCaseId(),
					"撤销外访", content, visitModel.getId(), HurryRecordConst.noaction));
			
			
		}
	}

	@Override
	public void backToAppoint(String[] id) {
		List<VisitRecordModel> visitModel = visitRecordMapper.selectIds(Arrays.asList(id));
		if(visitModel!=null){
			for (VisitRecordModel vm : visitModel) {
				vm.setState(2);
				vm.setModifyTime(new Date());
				vm.setVisitUser(null);
				vm.setVisitUserId(null);
				visitRecordMapper.update(vm);
				/*addressMapper.resetAddressVisApp(Lists.newArrayList(vm.getAddressId()));*/
				String content ="";
				hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.oVis, vm.getCaseId(),
									"返回排程", content, vm.getId(), HurryRecordConst.noaction));
			}
		}
	}

	@Override
	public void finishVisit(VisitRecordModel model) {

		VisitRecordModel visitModel = visitRecordMapper.findById(model.getId());
		visitModel.setActualVisitTime(model.getActualVisitTime());
		visitModel.setIsEffective(model.getIsEffective());
		visitModel.setVisitReport(model.getVisitReport());
		visitModel.setState(4);
		visitModel.setVisitState(model.getVisitState());
		visitModel.setModifyTime(new Date());
		visitRecordMapper.update(visitModel);
		addressMapper.resetAddressVisApp(Lists.newArrayList(visitModel.getAddressId()));
		String caseId = visitModel.getCaseId();
		// 完成外访时保存催记
		savePhoneRecord(visitModel, caseId);
		
		// 保存操作记录 bianbianbian
		String content ="外访报告:"+model.getVisitReport();
		hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.oVis, visitModel.getCaseId(),
				"完成外访", content, visitModel.getId(), HurryRecordConst.noaction));
	}

	@Override
	public List<VisitShowView> findVisitRecordIds(List<String> visitRecordIds) {
		// 处理batchIds ，batchIds是数组 .xml里需要他做查询条件
		Map<String, Object> params = new HashMap<>();
		params.put("visitRecordIds", visitRecordIds);
		return visitRecordMapper.findVisitRecordIds(params);
	}

	@Override
	public List<VisitShowView> queryAll(ParamCondition condition) {
		// 已排程和已完成外访：催收员和外访员可以看见外访员是自己的记录, 其他角色可以可见所有的

		if (this.isCollectorOrVisitor()) {
			condition.put("visitUserId", SecurityUtil.getCurrentUser().getId());
		}
		condition.put("state", 3);
		return visitRecordMapper.queryAll(condition);
	}

	@Override
	public SearchResult<VisitRecordVo> queryVisitRecordVoForPage(ParamCondition condition) {
		List<VisitRecordVo> list = visitRecordMapper.queryVisitRecordVo(condition);
		for (int i = 0; i < list.size(); i++) {
			if(StringUtils.isNotBlank(list.get(i).getInfoId())){
				String visitIPhone=list.get(i).getInfoId();
				if (visitIPhone.contains(",")) {
					String[] ary = visitIPhone.split(",");
					StringBuffer testStrBuff=new StringBuffer("");
					for (int j = 0; j < ary.length; j++) {
						EmployeeInfoModel model= employeeInfoService.findById(ary[j]);
						if(model!=null){
							String contactMode = model.getContactMode()==null?"":model.getContactMode();
							if(!"".equals(contactMode)){
								testStrBuff.append(contactMode);
								testStrBuff.append(",");
							}
							
						}
						
					}
					if(StringUtils.isNotBlank(testStrBuff.toString())){
						list.get(i).setContactMode(testStrBuff.toString().substring(0, testStrBuff.toString().length()-1));
					}
				}
			}
		}
		SearchResult<VisitRecordVo> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	private void savePhoneRecord(VisitRecordModel visitModel, String caseId) {
		PhoneRecordModel phoneRecordModel = new PhoneRecordModel();
		CaseModel caseModel = caseMapper.findById(caseId);
		phoneRecordModel.setCaseId(visitModel.getCaseId());
		phoneRecordModel.setCollecStateId(caseModel.getCollecStateId());
		phoneRecordModel.setName(visitModel.getName());
		phoneRecordModel.setCreateTime(new Date());
		phoneRecordModel.setId(UUIDUtil.UUID32());
		phoneRecordModel.setPrCat("1");
		phoneRecordModel.setContact(visitModel.getVisitAddress());
		phoneRecordModel.setContent(visitModel.getVisitReport());
		phoneRecordModel.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
		phoneRecordModel.setCreateTime(new Date());
		// 数据导入问题
		AddressModel addressModel = addressMapper.findById(visitModel.getAddressId());
		if (addressModel != null) {
			phoneRecordModel.setRelation(addressModel.getRelation());
		}
		phoneRecordMapper.save(phoneRecordModel);
	}

	@Override
	public List<VisitRecordDto> seletAll(ParamCondition condition) {
		// 已排程和已完成外访：催收员和外访员可以看见外访员是自己的记录, 其他角色可以可见所有的
		if (this.isCollectorOrVisitor()) {
			condition.put("visitUserId", SecurityUtil.getCurrentUser().getId());
		}
		
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<VisitRecordDto> list = visitRecordMapper.queryAppointedORFinished(condition);
		return list;
	}

	@Override
	public List<VisitRecordDto> selectByIds(List<String> ids) {
		return visitRecordMapper.selectByIds(ids);
	}

	public boolean isCollectorOrVisitor() {
		return SecurityUtil.hasRole("csy") || SecurityUtil.hasRole("wfy");
	}

	@Override
	public int queryTomorrowVisitCount() {
		String userId = SecurityUtil.getCurrentUser().getId();
		Date tomorrow = DateUtil.addDays(new Date(), 1);
		int count= visitRecordMapper.visitCount(userId, DateUtil.date2Str(tomorrow, null));
		return count;
	}
	
	@Override
	public int queryTodayVisitCount() {
		String userId = SecurityUtil.getCurrentUser().getId();
		int count= visitRecordMapper.visitCount(userId, DateUtil.date2Str(new Date(), null));
		return count;
	}
	

	@Override
	public List<VisitRecordDto> queryCaseToUploadReport() {
		String userId = SecurityUtil.getCurrentUser().getId();
		Date tomorrow = DateUtil.addDays(new Date(), -1);
		List<VisitRecordDto>  list=visitRecordMapper.caseToUploadReport(userId, DateUtil.date2Str(tomorrow, null));
		return list;
	}

	@Override
	public int queryToApproveORToAppointCount(int state) {
		List<String> area = WEBUtil.getSessionAttribute("FGSArea");
		return visitRecordMapper.queryToApproveORToAppointCount(area,  state);
	}
	
	@Override
	public VisitRecordModel findByAddresId(String id){
		return visitRecordMapper.findByAddresId(id);
	}

}
