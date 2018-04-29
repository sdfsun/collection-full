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

/**
 * 用于案件审批，xml解析
 * @author wcy
 * @date 2016年5月25日16:53:37
 *
 */
@SuppressWarnings("serial")
public class CaseDto extends CaseModel{

	private CaseBatchModel batchmodel;   // 批次表
	private ApproveRecordModel approverecordmodel; //留案表
	private VisitRecordModel visitrecordmodel;   //外访表
	private CasePaid casepaidmodel;   //支付表
	/**
	 * 员工表
	 */
	private EmployeeInfoModel employeeInfo;//员工表
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
	
	
	public CaseBatchModel getBatchmodel() {
		return batchmodel;
	}
	public void setBatchmodel(CaseBatchModel batchmodel) {
		this.batchmodel = batchmodel;
	}
	public ApproveRecordModel getApproverecordmodel() {
		return approverecordmodel;
	}
	public void setApproverecordmodel(ApproveRecordModel approverecordmodel) {
		this.approverecordmodel = approverecordmodel;
	}
	public VisitRecordModel getVisitrecordmodel() {
		return visitrecordmodel;
	}
	public void setVisitrecordmodel(VisitRecordModel visitrecordmodel) {
		this.visitrecordmodel = visitrecordmodel;
	}
	public CasePaid getCasepaidmodel() {
		return casepaidmodel;
	}
	public void setCasepaidmodel(CasePaid casepaidmodel) {
		this.casepaidmodel = casepaidmodel;
	}
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
	
	
	
}
