package com.kangde.collection.vo;

import java.util.Date;

/**
 * 
 * @Description: 共债案件
 * @author lidengwen
 * @date 2016年6月2日 下午2:38:05
 *
 */
public class CaseJointDebtVo {

	/** 批次号 */
	private String batchCode;
	/** 案件ID */
	private String caseId;
	/** 案件序列号 */
	private String caseCode;
	/** 委案日期 */
	private Date caseDate;
	/** 卡号 */
	private String caseNum;
	/** 委托方 */
	private String entrustName;
	/** 委案金额 */
	private Double caseMoney;
	/** 风控状态 */
	private String collecStateId;
	/** 已还款 */
	private Double paidNum;
	/** 风控员 */
	private String userName;

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Date getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(Date caseDate) {
		this.caseDate = caseDate;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseCard) {
		this.caseNum = caseCard;
	}

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrust) {
		this.entrustName = entrust;
	}

	public Double getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(Double caseMoney) {
		this.caseMoney = caseMoney;
	}

	public String getCollecStateId() {
		return collecStateId;
	}

	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}

	public Double getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(Double paidNum) {
		this.paidNum = paidNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
}
