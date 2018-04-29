package com.kangde.collection.controller;



import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.service.HelpMeService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;

/**
 * 查资审批 Controller
 * 
 * @author wcy
 * @date 2016年7月19日17:42:20
 */
@Controller
@RequestMapping("collection/helpme")
public class HelpMeController extends RestfulUrlController<HelpMeDto, String> {

	// 权限码前缀
	private static final String PERMISION_PREFIX = "helpme";
		
	@Autowired
	private HelpMeService helpMeService;
	@Autowired
	private RoleService roleService;
	
	public HelpMeController() {
		//2016年11月24日17:29:15 添加排序
		columnNames.put("caseModel.caseCode", "case_info.`case_code`");
		columnNames.put("caseModel.caseNum", "case_info.case_Num");
		columnNames.put("caseModel.caseMoney", "case_info.case_money");
		columnNames.put("surplusMoney", "surplusMoney");
		columnNames.put("applyType", "case_apply.apply_type");
		columnNames.put("entrustModel.name", "sys_organization.name");
		columnNames.put("employeeInfoModel.userName", "ci.user_name");
		columnNames.put("appTime", "case_apply.app_time");
	}
	
	/** 待审批查资分页查询  */
	@Override
	public SearchResult<HelpMeDto> queryForPage() {
		ParamCondition condition = parseCondition("*");
	/*	
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}*/
		//查看当前登录人的角色， 是否有权限查看退案的案件
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String attachOrgId = currentUser.getAttachOrgId();
		String queryOrgs=currentUser.getOrgId()+",";
		if(StringUtils.isNotBlank(attachOrgId)){
			queryOrgs=queryOrgs+attachOrgId;
		}
		
		condition.put("queryOrgs", SQLUtil.warpSql(queryOrgs));
		condition.put("loginName", currentUser.getLoginName());
		if(StringUtils.isNotBlank(currentUser.getAttachEntrustId())){
			condition.put("attachEntrustId", SQLUtil.warpSql(currentUser.getAttachEntrustId()));
		}
		
		
		
		
		return super.queryForPage(condition);
	}
	

	/** 查资审批同意方法 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":agree" })
	@RequestMapping(value = "agree")
	@ResponseBody
	public AjaxResult agree(CaseApplyModel model){
		//申请状态 -2待审批 -1审批失败 0审批通过 1已完成
		model.setState(0);
		helpMeService.agree(model,1);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}
	/** 查资审批不同意方法 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":noagree" })
	@RequestMapping(value = "noagree")
	@ResponseBody
	public AjaxResult noagree(CaseApplyModel model){
		//申请状态 -2待审批 -1审批失败 0审批通过 1已完成
		model.setState(-1);
		helpMeService.agree(model,1);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}
	
	@Override
	protected String getBaseName() {
		return "collection/helpme/";
	}

}
