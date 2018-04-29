package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.dto.EntrustVisitDto;
import com.kangde.sys.model.EntrustVisit;

/**
 *  委托方Service
 * @author zhangyj
 */
public interface EntrustVisitService extends BaseService<EntrustVisitDto,String>{
	
	List<EntrustVisit> findByProductId(String productId);
	
}
