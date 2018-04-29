package com.kangde.collection.service;


import java.util.List;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.dto.ControllerMessageDto;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 *  催记Service  
 * @author wcy
 * @date 2016年6月13日13:37:22
 */
public interface CollectionManageService extends BaseService<ControllerMessageDto,String>{
	
	List<ControllerMessageDto> queryForId(List<String> caseIds);
	
}
