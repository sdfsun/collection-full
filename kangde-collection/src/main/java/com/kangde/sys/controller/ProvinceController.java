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
import com.kangde.sys.model.ProvinceModel;
import com.kangde.sys.service.ProvinceService;
/**
 * 外访区域 Controller
 * 
 * @author wcy
 * @date 2016年6月23日10:53:37
 */
@Controller
@RequestMapping("sys/province")
public class ProvinceController extends RestfulUrlController<ProvinceModel, String> {

	@Autowired
	private ProvinceService provinceService;

	/** 省份查询所有： queryForPage到.xml走的query的方法 */
	@Override
	public SearchResult<ProvinceModel> queryForPage() {
		ParamCondition condition = parseCondition();
		return super.queryForPage(condition);
	}
	

	/** 省份 禁用/启用 */
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	public AjaxResult updateForStatus(ProvinceModel model) {
		provinceService.updateForStatus(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	

	/**
	 * 新增省份 重写父类方法 因为父类set的id与 CusAreaModel的名字不一致
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxResult save(ProvinceModel model) {
		provinceService.saveProv(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 编辑访问inpu页面，把id给他*/
	@RequestMapping(value = PAGE_INPUT + "/{provinceId}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("provinceId") String provinceId) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("provinceId", provinceId);
		return view;
	}
	
	/** 编辑方法*/
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(ProvinceModel model) {
		provinceService.updateProv(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	 @RequestMapping(value = "combobox")
	    @ResponseBody
	    public List<Combobox> combobox(@RequestParam(value="selectType",required=false)  String selectType,String incluedeId){
	    	List<ProvinceModel> entrusts = provinceService.findAll();
			return combobox(entrusts, selectType);
	    }
	 /**
	     * 生成combobox
	     * @param entrusts
	     * @param selectType
	     * @return
	     */
	    private  List<Combobox> combobox(List<ProvinceModel> entrusts,String selectType){
	    	List<Combobox> resultList = new ArrayList<>();
	    	//获取标题
	    	CoreUtil.getComboTitle(resultList, selectType);
	    	if(CollectionUtils.isNotEmpty(entrusts)){
	    		for (ProvinceModel entrust:entrusts) {
	    			Combobox combobox = new Combobox(entrust.getProvinceId(),entrust.getProvinceName());
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
