package com.kangde.sys.controller;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.commons.easyui.Datagrid;
import com.kangde.commons.easyui.EasyUITreeNode;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.OrganizationModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.OrganizationService;

/**
 * 组织机构 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/organization")
public class OrganizationController extends RestfulUrlController<OrganizationModel,String> {
	
	//权限码前缀
	private static final String PERMISION_PREFIX = "organization";
	
	@Autowired
	private OrganizationService OrganizationService;

	/**
	 * 首页列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@RequestMapping(value = "treegrid")
	@ResponseBody
	public Datagrid<OrganizationModel> treegrid(){
		ParamCondition condition = parseCondition();
		List<OrganizationModel> list = query(condition);
		Datagrid<OrganizationModel> dg = new Datagrid<>(list.size(), list);
		return dg;
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getOrganizationTreeJoinAttachedOrgs")
	@ResponseBody
	public List<TreeNode> getOrganizationTreeJoinAttachedOrgs(@RequestParam(value="selectType",required=false) String selectType) {
		List<TreeNode> titleList = null;
		if(StringUtils.isNotBlank(selectType)){
			//---请选择---"
			SelectType s = SelectType.getSelectTypeValue(selectType);
			TreeNode selectTreeNode = new TreeNode("", s.getDescription());
			titleList = Lists.newArrayList(selectTreeNode);
		}
		List<TreeNode> treeNodes = OrganizationService.getOrganizationTreeJoinAttachedOrgs(SecurityUtil.getCurrentUser());
		if(titleList!=null){
			return ListUtils.union(titleList, treeNodes);
		}
		return treeNodes;
	}
	@RequestMapping(value = "getOrganizationList")
	@ResponseBody
	public List<EasyUITreeNode> getOrganizationList(@RequestParam(value="selectType",required=false) String selectType) {
		List<EasyUITreeNode> treeNodes = OrganizationService.getOrganizationList(SecurityUtil.getCurrentUser());
		return treeNodes;
	}
	
	/**
	 * 新增|修改：上级所属资源下拉列表.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "parentOrganization")
	@ResponseBody
	public List<TreeNode> parentOrganization(@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="selectType",required=false) String selectType) {
		List<TreeNode> titleList = null;
		if(StringUtils.isNotBlank(selectType)){
			//---请选择---"
			SelectType s = SelectType.getSelectTypeValue(selectType);
			TreeNode selectTreeNode = new TreeNode("", s.getDescription());
			titleList = Lists.newArrayList(selectTreeNode);
		}
		List<TreeNode> treeNodes = OrganizationService.getOrganizationTree(parentId,SecurityUtil.getCurrentUser());
		if(titleList!=null){
			return ListUtils.union(titleList, treeNodes);
		}
		return treeNodes;
	}
	
	/**
	 * 排序最大值.
	 */
	@RequestMapping(value = "maxSort")
	@ResponseBody
	public AjaxResult maxSort() {
		Integer maxSort = OrganizationService.findMaxSort();
		return AjaxResult.success(null, maxSort);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		return super.pageIndex();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView pageIndex() {
		return super.pageIndex();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public SearchResult<OrganizationModel> queryForPage() {
		return super.queryForPage();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(OrganizationModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(OrganizationModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}
	
	@Override
	protected String getBaseName() {
		return "sys/organization/";
	}

}
