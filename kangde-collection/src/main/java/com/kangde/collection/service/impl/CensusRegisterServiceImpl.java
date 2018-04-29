package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CensusRegisterMapper;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.service.CensusRegisterService;
import com.kangde.commons.service.AbstractService;

/**
 * 户籍 ServiceImpl
 * 
 * @author wcy
 * @date 2016年7月21日13:54:52
 */
@Service
public class CensusRegisterServiceImpl extends AbstractService<CensusRegisterModel, String>
		implements CensusRegisterService {

	@Autowired
	private CensusRegisterMapper censusRegisterMapper;

	public List<CensusRegisterModel> findByCaseApplyId(String caseApplyId) {
		List<CensusRegisterModel> list = censusRegisterMapper.findByCaseApplyId(caseApplyId);
		return list;
	}
}
