package com.kangde.collection.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.util.DateUtil;

/**
 * 外访记录 案件详情页展示
 * 
 * @author lidengwen
 * @date 2016年7月16日 上午11:22:08
 *
 */
public class VisitRecordVo {
	/** 员工id */
	private String infoId;
	/** 外访id */
	private String id;
	/** 案件id */
	private String caseId;
	/** 姓名 */
	private String name;
	/** 关系 */
	private String relation;
	/** 外访地址 */
	private String visitAddress;
	/** 申请原因 */
	private String applyReason;
	/** 外访时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualVisitTime;
	/** 外访报告 */
	private String visitReport;
	/** 外访员 */
	private String visitUser;
	/** 外访员联系方式 */
	private String contactMode;
	/** 申请时间 */
	private Date applyTime;
	/** 外访状态 状态 0待审核 1被撤销 2待排程 3已排程 4已完成-无效 */
	private Integer state;
	/** 审批意见 */
	private String approveOpinion;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getVisitAddress() {
		return visitAddress;
	}

	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Date getActualVisitTime() {
		return actualVisitTime;
	}

	public void setActualVisitTime(Date actualVisitTime) {
		this.actualVisitTime = actualVisitTime;
	}

	public String getVisitReport() {
		return visitReport;
	}

	public void setVisitReport(String visitReport) {
		this.visitReport = visitReport;
	}

	public String getVisitUser() {
		return visitUser;
	}

	public void setVisitUser(String visitUser) {
		this.visitUser = visitUser;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContactMode() {
		return contactMode;
	}

	public void setContactMode(String contactMode) {
		this.contactMode = contactMode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
