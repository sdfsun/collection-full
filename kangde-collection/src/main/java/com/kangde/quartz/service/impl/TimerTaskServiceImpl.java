package com.kangde.quartz.service.impl;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.TypeUtils;

import com.alibaba.fastjson.JSONObject;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.ValidateUtil;
import com.kangde.quartz.manager.TimerTaskSchedulerManager;
import com.kangde.quartz.model.TimerTaskModel;
import com.kangde.quartz.service.TimerTaskService;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 定时任务 Service 实现类
 * @author lisuo
 *
 */
@Service("timerTaskService")
public class TimerTaskServiceImpl extends AbstractService<TimerTaskModel, String> implements TimerTaskService {
	
	@Lazy
	@Autowired(required=false)
	private TimerTaskSchedulerManager timerTaskSchedulerManager;
	
	private void prepareSaveOrUpdate(TimerTaskModel model){
		ReflectUtil.trimFields(model, "jobClass","params","cronExpression","name","description","errorEmail");
		//errorEmail校验
		if(StringUtils.isNotBlank(model.getErrorEmail())){
			String[] emails = StringUtils.split(model.getErrorEmail(), ",");
			for(String email:emails){
				if(!ValidateUtil.validateEmail(email)){
					throw new ServiceException("Email["+email+"]不是一个合法的邮箱");
				}
			}
		}
		//调度类校验
		String jobClass = model.getJobClass();
		if(StringUtils.isBlank(jobClass)){
			throw new ServiceException("JobClass不能为空");
		}
		try {
			Class<?> clazz = Class.forName(jobClass);
			if(!TypeUtils.isAssignable(Job.class, clazz)){
				throw new ServiceException("参数类型JobClass错误["+jobClass+"]不是一个可以被调度的Job");
			}
		} catch (ClassNotFoundException e) {
			throw new ServiceException("没有找到对应的类["+jobClass+"]");
		}
		//corn表达式校验
		String corn = model.getCronExpression();
		if(StringUtils.isBlank(corn)){
			throw new ServiceException("Corn表达式不能为空");
		}
		if(!CronExpression.isValidExpression(corn)){
			throw new ServiceException("Corn表达式错误["+corn+"]不是一个正确的Corn表达式");
		}
		//json参数校验
		String params = model.getParams();
		if(StringUtils.isNotBlank(params)){
			try{
				JSONObject.parse(params);
			}catch(Exception e){
				throw new ServiceException("参数错误["+params+"]不是一个正确的JSON");
			}
		}
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		model.setCreateEmpId(currentUser.getId());
		model.setModifyEmpId(currentUser.getId());
	}
	
	@Override
	public TimerTaskModel save(TimerTaskModel model) {
		//名称重复校验
		if(super.hasDuplicate(null, "name", model.getName())){
			throw new ServiceException("作业名称["+model.getName()+"]重复");
		}
		prepareSaveOrUpdate(model);
		super.save(model);
		timerTaskSchedulerManager.saveOrUpdateJob(model);
		return model;
	}
	
	@Override
	public TimerTaskModel update(TimerTaskModel model) {
		//名称重复校验
		if(super.hasDuplicate(model.getId(), "name", model.getName())){
			throw new ServiceException("作业名称["+model.getName()+"]重复");
		}
		prepareSaveOrUpdate(model);
		super.update(model);
		timerTaskSchedulerManager.saveOrUpdateJob(model);
		return model;
	}
	
	@Override
	public void deleteById(String id) {
		TimerTaskModel model = super.findById(id);
		if(model!=null){
			super.deleteById(id);
			timerTaskSchedulerManager.deleteJob(model);
		}
	}
	
	@Override
	public void runJob(TimerTaskModel model){
		timerTaskSchedulerManager.runJob(model);
	}
	
}
