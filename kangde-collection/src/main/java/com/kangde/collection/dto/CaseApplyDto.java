package com.kangde.collection.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.collection.model.TelecomModel;
import com.kangde.commons.util.DateUtil;

/**
 * 用于待处理协催---导入，xml解析
 * @author zhangyj
 * @date 2016年7月21日
 *
 */

@SuppressWarnings("serial")
public class CaseApplyDto extends CaseApplyModel{
	
	//案件表
	/** 案件序列号 */
	private String caseCode;
	
	/** 证件号 */
	private String caseNum;
	
	/** 姓名 */
	private String caseName;
	
	//户籍
	/**  案件协催申请id*/  //户籍、社保和电信共有
    private String caseApplyId;
    
    /**  户籍地址  */ //户籍和电信共有
    private String crAddress;
    
    /**  住址  */  //户籍和电信共有
    private String address;
    /**  婚姻状况 */
    private Integer married;
    /**  配偶姓名  */
    private String mate;
    /**  文化程度  */
    private String culture;
    /**  职业  */
    private String career;
    /**  家庭电话  */
    private String familyPhone;
    
    /**  手机  */   //户籍和电信共有
    private String mobile;
    
    /**  关系1  */   //户籍和电信共有
    private String relation1;
    
    /**  同户人员1  */
    private String personnel1;
    /**  证件号1  */  //户籍和电信共有
    private String caseNum1;
    
    /**  关系2  */   //户籍和电信共有
    private String relation2;
    
    /**  同户人员2  */ 
    private String personnel2;
    
    /**  证件号2  */  //户籍和电信共有
    private String caseNum2;
    
    /**  联系人状态 0未知 1有效 2无效  */
    private Integer status;
    
    /**  备注  */   //户籍、社保和电信共有
    private String remark;
    
    //社保
    /** 单位名称  */
    private String unitName;
    /** 单位电话1  */
    private String unitPhone1;
    /** 单位地址  */
    private String unitAddress;
    /** 单位电话2  */
    private String unitPhone2;
    /** 联系人  */
    private String linkman;
    /** 电话  */
    private String phone;
    /** 最后续费日期  */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date lastRenewDate;
    
    //电信
    /** 手机 */
    /** 运营商 */
    private String operator;
    /** 手机状态 0停机 1正常 2销户 */
    private Integer mobileStatus;
    /** 宽带号 */
    private String wideBand;
    /** 网络提供商 */
    private String networkProvide;
    /** 宽带状态 0停机 1正常 2销户 */
    private Integer wideStatus;
    /** 户籍地址 */
    /** 住址  */
    /** 关系1 */
    /** 联系人1 */
    private String linkman1;
    /** 证件号1 */
    /** 关系2 */
    /** 联系人2 */
    private String linkman2;
    /** 证件号2 */
    /** 备注  */
    
   //快递
    private String cardNum;
	/**'历史用户名'*/
	private String hisUserName;
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
	/**'单位电话'*/
	private String unitPhone;
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
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getCrAddress() {
		return crAddress;
	}
	public void setCrAddress(String crAddress) {
		this.crAddress = crAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMarried() {
		return married;
	}
	public void setMarried(Integer married) {
		this.married = married;
	}
	public String getMate() {
		return mate;
	}
	public void setMate(String mate) {
		this.mate = mate;
	}
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRelation1() {
		return relation1;
	}
	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}
	public String getPersonnel1() {
		return personnel1;
	}
	public void setPersonnel1(String personnel1) {
		this.personnel1 = personnel1;
	}
	public String getCaseNum1() {
		return caseNum1;
	}
	public void setCaseNum1(String caseNum1) {
		this.caseNum1 = caseNum1;
	}
	public String getRelation2() {
		return relation2;
	}
	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}
	public String getPersonnel2() {
		return personnel2;
	}
	public void setPersonnel2(String personnel2) {
		this.personnel2 = personnel2;
	}
	public String getCaseNum2() {
		return caseNum2;
	}
	public void setCaseNum2(String caseNum2) {
		this.caseNum2 = caseNum2;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitPhone1() {
		return unitPhone1;
	}
	public void setUnitPhone1(String unitPhone1) {
		this.unitPhone1 = unitPhone1;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getUnitPhone2() {
		return unitPhone2;
	}
	public void setUnitPhone2(String unitPhone2) {
		this.unitPhone2 = unitPhone2;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getLastRenewDate() {
		return lastRenewDate;
	}
	public void setLastRenewDate(Date lastRenewDate) {
		this.lastRenewDate = lastRenewDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getMobileStatus() {
		return mobileStatus;
	}
	public void setMobileStatus(Integer mobileStatus) {
		this.mobileStatus = mobileStatus;
	}
	public String getWideBand() {
		return wideBand;
	}
	public void setWideBand(String wideBand) {
		this.wideBand = wideBand;
	}
	public String getNetworkProvide() {
		return networkProvide;
	}
	public void setNetworkProvide(String networkProvide) {
		this.networkProvide = networkProvide;
	}
	public Integer getWideStatus() {
		return wideStatus;
	}
	public void setWideStatus(Integer wideStatus) {
		this.wideStatus = wideStatus;
	}
	public String getLinkman1() {
		return linkman1;
	}
	public void setLinkman1(String linkman1) {
		this.linkman1 = linkman1;
	}
	public String getLinkman2() {
		return linkman2;
	}
	public void setLinkman2(String linkman2) {
		this.linkman2 = linkman2;
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
	public String getUnitPhone() {
		return unitPhone;
	}
	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
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
	public Date getObtainTime() {
		return obtainTime;
	}
	public void setObtainTime(Date obtainTime) {
		this.obtainTime = obtainTime;
	}

}
