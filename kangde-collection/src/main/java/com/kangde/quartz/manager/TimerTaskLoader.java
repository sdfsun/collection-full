package com.kangde.quartz.manager;

import java.util.List;

import com.kangde.quartz.model.TimerTaskModel;

/**
 * 作业加载器
 * @author lisuo
 *
 */
public interface TimerTaskLoader {
	
	/**
	 * 加载JobBean
	 * @return List<TimerTaskModel>
	 * @throws Exception
	 */
	public List<TimerTaskModel> loadTasks() throws Exception;
}
