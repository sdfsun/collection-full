package com.kangde.collection.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.model.MessageReminderModel;
import com.kangde.commons.mapper.BaseMapper;

public interface MessageReminderMapper extends BaseMapper<MessageReminderModel, String>{

    int save(MessageReminderModel record);

	void updateMessageReminder(@Param(value = "id") String id, @Param(value = "modifyTime") Date modifyTime);

	void deleteReminderMapper(String dateStr);

}