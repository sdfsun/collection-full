package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
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

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.Datagrid;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;

/**
 * 员工信息 Controller
 * 
 * @author zhangyj
 * @date 2016.5.5
 */
@Controller
@RequestMapping("sys/employeeInfo")
public class EmployeeInfoController extends RestfulUrlController<EmployeeInfoModel, String> {

	//权限码前缀
	private static final String PERMISION_PREFIX = "employeeInfo";
	
	@Autowired
	private EmployeeInfoService employeeInfoService;

	@RequiresPermissions(value={PERMISION_PREFIX+":view"})
	@Override
	public SearchResult<EmployeeInfoModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		return super.queryForPage(condition);
	}
	
	@RequiresPermissions(value={PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		return super.index();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(EmployeeInfoModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(EmployeeInfoModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	@Override
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}
	
	/**
	 * 跳转到用户角色关联页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "editRole", method = RequestMethod.GET)
	public ModelAndView pageEditRole(@RequestParam("userId") String id) {
		return createBaseView("editRole").addObject("userId", id);
	}
	
	/**
	 * 修改用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "editRole", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult editRole(@RequestParam("userId") String userId, String[] roleIds) {
		List<String> list = null;
		if (ArrayUtils.isNotEmpty(roleIds)) {
			list = Arrays.asList(roleIds);
		}
		employeeInfoService.editRole(userId, list);
		return AjaxResult.success(getSuccessMessage());
	}

	/**
	 * 获取用户关联角色ID
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "getRoleIds")
	@ResponseBody
	public AjaxResult getRoleIds(@RequestParam String userId) {
		List<String> roleIds = employeeInfoService.findRoleIdsByUserId(userId);
		return AjaxResult.success(null, roleIds);
	}

	/**
	 * 跳转到用户资源关联页面
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions({PERMISION_PREFIX+":right"})
	@RequestMapping(value = "editResource", method = RequestMethod.GET)
	public ModelAndView pageEditResource(@RequestParam("userId") String id) {
		return createBaseView("editResource").addObject("userId", id);
	}

	/**
	 * 修改用户-资源关系
	 * 
	 * @param userId
	 * @param resourceIds
	 * @return
	 */
	@RequiresPermissions({PERMISION_PREFIX+":right"})
	@RequestMapping(value = "editResource", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult editResource(@RequestParam("userId") String userId, String[] resourceIds) {
		List<String> list = null;
		if (ArrayUtils.isNotEmpty(resourceIds)) {
			list = Arrays.asList(resourceIds);
		}
		employeeInfoService.editResource(userId, list);
		return AjaxResult.success(getSuccessMessage());
	}

	/**
	 * 获取用户关联资源ID
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "getResourceIds")
	@ResponseBody
	public AjaxResult getResourceIds(@RequestParam String userId) {
		List<String> resourceIds = employeeInfoService.findResourceIdsByUserId(userId);
		return AjaxResult.success(null, resourceIds);
	}

	/**
	 * 获取组织机构负责人
	 * 
	 * @param incluedeId
	 * @return
	 */
	@RequestMapping(value = "combogrid")
	@ResponseBody
	protected Datagrid<EmployeeInfoModel> combogrid(String incluedeId) {
		Datagrid<EmployeeInfoModel> dg = new Datagrid<>();
		List<EmployeeInfoModel> emps = employeeInfoService.findEmpsForOrg(incluedeId, SecurityUtil.getCurrentUser());
		dg.setRows(emps);
		dg.setTotal(emps.size());
		return dg;
	}

	/**
	 * 获取部门用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "orgusers")
	@ResponseBody
	public Datagrid<EmployeeInfoModel> orgusers() {
		Datagrid<EmployeeInfoModel> dg = new Datagrid<>();
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		List<EmployeeInfoModel> emps = employeeInfoService.findEmpsByOrg(currentUser);
		dg.setRows(emps);
		dg.setTotal(emps.size());
		return dg;
	}

	
	

	/**
	 * 获取部门用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "orgusersByOrg")
	@ResponseBody
	public List<Combobox> orgusers(@RequestParam(value = "selectType", required = false) String selectType,
			@RequestParam("orgId") String orgId) {
		List<Combobox> resultList = new ArrayList<>();
		// ---请选择---"
		if (StringUtils.isNotBlank(selectType)) {
			SelectType s = SelectType.getSelectTypeValue(selectType);
			if (s != null) {
				Combobox selectCombobox = new Combobox("", s.getDescription());
				resultList.add(selectCombobox);
			}
		}
		List<EmployeeInfoModel> emps = employeeInfoService.findEmpsByOrg(orgId);
		if (CollectionUtils.isNotEmpty(emps)) {
			for (EmployeeInfoModel emp : emps) {
				if(emp.getStatus()==0){
					
					Combobox combobox = new Combobox(emp.getId(), emp.getUserName()+"(离职)");
					resultList.add(combobox);
				}
				if (emp.getStatus()==1){
						
						Combobox combobox = new Combobox(emp.getId(), emp.getUserName()+"(在职)");
						resultList.add(combobox);
					}
			}
		}
		return resultList;
	}
	/**
	 * 获取部门用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "posusersByPos")
	@ResponseBody
	public List<Combobox> posusers(@RequestParam(value = "selectType", required = false) String selectType,
			@RequestParam("posId") String posId) {
		List<Combobox> resultList = new ArrayList<>();
		// ---请选择---"
		if (StringUtils.isNotBlank(selectType)) {
			SelectType s = SelectType.getSelectTypeValue(selectType);
			if (s != null) {
				Combobox selectCombobox = new Combobox("", s.getDescription());
				resultList.add(selectCombobox);
			}
		}
		List<EmployeeInfoModel> emps = employeeInfoService.findEmpsByPos(posId);
		if (CollectionUtils.isNotEmpty(emps)) {
			for (EmployeeInfoModel emp : emps) {
				Combobox combobox = new Combobox(emp.getId(), emp.getUserName());
				resultList.add(combobox);
			}
		}
		return resultList;
	}

	/**
	 * 获取部门用户信息,以combotree形式展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "orgUserCombotree")
	@ResponseBody
	protected List<TreeNode> orgUserCombotree(@RequestParam(value = "selectType", required = false) String selectType) {
		List<TreeNode> result = new ArrayList<>();
		CoreUtil.getTreeTitle(result, selectType);
		List<TreeNode> treeNodes = employeeInfoService.orgUserCombotree(SecurityUtil.getCurrentUser());
		result.addAll(treeNodes);
		return result;
	}

	/**
	 * 启用 停用
	 * 
	 * @param status
	 * @param model
	 * @return
	 */
	@RequiresPermissions({PERMISION_PREFIX+":enable"})
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	// 修改状态
	public AjaxResult updateForStatus(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids[]");
		employeeInfoService.updateForStatus(ids);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@RequiresPermissions({PERMISION_PREFIX+":disable"})
	@RequestMapping(value = "updateForStatusNo")
	@ResponseBody
	// 修改状态
	public AjaxResult updateForStatusNo(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids[]");
		employeeInfoService.updateForStatusNo(ids);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	// 修改当前用户密码
	@RequiresPermissions(value = { PERMISION_PREFIX + ":updateUserPassword" })
	@RequestMapping(value = "updateUserPassword")
	@ResponseBody
	public AjaxResult updateUserPassword(@RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("ccPwd") String ccPwd) {
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		if (SecurityUtil.isSuperAdmin(currentUser)) {
			throw new ServiceException("超级管理员无法修改密码");
		}
		employeeInfoService.updateUserPassword(currentUser, password, newPassword,ccPwd);
		return AjaxResult.success(getSuccessMessage());
	}

	// 检查用户是指定的角色
	@RequestMapping(value = "hasRole")
	@ResponseBody
	public AjaxResult hasRole(@RequestParam("roleCode") String roleCode) {
		return AjaxResult.success(null, SecurityUtil.hasRole(roleCode));
	}

	@Override
	protected String getBaseName() {
		return "sys/employeeInfo/";
	}

}
