package com.kangde.collection.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.collection.view.VisitDocView;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.VisitTemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.VisitTemplateService;

@Controller
@RequestMapping("collection/cache")
public class CacheController extends RestfulUrlController<VisitRecordModel, String> {
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private CacheManager cm;
	@RequestMapping(value = "findEmpsByOrg")
	@ResponseBody
	public List<EmployeeInfoModel> getCurrentOrgVisitor() {
		List<EmployeeInfoModel> emps = employeeInfoService.findEmpsByOrg(SecurityUtil.getCurrentUser().getOrgId());
		return emps;
	}

	@RequestMapping(value = "updateEmp")
	@ResponseBody
	public void updateEmp() {
		employeeInfoService.updateForStatus(new String[] {"1"});
	}
	
	

	@RequestMapping(value = "/delCache")
	@ResponseBody
	public String delCache(String cacheName) throws IOException {

		Cache uCache = cm.getCache(cacheName);
		uCache.clear();
		return new JSONObject().toJSONString();
	}

}
