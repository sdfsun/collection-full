package com.kangde.collection.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 批次 Controller
 * @author zhangyj
 * @date 2016年5月23日
 */
@Controller
@RequestMapping("collection/casebatch")
public class CaseBatchController extends RestfulUrlController<CaseBatchModel,String> {
	private static final String PERMISION_PREFIX = "caseImport";
	@Autowired
	private CaseBatchService caseBatchService;
	
	// 前端排序字段 
	public CaseBatchController() {
		columnNames.put("totalNumber", "case_batch.total_number");
		columnNames.put("totalMoney", "case_batch.total_money");
		columnNames.put("beginDate", "case_batch.begin_date");
		columnNames.put("endDate", "case_batch.end_date");
		columnNames.put("modifyTime", "case_batch.modify_time");
		
		//2016年11月24日17:22:29   添加排序
		columnNames.put("batchCode", "case_batch.batch_code");
		columnNames.put("entrustName", "entrust.name");
		columnNames.put("typeId", "case_batch.type_id");
	}
	@RequiresPermissions(value={PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(CaseBatchModel model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	
	@Override
	public AjaxResult deleteById(@RequestParam("batchId") String batchId) {
		caseBatchService.deleteById(batchId);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	@Override  
	protected AjaxResult delete(CaseBatchModel model) {
		caseBatchService.delete(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	
	//模糊查询，  name是页面上 搜索框的name；
	@Override
	public SearchResult<CaseBatchModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		String Code=condition.get("batchCode");
		String batchCode=caseBatchService.BatchCode(Code);
		condition.put("batchCode", batchCode);
		return super.queryForPage(condition);
	}
	/*
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("id") String id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		if(id !=null && StringUtils.isNotBlank(id.toString())){
			 String i=id.substring(id.length()-1,id.length());//截取字符串， 截取最后一位。
			 String caseId=id.substring(0,id.length()-1);
			view.addObject("caseId", caseId);//set到页面
			view.addObject("i", i);//set到页面
		}
		return view;
	}*/
	
	/**
	 * 撤案和恢复案件
	 * @param state
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForStatus(CaseBatchModel model){
		model.setState(1);
		model.setModifyTime(new Date());
	    caseBatchService.updateForStatus(model); 
	    
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	@RequestMapping(value = "updateForState")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForState(CaseBatchModel model){
		model.setState(0);
		model.setModifyTime(new Date());
		caseBatchService.updateForStatus(model); 
		
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	@RequiresPermissions(value={PERMISION_PREFIX+":delete"})
	@RequestMapping(value = "softDelete")
	@ResponseBody
	//修改状态  
	public AjaxResult softDelete(@RequestParam("status") Integer status,CaseBatchModel model){
		//model.setState(status);
		//model.setModifyTime(new Date());
	    caseBatchService.deleteByBatchId(model.getId()); 
	    
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	
	@Override
	protected String getBaseName() {
		return "collection/casebatch/";
	}
	
	
	
//	/**
//	 * 跳转到导出所有excl    选择字段页面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value="excelall",method=RequestMethod.GET)
//	public ModelAndView excelall() {
//		return createBaseView("excelall");
//	}
//	
	
}
