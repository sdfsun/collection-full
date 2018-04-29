package com.kangde.collection.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 待审批协催
 * MeMapper
 * @author wcy
 * @date 2016年7月19日10:58:33
 */
public interface HelpMeMapper extends BaseMapper<HelpMeDto, String> {
	/** 待审批协催*/
	int agree(Map<String, Object> params);
	/** 待审批协催*/
	int agree1(Map<String, Object> params);
	
	
	/** 待处理协催审批*/
	List<HelpMeDto> querydeal(ParamCondition condition);
	
	/** 协催记录*/
	List<HelpMeDto> queryrecord(ParamCondition condition);
	
	/** 导出户籍查询方法*/
	List<HelpMeDto> queryExport(List<String> list);
	
	List<CaseApplyModel> findIds(List<String> list);
	/** 根据CaseApplyId去查询CaseApplyModel*/
	CaseApplyModel findByApplyId(String CaseApplyId);
	int chaziToApproveCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "loginName")String loginName);
	int queryDealCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "loginName")String loginName);
	
	
}