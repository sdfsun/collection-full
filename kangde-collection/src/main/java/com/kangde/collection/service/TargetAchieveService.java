package com.kangde.collection.service;

import com.kangde.collection.model.TargetAchieve;
import com.kangde.commons.service.BaseService;

/**
 * service
 * @author zhangyj
 *
 */
public interface TargetAchieveService extends BaseService<TargetAchieve,String>{
	TargetAchieve findByEmpId(String userId, int year, int month);
	int queryTargetArchive();

}
