package com.kangde.log.model;

import java.util.Date;

import com.kangde.commons.model.BaseModel;

/**
 * 日志模型
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class LogModel extends BaseModel<String> {
	
	/** 调度状态：成功1 */
	public static final Integer STATE_SUCCESS = 1;
	/** 调度状态：失败0 */
	public static final Integer STATE_FAILURE = 0;
	
	/** 日志标题 */
	private String title;
	/** 日志状态 */
	private Integer state = STATE_SUCCESS;
	/** 耗时ms */
	private Long timeConsuming;
	/** 服务主机名称 */
	private String serverHostName;
	/** 服务主机地址 */
	private String serverHostAddr;
	/** 用户操作起始时间 */
	private Date startTime;
	/** 操作用户的IP地址 */
	private String remoteAddr;
	/** 操作的url */
	private String requestUrl;
	/** 操作提交的数据 */
	private String params;
	/** 操作用户代理信息 */
	private String userAgent;
	/** 操作用户ID */
	private String userId;
	/** 操作用户登录名称 */
	private String loginName;
	/** 操作用户中文名称 */
	private String userName;
	/** 异常信息（失败时的异常信息） */
	private String exceptionInfo;
	/** Http请求方式：GET,POST,PUT,DELETE */
	private String httpMethod;
	/** 后台请求方法名称 */
	private String methodName;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getTimeConsuming() {
		return timeConsuming;
	}
	public void setTimeConsuming(Long timeConsuming) {
		this.timeConsuming = timeConsuming;
	}
	public String getServerHostName() {
		return serverHostName;
	}
	public void setServerHostName(String serverHostName) {
		this.serverHostName = serverHostName;
	}
	public String getServerHostAddr() {
		return serverHostAddr;
	}
	public void setServerHostAddr(String serverHostAddr) {
		this.serverHostAddr = serverHostAddr;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
}
