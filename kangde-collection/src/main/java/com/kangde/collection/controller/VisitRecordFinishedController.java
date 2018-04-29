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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;
import com.kangde.sys.service.TemplateService;

@Controller
@RequestMapping("collection/visitrecordfinished")
public class VisitRecordFinishedController extends RestfulUrlController<VisitRecordModel, String> {
	
	// 权限码前缀
	private static final String PERMISION_PREFIX = "visitrecordfinished";
	
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private RoleService roleService;
	
	public VisitRecordFinishedController() {
		columnNames.put("visitState", "vr.visit_state");
		columnNames.put("caseModel.collecStateId", "c.collec_state_id");
		columnNames.put("caseModel.caseCode", "c.case_code");
		columnNames.put("caseModel.caseMoney", "c.case_money");
		columnNames.put("paidNum", "paidNum");
		columnNames.put("visitAddress", "vr.visit_address");
		columnNames.put("actualVisitTime", "vr.actual_visit_time");
		columnNames.put("isEffective", "vr.is_effective");
	}
	
	/**
	 * 已完成
	 */
	@RequestMapping(value = "queryFinish")
	@ResponseBody
	public SearchResult<VisitRecordDto> queryFinish() {
		ParamCondition condition = parseCondition("*");
		condition.put("state", 4);
		//获取当前登陆人的所有角色，查看该登录人是否有权限查看退案
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		
		return visitRecordService.queryAppointedORFinished(condition);
	}
	
	/**
	 * 访问input页面,后台查询sys_column表里字段
	 * wcy
	 * 2016年8月15日16:08:59
	 */
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		List<ColumnModel> column = templateService.findVisitRecord();
		view.addObject("column", column);
		return view;
	}
	
	/**
	 * 导出所选外访方法
	 * wcy
	 * 参数： array~input页面选中的字段、ids~选中数据的id
	 * 2016年8月15日16:09:29
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportSelected" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] array, String[] ids) {
		//没勾选字段  提示
		if(array.length==0){
			throw new ServiceException("请勾选导出字段，至少一个！");
		}
		List<VisitRecordDto> list = null;
		// 判断批次id 是否有值， 没有值的话 提示没有可以导出的数据
				if (ArrayUtils.isNotEmpty(ids)) {
					// 把数组放入condition serviceImpl 处理 在.xml里面解析
					ParamCondition condition = new ParamCondition();
					condition.put("ids", Arrays.asList(ids));
					list = visitRecordService.selectByIds(Arrays.asList(ids));
				}
				return exportCaseExcel(list, array);
	}
	
	//Excel导出选查案件
	@RequiresPermissions(value = { PERMISION_PREFIX + ":exportSelected1" })
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(String[] array){
		ParamCondition condition = parseCondition("*");
		condition.put("state", 4);
		//导出也添加过滤条件
				EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
				roleService.getOrgCode(empModel.getId(),condition);
		List<VisitRecordDto> list=visitRecordService.seletAll(condition);
		
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
				if("caseCode".equals(cids.get(i))){
					titleList.add("caseModel.caseCode");
				}
				if("collecStateId".equals(cids.get(i))){
					titleList.add("caseModel.collecStateId");
				}
				if("relation".equals(cids.get(i))){
					titleList.add("addressModel.relation");
				}
				if("caseMoney".equals(cids.get(i))){
					titleList.add("caseModel.caseMoney");
				}
				if("ename".equals(cids.get(i))){
					titleList.add("entrustModel.ename");
				}
				if("userName".equals(cids.get(i))){
					titleList.add("employeeInfo.userName");
				}
			}
		}
		return super.createExcelView("visitRecordView", list, "导出所查外访信息", null, titleList);
	}
	
	private ModelAndView exportCaseExcel(List<VisitRecordDto> list, String[] array) {
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
				if("caseCode".equals(cids.get(i))){
					titleList.add("caseModel.caseCode");
				}
				if("collecStateId".equals(cids.get(i))){
					titleList.add("caseModel.collecStateId");
				}
				if("relation".equals(cids.get(i))){
					titleList.add("addressModel.relation");
				}
				if("caseMoney".equals(cids.get(i))){
					titleList.add("caseModel.caseMoney");
				}
				if("ename".equals(cids.get(i))){
					titleList.add("entrustModel.ename");
				}
				if("userName".equals(cids.get(i))){
					titleList.add("employeeInfo.userName");
				}
			}
		/*	for (int i = 0; i < list.size(); i++) {
				if(null==list.get(i).getPaidNum()){
					list.get(i).setPaidNum(new BigDecimal(0));
				}
			}*/
		}
		return super.createExcelView("visitRecordView", list, "所选外访信息", null, titleList);
	}
	


	@Override
	protected String getBaseName() {
		return "collection/visitrecordfinished/";
	}

}
