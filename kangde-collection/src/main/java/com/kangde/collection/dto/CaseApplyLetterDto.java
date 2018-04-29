package com.kangde.collection.dto;

import com.kangde.collection.model.CaseApply;

/**
 * 信函Dto
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseApplyLetterDto extends CaseApply {
	/** 案件序列号 */
	private String caseCode;
	/** 证件号 */
	private String caseNum;
	/** 姓名 */
	private String caseName;
	/** 委案金额 */
	private Double caseMoney;
	/** 还款金额 */
	private Double paidNum;
	/** 风控方 */
	private String orgName;
	/** 操作人 */
	private String surUserName;
	/** 申请人名称 */
	private String applyUserName2;
	/** 颜色 */
	private String color;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Double getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(Double caseMoney) {
		this.caseMoney = caseMoney;
	}

	public Double getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(Double paidNum) {
		this.paidNum = paidNum;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSurUserName() {
		return surUserName;
	}

	public void setSurUserName(String surUserName) {
		this.surUserName = surUserName;
	}

	public String getApplyUserName2() {
		return applyUserName2;
	}

	public void setApplyUserName2(String applyUserName2) {
		this.applyUserName2 = applyUserName2;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
