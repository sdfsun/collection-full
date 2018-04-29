package com.kangde.collection.dto;

import java.util.Date;

import com.kangde.collection.model.CaseModel;

/**
 * 案件查询Dto,用于案件--查询视图
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseViewDto extends CaseModel {
	/** 批次号 */
	private String batchCode;
	/** 风控人姓名 */
	private String caseAssignedName;
	/** 已还款（实际还款金额） */
	private Double surplusMoney;
	/** PTP承诺还款金额 */
	private Double ptpMoney;
	/** CP待确认还款金额 */
	private Double cpMoney;
	/** 已确认还款金额 */
	private Double paidNum;
	/** 风控次数 */
	private Integer csCount;
	/** 委托方名称 */
	private String entrustName;
	/** cp日期 */
	private Date cpTime;
	
	/** 型号*/
	private String number;
	/** 车架号*/
	private String vinNo;
	/** 车牌号*/
	private String liceNo;
	/** 品牌*/
	private String brand;
	
	
	/** 单位号码 */
	private String companyPhone;
	/** 单位地址 */
	private String companyAddress;
	/** 家庭号码 */
	private String familyPhone;
	/** 家庭地址 */
	private String familyAddress;
	

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	public String getLiceNo() {
		return liceNo;
	}

	public void setLiceNo(String liceNo) {
		this.liceNo = liceNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getCpTime() {
		return cpTime;
	}

	public void setCpTime(Date cpTime) {
		this.cpTime = cpTime;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getCaseAssignedName() {
		return caseAssignedName;
	}

	public void setCaseAssignedName(String caseAssignedName) {
		this.caseAssignedName = caseAssignedName;
	}

	public Double getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(Double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public Double getPtpMoney() {
		return ptpMoney;
	}

	public void setPtpMoney(Double ptpMoney) {
		this.ptpMoney = ptpMoney;
	}

	public Double getCpMoney() {
		return cpMoney;
	}

	public void setCpMoney(Double cpMoney) {
		this.cpMoney = cpMoney;
	}

	public Double getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(Double paidNum) {
		this.paidNum = paidNum;
	}

	public Integer getCsCount() {
		return csCount;
	}

	public void setCsCount(Integer csCount) {
		this.csCount = csCount;
	}

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}

}
