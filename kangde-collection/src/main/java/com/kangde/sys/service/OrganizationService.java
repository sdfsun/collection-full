package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.easyui.EasyUITreeNode;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.OrganizationModel;

/**
 * 组织机构 Service
 * @author lisuo
 *
 */
public interface OrganizationService extends BaseService<OrganizationModel,String>{
	
	/**
	 * 获取组织机构的
	 * @param orgId 排除的机构节点ID
	 * @return
	 */
	List<TreeNode> getOrganizationTree(String orgId,EmployeeInfoModel employee);

	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();

	/**
	 * 获取组织机构的（不缓存）
	 * @param orgId 排除的机构节点ID
	 * @return
	 */
	List<TreeNode> getOrganizationTreeNoCache(String orgId, EmployeeInfoModel employee);

	/**
	 * 获取组织机构集合,合并用户附加的机构
	 * @param currentUser 当前用户
	 * @return
	 */
	List<TreeNode> getOrganizationTreeJoinAttachedOrgs(EmployeeInfoModel currentUser);
	List<EasyUITreeNode> getOrganizationList(EmployeeInfoModel currentUser);

}
