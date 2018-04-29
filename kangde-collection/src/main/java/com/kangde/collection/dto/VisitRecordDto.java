package com.kangde.collection.dto;

import java.math.BigDecimal;

import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.AreaModel;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;

/**
 * 用于风控管理，xml解析
 * 
 * @author zhangyj
 * @date 2016年6月23日
 *
 */
@SuppressWarnings("serial")
public class VisitRecordDto extends VisitRecordModel {

	/**
	 * 案件表
	 */
	private CaseModel caseModel;
	/**
	 * 员工表
	 */
	private EmployeeInfoModel employeeInfo;
	private EmployeeInfoModel employeeInfo1;
	/**
	 * /** 区域表
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

	private CaseBatchModel batchModel;
	/** 已还款金额 */
	private BigDecimal paidNum;
	/** 地址类别 */
	private String adrCat;
	/** 已完成 外访是否有还款记录 */
	private int countSize;
	
	public String getAdrCat() {
		return adrCat;
	}

	public void setAdrCat(String adrCat) {
		this.adrCat = adrCat;
	}

	public BigDecimal getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(BigDecimal paidNum) {
		this.paidNum = paidNum;
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

	public CaseModel getCaseModel() {
		return caseModel;
	}

	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}

	public CaseBatchModel getBatchModel() {
		return batchModel;
	}

	public void setBatchModel(CaseBatchModel batchModel) {
		this.batchModel = batchModel;
	}

	public EmployeeInfoModel getEmployeeInfo1() {
		return employeeInfo1;
	}

	public void setEmployeeInfo1(EmployeeInfoModel employeeInfo1) {
		this.employeeInfo1 = employeeInfo1;
	}

	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

}
