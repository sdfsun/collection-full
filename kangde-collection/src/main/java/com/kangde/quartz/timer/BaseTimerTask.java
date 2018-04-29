package com.kangde.quartz.timer;

import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 可以被调度的定时任务
 * @author lisuo
 *
 */
public abstract class BaseTimerTask extends QuartzJobBean{
	
	/** 日志 */
	protected Logger log = Logger.getLogger(this.getClass());

}
