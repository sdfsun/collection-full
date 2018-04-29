package com.kangde.collection.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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

import com.kangde.collection.dto.CaseApplyDto;
import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.AttachmentModel;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.model.ExpressModel;
import com.kangde.collection.model.SocialSecurityModel;
import com.kangde.collection.model.TelecomModel;
import com.kangde.collection.service.AttachmentService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.CensusRegisterService;
import com.kangde.collection.service.ExpressService;
import com.kangde.collection.service.HelpMeService;
import com.kangde.collection.service.SocialSecurityService;
import com.kangde.collection.service.TelecomService;
import com.kangde.collection.util.PluploadUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.excel.ExcelContext;
import com.kangde.commons.util.excel.vo.ExcelImportResult;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.RoleService;


/**
 * 查资审批 Controller
 * 
 * @author wcy
 * @date 2016年7月19日17:42:20
 */
@Controller
@RequestMapping("collection/helpmedeal")
public class HelpMeDealController extends RestfulUrlController<HelpMeDto, String> {
	private static Logger logger = Logger.getLogger(UploadController.class);

	// 权限码前缀
	private static final String PERMISION_PREFIX = "helpmedeal";
		
	@Autowired
	private HelpMeService helpMeService;
	@Autowired
	private ExcelContext excelContext;
	@Autowired
	private AttachmentService attachmentService;
	/** 户籍  */
	@Autowired
	private CensusRegisterService censusRegisterService;
	/** 社保  */
	@Autowired
	private SocialSecurityService socialSecurityService;
	/** 电信  */
	@Autowired
	private TelecomService telecomService;
	/** 快递  */
	@Autowired
	private ExpressService expressService;
	
	@Autowired
	private CaseService caseService;
	@Autowired
	private RoleService roleService;
	
	
	public HelpMeDealController() {
		//2016年11月24日17:29:15 添加排序
		columnNames.put("caseModel.caseCode", "case_info.`case_code`");
		columnNames.put("caseModel.caseNum", "case_info.case_Num");
		columnNames.put("caseModel.caseMoney", "case_info.case_money");
		columnNames.put("surplusMoney", "surplusMoney");
		columnNames.put("applyType", "case_apply.apply_type");
		columnNames.put("entrustModel.name", "sys_organization.name");
		columnNames.put("employeeInfoModel.userName", "ci.user_name");
		columnNames.put("appTime", "case_apply.app_time");
	
		
	}
	
	/** 查资审批同意方法 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":bohui" })
	@RequestMapping(value = "bohui")
	@ResponseBody
	public AjaxResult agree(CaseApplyModel model){
		//申请状态 -2待审批 -1审批失败 0审批通过 1已完成
		model.setState(3);
		model.setSurTime(new Date());
		helpMeService.agree(model,1);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		 return result;
	}
	
	
	/** 待处理查资分页查询  */
	@RequestMapping(value = "querydeal")
	@ResponseBody
	public SearchResult<HelpMeDto> querydeal() {
		ParamCondition condition = parseCondition("*");
		
		/*String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}*/
		//查看当前登录人的角色， 是否有权限查看退案的案件
		EmployeeInfoModel empModel=SecurityUtil.getCurrentUser();
		roleService.getOrgCode(empModel.getId(),condition);
		return helpMeService.querydeal(condition);
	}
	
	/** 导出户籍  */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":Export" })
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String[] ids){
		List<HelpMeDto> list=helpMeService.queryExport(Arrays.asList(ids));
		return super.createExcelView("helpmedto", list, "待处理户籍查资", null, null);
	}
	
	/** 导出社保*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":Export" })
	@RequestMapping(value = "/exportSelectedExcelSeBao")
	public ModelAndView exportSelectedExcelSeBao(String[] ids){
		List<HelpMeDto> list=helpMeService.queryExport(Arrays.asList(ids));
		return super.createExcelView("helpmedtoSheBao", list, "待处理社保查资", null, null);
	}
	
	/** 导出电信  */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":Export" })
	@RequestMapping(value = "/exportSelectedExcelDianXIn")
	public ModelAndView exportSelectedExcelDianXIn(String[] ids){
		List<HelpMeDto> list=helpMeService.queryExport(Arrays.asList(ids));
		return super.createExcelView("helpmedtoDianXin", list, "待处理电信查资", null, null);
	}
	
	/** 导出社保*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":Export" })
	@RequestMapping(value = "/exportSelectedExcelKuaiDi")
	public ModelAndView exportSelectedExcelKuaiDi(String[] ids){
		List<HelpMeDto> list=helpMeService.queryExport(Arrays.asList(ids));
		return super.createExcelView("helpmedtoKuaiDi", list, "待处理快递查资", null, null);
	}
	
	/**
	 * 导入查资记录
	 * 
	 * @param type
	 *            模板类型
	 * @param 
	 *            ID
	 * @param file
	 *            Excel文件信息
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":upload" })
	@RequestMapping(value = "/applyEexcelImport", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult importExcel(HttpServletRequest request,@RequestParam("excelFile") MultipartFile file,
			@RequestParam(name="orgId",required=false) String orgId,
			@RequestParam(name="appTime",required=false) String appTime,
			@RequestParam(name="caseCode",required=false) String caseCode,
			@RequestParam(name="applyType",required=false) String applyType,
			@RequestParam(name="applyUser",required=false) String applyUser,
			@RequestParam(name="caseName",required=false) String caseName,
			@RequestParam(name="caseNum",required=false) String caseNum) throws IOException, Exception {
		AjaxResult result = AjaxResult.success("导入数据成功");
		/*
		 * 接不到值
		 */
//		String caseName1="caseName="+caseName;
//		String[] caseName1s = caseName1.split("=");
//		caseName1=caseName1s[0]+"="+caseName1s[1];
		ParamCondition condition = new ParamCondition();
		condition.put("orgId", orgId);
		condition.put("caseName", caseName);
		ExcelImportResult readExcel = excelContext.readExcel("caseApplyDto", file.getInputStream());
		List<CaseApplyDto> listBean = readExcel.getListBean();
		if(CollectionUtils.isNotEmpty(listBean)){
			Set<Integer> ss = new HashSet<>();
			for(CaseApplyDto d:listBean){
				String id = d.getId();
				Integer type = d.getApplyType();
				ss.add(type);
			}
			if(ss.size() > 1){
				throw new ServiceException("导入的数据类型不一致,无法完成。");
			}
			Integer tempType = listBean.get(0).getApplyType();
			if (tempType.equals(4)) {
				// 户籍
				List<CensusRegisterModel> list = new ArrayList<>(listBean.size());
				for(CaseApplyDto d:listBean){
					CensusRegisterModel m = new CensusRegisterModel();
					ReflectUtil.copyProps(d, m,"id");
					m.setCaseApplyId(d.getId());
					updateForStatus(m);
					list.add(m);
				}
			} else if (tempType.equals(6)) {
				// 社保
				List<SocialSecurityModel> list = new ArrayList<>(listBean.size());
				for(CaseApplyDto d:listBean){
					SocialSecurityModel model = new SocialSecurityModel();
					ReflectUtil.copyProps(d, model,"id");
					model.setCaseApplyId(d.getId());
					saveshebao(model);
					list.add(model);
				}
			} else if (tempType.equals(12)) {
				// 电信
				List<TelecomModel> list = new ArrayList<>(listBean.size());
				for(CaseApplyDto d:listBean){
					TelecomModel model = new TelecomModel();
					ReflectUtil.copyProps(d, model,"id");
					model.setCaseApplyId(d.getId());
					savedianxin(model);
					list.add(model);
				}
			}else if (tempType.equals(15)) {
				// 快递
				List<ExpressModel> list = new ArrayList<>(listBean.size());
				for(CaseApplyDto d:listBean){
					ExpressModel model = new ExpressModel();
					ReflectUtil.copyProps(d, model,"id");
					model.setCaseApplyId(d.getId());
					savekuaidi(model);
					list.add(model);
				}
			}else{
				throw new ServiceException("请检测excel的风控状态");
			}
		}
		return result;
	}

	private void saveFile(HttpServletRequest request, String batchId) {
		request.setAttribute("businessId", batchId);
		request.setAttribute("businessType", "batchImport");
		AttachmentModel attachment;
		try {
			attachment = PluploadUtil.upload(request);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @return 上传Excel页面视图
	 */

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView pageUpload() {
		ModelAndView view = super.createBaseView("upload");
		
		return view;
	}
/*	**
	 * @return 上传Excel页面视图
	 *//*
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView pageUpload(@RequestParam("caseApplyId") String caseApplyId) {
		ModelAndView view = super.createBaseView("upload");
		view.addObject("caseApplyId", caseApplyId);
		return view;
	}
*/	
	
	/** 录入户籍  */
	@RequestMapping(value = "saveAll")
	@ResponseBody
	public AjaxResult updateForStatus(CensusRegisterModel model){
		CensusRegisterModel size=censusRegisterService.save(model);
		//如果录入成功修改查资状态。协催数据将变成已完成
		if(size!=null){
			CaseApplyModel camodele =new CaseApplyModel();
			camodele.setId(model.getCaseApplyId());
			camodele.setState(1);
			//协催审批的时候没有协催时间
			camodele.setSurTime(new Date());
			int s=helpMeService.agree(camodele,2);
			if(s>0){
				//去给历史表添加操作记录
				helpMeService.hurryRecordHj(model);
			}
		}
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	/** 录入社保  */
	@RequestMapping(value = "saveshebao")
	@ResponseBody
	public AjaxResult saveshebao(SocialSecurityModel model){
		SocialSecurityModel size=socialSecurityService.save(model);
		//如果录入成功修改查资状态。协催数据将变成已完成
		if(size!=null){
			CaseApplyModel camodele =new CaseApplyModel();
			camodele.setId(model.getCaseApplyId());
			camodele.setState(1);
			camodele.setSurTime(new Date());
			int s=helpMeService.agree(camodele,2);
			if(s>0){
				//去给历史表添加操作记录
				helpMeService.hurryRecordSB(model);
			}
		}
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	
	/** 录入社保  */
	@RequestMapping(value = "savekuaidi")
	@ResponseBody
	public AjaxResult savekuaidi(ExpressModel model){
		ExpressModel size=expressService.save(model);
		//如果录入成功修改查资状态。协催数据将变成已完成
		if(size!=null){
			CaseApplyModel camodele =new CaseApplyModel();
			camodele.setId(model.getCaseApplyId());
			camodele.setState(1);
			camodele.setSurTime(new Date());
			int s=helpMeService.agree(camodele,2);
			if(s>0){
				//去给历史表添加操作记录
				helpMeService.hurryRecordKD(model);
			}
		}
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	/** 录入电信  */
	@RequestMapping(value = "savedianxin")
	@ResponseBody
	public AjaxResult savedianxin(TelecomModel model){
		TelecomModel size=telecomService.save(model);
		//如果录入成功修改查资状态。协催数据将变成已完成
		if(size!=null){
			CaseApplyModel camodele =new CaseApplyModel();
			camodele.setId(model.getCaseApplyId());
			camodele.setState(1);
			camodele.setSurTime(new Date());
			int s=helpMeService.agree(camodele,2);
			if(s>0){
				//去给历史表添加操作记录
				helpMeService.hurryRecordDX(model);
			}
		}
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	/** 访问户籍录入页面  */
	@RequestMapping(value = "huji" + "/{caseApplyId}", method = RequestMethod.GET)
	public ModelAndView huji(@PathVariable("caseApplyId") String caseApplyId) {
		CaseApplyModel model=helpMeService.findById(caseApplyId);
		String caseId=model.getCaseId();
		String caseCode="";//案件序列号
		String caseName="";//案件人姓名
		String caseNum="";//证件号
		if(StringUtils.isNotBlank(caseId)){
			CaseModel casemodel=caseService.findById(caseId);
			 	caseCode=casemodel.getCaseCode();
			 	caseName=casemodel.getCaseName();
			 	caseNum=casemodel.getCaseNum();
		}
		ModelAndView view = createBaseView("huji");
			view.addObject("caseCode",caseCode );//set到页面
			view.addObject("caseName", caseName);//set到页面
			view.addObject("caseNum", caseNum);
		return view;
	}
	/** 访问社保录入页面  */
	@RequestMapping(value = "shebao" + "/{caseApplyId}", method = RequestMethod.GET)
	public ModelAndView shebao(@PathVariable("caseApplyId") String caseApplyId) {
		CaseApplyModel model=helpMeService.findById(caseApplyId);
		String caseId=model.getCaseId();
		String caseCode="";//案件序列号
		String caseName="";//案件人姓名
		String caseNum="";//证件号
		if(StringUtils.isNotBlank(caseId)){
			CaseModel casemodel=caseService.findById(caseId);
			 	caseCode=casemodel.getCaseCode();
			 	caseName=casemodel.getCaseName();
			 	caseNum=casemodel.getCaseNum();
		}
		ModelAndView view = createBaseView("shebao");
		view.addObject("caseCode",caseCode );//set到页面
		view.addObject("caseName", caseName);//set到页面
		view.addObject("caseNum", caseNum);
		return view;
	}
	
	/** 访问电信录入页面  */
	@RequestMapping(value = "dianxin" + "/{caseApplyId}", method = RequestMethod.GET)
	public ModelAndView dianxin(@PathVariable("caseApplyId") String caseApplyId) {
		CaseApplyModel model=helpMeService.findById(caseApplyId);
		String caseId=model.getCaseId();
		String caseCode="";//案件序列号
		String caseName="";//案件人姓名
		String caseNum="";//证件号
		if(StringUtils.isNotBlank(caseId)){
			CaseModel casemodel=caseService.findById(caseId);
			 	caseCode=casemodel.getCaseCode();
			 	caseName=casemodel.getCaseName();
			 	caseNum=casemodel.getCaseNum();
		}
		ModelAndView view = createBaseView("dianxin");
		view.addObject("caseCode",caseCode );//set到页面
		view.addObject("caseName", caseName);//set到页面
		view.addObject("caseNum", caseNum);
		return view;
	}
	
	/** 访问电信录入页面  */
	@RequestMapping(value = "kuaidi" + "/{caseApplyId}", method = RequestMethod.GET)
	public ModelAndView kuaidi(@PathVariable("caseApplyId") String caseApplyId) {
		CaseApplyModel model=helpMeService.findById(caseApplyId);
		String caseId=model.getCaseId();
		String caseCode="";//案件序列号
		String caseName="";//案件人姓名
		String caseNum="";//证件号
		if(StringUtils.isNotBlank(caseId)){
			CaseModel casemodel=caseService.findById(caseId);
			 	caseCode=casemodel.getCaseCode();
			 	caseName=casemodel.getCaseName();
			 	caseNum=casemodel.getCaseNum();
		}
		ModelAndView view = createBaseView("kuaidi");
		view.addObject("caseCode",caseCode );//set到页面
		view.addObject("caseName", caseName);//set到页面
		view.addObject("caseNum", caseNum);
		return view;
	}
	@Override
	protected String getBaseName() {
		return "collection/helpmedeal/";
	}

}
