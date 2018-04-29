package com.kangde.collection.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 电话簿Model
 * 
 * @author wcy
 * @date 2016年5月31日16:34:59
 */
@SuppressWarnings("serial")
public class PhoneRecordModel extends BizModel<String> {
	/** 风控结果 字典表ID casePhone类型 */
	private String typeId;
	/** 联系方式 电话/地址 */
	private String contact;
	/** 案件表id */
	private String caseId;
	/** 通话记录/风控记录 */
	private String content;
	/** 姓名/类型 */
	private String conType;
	/**   */
	private Long prPaId;
	/** 姓名 */
	private String name;
	/** 风控措施 */
	private String prCat;
	/** ptp录入时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date ptpTime;
	/** ptp金额 */
	private Double ptpMoney;
	/** 关系 */
	private String relation;
	/** 风控状态 对应字典表 caseState */
	private String collecStateId;
	/** 谈判方式 */
	private String negotiation;
	/**   */
	private Long prCcId;
	/** 呼叫中心id */
	private Long callId;
	/**操作人 */
	private EmployeeInfoModel employeeInfo;
	/**风控员 */
	private EmployeeInfoModel caseAssignedEmployeeInfo;
	/** ptp状态 0未转cp 1已转cp */
	private String cpState;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getConType() {
		return conType;
	}

	public void setConType(String conType) {
		this.conType = conType;
	}

	public Long getPrPaId() {
		return prPaId;
	}

	public void setPrPaId(Long prPaId) {
		this.prPaId = prPaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrCat() {
		return prCat;
	}

	public void setPrCat(String prCat) {
		this.prCat = prCat;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getCollecStateId() {
		return collecStateId;
	}

	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}

	public String getNegotiation() {
		return negotiation;
	}

	public void setNegotiation(String negotiation) {
		this.negotiation = negotiation;
	}

	public Long getPrCcId() {
		return prCcId;
	}

	public void setPrCcId(Long prCcId) {
		this.prCcId = prCcId;
	}

	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public String getCreateEmpId() {
		return createEmpId;
	}

	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
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

	public String getCpState() {
		return cpState;
	}

	public void setCpState(String cpState) {
		this.cpState = cpState;
	}

	public EmployeeInfoModel getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfoModel employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public EmployeeInfoModel getCaseAssignedEmployeeInfo() {
		return caseAssignedEmployeeInfo;
	}

	public void setCaseAssignedEmployeeInfo(EmployeeInfoModel caseAssignedEmployeeInfo) {
		this.caseAssignedEmployeeInfo = caseAssignedEmployeeInfo;
	}
}