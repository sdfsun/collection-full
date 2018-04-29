package com.kangde.collection.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.mapper.CaseApprovalMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.CaseApprovalService;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.UUIDUtil;
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
@Service("caseApprovalService")
public class CaseApprovalServiceImpl extends AbstractService<CaseDto, String> implements CaseApprovalService {

	@Autowired
	private CaseApprovalMapper caseApprovalMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CaseMapper caseMapper;

	@Override
	public SearchResult<CaseDto> queryforLeave(ParamCondition condition) {
		// 需要算出剩余金额，
		// 公式： 委案金额减去已还款
		List<CaseDto> list = caseApprovalMapper.queryforLeave(condition);
		double SumMoney = 0;
		if (0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				CaseDto dto = list.get(i);
				double caseMoney = dto.getCaseMoney().doubleValue();// 得到每一条数据的委案金额
				try { // 如果没有已还款， 那么已还款就是null， 会报空指针， 所以有异常不处理。
					SumMoney = dto.getCasepaidmodel().getSumMoney();// 得到已还款。有值就显示，没值报异常不处理。
				} catch (Exception e) {
				}
				double remainMoney = caseMoney - SumMoney;// 得出剩余金额
				dto.setRemainMoney(remainMoney);
			}
		}
		SearchResult<CaseDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public SearchResult<CaseDto> queryforGo(ParamCondition condition) {
		String getid = condition.get("caseAssignedId");
		if (StringUtils.isNotBlank(getid)) {
			String caseAssignedIds = SQLUtil.warpSql(getid);
			condition.put("caseAssignedIds", caseAssignedIds);
		}
		// 案件序列号
		// 需要算出剩余金额，
		// 公式： 委案金额减去已还款
		List<CaseDto> list = caseApprovalMapper.queryforGo(condition);
		double SumMoney = 0;
		if (0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				CaseDto dto = list.get(i);
				double caseMoney = dto.getCaseMoney().doubleValue();// 得到每一条数据的委案金额
				try { // 如果没有已还款， 那么已还款就是null， 会报空指针， 所以有异常不处理。
					SumMoney = dto.getCasepaidmodel().getSumMoney();// 得到已还款。有值就显示，没值报异常不处理。
				} catch (Exception e) {
				}
				double remainMoney = caseMoney - SumMoney;// 得出剩余金额
				dto.setRemainMoney(remainMoney);
			}
		}
		SearchResult<CaseDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 留案操作记录方法
	 */
	@Override
	public int updatefoApp(ApproveRecordModel model) {
		int upstate = caseApprovalMapper.updatefoApp(model);
		if (upstate > 0) {
			CaseDto StayTime = caseApprovalMapper.findStayTime(model.getCaseId());
			CaseModel cm = caseMapper.findById(model.getCaseId());
			cm.setCaseBackdate(StayTime.getApproverecordmodel().getStayTime());
			caseMapper.update(cm);
		}
		if (upstate > 0) {
			// 操作记录表id
			HurryRecordModel hr = new HurryRecordModel();
			Date date=new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
			String state = "";// 审批状态
			if (1 == model.getApproveState()) {
				state = "待审批";
			} else if (2 == model.getApproveState()) {
				state = "审批通过";
			} else if (3 == model.getApproveState()) {
				state = "审批不通过";
			}
			String content = "审批状态：" + state + ",审批内容:" + model.getApproveOpinion() +  ",审批时间"
					+ dateStr;
			hr.setId(UUIDUtil.UUID32());
			hr.setHurryRecordId(model.getId());
			hr.setHurCat("casM");
			hr.setContent(content);
			hr.setCaseId(model.getCaseId());
			hr.setCreateEmpId(currentUser.getId());
			hr.setOperatorName(currentUser.getUserName());
			hr.setCreateTime(date);
			hurryRecordMapper.save(hr);

		}
		return upstate;
	}

	/**
	 * 外访操作展示方法
	 */
	@Override
	public int updatefoVi(VisitRecordModel model) {
		int upstate = caseApprovalMapper.updatefoVi(model);
		if (upstate > 0) {
			 // 操作记录表id
			HurryRecordModel hr = new HurryRecordModel();
			Date date=new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
			String state = "";// 审批状态
			if (1 == model.getApproveState()) {
				state = "待审批";
			} else if (2 == model.getApproveState()) {
				state = "审批通过";
			} else if (3 == model.getApproveState()) {
				state = "审批不通过";
			}
			String content = "审批状态：" + state + ",审批内容:" + model.getApproveOpinion() + ",审批时间"
					+ dateStr;
			hr.setId(UUIDUtil.UUID32());
			hr.setHurCat("oVis");
			hr.setContent(content);
			hr.setCaseId(model.getCaseId());
			hr.setCreateEmpId(currentUser.getId());
			hr.setOperatorName(currentUser.getUserName());
			hr.setHurryRecordId(model.getId());
			hr.setCreateTime(date);
			hurryRecordMapper.save(hr);
		}
		return upstate;
	}

	@Override
	public int saveApproveRecord(ApproveRecordModel model) {
		caseApprovalMapper.insert(model);
		return 1;
	}

	@Override
	public SearchResult<ApproveRecordModel> queryStayByCaseId(ParamCondition condition) {
		List<ApproveRecordModel> list = caseApprovalMapper.queryStay(condition);
		SearchResult<ApproveRecordModel> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public List<ApproveRecordModel> findByCaseId(String caseId) {
		return caseApprovalMapper.findByCaseId(caseId);
	}

}
