package com.kangde.collection.mapper;
import java.util.List;

import com.kangde.collection.model.ExpressModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 快递
 * mapper
 * @author wangcy
 * @date 2016年12月16日11:31:03
 *
 */
public interface ExpressMapper extends BaseMapper<ExpressModel, String> {
	
	 List<ExpressModel> findByCaseApplyId(String caseApplyId);
}