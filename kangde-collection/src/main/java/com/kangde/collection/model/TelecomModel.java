package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;

/**
 * 电信表Model
 * @date 2016年7月21日 
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class TelecomModel extends BizModel<String>{
	
	/** 案件协催申请id */
    private String caseApplyId;
    /** 手机 */
    private String mobile;
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
    private String crAddress;
    /** 住址  */
    private String address;
    /** 关系1 */
    private String relation1;
    /** 联系人1 */
    private String linkman1;
    /** 证件号1 */
    private String caseNum1;
    /** 关系2 */
    private String relation2;
    /** 联系人2 */
    private String linkman2;
    /** 证件号2 */
    private String caseNum2;
    /** 备注  */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId == null ? null : caseApplyId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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
        this.wideBand = wideBand == null ? null : wideBand.trim();
    }

    public String getNetworkProvide() {
        return networkProvide;
    }

    public void setNetworkProvide(String networkProvide) {
        this.networkProvide = networkProvide == null ? null : networkProvide.trim();
    }

    public Integer getWideStatus() {
        return wideStatus;
    }

    public void setWideStatus(Integer wideStatus) {
        this.wideStatus = wideStatus;
    }

    public String getCrAddress() {
        return crAddress;
    }

    public void setCrAddress(String crAddress) {
        this.crAddress = crAddress == null ? null : crAddress.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1 == null ? null : relation1.trim();
    }

    public String getLinkman1() {
        return linkman1;
    }

    public void setLinkman1(String linkman1) {
        this.linkman1 = linkman1 == null ? null : linkman1.trim();
    }

    public String getCaseNum1() {
        return caseNum1;
    }

    public void setCaseNum1(String caseNum1) {
        this.caseNum1 = caseNum1 == null ? null : caseNum1.trim();
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2 == null ? null : relation2.trim();
    }

    public String getLinkman2() {
        return linkman2;
    }

    public void setLinkman2(String linkman2) {
        this.linkman2 = linkman2 == null ? null : linkman2.trim();
    }

    public String getCaseNum2() {
        return caseNum2;
    }

    public void setCaseNum2(String caseNum2) {
        this.caseNum2 = caseNum2 == null ? null : caseNum2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}