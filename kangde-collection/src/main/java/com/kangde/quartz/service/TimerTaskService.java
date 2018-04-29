package com.kangde.quartz.service;

import com.kangde.commons.service.BaseService;
import com.kangde.quartz.model.TimerTaskModel;

/**
 * 定时任务 Service
 * @author lisuo
 *
 */
public interface TimerTaskService extends BaseService<TimerTaskModel, String> {

	/**
	 * 立即执行一次当前Job
	 * @param model
	 */
	public void runJob(TimerTaskModel model);
	
}
