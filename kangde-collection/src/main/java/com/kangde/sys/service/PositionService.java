package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.PositionModel;


/**
 * 职位 Service
 * @author lisuo
 *
 */
public interface PositionService extends BaseService<PositionModel,String>{

	/**
	 * 获取职位的树节点信息
	 * @param positionId 排除的岗位节点ID
	 * @return
	 */
	List<TreeNode> getPositionTree(String positionId);

	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();
}
