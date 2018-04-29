package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;

/**
 * 案件联系人表Model
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseLinkmanModel extends BizModel<String> {
	/** 案件表ID */
	private String caseId;
	/** 姓名 */
	private String name;
	/** 手机 */
	private String mobile;
	/** 电话类型  */
	private String phoneType;
	/** 电话 */
	private String phone;
	/** 证件号 */
	private String cardNo;
	/** 单位 */
	private String unitName;
	/** 单位电话 */
	private String unitPhone;
	/** 家庭电话 */
	private String familyPhone;
	/** 地址 */
	private String address;
	/** 位置 */
	private Integer position;
	/** 关系 */
	private String relation;
	/** 备注 */
	private String remark;
	/** 来源 0贴档 1新增' */
	private String source;
	
	/** 地址 */
	private String addressJtzz;
	/** 地址 */
	private String addressHjzz;
	/** 地址 */
	private String addressDwmc;
	/** 地址 */
	private String addressDwdz;
	
	

	public String getAddressJtzz() {
		return addressJtzz;
	}

	public void setAddressJtzz(String addressJtzz) {
		this.addressJtzz = addressJtzz;
	}

	public String getAddressHjzz() {
		return addressHjzz;
	}

	public void setAddressHjzz(String addressHjzz) {
		this.addressHjzz = addressHjzz;
	}

	public String getAddressDwmc() {
		return addressDwmc;
	}

	public void setAddressDwmc(String addressDwmc) {
		this.addressDwmc = addressDwmc;
	}

	public String getAddressDwdz() {
		return addressDwdz;
	}

	public void setAddressDwdz(String addressDwdz) {
		this.addressDwdz = addressDwdz;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
