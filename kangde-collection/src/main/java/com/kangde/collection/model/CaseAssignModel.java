package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BaseModel;

/**
 * 案件分配 Model
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseAssignModel extends BaseModel<String> {

	/** 案件表id */
	private String caseId;
	/** 被分配人ID */
	private String caseAssignedId;
	/** 被分配人名称 */
	private String caseAssignedName;
	/** 分配人ID */
	private String caseAssignId;
	/** 分配人名称 */
	private String caseAssignName;
	/** 分配时间 */
	private Date caseAssignTime;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseAssignedId() {
		return caseAssignedId;
	}

	public void setCaseAssignedId(String caseAssignedId) {
		this.caseAssignedId = caseAssignedId;
	}

	public String getCaseAssignedName() {
		return caseAssignedName;
	}

	public void setCaseAssignedName(String caseAssignedName) {
		this.caseAssignedName = caseAssignedName;
	}

	public String getCaseAssignId() {
		return caseAssignId;
	}

	public void setCaseAssignId(String caseAssignId) {
		this.caseAssignId = caseAssignId;
	}

	public String getCaseAssignName() {
		return caseAssignName;
	}

	public void setCaseAssignName(String caseAssignName) {
		this.caseAssignName = caseAssignName;
	}

	public Date getCaseAssignTime() {
		return caseAssignTime;
	}

	public void setCaseAssignTime(Date caseAssignTime) {
		this.caseAssignTime = caseAssignTime;
	}

}
