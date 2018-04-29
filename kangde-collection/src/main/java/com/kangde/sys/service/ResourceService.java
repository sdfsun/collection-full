package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.ResourceModel;

/**
 * 资源菜单 Service
 * @author lisuo
 *
 */
public interface ResourceService extends BaseService<ResourceModel,String>{
	
	/**
	 * 用户登录,初始化菜单
	 * @param userId 当前用户ID
	 * @return	
	 */
	public List<TreeNode> findMenus(EmployeeInfoModel currentUser);
	
	/**
	 * 获取当前用户可用的所有资源（包括所有子集）,转换为树节点
	 * @param resourceId 排除的资源节点ID
	 * @param currentUser 当前用户
	 * @return
	 */
	public List<TreeNode> findResourcesToTreeNodeByUser(String resourceId,EmployeeInfoModel currentUser);

	/**
	 * 获取最大的排序号
	 * @return
	 */
	public Integer getMaxSort();

	/**
	 * 通过用户ID,查询用户所有资源(权限信息)
	 * @param userId 用户ID
	 * @return List<功能,权限信息>
	 */
	public List<ResourceModel> findResourceByUserId(String userId);

	/**
	 * 获取日志标题
	 * @param field 字段
	 * @param value Value
	 * @return
	 */
	public String getTitle(String field,String value);
	
}
