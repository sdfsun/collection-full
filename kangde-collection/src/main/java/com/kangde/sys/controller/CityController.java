package com.kangde.sys.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.dto.CityDto;
import com.kangde.sys.model.CityModel;
import com.kangde.sys.service.CityService;

/**
 * 外访区域 城市 Controller
 * 
 * @author wcy
 * @date 2016年6月23日10:53:37
 */
@Controller
@RequestMapping("sys/city")
public class CityController extends RestfulUrlController<CityDto, String> {

	@Autowired
	private CityService cityService;
	
	/** 省份查询所有： queryForPage到.xml走的query的方法 */
	@Override
	public SearchResult<CityDto> queryForPage() {
		ParamCondition condition = parseCondition();
		return super.queryForPage(condition);
	}

	/** 城市 禁用/启用 */
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	public AjaxResult updateForStatus(CityModel model) {
		cityService.updateForStatus(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/**访问新增页面*/
	@RequestMapping(value = "inputCity", method = RequestMethod.GET)
	public ModelAndView inputCity() {
		ModelAndView view = createBaseView("inputCity");
		return view;
	}

	/**
	 * 新增省份 重写父类方法 因为父类set的id与 CusAreaModel的名字不一致
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxResult save(CityModel model) {
		cityService.saveCity(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 编辑访问inpu页面，把id给他*/
	@RequestMapping(value = "inputCity" + "/{cityId}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("cityId") String cityId) {
		ModelAndView view = createBaseView("inputCity");
		view.addObject("cityId", cityId);
		return view;
	}
	
	/** 编辑方法*/
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(CityModel model) {
		cityService.updateCity(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	 @RequestMapping(value = "combobox")
	    @ResponseBody
	    public List<Combobox> combobox(@RequestParam(value="selectType",required=false)  String selectType,String incluedeId){
	    	List<CityModel> entrusts = cityService.findAllCity();
			return combobox(entrusts, selectType);
	    }
	 	/**
	     * 生成combobox
	     * @param entrusts
	     * @param selectType
	     * @return
	     */
	    private  List<Combobox> combobox(List<CityModel> entrusts,String selectType){
	    	List<Combobox> resultList = new ArrayList<>();
	    	//获取标题
	    	CoreUtil.getComboTitle(resultList, selectType);
	    	if(CollectionUtils.isNotEmpty(entrusts)){
	    		for (CityModel entrust:entrusts) {
	    			Combobox combobox = new Combobox(entrust.getCityId(),entrust.getCityName());
	    			resultList.add(combobox);
	    		}
	    	}
			return resultList;
	    }
	@Override
	protected String getBaseName() {
		return "sys/province/";
	}

}
