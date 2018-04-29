package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.model.OrganizationModel;

/**
 * 统计mapper
 * 
 * @author zhangyj
 *
 */
public interface StatisticsOrganizationMapper extends BaseMapper<StatisticsOrganizationDto, String> {
	
	/**
	 * 风控方统计
	 * @param condition
	 * @return
	 */
	List<StatisticsOrganizationDto> queryCollection(ParamCondition condition);
	
	/** 导出所选分公司*/
	List<StatisticsOrganizationDto> queryExport(List<String> list);
	/**
	 * 查询所有顶级节点
	 * @param condition 检索条件
	 * @return 
	 *//*
	List<OrganizationModel> findTops(ParamCondition condition);
	*//**
	 * 通过父节点查询子节点
	 * @param parentId 父节点
	 * @return 
	 *//*
	List<OrganizationModel> findByParentId(String parentId);*/

	List<StatisticsOrganizationDto> queryOrganizationCaseState(ParamCondition condition);

	List<StatisticsOrganizationDto> queryOrganizationOrganization(ParamCondition condition);

	List<StatisticsOrganizationDto> queryOrganizationPaid(ParamCondition condition);
	
}