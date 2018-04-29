package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;

/**
 * @Description: 地址model
 * @author lidengwen
 * @date 2016年6月8日 下午5:40:25
 *
 */
public class AddressModel extends BizModel<String> {
	
	private static final long serialVersionUID = 1670059858363154555L;


	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 年龄
	 */
	private Integer age;

	/** 省 */
	private String areaId1;
	/** 市 */
	private String areaId2;
	/** 区县 */
	private String areaId3;
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 案件id
	 */
	private String caseId;

	/**
	 * 地址类别：单位地址、家庭地址、对账单地址、户籍地址、其他地址
	 */
	private String adrCat;

	/**
	 * 备注
	 */
	private String remark;

	private Integer checkApp;

	/**
	 * 是否申请信函 0否 1是
	 */
	private Integer mailApp;

	/**
	 * 是否申请外访 0否 1是
	 */
	private Integer visApp;

	/**
	 * 关系
	 */
	private String relation;

	/**
	 * 外访次数
	 */
	private Integer visCount;
	/**
	 * 信函次数
	 */
	private Integer mailCount;

	/**
	 * 是否新地址 0否 1是
	 */
	private Integer isnew;
	
	/** 来源 0贴档 1新增' */
	private String source;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId == null ? null : caseId.trim();
	}

	public String getAdrCat() {
		return adrCat;
	}

	public void setAdrCat(String adrCat) {
		this.adrCat = adrCat == null ? null : adrCat.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getCheckApp() {
		return checkApp;
	}

	public void setCheckApp(Integer checkApp) {
		this.checkApp = checkApp;
	}

	public Integer getMailApp() {
		return mailApp;
	}

	public void setMailApp(Integer mailApp) {
		this.mailApp = mailApp;
	}

	public Integer getVisApp() {
		return visApp;
	}

	public void setVisApp(Integer visApp) {
		this.visApp = visApp;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation == null ? null : relation.trim();
	}

	public Integer getMailCount() {
		return mailCount;
	}

	public void setMailCount(Integer mailCount) {
		this.mailCount = mailCount;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public Integer getVisCount() {
		return visCount;
	}

	public void setVisCount(Integer visCount) {
		this.visCount = visCount;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	

}