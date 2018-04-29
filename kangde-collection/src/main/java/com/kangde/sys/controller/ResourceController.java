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
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.ResourceModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.ResourceService;

/**
 * 资源菜单 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/resource")
public class ResourceController extends RestfulUrlController<ResourceModel,String> {
	
	//权限码前缀
	private static final String PERMISION_PREFIX = "resource";

	@Autowired
	private ResourceService resourceService;

	/**
	 * 导航菜单
	 * @param id 父节点ID 
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "navTree")
    public List<TreeNode> navTree(){
        return resourceService.findMenus(SecurityUtil.getCurrentUser());
    }
    
	/**
	 * 首页列表
	 * 
	 * @param sort
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "treegrid")
	@ResponseBody
	public Datagrid<ResourceModel> treegrid(String sort, String order){
		ParamCondition condition = parseCondition();
		List<ResourceModel> list = query(condition);
		Datagrid<ResourceModel> dg = new Datagrid<>(list.size(), list);
		return dg;
	}

	/**
	 * 新增|修改：上级所属资源下拉列表.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "parentResource")
	@ResponseBody
	public List<TreeNode> parentResource(@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="selectType",required=false) String selectType) {
		List<TreeNode> titleList = null;
		if(StringUtils.isNotBlank(selectType)){
			//---请选择---"
			SelectType s = SelectType.getSelectTypeValue(selectType);
			TreeNode selectTreeNode = new TreeNode("", s.getDescription());
			titleList = Lists.newArrayList(selectTreeNode);
		}
		List<TreeNode> treeNodes = resourceService.findResourcesToTreeNodeByUser(parentId,SecurityUtil.getCurrentUser());
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
		Integer maxSort = resourceService.getMaxSort();
		return AjaxResult.success(null, maxSort);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		return super.pageIndex();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public SearchResult<ResourceModel> queryForPage() {
		return super.queryForPage();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView pageIndex() {
		return super.pageIndex();
	}

	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(ResourceModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(ResourceModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}
	
	@Override
	protected String getBaseName() {
		return "sys/resource/";
	}

}
