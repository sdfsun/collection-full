package com.kangde.collection.service;

import com.kangde.collection.model.CaseHistoryModel;
import com.kangde.commons.service.BaseService;

public interface CaseHistoryService extends BaseService<CaseHistoryModel, String>{
	int saveCaseHistory(CaseHistoryModel model);

}
