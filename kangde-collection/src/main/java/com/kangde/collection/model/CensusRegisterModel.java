package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;

/**
 * 户籍表Model
 * @date 2016年7月21日 
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class CensusRegisterModel extends BizModel<String>{
	
	/**  案件协催申请id   */
    private String caseApplyId;
    /**  户籍地址  */
    private String crAddress;
    /**  住址  */
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
    /**  手机  */
    private String mobile;
    /**  关系1  */
    private String relation1;
    /**  同户人员1  */
    private String personnel1;
    /**  证件号1  */
    private String caseNum1;
    /**  关系2  */
    private String relation2;
    /**  同户人员2  */
    private String personnel2;
    /**  证件号2  */
    private String caseNum2;
    /**  联系人状态 0未知 1有效 2无效  */
    private Integer status;
    /**  备注  */
    private String remark;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId == null ? null : caseApplyId.trim();
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
        this.mate = mate == null ? null : mate.trim();
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture == null ? null : culture.trim();
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone == null ? null : familyPhone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1 == null ? null : relation1.trim();
    }

    public String getPersonnel1() {
        return personnel1;
    }

    public void setPersonnel1(String personnel1) {
        this.personnel1 = personnel1 == null ? null : personnel1.trim();
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

    public String getPersonnel2() {
        return personnel2;
    }

    public void setPersonnel2(String personnel2) {
        this.personnel2 = personnel2 == null ? null : personnel2.trim();
    }

    public String getCaseNum2() {
        return caseNum2;
    }

    public void setCaseNum2(String caseNum2) {
        this.caseNum2 = caseNum2 == null ? null : caseNum2.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }
}