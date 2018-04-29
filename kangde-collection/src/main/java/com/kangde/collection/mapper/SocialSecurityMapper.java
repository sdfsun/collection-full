package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 社保mapper
 * @author zhangyj
 *
 */
public interface SocialSecurityMapper extends BaseMapper<SocialSecurityModel, String> {
	
	/**
	 * 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(SocialSecurityModel model);

	 List<SocialSecurityModel> findByCaseApplyId(String caseApplyId);
}