package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.MessageReminderMapper;
import com.kangde.collection.model.MessageReminderModel;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;

@Service
public class MessageReminderServiceImpl extends AbstractService<MessageReminderModel, String> implements MessageReminderService {

	@Autowired
	private MessageReminderMapper messageReminderMapper;
	@Override
	public void readMessageReminderService(String id) {
		messageReminderMapper.updateMessageReminder(id, new Date());
	}
	
	
	@Override
	public List<MessageReminderModel> query(ParamCondition condition) {
		return super.query(condition);
	}


	@Override
	public void saveReminder(String content, int remindType, String remindTarget, String url, String title) {
		MessageReminderModel model=new MessageReminderModel();
		model.setContent(content);
		model.setRemindTarget(remindTarget);
	
		//model.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
		model.setCreateTime(new Date());//创建时间
		model.setIsRead(0);
		model.setVersion(0);
		model.setRemindType(remindType);
		model.setUrl(url);
		model.setTitle(title);
		super.save(model);
	}


	@Override
	public void deleteMessageReminder(int day) {
		Date d = DateUtil.addDays(new Date(), -3);
		messageReminderMapper.deleteReminderMapper(DateUtil.date2Str(d, null));
	}


}
