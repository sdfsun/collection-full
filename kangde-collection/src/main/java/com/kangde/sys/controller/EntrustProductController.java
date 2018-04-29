package com.kangde.sys.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.dto.EntrustDto;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustProduct;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.EntrustProductService;

/**
 * 外访区域 城市 Controller
 * 
 * @author wcy
 * @date 2016年6月23日10:53:37
 */
@Controller
@RequestMapping("sys/entrustProduct")
public class EntrustProductController extends RestfulUrlController<EntrustProduct, String> {

	@Autowired
	private EntrustProductService entrustProductService;
	@Autowired
	private EmployeeInfoService employeeInfoService;

	/**
	 * 展示方法
	 */ 
	@RequestMapping(value = "queryProduct")
	@ResponseBody
	public SearchResult<EntrustProduct> queryProduct() {
		ParamCondition condition = parseCondition("*");
		return entrustProductService.queryProduct(condition);
	}
	
	@Override
	public AjaxResult save(EntrustProduct model) {
		String staffs = model.getStaff();
		if (staffs!=null){
			String[] useridArray = staffs.split(",");
			StringBuffer names = new StringBuffer();
			for (String usersid : useridArray) {
				EmployeeInfoModel emp = employeeInfoService.findById(usersid);
				names.append(emp.getUserName() + ",");
			}
			String nameStr = names.toString();
			if (nameStr.length() > 0) {
				String substring = nameStr.substring(0, nameStr.length() - 1);
				model.setStaffName(substring);
			}
			
		}
		return super.save(model);
	}
	
	@Override
	public AjaxResult update(EntrustProduct model) {
		String staffs = model.getStaff();
		if (staffs!=null){
			String[] useridArray = staffs.split(",");
			StringBuffer names = new StringBuffer();
			for (String usersid : useridArray) {
				EmployeeInfoModel emp = employeeInfoService.findById(usersid);
				names.append(emp.getUserName() + ",");
			}
			String nameStr = names.toString();
			if (nameStr.length() > 0) {
				String substring = nameStr.substring(0, nameStr.length() - 1);
				model.setStaffName(substring);
			}
			
		}
		return super.update(model);
	}
	
	//单独生成批次号
		@RequestMapping(value = "createWholeCode")
		@ResponseBody
		//修改状态  
		public String createWholeCode(EntrustProduct model){
			String wholeCode = entrustProductService.createWholeCode(model);
		    JSONObject json=new JSONObject();
		    json.put("wholeCode", wholeCode);
		    return JSON.toJSONString(json);
		}
		
		/**
		 * 启用 停用
		 * @param status
		 * @param model
		 * @return
		 */
		//@RequiresPermissions(value = { PERMISION_PREFIX + ":start" })
		@RequestMapping(value = "updateForStatus")
		@ResponseBody
		//修改状态  
		public AjaxResult updateForStatus(HttpServletRequest request){
			String[] ids=request.getParameterValues("ids[]");
			entrustProductService.updateForStatus(ids);
		    AjaxResult result = AjaxResult.success(getSuccessMessage());
		    return result;
		}
		//@RequiresPermissions(value = { PERMISION_PREFIX + ":stop" })
		@RequestMapping(value = "updateForStatusNo")
		@ResponseBody
		//修改状态  
		public AjaxResult updateForStatusNo(HttpServletRequest request){
			String[] ids=request.getParameterValues("ids[]");
			entrustProductService.updateForStatusNo(ids);
		    AjaxResult result = AjaxResult.success(getSuccessMessage());
		    return result;
		}
		
		

	@Override
	protected String getBaseName() {
		return "sys/entrustProduct/";
	}

}
