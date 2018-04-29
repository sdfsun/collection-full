package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.EntrustCaseProductReleation;
import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustModel;

/**
 * 委托方mapper
 * @author zhangyj
 * @date 2016.6.1
 *
 */
public interface EntrustCaseProductReleationMapper extends BaseMapper<EntrustCaseProductReleation,String> {
	
	 List<EntrustCaseProductReleation> findByEntrustId(String id);
	 
	 
	 EntrustCaseProductReleation findByProductId(String productId);
	
	
}