package com.kangde.quartz.service.impl;

import org.springframework.stereotype.Service;

import com.kangde.commons.service.AbstractService;
import com.kangde.quartz.model.TimerTaskLogModel;
import com.kangde.quartz.service.TimerTaskLogService;

/**
 * 定时任务日志 Service 实现类
 * @author lisuo
 *
 */
@Service("timerTaskLogService")
public class TimerTaskLogServiceImpl extends AbstractService<TimerTaskLogModel, String> implements TimerTaskLogService {
}
