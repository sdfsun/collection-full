package com.kangde.quartz.manager;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jfree.util.Log;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.kangde.commons.util.CcUtil;
import com.kangde.commons.util.EmailUtil;
import com.kangde.commons.util.IpUtil;
import com.kangde.quartz.model.TimerTaskLogModel;
import com.kangde.quartz.model.TimerTaskModel;
import com.kangde.quartz.service.TimerTaskLogService;

/**
 * 调度任务日志监听器
 * @author lisuo
 *
 */
public class TimerTaskLogListener implements JobListener {
	
	@Autowired
	private TimerTaskLogService timerTaskLogService;
	
	@Override
	public String getName() {
		return "timerTaskLogListener";
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		
	}
	// 任务开始前
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
	}
	
	// 任务结束后
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException ex) {
		JobDetail detail = context.getJobDetail();
		TimerTaskLogModel taskLog = new TimerTaskLogModel();
		Date startTime = context.getFireTime();
		taskLog.setTimeConsuming(context.getJobRunTime());
		taskLog.setTaskName(detail.getKey().getName());
		taskLog.setStartTime(startTime);
		taskLog.setPreviousFireTime(context.getPreviousFireTime());
		taskLog.setNextFireTime(context.getNextFireTime());
		taskLog.setServerHostAddr(IpUtil.getHostAddress());
		taskLog.setServerHostName(IpUtil.getHostName());
		
		if(ex!=null){
			taskLog.setState(TimerTaskLogModel.STATE_FAILURE);
			//只保存异常的起源信息
			Throwable cause = ExceptionUtils.getRootCause(ex);
			String stackTrace = ExceptionUtils.getStackTrace(cause);
			taskLog.setExceptionInfo(stackTrace);
			//发送错误邮件
			JobDataMap dataMap = detail.getJobDataMap();
			TimerTaskModel timerTask = (TimerTaskModel) dataMap.get(JobBean.THIS_TIMER_TASK);
			if(timerTask!=null){
				//邮件地址不是空
				if(StringUtils.isNotBlank(timerTask.getErrorEmail())){
					String[] emails = StringUtils.split(timerTask.getErrorEmail(), ",");
					CcUtil.asyncRun(new Runnable() {
						@Override
						public void run() {
							EmailUtil.sendEmail("【"+timerTask.getName()+"】调度发生错误", stackTrace, emails);
						}
					});
				}
			}
		}
		saveTaskLog(taskLog);
	}
	
	//保存日志
	private void saveTaskLog(TimerTaskLogModel taskLog){
		CcUtil.asyncRun(new Runnable() {
			@Override
			public void run() {
				try{
					timerTaskLogService.save(taskLog);
				}catch(Exception e){
					e.printStackTrace();
					Log.error(e);
				}
			}
		});
	}

}
