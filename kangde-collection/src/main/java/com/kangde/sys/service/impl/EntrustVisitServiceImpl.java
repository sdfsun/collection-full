package com.kangde.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.service.AbstractService;
import com.kangde.sys.dto.EntrustVisitDto;
import com.kangde.sys.mapper.EntrustVisitMapper;
import com.kangde.sys.model.EntrustLinkman;
import com.kangde.sys.model.EntrustVisit;
import com.kangde.sys.service.EntrustVisitService;

/**
 * 委托方 Service实现类
 * 
 * @author zhangyj
 */
@Service("entrustVisitService")
public class EntrustVisitServiceImpl extends AbstractService<EntrustVisitDto,String> implements EntrustVisitService {
	@Autowired
	private EntrustVisitMapper entrustVisitmapper;
	
	@Override
	public List<EntrustVisit> findByProductId(String productId){
		
		return entrustVisitmapper.findByProductId(productId);
	}

}
