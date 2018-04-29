package com.kangde.collection.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.util.DateUtil;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 支付Model
 * 
 * @author wcy
 */
@SuppressWarnings("serial")
public class CasePaid extends BaseModel<String> {
	/** 案件id */
	private String caseId;
	/** 登账状态 0ptp正常 1 cp正常 2确认 3作废ptp 4作废cp(撤销) */
	private Integer state;
	/** ptp时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date ptpTime;
	/** ptp金额 */
	private Double ptpMoney;
	/** cp时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date cpTime;
	/** cp金额 */
	private Double cpMoney;
	/** 确认还款时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_TIME_FORMAT)
	private Date paidTime;
	/** 确认还款金额 */
	private Double paidNum;
	/** 确认人 */
	private String surUser;
	/** 确认时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_TIME_FORMAT)
	private Date surTime;
	/** 备注 */
	private String surRemark;
	/** 删除人 */
	private String delUser;
	/** 删除时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_TIME_FORMAT)
	private Date delTime;
	/** 操作人 */
	private String operateEmpId;
	/** 操作时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_TIME_FORMAT)
	private Date operateTime;
	/**  */
	private Double mPaid;
	/**  */
	private Double cpmPaid;
	/** 风控人ID */
	private String seNo;
	
	private EmployeeInfoModel employeeInfo;
	/** ptp时间 */
	private Double cmPaid;
	
	/**  */
	private Double pbackPaid;
	/** */
	private Double lastDebtM;
	/** 已还款总和，数据库无此字段*/
	private double sumMoney;
	private double  leftAmt;
	 /** 创建人*/
    private String createEmpId;
    private String operateName;
    /** 修改人*/
    private String modifyEmpId;
    
    private Double cpTotalMoney;
    
    private String cpName;
    /** 剩余还款 */
    private Double surplusMoney;
    /** 减免状态 */
    private String isDerate;
    /** 内部减免 */
    private Double inDerate;
    /** 外部减免 */
    private Double outDerate;
    /** 内部减免 */
    private Double inDerateNew;
    /** 外部减免 */
    private Double outDerateNew;
    /** 结算金额 */
    private Double compBorker;
    /** 作废原因 */
    private String cancelReason;
    /** 还款类型 1正常 2自来账  默认为1正常*/
    private String repayType="1";
    /** 公司佣金 */
    private Double backPaid;
    /** 佣金率 */
    private Double backPaidRate;
    /** 公司佣金 */
    private Double entrustPaidRate;
    /** 佣金率 */
    private Double entrustPaid;
    
    
   	public Double getEntrustPaidRate() {
		return entrustPaidRate;
	}
	public void setEntrustPaidRate(Double entrustPaidRate) {
		this.entrustPaidRate = entrustPaidRate;
	}
	public Double getEntrustPaid() {
		return entrustPaid;
	}
	public void setEntrustPaid(Double entrustPaid) {
		this.entrustPaid = entrustPaid;
	}
	public String getIsDerate() {
		return isDerate;
	}
	public void setIsDerate(String isDerate) {
		this.isDerate = isDerate;
	}
	public Double getCpTotalMoney() {
   		return cpTotalMoney;
   	}
   	public void setCpTotalMoney(Double cpTotalMoney) {
   		this.cpTotalMoney = cpTotalMoney;
   	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getPtpTime() {
		return ptpTime;
	}
	public void setPtpTime(Date ptpTime) {
		this.ptpTime = ptpTime;
	}
	public Double getPtpMoney() {
		return ptpMoney;
	}
	public void setPtpMoney(Double ptpMoney) {
		this.ptpMoney = ptpMoney;
	}
	public Date getCpTime() {
		return cpTime;
	}
	public void setCpTime(Date cpTime) {
		this.cpTime = cpTime;
	}
	public Double getCpMoney() {
		return cpMoney;
	}
	public void setCpMoney(Double cpMoney) {
		this.cpMoney = cpMoney;
	}
	public Date getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}
	public Double getPaidNum() {
		return paidNum;
	}
	public void setPaidNum(Double paidNum) {
		this.paidNum = paidNum;
	}
	public String getSurUser() {
		return surUser;
	}
	public void setSurUser(String surUser) {
		this.surUser = surUser;
	}
	public Date getSurTime() {
		return surTime;
	}
	public void setSurTime(Date surTime) {
		this.surTime = surTime;
	}
	public String getSurRemark() {
		return surRemark;
	}
	public void setSurRemark(String surRemark) {
		this.surRemark = surRemark;
	}
	public String getDelUser() {
		return delUser;
	}
	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public Double getmPaid() {
		return mPaid;
	}
	public void setmPaid(Double mPaid) {
		this.mPaid = mPaid;
	}
	public Double getCpmPaid() {
		return cpmPaid;
	}
	public void setCpmPaid(Double cpmPaid) {
		this.cpmPaid = cpmPaid;
	}
	public String getSeNo() {
		return seNo;
	}
	public void setSeNo(String seNo) {
		this.seNo = seNo;
	}
	public Double getCmPaid() {
		return cmPaid;
	}
	public void setCmPaid(Double cmPaid) {
		this.cmPaid = cmPaid;
	}
	public Double getBackPaid() {
		return backPaid;
	}
	public void setBackPaid(Double backPaid) {
		this.backPaid = backPaid;
	}
	public Double getPbackPaid() {
		return pbackPaid;
	}
	public void setPbackPaid(Double pbackPaid) {
		this.pbackPaid = pbackPaid;
	}
	public Double getLastDebtM() {
		return lastDebtM;
	}
	public void setLastDebtM(Double lastDebtM) {
		this.lastDebtM = lastDebtM;
	}
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getCreateEmpId() {
		return createEmpId;
	}
	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}
	public String getModifyEmpId() {
		return modifyEmpId;
	}
	public void setModifyEmpId(String modifyEmpId) {
		this.modifyEmpId = modifyEmpId;
	}
	public double getLeftAmt() {
		return leftAmt;
	}
	public void setLeftAmt(double leftAmt) {
		this.leftAmt = leftAmt;
	}

	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public EmployeeInfoModel getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfoModel employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public Double getSurplusMoney() {
		return surplusMoney;
	}
	public void setSurplusMoney(Double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
	public Double getInDerate() {
		return inDerate;
	}
	public void setInDerate(Double inDerate) {
		this.inDerate = inDerate;
	}
	public Double getOutDerate() {
		return outDerate;
	}
	public void setOutDerate(Double outDerate) {
		this.outDerate = outDerate;
	}
	public Double getInDerateNew() {
		return inDerateNew;
	}
	public void setInDerateNew(Double inDerateNew) {
		this.inDerateNew = inDerateNew;
	}
	public Double getOutDerateNew() {
		return outDerateNew;
	}
	public void setOutDerateNew(Double outDerateNew) {
		this.outDerateNew = outDerateNew;
	}
	public Double getCompBorker() {
		return compBorker;
	}
	public void setCompBorker(Double compBorker) {
		this.compBorker = compBorker;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getRepayType() {
		return repayType;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public Double getBackPaidRate() {
		return backPaidRate;
	}
	public void setBackPaidRate(Double backPaidRate) {
		this.backPaidRate = backPaidRate;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateEmpId() {
		return operateEmpId;
	}
	public void setOperateEmpId(String operateEmpId) {
		this.operateEmpId = operateEmpId;
	}
}