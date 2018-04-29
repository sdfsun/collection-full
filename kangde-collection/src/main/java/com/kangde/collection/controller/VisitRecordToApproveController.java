package com.kangde.collection.controller;


import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.VisitDto;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.collection.service.VisitRecordToApproveService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;

/**
 * 
  * @Description: 待审批外访
  * @author lidengwen
  * @date 2016年8月13日 下午4:20:28
  *
 */
@Controller
@RequestMapping("collection/visitrecordtoapprove")
public class VisitRecordToApproveController extends RestfulUrlController<VisitDto,String> {

	// 权限码前缀
		private static final String PERMISION_PREFIX = "visitrecordtoapprove";
		
	@Autowired
	private VisitRecordToApproveService visitRecordToApproveService;
	
	@Autowired
	private VisitRecordService visitRecordService;
	
	@Autowired
	private RoleService roleService;
	
	//审批通过
	public static final Integer STATE_PASS = 1;
	//审批不通过
	public static final Integer STATE_NOAPPROVAL = 2;
	
	
	public VisitRecordToApproveController() {
		columnNames.put("caseModel.collecStateId", "c.collec_state_id");
		columnNames.put("caseModel.caseCode", "c.case_code");
		columnNames.put("caseModel.caseMoney", "c.case_money");
		columnNames.put("paidNum", "paidNum");
		columnNames.put("visitAddress", "vr.visit_address");
		columnNames.put("applyTime", "vr.apply_time");
	}
	

	@RequestMapping(value = "queryToApprove")
	@ResponseBody
	public SearchResult<VisitRecordDto> queryToApprove() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", 0);
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		SearchResult<VisitRecordDto> list = visitRecordService.queryToApproveORToAppoint(condition);
		return list;
	}
	/** 访问审批页面 */
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("id", id);//set到页面
			return view;
	}
		
	/** 外访审批同意方法*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":approvalYes" })
	@RequestMapping(value = "approvalForgo")
	@ResponseBody
	public AjaxResult approvalForgo(VisitRecordModel model){
		model.setState(2);// 审批通过后，数据转到待安排程
		model.setModifyTime(new Date()); // 修改时间
		visitRecordToApproveService.approveYes(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}	
	
	/** 外访不审批同意方法 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":approvalNo" })
	@RequestMapping(value = "approvalForgoNo")
	@ResponseBody
	public AjaxResult approvalForgoNo(VisitRecordModel model){
		model.setState(1);
		model.setModifyTime(new Date()); // 修改时间
		model.setApproveState(STATE_NOAPPROVAL);
		visitRecordToApproveService.approveNo(model);
		//修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}	

	@Override
	protected String getBaseName() {
		return "collection/visitrecordtoapprove/";
	}
	
}
