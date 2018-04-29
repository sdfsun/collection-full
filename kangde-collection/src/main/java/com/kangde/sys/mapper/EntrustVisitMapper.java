package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.dto.EntrustVisitDto;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.model.EntrustVisit;

/**
 * 委托方mapper
 * @author zhangyj
 * @date 2016.6.1
 *
 */
public interface EntrustVisitMapper extends BaseMapper<EntrustVisitDto,String> {
	
	List<EntrustVisit> findByProductId(String productId);
}