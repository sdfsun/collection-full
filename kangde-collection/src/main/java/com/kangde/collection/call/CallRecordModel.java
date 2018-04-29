package com.kangde.collection.call;

/**
 * 外呼记录Model
 * @date 2016年6月28日
 * @author zl
 */

@SuppressWarnings("serial")
public class CallRecordModel {
	
	/** 电话唯一标识  */ 
	private String uniqueId;
	
	/** 客户号码   */ 
	private String customerNumber;
	
	/** 客户号码归属省份   */ 
	private String customerProvince;
	
	/** 客户号码归属城市  */
	private String customerCity;
	
	/** 中继号码  */
	private String numberTrunk;
	
	/** 队列号  */
	private String queueName;
	
	/** 座席工号  */
	private String cno;
	
	/** 座席电话  */
	private String clientNumber;
	
	/** 外呼结果  */
	private String status;
	
	/** 电话进入系统时间  */
	private String startTime;
	
	/** 客户接听时间  */
	private String bridgeTime;
	
	/** 通话时长  */
	private String bridgeDuration;
	
	/** 费用(元)  */
	private String cost;
	
	/** 总时长  */
	private String totalDuration;
	
	/** 录音文件的最终地址及录音文件名  */
	private String recordFile;
	
	/** 是否在案例库  */
	private String inCaseLib;
	
	/** 录音质检打分，0为未打分  */
	private String score;
	
	/** 呼叫类型  */
	private String callType;
	
	/** 备注  */
	private String comment;
	
	/** 外呼任务名称  */
	private String taskName;
	
	/** 结束原因  */
	private String endReason;
	
	/** 用户自定义参数  */
	private String userField;
	
	/** 呼叫结果  */
	private String sipCause;
	

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
	

	
}
