package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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
import com.kangde.collection.CaseDivisionConst;
import com.kangde.collection.CollectionConst;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.ColumnService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.service.impl.CaseColorService;
import com.kangde.collection.vo.CaseCommentVo;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.TemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;
import com.kangde.sys.service.TemplateService;

/**
 * 案件 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("collection/case")
public class CaseController extends RestfulUrlController<CaseModel, String> {

	// 权限码前缀
	private static final String PERMISION_PREFIX = "case";

	@Autowired
	private CaseService caseService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private ColumnService columnService;
	
	@Autowired
	private CaseColorService caseColorService;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageReminderService messageReminderService;
	

	public CaseController() {
		columnNames.put("caseMoney", "cinfo.case_money");
		columnNames.put("surplusMoney", "surplus_money");
		columnNames.put("paidNum", "paid_num");
		columnNames.put("caseDate", "cinfo.case_date");
		
		columnNames.put("caseNum", "cinfo.case_num");
		columnNames.put("caseName", "cinfo.case_name");
		columnNames.put("batchCode", "cbatch.batch_code");
		columnNames.put("caseCode", "cinfo.case_code");
		
		columnNames.put("state", "cinfo.state");
		
		//2016年11月24日17:29:15 添加排序
		columnNames.put("caseBackdate", "cinfo.case_backdate");
		columnNames.put("caseNum", "cinfo.case_num");
		columnNames.put("caseCard", "cinfo.case_card");
		
		columnNames.put("cpMoney", "cp_money");
		columnNames.put("lastPhone", "lastPhone");
		columnNames.put("collecStateId", "cinfo.collec_state_id");
		
	}

	/**
	 * 上传页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView upload() {
		return super.createBaseView("upload");
	}

	/**
	 * 所选分案页面跳转
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":division" })
	@RequestMapping(value = "division", method = RequestMethod.GET)
	public ModelAndView divisionPage() {
		return super.createBaseView("division");
	}

	/**
	 * 所查结果分案页面跳转
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":queryDivision" })
	@RequestMapping(value = "queryDivision", method = RequestMethod.GET)
	public ModelAndView queryDivisionPage() {
		return super.createBaseView("division");
	}

	/**
	 * 所查自动分案页面跳转
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":autoDivision" })
	@RequestMapping(value = "autoDivision", method = RequestMethod.GET)
	public ModelAndView autoDivisionPage() {
		return super.createBaseView("autoDivision");
	}

	

	/**
	 * 修改案件状态页面跳转
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":pause", PERMISION_PREFIX + ":revocation",
			PERMISION_PREFIX + ":recover", PERMISION_PREFIX + ":settle" }, logical = Logical.OR)
	@RequestMapping(value = "changeState", method = RequestMethod.GET)
	public ModelAndView changeState() {
		return super.createBaseView("changeState");
	}

	/**
	 * 修改案件状态
	 * 
	 * @param state
	 * @param caseIds
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":pause", PERMISION_PREFIX + ":revocation",
			PERMISION_PREFIX + ":recover", PERMISION_PREFIX + ":settle" }, logical = Logical.OR)
	@RequestMapping(value = "changeState", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeState(@RequestParam("state") Integer state, @RequestParam("caseIds") String[] caseIds,
			@RequestParam("remark") String remark) {
		AjaxResult success = AjaxResult.success(getSuccessMessage());
		caseService.changeState(state, Arrays.asList(caseIds), remark);
		
		List<CaseModel> cases = caseService.findCasesByIds(caseIds);
		
		String message="";
	
		if(state==1){
			message="已被暂停";
			for (CaseModel caseModel : cases) {
				messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId() ,"案件详情");
			}
		}else if(state==0){
			message="已恢复正常";
			for (CaseModel caseModel : cases) {
				messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId() ,"案件详情");
			}
		}else if(state==4){
			message="已结清";
			for (CaseModel caseModel : cases) {
				messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId() ,"案件详情");
			}
		}else if(state==5){
			message="已被撤回";
			for (CaseModel caseModel : cases) {
				messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId() ,"案件详情");
			}
		}
		
		return success;
	}

	
	/** 所选分案 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":division" })
	@RequestMapping(value = "division", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult division(@RequestParam("userId") String userId, @RequestParam("caseIds[]") String[] caseIds) {
		AjaxResult success = AjaxResult.success(getSuccessMessage());
		caseService.divisionCase(userId, Arrays.asList(caseIds), CaseDivisionConst.divisionSelected);
		return success;
	}
	
	/** 所查结果分案 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":queryDivision" })
	@RequestMapping(value = "divisionForQuery", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult divisionCase(@RequestParam("userId") String userId) {
		AjaxResult result;
		ParamCondition condition = parseCondition("*");
		condition.remove("userId");// 从条件中移除
		List<CaseModel> list = super.query(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			List<String> ids = new ArrayList<>(list.size());
			for (CaseModel c : list) {
				ids.add(c.getId());
			}
			caseService.divisionCase(userId, ids, CaseDivisionConst.divisionQuery);
			result = AjaxResult.success(getSuccessMessage());
		} else {
			result = AjaxResult.failure("没有可分配的案件");
		}
		return result;
	}

	
	/**所选自动分案   所查自动分案*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":autoDivision", PERMISION_PREFIX + ":selectDivisionForAuto" })
	@RequestMapping(value = "autoDivision", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult autoDivision(@RequestParam("listUserGrop") String listUserGrop) {
		AjaxResult success = AjaxResult.success(getSuccessMessage());
		caseService.autoDivision(listUserGrop);
		return success;
	}

	
	// 所查自动分案结果统计数量结果计算
	@RequestMapping(value = "statisticsCaseCounts", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult statisticsCaseCounts() {
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		ParamCondition condition = parseCondition("*");
		Map<String, Object> map = caseService.queryStatisticsForCaseCounts(condition);
		result.setObj(map);
		return result;
	}

	/**
	 * 自动分案结果计算
	 * 
	 * @param divisionWay
	 *            分配方式 1:按照数量,2:按照金额,3:综合分配
	 * @param hasNotDivision
	 *            是否包含已分配？
	 * @param userList
	 *            被分案人员集合（id:分案人员ID,rate:比例）
	 * @return
	 */
	@RequestMapping(value = "autoDivisionCompute", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult autoDivisionCompute(@RequestParam("divisionWay") String divisionWay,
			@RequestParam("hasNotDivision") Boolean hasNotDivision, @RequestParam("userList") String userList) {
		AjaxResult result;
		ParamCondition condition = parseCondition("*");
		// 如果不包含已分配案件,只查询没有分配的案件
		if (!hasNotDivision) {
			condition.put("caseAssignState", CollectionConst.CASE_NOT_ASSIGN);
		}
		List<CaseModel> list = super.query(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			List<Map<String, Object>> computeResults = caseService.autoDivisionCompute(list, divisionWay, userList);
			result = AjaxResult.success(getSuccessMessage());
			result.setObj(computeResults);
		} else {
			result = AjaxResult.failure("没有可分配的案件");
		}
		return result;
	}

	
	@Override
	protected String getBaseName() {
		return "collection/case/";
	}

	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.POST)
	public SearchResult<CaseModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		return super.queryForPage(condition);
	}

	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		ModelAndView view = createBaseView("index");
		String batchCode = getRequest().getParameter("batchCode");
		if(batchCode!=null){
			batchCode=batchCode.trim().replace(' ','+');
			view.addObject("batchCode", batchCode);
		}
		CaseCommentVo attributeValue = new CaseCommentVo();
		attributeValue.setCaseCommentColor("0");
		view.addObject("caseCommentVo", attributeValue);
		view.addObject("commentColor", caseColorService.getColorMap());
		return view;
	}

	@RequestMapping(value = "statistics", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult statistics() {
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		ParamCondition condition = parseCondition("*");
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		Map<String, Object> map = caseService.queryStatistics(condition);
		result.setObj(map);
		return result;
	}

	// Excel导出选中案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportSelected" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] caseIds, @RequestParam("templateId") String templateId) {
		List<CaseModel> list = null;
		if (ArrayUtils.isNotEmpty(caseIds)) {
			ParamCondition condition = new ParamCondition();
			//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
			EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
			roleService.getOrgCode(empModel.getId(),condition);
			condition.put("caseIds", StringUtils.join(caseIds, ","));
			list = super.query(condition);
		}
		return exportCaseExcel(templateId, list);
	}

	// Excel导出所查案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportQuery" })
	@RequestMapping(value = "/exportQueryExcel", method = RequestMethod.POST)
	public ModelAndView exportExcel() {
		ParamCondition condition = parseCondition("*");
		String templateId = condition.remove("templateId");
		
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
				
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		List<CaseModel> list = super.query(condition);
		return exportCaseExcel(templateId, list);
	}

	private ModelAndView exportCaseExcel(String templateId, List<CaseModel> list) {
		TemplateModel template = templateService.findById(templateId);
		if (template == null) {
			throw new ServiceException("没有找到模板信息");
		}
		List<String> cids = Arrays.asList(StringUtils.split(template.getSysColumnIds(), ","));
		List<ColumnModel> clist = columnService.findByIds(cids);
		List<String> titleList = null;
		if (CollectionUtils.isNotEmpty(clist)) {
			titleList = new ArrayList<>(clist.size());
			for (ColumnModel c : clist) {
				titleList.add(c.getField());
			}
		}
		return super.createExcelView("caseViewDto", list, "案件信息列表", null, titleList);
	}

	/** 案件标色   页面*/
	@RequestMapping(value = "/mark")
	public ModelAndView mark(String caseId) {
		ModelAndView view = new ModelAndView("collection/case/mark");
		view.addObject("caseModel", new CaseModel());
		view.addObject("colorMap", caseColorService.getColorMap());
		view.addObject("caseId", caseId);
		return view;
	}

			
	/** 案件标色 */
	@RequiresPermissions(logical=Logical.OR,value={PermissionConst.case_markcolor,PermissionConst.departmentcase_markcolor,PermissionConst.mycase_markcolor})
	@RequestMapping(value = "updateColor", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateColor(HttpServletRequest request, String color) {
		String []ids=request.getParameterValues("ids[]");
		caseColorService.updateCaseColor(ids, color);
		
		for (CaseModel caseModel: caseService.findCasesByIds(ids)) {
			messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+"被标色", 0, caseModel.getCaseAssignedId(),  "/collection/casedetail?caseId="+caseModel.getId() ,"案件详情");
		}
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@RequestMapping(value = "/caseState")
	@ResponseBody
	public AjaxResult caseState() {
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		boolean tf= roleService.getOrgCodeTF(empModel.getId());
		if(tf){
			result.setObj(AjaxResult.CODE_SUCCESS);
		}else{
			result.setObj(AjaxResult.CODE_FAILURE);
		}
		
		return result;
	}

	
}
