package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.CasePaidDto;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.CasePaidMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CasePaidService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.OrganizationService;


@Service
public class CasePaidServiceImpl extends AbstractService<CasePaid,String> implements CasePaidService {

	
	
	@Autowired
	private CasePaidMapper casePaidMapper;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CasePaidStatisticsService caseStatisticsService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseBatchService caseBatchService;
	
	@Override
	public CasePaid save(CasePaid model) {
		model.setCreateTime(new Date());//创建时间
		CaseModel caseModel = caseMapper.findById(model.getCaseId());
		String assignedId = caseModel.getCaseAssignedId();
		if(assignedId==null){
			throw new ServiceException("该案件未分配，请分配风控员!");
		}else{
			model.setSeNo(assignedId);// 风控人ID
		}
		
		String userId = SecurityUtil.getCurrentUser().getId();
		model.setCreateEmpId(userId);
		if(model.getStatus() == null){
			model.setStatus(CoreConst.STATUS_NORMAL);//系统状态,默认正常
		}
		if(model.getState() == null){
			model.setState(1);//系统状态,默认正常
		}
		casePaidMapper.save(model);
		//保存操作记录  bianbianbian
		String content="[还款金额："+model.getCpMoney()+", 还款时间:"+DateFormatUtils.format(model.getCpTime(), "yyyy-MM-dd")+"]";
		hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.CP, model.getCaseId(), "CP", content, model.getId(), HurryRecordConst.add));
		return model;
	}
	
	@Override
	public CasePaid savezlz(CasePaid model) {
		model.setCreateTime(new Date());//创建时间
		model.setSurTime(new Date());//确认日期
		String userId = SecurityUtil.getCurrentUser().getId();
		
		CaseModel caseModel = caseMapper.findById(model.getCaseId());
		String assignedId = caseModel.getCaseAssignedId();
		if(assignedId==null){
			throw new ServiceException("该案件未分配，请分配风控员!");
		}else{
			model.setSeNo(assignedId);// 风控人ID
		}
		
		model.setOperateEmpId(userId);
		if(model.getStatus() == null){
			model.setStatus(CoreConst.STATUS_NORMAL);//系统状态,默认正常
		}
		if(model.getState() == null){
			model.setState(1);//系统状态,默认正常
		}
		casePaidMapper.save(model);
		return model;
	}
	/**
	 * 显示还款
	 */
	@Override
	public SearchResult<CasePaidDto> queryPaid(ParamCondition condition) {
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<CasePaidDto> list =casePaidMapper.queryPaid(condition);
		SearchResult<CasePaidDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	@Override
	public List<CasePaidDto> queryPaidall(ParamCondition condition) {
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<CasePaidDto> list =casePaidMapper.queryPaid(condition);
		return list;
	}

	
	/**
	 * 显示还款
	 */
	@Override
	public List<CasePaidDto> queryAll(ParamCondition condition) {
		String getid = condition.get("caseAssignedId");
		String attachEntrustId = SecurityUtil.getCurrentUser().getAttachEntrustId();
		if(StringUtils.isNotBlank(attachEntrustId)){
			condition.put("attachEntrustId", SQLUtil.warpSql(attachEntrustId));
		}
		
		if (StringUtils.isNotBlank(getid)) {
			String caseAssignedIds = SQLUtil.warpSql(getid);
			condition.put("caseAssignedIds", caseAssignedIds);
		}
		
		return casePaidMapper.queryPaid(condition);
	}
	
	/**
	 * 更新状态
	 */
	@Override
	public int updateForState(CasePaid model) {
		int t = casePaidMapper.updateForState(model);
		
		// 操作记录
		CaseModel caseModel = caseMapper.findById(model.getCaseId());
		if (2 == model.getState()) {
			String content = "[确认还款]:案件序列号"+caseModel.getCaseCode()+",确认还款"+model.getPaidNum()+"元。";
			HurryRecordModel hr=OperationUtil.Operation("CP",model.getCaseId(),content,model.getId());
			hurryRecordMapper.save(hr);
		} else if (4 == model.getState()) {
			String content = "[作废还款]:案件序列号"+caseModel.getCaseCode()+",作废原因："+model.getCancelReason()+"。";
			HurryRecordModel hr=OperationUtil.Operation("CP",model.getCaseId(),content,model.getId());
			hurryRecordMapper.save(hr);
		} else if (1==model.getState()){//如果为cp正常的，添加操作记录。
			String content = "[作废还款]:案件序列号"+caseModel.getCaseCode()+",作废原因："+model.getCancelReason()+"。";
			HurryRecordModel hr=OperationUtil.Operation("CP",model.getCaseId(),content,model.getId());
			hurryRecordMapper.save(hr);
		}
		
		return t;
	}
	
	@Override
	@Transactional
	public void toCP(String id) {
		CasePaid casePaid = casePaidMapper.findById(id);
		casePaid.setState(1);
		casePaid.setModifyEmpId(SecurityUtil.getCurrentUser().getId());
		casePaid.setModifyTime(new Date());
		casePaid.setCpMoney(casePaid.getPtpMoney());
		casePaid.setCpTime(casePaid.getPtpTime());
		
		String content="[还款金额："+casePaid.getPtpMoney()+", 还款时间:"+DateFormatUtils.format(casePaid.getPtpTime(), "yyyy-MM-dd")+"]";
		hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.CP, casePaid.getCaseId(), "转CP", content, id, HurryRecordConst.noaction));
		
		
		casePaidMapper.update(casePaid);

	}
	@Override
	public SearchResult<CasePaid> queryPTPorCPForPage(ParamCondition condition) {
		List<CasePaid> list = casePaidMapper.queryPTPorCPForPage(condition);
		SearchResult<CasePaid> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public List<CasePaidDto> queryForIds(ParamCondition condition) {
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());
		List<CasePaidDto> list =casePaidMapper.queryPaid(condition);
		return list;
	}
	

	@Override
	public Map<String, Object> queryStatistics(ParamCondition condition) {
		condition.clearPager();
		return caseStatisticsService.queryStatistics(getSql(condition).toString());
	}
	/**
	 * 生成案件复杂的查询语句
	 * 
	 * @param condition
	 *            检索条件
	 * @return SQL语句
	 */
	private StringBuilder getSql(ParamCondition condition) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String content = condition.get("content");//内容
		String CodeAll=null;//多个案件序列号
		String CodeOne=null;//单个案件序列号
		String caseFileNoAll=null;//多个档案号
		String caseFileNoOne=null;//单个档案号
		String caseNumAll=null;//多个档案号
		String caseNumOne=null;//单个档案号
		String caseCardAll=null;//多个卡号
		String caseCardOne=null;//单个卡号
		String decompose=caseService.decompose(content);
		
		String selectNum= condition.get("selectByNum");//选中的类型
		if(StringUtils.isNotBlank(selectNum)){
			if("1".equals(selectNum)){
				if(StringUtils.isNotBlank(decompose)){
				if(decompose.contains(",")){
					CodeAll=decompose;
				}
				}else{
					CodeOne=condition.get("content");
				}
			}else if("2".equals(selectNum)){
				if(StringUtils.isNotBlank(decompose)){
					if(decompose.contains(",")){
						caseFileNoAll=decompose;
					}
					}else{
						caseFileNoOne=condition.get("content");
					}
			}else if("3".equals(selectNum)){
				if(StringUtils.isNotBlank(decompose)){
					if(decompose.contains(",")){
						caseNumAll=decompose;
					}
					}else{
						caseNumOne=condition.get("content");
					}
			}else if("4".equals(selectNum)){
				if(StringUtils.isNotBlank(decompose)){
					if(decompose.contains(",")){
						caseCardAll=decompose;
					}
					}else{
						caseCardOne=condition.get("content");
					}
			}
		}
		
		String code=condition.get("batchCode");
		String batchCode=caseBatchService.BatchCode(code);
		
		
		// 单个案件序列号
		String caseCode = condition.get("caseCode");
		// 风控方
		String orgId = condition.get("orgId");
		// 风控员
		String caseAssignedId = condition.get("caseAssignedId");//se_no
		// 姓名
		String caseName = condition.get("caseName");
		// 证件号
		String caseNum = condition.get("caseNum");
		// 手机号
		String mobile1 = condition.get("mobile1");
		// 卡号
		String caseCard = condition.get("caseCard");
		// 案件状态
		String casestatus = condition.get("casestatus");
		// 案件状态1
		String casestatus1 = condition.get("casestatus1");
		// 委托方
		String entrustId = condition.get("entrustId");
		// 日期
		String paidTime = condition.get("paidTime");
		String paidTime1 = condition.get("paidTime1");
		// 
		String surTime = condition.get("surTime");
		String surTime1 = condition.get("surTime1");
		
		String state = condition.get("state");

		// 委托开始日期,委托结束日期
		String cpTime = condition.get("cpTime");
		String cpTime1 = condition.get("cpTime1");
		
		
		String caseIdMore = condition.get("caseIdMore");
		
		// 开始构建查询语句
		StringBuilder sql = new StringBuilder("select ")
				.append("cp.id, cp.back_paid_rate, cp.case_id, cp.paid_time, cp.entrust_paid_rate, cp.entrust_paid, cp.sur_time, cp.cancel_reason, ci.entrust_rate, ci.case_code, ci.case_name,")
				.append("ci.case_num, ci.case_card, ci.agent_rate, ci.mobile1, ci.case_money, ci.case_assigned_id, ci.in_derate, ci.out_derate,")
				.append("ci.case_file_no, cb.batch_code, en.name AS entrustName, cp.in_derate AS inDerateNew,")
				.append("cp.out_derate AS outDerateNew, cb.entrust_id, cp.paid_num, cp.cp_money, cp.is_derate,cp.state AS cpState,")
				.append("cb.achieve, cp.cp_time, cp.state, cp.se_no, ei.user_name AS cpName, em.user_name AS operateName,cp.operate_time, so.name AS SONAME,")
				.append("ei.org_id, cp.sur_remark, vr.actual_visit_time, vr.visit_user, ar.name AS areaName,")
				.append("CASE WHEN ( ci.case_money - (SELECT IFNULL(SUM(paid_num), 0) FROM case_paid WHERE case_id = cp.case_id AND state = '2')) < 0 THEN 0 ")
				.append("ELSE ( ci.case_money - (SELECT IFNULL(SUM(paid_num), 0) FROM case_paid WHERE case_id = cp.case_id AND state = '2')) END AS surplusMoney,")
				.append("cp.entrust_paid AS compBorker,")
				.append("cp.back_paid AS commission, ci.color,")
				.append("(SELECT COUNT(id) FROM attachment att WHERE att.fk_id = cp.id) AS fzsize,")
				.append("cp.repay_type, ci.account_no, ci.loan_contract, ci.cus_no, ci.currency, ci.overdue_days, ci.overdue_age, ci.end_date, ci.remark1, ci.sales_dep,ci.case_app_no,ci.case_date,ci.case_backdate ")
				.append("FROM case_paid cp ");
		sql.append("INNER JOIN case_info ci ON cp.case_id = ci.id ");
		sql.append("LEFT JOIN case_batch cb ON ci.batch_id = cb.id ");
		sql.append("LEFT JOIN entrust en ON cb.entrust_id = en.id ");
		sql.append("LEFT JOIN employee_info ei ON cp.se_no = ei.id ");
		sql.append("LEFT JOIN employee_info em ON cp.operate_emp_id = em.id ");
		sql.append("LEFT JOIN sys_organization so ON ei.org_id = so.id ");
		sql.append("LEFT JOIN AREA ar ON ci.area_id = ar.id ");
		sql.append("LEFT JOIN (SELECT * FROM (SELECT actual_visit_time, visit_user, case_id FROM visit_record WHERE state = '4' ORDER BY actual_visit_time DESC) temp GROUP BY case_id ORDER BY actual_visit_time DESC) vr ON vr.case_id = cp.case_id ");
		sql.append("WHERE cp.state != 0 and ci.status != -1 and cb.status != -1 ");

		
		String attachOrgId = currentUser.getAttachOrgId();
		String queryOrgs=currentUser.getOrgId()+",";
		if(StringUtils.isNotBlank(attachOrgId)){
			queryOrgs=queryOrgs+attachOrgId;
		}
		sql.append(" and ( ");
		sql.append(" ei.org_id in ("+SQLUtil.warpSql(queryOrgs)+") ");
		String attachEntrustId = currentUser.getAttachEntrustId();
		if(StringUtils.isNotBlank(attachEntrustId)){
			sql.append(" or cb.entrust_id in (" + SQLUtil.warpSql(attachEntrustId) + ") ");
		}
		if("admin".equals(currentUser.getLoginName())){
			sql.append(" or 1=1 ");
		}
		sql.append(")");
		
		
		//ids所选id
		if (StringUtils.isNotBlank(caseIdMore)) {
			sql.append(" and cp.id in (" + SQLUtil.warpSql(caseIdMore) + ") ");
		}
		if (StringUtils.isNotBlank(entrustId)) {
			sql.append(" and entrust_id in (" + SQLUtil.warpSql(entrustId) + ") ");
		}
		
		// 风控方
		if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and ei.org_id in (");
			String orgPath = organizationService.findById(orgId).getPath();// 机构路径
			sql.append("select id from sys_organization where path like '" + orgPath + "%'");
			sql.append(")");
		}
		// 风控员
		if (StringUtils.isNotBlank(caseAssignedId)) {
			sql.append(" and cp.se_no in (" + SQLUtil.warpSql(caseAssignedId) + ")");
		}
		// 案件序列号
		if (StringUtils.isNotBlank(caseCode)) {
			sql.append(" and case_code like '" + caseCode + "%'");
		}
		// 还款状态
		if (StringUtils.isNotBlank(state)) {
			sql.append(" and cp.state like '" + state + "%'");
		}
		// 姓名
		if (StringUtils.isNotBlank(caseName)) {
			sql.append(" and case_name like '" + caseName + "%'");
		}
		// 证件号
		if (StringUtils.isNotBlank(caseNum)) {
			sql.append(" and case_num like '" + caseNum + "%'");
		}
		// 手机号
		if (StringUtils.isNotBlank(mobile1)) {
			sql.append(" and mobile1 like'" + mobile1 + "%'");
		}
		// 卡号
		if (StringUtils.isNotBlank(caseCard)) {
			sql.append(" and case_card like '" + caseCard + "%'");
		}
		// 案件状态
		if (StringUtils.isNotBlank(casestatus)) {
			sql.append(" and ci.state = " + casestatus);
		} else {
			// sql.append(" and cinfo.state != 3 ");
		}
		// 案件状态1
		if (StringUtils.isNotBlank(casestatus1)) {
			sql.append(" and ci.state = " + casestatus1);
		} else {
			// sql.append(" and cinfo.state != 3 ");
		}
		
		// 批次号
		if (StringUtils.isNotBlank(batchCode)) {
			sql.append(" and cb.id in " + batchCode);
		}
		
		if (StringUtils.isNotBlank(paidTime) && StringUtils.isNotBlank(paidTime1)) {
			sql.append(" and (DATE_FORMAT(cp.`paid_time`,'%Y-%m-%d') between '" + paidTime + "' and '"
						+ paidTime1 + "' )");
				}
		if (StringUtils.isNotBlank(surTime) && StringUtils.isNotBlank(surTime1)) {
			sql.append(" and (DATE_FORMAT(cp.`sur_time`,'%Y-%m-%d') between '" + surTime + "' and '"
					+ surTime1 + "' )");
		}
		if (StringUtils.isNotBlank(cpTime) && StringUtils.isNotBlank(cpTime1)) {
			sql.append(" and (DATE_FORMAT(cp_time,'%Y-%m-%d') between '" + cpTime + "' and '"
					+ cpTime1 + "' )");
		}
		
		
		if (StringUtils.isNotBlank(CodeAll)) {
			sql.append(" and ci.case_code in " + CodeAll + "");
		}
		if (StringUtils.isNotBlank(CodeOne)) {
			sql.append(" and ci.case_code = '" + CodeOne + "'");
		}
		
		if (StringUtils.isNotBlank(caseFileNoAll)) {
			sql.append(" and ci.case_file_no in " + caseFileNoAll + "");
		}
		if (StringUtils.isNotBlank(caseFileNoOne)) {
			sql.append(" and ci.case_file_no = '" + caseFileNoOne + "'");
		}
		
		if (StringUtils.isNotBlank(caseNumAll)) {
			sql.append(" and ci.case_num in " + caseNumAll + "");
		}
		if (StringUtils.isNotBlank(caseNumOne)) {
			sql.append(" and ci.case_num = '" + caseNumOne + "'");
		}
		
		if (StringUtils.isNotBlank(caseCardAll)) {
			sql.append(" and ci.case_card in " + caseCardAll + "");
		}
		if (StringUtils.isNotBlank(caseCardOne)) {
			sql.append(" and ci.case_card = '" + caseCardOne + "'");
		}
	
		// 排序处理
		if (condition.hasOrder()) {
			sql.append(" order by " + condition.getSort() + " " + condition.getOrder());
		} else {
			sql.append(" ORDER BY cp.state, cp.create_time DESC, cp_time DESC  ");
		}
		return sql;
	}

	@Override
	public int queryToConfirmPayCount() {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachEntrustId = currentUser.getAttachEntrustId();
		String attach="";
		if(StringUtils.isNotBlank(attachEntrustId)){
			attach = SQLUtil.warpSql(attachEntrustId);
		}
		String orgPath = organizationService.findById(currentUser.getOrgId()).getPath();// 机构路径
		return casePaidMapper.toConfirmPayCount(attach,SQLUtil.warpSql(getQueryOrgs()), orgPath, currentUser.getLoginName());
		
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
}
