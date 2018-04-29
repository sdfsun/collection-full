package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.vo.PhoneRecordVo;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

public interface PhoneRecordMapper extends BaseMapper<PhoneRecordModel, String> {
	int deleteByPrimaryKey(String id);
	int insertSelective(PhoneRecordModel record);

	PhoneRecordModel selectByPrimaryKey(String id);

	List<PhoneRecordModel> selectByCaseId(String caseId);
	
	List<PhoneRecordModel> queryByCaseId(String caseId);

	int updateStatusByPrimaryKey(PhoneRecordModel record);
	
	List<PhoneRecordVo> queryPhoneRecordByCondition(ParamCondition condition);
}