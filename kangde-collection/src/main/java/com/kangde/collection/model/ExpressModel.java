package com.kangde.collection.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;

/**
 * 
 * @Description: 快递
 * @author wangcy
 * @date 2016年12月16日10:38:28
 *
 */
public class ExpressModel extends BizModel<String> {
	private static final long serialVersionUID = -1482032318920983067L;
	/**'协催申请id'*/
	private String caseApplyId;
	/**'姓名'*/
	private String name;
	/**'证件类型'*/
	private String cardType;
	/**'证件号'*/
	private String cardNum;
	/**'历史用户名'*/
	private String hisUserName;
	/**'手机'*/
	private String mobile;
	/**'家庭电话'*/
	private String familyPhone;
	/**'家庭地址'*/
	private String familyAddress;
	/**'邮箱'*/
	private String email;
	/**'QQ'*/
	private String qq;
	/**'qq群'*/
	private String qqGroup;
	/**'微信'*/
	private String wechat;
	/**'微博'*/
	private String blog;
	/**'收货人姓名'*/
	private String consigneeName;
	/**'收货人电话'*/
	private String consigneePhone;
	/**'收货人地址'*/
	private String consigneeAddress;
	/**'单位名称'*/
	private String unitName;
	/**'单位电话'*/
	private String unitPhone;
	/**'单位地址'*/
	private String unitAddress;
	/**'重要联系人名称'*/
	private String importantLinkmanName;
	/**'重要联系人身份证号'*/
	private String importantLinkmanCardNum;
	/**'重要联系人电话'*/
	private String importantLinkmanPhone;
	/**'重要联系人地址'*/
	private String importantLinkmanAddress;
	/**'联系人名称'*/
	private String linkmanName;
	/**'联系人身份证号'*/
	private String linkmanCardNum;
	/**'联系人电话'*/
	private String linkmanPhone;
	/**'联系人地址'*/
	private String linkmanAddress;
	/**'联系人单位名称'*/
	private String linkmanUnitName;
	/**'获取时间'*/
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date obtainTime;
	/**'创建人'*/
	private String createEmpId;
	/**'备注'*/
	private String remark;
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getHisUserName() {
		return hisUserName;
	}
	public void setHisUserName(String hisUserName) {
		this.hisUserName = hisUserName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getQqGroup() {
		return qqGroup;
	}
	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
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
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getImportantLinkmanName() {
		return importantLinkmanName;
	}
	public void setImportantLinkmanName(String importantLinkmanName) {
		this.importantLinkmanName = importantLinkmanName;
	}
	public String getImportantLinkmanCardNum() {
		return importantLinkmanCardNum;
	}
	public void setImportantLinkmanCardNum(String importantLinkmanCardNum) {
		this.importantLinkmanCardNum = importantLinkmanCardNum;
	}
	public String getImportantLinkmanPhone() {
		return importantLinkmanPhone;
	}
	public void setImportantLinkmanPhone(String importantLinkmanPhone) {
		this.importantLinkmanPhone = importantLinkmanPhone;
	}
	public String getImportantLinkmanAddress() {
		return importantLinkmanAddress;
	}
	public void setImportantLinkmanAddress(String importantLinkmanAddress) {
		this.importantLinkmanAddress = importantLinkmanAddress;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getLinkmanCardNum() {
		return linkmanCardNum;
	}
	public void setLinkmanCardNum(String linkmanCardNum) {
		this.linkmanCardNum = linkmanCardNum;
	}
	public String getLinkmanPhone() {
		return linkmanPhone;
	}
	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	public String getLinkmanAddress() {
		return linkmanAddress;
	}
	public void setLinkmanAddress(String linkmanAddress) {
		this.linkmanAddress = linkmanAddress;
	}
	public String getLinkmanUnitName() {
		return linkmanUnitName;
	}
	public void setLinkmanUnitName(String linkmanUnitName) {
		this.linkmanUnitName = linkmanUnitName;
	}
	public String getCreateEmpId() {
		return createEmpId;
	}
	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getObtainTime() {
		return obtainTime;
	}
	public void setObtainTime(Date obtainTime) {
		this.obtainTime = obtainTime;
	}

}