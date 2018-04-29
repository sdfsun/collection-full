package com.kangde.collection.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.util.DateUtil;

public class VisitRecordView{

	/** 风控状态 对应字典表 */
	private String collecStateId;
	/** 案件序列号 */
	private String caseCode;
	/** 姓名 */
	private String name;
	/** 关系 */
	private String relation;
	/** 委案金额 */
	private BigDecimal caseMoney;
	/** 已还款金额 */
	private BigDecimal paidNum;
	/** 外访地址 */
	private String visitAddress;
	/** 要求 */
	private String require;
	/** 员工姓名 */
	private String userName;
	/** 外访人 */
	private String visitUser;
	/** 实际外访时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualVisitTime;
	/** 是否有效 */
	private String isEffective;
	public String getCollecStateId() {
		return collecStateId;
	}
	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
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
	public BigDecimal getCaseMoney() {
		return caseMoney;
	}
	public void setCaseMoney(BigDecimal caseMoney) {
		this.caseMoney = caseMoney;
	}
	public BigDecimal getPaidNum() {
		return paidNum;
	}
	public void setPaidNum(BigDecimal paidNum) {
		this.paidNum = paidNum;
	}
	public String getVisitAddress() {
		return visitAddress;
	}
	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVisitUser() {
		return visitUser;
	}
	public void setVisitUser(String visitUser) {
		this.visitUser = visitUser;
	}
	public Date getActualVisitTime() {
		return actualVisitTime;
	}
	public void setActualVisitTime(Date actualVisitTime) {
		this.actualVisitTime = actualVisitTime;
	}
	public String getIsEffective() {
		return isEffective;
	}
	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}
	

}
