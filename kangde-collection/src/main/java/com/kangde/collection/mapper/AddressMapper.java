package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.AddressModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
public interface AddressMapper extends BaseMapper<AddressModel, String>{
	List<AddressModel> queryByCaseId(String caseId);
    int deleteById(String id);
    int updateById(AddressModel record);
	void updateApplyToVisitStatusByPrimaryKey(AddressModel model);
	/** 申请外访失败后修改状态*/
	void resetAddressVisApp(List<String> list);
	
	/**
	 * 修改申请外访
	 * @param state
	 * @param id
	 * @return
	 */
	int updateVisApp(String id);
	List<AddressModel> findFinishedVisitRecord(String caseId);
	List<AddressModel> queryDetail(ParamCondition condition);
	List<AddressModel> query(ParamCondition condition);
	int saveAddress(AddressModel model);
	List<AddressModel> AddressAll(String caseId);
	void updateForAddress(String id,String addres);
}