package com.kangde.collection.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.model.CaseApply;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.excel.ExcelContext;
import com.kangde.commons.util.excel.vo.ExcelImportResult;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;

/**
 * 协催管理
 * 
 * @author lidengwen
 * @date 2016年8月11日 上午10:13:57
 *
 */
@Controller
@RequestMapping("collection/xiecui")
public class XiecuiController extends RestfulUrlController<CaseApply, String> {

	// 权限码前缀
	private static final String PERMISION_PREFIX = "xiecui";
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private MessageReminderService messageReminderService;
	@Autowired
	private ExcelContext excelContext;
	// 前端排序字段
		public XiecuiController() {
			columnNames.put("surTime", "case_apply.sur_time");
			columnNames.put("appTime", "case_apply.app_time");
			columnNames.put("surUserName", "case_apply.sur_user_name");
			
			columnNames.put("caseModel.caseCode", "case_info.case_code");
			columnNames.put("entrustModel.name", "entrust.name");
			columnNames.put("caseModel.caseMoney", "case_info.case_money");
			columnNames.put("caseModel.caseName", "case_info.case_name");
			columnNames.put("applyType", "case_apply.apply_type");
			columnNames.put("appTime", "case_apply.app_time");
			columnNames.put("caseModel.orgName", "case_info.org_name");
			
		}

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView upload() {
		ModelAndView view = createBaseView("upload");
		return view;
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":upload" })
	@RequestMapping(value = "/applyEexcelImport", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult importExcel(HttpServletRequest request,@RequestParam("excelFile") MultipartFile file
			) throws IOException, Exception {
		ExcelImportResult readExcel = excelContext.readExcel("huifu", file.getInputStream());
		List<CaseApply> listBean = readExcel.getListBean();
		if(CollectionUtils.isNotEmpty(listBean)){
			for(CaseApply d:listBean){
				updateXiecui(d);
			}
		
		}
		AjaxResult result = AjaxResult.success("导入数据成功");
		return result;
	}
	@RequestMapping(value = "task", method = RequestMethod.GET)
	public ModelAndView letter() {
		ModelAndView view = createBaseView("task");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@RequestMapping(value = "record", method = RequestMethod.GET)
	public ModelAndView xiecui() {
		ModelAndView view = createBaseView("record");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	/**
	 * 待处理协催
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "tasklist", method = RequestMethod.GET)
	public SearchResult<CaseApply> tasklist() {
		ParamCondition condition = parseCondition("*");
		condition.put("applyTypes", "(11, 7, 5, 10)");
		condition.put("state", -2);
		
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		common(condition);
		SearchResult<CaseApply> page = super.queryForPage(condition);
		return page;
	}

	private void common(ParamCondition condition) {
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
	}

	/**
	 * 带分页查询 协催记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "recordlist", method = RequestMethod.GET)
	public SearchResult<CaseApply> recordlist() {
		ParamCondition condition = parseCondition("*");
		condition.put("applyTypes", "(11, 7, 5, 10)");
		condition.put("state", 1);
		
		//查看当前登录人的角色， 是否有权限查看退案的案件 
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		
		common(condition);

		
		SearchResult<CaseApply> page = super.queryForPage(condition);
		return page;
	}

	@Override
	public ModelAndView pageIndex() {
		ModelAndView view = createBaseView(PAGE_INDEX);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("id", this.getRequest().getParameter("id"));
		return view;
	}

	@RequiresPermissions(value = { PERMISION_PREFIX + ":updateXiecui" })
	@RequestMapping(value = "updateXiecui")
	@ResponseBody
	public AjaxResult updateXiecui(CaseApply model) {
		CaseApply record = caseApplyService.findById(model.getId());
		record.setHurryContent(model.getHurryContent());
		record.setSurTime(new Date());
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		record.setSurUser(currentUser.getId());
		record.setSurUserName(currentUser.getUserName());
		record.setState(1);//已完成
		caseApplyService.update(record);
		CaseModel caseModel = caseService.findById(record.getCaseId());
		if(caseModel!=null){
			messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]已完成协催", 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
		}
			return AjaxResult.success(getSuccessMessage());
	}

	
	//Excel导出选查案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportQueryExcel" })
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		condition.put("applyTypes", "(11, 7, 5, 10)");
		condition.put("state", -2);
		
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		common(condition);
		
		List<CaseApply> list = caseApplyService.query(condition);
		return super.createExcelView("xiecui", list, "待处理协催记录", null,null); 
	}
	
	
	// 导出所选 参数 ids 选中id,array选中勾选字段
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportSelectedExcel" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] ids) {
		List<CaseApply> list=null;
		ParamCondition condition = parseCondition("*");
		if (ArrayUtils.isNotEmpty(ids)) {
			condition.put("caseIdMore",ids);
			EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
			roleService.getOrgCode(empModel.getId(),condition);
			common(condition);
			list= caseApplyService.query(condition);
		}
		return super.createExcelView("xiecui", list, "待处理协催记录", null,null); 
	}

	
	
	@Override
	protected String getBaseName() {
		return "collection/xiecui/";
	}
}
