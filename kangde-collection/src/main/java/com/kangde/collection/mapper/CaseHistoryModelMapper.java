package com.kangde.collection.mapper;

import com.kangde.collection.model.CaseHistoryModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 案件历史mapper
 * @author zhangyj
 * @date 2016.6.16
 *
 */
public interface CaseHistoryModelMapper extends BaseMapper<CaseHistoryModel,String> {
	int save(CaseHistoryModel model);
	
    
}