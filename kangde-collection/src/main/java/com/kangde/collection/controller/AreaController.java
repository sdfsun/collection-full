package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.model.AreaModel;
import com.kangde.collection.service.AreaService;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 区域 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("collection/area")
public class AreaController extends RestfulUrlController<AreaModel,String> {
	//权限码前缀
		private static final String PERMISION_PREFIX = "area";
	@Autowired
	private AreaService areaService;
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return super.index();
	}
	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(AreaModel model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(AreaModel model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	@Override
	protected AjaxResult delete(AreaModel model) {
		// TODO Auto-generated method stub
		return super.delete(model);
	}
	/**
	 * 获取区域下拉框
	 * @param selectType
	 * @param incluedeIds
	 * @return
	 */
    @RequestMapping(value = "combobox")
    @ResponseBody
    public List<Combobox> combobox(@RequestParam(value="selectType",required=false) 
    String selectType,String[] incluedeIds){
    	List<AreaModel> areas;
    	if(incluedeIds==null){
    		areas = areaService.findAreas(null);
    	}else{
    		areas = areaService.findAreas(Arrays.asList(incluedeIds));
    	}
		return combobox(areas, selectType);
    }
    
    /**
     * 生成combobox
     * @param areas
     * @param selectType
     * @return
     */
    private  List<Combobox> combobox(List<AreaModel> areas,String selectType){
    	List<Combobox> resultList = new ArrayList<>();
    	//获取标题
    	CoreUtil.getComboTitle(resultList, selectType);
    	if(CollectionUtils.isNotEmpty(areas)){
    		for (AreaModel area:areas) {
    			Combobox combobox = new Combobox(area.getId(),area.getName());
    			resultList.add(combobox);
    		}
    	}
		return resultList;
    }
    @Override
	public SearchResult<AreaModel> queryForPage() {
		ParamCondition condition = parseCondition("name");
		return super.queryForPage(condition);
	}
	@Override
	protected String getBaseName() {
		return "collection/area/";
	}
	
}
