package com.kangde.demo.timer;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kangde.commons.util.DateUtil;
import com.kangde.quartz.timer.BaseTimerTask;

/**
 * 案例测试Timer
 * @author lisuo
 *
 */
public class DemoTimerTask extends BaseTimerTask{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//获取参数
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String name = dataMap.getString("name");
		//执行业务...
		String date = DateUtil.date2Str(new Date(), DateUtil.DEFAULT_DATE_TIME_FORMAT);
		System.out.println("你好：【"+name+"】，当前时间是【"+date+"】");
	}

}
