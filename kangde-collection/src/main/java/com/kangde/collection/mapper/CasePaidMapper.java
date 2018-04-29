package com.kangde.collection.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.dto.CasePaidDto;
import com.kangde.collection.model.CasePaid;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

public interface CasePaidMapper extends BaseMapper<CasePaid, String> {

	List<CasePaid> query(String id);
	
	/**
	 * 还款管理  显示主页面信息
	 * @param condition
	 * @return
	 */
	List<CasePaidDto> queryPaid(ParamCondition condition);
	

	/**
	 * 修改状态 
	 * @param model
	 * @return
	 */
	int updateForState(CasePaid model);

	List<CasePaid> queryPTPrecordByCaseId(String caseId);

	List<CasePaid> queryPTPorCPForPage(ParamCondition condition);
	
	List<CasePaidDto> queryForIds(List<String> ids);
	
	CasePaidDto queryById(String id);

	Map<String, Object> statistics(ParamCondition condition);

	int toConfirmPayCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "loginName")String loginName);

}