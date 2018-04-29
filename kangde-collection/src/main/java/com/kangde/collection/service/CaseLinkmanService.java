package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.commons.service.BaseService;

public interface CaseLinkmanService extends BaseService<CaseLinkmanModel,String> {
	
	List<CaseLinkmanModel> findCaseIds(String caseId);
	
	public int  updateStatus(CaseLinkmanModel model);
	
	public int  updateForStatusinvalid(CaseLinkmanModel model);
	
	/** 根据电话号查询
	 *  如果在电话簿里查到了该电话，提示让用户从电话簿里操作
	 * */
	List<CaseLinkmanModel> findPhone(String phone,String caseId);
	
	
	List<CaseLinkmanModel> findByCaseIds(String caseId);
	
	List<CaseLinkmanModel> iPhone(String caseId);
	
	CaseLinkmanModel iPhone1(String caseId);
	
	void updateForPhone(String id , String phone);
	
	
	
}
