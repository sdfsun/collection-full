package com.kangde.collection.service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.TelecomMapper;
import com.kangde.collection.model.TelecomModel;
import com.kangde.collection.service.TelecomService;
import com.kangde.commons.service.AbstractService;
/**
 * 电信
 * ServiceImpl
 * @author wcy
 * @date 2016年7月21日19:29:26
 */
@Service("telecomService")
public class TelecomServiceImpl extends AbstractService<TelecomModel,String> implements TelecomService {

	@Autowired
	private TelecomMapper telecomMapper;
	@Override
	public List<TelecomModel> findbyCaseApplyId(String caseApplyId) {
		return telecomMapper.findByCaseApplyId(caseApplyId);
	}

	

	
}
