package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.CollectionConst;
import com.kangde.collection.dto.CaseCollectViewDto;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.service.CaseCollectService;
import com.kangde.collection.service.CaseService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.OrganizationService;

/**
 * 
 * @Description: 案件风控
 * @author lidengwen
 * @date 2016年6月23日 下午1:43:31
 *
 */
@Service("caseCollectService")
public class CaseCollectServiceImpl extends AbstractService<CaseCollectViewDto, String> implements CaseCollectService {
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CaseStatisticsService caseStatisticsService;
	@Autowired
	private CaseService caseService;

	@Override
	public SearchResult<CaseCollectViewDto> queryForPage(ParamCondition condition) {
		//拼接字符串，拼接成 符合sql 的字符串  
		String caseCodeAll=condition.get("caseCode");
		if(StringUtils.isNotBlank(caseCodeAll)){
			   if(caseCodeAll.contains(",")){
				   String[] ary = caseCodeAll.split(",");
				   StringBuffer testStrBuff=new StringBuffer("(");
					  for (int i = 0; i < ary.length; i++) {
						  testStrBuff.append("'"+ary[i]+"',");
					}
					String CodeAll=testStrBuff.substring(0,testStrBuff.length()-1);
					CodeAll=CodeAll+")";
					//拼接后的结果（'12312312','321312321'）
					condition.put("CodeAll", CodeAll);
			   }else{
				   String caseCodeOne=condition.get("caseCode");
				   condition.put("caseCodeOne", caseCodeOne);
			   }
		}
		StringBuilder sql = getSql(condition);
		condition.put("dynamicSql", sql.toString());

		List<CaseCollectViewDto> list = caseMapper.queryCaseCollect(condition);
		SearchResult<CaseCollectViewDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 生成案件复杂的查询语句
	 * 
	 * @param condition
	 *            检索条件
	 * @return SQL语句
	 */
	private StringBuilder getSql(ParamCondition condition) {
		
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
		// assigned个人 department部门
		String scope = condition.get("scope");
		// 案件ID,作为其他业务查询条件,与列表条件无关
		List<String> caseIds = condition.get("caseIds");
		// 姓名
		String caseName = condition.get("caseName");
		// 证件号
		String caseNum = condition.get("caseNum");
		// 风控区域
		String areaName = condition.get("areaName");
		// 手机号
		String phone = condition.get("phone");
		// 案件状态
		String casestatus = condition.get("casestatus");
		// 案件状态
		String casestatus1 = condition.get("casestatus1");
		// 留案状态
		String approveState = condition.get("approveState");
		// 外访审批状态
		String visitState = condition.get("visitState");
		// 委托方
		String entrustId = condition.get("entrustId");
		// 批次号
		String batchCode = condition.get("batchCode");
		// 风控状态
		String collecStateId = condition.get("collecStateId");
		// 逾期账龄
		String overdueAge = condition.get("overdueAge");
		// 案件类型
		String caseType = condition.get("caseType");
		// 委托开始日期,委托结束日期
		String entrustDateStart = condition.get("entrustDateStart");
		String entrustDateEnd = condition.get("entrustDateEnd");
		
		String caseDate = condition.get("caseDate");
		String caseDate1 = condition.get("caseDate1");
		

		// department begin
		// 风控方
		String orgId = condition.get("orgId");
		// 风控员
		String caseAssignedId = condition.get("caseAssignedId");
		// 退案日期
		String withDrawBeginDate = condition.get("withDrawBeginDate");
		String withDrawEndDate = condition.get("withDrawEndDate");
		
		Integer caseState=condition.get("caseState");
	
		
		// 委托开始日期,委托结束日期
		String endDate = condition.get("endDate");
		String endDate1 = condition.get("endDate1");
		
		// 委托开始日期,委托结束日期
		String caseBackdate = condition.get("caseBackdate");
		String caseBackdate1 = condition.get("caseBackdate1");
		
		//字体颜色
		String color = condition.get("color");
				
		// department end
		// 开始构建查询语句
		StringBuilder sql = new StringBuilder("select ");
				if (StringUtils.isNotBlank(phone)) {
					sql.append(" distinct ");
					
				}
				// 案件表所有信息
				sql.append("cinfo.*,cinfo.last_phone_time as lastPhone, ")
				//.append("(SELECT max(create_time) from phone_record where case_id=cinfo.id and pr_cat=0) as lastPhone,")
				// 批次号
				.append("cbatch.batch_code,")
				// 风控人
				.append("caseAssigned.user_name as caseAssignedName,")
				// 委托方名称
				.append("entrust.name as entrustName,")
				// 剩余还款
				.append("IF((IFNULL(cinfo.case_money,0) - IFNULL(tb.paid_num,0)- IFNULL(cinfo.out_derate,0))<0,0, IFNULL(cinfo.case_money,0) - IFNULL(tb.paid_num,0)- IFNULL(cinfo.out_derate,0)) as surplusMoney,")
				.append("cbatch.begin_date as beginDate, cbatch.end_date as endDate,")
				// PTP承诺还款金额
				.append("ifnull(tb.ptp_money,0) as ptp_money,")
				// CP待确认还款金额
				.append("ifnull(tb.cp_money, 0) as cp_money,")
				// 已确认还款金额
				.append("ifnull(tb.paid_num, 0) as paid_num ").append("from case_info as cinfo ");
		// 加入批次表
		sql.append("join case_batch as cbatch on cinfo.batch_id = cbatch.id ");
		// 加入风控员信息
		sql.append("left join employee_info as caseAssigned on cinfo.case_assigned_id = caseAssigned.id ");
		// 加入委托方表
		sql.append("left join entrust on cbatch.entrust_id = entrust.id ");

		if (StringUtils.isNotBlank(phone)) {
			sql.append(" INNER JOIN case_linkman linkman on cinfo.id=linkman.case_id ");
		}
		
		// 加入临时表1,查询-- PTP承诺还款金额,CP待确认还款金额,已确认还款金额
		sql.append(
				"left join (SELECT case_id,sum(if(state=2, IFNULL(paid_num,0), 0)) as paid_num, sum(if(state=1, IFNULL(cp_money,0), 0)) as cp_money, sum(if(state=0, IFNULL(ptp_money,0), 0)) as ptp_money 	from case_paid GROUP BY case_id ) as tb on tb.case_id = cinfo.id ");
		if (CollectionUtils.isNotEmpty(caseIds)) {
			StringBuilder temp = new StringBuilder(" and cinfo.id in (");
			for (String caseId : caseIds) {
				temp.append("'" + caseId + "',");
			}
			temp.delete(temp.length() - 1, temp.length());
			temp.append(") ");
			sql.append(temp);
		}

		sql.append(" where 1=1 ");
		sql.append(" and cinfo.STATUS = 1 ");
		
		//案件标色
		if (StringUtils.isNotBlank(color)) {
			sql.append(" and cinfo.color='" + color + "'");
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
	
				
		// 案件状态 --- 风控管理， 可见的案件状态有三种：正常：0 暂停：1 结清：4
		if (StringUtils.isNotBlank(casestatus)) {
			String string = " and cinfo.state=" + Integer.parseInt(casestatus);
			sql.append(string);
		}

		/*sql.append(" and cinfo.state in(0,1,4)");*/
		
		
		if (null==caseState) {
			sql.append(" and cinfo.state in(0,1,4)");
		}
		if(null!=caseState){
			sql.append(" and cinfo.state in(0,1,4,3)");
		}

		// 姓名 ---
		if (StringUtils.isNotBlank(caseName)) {
			sql.append("and cinfo.case_name like '%" + caseName + "%'");
		}
		// 证件号 ---
		if (StringUtils.isNotBlank(caseNum)) {
			sql.append("and cinfo.case_num like '%" + caseNum + "%'");
		}
		// 案件地区---
		if (StringUtils.isNotBlank(areaName)) {
			sql.append("and cinfo.area_name like '%" + areaName + "%'");
		}
		// 手机号 ---
//		if (StringUtils.isNotBlank(phone)) {
//			sql.append("and (cinfo.mobile1 like '%" + phone + "%' or cinfo.mobile2 like '" + phone + "%')");
//		}
		
	
		if (StringUtils.isNotBlank(phone)) {
			sql.append(" and linkman.phone='"+phone+"'");
			
		}

		// 还款情况
		String circumstance = condition.get("circumstance");

		// 留案审批状态 --
		if (StringUtils.isNotBlank(approveState)) {

			String approveStateSql = "SELECT\n" + "	approve_record.case_id\n" + "FROM\n" + "	approve_record,\n"
					+ "	(\n" + "		SELECT\n" + "			case_id,\n" + "			max(modify_time) modify_time\n"
					+ "		FROM\n" + "			approve_record\n" + "		GROUP BY\n" + "			case_id\n"
					+ "	) A\n" + "where approve_record.approve_state=" + approveState
					+ " and approve_record.case_id=A.case_id and approve_record.modify_time=A.modify_time";

			sql.append("and cinfo.id in (" + approveStateSql + ")");
		}
		// 外访审批状态
		if (StringUtils.isNotBlank(visitState)) {
			String visitStateSql = "SELECT visit_record.case_id FROM visit_record,(SELECT case_id, max(modify_time) modify_time FROM visit_record GROUP BY case_id) B	WHERE	visit_record.approve_state = "
					+ visitState
					+ " AND visit_record.case_id = B.case_id	AND visit_record.modify_time = B.modify_time";
			sql.append("and cinfo.id in (" + visitStateSql + ")");
		}

		// 委托方---
		if (StringUtils.isNotBlank(entrustId)) {
			sql.append(" and cbatch.entrust_id in (" + SQLUtil.warpSql(entrustId) + ") ");
		}
		// 批次号
		if (StringUtils.isNotBlank(batchCode)) {
			sql.append(" and cbatch.batch_code in (" + SQLUtil.warpSql(batchCode) + ") ");
		}
		// 风控状态 ---
		if (StringUtils.isNotBlank(collecStateId)) {
			sql.append("and cinfo.collec_state_id = '" + collecStateId + "' ");
		}
		// 逾期账龄 ---
		if (StringUtils.isNotBlank(overdueAge)) {
			sql.append("and cinfo.overdue_age = '" + overdueAge + "' ");
		}
		// 案件类型 ---
		if (StringUtils.isNotBlank(caseType)) {
			sql.append("and cbatch.type_id in (" + SQLUtil.warpSql(caseType) + ") ");
		}

		// 还款情况
		if (StringUtils.isNotBlank(circumstance)) {
			// 未还款
			if (CollectionConst.NOT_REPAY.equals(circumstance)) {
				sql.append("  and ifnull(tb.paid_num, 0)=0 ");
			}
			// 已还款
			else if (CollectionConst.PART_REPAY.equals(circumstance)) {
				sql.append(" and ifnull(tb.paid_num, 0)>0  ");//
			}
		}

		// 委案日期
		if (StringUtils.isNotBlank(caseDate) && StringUtils.isNotBlank(caseDate1)) {
			sql.append("and (DATE_FORMAT(cinfo.case_date,'%Y-%m-%d') between '" + caseDate + "' and '" + caseDate1
					+ "') ");
		}

		// 风控方
		if ("department".equals(scope)) {
			if (StringUtils.isNotBlank(orgId)) {
				sql.append("and cinfo.org_id in (");
				String orgPath = organizationService.findById(orgId).getPath();
				sql.append("select id from sys_organization where path like '" + orgPath + "%'");
				sql.append(")");
			}

			if (StringUtils.isNotBlank(withDrawBeginDate) && StringUtils.isNotBlank(withDrawEndDate)) {
				sql.append("and  (DATE_FORMAT(cbatch.end_date,'%Y-%m-%d') between '" + withDrawBeginDate
						+ "' and '" + withDrawEndDate + "') ");
			}

			// 风控员
			if (StringUtils.isNotBlank(caseAssignedId)) {
				sql.append("and cinfo.case_assigned_id='" + caseAssignedId + "'");
			}

			String currentUserOrgId = SecurityUtil.getCurrentUser().getOrgId();
			if(StringUtils.isNotBlank(currentUserOrgId)){
				String orgPath = organizationService.findById(currentUserOrgId).getPath();// 机构路径
				sql.append(
						"and cinfo.case_assigned_id in (SELECT	id	FROM	employee_info WHERE	org_id IN (	SELECT	id	FROM sys_organization	WHERE path LIKE '"
								+ orgPath + "%'))");
			}

		} else if ("assigned".equals(scope)) {
			sql.append(" and cinfo.case_assigned_id='" + SecurityUtil.getCurrentUser().getId() + "' ");
		}
		
		// 退案日期
				if (StringUtils.isNotBlank(caseBackdate) && StringUtils.isNotBlank(caseBackdate1)) {
					sql.append(" and (DATE_FORMAT(cinfo.case_backdate,'%Y-%m-%d') between '" + caseBackdate + "' and '"
								+ caseBackdate1 + "' )");
						}

		// 排序处理
		if (condition.hasOrder()) {
			sql.append("order by " + condition.getSort() + " " + condition.getOrder());
		} else {
			sql.append(" order by cinfo.create_time desc");
		}

		return sql;
	}

	@Override
	public void changeState(Integer state, List<String> caseIds) {
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
	public CaseCollectViewDto findById(String id) {
		return null;
	}
}
