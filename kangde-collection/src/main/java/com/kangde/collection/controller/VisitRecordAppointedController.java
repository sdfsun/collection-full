package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.google.common.collect.Lists;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.AttachmentService;
import com.kangde.collection.service.VisitRecordMessageReminderService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.collection.view.VisitDocView;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.VisitTemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.RoleService;
import com.kangde.sys.service.VisitTemplateService;

/**
 * 
 * @Description: 已排程外访
 * @author lidengwen
 * @date 2016年8月13日 下午5:48:50
 *
 */
@Controller
@RequestMapping("collection/visitrecordappointed")
public class VisitRecordAppointedController extends RestfulUrlController<VisitRecordModel, String> {
	
	// 权限码前缀
	private static final String PERMISION_PREFIX = "visitrecordappointed";
		
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private VisitTemplateService visitTemplateService;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private VisitRecordMessageReminderService visitRecordMessageReminderService;
	@Autowired
	private  AddressService addressService;
	
	public VisitRecordAppointedController() {
		columnNames.put("caseModel.collecStateId", "c.collec_state_id");
		columnNames.put("caseModel.caseCode", "c.case_code");
		columnNames.put("caseModel.caseMoney", "c.case_money");
		columnNames.put("paidNum", "paidNum");
		columnNames.put("visitAddress", "vr.visit_address");
	}
	
	/**
	 * 已排程
	 */
	@RequestMapping(value = "queryAppointed")
	@ResponseBody
	public SearchResult<VisitRecordDto> queryAppointed() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", 3);
		
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
				
		SearchResult<VisitRecordDto> list = visitRecordService.queryAppointedORFinished(condition);
		return list;
	}

	@RequestMapping(value = "inputpoint" + "/{ids}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("ids") String ids) {
		ModelAndView view = createBaseView("inputpoint");
		if(ids !=null && StringUtils.isNotBlank(ids.toString())){
			view.addObject("ids", ids);
		}
		return view;
	}
	
	@RequestMapping(value = "revoke" + "/{id}", method = RequestMethod.GET)
	public ModelAndView revoke(@PathVariable("id") String id) {
		ModelAndView view = createBaseView("inputpoint");
		if(id !=null && StringUtils.isNotBlank(id.toString())){
			VisitRecordModel model=visitRecordService.findByAddresId(id);
			
			view.addObject("ids", model.getId());
		}
		return view;
	}
	
	/**
	 * 
	 * 撤销外访
	 * 
	 * @param state
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":cancelVisit" })	
	@RequestMapping(value = "cancelVisit")
	@ResponseBody
	// 修改状态
	public AjaxResult cancelVisit(VisitRecordModel model) {
		String[] ids = model.getId().split(",");
		visitRecordService.cancelVisit(ids, model.getApproveOpinion());
		visitRecordMessageReminderService.saveMessageMinders(new String[]{model.getId()}, "已撤销外访");
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 
	 * 返回排程
	 * 
	 * @param state
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":backToAppoint" })
	@RequestMapping(value = "backToAppoint", method = RequestMethod.POST) 
	@ResponseBody
	// 修改状态
	public AjaxResult backToAppoint(String[] ids) {
		visitRecordService.backToAppoint(ids);
		for (String id : ids) {
			visitRecordMessageReminderService.saveMessageMinders(new String[]{id}, "已返回排程");
		}
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":finishToVisit" })
	@RequestMapping(value = "finishVisit")
	@ResponseBody
	// 完成外访
	public AjaxResult finishVisit(VisitRecordModel model) {
		//判断是否上传了外访附件
		ParamCondition condition=new ParamCondition();
		condition.put("businessId", model.getId());
		condition.put("type", "vis");
		int queryCount = attachmentService.queryCount(condition);
		/*if(queryCount==0){
			throw new ServiceException("还未上传外访附件");
		}*/
		visitRecordService.finishVisit(model);
		visitRecordMessageReminderService.saveMessageMinders(new String[]{model.getId()}, "已完成外访");
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 跳转完成外访
	 * 
	 * @return
	 */
	@RequestMapping(value = "finish", method = RequestMethod.GET)
	public ModelAndView finish(String id, String caseId) {
		ModelAndView view = createBaseView("finish");
		view.addObject("id", id);
		view.addObject("caseId", caseId);
		view.addObject("actualVisitTime", DateUtil.thisDate());
		return view;
	}

	/**
	 * 导出 外访/信函 Word模板 wcy
	 * 
	 * @param ids:信函id、
	 *            外访id templateId:模板id type：类型 1外访 2信函
	 * @2016年7月7日14:25:23
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportSelected" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] ids, @RequestParam("templateId") String templateId, String type) {
		String templateName = "";
		List<VisitShowView> list = null;
		// List<CaseLinkmanModel> link = null;// 存放联系人信息
		if (type.equals("1")) {
			templateName = "外访";
			if (ArrayUtils.isNotEmpty(ids)) {
				ParamCondition condition = new ParamCondition();
				condition.put("visitRecordIds", Arrays.asList(ids));
				list = visitRecordService.findVisitRecordIds(Arrays.asList(ids));// 查询勾选数据的详细信息。
			}
			// for (int i = 0; i < list.size(); i++) {// 循环勾选数据 ，去查询案件的联系人，
			// 并且set到
			// VisitShowView dto = list.get(i);
			// if (StringUtils.isNotBlank(dto.getCaseId())) {
			// //link = caseLinkmanService.findCaseIds(dto.getCaseId());//
			// 拿到联系人信息
			// /* list.get(i).setLinkmanModel(link);*/
			// }
			// }
			VisitTemplateModel visit = visitTemplateService.findById(templateId);// 拿到模板信息
			ModelAndView view = new ModelAndView("visitDocView");
			view.addObject(VisitDocView.DOC_NAME, templateName).addObject(VisitDocView.DOC_CONTENT, visit.getContent())
					.addObject(VisitDocView.VISIT_BEANS, list);
			return view;
		}
		return null;
	}

	/**
	 * Excel导出所查数据 wcy
	 * 
	 * @2016年7月7日14:25:23
	 * @return
	 */
	// Excel导出选查案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportQuery" })
	@RequestMapping(value = "/exportQueryExcel", method = RequestMethod.POST)
	public ModelAndView exportExcel() {
		ParamCondition condition = parseCondition("*");
		String templateId = condition.remove("templateId");
		VisitTemplateModel visit = visitTemplateService.findById(templateId);// 拿到模板信息
		
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
			EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
			roleService.getOrgCode(empModel.getId(),condition);
				
		List<VisitShowView> list = visitRecordService.queryAll(condition);

		ModelAndView view = new ModelAndView("visitDocView");
		view.addObject(VisitDocView.DOC_NAME, "外访").addObject(VisitDocView.DOC_CONTENT, visit.getContent())
				.addObject(VisitDocView.VISIT_BEANS, list);
		return view;
	}

	@RequestMapping(value = "getCurrentOrgVisitor")
	@ResponseBody
	public List<Combobox> getCurrentOrgVisitorCombobox() {
		List<Combobox> resultList = new ArrayList<Combobox>();
		Combobox selectCombobox = new Combobox("", "");
		resultList.add(selectCombobox);
		List<EmployeeInfoModel> emplist = getCurrentOrgVisitor();
		for (EmployeeInfoModel emp : emplist) {
			Combobox combobox = new Combobox(emp.getId(), emp.getUserName());
			resultList.add(combobox);
		}
		return resultList;
	}

	private List<EmployeeInfoModel> getCurrentOrgVisitor() {
		List<EmployeeInfoModel> list = new ArrayList<EmployeeInfoModel>();
		if (visitRecordService.isCollectorOrVisitor()) {
			EmployeeInfoModel emp = employeeInfoService.findById(SecurityUtil.getCurrentUser().getId());
			list.add(emp);
		} else {
			List<EmployeeInfoModel> emps = employeeInfoService.findEmpsByOrg(SecurityUtil.getCurrentUser().getOrgId());
			if (CollectionUtils.isNotEmpty(emps)) {
				list.addAll(emps);
			}
		}
		return list;
	}

	@Override
	protected String getBaseName() {
		return "collection/visitrecordappointed/";
	}

}
