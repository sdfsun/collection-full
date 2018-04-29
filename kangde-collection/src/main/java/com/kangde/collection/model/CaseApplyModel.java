package com.kangde.collection.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.util.DateUtil;

/**
 * 待审批协催
 * 
 * @author wcy
 * @date 2016年7月19日11:37:30
 */
@SuppressWarnings("serial")
public class CaseApplyModel extends BaseModel<String>{
	/** 申请状态 -2协催申请 -1被撤销 0待核查 1已完成*/
	private int state;
	/**申请类型 对应字典表 1信函 2外访 3投诉预警 4户籍查询 5法院协催 6社保查询 7公安协催 8争议咨询 9移动查询 10主管协催 11客服咨询 12电信查询 13短信申请 14退件查询 */
	private int applyType;
	/**申请内容 */
	private String applyContent;
	/** 对应地址表 地址类别*/
	private String chCat1;
	/** 信函模板 demo、CGI律师函、CGI追偿通知书、浦发卡中心信函模板*/
	private String chCat2;
	/** 地址表id*/
	private String addressId;
	/** 信函状态 1或空正常 -1退信 */
	private int msgState;
	/** 案件ID*/
	private String caseId;
	/** 协催内容*/
	private String hurryContent;
	/** 申请人*/
	private String applyUser;
	/** 申请人姓名*/
	private String applyUserName;
	/** 协催人*/
	private String surUser;
	/** 申请时间*/
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date appTime;
	/** 协催时间*/
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date surTime;
	/** 备注*/
	private String remark;
	/** 联系人  注：广东-陈锦龙*/
	private String contactUser;
	/** 地址*/
	private String address;
	/** 审批意见*/
	private String approvalOpinion;
	
	private String entrustId;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getApplyType() {
		return applyType;
	}
	public void setApplyType(int applyType) {
		this.applyType = applyType;
	}
	public String getApplyContent() {
		return applyContent;
	}
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	public String getChCat1() {
		return chCat1;
	}
	public void setChCat1(String chCat1) {
		this.chCat1 = chCat1;
	}
	public String getChCat2() {
		return chCat2;
	}
	public void setChCat2(String chCat2) {
		this.chCat2 = chCat2;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public int getMsgState() {
		return msgState;
	}
	public void setMsgState(int msgState) {
		this.msgState = msgState;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getHurryContent() {
		return hurryContent;
	}
	public void setHurryContent(String hurryContent) {
		this.hurryContent = hurryContent;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getSurUser() {
		return surUser;
	}
	public void setSurUser(String surUser) {
		this.surUser = surUser;
	}
	public Date getAppTime() {
		return appTime;
	}
	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}
	public Date getSurTime() {
		return surTime;
	}
	public void setSurTime(Date surTime) {
		this.surTime = surTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	
}