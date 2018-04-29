package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.TelecomModel;
import com.kangde.commons.mapper.BaseMapper;

public interface TelecomMapper extends BaseMapper<TelecomModel, String>{

	List<TelecomModel> findByCaseApplyId(String caseApplyId);
 
}