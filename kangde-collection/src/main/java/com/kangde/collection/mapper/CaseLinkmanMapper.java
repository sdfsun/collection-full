package com.kangde.collection.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 案件联系人表mapper
 * 
 * @author lisuo
 *
 */
public interface CaseLinkmanMapper extends BaseMapper<CaseLinkmanModel, String> {
	
	List<CaseLinkmanModel> findCaseIds(String caseId);
	
	int updateStatus(CaseLinkmanModel model);
	
	List<CaseLinkmanModel> findPhone(@Param("phone") String phone,@Param("caseId") String caseId);

	List<CaseLinkmanModel> findByCaseIds(String caseId);
	
	List<CaseLinkmanModel> iPhone(String caseId);
	
	CaseLinkmanModel iPhone1(String caseId);
	
	void updateForPhone(String id , String phone);
}