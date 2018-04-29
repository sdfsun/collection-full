package com.kangde.quartz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kangde.quartz.mapper.TimerTaskMapper;
import com.kangde.quartz.model.TimerTaskModel;

/**
 * 初始化TimerTaskLoader,从数据库查询需要调度的任务
 * @author lisuo
 *
 */
public class InitTimerTaskLoader implements TimerTaskLoader{
	
	@Autowired
	private TimerTaskMapper timerTaskMapper;
	
	public List<TimerTaskModel> loadTasks() throws ClassNotFoundException {
		List<TimerTaskModel> tasks = timerTaskMapper.findAll();
		return tasks;
	}
}
