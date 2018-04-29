package com.kangde.collection.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.ApproveDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.service.CaseApprovalLeaveService;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;

/**
 * 审批 Controller
 * @author wcy
 * @date 2016年5月24日17:37:47
 */
@Controller
@RequestMapping("collection/caseapprovalleave")
public class CaseApprovalLeaveController extends RestfulUrlController<ApproveDto,String> {

	// 权限码前缀
	private static final String PERMISION_PREFIX = "caseapprovalleave";
		
	@Autowired
	private CaseApprovalLeaveService caseApprovalLeaveService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private CaseBatchService caseBatchService;
	
	//审批通过
	public static final Integer STATE_PASS = 1;
	//审批不通过
	public static final Integer STATE_NOAPPROVAL = 2;
	
	
	public CaseApprovalLeaveController() {
		
		//2016年11月24日17:29:15 添加排序
		columnNames.put("caseModel.caseCode", "ci.case_code");
		
		columnNames.put("caseModel.collecStateId", "ci.collec_state_id");
		columnNames.put("caseModel.caseDate", "ci.case_date");
		columnNames.put("employeeInfo.userName", "ei.user_name");
		
		columnNames.put("caseModel.caseNum", "ci.case_num");
		columnNames.put("caseModel.caseCard", "ci.case_card");
		columnNames.put("applyTime", "ar.apply_time");
		
		
	}
	
	/** 访问审批页面 */
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("id", id);//set到页面
			return view;
	}
	
	/** 访问审批页面 */
	@RequestMapping(value = "inputno" + "/{id}", method = RequestMethod.GET)
	public ModelAndView pageInput1(@PathVariable("id") String id) {
		ModelAndView view = createBaseView("inputno");
		view.addObject("id", id);//set到页面
		return view;
	}
	/** 留案展示方法  */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@RequestMapping(value = "queryforLeave",method = RequestMethod.POST)
	@ResponseBody
	public SearchResult<ApproveDto> queryforLeave() {
		ParamCondition condition = parseCondition("*");
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		String CodeAll=null;//多个案件序列号
		String CodeOne=null;//单个案件序列号
		String caseFileNoAll=null;//多个档案号
		String caseFileNoOne=null;//单个档案号
		String caseNumAll=null;//多个档案号
		String caseNumOne=null;//单个档案号
		String caseCardAll=null;//多个卡号
		String caseCardOne=null;//单个卡号
		String content = condition.get("content");//内容
		String selectNum= condition.get("selectByNum");//选中的类型
		String decompose=caseService.decompose(content);
		if("1".equals(selectNum)){
			if(StringUtils.isNotBlank(decompose)){
			if(decompose.contains(",")){
				CodeAll=decompose;
				condition.put("CodeAll", CodeAll);
			}
			}else{
				CodeOne=condition.get("content");
				condition.put("CodeOne", CodeOne);
			}
		}else if("2".equals(selectNum)){
			if(StringUtils.isNotBlank(decompose)){
				if(decompose.contains(",")){
					caseFileNoAll=decompose;
					condition.put("caseFileNoAll", caseFileNoAll);
				}
				}else{
					caseFileNoOne=condition.get("content");
					condition.put("caseFileNoOne", caseFileNoOne);
				}
		}else if("3".equals(selectNum)){
			if(StringUtils.isNotBlank(decompose)){
				if(decompose.contains(",")){
					caseNumAll=decompose;
					condition.put("caseNumAll", caseNumAll);
				}
				}else{
					caseNumOne=condition.get("content");
					condition.put("caseNumOne", caseNumOne);
				}
		}else if("4".equals(selectNum)){
			if(StringUtils.isNotBlank(decompose)){
				if(decompose.contains(",")){
					caseCardAll=decompose;
					condition.put("caseCardAll", caseCardAll);
				}
				}else{
					caseCardOne=condition.get("content");
					condition.put("caseCardOne", caseCardOne);
				}
			}
		
		String code=condition.get("batchCode");
		String batchCode=caseBatchService.BatchCode(code);
		condition.put("batchCode", batchCode);
		return caseApprovalLeaveService.queryforLeave(condition);
	}
	
	// 导出所选中
	@RequiresPermissions(value = { PERMISION_PREFIX + ":choose" })
	@RequestMapping(value = "/exportForIds", method = RequestMethod.POST)
	public ModelAndView exportAllExcel(String[] ids) {
	
		ParamCondition condition = parseCondition("*");
		if(ArrayUtils.isNotEmpty(ids)){
			condition.put("caseIdMore",ids );
		}
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		List<ApproveDto> list =caseApprovalLeaveService.queryforLeaveList(condition);
		
		return super.createExcelView("caseapprovalleave", list, "留案信息", null, null);
	}
	
	//Excel导出选查案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":select" })
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
				EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
				roleService.getOrgCode(empModel.getId(),condition);
				
		List<ApproveDto> list =caseApprovalLeaveService.queryforLeaveList(condition);
		return super.createExcelView("caseapprovalleave", list, "留案信息", null, null);
	}
	
	/** 留案审批同意方法*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":approvalYes" })
	@RequestMapping(value = "approvalYes")
	@ResponseBody
	public AjaxResult approvalYes(ApproveRecordModel model){
		model.setApproveState(1);// 审批通过状态
		model.setState(2);// 审批通过后，数据转到待安排程
		model.setModifyTime(new Date()); // 修改时间
		model.setStayTime(new Date());//留案时间
		Date date=new Date();
		int day;
		if(model.getStayPeriod()==null){
			throw new ServiceException("请输入留案周期.");
		}else if(model.getStayPeriod()!=null){
			day=DateUtil.compareDate(model.getStayPeriod(), date, "yyyy-MM-dd");
			if(day<=0){
				throw new ServiceException("留案周期必须大于当前日期.");
			}else if(day>0){
				caseApprovalLeaveService.approvalYes(model);
				AjaxResult result = AjaxResult.success(getSuccessMessage());
				 return result;
			}
		}
		return null;
	}	
	
	/** 外访不审批同意方法 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":approvalNo" })
	@RequestMapping(value = "approvalNo")
	@ResponseBody
	public AjaxResult approvalNo(ApproveRecordModel model){
		model.setApproveState(2);// 审批不通过状态
		model.setModifyTime(new Date()); // 修改时间
		model.setApproveState(STATE_NOAPPROVAL);
		caseApprovalLeaveService.approvalYes(model);
		//修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}	
	
	@Override
	protected String getBaseName() {
		return "collection/caseapprovalleave/";
	}
	
}
