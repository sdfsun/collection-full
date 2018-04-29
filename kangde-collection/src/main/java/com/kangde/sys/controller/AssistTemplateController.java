package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.TemplateModel;
import com.kangde.sys.service.TemplateService;

/**
 * 模板 Controller
 * 
 * @author wcy
 * @date 2016年6月14日17:26:29
 */
@Controller
@RequestMapping("sys/assisttemplate")
public class AssistTemplateController extends RestfulUrlController<TemplateModel, String> {

	@Autowired
	private TemplateService templateService;

	/** 协催条件查询 */
	@RequestMapping(value = "querycollection")
	@ResponseBody
	public SearchResult<TemplateModel> querycollection() {
		ParamCondition condition = parseCondition("name");
		return templateService.querycollection(condition);
	}

	/** 催记模启用板， */
	@RequestMapping(value = "updateForStatusCollection")
	@ResponseBody
	public AjaxResult updateForStatusCollection(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids[]");
		templateService.updateForStatusCollection(Arrays.asList(ids));
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 催记停用板， */
	@RequestMapping(value = "updateForStatusStop")
	@ResponseBody
	public AjaxResult updateForStatusStop(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids[]");
		templateService.updateForStatusStop(Arrays.asList(ids));
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/** 批量删除， */
	@RequestMapping(value = "delateIds")
	@ResponseBody
	public AjaxResult delateIds(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids[]");
		templateService.delateIds(Arrays.asList(ids));
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/** 新增风控模板 */
	@RequestMapping(value = "saveTemplatecollection")
	@ResponseBody
	public AjaxResult saveTemplatecollection(@RequestParam("name") String name, String[] list) {
		templateService.saveTemplatecollection(name, list);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/** 修改催记模板 */
	@RequestMapping(value = "updateTemplatecollection")
	@ResponseBody
	public AjaxResult updateTemplatecollection(@RequestParam("name") String name, String[] list, String id) {
		templateService.updateTemplate(name, list, id,"cj");
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	// 输入页面（增加，修改，展示都用这个页面）,转发到getBaseName/input
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		List<ColumnModel> column = templateService.findCheckbox();
		view.addObject("column", column);
		return view;
	}

	// 输入页面（增加，修改，展示都用这个页面）,转发到getBaseName/input
	@RequestMapping(value = "inputcollection", method = RequestMethod.GET)
	public ModelAndView pageInputCollection() {
		ModelAndView view = createBaseView("inputcollection");
		List<ColumnModel> column = templateService.findCheckboxcollection();
		view.addObject("column", column);
		return view;
	}



	/** 访问催记模板编辑 */
	@RequestMapping(value = "editinputcollection" + "/{id}", method = RequestMethod.GET)
	public ModelAndView pageInputcollection(@PathVariable("id") String id) {
		ModelAndView view = createBaseView("editinputcollection");
		// 去查询name 就是模板名称
		TemplateModel model = templateService.findById(id);
		// 得到模板名称
		String name = model.getName();
		// 得到sys_column_ids 里面储存的id
		String SysColumnIds = model.getSysColumnIds();
		// 得到已勾选字段
		List<ColumnModel> caseColumnsOk = templateService.findOkCollection(SysColumnIds);
		// 所有字段
		List<ColumnModel> column = templateService.findCheckboxcollection();
		view.addObject("column", column);// 所有字段
		view.addObject("name", name);// 模板名称
		view.addObject("caseColumnsOk", caseColumnsOk);// 勾选字段
		view.addObject("id", id);
		return view;
	}

	/**
	 * 获取模板下拉框
	 * @param gropNo
	 * @param selectType
	 * @return
	 */
	@RequestMapping(value = "combobox")
	@ResponseBody
	public List<Combobox> combobox(@RequestParam("gropNo") String gropNo,
			@RequestParam(value = "selectType", required = false) String selectType) {
		List<TemplateModel> temps = templateService.findByGropNo(gropNo);
		return combobox(temps, selectType);
	}

	/**
	 * 生成combobox
	 * 
	 * @param temps
	 * @param selectType
	 * @return
	 */
	private List<Combobox> combobox(List<TemplateModel> temps, String selectType) {
		List<Combobox> resultList = new ArrayList<>();
		// 获取标题
		CoreUtil.getComboTitle(resultList, selectType);
		if (CollectionUtils.isNotEmpty(temps)) {
			for (TemplateModel temp : temps) {
				Combobox combobox = new Combobox(temp.getId(), temp.getName());
				resultList.add(combobox);
			}
		}
		return resultList;
	}
	
	//跳转到导出Excel页
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcelPage(@RequestParam("gropNo") String gropNo,@RequestParam("name") String name){
		ModelAndView view = createBaseView("exportExcel");
		view.addObject("gropNo", gropNo);
		view.addObject("name", name);
		return view;
	}

	@Override
	protected String getBaseName() {
		return "sys/assisttemplate/";
	}

}
