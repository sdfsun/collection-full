package com.kangde.collection.service;

import com.kangde.collection.dto.VisitDto;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.service.BaseService;


public interface VisitRecordToApproveService extends BaseService<VisitDto,String>{
	int approveYes(VisitRecordModel model);
	int approveNo(VisitRecordModel model);
	
	
}
