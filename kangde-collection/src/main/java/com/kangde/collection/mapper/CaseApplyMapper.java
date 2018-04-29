package com.kangde.collection.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.model.CaseApply;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

public interface CaseApplyMapper  extends BaseMapper<CaseApply, String> {
	List<CaseApply> queryLetter(ParamCondition condition);
	void changeState(Map<String, Object> map);
	List<VisitShowView> findVisitShowViewByIds(List<String> ids);
	List<CaseApply> queryForIds(List<String> ids);
	List<CaseApply> queryMaterialByCaseId(ParamCondition condition);
	List<CaseApply> querycasedetailxiecui(ParamCondition condition);
	List<CaseApply> queryLetterByCaseId(ParamCondition condition);
	int queryLetterCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "loginName")String loginName, @Param(value = "applyState")int applyState);
	int xiecuiTasklistCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "loginName")String loginName);
}