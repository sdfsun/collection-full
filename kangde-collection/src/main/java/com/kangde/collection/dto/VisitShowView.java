package com.kangde.collection.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.util.DateUtil;

/**
 * 外访模板 导出字段提供视图
 * 作用：方便使用导出方法
 * 
 * @date 2016年7月7日10:23:15
 * @author wcy
 *
 */

@SuppressWarnings("serial")
public class VisitShowView extends VisitRecordModel implements Serializable{
	
	private Date currentDate = new Date();
	
	/** 年 */
	private int year = DateUtil.getYear(currentDate);
	/** 月 */
	private int month = DateUtil.getMonth(currentDate);
	/** 日 */
	private int day = DateUtil.getDay(currentDate);
	
	

	/** 批次号 */
	private String batchCode;
	/** 账单日 */
	private Integer bill;
	/** 最后还款金额 */
	private Double lastRepayment;
	/** 剩余本金 */
	private Double surplusPrincipal;
	/** 已还期数 */
	private Integer repaymentPeriods;
	/** 逾期罚息 */
	private Double overduePenalty;

	/** 风控员 */
	private String userName;
	/** 委托方? */
	private String name;
	/** 委托方 */
	private String entrustName;
	/** 案件序列号*/
	private String caseCode;
	/** 委案日期*/
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date caseDate;
	/** 退案日期*/
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualEndDate;
	/** 证件号*/
	private String caseNum;
	/** 姓名*/
	private String caseName;
	/** 逾期开始日期*/
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date overdueDate;
	/** 逾期天数*/
	private Integer overdueDays;
	/** 逾期期数*/
	private Integer overduePeriods;
	/** 逾期账龄*/
	private String overdueAge;
	/** 委案金额*/
	private BigDecimal caseMoney;
	/** 卡号*/
	private String caseCard;
	/** 性别*/
	private String caseSex;
	/** 年龄*/
	private Integer caseAge;
	/** 婚否*/
	private String caseIsMarriage;
	/** 职位*/
	private String casePosition;
	/** 单位名称*/
	private String companyName;
	/** 单位号码*/
	private String companyPhone;
	/** 单位地址*/
	private String companyAddress;
	/** 家庭号码*/
	private String familyPhone;
	/** 本人手机1 */
	private String mobile1;
	/** 本人手机2 */
	private String mobile2;
	/** 家庭地址 */
	private String familyAddress;
	/** 户籍 */
	private String domicile;
	/** 押品编号 */
	private String collateralNo;
	/** 押品名称 */
	private String collateralName;
	/** 押品地址 */
	private String collateralAddress;
	/** 押品评估值 */
	private String collateralEvalua;
	/** 押品类型 */
	private String collateralId;
	/** 省份名称 */
	private String oName;
	/** 城市名称 */
	private String tName;
	/** 县名称 */
	private String sName;
	/** 已还金额 */
	private BigDecimal paidNum;
	
	//信函字段
	/** ID */
	private String id;
	/** 风控机构 */
	private String orgName;
	/** 风控员 */
	private String caseAssignedName;
	/** 风控员手机号码 */
	private String caseAssignedPhone;
	/** 证件类型 */
	private String caseNumId;
	/** 信函地址 */
	private String caseApplyAddress;
	/** 公司邮编 */
	private String companyZipcode;
	/** 家庭邮编 */
	private String familyZipcode;
	/** 账号 */
	private String accountNo;
	/** 商品名称 */
	private String commodity;
	/** 档案号 */
	private String caseFileNo;
	/** 贷款日期 */
	private Date loanStartdate;
	/** 扣款银行名称 */
	private String debitBankName;
	/** 本金 */
	private Double principal;
	/** 每月还款金额 */
	private Double monthRepayment;
	/** 贷款截止日期 */
	private Date loanEnddate;
	/** 开户行 */
	private String bankAccount;
	/** 申请单号 */
	private String caseAppNo;
	/** 风控状态 对应字典表 */
	private String collecStateId;
	
	
	//卡号(隐藏后四位)
	private String caseNumHideLast;
	public String getCaseNumHideLast() {
		caseNumHideLast = CoreUtil.lastFourHide(this.caseNum);
		return caseNumHideLast;
	}
	//卡号(隐藏中四位) 
	private String caseNumHideMiddle;
	public String getCaseNumHideMiddle() {
		caseNumHideMiddle = CoreUtil.middleFourHide(this.caseNum);
		return caseNumHideMiddle;
	}
	
	//证件号(隐藏后四位)
	private String caseCardHideLast;
	public String getCaseCardHideLast() {
		caseCardHideLast = CoreUtil.lastFourHide(this.caseCard); 
		return caseCardHideLast;
	}
	
	//证件号(隐藏中四位) 
	private String caseCardHideMiddle;
	public String getCaseCardHideMiddle() {
		caseCardHideMiddle = CoreUtil.middleFourHide(this.caseCard); 
		return caseCardHideMiddle;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public Date getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(Date caseDate) {
		this.caseDate = caseDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public Date getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}
	public Integer getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}
	public Integer getOverduePeriods() {
		return overduePeriods;
	}
	public void setOverduePeriods(Integer overduePeriods) {
		this.overduePeriods = overduePeriods;
	}
	public String getOverdueAge() {
		return overdueAge;
	}
	public void setOverdueAge(String overdueAge) {
		this.overdueAge = overdueAge;
	}
	public BigDecimal getCaseMoney() {
		return caseMoney;
	}
	public void setCaseMoney(BigDecimal caseMoney) {
		this.caseMoney = caseMoney;
	}
	public String getCaseCard() {
		return caseCard;
	}
	public void setCaseCard(String caseCard) {
		this.caseCard = caseCard;
	}
	public String getCaseSex() {
		return caseSex;
	}
	public void setCaseSex(String caseSex) {
		this.caseSex = caseSex;
	}
	public Integer getCaseAge() {
		return caseAge;
	}
	public void setCaseAge(Integer caseAge) {
		this.caseAge = caseAge;
	}
	public String getCaseIsMarriage() {
		return caseIsMarriage;
	}
	public void setCaseIsMarriage(String caseIsMarriage) {
		this.caseIsMarriage = caseIsMarriage;
	}
	public String getCasePosition() {
		return casePosition;
	}
	public void setCasePosition(String casePosition) {
		this.casePosition = casePosition;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public String getCollateralNo() {
		return collateralNo;
	}
	public void setCollateralNo(String collateralNo) {
		this.collateralNo = collateralNo;
	}
	public String getCollateralName() {
		return collateralName;
	}
	public void setCollateralName(String collateralName) {
		this.collateralName = collateralName;
	}
	public String getCollateralAddress() {
		return collateralAddress;
	}
	public void setCollateralAddress(String collateralAddress) {
		this.collateralAddress = collateralAddress;
	}
	public String getCollateralEvalua() {
		return collateralEvalua;
	}
	public void setCollateralEvalua(String collateralEvalua) {
		this.collateralEvalua = collateralEvalua;
	}
	public String getCollateralId() {
		return collateralId;
	}
	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCaseAssignedName() {
		return caseAssignedName;
	}
	public void setCaseAssignedName(String caseAssignedName) {
		this.caseAssignedName = caseAssignedName;
	}
	public String getCaseNumId() {
		return caseNumId;
	}
	public void setCaseNumId(String caseNumId) {
		this.caseNumId = caseNumId;
	}
	public String getCaseApplyAddress() {
		return caseApplyAddress;
	}
	public void setCaseApplyAddress(String caseApplyAddress) {
		this.caseApplyAddress = caseApplyAddress;
	}
	public String getCompanyZipcode() {
		return companyZipcode;
	}
	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}
	public String getFamilyZipcode() {
		return familyZipcode;
	}
	public void setFamilyZipcode(String familyZipcode) {
		this.familyZipcode = familyZipcode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getCaseFileNo() {
		return caseFileNo;
	}
	public void setCaseFileNo(String caseFileNo) {
		this.caseFileNo = caseFileNo;
	}
	public Date getLoanStartdate() {
		return loanStartdate;
	}
	public void setLoanStartdate(Date loanStartdate) {
		this.loanStartdate = loanStartdate;
	}
	public String getDebitBankName() {
		return debitBankName;
	}
	public void setDebitBankName(String debitBankName) {
		this.debitBankName = debitBankName;
	}
	public Double getPrincipal() {
		return principal;
	}
	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
	public Double getMonthRepayment() {
		return monthRepayment;
	}
	public void setMonthRepayment(Double monthRepayment) {
		this.monthRepayment = monthRepayment;
	}
	public Date getLoanEnddate() {
		return loanEnddate;
	}
	public void setLoanEnddate(Date loanEnddate) {
		this.loanEnddate = loanEnddate;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getCaseAppNo() {
		return caseAppNo;
	}
	public void setCaseAppNo(String caseAppNo) {
		this.caseAppNo = caseAppNo;
	}
	public String getCaseAssignedPhone() {
		return caseAssignedPhone;
	}
	public void setCaseAssignedPhone(String caseAssignedPhone) {
		this.caseAssignedPhone = caseAssignedPhone;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Double getLastRepayment() {
		return lastRepayment;
	}

	public void setLastRepayment(Double lastRepayment) {
		this.lastRepayment = lastRepayment;
	}

	public Double getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(Double surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public Integer getRepaymentPeriods() {
		return repaymentPeriods;
	}

	public void setRepaymentPeriods(Integer repaymentPeriods) {
		this.repaymentPeriods = repaymentPeriods;
	}

	public Double getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(Double overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public String getCollecStateId() {
		return collecStateId;
	}

	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}

	public void setCaseNumHideLast(String caseNumHideLast) {
		this.caseNumHideLast = caseNumHideLast;
	}

	public void setCaseNumHideMiddle(String caseNumHideMiddle) {
		this.caseNumHideMiddle = caseNumHideMiddle;
	}

	public void setCaseCardHideLast(String caseCardHideLast) {
		this.caseCardHideLast = caseCardHideLast;
	}

	public void setCaseCardHideMiddle(String caseCardHideMiddle) {
		this.caseCardHideMiddle = caseCardHideMiddle;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public BigDecimal getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(BigDecimal paidNum) {
		this.paidNum = paidNum;
	}

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}

}
