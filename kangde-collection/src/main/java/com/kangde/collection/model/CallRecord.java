package com.kangde.collection.model;

import java.math.BigDecimal;
import java.util.Date;

import com.kangde.commons.model.BizModel;
/**
 * 电催统计
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class CallRecord extends BizModel<String>{

    private String uniqueId;

    private String customerNumber;

    private String customerProvince;

    private String customerCity;

    private String numberTrunk;

    private String queueName;

    private String cno;

    private String clientNumber;

    private String callStatus;

    private Date startTime;

    private String bridgeTime;

    private String bridgeDuration;

    private BigDecimal cost;

    private String totalDuration;

    private String recordFile;

    private String inCaseLib;

    private String score;

    private String callType;

    private String taskName;

    private String endReason;

    private String userField;

    private String sipCause;

    private String mark;

    private Date answerTime;

    private String hotline;

    private String ivrName;

    private String comment;
    
    private String name;
	private String emId;
	private String orgId;
	private Integer caseCount=0;
	private Integer crCount =0;
	private Integer status =0;
	private String averageDuration;
	private String rate;
	private String averageTime;

	public Integer getCrCount() {
		return crCount;
	}

	public void setCrCount(Integer crCount) {
		this.crCount = crCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAverageDuration() {
		return averageDuration;
	}

	public void setAverageDuration(String averageDuration) {
		this.averageDuration = averageDuration;
	}


	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmId() {
		return emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

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

	public String getNumberTrunk() {
		return numberTrunk;
	}

	public void setNumberTrunk(String numberTrunk) {
		this.numberTrunk = numberTrunk;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getBridgeTime() {
		return bridgeTime;
	}

	public void setBridgeTime(String bridgeTime) {
		this.bridgeTime = bridgeTime;
	}

	public String getBridgeDuration() {
		return bridgeDuration;
	}

	public void setBridgeDuration(String bridgeDuration) {
		this.bridgeDuration = bridgeDuration;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getRecordFile() {
		return recordFile;
	}

	public void setRecordFile(String recordFile) {
		this.recordFile = recordFile;
	}

	public String getInCaseLib() {
		return inCaseLib;
	}

	public void setInCaseLib(String inCaseLib) {
		this.inCaseLib = inCaseLib;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public String getUserField() {
		return userField;
	}

	public void setUserField(String userField) {
		this.userField = userField;
	}

	public String getSipCause() {
		return sipCause;
	}

	public void setSipCause(String sipCause) {
		this.sipCause = sipCause;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getIvrName() {
		return ivrName;
	}

	public void setIvrName(String ivrName) {
		this.ivrName = ivrName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	public Integer getCaseCount() {
		return caseCount;
	}

	public void setCaseCount(Integer caseCount) {
		this.caseCount = caseCount;
	}


}