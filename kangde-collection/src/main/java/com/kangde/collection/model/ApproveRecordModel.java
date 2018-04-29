package com.kangde.collection.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 留案 模型
 * 
 * @author wcy
 *
 */
@SuppressWarnings("serial")
public class ApproveRecordModel extends BizModel<String> {

	/** 案件id */
	private String caseId;
	/** 申请原因 */
	private String applyReason;
	/** 状态 0待审批 1审批通过 2审批不通过 */
	private Integer approveState;
	/** 审批意见 */
	private String approveOpinion;
	/** 备注 申请原因 */
	private String remark;
	/** 申请人 */
	private String applyEmpId;
	/** 申请人 */
	private EmployeeInfoModel employeeInfo;
	/** 申请时间 */
	private Date applyTime;
	/** 留案原因 */
	private String stayReason;
	/** 留案时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date stayTime;
	/** 留案周期*/
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date stayPeriod;
	private Integer arState;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Integer getApproveState() {
		return approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplyEmpId() {
		return applyEmpId;
	}

	public void setApplyEmpId(String applyEmpId) {
		this.applyEmpId = applyEmpId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getStayReason() {
		return stayReason;
	}

	public void setStayReason(String stayReason) {
		this.stayReason = stayReason;
	}

	public Date getStayTime() {
		return stayTime;
	}

	public void setStayTime(Date stayTime) {
		this.stayTime = stayTime;
	}

	public Integer getArState() {
		return arState;
	}

	public void setArState(Integer arState) {
		this.arState = arState;
	}

	public EmployeeInfoModel getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfoModel employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public Date getStayPeriod() {
		return stayPeriod;
	}

	public void setStayPeriod(Date stayPeriod) {
		this.stayPeriod = stayPeriod;
	}
}