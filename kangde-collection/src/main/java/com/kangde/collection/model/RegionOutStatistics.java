package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BizModel;

@SuppressWarnings("serial")
public class RegionOutStatistics extends BizModel<String>{

    private String customerProvince;

    private String customerCity;

    private String cno;

    private String empId;

    private String empName;

    private String orgId;

    private String bridgeDurationTotal;

    private Integer callOutCount=0;

    private Integer callOutConnectCount=0;
    
    private String callOutConnectDuration;

    private String callOutConnectDurationAvg;

    private String callOutConnectRate;

    private String bridgeDurationDayAvg;
    
    private Date createtime;

	public String getCustomerProvince() {
		return customerProvince;
	}

	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBridgeDurationTotal() {
		return bridgeDurationTotal;
	}

	public void setBridgeDurationTotal(String bridgeDurationTotal) {
		this.bridgeDurationTotal = bridgeDurationTotal;
	}

	public Integer getCallOutCount() {
		return callOutCount;
	}

	public void setCallOutCount(Integer callOutCount) {
		this.callOutCount = callOutCount;
	}

	public Integer getCallOutConnectCount() {
		return callOutConnectCount;
	}

	public void setCallOutConnectCount(Integer callOutConnectCount) {
		this.callOutConnectCount = callOutConnectCount;
	}

	public String getCallOutConnectDuration() {
		return callOutConnectDuration;
	}

	public void setCallOutConnectDuration(String callOutConnectDuration) {
		this.callOutConnectDuration = callOutConnectDuration;
	}

	public String getCallOutConnectDurationAvg() {
		return callOutConnectDurationAvg;
	}

	public void setCallOutConnectDurationAvg(String callOutConnectDurationAvg) {
		this.callOutConnectDurationAvg = callOutConnectDurationAvg;
	}

	public String getCallOutConnectRate() {
		return callOutConnectRate;
	}

	public void setCallOutConnectRate(String callOutConnectRate) {
		this.callOutConnectRate = callOutConnectRate;
	}

	public String getBridgeDurationDayAvg() {
		return bridgeDurationDayAvg;
	}

	public void setBridgeDurationDayAvg(String bridgeDurationDayAvg) {
		this.bridgeDurationDayAvg = bridgeDurationDayAvg;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}