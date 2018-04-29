package com.kangde.log.model;

import com.kangde.commons.model.BaseModel;

/**
 * 日志模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class UserLogModel extends BaseModel<String> {

	/** 操作类型 */
	private String type;
	/** 用户ID */
	private String userId;
	/** 操作用户中文名称 */
	private String userName;
	/** 操作用户登录名称 */
	private String loginName;
	/** 操作内容 */
	private String operateContent;
	/** IP地址 */
	private String ipAddr;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
}
