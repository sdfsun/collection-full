package com.kangde.collection.dto;

import java.util.Date;

import com.kangde.collection.model.PhoneRecordModel;

/**
 * 案件审批 Dto,用于案件审批--查询视图
 * 
 * @author wcy
 * @date 2016年6月30日09:49:34
 *
 */
@SuppressWarnings("serial")
public class PhoneRecordViewDto extends PhoneRecordModel {
	/** 案件序列号 */
	private String caseCode;
	/** 姓名 */
	private String caseName;
	/** 被分配人ID,(风控员(员工)ID) */
	private String caseAssignedId;
	/** 委案日期 */
	private Date caseDate;
	/** 证件号 */
	private String caseNum;
	/** 卡号 */
	private String caseCard;
	/** 贷款合同号 */
	private String loanContract;
	/** 委托方id*/
    private String entrustId;
	/** 委托方名称   */
	private String entrustName;
	/** 批次号*/
	private String batchCode;
	/** 员工姓名 */
	private String userName;
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseAssignedId() {
		return caseAssignedId;
	}
	public void setCaseAssignedId(String caseAssignedId) {
		this.caseAssignedId = caseAssignedId;
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
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getCaseCard() {
		return caseCard;
	}
	public void setCaseCard(String caseCard) {
		this.caseCard = caseCard;
	}
	public String getLoanContract() {
		return loanContract;
	}
	public void setLoanContract(String loanContract) {
		this.loanContract = loanContract;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getEntrustName() {
		return entrustName;
	}
	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
