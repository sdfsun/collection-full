package com.kangde.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.dto.AreaDto;
import com.kangde.sys.model.AreaModel;
import com.kangde.sys.model.CityModel;
import com.kangde.sys.service.SysAreaService;

/**
 * 外访区域 县 Controller
 * 
 * @author wcy
 * @date 2016年6月23日10:53:37
 */
@Controller
@RequestMapping("sys/sysarea")
public class SysAreaController extends RestfulUrlController<AreaDto, String> {

	@Autowired
	private SysAreaService SysAreaService;
	
	/** 省份查询所有： queryForPage到.xml走的query的方法 */
	@Override
	public SearchResult<AreaDto> queryForPage() {
		ParamCondition condition = parseCondition();
		return super.queryForPage(condition);
	}
	

	/** 县 禁用/启用 */
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	public AjaxResult updateForStatus(AreaModel model) {
		SysAreaService.updateForStatus(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/**访问新增页面*/
	@RequestMapping(value = "inputArea", method = RequestMethod.GET)
	public ModelAndView inputCity() {
		ModelAndView view = createBaseView("inputArea");
		return view;
	}
	
	/**
	 * 新增省份 重写父类方法 因为父类set的id与 CusAreaModel的名字不一致
	 */
	@RequestMapping(value = "save")              
	@ResponseBody
	public AjaxResult save(AreaModel model) {
		SysAreaService.saveArea(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 编辑访问inpu页面，把id给他*/
	@RequestMapping(value = "inputArea" + "/{areaId}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("areaId") String areaId) {
		ModelAndView view = createBaseView("inputArea");
		view.addObject("areaId", areaId);
		return view;
	}
	
	/** 编辑方法*/
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(AreaModel model) {
		SysAreaService.updateArea(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	@Override
	protected String getBaseName() {
		return "sys/province/";
	}

}
