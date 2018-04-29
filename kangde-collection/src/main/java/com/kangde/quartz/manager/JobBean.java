package com.kangde.quartz.manager;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.alibaba.fastjson.JSON;
import com.kangde.quartz.model.TimerTaskModel;

/**
 * 可以被执行的Job实例
 * @author lisuo
 *
 */
class JobBean {
	
	public static final String DEFAULT_GROUP = Scheduler.DEFAULT_GROUP;
	public static final String THIS_TIMER_TASK = "JobBean_THIS_TIMER_TASK";
	
	public JobBean(Trigger trigger, JobDetail jobDetail, TimerTaskModel timerTask) {
		this.trigger = trigger;
		this.jobDetail = jobDetail;
		this.timerTask = timerTask;
	}

	/** 触发器 */
	private Trigger trigger;
	/** 调度具体信息 */
	private JobDetail jobDetail;
	/** 定时任务 */
	private TimerTaskModel timerTask;

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public TimerTaskModel getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(TimerTaskModel timerTask) {
		this.timerTask = timerTask;
	}
	
	/**
	 * 创建JobBean
	 * @param task 定时任务模型
	 * @return JobBean 实例
	 * @throws ClassNotFoundException
	 */
	public static JobBean buildJobBean(TimerTaskModel task) throws ClassNotFoundException {
		//构建Timer参数
		JobDataMap jobDataMap = new JobDataMap();
		if(StringUtils.isNotBlank(task.getParams())){
			Map<String, ?> paramsMap = JSON.parseObject(task.getParams());
			jobDataMap.putAll(paramsMap);
		}
		jobDataMap.put(THIS_TIMER_TASK, task);
		//构建可进行调度的Job
		@SuppressWarnings("unchecked")
		Class<Job> jobClass = (Class<Job>) Class.forName(task.getJobClass());
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(task.getName(), DEFAULT_GROUP)
				.withDescription(task.getDescription())
				.storeDurably(true)
				.usingJobData(jobDataMap)
				.build();
		//构建触发器
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()))
				.withIdentity(task.getName(), DEFAULT_GROUP)
				.withDescription(task.getDescription())
				.forJob(jobDetail)
				.usingJobData(jobDataMap)
				.build();
		return new JobBean(trigger, jobDetail, task);
	}
	
}
