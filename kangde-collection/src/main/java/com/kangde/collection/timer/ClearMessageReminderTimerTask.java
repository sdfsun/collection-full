package com.kangde.collection.timer;

import java.util.concurrent.atomic.AtomicBoolean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kangde.collection.service.MessageReminderService;
import com.kangde.commons.web.filter.SpringUtil;
import com.kangde.quartz.timer.BaseTimerTask;

/**
 * 清除消息提醒,保留3天的数据
 */
public class ClearMessageReminderTimerTask extends BaseTimerTask{
	
	private MessageReminderService messageReminderService = SpringUtil.getBean(MessageReminderService.class);
	private static AtomicBoolean isWorking = new AtomicBoolean(false);
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try{
			if(!isWorking.get()){
				isWorking.set(true);
				messageReminderService.deleteMessageReminder(2);
			}else{
				if(log.isDebugEnabled()){
					log.debug("清除消息提醒定时任务 正在工作");
				}
			}
		}finally{
			isWorking.set(false);
		}
	}
	
	
}
