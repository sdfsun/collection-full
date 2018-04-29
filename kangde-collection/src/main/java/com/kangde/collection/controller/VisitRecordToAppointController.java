package com.kangde.collection.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordMessageReminderService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.RoleService;

/**
 * 
 * @Description: 待排程外访
 * @author lidengwen
 * @date 2016年8月13日 下午4:46:37
 *
 */
@Controller
@RequestMapping("collection/visitrecordtoappoint")
public class VisitRecordToAppointController extends RestfulUrlController<VisitRecordModel, String> {
	
	// 权限码前缀
	private static final String PERMISION_PREFIX = "visitrecordtoappoint";
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private VisitRecordMessageReminderService visitRecordMessageReminderService;
	public VisitRecordToAppointController() {
		columnNames.put("caseModel.collecStateId", "c.collec_state_id");
		columnNames.put("caseModel.caseCode", "c.case_code");
		columnNames.put("caseModel.caseMoney", "c.case_money");
		columnNames.put("paidNum", "paidNum");
		columnNames.put("visitAddress", "vr.visit_address");
		columnNames.put("applyTime", "vr.apply_time");
	}
	
	/**
	 * 待排程
	 */
	@RequestMapping(value = "queryToAppoint")
	@ResponseBody
	public SearchResult<VisitRecordDto> queryToAppoint() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", 2);
		
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
				
		SearchResult<VisitRecordDto> list = visitRecordService.queryToApproveORToAppoint(condition);
		return list;
	}

	@RequestMapping(value = "inputpoint", method = RequestMethod.GET)
	public ModelAndView inputpoint() {
		ModelAndView view = createBaseView("cancelVisit");
		return view;
	}

	/**
	 * 外访排程
	 * 
	 * @param state
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":PlanToVisit" })
	@RequestMapping(value = "appointVisitor")
	@ResponseBody
	public AjaxResult appointVisitor(HttpServletRequest request) throws ParseException {
		String[]ids=request.getParameterValues("ids[]");
		String visitUserIds=request.getParameter("userid");
		String visitTime=request.getParameter("visitTime");
		
		ArrayList<VisitRecordModel> list = Lists.newArrayList();
		for (String visitRecordId : ids) {
			VisitRecordModel updateModel=appointAction(visitRecordId, visitUserIds, visitTime);
			if(updateModel!=null){
				list.add(updateModel);
			}
		}
		visitRecordService.appointVisitorBatch(list);
		
		visitRecordMessageReminderService.saveMessageMinders(ids, "已进行排程");
		
		return AjaxResult.success(getSuccessMessage());
	}

	private VisitRecordModel appointAction(String visitRecordId, String visitUserIds, String visitTime) throws ParseException {
		VisitRecordModel visitModel = visitRecordService.findById(visitRecordId);
		if (visitUserIds != null) {
			visitModel.setState(3);
			visitModel.setModifyTime(new Date());
			visitModel.setVisitUserId(visitUserIds);
			String[] useridArray = visitUserIds.split(",");
			StringBuffer names = new StringBuffer();
			for (String usersid : useridArray) {
				EmployeeInfoModel emp = employeeInfoService.findById(usersid);
				names.append(emp.getUserName() + ",");
			}
			String nameStr = names.toString();
			if (nameStr.length() > 0) {
				String substring = nameStr.substring(0, nameStr.length() - 1);
				visitModel.setVisitUser(substring);
			}
			
			if(StringUtils.isNotBlank(visitTime)){
				SimpleDateFormat sdf=new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
				visitModel.setVisitTime(sdf.parse(visitTime));
				
			}
			return visitModel;
		}
		return null;
	}

	/**
	 * 撤销外访
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":cancelVisit" })
	@RequestMapping(value = "cancelVisit")
	@ResponseBody
	public AjaxResult cancelVisit(HttpServletRequest request) {
		String[]ids=request.getParameterValues("ids[]");
		String approveOpinion = request.getParameter("approveOpinion");
		visitRecordService.cancelVisit(ids, approveOpinion);
		visitRecordMessageReminderService.saveMessageMinders(ids, "已撤销外访");
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@Override
	protected String getBaseName() {
		return "collection/visitrecordtoappoint/";
	}

}
