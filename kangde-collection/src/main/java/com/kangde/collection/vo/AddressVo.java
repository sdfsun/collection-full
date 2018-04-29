package com.kangde.collection.vo;

import java.util.Date;

public class AddressVo {

	private String id;
	private String name;
	private String relation;
	private String adrCat;
	private String status;
	private String fullAddress;
	private String address;
	private String remark;
	private String visApp;
	private String mailApp;
	private String caseId;
	private String source;
	/** 创建时间 */
	private Date createTime;
	/** 省 */
	private String areaId1;
	/** 市 */
	private String areaId2;
	/** 区县 */
	private String areaId3;
	/** 省 */
	private String areaName1;
	/** 市 */
	private String areaName2;
	/** 区县 */
	private String areaName3;

	public String getAreaName1() {
		return areaName1;
	}

	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}

	public String getAreaName2() {
		return areaName2;
	}

	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}

	public String getAreaName3() {
		return areaName3;
	}

	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAdrCat() {
		return adrCat;
	}

	public void setAdrCat(String adrCat) {
		this.adrCat = adrCat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVisApp() {
		return visApp;
	}

	public void setVisApp(String visApp) {
		this.visApp = visApp;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getAreaId1() {
		return areaId1;
	}

	public void setAreaId1(String areaId1) {
		this.areaId1 = areaId1;
	}

	public String getAreaId2() {
		return areaId2;
	}

	public void setAreaId2(String areaId2) {
		this.areaId2 = areaId2;
	}

	public String getAreaId3() {
		return areaId3;
	}

	public void setAreaId3(String areaId3) {
		this.areaId3 = areaId3;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailApp() {
		return mailApp;
	}

	public void setMailApp(String mailApp) {
		this.mailApp = mailApp;
	}

}
