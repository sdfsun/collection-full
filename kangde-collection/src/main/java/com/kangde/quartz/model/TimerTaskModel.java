package com.kangde.quartz.model;

import com.kangde.commons.model.BizModel;

/**
 * 定时任务模型
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class TimerTaskModel extends BizModel<String> {

	/** 状态,0:暂停 */
	public static final Integer STATE_PAUSE = 0;
	/** 状态,1:启用 */
	public static final Integer STATE_NORMAL = 1;

	/** 作业名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 作业类 */
	private String jobClass;
	/** cron表达式 */
	private String cronExpression;
	/** 参数 */
	private String params;
	/** 当调度发生错误时通知的邮件地址 */
	private String errorEmail;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	
	
}
