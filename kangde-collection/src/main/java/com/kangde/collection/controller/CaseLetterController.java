package com.kangde.collection.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.model.CaseApply;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.view.VisitDocView;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.AbstractController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.VisitTemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;
import com.kangde.sys.service.VisitTemplateService;

/**
 * 信函Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("collection/caseLetter")
public class CaseLetterController extends AbstractController<CaseApply, String> {
	//权限码前缀
	private static final String PERMISION_PREFIX = "caseLetter";
	/** 案件协催申请Service */
	@Autowired
	private CaseApplyService applyService;
	@Autowired
	private VisitTemplateService visitTemplateService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageReminderService messageReminderService;
	
	public CaseLetterController() {
		columnNames.put("paidNum", "paid_num");
		columnNames.put("caseMoney", "case_info.case_money");
		
		//2016年11月28日11:20:50    信函添加一大波排序
		columnNames.put("caseCode", "case_info.case_code");
		columnNames.put("caseNum", "case_info.case_num");
		columnNames.put("orgName", "case_info.org_name");
		columnNames.put("applyUserName", "employee_info.user_name");
		columnNames.put("appTime", "case_apply.app_time");
	}
	
	@RequiresPermissions(value={PERMISION_PREFIX+":approval"})
	//待审批
	@RequestMapping(value = "approvalPending", method = RequestMethod.GET)
	public ModelAndView approvalPending() {
		return createBaseView("approvalPending");
	}
	
	@RequiresPermissions(value={PERMISION_PREFIX+":pending"})
	//待处理
	@RequestMapping(value = "pending", method = RequestMethod.GET)
	public ModelAndView pending() {
		return createBaseView("pending");
	}
	
	@RequiresPermissions(value={PERMISION_PREFIX+":record"})
	//信函记录
	@RequestMapping(value = "record", method = RequestMethod.GET)
	public ModelAndView record() {
		return createBaseView("record");
	}
	
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<CaseApply> queryForPage() {
		ParamCondition condition = parseCondition("*");
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		return applyService.queryLetter(condition);
	}

	/**
	 * 修改案件协催申请状态页面跳转
	 * @return
	 */
	@RequestMapping(value = "changeState",method=RequestMethod.GET)
	public ModelAndView changeState() {
		return super.createBaseView("changeState");
	}
	
	/**
	 * 信函审批
	 * @param state
	 * @param ids
	 * @param approvalOpinion
	 * @return
	 */
	@RequiresPermissions(value={PERMISION_PREFIX+":pass",PERMISION_PREFIX+":nopass",PERMISION_PREFIX+":post",PERMISION_PREFIX+":backLetter"},logical=Logical.OR)
	@RequestMapping(value = "changeState",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeState(@RequestParam("state")Integer state,@RequestParam("ids")String [] ids,@RequestParam(value="approvalOpinion",required=false) String approvalOpinion) {
		AjaxResult success = AjaxResult.success(getSuccessMessage());
		applyService.changeState(state,Arrays.asList(ids),approvalOpinion);
		List<CaseApply> queryForIds = applyService.queryForIds(Lists.newArrayList(ids));
		String message="";
		if(state==0){
			message="已通过信函审批";
		}else if(state==-1){
			message="未通过信函审批";
		}else if(state==1){
			message="已完成信函邮寄";
		}else if(state==2){
			message="信函被退回";
		}
		for (CaseApply caseApply : queryForIds) {
			messageReminderService.saveReminder("案件["+caseApply.getCaseModel().getCaseCode()+"]"+message, 0, caseApply.getCaseModel().getCaseAssignedId(), "/collection/casedetail?caseId="+caseApply.getCaseModel().getId(),"案件详情");
		}
		return success;
	}
	
	//导出信函
	@RequiresPermissions(value={PERMISION_PREFIX+":export"})
	@RequestMapping(value = "/exportLetter")
	public ModelAndView exportExcel(String[] ids, @RequestParam("templateId") String templateId, String type) {
		String templateName = "信函";
		List<VisitShowView> listData = applyService.findVisitShowViewByIds(Arrays.asList(ids));
		VisitTemplateModel visit = visitTemplateService.findById(templateId);// 拿到模板信息
		ModelAndView view = new ModelAndView("visitDocView");
		view.addObject(VisitDocView.DOC_NAME, templateName).addObject(VisitDocView.DOC_CONTENT, visit.getContent())
				.addObject(VisitDocView.VISIT_BEANS, listData);
		return view;
	}
	
	@Override
	protected String getBaseName() {
		return "collection/caseLetter/";
	}
	
}
