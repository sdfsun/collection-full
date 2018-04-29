package com.kangde.collection.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.sys.model.EmployeeInfoModel;

/**
 * 用于协催信息展示，xml解析
 * @author wcy
 * @date 2016年6月13日13:48:55
 *
 */
@SuppressWarnings("serial")
public class ControllerMessageDto extends PhoneRecordModel{

	/** 案件表*/
	private CaseModel casemodel;
	/** 批次表*/
	private CaseBatchModel batchmodel;
	/** 员工表*/
	private EmployeeInfoModel employeeInfoModel;
	
	private CasePaid casePaidModel;

	
	
	public CasePaid getCasePaidModel() {
		return casePaidModel;
	}

	public void setCasePaidModel(CasePaid casePaidModel) {
		this.casePaidModel = casePaidModel;
	}

	public CaseModel getCasemodel() {
		return casemodel;
	}

	public void setCasemodel(CaseModel casemodel) {
		this.casemodel = casemodel;
	}
	public CaseBatchModel getBatchmodel() {
		return batchmodel;
	}

	public void setBatchmodel(CaseBatchModel batchmodel) {
		this.batchmodel = batchmodel;
	}

	public EmployeeInfoModel getEmployeeInfoModel() {
		return employeeInfoModel;
	}

	public void setEmployeeInfoModel(EmployeeInfoModel employeeInfoModel) {
		this.employeeInfoModel = employeeInfoModel;
	}
	
}
