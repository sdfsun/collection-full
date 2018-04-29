package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.dto.AddressDto;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.model.AddressModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
public interface AddressService  extends BaseService<AddressModel,String>{
	List<AddressModel> queryByCaseId(String caseId);
	AddressModel findById(String id);
	List<AddressModel> findFinishedVisitRecord(String caseId);
	AddressDto findFullAddress(String areaId1,String areaId2,String areaId3) throws AddressException;
	void updateById(AddressModel address);
	SearchResult<AddressModel> queryDetail(ParamCondition condition);
	List<AddressModel> query(ParamCondition condition);
	List<AddressModel> AddressAll(String caseId);
	
	
}


