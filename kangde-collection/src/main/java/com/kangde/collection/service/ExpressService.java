package com.kangde.collection.service;
import java.util.List;

import com.kangde.collection.model.ExpressModel;
import com.kangde.commons.service.BaseService;


/**
 * 快递
 * service
 * @author wcy
 * @date 2016年12月16日11:26:32
 */
public interface ExpressService  extends BaseService<ExpressModel, String> {

	List<ExpressModel> findByCaseApplyId(String caseApplyId);
	
}


