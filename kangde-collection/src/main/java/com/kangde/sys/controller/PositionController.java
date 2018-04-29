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
import com.kangde.sys.model.PositionModel;
import com.kangde.sys.service.PositionService;

/**
 * 职位 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/position")
public class PositionController extends RestfulUrlController<PositionModel,String> {

	//权限码前缀
	private static final String PERMISION_PREFIX = "position";
	
	@Autowired
	private PositionService positionService;
	
	/**
	 * 首页列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@RequestMapping(value = "treegrid")
	@ResponseBody
	public Datagrid<PositionModel> treegrid(){
		ParamCondition condition = parseCondition();
		List<PositionModel> list = query(condition);
		Datagrid<PositionModel> dg = new Datagrid<>(list.size(), list);
		return dg;
	}

	/**
	 * 新增|修改：上级所属资源下拉列表.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "parentPosition")
	@ResponseBody
	public List<TreeNode> parentPosition(@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="selectType",required=false) String selectType) {
		List<TreeNode> titleList = null;
		if(StringUtils.isNotBlank(selectType)){
			//---请选择---"
			SelectType s = SelectType.getSelectTypeValue(selectType);
			TreeNode selectTreeNode = new TreeNode("", s.getDescription());
			titleList = Lists.newArrayList(selectTreeNode);
		}
		List<TreeNode> treeNodes = positionService.getPositionTree(parentId);
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
		Integer maxSort = positionService.findMaxSort();
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
	public SearchResult<PositionModel> queryForPage() {
		return super.queryForPage();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(PositionModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(PositionModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}

	
	@Override
	protected String getBaseName() {
		return "sys/position/";
	}
	
}
