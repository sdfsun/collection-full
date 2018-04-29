package com.kangde.collection.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.service.CasePaidService;
import com.kangde.collection.service.CaseService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.NumberUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/casepaid")
public class CasePaidController extends RestfulUrlController<CasePaid, String> {

	@Autowired
	CasePaidService casePaidService;
	@Autowired
	CaseService caseService;

	@Override
	public ModelAndView pageIndex() {
		ModelAndView view = createBaseView(PAGE_INDEX);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(value=PermissionConst.detail_casepaid_save)
	public AjaxResult save(CasePaid model) {
		String caseId = model.getCaseId();
		CaseModel caseModel = caseService.findById(caseId);

		String rate = caseModel.getEntrustRate();
		if(rate != null){
			double entrustRate = Double.parseDouble(rate);
			Double cpMoney = model.getCpMoney();
			double entrustPaid = NumberUtil.doubleMultiply(cpMoney, entrustRate);
			model.setEntrustPaidRate(entrustRate);
			model.setEntrustPaid(entrustPaid);
		}
		Double agentRate = caseModel.getAgentRate();
		if(agentRate!=null){
			Double cpMoney = model.getCpMoney();
			Double backPaid = NumberUtil.doubleMultiply(cpMoney, agentRate);
			model.setBackPaidRate(agentRate);
			model.setBackPaid(backPaid);
		}
		
		Date cpTime = model.getCpTime();
		int compareTo = cpTime.compareTo(new Date());
		if (compareTo > 0) {
			return AjaxResult.failure("CP日期不能大于当前日期");
		}
		return super.save(model);
	}

	@Override
	public List<CasePaid> list() {
		ParamCondition condition = this.parseCondition("caseId");
		return super.query(condition);
	}

	@ResponseBody
	@RequestMapping(value = "queryPTP", method = RequestMethod.GET)
	public SearchResult<CasePaid> queryPTPForPage() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", Lists.newArrayList(0));
		SearchResult<CasePaid> list = casePaidService.queryPTPorCPForPage(condition);
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "queryCP", method = RequestMethod.GET)
	public SearchResult<CasePaid> queryCPForPage() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", Lists.newArrayList(1,2,4));
		//condition.put("repayType", "1"); //查询正常还款 排除自来帐
		SearchResult<CasePaid> list = casePaidService.queryPTPorCPForPage(condition);
		return list;
	}

	@Override
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		view.addObject("id", UUIDUtil.UUID32());
		view.addObject("cpTime", DateUtil.thisDate());
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/toCP", method = RequestMethod.POST)
	public AjaxResult toCP(String id) {
		casePaidService.toCP(id);
		return AjaxResult.success(getSuccessMessage());
	}

	@Override
	protected String getBaseName() {
		return "collection/casepaid/";
	}

}
