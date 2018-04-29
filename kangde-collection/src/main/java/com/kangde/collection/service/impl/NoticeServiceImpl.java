package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.NoticeModelMapper;
import com.kangde.collection.model.NoticeModel;
import com.kangde.collection.service.NoticeService;
import com.kangde.commons.service.AbstractService;

@Service
public class NoticeServiceImpl extends AbstractService<NoticeModel, String> implements NoticeService {

	@Autowired
	private NoticeModelMapper noticeMapper;

	@Override
	public List<NoticeModel> queryTop() {
		return noticeMapper.queryTop();
	}
	
	
	
}
