package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.VisitTemplateModel;
import com.kangde.sys.service.VisitTemplateService;

/**
 * 外访模板设置Controller
 * @author lisuo
 *
 */
@Controller
@RequestMapping("sys/visitTemplate")
public class VisitTemplateController extends RestfulUrlController<VisitTemplateModel,String>  {
	
	// 权限码前缀
		private static final String PERMISION_PREFIX = "visitTemplate";
	
	@Autowired
	private VisitTemplateService visitTemplateService;
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView input = super.pageInput(id);
		input.addObject("model", super.findById(id));
		return input;
	}
	
	/**
	 * 跳入选择外访模板页面
	 * wcy
	 * @param
	 * @date 2016年7月5日11:06:31
	 */
	@RequestMapping(value="/exportVisitExcel")	
	public ModelAndView exportExcelPage(@RequestParam("name") String name,@RequestParam(value="type",required=false) Integer type){
		ModelAndView view =createBaseView("exportVisitExcel");
		view.addObject("name",name);
		view.addObject("type",type);
		return view;
	}
	/**
	 *	获取外访模板下拉
	 *	wcy
	 *@date 2016年7月5日11:26:39
	 */
	@RequestMapping(value = "combobox")
	@ResponseBody
	public List<Combobox> combobox(@RequestParam("type") Integer type,
			@RequestParam(value = "selectType", required = false) String selectType) {
		List<VisitTemplateModel> temps = visitTemplateService.findByType(type);
		return combobox(temps, selectType);
	}
	
	/**
	 * 生成combobox
	 * @param temps
	 * @param selectType
	 * @return
	 */
	private List<Combobox> combobox(List<VisitTemplateModel> temps, String selectType) {
		List<Combobox> resultList = new ArrayList<>();
		// 获取标题
		CoreUtil.getComboTitle(resultList, selectType);
		if (CollectionUtils.isNotEmpty(temps)) {
			for (VisitTemplateModel temp : temps) {
				Combobox combobox = new Combobox(temp.getId(), temp.getName());
				resultList.add(combobox);
			}
		}
		return resultList;
	}
	
	/** 外访禁用/启用模板， */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":state" })
	@RequestMapping(value = "updateForStart")
	@ResponseBody
	public AjaxResult updateForStatus(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids[]");
		visitTemplateService.updateForStatus(Arrays.asList(ids));
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 外访禁用/启用模板， */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":stop" })
	@RequestMapping(value = "updateForStop")
	@ResponseBody
	public AjaxResult updateForStop(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids[]");
		visitTemplateService.updateForStop(Arrays.asList(ids));
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return super.index();
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":addVisit" })
	@Override
	public AjaxResult save(VisitTemplateModel model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":editVisit" })
	@Override
	public AjaxResult update(VisitTemplateModel model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":delVisit" })
	@Override
	protected AjaxResult delete(VisitTemplateModel model) {
		// TODO Auto-generated method stub
		return super.delete(model);
	}
	
	@Override
	protected String getBaseName() {
		return "sys/visitTemplate/";
	}
	
}
