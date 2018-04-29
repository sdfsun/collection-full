package com.kangde.collection.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.mapper.StatisticsOrganizationMapper;
import com.kangde.collection.service.StatisticsOrganizationService;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.OrganizationModel;
import com.kangde.sys.security.util.SecurityUtil;


@Service
public class StatisticsOrganizationServiceImpl extends AbstractService<StatisticsOrganizationDto,String> implements StatisticsOrganizationService {

	
	
	@Autowired
	private StatisticsOrganizationMapper statisticsMapper;

	@Override
	public SearchResult<StatisticsOrganizationDto> queryCollection(ParamCondition condition) {
		List<StatisticsOrganizationDto> list =statisticsMapper.queryCollection(condition);
		SearchResult<StatisticsOrganizationDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	@Override
	public SearchResult<StatisticsOrganizationDto> queryOrganizationCaseState(ParamCondition condition) {
		List<StatisticsOrganizationDto> list =statisticsMapper.queryOrganizationCaseState(condition);
		SearchResult<StatisticsOrganizationDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	@Override
	public SearchResult<StatisticsOrganizationDto> queryOrganizationOrganization(ParamCondition condition) {
		List<StatisticsOrganizationDto> list =statisticsMapper.queryOrganizationOrganization(condition);
		SearchResult<StatisticsOrganizationDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	@Override
	public SearchResult<StatisticsOrganizationDto> queryOrganizationPaid(ParamCondition condition) {
		List<StatisticsOrganizationDto> list =statisticsMapper.queryOrganizationPaid(condition);
		SearchResult<StatisticsOrganizationDto> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	/** 导出所选分公司 */
	@Override
	public List<StatisticsOrganizationDto> queryExport(List<String> ids) {
		List<StatisticsOrganizationDto> list = statisticsMapper.queryExport(ids);
		return list;
	}
	
	/** 导出所查分公司 */
	@Override
	public List<StatisticsOrganizationDto> exportQueryExcel(ParamCondition condition) {
		List<StatisticsOrganizationDto> list =statisticsMapper.queryCollection(condition);
		return list;
	}
	
	/*@Cacheable
	@Override
	public List<TreeNode> getOrganizationTreeJoinAttachedOrgs(EmployeeInfoModel employee) {
		//1.获取当前用户的机构列表
		List<TreeNode> treeNode = getOrganizationTreeNoCache(null,employee);
		//没有附加区域,返回用户的机构列表
		if(StringUtils.isBlank(employee.getAttachOrgId())){
			return treeNode;
		}
		List<TreeNode> mergeList = new ArrayList<>();
		//2.获取用户附加的机构列表
		List<TreeNode> nodes = Lists.newArrayList();
		String[] attchOrgs = StringUtils.split(employee.getAttachOrgId(), ",");
		if(ArrayUtils.isNotEmpty(attchOrgs)){
			for(String orgId:attchOrgs){
				OrganizationModel root = super.findById(orgId);
				TreeNode node = this.organizationToTreeNode(root,null,true);
				if (node != null) {
					if(!CoreUtil.treeContains(nodes, node.getId())){
						nodes.add(node);
					}
				}
			}
		}
		mergeList.addAll(treeNode);
		mergeList.addAll(nodes);
		//3.合并1，2步骤
		return mergeList;
	}
	*//**
	 * 织机机构 
	 * @param org 组织信息
	 * @param excludeOrgId 排除的ID
	 * @param isCascade 是否级联,列出下级所有组织机构
	 * @return
	 *//*
	private TreeNode organizationToTreeNode(OrganizationModel org,String excludeOrgId,boolean isCascade) {
		if(org==null){
			return null;
		}
		//排除orgId
		if(excludeOrgId!=null && StringUtils.isNotBlank(excludeOrgId.toString())){
			if(excludeOrgId.equals(org.getId())){
				return null;
			}
		}
		TreeNode treeNode = new TreeNode(org.getId(), org.getName(), null);
		if (isCascade) {
			// 递归查询子节点
			List<TreeNode> childrenTreeNodes = Lists.newArrayList();
			//查询子集节点
			List<OrganizationModel> children = statisticsMapper.findByParentId(org.getId());
			for (OrganizationModel o : children) {
				TreeNode node = organizationToTreeNode(o, excludeOrgId, isCascade);
				if (node != null) {
					childrenTreeNodes.add(node);
				}
			}
			treeNode.setChildren(childrenTreeNodes);
		}
		
		return treeNode;
	}
	public List<TreeNode> getOrganizationTreeNoCache(String excludeOrgId,EmployeeInfoModel employee){
		List<TreeNode> nodes = Lists.newArrayList();
		List<OrganizationModel> organizations = null;
		//判断用户是否是超级管理员
		if(SecurityUtil.isSuperAdmin(employee)){
			//查询顶级组织机构
			organizations = statisticsMapper.findTops(null);
		}else{
			//根据当前用户信息查询机构
			OrganizationModel root = super.findById(employee.getOrgId());
			organizations = Lists.newArrayList(root);
		}
		for (OrganizationModel org : organizations) {
			TreeNode node = this.organizationToTreeNode(org,excludeOrgId,true);
			if (node != null) {
				nodes.add(node);
			}
		}
		return nodes;
	}*/

	
}
