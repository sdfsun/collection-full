package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.model.NoticeModel;
import com.kangde.commons.service.BaseService;
public interface NoticeService  extends BaseService<NoticeModel,String>{
	public List<NoticeModel> queryTop();
}


