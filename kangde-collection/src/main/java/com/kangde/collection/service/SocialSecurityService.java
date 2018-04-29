package com.kangde.collection.service;


import java.util.List;

import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.commons.service.BaseService;


/**
 * 社保
 * service
 * @author wcy
 * @date 2016年7月21日19:28:40
 */
public interface SocialSecurityService  extends BaseService<SocialSecurityModel, String> {

	List<SocialSecurityModel> findByCaseApplyId(String caseApplyId);
	
	
}


