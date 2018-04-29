package com.kangde.collection.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;

/**
 * 外访 模型
 * 
 * @author wcy
 *
 */
@SuppressWarnings("serial")
public class VisitRecordModel extends BizModel<String> {

	/** 地址id */
	private String addressId;
	/** 案件id */
	private String caseId;
	/** 外访期次 */
	private Integer num;
	/** 省 */
	private String areaId1;
	/** 市 */
	private String areaId2;
	/** 区县 */
	private String areaId3;
	/** 外访原因 字典表 */
	private String reasonTypeId;
	/** 姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 年龄 */
	private Integer age;
	/** 要求 */
	private String require;
	/** 申请原因 */
	private String applyReason;
	/** 状态 0待审批 1审批通过 2审批不通过 */
	private Integer approveState;
	/** 审批意见 */
	private String approveOpinion;
	/** 备注 申请原因 */
	private String remark;
	/** 外访报告 */
	private String visitReport;
	/** 外访时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date visitTime;
	/** 返回时间 */
	private Date backTime;
	/** 外访人 */
	private String visitUser;
	/** 外访人 */
	private String visitUserId;
	/** 外访地址 */
	private String visitAddress;
	/** 创建人 */
	private String createEmpId;
	/** 创建时间 */
	private Date createTime;
	/** 申请人 */
	private String applyEmpId;
	/** 申请时间 */
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date applyTime;
	/** 审批状态，数据库无字段 */
	private Integer vrState;
	/** 是否有效 */
	private String isEffective;
	/** 实际外访时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualVisitTime;

	private String visitUserIds;
	private String visitUsers;
	/** 外访结果   */
	private String visitState;

	public String getVisitState() {
		return visitState;
	}

	public void setVisitState(String visitState) {
		this.visitState = visitState;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public String getReasonTypeId() {
		return reasonTypeId;
	}

	public void setReasonTypeId(String reasonTypeId) {
		this.reasonTypeId = reasonTypeId;
	}

	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Integer getApproveState() {
		return approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVisitReport() {
		return visitReport;
	}

	public void setVisitReport(String visitReport) {
		this.visitReport = visitReport;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getVisitUser() {
		return visitUser;
	}

	public void setVisitUser(String visitUser) {
		this.visitUser = visitUser;
	}

	public String getVisitAddress() {
		return visitAddress;
	}

	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}

	public String getCreateEmpId() {
		return createEmpId;
	}

	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApplyEmpId() {
		return applyEmpId;
	}

	public void setApplyEmpId(String applyEmpId) {
		this.applyEmpId = applyEmpId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getVrState() {
		return vrState;
	}

	public void setVrState(Integer vrState) {
		this.vrState = vrState;
	}

	public Date getActualVisitTime() {
		return actualVisitTime;
	}

	public void setActualVisitTime(Date actualVisitTime) {
		this.actualVisitTime = actualVisitTime;
	}

	public String getVisitUserId() {
		return visitUserId;
	}

	public void setVisitUserId(String visitUserId) {
		this.visitUserId = visitUserId;
	}

	public String getVisitUserIds() {
		return visitUserIds;
	}

	public void setVisitUserIds(String visitUserIds) {
		this.visitUserIds = visitUserIds;
	}

	public String getVisitUsers() {
		return visitUsers;
	}

	public void setVisitUsers(String visitUsers) {
		this.visitUsers = visitUsers;
	}

}