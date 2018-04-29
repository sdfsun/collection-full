package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.TargetAchieveMapper;
import com.kangde.collection.model.TargetAchieve;
import com.kangde.collection.service.TargetAchieveService;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.security.util.SecurityUtil;

@Service
public class TargetAchieveServiceImpl extends AbstractService<TargetAchieve, String> implements TargetAchieveService {
	@Autowired
	private TargetAchieveMapper targetAchieveMapper;
	
	@Override
	public TargetAchieve update(TargetAchieve model) {
		String userId = SecurityUtil.getCurrentUser().getId();
		model.setModifyEmpId(userId);
		model.setModifyName(SecurityUtil.getCurrentUser().getUserName());
		Integer state = model.getState();
		//添加创建时间
		if(state==0){
			model.setCreateTime(new Date());
			model.setCreateEmpId(userId);
			model.setCreateName(SecurityUtil.getCurrentUser().getUserName());
			model.setState(1);
		}
		// TODO Auto-generated method stub
		return super.update(model);
		
		
				
	}

	@Override
	public TargetAchieve findByEmpId(String userId, int year, int month) {
		TargetAchieve targetAchieve = targetAchieveMapper.findByEmpId(userId, year, month);
		return targetAchieve;
	}

	@Override
	public int queryTargetArchive() {
		String orgId = SecurityUtil.getCurrentUser().getOrgId();
		Date date=new Date();
		return targetAchieveMapper.queryTargetArchive(DateUtil.getYear(date),DateUtil.getMonth(date),orgId);
	}
}
