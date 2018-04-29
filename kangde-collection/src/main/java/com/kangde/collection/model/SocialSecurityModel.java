package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BizModel;


/**
 * 社保表Model
 * @date 2016年7月21日 
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class SocialSecurityModel extends BizModel<String>{
   
	/** 案件协催申请id  */
    private String caseApplyId;
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
    private Date lastRenewDate;
    /** 备注  */
    private String remark;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId == null ? null : caseApplyId.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitPhone1() {
        return unitPhone1;
    }

    public void setUnitPhone1(String unitPhone1) {
        this.unitPhone1 = unitPhone1 == null ? null : unitPhone1.trim();
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress == null ? null : unitAddress.trim();
    }

    public String getUnitPhone2() {
        return unitPhone2;
    }

    public void setUnitPhone2(String unitPhone2) {
        this.unitPhone2 = unitPhone2 == null ? null : unitPhone2.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getLastRenewDate() {
        return lastRenewDate;
    }

    public void setLastRenewDate(Date lastRenewDate) {
        this.lastRenewDate = lastRenewDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}