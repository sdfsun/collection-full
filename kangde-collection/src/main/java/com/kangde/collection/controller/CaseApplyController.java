package com.kangde.collection.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.model.CaseApply;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.model.ExpressModel;
import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.collection.model.TelecomModel;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.CensusRegisterService;
import com.kangde.collection.service.ExpressService;
import com.kangde.collection.service.SocialSecurityService;
import com.kangde.collection.service.TelecomService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * @author lidengwen
 * @date 2016年7月16日 下午5:24:13
 *
 */
@Controller
@RequestMapping("collection/caseapply")
public class CaseApplyController extends RestfulUrlController<CaseApply, String> {

	/** 户籍 */
	@Autowired
	private CensusRegisterService censusRegisterService;
	/** 社保 */
	@Autowired
	private SocialSecurityService socialSecurityService;
	/** 电信 */
	@Autowired
	private TelecomService telecomService;
	/** 快递  */
	@Autowired
	private ExpressService expressService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseApplyService caseApplyService;

	@Override
	@RequiresPermissions(logical = Logical.OR, value = { PermissionConst.detail_repair_save,
			PermissionConst.detail_xiecui_save })
	public AjaxResult save(CaseApply model) {
		return super.save(model);
	}
	@RequestMapping(value = "batchSave",method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.case_repair_batchsave)
	public AjaxResult batchsave(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids[]");
		String applyContent=request.getParameter("applyContent");
		String applyType=request.getParameter("applyType");
		caseApplyService.batchSave(ids,applyContent,Integer.parseInt(applyType));
		return AjaxResult.success(getSuccessMessage());
	}

	/** 详情页 资料修复 */
	@RequestMapping(value = "casedetailRepair", method = RequestMethod.GET)
	public ModelAndView casedetailRepair() {
		ModelAndView view = createBaseView("casedetailRepair");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	/** 详情页 查资记录 */
	@ResponseBody
	@RequestMapping(value = "queryMaterialByCaseId", method = RequestMethod.GET)
	public SearchResult<CaseApply> queryRepairForPage() {
		ParamCondition condition = parseCondition("*");
		SearchResult<CaseApply> page = caseApplyService.queryMaterialByCaseId(condition);
		return page;
	}

	/** 详情页 资料修复 */
	@RequestMapping(value = "casedetailRepairDetail", method = RequestMethod.GET)
	public ModelAndView casedetailRepairDetail() {
		ModelAndView view = createBaseView("casedetailRepairDetail");
		String id = this.getRequest().getParameter("id");
		CaseApply caseApply = this.findById(id);
		view.addObject("caseApply", caseApply);
		return view;
	}

	/** 详情页 协催记录 */
	@RequestMapping(value = "casedetailxiecui", method = RequestMethod.GET)
	public ModelAndView casedetailxiecui() {
		ModelAndView view = createBaseView("casedetailxiecui");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	/** 详情页 协催记录 */
	@ResponseBody
	@RequestMapping(value = "querycasedetailxiecui", method = RequestMethod.GET)
	public SearchResult<CaseApply> querycasedetailxiecui() {
		ParamCondition condition = parseCondition("*");
		SearchResult<CaseApply> page = caseApplyService.querycasedetailxiecui(condition);
		return page;
	}

	@RequestMapping(value = "casedetailapplyxiecui", method = RequestMethod.GET)
	public ModelAndView casedetailapplyxiecui() {
		ModelAndView view = createBaseView("casedetailapplyxiecui");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@RequestMapping(value = "casedetailviewxiecui", method = RequestMethod.GET)
	public ModelAndView casedetailviewxiecui() {
		ModelAndView view = createBaseView("casedetailviewxiecui");
		String id = this.getRequest().getParameter("id");
		CaseApply caseApply = this.findById(id);
		view.addObject("caseApply", caseApply);
		return view;
	}

	/** 详情页 查询信函 */
	@RequestMapping(value = "casedetailletter", method = RequestMethod.GET)
	public ModelAndView casedetailletter() {
		ModelAndView view = createBaseView("casedetailletter");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	/** 详情页 查询信函 */
	@ResponseBody
	@RequestMapping(value = "queryLetterByCaseId", method = RequestMethod.GET)
	public SearchResult<CaseApply> queryLetterByCaseId() {
		ParamCondition condition = parseCondition("*");
		SearchResult<CaseApply> page = caseApplyService.queryLetterByCaseId(condition);
		return page;
	}

	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@RequestMapping(value = "censusRegister", method = RequestMethod.GET)
	public ModelAndView censusRegister(String caseApplyId, String caseId) {
		ModelAndView view = createBaseView("censusRegister");
		List<CensusRegisterModel> list = censusRegisterService.findByCaseApplyId(caseApplyId);
		if (CollectionUtils.isNotEmpty(list)) {
			view.addObject("censusRegister", list.get(0));
		}
		CaseModel casemodel = caseService.findById(caseId);
		view.addObject("caseId", caseId);
		view.addObject("caseCode", casemodel.getCaseCode());
		view.addObject("caseName", casemodel.getCaseName());
		view.addObject("caseNum", casemodel.getCaseNum());
		return view;
	}
	
	@RequestMapping(value = "censuskuaidi", method = RequestMethod.GET)
	public ModelAndView censusExpress(String caseApplyId, String caseId) {
		ModelAndView view = createBaseView("censuskuaidi");
		CaseModel casemodel = caseService.findById(caseId);
		
		List<ExpressModel> list =expressService.findByCaseApplyId(caseApplyId);
		if (CollectionUtils.isNotEmpty(list)) {
			view.addObject("censuskuaidi", list.get(0));
		}
		view.addObject("caseId", caseId);
		view.addObject("caseCode", casemodel.getCaseCode());
		view.addObject("caseName", casemodel.getCaseName());
		view.addObject("caseNum", casemodel.getCaseNum());
		return view;
	}

	@RequestMapping(value = "telecom", method = RequestMethod.GET)
	public ModelAndView telecom(String caseApplyId, String caseId) {
		ModelAndView view = createBaseView("telecom");
		List<TelecomModel> list = telecomService.findbyCaseApplyId(caseApplyId);
		if (CollectionUtils.isNotEmpty(list)) {
			view.addObject("telecom", list.get(0));
		}
		CaseModel casemodel = caseService.findById(caseId);
		view.addObject("caseId", caseId);
		view.addObject("caseCode", casemodel.getCaseCode());
		view.addObject("caseName", casemodel.getCaseName());
		view.addObject("caseNum", casemodel.getCaseNum());
		return view;
	}

	@RequestMapping(value = "socialSecurity", method = RequestMethod.GET)
	public ModelAndView socialSecurity(String caseApplyId, String caseId) {
		ModelAndView view = createBaseView("socialSecurity");
		List<SocialSecurityModel> list = socialSecurityService.findByCaseApplyId(caseApplyId);
		if (CollectionUtils.isNotEmpty(list)) {
			view.addObject("socialSecurity", list.get(0));
		}
		CaseModel casemodel = caseService.findById(caseId);
		view.addObject("caseId", caseId);
		view.addObject("caseCode", casemodel.getCaseCode());
		view.addObject("caseName", casemodel.getCaseName());
		view.addObject("caseNum", casemodel.getCaseNum());
		return view;
	}

	@RequestMapping(value = "deleteById")
	@ResponseBody
	public AjaxResult deleteById(String id) {
		caseApplyService.deleteById(id);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@Override
	protected String getBaseName() {
		return "collection/caseapply/";
	}
}
