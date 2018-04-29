package com.kangde.quartz.model;

import java.util.Date;

import com.kangde.commons.model.BaseModel;

/**
 * 定时任务日志模型
 * @author lisuo
 */
@SuppressWarnings("serial")
public class TimerTaskLogModel extends BaseModel<String> {

	/** 调度状态：成功1 */
	public static final Integer STATE_SUCCESS = 1;
	/** 调度状态：失败0 */
	public static final Integer STATE_FAILURE = 0;
	/** 调度任务名称 */
	private String taskName;
	/** 调度状态 */
	private Integer state = STATE_SUCCESS;
	/** 耗时ms */
	private Long timeConsuming;
	/** 服务主机名称 */
	private String serverHostName;
	/** 服务主机地址 */
	private String serverHostAddr;
	/** 异常信息（调度失败时的异常信息） */
	private String exceptionInfo;
	/** 本次调度时间 */
	private Date startTime;
	/** 调度任务上一次触发时间 */
	private Date previousFireTime;
	/** 调度任务下一次触发时间 */
	private Date nextFireTime;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

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

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

}
