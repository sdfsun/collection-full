package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BizModel;

@SuppressWarnings("serial")
public class CnoInStatistics extends BizModel<String>{

    private String cno;

    private String empId;

    private String empName;

    private String orgId;

    private Integer callInCount=0;

    private Integer callInConnectCount=0;

    private String callInConnectDuration="00:00:00";

    private String callInConnectDurationAvg="00:00:00";

    private String callInConnectRate;
    private String bridgeDurationDayAvg="00:00:00";
    private Date createtime;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getCallInCount() {
        return callInCount;
    }

    public void setCallInCount(Integer callInCount) {
        this.callInCount = callInCount;
    }

    public Integer getCallInConnectCount() {
        return callInConnectCount;
    }

    public void setCallInConnectCount(Integer callInConnectCount) {
        this.callInConnectCount = callInConnectCount;
    }

    public String getCallInConnectDuration() {
        return callInConnectDuration;
    }

    public void setCallInConnectDuration(String callInConnectDuration) {
        this.callInConnectDuration = callInConnectDuration == null ? null : callInConnectDuration.trim();
    }

    public String getCallInConnectDurationAvg() {
        return callInConnectDurationAvg;
    }

    public void setCallInConnectDurationAvg(String callInConnectDurationAvg) {
        this.callInConnectDurationAvg = callInConnectDurationAvg == null ? null : callInConnectDurationAvg.trim();
    }

    public String getCallInConnectRate() {
        return callInConnectRate;
    }

    public void setCallInConnectRate(String callInConnectRate) {
        this.callInConnectRate = callInConnectRate == null ? null : callInConnectRate.trim();
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