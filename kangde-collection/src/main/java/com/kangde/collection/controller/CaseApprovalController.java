package com.kangde.collection.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.exception.CaseApproveException;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.CaseApprovalService;
import com.kangde.collection.service.CaseService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 审批 Controller
 * 
 * @author wcy
 * @date 2016年5月24日17:37:47
 */
@Controller
@RequestMapping("collection/caseapproval")
public class CaseApprovalController extends RestfulUrlController<CaseDto, String> {

	@Autowired
	private CaseApprovalService caseApprovalservice;
	@Autowired
	private CaseService caseService;

	// 审批通过
	public static final Integer STATE_PASS = 1;
	// 审批不通过
	public static final Integer STATE_NOAPPROVAL = 2;

	/** 申请留案 */
	@RequestMapping(value = "casedetailstay", method = RequestMethod.GET)
	public ModelAndView casedetailstay() {
		HttpServletRequest request = this.getRequest();
		ModelAndView view = createBaseView("casedetailstay");
		String caseId = request.getParameter("caseId");
		view.addObject("caseId", caseId);
		return view;
	}

	/** 查询留案申请记录 */
	@ResponseBody
	@RequestMapping(value = "queryStayByCaseId", method = RequestMethod.GET)
	public SearchResult<ApproveRecordModel> queryStayByCaseId() {
		ParamCondition condition = parseCondition("*");
		return caseApprovalservice.queryStayByCaseId(condition);
	}

	@RequestMapping(value = "applyStay", method = RequestMethod.GET)
	public ModelAndView applyStay() {
		HttpServletRequest request = this.getRequest();
		ModelAndView view = createBaseView("applyStayinput");
		String caseId = request.getParameter("caseId");
		view.addObject("caseId", caseId);
		return view;
	}

	/** 保存 留案申请记录 */
	@RequestMapping(value = "applyStay", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_stay_save)
	public AjaxResult saveApplyStay(ApproveRecordModel model) {
		// 查询审批待审批的记录
		synchronized (this) {
			try {
				checkStatus(model.getCaseId());
			} catch (CaseApproveException e) {
				throw new ServiceException(e.getMessage());
			}
			EmployeeInfoModel user = SecurityUtil.getCurrentUser();
			model.setApplyEmpId(user.getId());
			Date time = new Date();
			model.setApplyTime(time);
			model.setApproveState(0);
			model.setId(UUIDUtil.UUID32());
			model.setCreateTime(time);
			model.setModifyTime(time);
			model.setCreateEmpId(user.getId());
			caseApprovalservice.saveApproveRecord(model);
		}
		AjaxResult success = AjaxResult.success(getSuccessMessage());
		return success;
	}

	/** 保存 留案申请记录 进入申请页面前校验, 如果已经存在申请记录 则不用进入申请页面 */
	@ResponseBody
	@RequestMapping(value = "checkCaseStatus", method = RequestMethod.POST)
	public AjaxResult checkCaseStatus(String caseId) {
		// 查询审批待审批的记录
		try {
			checkStatus(caseId);
		} catch (CaseApproveException e) {
			AjaxResult result = AjaxResult.failure(e.getMessage());
			return result;
		}
		return AjaxResult.success(getSuccessMessage());
	}

	private void checkStatus(String caseId) throws CaseApproveException {
		CaseModel caseInfo = caseService.findById(caseId);
		if (caseInfo.getState() != null && caseInfo.getState() == 1) {
			throw new CaseApproveException("案件为暂停状态, 不允许申请留案");
		}
		List<ApproveRecordModel> list = caseApprovalservice.findByCaseId(caseId);
		if (CollectionUtils.isNotEmpty(list)) {
			throw new CaseApproveException("已存在留案申请记录!");
		}
	}

	/**
	 * 外访展示方法
	 */
	@RequestMapping(value = "queryforGo")
	@ResponseBody
	public SearchResult<CaseDto> queryforGo() {
		ParamCondition condition = parseCondition("*");
		return caseApprovalservice.queryforGo(condition);
	}

	/**
	 * 留案展示方法
	 */
	@RequestMapping(value = "queryforLeave")
	@ResponseBody
	public SearchResult<CaseDto> queryfor() {
		ParamCondition condition = parseCondition("*");
		return caseApprovalservice.queryforLeave(condition);
	}

	/**
	 * 审批方法。 点击审批，访问审批页面时都会先进这个方法。 重写了父类的方法。 参数id 是case_Id 加上标示 留案是0 外访是1
	 */
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		if (id != null && StringUtils.isNotBlank(id.toString())) {
			String i = id.substring(id.length() - 1, id.length());// 截取字符串，
																	// 截取最后一位。
			String caseId = id.substring(0, id.length() - 1);
			view.addObject("caseId", caseId);// set到页面
			view.addObject("i", i);// set到页面
		}
		return view;
	}

	/**
	 * 留案审批同意方法
	 */
	@RequestMapping(value = "approvalLeave")
	@ResponseBody
	// 留案审批同意操作
	public AjaxResult approvalLeave(ApproveRecordModel model) {
		model.setApproveState(STATE_PASS);
		model.setModifyTime(new Date());
		caseApprovalservice.updatefoApp(model);
		// 修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 留案审批不同意方法
	 */
	@RequestMapping(value = "approvalLeaveNo")
	@ResponseBody
	// 留案审批不同意操作
	public AjaxResult approvalLeaveNo(ApproveRecordModel model) {
		model.setApproveState(STATE_NOAPPROVAL);
		model.setModifyTime(new Date());
		caseApprovalservice.updatefoApp(model);
		// 修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 外访审批同意方法
	 */
	@RequestMapping(value = "approvalForgo")
	@ResponseBody
	// 外访审批同意操作
	public AjaxResult approvalForgo(VisitRecordModel model) {
		model.setApproveState(STATE_PASS);
		model.setState(2);
		caseApprovalservice.updatefoVi(model);
		// 修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 外访不审批同意方法
	 */
	@RequestMapping(value = "approvalForgoNo")
	@ResponseBody
	// 外访不审批同意操作
	public AjaxResult approvalForgoNo(VisitRecordModel model) {
		model.setApproveState(STATE_NOAPPROVAL);
		caseApprovalservice.updatefoVi(model);
		// 修改成功提示操作成功
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@Override
	protected String getBaseName() {
		return "collection/caseapproval/";
	}

}
