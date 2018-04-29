package com.kangde.collection.call;

/**
 * 外呼Model
 * @date 2016年6月28日
 * @author zl
 */

@SuppressWarnings("serial")
public class CallModel {
	
	/** 企业ID  */ 
	private String enterpriseId;
	
	/** 企业热线号码   */ 
	private String hotline;
	
	/** 坐席号码   */ 
	private String cno;
	
	/** 用户名  */
	private String userName;
	
	/** 密码  */
	private String pwd;
	
	/** 随机字符串  */
	private String seed;
	
	/** 电话唯一标识  */
	private String uniqueId;
	
	/** 呼叫类型  */
	private String callType;
	
	/** 任务Id  */
	private String taskId;
	
	/** 呼叫结果  */
	private String status;
	
	/** 查询条件  */
	private String title;
	
	/** 查询条件值    */
	private String value;
	
	/** 客户电话归属省份  */
	private String province;
	
	/** 客户电话归属城市  */
	private String city;
	
	/** 开始时间  */
	private String startTime;
	
	/** 结束时间  */
	private String endTime;
	
	/** 查询的时间类型  */
	private String timeType;
	
	/** 从第几条开始取  */
	private String start;
	
	/** 需要取出的数据条数  */
	private String limit;
	
	/** 用户自定义参数  */
	private String userField;
	
	/** 客户电话号码  */
	private String customerNumber;
	
	
	/** 客户侧外显号码  */
	private String clidLeftNumber;
	
	/** 是否异步调用  */
	private String sync;

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getUserField() {
		return userField;
	}

	public void setUserField(String userField) {
		this.userField = userField;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getClidLeftNumber() {
		return clidLeftNumber;
	}

	public void setClidLeftNumber(String clidLeftNumber) {
		this.clidLeftNumber = clidLeftNumber;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	
	
}
