package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustVisit;
import com.kangde.sys.service.EntrustCaseSourceService;

/**
 * 委托方 Controller
 * @author zhangyj
 * @date 2016.6.1
 */
@Controller
@RequestMapping("sys/entrustCaseSource")
public class EntrustCaseSourceController extends RestfulUrlController<EntrustCaseSource,String> {
	// 权限码前缀
	private static final String PERMISION_PREFIX = "entrustCaseSource";
	@Autowired
	private EntrustCaseSourceService entrustCaseSourceService;
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return super.index();
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":save" })
	@Override
	public AjaxResult save(EntrustCaseSource model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":update" })
	@Override
	public AjaxResult update(EntrustCaseSource model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	/**
	 * 启用 停用
	 * @param status
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":start" })
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForStatus(HttpServletRequest request){
		String[] ids=request.getParameterValues("ids[]");
		entrustCaseSourceService.updateForStatus(ids);
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":stop" })
	@RequestMapping(value = "updateForStatusNo")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForStatusNo(HttpServletRequest request){
		String[] ids=request.getParameterValues("ids[]");
		entrustCaseSourceService.updateForStatusNo(ids);
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	
	@Override
	public SearchResult<EntrustCaseSource> queryForPage() {
		ParamCondition condition = parseCondition("*");
//		String cooperateDate = getRequest().getParameter("cooperateDate");
//		condition.put("cooperateDate", cooperateDate);
		return super.queryForPage(condition);
	}
	
	/**
	 * 获取部门用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "enSourcesByEnId")
	@ResponseBody
	public List<Combobox> orgusers(@RequestParam(value = "selectType", required = false) String selectType,
			@RequestParam("entrustId") String entrustId) {
		List<Combobox> resultList = new ArrayList<>();
		// ---请选择---"
		if (StringUtils.isNotBlank(selectType)) {
			SelectType s = SelectType.getSelectTypeValue(selectType);
			if (s != null) {
				Combobox selectCombobox = new Combobox("", s.getDescription());
				resultList.add(selectCombobox);
			}
		}
		List<EntrustCaseSource> ecses = entrustCaseSourceService.findSourceByEnId(entrustId);
		if (CollectionUtils.isNotEmpty(ecses)) {
			for (EntrustCaseSource ecs : ecses) {
				Combobox combobox = new Combobox(ecs.getId(), ecs.getName());
				resultList.add(combobox);
			}
		}
		return resultList;
	}
    
	@Override
	protected String getBaseName() {
		return "sys/entrustCaseSource/";
	}
	
	
	
}
