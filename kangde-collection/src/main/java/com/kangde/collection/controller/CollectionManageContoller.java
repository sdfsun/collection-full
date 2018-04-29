package com.kangde.collection.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.ControllerMessageDto;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.CollectionManageService;
import com.kangde.collection.service.ColumnService;
import com.kangde.commons.exception.BaseException;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.TemplateModel;
import com.kangde.sys.service.TemplateService;

/**
 * 催记管理 Controller
 * 
 * @author wcy
 * @date 2016年6月13日13:31:29
 */
@Controller
@RequestMapping("collection/collectionmanage")
public class CollectionManageContoller extends RestfulUrlController<ControllerMessageDto, String> {
	
	// 权限码前缀
	private static final String PERMISION_PREFIX = "collectionmanage";
		
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private ColumnService columnService;
	
	@Autowired
	private CollectionManageService collectionManageService;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired 
	private CaseBatchService caseBatchService;
	
	// 前端排序字段
		public  CollectionManageContoller() {
			columnNames.put("casemodel.caseCode", "ci.case_code");
			columnNames.put("collecStateId", "pr.collec_state_id");
			columnNames.put("typeId", "pr.type_id");
			columnNames.put("casemodel.caseName", "ci.case_name");
			columnNames.put("name", "pr.name");
			columnNames.put("contact", "pr.contact");
			columnNames.put("ptpMoney", "pr.ptp_money");
			columnNames.put("ptpTime", "pr.ptp_time");
			columnNames.put("employeeInfoModel.userName", "ei.user_name");
			columnNames.put("createTime", "pr.create_time");
		}
	
	
	@Override
	public ModelAndView index() {
		ModelAndView view = super.index();
		String monthFirstDay=DateUtil.thisDate().substring(0, 8)+"01";
		view.addObject("createTimeBegin", monthFirstDay);
		view.addObject("createTimeEnd", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return view;
	}
	
	/**
	 * 查询所有催记方法
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.POST)
	public SearchResult<ControllerMessageDto> queryForPage() {
		ParamCondition condition = parseCondition("*");
		checkDays(condition);
		condition.put("DSJ", "DSJ");
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
		String code= condition.get("batchCode");
		String batchCode=caseBatchService.BatchCode(code);
		condition.put("batchCode", batchCode);
		SearchResult<ControllerMessageDto> queryForPage = super.queryForPage(condition);
		return queryForPage;
	}

	private void checkDays(ParamCondition condition) {
		Object start = condition.get("createTime");
		if(start==null){
			throw new BaseException("催记开始日期不能为空");
		}
		Object end = condition.get("createTime1");
		if(end==null){
			throw new BaseException("催记截止日期不能为空");
		}
		
		try {
			DateUtil.getLastDay((String)start,4);

		
			if(((String)end).compareTo(DateUtil.getLastDay((String)start,3))>0){
				throw new BaseException("催记日期跨度不能超过4个月");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BaseException("日期格式错误");
		}
	}
	
	
	//Excel导出选中案件   caseIds?查选中的？        模板的id  
		@RequiresPermissions(value = { PERMISION_PREFIX + ":exportCaseId" })
		@RequestMapping(value="/exportSelectedExcel")
		public ModelAndView exportExcel(String [] caseIds,@RequestParam("templateId") String templateId){
			List<ControllerMessageDto> list = null;
			if(ArrayUtils.isNotEmpty(caseIds)){
				ParamCondition condition = new ParamCondition();
				List<String> caseIdMore=Arrays.asList(caseIds);
				condition.put("caseIdMore",caseIdMore );
				list = collectionManageService.query(condition);
			}
			return exportCaseExcel(templateId, list);
		}
		

		//Excel导出选查案件
		@RequiresPermissions(value = { PERMISION_PREFIX + ":exportCaseIdAll" })
		@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
		public ModelAndView exportExcel(){
			ParamCondition condition = parseCondition("*");
			checkDays(condition);
			String templateId = condition.remove("templateId");
			checkDays(condition);
			condition.put("DSJ", "DSJ");
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
			String code= condition.get("batchCode");
			String batchCode=caseBatchService.BatchCode(code);
			condition.put("batchCode", batchCode);
		
			List<ControllerMessageDto> list = super.query(condition);
			return exportCaseExcel(templateId, list);
		}
		
		
		private ModelAndView exportCaseExcel(String templateId,List<ControllerMessageDto> list){
			TemplateModel template = templateService.findById(templateId);
			if(template==null){
				throw new ServiceException("没有找到模板信息");
			}
			List<String> cids = Arrays.asList(StringUtils.split(template.getSysColumnIds(),","));
			List<ColumnModel> clist = columnService.findByIds(cids);
			List<String> titleList = null;
			if(CollectionUtils.isNotEmpty(clist)){
				titleList = new ArrayList<>(clist.size());
				for(ColumnModel c:clist){
					titleList.add(c.getField());
				}
			}
			/*titleList.add("casemodel.caseCode");
			titleList.add("casemodel.caseName");
			titleList.add("employeeInfoModel.userName");
			
			titleList.add("casemodel.remark1");
			titleList.add("casemodel.remark2");
			titleList.add("casemodel.accountNo");
			titleList.add("casemodel.cusNo");
			titleList.add("casemodel.caseFileNo");
			titleList.add("casemodel.caseCard");
			titleList.add("casemodel.caseNum");
			titleList.add("casemodel.state");
			titleList.add("casemodel.caseNum");
			titleList.add("casemodel.loanContract");
			titleList.add("casemodel.caseFileNo");
			titleList.add("casemodel.overdueDays");
			titleList.add("casemodel.markId");
			titleList.add("casemodel.caseDate");
			titleList.add("casemodel.caseBackdate");
			titleList.add("casemodel.overdueMoney");
			titleList.add("casemodel.overdueAge");
			titleList.add("casemodel.caseMoney");
			titleList.add("casemodel.caseAppNo");
			titleList.add("casemodel.commodity");
			titleList.add("casemodel.surplusPrincipal");
			titleList.add("batchmodel.batchCode");
		*/
			
			return super.createExcelView("controllerMessageDto", list, "催记信息列表", null,titleList); 
		}
		
	@Override
	protected String getBaseName() {
		return "collection/collectionmanage/";
	}

}
