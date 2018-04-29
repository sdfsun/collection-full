package com.kangde.collection.controller;



import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.service.HelpMeService;
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
@RequestMapping("collection/helpmerecord")
public class HelpMeRecordController extends RestfulUrlController<HelpMeDto, String> {

	@Autowired
	private HelpMeService helpMeService;
	@Autowired
	private RoleService roleService;
	
	public HelpMeRecordController() {
		//2016年11月24日17:29:15 添加排序
		columnNames.put("caseModel.caseCode", "case_info.`case_code`");
		columnNames.put("caseModel.caseNum", "case_info.case_Num");
		columnNames.put("caseModel.caseMoney", "case_info.case_money");
		columnNames.put("surplusMoney", "surplusMoney");
		columnNames.put("applyType", "case_apply.apply_type");
		columnNames.put("entrustModel.name", "sys_organization.name");
		columnNames.put("employeeInfoModel.userName", "ci.user_name");
		columnNames.put("appTime", "case_apply.app_time");
		columnNames.put("surTime", "sur_time");
	}
	
	/** 查资记录分页查询  */
	@RequestMapping(value = "queryrecord")
	@ResponseBody
	public SearchResult<HelpMeDto> queryrecord() {
		ParamCondition condition = parseCondition("*");
		
		/*String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}*/
		//查看当前登录人的角色， 是否有权限查看退案的案件
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		return helpMeService.queryrecord(condition);
	}
	@Override
	protected String getBaseName() {
		return "collection/helpmerecord/";
	}

}
