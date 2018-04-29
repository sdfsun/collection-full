package com.kangde.collection.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.dto.CaseDetailDto;
import com.kangde.collection.dto.CaseDetailVo;
import com.kangde.collection.service.CaseDetailService;
import com.kangde.collection.service.CommentService;
import com.kangde.collection.service.impl.CaseColorService;
import com.kangde.collection.vo.CaseCommentVo;
import com.kangde.collection.vo.PoliceStation;
import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.HttpRequestUtils;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.web.controller.SimpleController;

/**
 * 案件详细信息 Controller
 */
@Controller
@RequestMapping("collection/casedetail")
public class CaseDetailContoller extends SimpleController {
	private final static String pythonIp;
	
	static {  
		pythonIp = CoreConst.getString("pythonIp");  
	} 
	
	@Autowired
	private CaseDetailService caseDetailservice;
	@Autowired
	private CaseColorService caseColorService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(String caseId) {
		ModelAndView view = createBaseView("casedetail");
		CaseDetailDto detail = caseDetailservice.queryDetailByCaseId(caseId);
		if(detail==null){
			throw new ServiceException("案件不存在");
		}
		//判断历史记录是否为空
		if(StringUtils.isNotBlank(detail.getHisRemark())){
			//获取历史记录
			String hisRemark=detail.getHisRemark();
			//判断历史记录是否存在&&（多个历史备注使用&&作为间隔）
			if (hisRemark.contains("&&")) {
				//以&&分割，将一个字符串分割成多个字符串
				String[] ary = hisRemark.split("&&");
				//定义 NB buff 
				StringBuffer testStrBuff=new StringBuffer("");
				//循环多个字符串
				for (int i = 0; i < ary.length; i++) {
					//将多个字符串拼接成理想格式  最终成为一个字符串
					testStrBuff.append(""+ary[i]+"");
					//
					testStrBuff.append('\n');
				}
				//将理想格式字符串set到案件信息里面
					detail.setHisRemark(testStrBuff.toString());
			}
		}
		
		
		CaseDetailVo caseDetailVo = convert(detail);
		view.addObject("caseId", caseId);
		view.addObject("caseDetailVo", caseDetailVo);
		CaseCommentVo attributeValue = new CaseCommentVo();
		attributeValue.setCaseCommentColor("0");
		view.addObject("caseCommentVo", attributeValue);
		view.addObject("commentColor", caseColorService.getColorMap());
		return view;
	}

	
	@RequestMapping(value = "more", method = RequestMethod.GET)
	public ModelAndView more(String caseId) {
		ModelAndView view = new ModelAndView("collection/casedetail/more");
		view.addObject("caseId", caseId);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "detailData", method = RequestMethod.GET)
	public CaseDetailVo detailData(String caseId) {
		CaseDetailDto detail = caseDetailservice.queryDetailByCaseId(caseId);
		if(detail==null){
			throw new ServiceException("案件不存在");
		}
		CaseDetailVo vo = convert(detail);
		return vo;
	}
	private CaseDetailVo convert(CaseDetailDto caseDetailDto) {
		CaseDetailVo caseDetailVo = new CaseDetailVo();
		caseDetailVo.setBatchCode(caseDetailDto.getBatchCode());
		caseDetailVo.setCaseCode(caseDetailDto.getCaseCode());
		caseDetailVo.setCaseName(caseDetailDto.getCaseName());
		caseDetailVo.setMobile1(caseDetailDto.getMobile1());
		caseDetailVo.setMobile2(caseDetailDto.getMobile2());
		caseDetailVo.setCaseNum(caseDetailDto.getCaseNum());
		caseDetailVo.setCaseNumId(caseDetailDto.getCaseNumId());
		caseDetailVo.setCaseSex(caseDetailDto.getCaseSex());
		caseDetailVo.setCaseAge(caseDetailDto.getCaseAge());
		caseDetailVo.setCaseIsMarriage(caseDetailDto.getCaseIsMarriage());
		caseDetailVo.setBankAccount(caseDetailDto.getBankAccount());
		caseDetailVo.setCaseCard(caseDetailDto.getCaseCard());
		// 没有历史记录时， 最新欠款为0; 存在历史记录时， 最新欠款是caseInfo的caseMoney字段
		if (caseDetailDto.getHistoryMoney().compareTo(BigDecimal.ZERO)==0) {
			caseDetailVo.setLatestMoney(BigDecimal.ZERO);
			caseDetailVo.setCaseMoney(caseDetailDto.getCaseMoney());
		} else {
			caseDetailVo.setLatestMoney(caseDetailDto.getCaseMoney());
			caseDetailVo.setCaseMoney(caseDetailDto.getHistoryMoney());
		}
		caseDetailVo.setCaseDate(caseDetailDto.getCaseDate());
		caseDetailVo.setCaseBackdate(caseDetailDto.getCaseBackdate());
		caseDetailVo.setEntrustName(caseDetailDto.getEntrustName());
		caseDetailVo.setEntrustName(caseDetailDto.getEntrustName());
		caseDetailVo.setEntrustRate(caseDetailDto.getEntrustRate());
		caseDetailVo.setAgentRate(caseDetailDto.getAgentRate());
		caseDetailVo.setCreditId(caseDetailDto.getCreditId());
		caseDetailVo.setLoanMoney(caseDetailDto.getLoanMoney());
		caseDetailVo.setRepayDay(caseDetailDto.getRepayDate());
		caseDetailVo.setInitialRepay(caseDetailDto.getInitialRepay());
		caseDetailVo.setSurplusPrincipal(caseDetailDto.getSurplusPrincipal());
		caseDetailVo.setPrincipal(caseDetailDto.getPrincipal());
		caseDetailVo.setLoanRate(caseDetailDto.getLoanRate());
		caseDetailVo.setInterestMoney(caseDetailDto.getInterestMoney());
		caseDetailVo.setPenaltyReference(caseDetailDto.getPenaltyReference());
		caseDetailVo.setCompoundInterestReference(caseDetailDto.getCompoundInterestReference());
		caseDetailVo.setContractMoney(caseDetailDto.getContractMoney());
		caseDetailVo.setConsultFee(caseDetailDto.getConsultFee());
		caseDetailVo.setAuditFee(caseDetailDto.getAuditFee());
		caseDetailVo.setServiceFee(caseDetailDto.getServiceFee());
		caseDetailVo.setAdviserFee(caseDetailDto.getAdviserFee());
		caseDetailVo.setFeeTotal(caseDetailDto.getFeeTotal());
		caseDetailVo.setActualLoanMoney(caseDetailDto.getActualLoanMoney());
		caseDetailVo.setBreach(caseDetailDto.getBreach());
		caseDetailVo.setPenaltyDays(caseDetailDto.getPenaltyDays());
		caseDetailVo.setRemainHistory(caseDetailDto.getRemainHistory());
		caseDetailVo.setLoanStartdate(caseDetailDto.getLoanStartdate());
		caseDetailVo.setLoanEnddate(caseDetailDto.getLoanEnddate());
		caseDetailVo.setMonthRepayment(caseDetailDto.getMonthRepayment());
		caseDetailVo.setLastRepayment(caseDetailDto.getLastRepayment());
		caseDetailVo.setDuePeriods(caseDetailDto.getDuePeriods());
		caseDetailVo.setOverdueMoney(caseDetailDto.getOverdueMoney());
		caseDetailVo.setOverdueDate(caseDetailDto.getOverdueDate());
		caseDetailVo.setOverdueDays(caseDetailDto.getOverdueDays());
		caseDetailVo.setOverdueAge(caseDetailDto.getOverdueAge());
		caseDetailVo.setOverduePeriods(caseDetailDto.getOverduePeriods());
		caseDetailVo.setOverdueLoan(caseDetailDto.getOverdueLoan());
		caseDetailVo.setOverduePrincipal(caseDetailDto.getOverduePrincipal());
		caseDetailVo.setOverduePrincipalBalance(caseDetailDto.getOverduePrincipalBalance());
		caseDetailVo.setOverdueExpense(caseDetailDto.getOverdueExpense());
		caseDetailVo.setOverdueInterest(caseDetailDto.getOverdueInterest());
		caseDetailVo.setOverduePenalty(caseDetailDto.getOverduePenalty());
		caseDetailVo.setOverdueCompound(caseDetailDto.getOverdueCompound());
		caseDetailVo.setOnceOverduePeriods(caseDetailDto.getOnceOverduePeriods());
		caseDetailVo.setStayPrincipal(caseDetailDto.getStayPrincipal());
		caseDetailVo.setStayPeriods(caseDetailDto.getStayPeriods());
		caseDetailVo.setStayInterest(caseDetailDto.getStayInterest());
		caseDetailVo.setStayExpense(caseDetailDto.getStayExpense());
		caseDetailVo.setAccountMoney(caseDetailDto.getAccountMoney());
		caseDetailVo.setBill(caseDetailDto.getBill());
		caseDetailVo.setRepaymentPeriods(caseDetailDto.getRepaymentPeriods());
		caseDetailVo.setDebitBankCode(caseDetailDto.getDebitBankCode());
		caseDetailVo.setDebitBankName(caseDetailDto.getDebitBankName());
		caseDetailVo.setCommodity(caseDetailDto.getCommodity());
		caseDetailVo.setCaseHouseId(caseDetailDto.getCaseHouseId());
		caseDetailVo.setCasePosition(caseDetailDto.getCasePosition());
		caseDetailVo.setCasePositionLevel(caseDetailDto.getCasePositionLevel());
		caseDetailVo.setCompanyName(caseDetailDto.getCompanyName());
		caseDetailVo.setCompanyAddress(caseDetailDto.getCompanyAddress());
		caseDetailVo.setCompanyZipcode(caseDetailDto.getCompanyZipcode());
		caseDetailVo.setCompanyPhone(caseDetailDto.getCompanyPhone());
		caseDetailVo.setFamilyAddress(caseDetailDto.getFamilyAddress());
		caseDetailVo.setFamilyZipcode(caseDetailDto.getFamilyZipcode());
		caseDetailVo.setFamilyPhone(caseDetailDto.getFamilyPhone());
		caseDetailVo.setDomicile(caseDetailDto.getDomicile());
		caseDetailVo.setLinkManName(caseDetailDto.getLinkManName());
		caseDetailVo.setLinkManCardNo(caseDetailDto.getLinkManRelation());
		caseDetailVo.setLinkManRelation(caseDetailDto.getLinkManRelation());
		caseDetailVo.setLinkManMobile(caseDetailDto.getLinkManMobile());
		caseDetailVo.setLinkManFamilyPhone(caseDetailDto.getLinkManFamilyPhone());
		caseDetailVo.setLinkManUnitPhone(caseDetailDto.getLinkManUnitPhone());
		caseDetailVo.setHisRemark(caseDetailDto.getHisRemark());
		caseDetailVo.setCaseLastMoney(caseDetailDto.getCaseLastMoney());
		caseDetailVo.setCaseLastDate(caseDetailDto.getCaseLastDate());
		
		Integer isVerify = caseDetailDto.getIsVerify();
		if(isVerify==null){
			caseDetailVo.setIsVerify("未知");
		}else if(0==isVerify){
			caseDetailVo.setIsVerify("未核销");
		}else if(1==isVerify){
			caseDetailVo.setIsVerify("已核销");
		}
		caseDetailVo.setVerifyDate(caseDetailDto.getVerifyDate());
		caseDetailVo.setRepaymentType(caseDetailDto.getRepaymentType());
		caseDetailVo.setProtocolNo(caseDetailDto.getProtocolNo());
		caseDetailVo.setLoanArea(caseDetailDto.getLoanArea());
		caseDetailVo.setAreaName(caseDetailDto.getAreaName());
		caseDetailVo.setCollateralNo(caseDetailDto.getCollateralNo());
		caseDetailVo.setCollateralId(caseDetailDto.getCollateralId());
		caseDetailVo.setCollateralName(caseDetailDto.getCollateralName());
		caseDetailVo.setCollateralAddress(caseDetailDto.getCollateralAddress());
		caseDetailVo.setTotalConstruc(caseDetailDto.getTotalConstruc());
		caseDetailVo.setCollateralEvalua(caseDetailDto.getCollateralEvalua());
		caseDetailVo.setQuotaNo(caseDetailDto.getQuotaNo());
		caseDetailVo.setQuotaManager1(caseDetailDto.getQuotaManager1());
		caseDetailVo.setManagerPhone(caseDetailDto.getManagerPhone());
		caseDetailVo.setQuotaManager2(caseDetailDto.getQuotaManager2());
		caseDetailVo.setRegione(caseDetailDto.getRegione());
		caseDetailVo.setSubCenter(caseDetailDto.getSubCenter());
		caseDetailVo.setSalesDep(caseDetailDto.getSalesDep());
		caseDetailVo.setAccountNo(caseDetailDto.getAccountNo());
		caseDetailVo.setMarkId(caseDetailDto.getMarkId());
		caseDetailVo.setUserName(caseDetailDto.getUserName());
		caseDetailVo.setLoanContract(caseDetailDto.getLoanContract());
		caseDetailVo.setCaseAppNo(caseDetailDto.getCaseAppNo());
		caseDetailVo.setSocialSecurityNo(caseDetailDto.getSocialSecurityNo());
		caseDetailVo.setCusNo(caseDetailDto.getCusNo());
		caseDetailVo.setUserId(caseDetailDto.getUserId());
		caseDetailVo.setCaseFileNo(caseDetailDto.getCaseFileNo());
		caseDetailVo.setReliefPolicy(caseDetailDto.getReliefPolicy());
		caseDetailVo.setNextFollowTime(caseDetailDto.getNextFollowTime());
		caseDetailVo.setPaidNum(caseDetailDto.getPaidNum());
		caseDetailVo.setPtpMoney(caseDetailDto.getPtpMoney());
		caseDetailVo.setCpMoney(caseDetailDto.getCpMoney());
		caseDetailVo.setProvinceName(caseDetailDto.getProvinceName());
		caseDetailVo.setCityName(caseDetailDto.getCityName());
		caseDetailVo.setLastPhone(caseDetailDto.getLastPhone());
		caseDetailVo.setLastVisit(caseDetailDto.getLastVisit());
		caseDetailVo.setInDerate(caseDetailDto.getInDerate());
		caseDetailVo.setOutDerate(caseDetailDto.getOutDerate());
		caseDetailVo.setColor(caseDetailDto.getColor());
		caseDetailVo.setRemark1(caseDetailDto.getRemark1());
		caseDetailVo.setRemark2(caseDetailDto.getRemark2());
		caseDetailVo.setCusIntroduc(caseDetailDto.getCusIntroduc());
		caseDetailVo.setTemplateType(caseDetailDto.getTemplateType());
		caseDetailVo.setStartCardDate(caseDetailDto.getStartCardDate());
		caseDetailVo.setStopCardDate(caseDetailDto.getStopCardDate());
		caseDetailVo.setCreditLimit(caseDetailDto.getCreditLimit());

		caseDetailVo.setDealer(caseDetailDto.getDealer());
		caseDetailVo.setPrice(caseDetailDto.getPrice());
		caseDetailVo.setLiceNo(caseDetailDto.getLiceNo());
		caseDetailVo.setBrand(caseDetailDto.getBrand());
		caseDetailVo.setNumber(caseDetailDto.getNumber());
		caseDetailVo.setVinNo(caseDetailDto.getVinNo());
		caseDetailVo.setEngineNo(caseDetailDto.getEngineNo());
		
		caseDetailVo.setCaseBirthday(caseDetailDto.getCaseBirthday());
		caseDetailVo.setSocialCardNo(caseDetailDto.getSocialCardNo());
		caseDetailVo.setPolicyExpire(caseDetailDto.getPolicyExpire());
		caseDetailVo.setRepayDate(caseDetailDto.getRepayDate());
		caseDetailVo.setCaseDepartment(caseDetailDto.getCaseDepartment());
		caseDetailVo.setCollecRemark(caseDetailDto.getCollecRemark());
		caseDetailVo.setEmail(caseDetailDto.getEmail());
		caseDetailVo.setCurrency(caseDetailDto.getCurrency());
		caseDetailVo.setOldCollecRecord(caseDetailDto.getOldCollecRecord());
		caseDetailVo.setRepaymentTerm(caseDetailDto.getRepaymentTerm());
		caseDetailVo.setCollecType(caseDetailDto.getCollecType());
		caseDetailVo.setLateFee(caseDetailDto.getLateFee());
		return caseDetailVo;
	}
	
	/**案件评语   详情页:isBatch:false   列表页：isBatch:true*/
	@ResponseBody
	@RequiresPermissions(logical=Logical.OR,value={PermissionConst.case_comment_markcolor,PermissionConst.departmentcase_comment_markcolor,PermissionConst.mycase_comment_markcolor})
	@RequestMapping(value="saveCommentAndUpdateCaseColor", method = RequestMethod.POST)
	public AjaxResult saveCommentAndUpdateCaseColor(HttpServletRequest request,String content, String color, boolean isBatch) {
	
		String []ids=request.getParameterValues("ids[]");
		commentService.saveCommentAndUpdateCaseColor(ids, content, color, isBatch);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	/**
	 * 身份证
	 * @param idCar 身份证号
	 * @return 身份证分析页面
	 * @author wangcy
	 */
	@RequestMapping(value = "idshow", method = RequestMethod.GET)
	public ModelAndView idshow(String idCar) {
		ModelAndView view =  createBaseView("idshow");
		try {
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(idCar,"UTF-8");
			String url="http://"+pythonIp+":8000/idcard";//请求路径
			sb.append("id=").append(params);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法
			if(null!=objArr){
				//获取第一条强转成JSONObject，再获取，再add到页面
				view.addObject("place",((JSONObject)objArr.get(0)).get("place"));//省市
				view.addObject("sex",((JSONObject)objArr.get(0)).get("sex"));//性别
				view.addObject("birth",((JSONObject)objArr.get(0)).get("birth"));//生日
			}else{
				view.addObject("mess","暂无数据");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 工资
	 * @param jobKw 职位名称
	 * @param cityKw 城市名称
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "casePositionshow", method = RequestMethod.GET)
	public ModelAndView casePositionshow(@RequestParam("jobKw")String jobKw,@RequestParam("cityKw")String cityKw) {
		ModelAndView view =  createBaseView("casepositionshow");
		try {
			String city =null;//
			if(StringUtils.isNotBlank(cityKw)){
				//我们库里存的是北京市、南京市、   然而接口数据库里存的是北京 ，  南京、  所以截掉最后一位
				city=cityKw.substring(0,cityKw.length()-1);
			}
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(jobKw,"UTF-8");
			String params1=URLEncoder.encode(city,"UTF-8");
			String url="http://"+pythonIp+":8000/salary";//请求路径
			sb.append("jobKw=").append(params).append("&").append("cityKw=").append(params1);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法
			if(objArr!=null){
				//获取第一条强转成JSONObject，再获取，再add到页面。
				view.addObject("job",((JSONObject)objArr.get(0)).get("job"));//职位名称
				view.addObject("salary",((JSONObject)objArr.get(0)).get("salary"));//预计薪资
				view.addObject("city",((JSONObject)objArr.get(0)).get("city"));//城市
			}else{
				view.addObject("mess","暂无数据");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 电话
	 * @param idCar 电话
	 * @return 电话分析页面
	 * @author wangcy
	 */
	@RequestMapping(value = "Phoneshow", method = RequestMethod.GET)
	public ModelAndView Phoneshow(String phone) {
		ModelAndView view =  createBaseView("phoneshow");
		try {
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(phone,"UTF-8");
			String url="http://"+pythonIp+":8000/phone_belong";//请求路径
			sb.append("phone=").append(params);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法
			if(null!=objArr){
			//获取第一条强转成JSONObject，再获取，再add到页面。
			view.addObject("telecomName",((JSONObject)objArr.get(0)).get("telecom_name"));//通信公司
			view.addObject("postcode",((JSONObject)objArr.get(0)).get("postcode"));//邮编
			view.addObject("phoneareacode",((JSONObject)objArr.get(0)).get("phoneareacode"));//电话区号
			view.addObject("city",((JSONObject)objArr.get(0)).get("city"));//城市
			view.addObject("province",((JSONObject)objArr.get(0)).get("province"));//省份
			view.addObject("phonecode",((JSONObject)objArr.get(0)).get("phonecode"));//手机前7位
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * 派出所
	 * @param areaName2 城市名称
	 * @param areaName3 区名称
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "PoliceStationShow", method = RequestMethod.GET)
	public ModelAndView PoliceStationShow(@RequestParam("areaName2")String areaName2,@RequestParam("areaName3")String areaName3) {
		ModelAndView view =  createBaseView("policestationshow");
		try {
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(areaName2,"UTF-8");
			String params1=URLEncoder.encode(areaName3,"UTF-8");
			String url="http://"+pythonIp+":8000/paichusuo";//请求路径
			sb.append("city=").append(params).append("&").append("qu=").append(params1);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法，获取解析内容
			if(null!=objArr){
				view.addObject("objArr",objArr);//多条派出所信息， 页面在循环输出
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	
	/**
	 * 简历
	 * @param name 姓名
	 * @param cityKw 城市
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "resumeshow", method = RequestMethod.GET)
	public ModelAndView resumeshow(@RequestParam("name")String name,@RequestParam("cityKw")String cityKw) {
		ModelAndView view =  createBaseView("resumeshow");
		try {
			String city =null;//
			if(StringUtils.isNotBlank(cityKw)){
				//我们库里存的是北京市、南京市、   然而接口数据库里存的是北京 ，  南京、  所以截掉最后一位
				city=cityKw.substring(0,cityKw.length()-1);
			}
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(name,"UTF-8");
			String params1=URLEncoder.encode(city,"UTF-8");
			String url="http://"+pythonIp+":8000/job";//请求路径
			sb.append("cityKw=").append(params1).append("&").append("name=").append(params);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法，获取解析内容
			if(null!=objArr){
			view.addObject("objArr",objArr);//多条派出所信息， 页面在循环输出
			}else{
				view.addObject("mess","暂无数据");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * 失信
	 * @param name 姓名
	 * @param idCode 证件号
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "Dishonestyshow", method = RequestMethod.GET)
	public ModelAndView Dishonestyshow(@RequestParam("name")String name,@RequestParam("idCode")String idCode) {
		ModelAndView view =  createBaseView("dishonestyshow");
		try {
			StringBuilder sb = new StringBuilder();
			String params=URLEncoder.encode(name,"UTF-8");
			String params1=URLEncoder.encode(idCode,"UTF-8");
			String url="http://"+pythonIp+":8000/shixin";//请求路径
			sb.append("name=").append(params).append("&").append("idcode=").append(params1);//参数拼接    拼接成固定格式
			JSONArray objArr = pub(sb, url);//json解析方法，获取解析内容
			if(null!=objArr){
			view.addObject("objArr",objArr);//多条派出所信息， 页面在循环输出
			}else{
				view.addObject("mess","暂无数据");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 根据简历id查找简历
	 * @param id 简历id
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "resumeshowForId", method = RequestMethod.GET)
	public ModelAndView resumeshowForId(@RequestParam("id")String id) {
		ModelAndView view =  createBaseView("resumeshowForId");
		StringBuilder sb = new StringBuilder();
		String url="http://"+pythonIp+":8000/job_id";//请求路径
		sb.append("id=").append(id);
		JSONArray objArr = pub(sb, url);//json解析方法，获取解析内容
		if(null!=objArr){
			//获取第一条强转成JSONObject，再获取，再add到页面。
			view.addObject("name",((JSONObject)objArr.get(0)).get("name"));//姓名
			view.addObject("age",((JSONObject)objArr.get(0)).get("age"));//年龄
			view.addObject("sex",((JSONObject)objArr.get(0)).get("sex"));//性别
			view.addObject("experience",((JSONObject)objArr.get(0)).get("experience"));//工作经验
			view.addObject("salary",((JSONObject)objArr.get(0)).get("salary"));//期待薪资
			view.addObject("want_job_name",((JSONObject)objArr.get(0)).get("want_job_name"));//期待职位
			view.addObject("detail",((JSONObject)objArr.get(0)).get("detail2"));//简历详细
			
			
		}else{
			view.addObject("mess","暂无数据");
		}
		return view;
	}
	
	
	/**
	 * 根据失信id查找失信
	 * @param id 失信id
	 * @return 请求页面
	 * @author wangcy
	 */
	@RequestMapping(value = "dishonestyshowForId", method = RequestMethod.GET)
	public ModelAndView dishonestyshowForId(@RequestParam("id")String id) {
		ModelAndView view =  createBaseView("dishonestyshowforid");
		StringBuilder sb = new StringBuilder();
		String url="http://"+pythonIp+":8000/oneshixin";//请求路径
		sb.append("id=").append(id);
		JSONArray objArr = pub(sb, url);//json解析方法，获取解析内容
		if(null!=objArr){
			//获取第一条强转成JSONObject，再获取，再add到页面。
			view.addObject("name",((JSONObject)objArr.get(0)).get("name"));//姓名
			view.addObject("sex",((JSONObject)objArr.get(0)).get("sex"));//姓名
			view.addObject("idcard",((JSONObject)objArr.get(0)).get("idcard"));//姓名
			view.addObject("excute_court",((JSONObject)objArr.get(0)).get("excute_court"));//姓名
			view.addObject("province",((JSONObject)objArr.get(0)).get("province"));//姓名
			view.addObject("excute_basis_num",((JSONObject)objArr.get(0)).get("excute_basis_num"));//姓名
			view.addObject("case_created_time",((JSONObject)objArr.get(0)).get("case_created_time"));//姓名
			view.addObject("case_num",((JSONObject)objArr.get(0)).get("case_num"));//姓名
			view.addObject("excute_basis_organization",((JSONObject)objArr.get(0)).get("excute_basis_organization"));//姓名
			view.addObject("duty",((JSONObject)objArr.get(0)).get("duty"));//姓名
			view.addObject("be_excuted_fulfil_state",((JSONObject)objArr.get(0)).get("be_excuted_fulfil_state"));//姓名
			view.addObject("be_excuted_behaviour_situation",((JSONObject)objArr.get(0)).get("be_excuted_behaviour_situation"));//姓名
			view.addObject("publish_time",((JSONObject)objArr.get(0)).get("publish_time"));//姓名
		}else{
			view.addObject("mess","暂无数据");
		}
		return view;
	}
	
	/**
	 * json解析方法
	 * @param sb 页面传过来的参数
	 * @param url 请求路径
	 * @return 解析结果
	 * @author wangcy
	 */
	private JSONArray pub(StringBuilder sb, String url) {
		//获取json数据
		String josin=HttpRequestUtils.sendGet(url,sb.toString());
		//把String类型转换为JSONObject类型
		JSONObject jsStr = JSONObject.parseObject(josin);
		//获取result内容   （因为数据是由result包裹着的， 不明白就跟一下断点）
		JSONArray objArr=(JSONArray)jsStr.get("result");
		return objArr;
	}

	@Override
	protected String getBaseName() {
		return "collection/casedetail/";
	}

}
