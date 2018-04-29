package com.kangde.collection.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.CollectionConst;
import com.kangde.collection.dto.CaseCarDto;
import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseCarLoanModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.util.PluploadUtil;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.exception.BaseException;
import com.kangde.commons.util.excel.ExcelContext;
import com.kangde.commons.util.excel.ExcelException;
import com.kangde.commons.util.excel.vo.ExcelImportResult;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 案件导入 Controller
 * 
 * @author lisuo
 *
 */
@Controller
@RequestMapping("collection/caseImport")
public class CaseImportController extends RestfulUrlController<CaseModel, String> {
	private static Logger logger = Logger.getLogger(UploadController.class);
	//权限码前缀
	private static final String PERMISION_PREFIX = "caseImport";
	
	@Autowired
	private ExcelContext excelContext;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseBatchService caseBatchService;
	
	@RequiresPermissions(value={PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		return super.index();
	}
	

	@RequestMapping(value = PAGE_INPUT + "/{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = super.pageInput(id);
		view.addObject("ip", PluploadUtil.getDocIp());
		return view;
	}

	// 输入页面（增加，修改，展示都用这个页面）,转发到getBaseName/input
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = super.pageInput(null);
		view.addObject("ip", PluploadUtil.getDocIp());
		return view;
	}
	
	@Override
	@RequestMapping(value = PAGE_SHOW, method = RequestMethod.GET)
	public ModelAndView pageShow(@RequestParam("id") String id) {
		ModelAndView view = super.pageShow(id);
		view.addObject("id", id);
		CaseBatchModel batch = caseBatchService.findById(id);
		view.addObject("batch", batch);
		return view;
	}
	
	//单独生成批次号
	@RequestMapping(value = "createBatchCode")
	@ResponseBody
	//修改状态  
	public String createBatchCode(CaseBatchModel model){
		String batchCode = caseBatchService.createBatchCode(model);
	    JSONObject json=new JSONObject();
	    json.put("batchCode", batchCode);
	    return JSON.toJSONString(json);
	}
/*	public ModelAndView createBatchCode1(CaseBatchModel model) {
		
		String batchCode = caseBatchService.createBatchCode(model);
		
		ModelAndView view = super.pageInput(null);
		view.addObject("ip", PluploadUtil.getDocIp());
		return view;
	}*/

	
	
	/**
	 * 导入案件
	 * 
	 * @param type
	 *            模板类型
	 * @param caseBatchId
	 *            批次ID
	 * @param file
	 *            Excel文件信息
	 * @return
	 */
	
	@RequiresPermissions(value={PERMISION_PREFIX+":save"})
	@RequestMapping(value = "/excelImport", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult importExcel(HttpServletRequest request, @RequestParam("templateType") String templateType,
			@RequestParam("excelFile") MultipartFile file, CaseBatchModel caseBatch) {
		AjaxResult result = AjaxResult.success("导入数据成功");
		try {
			String tempType = null;
			if (CollectionConst.CASE_TEMP_TYPE_P2P.equals(templateType)) {
				tempType = "caseP2p";
			} else if (CollectionConst.CASE_TEMP_TYPE_BANK.equals(templateType)) {
				tempType = "caseBank";
			}else if(CollectionConst.CASE_TEMP_TYPE_CAR.equals(templateType)){
				tempType = "caseCar";
			}
			ExcelImportResult readExcel = excelContext.readExcel(tempType, file.getInputStream());
			List<CaseExcelDto> listBean = readExcel.getListBean();
			CaseBatchModel batch = caseService.importExcelData(listBean, caseBatch, templateType);
			String batchId = batch.getId();
			saveFile(request, batchId);

		} catch (Exception e) {

			result.setCode(AjaxResult.CODE_FAILURE);
			if (e instanceof ExcelException || e instanceof BaseException) {
				result.setMsg(e.getMessage());
			} else {
				if (e instanceof InvalidFormatException) {
					result.setMsg("错误的文件格式");
				}
				result.setMsg(getErrorMessage());
				e.printStackTrace();
				log.error(e);
			}
		}
		return result;
	}

	private void saveFile(HttpServletRequest request, String batchId) {
		request.setAttribute("businessId", batchId);
		request.setAttribute("businessType", "batchImport");
		try {
			PluploadUtil.upload(request);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 更新案件
	 * 
	 * @param type
	 *            模板类型
	 * @param caseBatchId
	 *            批次ID
	 * @param file
	 *            Excel文件信息
	 * @return
	 */
	@RequiresPermissions(value={PERMISION_PREFIX+":upload"})
	@RequestMapping(value = "/excelUpdate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateExcel(HttpServletRequest request,
			@RequestParam("caseBatchId") String caseBatchId, 
			@RequestParam("excelFile") MultipartFile file) {
		
		AjaxResult result = AjaxResult.success("更新数据成功");
		
		CaseBatchModel batchModel = caseBatchService.findById(caseBatchId);
		String templateType = batchModel.getTemplateType();
		try {
			String[] types = templateType.split(",");
			templateType=types[types.length-1];
			String tempType = null;
			if (CollectionConst.CASE_TEMP_TYPE_P2P.equals(templateType)) {
				tempType = "caseP2p";
			} else if (CollectionConst.CASE_TEMP_TYPE_BANK.equals(templateType)) {
				tempType = "caseBank";
			}else if(CollectionConst.CASE_TEMP_TYPE_CAR.equals(templateType)){
				tempType = "caseCar";
			}
			ExcelImportResult readExcel = excelContext.readExcel(tempType, file.getInputStream());
			List<CaseExcelDto> listBean = readExcel.getListBean();
			caseService.updateExcelData(listBean, caseBatchId, templateType);
			saveFile(request, caseBatchId);
		} catch (Exception e) {

			result.setCode(AjaxResult.CODE_FAILURE);
			if (e instanceof ExcelException || e instanceof BaseException) {
				result.setMsg(e.getMessage());
			} else {
				if (e instanceof InvalidFormatException) {
					result.setMsg("错误的文件格式");
				}
				result.setMsg(getErrorMessage());
				e.printStackTrace();
				log.error(e);
			}
		}
		return result;
	}

	/**
	 * @return 上传Excel页面视图
	 */

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView pageUpload(@RequestParam("caseBatchId") String caseBatchId) {
		ModelAndView view = super.createBaseView("upload");
		view.addObject("caseBatchId", caseBatchId);
		return view;
	}

	/**
	 * 跳转到导出所有excel p2p 模板
	 * @date 2016年10月24日11:46:52
	 */
	@RequestMapping(value = "excelP2p", method = RequestMethod.GET)
	public ModelAndView excelP2p() {
		ModelAndView view = createBaseView("excelP2p");
		return view;
	}
	
	/**
	 * 跳转到导出所有excel 银行模板
	 * @date 2016年10月24日11:46:48
	 */
	@RequestMapping(value = "excelBank", method = RequestMethod.GET)
	public ModelAndView excelBank() {
		ModelAndView view = createBaseView("excelBank");
		return view;
	}
	
	/**
	 * 跳转到导出所有excel 车贷模板
	 * @date 2016年10月24日11:46:44
	 */
	@RequestMapping(value = "excelCar", method = RequestMethod.GET)
	public ModelAndView excelCar() {
		ModelAndView view = createBaseView("excelCar");
		return view;
	}

	/**
	 * 导出功能 wcy 上个版本 是动态sql 实现的。 如果导出字段 要添加新的字段 需要注意：1.excel-config.xml
	 * caseInFoView 需要添加字段 2.caseMapper.xml 里的sql 需要添加字段
	 * 
	 * @date 2016年7月4日10:47:58
	 */
	@RequiresPermissions(value={PERMISION_PREFIX+":export"})
	@RequestMapping(value = "/exportSelectedExcel")
	// 参数 ： array选中字段， batchIds批次id
	public ModelAndView exportExcel(String[] array, String[] batchIds,String bs) {
		List<CaseModel> list = null;
		List<CaseCarDto> carlist=null;
		String tempType=null;
		//bs 1：解析caseP2p 2：caseBank  3：caseCar
		if (CollectionConst.CASE_TEMP_TYPE_P2P.equals(bs)) {
			tempType = "caseP2p1";
		} else if (CollectionConst.CASE_TEMP_TYPE_BANK.equals(bs)) {
			tempType = "caseBank1";
		}else if(CollectionConst.CASE_TEMP_TYPE_CAR.equals(bs)){
			tempType = "caseCae1";
		}
		if(tempType.equals("caseCae1")){
			carlist=caseService.findBatchCar(Arrays.asList(batchIds), array);}
			else{
		// 判断批次id 是否有值， 没有值的话 提示没有可以导出的数据
		if (ArrayUtils.isNotEmpty(batchIds)) {
			// 把数组放入condition serviceImpl 处理 在.xml里面解析
			ParamCondition condition = new ParamCondition();
			condition.put("batchIds", Arrays.asList(batchIds));
			list = caseService.findBatchCodes(Arrays.asList(batchIds), array);
		}
		//如果解析的是车贷的模板
		
			
		}
				
		return exportCaseExcel(list, array,tempType,carlist);
	}

	private ModelAndView exportCaseExcel(List<CaseModel> list, String[] array,String tempType,List<CaseCarDto> carlist) {
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
		if(tempType.equals("caseCae1")){
			if(null!=carlist){
				return super.createExcelView(tempType, carlist, "批次下所有案件", null, titleList);
			}
			
		}
		// 参数 caseInFoView ：1.解析的视图 2.list： 导出的数据 3.批次下所有案件信息： 导出文件的名称
		// 4.   5.titleList ：勾选的字段， 只会导出这些字段
		return super.createExcelView(tempType, list, "批次下所有案件", null, titleList);
	}
	
		/** 批次号下拉框 
		 *  注意：findALL方法是查询所有批次号的id  和  BatchCode  */
		@RequestMapping(value = "Codecombobox")
	    @ResponseBody
	    public List<Combobox> combobox(){
		  List<Combobox> resultList = new ArrayList<>();
		  List<CaseBatchModel> caseBatch=caseBatchService.findBatchCode();
		  if(CollectionUtils.isNotEmpty(caseBatch)){
			  for (CaseBatchModel batch:caseBatch) {
	    			Combobox combobox = new Combobox(batch.getId(),batch.getBatchCode());
	    			resultList.add(combobox);
	    		}
		  }
			return resultList;
	    }

	@Override
	protected String getBaseName() {
		return "collection/caseImport/";
	}

}
