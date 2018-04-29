package com.kangde.log;

import com.kangde.commons.util.CcUtil;
import com.kangde.commons.web.filter.SpringUtil;
import com.kangde.log.model.UserLogModel;
import com.kangde.log.service.UserLogService;

/**
 * 日志工具类
 * @author lisuo
 *
 */
public abstract class LogUtil {
	
	private static UserLogService userLogService = SpringUtil.getBean(UserLogService.class);
	
	/**
	 * 保存用户日志
	 * @param useLog
	 */
	public static void saveUserLog(final UserLogModel useLog){
		CcUtil.asyncRun(new Runnable() {
			@Override
			public void run() {
				userLogService.save(useLog);
			}
		});
	}
}
