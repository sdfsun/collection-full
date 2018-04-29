package com.kangde.collection.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.dto.CaseDto;
import com.kangde.collection.dto.CasePaidDto;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.CasePaidMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.service.CasePaidService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.NumberUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;

@Controller
@RequestMapping("collection/casepaidshow")
public class CasePaidShowController extends RestfulUrlController<CaseDto, String> {
	// 权限码前缀
	private static final String PERMISION_PREFIX = "casepaidshow";

	@Autowired
	private CasePaidService casePaidService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private CasePaidMapper casePaidMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageReminderService messageReminderService;
	/**
	 * 跳转到导出所有excel 选择字段页面 wcy
	 * 
	 * @date 2016年7月4日10:47:58
	 * @return
	 */
	@RequestMapping(value = "excelAll", method = RequestMethod.GET)
	public ModelAndView excelall() {
		ModelAndView view = createBaseView("excelAll");
		return view;
	}

	// 前端排序字段
	public CasePaidShowController() {
		columnNames.put("caseModel.agentRate", "ci.agent_rate");
		columnNames.put("caseModel.caseMoney", "ci.case_money");
		columnNames.put("paidTime", "cp.paid_time");
		columnNames.put("actual_visit_time", "actual_visit_time");
		
		columnNames.put("caseModel.caseCode", "ci.case_code");
		columnNames.put("batchModel.entrustName", "entrustName");
		columnNames.put("caseModel.caseNum", "ci.case_num");
		
		columnNames.put("caseModel.caseNum", "ci.case_num");
		columnNames.put("caseModel.caseCard", "ci.case_card");
		columnNames.put("cpMoney", "cp.cp_money");
		
		
		columnNames.put("paidNum", "cp.paid_num");
		columnNames.put("compBorker", "compBorker");
		columnNames.put("commission", "commission");
		columnNames.put("cpTime", "cp.cp_time");
		columnNames.put("visit_user", "vr.visit_user");
		columnNames.put("organization.soName", "so.name");
		columnNames.put("cpName", "cpName");
		
		
	}

	/*@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		return super.index();
	}*/

	// 导出所选 参数 ids 选中id,array选中勾选字段
	@RequiresPermissions(value = { PERMISION_PREFIX + ":choose" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] ids, String array[]) {
		if(array.length==0){
			throw new ServiceException("请勾选导出字段，至少一个！");
		}
		List<CasePaidDto> list = null;
		if (ArrayUtils.isNotEmpty(ids)) {
			ParamCondition condition = parseCondition("*");
			List<String> caseIdMore=Arrays.asList(ids);
			condition.put("caseIdMore", StringUtils.join(caseIdMore, ","));
			list = casePaidService.queryForIds(condition);
		}
		return exportCaseExcel(list, array);
	}

	// 导出所查
	@RequiresPermissions(value = { PERMISION_PREFIX + ":choose" })
	@RequestMapping(value = "/exportAllExcel", method = RequestMethod.POST)
	public ModelAndView exportAllExcel(String array[]) {

		if(array.length==0){
			throw new ServiceException("请勾选导出字段，至少一个！");
		}
		List<CasePaidDto> list = null;
		ParamCondition condition = parseCondition("*");
		
		//导出也添加过滤条件
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		
		list = casePaidService.queryPaidall(condition);
		return exportCaseExcel(list, array);
	}

	private ModelAndView exportCaseExcel(List<CasePaidDto> list, String[] array) {
		// 拼接勾选字符串 目的:吧 拼接的字符串放入createExcelView();方法 里面。
		StringBuffer field = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			field.append("," + array[i]);
		}
		// 处理field
		List<String> cids = Arrays.asList(StringUtils.split(field.toString(), ","));
		// titleList 里面存放勾选字段，。
		List<String> titleList = null;
		if (CollectionUtils.isNotEmpty(cids)) {
			// 把所有勾选的字段 放入titleList
			titleList = new ArrayList<>(cids.size());
			for (int i = 0; i < cids.size(); i++) {
				titleList.add(cids.get(i));
			}
		}
		return super.createExcelView("casepaidshow", list, "还款列表信息", null, titleList);
	}

	/**
	 * 还款展示方法
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@RequestMapping(value = "queryPaid", method = RequestMethod.POST)
	@ResponseBody
	public SearchResult<CasePaidDto> queryPaid() {
		ParamCondition condition = parseCondition("*");
	
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		return casePaidService.queryPaid(condition);
	}

	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		if (id != null && StringUtils.isNotBlank(id.toString())) {

			String caseId = id;
			view.addObject("caseId", caseId);// set到页面

			CasePaid paid = casePaidService.findById(id);
			String caseId2 = paid.getCaseId();

			CaseModel model = caseService.findById(caseId2);
			BigDecimal caseMoney = model.getCaseMoney();

			/*
			 * Double surplusMoney = paid.getSurplusMoney();
			 * 
			 * if(surplusMoney.compareTo(0.0)<0){ surplusMoney=0.0;
			 * view.addObject("surplusMoney", surplusMoney);//set到页面 } else {
			 * view.addObject("surplusMoney", surplusMoney);//set到页面
			 * 
			 * }
			 */
			// Double surplus = caseMoney-cpMoney;

			view.addObject("caseMoney", caseMoney);// set到页面

		}
		return view;
	}

	/**
	 * 更新状态
	 * 
	 * @param state
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":affirm" })
	@RequestMapping(value = "updateForState")
	@ResponseBody
	// 修改状态 登账状态 0ptp正常 1 cp正常 2确认 3作废ptp 4作废cp(撤销) 5提交委托方
	public AjaxResult updateForState(@RequestParam("paidNum") Double paidNum,
			@RequestParam("outDerate") Double outDerate, 
			@RequestParam("inDerate") Double inDerate, 
			 String paidTime6, 
			CasePaid model) {
		model.setState(2);
		model.setModifyTime(new Date());
		
	    Date paidTime1 = DateUtil.str2Date(paidTime6);
		model.setPaidTime(paidTime1);
		//操作人
		String userId = SecurityUtil.getCurrentUser().getId();
		model.setOperateEmpId(userId);
		
		model.setSurTime(new Date());

		String caseId = model.getCaseId();
		CaseModel caseModel = caseService.findById(caseId);

		String rate = caseModel.getEntrustRate();
		if(rate != null){
			double entrustRate = Double.parseDouble(rate);
			double entrustPaid = NumberUtil.doubleMultiply(paidNum, entrustRate);
			model.setEntrustPaidRate(entrustRate);
			model.setEntrustPaid(entrustPaid);
		}
		Double agentRate = caseModel.getAgentRate();
		if(agentRate!=null){
			double backPaid = NumberUtil.doubleMultiply(paidNum, agentRate);
			model.setBackPaidRate(agentRate);
			model.setBackPaid(backPaid);
		}
		
		caseModel.setOutDerate(outDerate);
		caseModel.setInDerate(inDerate);
		caseService.update(caseModel);

		model.setPaidNum(paidNum);

		if (outDerate > 0 && inDerate == 0) {
			model.setIsDerate("2");
		} else if (outDerate == 0 && inDerate > 0) {
			model.setIsDerate("1");
		} else if (outDerate > 0 && inDerate > 0) {
			model.setIsDerate("3");
		}
		double doubleAdd = NumberUtil.doubleAdd(outDerate, paidNum, inDerate);

		casePaidService.updateForState(model);
		
		
		messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+"已确认还款", 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
		
		
		AjaxResult result = AjaxResult.success(getSuccessMessage());

		result.setObj(doubleAdd);

		return result;
	}

	@RequiresPermissions(value = { PERMISION_PREFIX + ":affirm" })
	@RequestMapping(value = "updateForStatePaid")
	@ResponseBody
	// 修改状态 登账状态 0ptp正常 1 cp正常 2确认 3作废ptp 4作废cp(撤销) 5提交委托方
	public AjaxResult updateForStatePaid(CasePaid model) {
		model.setState(2);
		model.setModifyTime(new Date());
		Double cpMoney = model.getCpMoney();
		model.setPaidNum(cpMoney);
		casePaidService.updateForState(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 更新状态
	 * 
	 * @param state
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":cancel" })
	@RequestMapping(value = "updateForCancelState")
	@ResponseBody
	// 修改状态 登账状态 0ptp正常 1 cp正常 2确认 3作废ptp 4作废cp(撤销) 5提交委托方
	public AjaxResult updateForCancelState(@RequestParam("cancelReason") String cancelReason,
			@RequestParam("id") String id) {
		CasePaid model = casePaidService.findById(id);
//		model.setOutDerate(outDerate);
//		model.setInDerate(inDerate);
		model.setCancelReason(cancelReason);

		String isDerate = model.getIsDerate();
		if (isDerate != null) {

			if (!isDerate.equals("0")) {

				String caseId = model.getCaseId();

				CaseModel caseModel = caseService.findById(caseId);

				Integer state = caseModel.getState();
				if (state == 4) {
					caseModel.setState(0);

				}
				caseModel.setOutDerate(0.00);
				caseModel.setInDerate(0.00);
				caseService.update(caseModel);
			}
		}

		model.setState(4);
		model.setModifyTime(new Date());
		// model.setPaidNum(paidNum);
		casePaidService.updateForState(model);
		
		
		CaseModel caseModel = caseService.findById(model.getCaseId());
		messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+"已作废还款", 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	/**
	 * 跳转到作废页面 zhangyj
	 * 
	 * @date 2016年9月6日
	 * @return
	 */
	@RequestMapping(value = "cancel"+ "/{id}", method = RequestMethod.GET)
	public ModelAndView cancel(@PathVariable("id") String id) {
		ModelAndView view = createBaseView("cancel");
		view.addObject("id", id);
		return view;
	}
	/**
	 * 跳转页面 zhangyj
	 * 
	 * @date 2016年9月6日
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView view = createBaseView("add");
		return view;
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":addInsert" })
	@RequestMapping(value = "addInsert")
	@ResponseBody
	public AjaxResult addInsert(String paidTime,
			Double paidNum,
			String surRemark,
			String caseCode,
			String caseFileNo) {
		CasePaid model=new CasePaid();
		model.setId(UUIDUtil.UUID32());
		model.setSurRemark(surRemark);
		
		String caseId=null;
		CaseModel caseModel=null;
		if(caseCode!=null){
			 caseId = caseMapper.getcaseIdByCaseCode(caseCode);
			if(caseId!=null){
				 caseModel = caseService.findById(caseId);
				
				String rate = caseModel.getEntrustRate();
				if(rate != null){
					double entrustRate = Double.parseDouble(rate);
					double entrustPaid = NumberUtil.doubleMultiply(paidNum, entrustRate);
					model.setEntrustPaidRate(entrustRate);
					model.setEntrustPaid(entrustPaid);
				}
				Double agentRate = caseModel.getAgentRate();
				if(agentRate!=null){
					double backPaid = NumberUtil.doubleMultiply(paidNum, agentRate);
					model.setBackPaidRate(agentRate);
					model.setBackPaid(backPaid);
				}
				model.setCaseId(caseId);
			}else{
				return AjaxResult.failure("请添加正确的案件序列号！");
			}
		}else if(caseFileNo!=null){
			 caseId =  caseMapper.getcaseIdByCaseFileNo(caseFileNo);
			if(caseId!=null){
				 caseModel = caseService.findById(caseId);
				Double agentRate = caseModel.getAgentRate();
				if(agentRate!=null){
					double backPaid = NumberUtil.doubleMultiply(paidNum, agentRate);
					model.setBackPaidRate(agentRate);
					model.setBackPaid(backPaid);
				}
				model.setCaseId(caseId);
			}else{
				return AjaxResult.failure("请添加正确的档案号！");
			}
		}else if (caseCode ==null || caseFileNo == null){
			return AjaxResult.failure("请添加正确的案件序列号或档案号！");
		}
	
		Date date = DateUtil.str2Date(paidTime);
		model.setPaidTime(date);
		model.setPaidNum(paidNum);
		model.setState(2);
		model.setRepayType("2");//标示为自来账
		casePaidService.savezlz(model);
		String content="案件序列号："+caseCode+",添加还款金额"+paidNum+"元。";
		HurryRecordModel hr =OperationUtil.addRecord("CP", caseId, "自来账还款", content, model.getId(), 1);
		hurryRecordMapper.save(hr); 
		
		messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+"已确认自来账", 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
		
		
		String id = model.getId();
		CasePaidDto casePaidDto = casePaidMapper.queryById(id);
		
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		
		result.setObj(casePaidDto);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "sum", method = RequestMethod.POST)
	public String sum(BigDecimal paidNum, BigDecimal outDerate) {
		JSONObject json = new JSONObject();
		BigDecimal sum = paidNum.add(outDerate);

		// BigDecimal sum = paidNum.add(inDerate).add(outDerate);
		json.put("sum", sum);
		return json.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "clear", method = RequestMethod.POST)
	public String clear(String caseId) {
		JSONObject json = new JSONObject();

		CaseModel caseModel = caseService.findById(caseId);
		Integer state = caseModel.getState();
		// BigDecimal sum = paidNum.add(inDerate).add(outDerate);
		json.put("state", state);
		return json.toJSONString();
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
	
	@RequestMapping(value = "statistics", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult statistics() {
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		ParamCondition condition = parseCondition("*");
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		Map<String, Object> map = casePaidService.queryStatistics(condition);
		result.setObj(map);
		return result;
	}
	
	@Override
	protected String getBaseName() {
		return "collection/casepaidshow/";
	}

}
