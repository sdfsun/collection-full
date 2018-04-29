package com.kangde.quartz.manager;

import static com.kangde.quartz.manager.JobBean.DEFAULT_GROUP;
import static com.kangde.quartz.manager.JobBean.buildJobBean;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.InitializingBean;

import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.BaseException;
import com.kangde.quartz.model.TimerTaskModel;

/**
 * 定时任务调度器管理
 * @author lisuo
 *
 */
public class TimerTaskSchedulerManager implements InitializingBean {
	
	//是否开启定时调度功能
	private static boolean enable = CoreConst.getBoolean("quartz.enable");
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	/** 调度器 */
	private Scheduler scheduler;

	/** 作业加载器 */
	private List<TimerTaskLoader> timerTaskLoaders;
	
	/** Job 监听 */
	private List<JobListener> jobListeners;
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void setTimerTaskLoaders(List<TimerTaskLoader> timerTaskLoaders) {
		this.timerTaskLoaders = timerTaskLoaders;
	}

	public void setJobListeners(List<JobListener> jobListeners) {
		this.jobListeners = jobListeners;
	}

	// 调度初始化入口
	public void afterPropertiesSet() throws Exception {
		//开启quartz定时调度,才进行扫描调度任务
		if(enable){
			// 初始化Quartz监听
			if (CollectionUtils.isNotEmpty(jobListeners)) {
				for (JobListener listener : jobListeners) {
					this.scheduler.getListenerManager().addJobListener(listener);
				}
			}
			//初始化调度信息
			if (CollectionUtils.isNotEmpty(timerTaskLoaders)) {
				for (TimerTaskLoader loader : this.timerTaskLoaders) {
					List<TimerTaskModel> tasks = loader.loadTasks();
					if (CollectionUtils.isNotEmpty(tasks)) {
						for (TimerTaskModel task:tasks) {
							saveOrUpdateJob(task);
						}
					}
				}
			}
		}
	}
	
	//添加触发器
	private void addTrigger(Trigger trigger,Integer state) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {}
		try {
			if (oldTrigger == null) {
				scheduler.scheduleJob(trigger);
				if(TimerTaskModel.STATE_PAUSE == state){
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				updateTrigger(trigger,state);
			}
		} catch (SchedulerException e) {
			logger.error("Try to add trigger : " + trigger + "  cause error : ", e);
		}
	}
	
	//更新触发器
	private void updateTrigger(Trigger trigger,Integer state) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {}
		try {
			if (oldTrigger != null) {
				scheduler.rescheduleJob(trigger.getKey(), trigger);
				if(TimerTaskModel.STATE_PAUSE == state){
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				logger.warn("Can't update trigger : " + trigger);
			}
		} catch (SchedulerException e) {
			logger.error("Try to update trigger : " + trigger + ", the old trigger is : "
					+ (oldTrigger == null ? "null" : oldTrigger.toString()) + " cause error : ", e);
		}
	}

	//添加Job
	private void addJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {}
		try {
			if (oldJobDetail == null) {
				this.scheduler.addJob(jobDetail, true);
			} else {
				updateJobDetail(jobDetail);
			}
		} catch (Exception e) {
			logger.error("Try to add jobDetail : " + jobDetail + ", the old jobDetail is : "
					+ (oldJobDetail == null ? "null" : oldJobDetail.toString()) + " cause error : ", e);
		}
	}

	//更新job
	private void updateJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {}
		try {
			if (oldJobDetail != null) {
				this.scheduler.addJob(jobDetail, true);
			} else {
				logger.warn("Can't update JobDetail : " + jobDetail);
			}
		} catch (SchedulerException e) {
			logger.error("Try to update JobDetail : " + jobDetail + ", the old JobDetail is : "
					+ (oldJobDetail == null ? "null" : oldJobDetail.toString()) + " cause error : ", e);
		}
	}

	/**
	 * 暂停任务
	 * @param timerTask
	 * @return
	 */
	public boolean pauseJob(TimerTaskModel timerTask) {
		if(enable){
			try {
				JobKey jobKey = JobKey.jobKey(timerTask.getName(), DEFAULT_GROUP);
				scheduler.pauseJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to stop Job cause error : ", e);
			}
		} else{
			throw new BaseException("没有开启quartz定时调度");
		}
		
		return false;
	}

	/**
	 * 恢复任务
	 * @param timerTask
	 * @return
	 */
	public boolean resumeJob(TimerTaskModel timerTask) {
		if(enable){
			try {
				JobKey jobKey = JobKey.jobKey(timerTask.getName(), DEFAULT_GROUP);
				scheduler.resumeJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : ", e);
			}
		} else{
			throw new BaseException("没有开启quartz定时调度");
		}
		return false;
	}

	/**
	 * 立即运行任务
	 * @param timerTask
	 * @return
	 */
	public boolean runJob(TimerTaskModel timerTask) {
		if(enable){
			try {
				JobKey jobKey = JobKey.jobKey(timerTask.getName(), DEFAULT_GROUP);
				scheduler.triggerJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : ", e);
			}
		} else{
			throw new BaseException("没有开启quartz定时调度");
		}
		return false;
	}
	
	/**
	 * 删除任务
	 * @param timerTask
	 * @return
	 */
	public boolean deleteJob(TimerTaskModel timerTask){
		if(enable){
			try {
				JobKey jobKey = JobKey.jobKey(timerTask.getName(), DEFAULT_GROUP);
				scheduler.deleteJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : ", e);
			}
		} else{
			throw new BaseException("没有开启quartz定时调度");
		}
		return false;
	}

	/**
	 * 新增任务||修改任务
	 * @param timerTask
	 * @return
	 */
	public boolean saveOrUpdateJob(TimerTaskModel timerTask) {
		if(enable){
			try {
				JobBean jobBean = buildJobBean(timerTask);
				this.addJobDetail(jobBean.getJobDetail());
				this.addTrigger(jobBean.getTrigger(),jobBean.getTimerTask().getState());
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : ", e);
			}
		} else{
			throw new BaseException("没有开启quartz定时调度");
		}
		return false;
	}
	
}

