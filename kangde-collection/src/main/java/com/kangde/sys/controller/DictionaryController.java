package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.Datagrid;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;
import com.kangde.sys.util.DictUtil;

/**
 * 字典 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/dictionary")
public class DictionaryController extends RestfulUrlController<DictionaryModel,String> {
	//权限码前缀
	private static final String PERMISION_PREFIX = "dictionary";
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		ModelAndView view = super.pageIndex();
		return view;
	}

	/**
	 * 首页列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@RequestMapping(value = "treegrid")
	@ResponseBody
	public Datagrid<DictionaryModel> treegrid(){
		ParamCondition condition = parseCondition("dictPath");
		String dictPath = condition.get("dictPath");
		List<DictionaryModel> list = super.query(condition);
		//兼容easyui treegrid父节点不存在问题
		if(StringUtils.isNotBlank(dictPath)){
			if(CollectionUtils.isNotEmpty(list)){
				DictionaryModel root = DictUtil.findDictByPath(dictPath);
				if(root!=null){
					for(DictionaryModel  dict:list){
						if(root.getId().equals(dict.getParentId())){
							dict.setParent_id(null);
						}
					}
				}
			}
		}
		Datagrid<DictionaryModel> dg = new Datagrid<>(list.size(), list);
		return dg;
	}

	/**
	 * 新增|修改：上级所属资源下拉列表.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "parentDictionary")
	@ResponseBody
	public List<TreeNode> parentDictionary(String parentId) {
		//---请选择---"
		SelectType s = SelectType.select;
		TreeNode selectTreeNode = new TreeNode("", s.getDescription());
		List<TreeNode> treeNodes = dictionaryService.getDictionaryTree(parentId,false);
		List<TreeNode> unionList = ListUtils.union(Lists.newArrayList(selectTreeNode), treeNodes);
		return unionList;
	}
	
	//获取上级节点。只获取不包含子节点的树
	@RequestMapping(value = "parentTree")
	@ResponseBody
	public List<TreeNode> parentTree() {
		List<TreeNode> treeNodes = dictionaryService.getDictionaryTree(null,true);
		return treeNodes;
	}
	
	/**
	 * 排序最大值.
	 */
	@RequestMapping(value = "maxSort")
	@ResponseBody
	public AjaxResult maxSort() {
		Integer maxSort = dictionaryService.findMaxSort();
		return AjaxResult.success(null, maxSort);
	}
	
	/**
     * combobox下拉列表框数据.
     *
     * @param selectType 标题类型提示名称
     * @param path 字典路径
     * @return
     */
    @RequestMapping(value = "combobox")
    @ResponseBody
    public List<Combobox> combobox(@RequestParam(value="selectType",required=false) String selectType,@RequestParam("path") String path){
    	List<Combobox> resultList = new ArrayList<>();
    	//---请选择---"
    	if(StringUtils.isNotBlank(selectType)){
    		SelectType s = SelectType.getSelectTypeValue(selectType);
            if (s != null) {
                Combobox selectCombobox = new Combobox("",s.getDescription());
                resultList.add(selectCombobox);
            }
    	}
    	List<DictionaryModel> dicts = dictionaryService.findSubsByPath(path);
    	if(CollectionUtils.isNotEmpty(dicts)){
    		for (DictionaryModel dict:dicts) {
    			Combobox combobox = new Combobox(dict.getValue(),dict.getName());
    			resultList.add(combobox);
    		}
    	}
		return resultList;
    }
	
    /**
     * 查询子节点
     * @param constName 字典常量类：映射路径的常量名称
     * @return
     */
    @RequestMapping(value = "subs")
    @ResponseBody
    public List<DictionaryModel> subs(@RequestParam("constName") String constName){
    	List<DictionaryModel> dicts = dictionaryService.findSubsByPath(DictUtil.getPathValue(constName));
    	return dicts;
    }
    
    @RequestMapping(value = "exportDicts",method=RequestMethod.POST)
    public void exportDicts(@RequestParam(value="id",required=false) String id){
//    	List<DictionaryModel> dicts = dictionaryService.findById(id)
//    	XmlUtil.object2Xml(object);
    }
    
    @RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(DictionaryModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(DictionaryModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}
    

	@Override
	protected String getBaseName() {
		return "sys/dictionary/";
	}

}
