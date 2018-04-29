package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.NoticeModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

public interface NoticeModelMapper extends BaseMapper<NoticeModel, String>{
	List<NoticeModel> queryTop();
}