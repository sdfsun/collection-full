package com.kangde.collection.dto;

import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.AreaModel;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.model.OrganizationModel;

/**
 * 用于还款管理，xml解析
 * @author zhangyj
 * @date 2016年6月24日
 *
 */
@SuppressWarnings("serial")
public class CasePaidDto extends CasePaid{
	private CaseModel caseModel;
	private CaseBatchModel batchModel;   // 批次表
	private ApproveRecordModel approveRecordModel; //留案表
	private VisitRecordModel visitRecordModel;   //外访表
	private String actual_visit_time;
	private String visit_user;
	/**
	 * 员工表
	 */
	private EmployeeInfoModel employeeInfo;//员工表
	
	private OrganizationModel organization;
	/**
	 * 区域表
	 */
	private AreaModel area;
	/**
	 * 委托方表
	 */
	private EntrustModel entrustModel;
	/**
	 * 地址表
	 */
	private AddressModel addressModel;
	/** 是否有附件 */
	private int fzsize;
	/** 公司佣金 */
	private Double commission;
	
	public EmployeeInfoModel getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfoModel employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public AreaModel getArea() {
		return area;
	}
	public void setArea(AreaModel area) {
		this.area = area;
	}
	public AddressModel getAddressModel() {
		return addressModel;
	}
	public void setAddressModel(AddressModel addressModel) {
		this.addressModel = addressModel;
	}
	public EntrustModel getEntrustModel() {
		return entrustModel;
	}
	public void setEntrustModel(EntrustModel entrustModel) {
		this.entrustModel = entrustModel;
	}
	public CaseModel getCaseModel() {
		return caseModel;
	}
	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}
	public VisitRecordModel getVisitRecordModel() {
		return visitRecordModel;
	}
	public void setVisitRecordModel(VisitRecordModel visitRecordModel) {
		this.visitRecordModel = visitRecordModel;
	}
	public ApproveRecordModel getApproveRecordModel() {
		return approveRecordModel;
	}
	public void setApproveRecordModel(ApproveRecordModel approveRecordModel) {
		this.approveRecordModel = approveRecordModel;
	}
	public CaseBatchModel getBatchModel() {
		return batchModel;
	}
	public void setBatchModel(CaseBatchModel batchModel) {
		this.batchModel = batchModel;
	}
	public OrganizationModel getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}
	public int getFzsize() {
		return fzsize;
	}
	public void setFzsize(int fzsize) {
		this.fzsize = fzsize;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public String getActual_visit_time() {
		return actual_visit_time;
	}
	public void setActual_visit_time(String actual_visit_time) {
		this.actual_visit_time = actual_visit_time;
	}
	public String getVisit_user() {
		return visit_user;
	}
	public void setVisit_user(String visit_user) {
		this.visit_user = visit_user;
	}
	
	
	
	
}
