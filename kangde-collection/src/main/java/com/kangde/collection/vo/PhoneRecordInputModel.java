package com.kangde.collection.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.util.DateUtil;

public class PhoneRecordInputModel {

	private String linkmanId;
	private String caseId;
	private String contact;
	private String prCat;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_TIME_FORMAT)
	private Date createTime;
	private String typeId;
	private Double ptpMoney;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date ptpTime;
	private String collecStateId;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date nextFollowTime;
	private String content;

	public String getLinkmanId() {
		return linkmanId;
	}

	public void setLinkmanId(String linkmanId) {
		this.linkmanId = linkmanId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getPrCat() {
		return prCat;
	}

	public void setPrCat(String prCat) {
		this.prCat = prCat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Double getPtpMoney() {
		return ptpMoney;
	}

	public void setPtpMoney(Double ptpMoney) {
		this.ptpMoney = ptpMoney;
	}

	public Date getPtpTime() {
		return ptpTime;
	}

	public void setPtpTime(Date ptpTime) {
		this.ptpTime = ptpTime;
	}

	public String getCollecStateId() {
		return collecStateId;
	}

	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}

	public Date getNextFollowTime() {
		return nextFollowTime;
	}

	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
