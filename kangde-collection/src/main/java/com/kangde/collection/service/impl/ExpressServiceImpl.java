package com.kangde.collection.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.ExpressMapper;
import com.kangde.collection.model.ExpressModel;
import com.kangde.collection.service.ExpressService;
import com.kangde.commons.service.AbstractService;
/**
 * 快递
 * ServiceImpl
 * @author wcy
 * @date 2016年12月16日11:28:10
 */
@Service("expressService")
public class ExpressServiceImpl extends AbstractService<ExpressModel,String> implements ExpressService {

	@Autowired
	private ExpressMapper expressMapper;
	
	@Override
	public List<ExpressModel> findByCaseApplyId(String caseApplyId){
		return expressMapper.findByCaseApplyId(caseApplyId);
	}
	
}
