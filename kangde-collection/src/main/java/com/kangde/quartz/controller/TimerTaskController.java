package com.kangde.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.quartz.model.TimerTaskModel;
import com.kangde.quartz.service.TimerTaskService;

/**
 * 定时任务 Controller
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/timerTask")
public class TimerTaskController extends RestfulUrlController<TimerTaskModel,String>{
	
	@Autowired
	private TimerTaskService timerTaskService;
	
	@Override
	public SearchResult<TimerTaskModel> queryForPage() {
		ParamCondition condition = parseCondition("name");
		return super.queryForPage(condition);
	}
	
	//立即调度一次任务
	@RequestMapping(value="runJob",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult runJob(final TimerTaskModel model) {
		timerTaskService.runJob(model);
		return AjaxResult.success(getSuccessMessage());
	}
	
	@Override
	protected String getBaseName() {
		return "sys/timerTask/";
	}
}
