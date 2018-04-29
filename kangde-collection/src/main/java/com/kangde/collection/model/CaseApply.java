package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BizModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;

/**
 * 案件协催申请
 * 
 * @author lidengwen
 * @date 2016年7月16日 下午5:17:02
 */
public class CaseApply extends BizModel<String> {
	private static final long serialVersionUID = -3536214324595424294L;

	/** 申请状态 -2协催申请 -1被撤销 0待核查 1已完成 */
	private Integer state;
	/**
	 * 申请类型 对应字典表 1信函 2外访 3投诉预警 4户籍查询 5法院协催 6社保查询 7公安协催 8争议咨询 9移动查询 10主管协催
	 * 11客服咨询 12电信查询 13短信申请 14退件查询
	 */
	private Integer applyType;

	/** 申请内容 */
	private String applyContent;

	/** 对应地址表 地址类别 */
	private String chCat1;

	/** 信函模板 demo、CGI律师函、CGI追偿通知书、浦发卡中心信函模板 */
	private String chCat2;

	/** 地址表id */
	private String addressId;

	/** 信函状态 1或空正常 -1退信 */
	private Integer msgState;

	/** 案件ID */
	private String caseId;

	private CaseModel caseModel;

	/** 协催内容 */
	private String hurryContent;
	/** 申请人id */
	private String applyUser;
	/** 申请人名称 */
	private String applyUserName;
	/** 申请人 */
	private EmployeeInfoModel employeeInfo;

	/** 协催人 */
	private String surUser;
	/** 协催人 */
	private String surUserName;

	/** 申请时间 */
	private Date appTime;

	/** 协催时间 */
	private Date surTime;
	/** 邮寄时间 */
	private Date mailTime;

	/** 备注 */
	private String remark;

	/** 联系人 注：广东-陈锦龙 */
	private String contactUser;

	/** 地址 */
	private String address;

	private Integer chCount;

	private EntrustModel entrustModel;
	/** 审批意见 */
	private String approvalOpinion;
	/** 为协催提供 */
	private String isNull;
	
	
	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent == null ? null : applyContent.trim();
	}

	public String getChCat1() {
		return chCat1;
	}

	public void setChCat1(String chCat1) {
		this.chCat1 = chCat1 == null ? null : chCat1.trim();
	}

	public String getChCat2() {
		return chCat2;
	}

	public void setChCat2(String chCat2) {
		this.chCat2 = chCat2 == null ? null : chCat2.trim();
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId == null ? null : addressId.trim();
	}

	public Integer getMsgState() {
		return msgState;
	}

	public void setMsgState(Integer msgState) {
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
		this.hurryContent = hurryContent == null ? null : hurryContent.trim();
	}

	public EmployeeInfoModel getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfoModel employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getSurUser() {
		return surUser;
	}

	public void setSurUser(String surUser) {
		this.surUser = surUser == null ? null : surUser.trim();
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
		this.remark = remark == null ? null : remark.trim();
	}

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser == null ? null : contactUser.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getChCount() {
		return chCount;
	}

	public void setChCount(Integer chCount) {
		this.chCount = chCount;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public CaseModel getCaseModel() {
		return caseModel;
	}

	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}

	public String getSurUserName() {
		return surUserName;
	}

	public void setSurUserName(String surUserName) {
		this.surUserName = surUserName;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public Date getMailTime() {
		return mailTime;
	}

	public void setMailTime(Date mailTime) {
		this.mailTime = mailTime;
	}

	public EntrustModel getEntrustModel() {
		return entrustModel;
	}

	public void setEntrustModel(EntrustModel entrustModel) {
		this.entrustModel = entrustModel;
	}
	
	
}