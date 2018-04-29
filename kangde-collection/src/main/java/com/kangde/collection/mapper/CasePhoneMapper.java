package com.kangde.collection.mapper;

import com.kangde.collection.model.CasePhoneModel;
import com.kangde.commons.mapper.BaseMapper;

public interface CasePhoneMapper  extends BaseMapper<CasePhoneModel, String> {

	int deleteByPrimaryKey(String id);


	int insertSelective(CasePhoneModel record);

	CasePhoneModel selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CasePhoneModel record);

	int updateByPrimaryKey(CasePhoneModel record);
}