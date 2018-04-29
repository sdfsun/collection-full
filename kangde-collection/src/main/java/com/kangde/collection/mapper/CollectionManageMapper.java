package com.kangde.collection.mapper;



import java.util.List;
import java.util.Map;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.dto.ControllerMessageDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
/**
 * 催记mapper
 * @author wcy
 * @date 2016年6月13日13:43:17
 *
 */
public interface CollectionManageMapper extends BaseMapper<ControllerMessageDto,String> {
	
	List<ControllerMessageDto> queryForId(Map<String, Object> params);
	
	List<ControllerMessageDto> queryAll(ParamCondition condition);
	
}