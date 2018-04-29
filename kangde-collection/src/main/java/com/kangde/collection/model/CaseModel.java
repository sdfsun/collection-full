package com.kangde.collection.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;

/**
 * 案件模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseModel extends BizModel<String> {

	// 案件状态 0正常(恢复) 1暂停 2关闭 3退案 4 结清 ,5 撤回
	/** 案件状态 0正常 */
	public static final Integer STATE_NORMAL = 0;
	/** 案件状态 1暂停 */
	public static final Integer STATE_PAUSE = 1;
	/** 案件状态 2关闭 */
	public static final Integer STATE_CLOSE = 2;
	/** 案件状态 3退案 */
	public static final Integer STATE_BACK = 3;
	/** 案件状态 4 结清 */
	public static final Integer STATE_SETTLE = 4;
	/** 案件状态 5 撤回 */
	public static final Integer STATE_REVOCATION = 5;

	// 是否核销
	/** 已核销 */
	public static final Integer IS_VERIFY_TRUE = 1;
	/** 为核销 */
	public static final Integer IS_VERIFY_FALSE = 0;

	/** 案件序列号 */
	private String caseCode;

	/** 风控状态 对应字典表 */
	private String collecStateId;

	/** 批次ID */
	private String batchId;

	/** 委案日期 */
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date caseDate;

	/** 预计退案日 */
	private Date caseBackdate;
	/** 退案日 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date endDate;

	/** 客户编号 */
	private String cusNo;

	/** 用户ID */
	private String userId;

	/** 账号 */
	private String accountNo;

	/** 申请单号 */
	private String caseAppNo;

	/** 档案号 */
	private String caseFileNo;

	/** 姓名 */
	private String caseName;

	/** 性别 */
	private String caseSex;

	/** 婚否 */
	private String caseIsMarriage;

	/** 生日 */
	private Date caseBirthday;

	/** 年龄 */
	private Integer caseAge;

	/** 风控区域ID */
	private String areaId;

	/** 风控区域名称 */
	private String areaName;

	/** 开户行 */
	private String bankAccount;

	/** 案人房产类型 */
	private String caseHouseId;

	/** 卡号 */
	private String caseCard;

	/** 证件号 */
	private String caseNum;

	/** 证件类型ID */
	private String caseNumId;

	/** 职位 */
	private String casePosition;

	/** 职位级别 */
	private String casePositionLevel;

	/** 账号名 */
	private String accountName;

	/** 放款机构 */
	private String lendInstitution;

	/** 原风控记录 */
	private String oldCollecRecord;

	/** 本金 */
	private Double principal;

	/** 剩余本金 */
	private Double surplusPrincipal;

	/** 委案金额 */
	private BigDecimal caseMoney;

	/** 贷款合同号 */
	private String loanContract;

	/** 贷款金额 */
	private Double loanMoney;

	/** 贷款利率 */
	private Double loanRate;

	/** 贷款日期 */
	private Date loanStartdate;

	/** 贷款截止日期 */
	private Date loanEnddate;

	/** 签约金额 */
	private Double contractMoney;

	/** 逾期金额 */
	private Double overdueMoney;

	/** 逾期本金 */
	private Double overduePrincipal;

	/** 违约金 */
	private Double breach;

	/** 逾期管理费 */
	private Double overdueExpense;

	/** 逾期账龄 */
	private String overdueAge;

	/** 逾期罚息 */
	private Double overduePenalty;

	/** 逾期利息 */
	private Double overdueInterest;

	/** 逾期天数 */
	private Integer overdueDays;

	/** 逾期开始日期 */
	private Date overdueDate;

	/** 逾期期数 */
	private Integer overduePeriods;

	/** 曾逾期期数 */
	private Integer onceOverduePeriods;

	/** 逾期借款 */
	private Double overdueLoan;

	/** 逾期本金余额 */
	private Double overduePrincipalBalance;

	/** 逾期复利 */
	private Double overdueCompound;

	/** 社保电脑号 */
	private String socialSecurityNo;
	
	/** 代理费率 */
	private Double agentRate;

	/** 委托费率 */
	private String entrustRate;

	/** 信贷分类 */
	private String creditId;

	/** 待还期数 */
	private Integer stayPeriods;

	/** 每月还款金额 */
	private Double monthRepayment;

	/** 最后还款金额 */
	private Double lastRepayment;

	/** 待还本金 */
	private Double stayPrincipal;

	/** 待还管理费 */
	private Double stayExpense;

	/** 待还利息 */
	private BigDecimal stayInterest;

	/** 账单日 */
	private Integer bill;

	/** 已还期数 */
	private Integer repaymentPeriods;

	/** 商品 */
	private String commodity;

	/** 单位名称 */
	private String companyName;

	/** 单位号码 */
	private String companyPhone;

	/** 单位地址 */
	private String companyAddress;

	/** 单位邮编 */
	private String companyZipcode;

	/** 本人手机1 */
	private String mobile1;

	/** 本人手机2 */
	private String mobile2;

	/** 家庭号码 */
	private String familyPhone;

	/** 家庭地址 */
	private String familyAddress;

	/** 家庭邮编 */
	private String familyZipcode;

	/** 户籍 */
	private String domicile;

	/** 标ID */
	private String markId;

	/** 用户名 */
	private String userName;

	/** 是否核销 0未核销 1已核销 */
	private Integer isVerify;

	/** 核销日期 */
	private Date verifyDate;

	/** 还款方式 */
	private String repaymentType;

	/** 协议编号 */
	private String protocolNo;

	/** 放款所属地 */
	private String loanArea;

	/** 实际-放款金额 */
	private Double actualLoanMoney;

	/** 欠息余额 */
	private Double interestMoney;

	/** 罚息参照 */
	private String penaltyReference;

	/** 复息参照 */
	private String compoundInterestReference;

	/** 根额度编号 */
	private String quotaNo;

	/** 根额度主办人客户经理1 */
	private String quotaManager1;

	/** 根额度协办人客户经理2 */
	private String quotaManager2;

	/** 客户经理电话 */
	private String managerPhone;

	/** 押品编号 */
	private String collateralNo;

	/** 押品类型 */
	private String collateralId;

	/** 押品名称 */
	private String collateralName;

	/** 押品地址 */
	private String collateralAddress;

	/** 押品评估值 */
	private String collateralEvalua;

	/** 还款日 */
	private Integer repayDate;

	/** 建筑面积合计 */
	private String totalConstruc;

	/** 大区名称 */
	private String regione;

	/** 分中心名称 */
	private String subCenter;

	/** 营业部名称 */
	private String salesDep;

	/** 咨询费 */
	private Double consultFee;

	/** 审核费 */
	private Double auditFee;

	/** 服务费 */
	private Double serviceFee;

	/** 顾问费 */
	private Double adviserFee;

	/** 费用总和 */
	private Double feeTotal;

	/** 最后还款日 */
	 @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date initialRepay;

	/** 应还期数 */
	private Integer duePeriods;

	/** 日罚息 */
	private Double penaltyDays;

	/** 历史遗留 */
	private String remainHistory;

	/** 扣款银行代码 */
	private String debitBankCode;

	/** 扣款银行名称 */
	private String debitBankName;

	/** 减免政策 */
	private String reliefPolicy;

	/** 备注1 */
	private String remark1;

	/** 备注2 */
	private String remark2;

	/** 被分配人ID,(风控员(员工)ID) */
	private String caseAssignedId;

	/** 分配人ID,(风控员(员工)ID) */
	private String caseAssignId;

	/** 分配人名称 */
	private String caseAssignName;

	/** 分配时间 */
	private Date caseAssignTime;

	/** 账户余额 */
	private BigDecimal accountMoney;

	/** 扣款账号 */
	private Double debitAccount;

	/** 组织id-风控方id */
	private String orgId;

	/** 组织名称-风控方名称 */
	private String orgName;
	/** 下次跟进时间 */
	private Date nextFollowTime;
	
	/** 下次跟进状态 1电话风控 2外访风控 */
	private String nextFollowState;
	
	/** 是否更新 0否 1是  */
	private String isUpdate = "0";
	
	/** 剩余金额  */
	private Double remainMoney;
	
	/** 省份ID  */
	private Integer provinceId;
	/** 省份名称  */
	private String provinceName;
	/** 市ID  */
	private Integer cityId;
	/** 市名称  */
	private String cityName;
	/** 手别   */
	private String handle;
	/** 内部减免   */
	private Double inDerate;
	/** 外部减免   */
	private Double outDerate;
	/** 业绩率   */
	private Double achieve;
	/** 案件标色   */
	private String color="0";
	  /** 开卡日   */
	private Date startCardDate;
	  /** 停卡日   */
	private Date stopCardDate;
	  /** 信用额度   */
	private Double creditLimit;
	
	
	/** 社保卡号 */
	private String socialCardNo;
	/** 保单到期日 */
	private Date policyExpire; 
	/** 案人部门 */
	private String caseDepartment;
	/** 催收小结 */
	private String collecRemark;
	/** 邮箱*/
	private String email;
	/** 币种*/
	private String currency; 
	/** 还款期限*/
	private String repaymentTerm; 
	/** 催收分类*/
	private String collecType;
	/** 滞纳金*/
	private Double lateFee; 
	/** 户籍地邮编*/
	private String domicileZipcode;
	/** 对账单地址*/
	private String billAddress; 
	/** 对账单邮编*/
	private String billZipcode; 
	/** 客户简介*/
	private String cusIntroduc;
	/** 最后退案日*/
	private Date lastPhone;
	/** 最后退案日*/
	private Date lastPhoneTime;
	/** 历史备注*/
	private String hisRemark;
	/** 最新欠款*/
	private BigDecimal caseLastMoney;
	/** 最新欠款时间*/
	private Date caseLastDate;
	
	
	public BigDecimal getCaseLastMoney() {
		return caseLastMoney;
	}

	public void setCaseLastMoney(BigDecimal caseLastMoney) {
		this.caseLastMoney = caseLastMoney;
	}
	public Date getCaseLastDate() {
		return caseLastDate;
	}

	public void setCaseLastDate(Date caseLastDate) {
		this.caseLastDate = caseLastDate;
	}

	public String getHisRemark() {
		return hisRemark;
	}

	public void setHisRemark(String hisRemark) {
		this.hisRemark = hisRemark;
	}

	public Date getLastPhone() {
		return lastPhone;
	}

	public void setLastPhone(Date lastPhone) {
		this.lastPhone = lastPhone;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCollecStateId() {
		return collecStateId;
	}

	public void setCollecStateId(String collecStateId) {
		this.collecStateId = collecStateId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(Date caseDate) {
		this.caseDate = caseDate;
	}

	public Date getCaseBackdate() {
		return caseBackdate;
	}

	public void setCaseBackdate(Date caseBackdate) {
		this.caseBackdate = caseBackdate;
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCaseAppNo() {
		return caseAppNo;
	}

	public void setCaseAppNo(String caseAppNo) {
		this.caseAppNo = caseAppNo;
	}

	public String getCaseFileNo() {
		return caseFileNo;
	}

	public void setCaseFileNo(String caseFileNo) {
		this.caseFileNo = caseFileNo;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseSex() {
		return caseSex;
	}

	public void setCaseSex(String caseSex) {
		this.caseSex = caseSex;
	}

	public String getCaseIsMarriage() {
		return caseIsMarriage;
	}

	public void setCaseIsMarriage(String caseIsMarriage) {
		this.caseIsMarriage = caseIsMarriage;
	}

	public Date getCaseBirthday() {
		return caseBirthday;
	}

	public void setCaseBirthday(Date caseBirthday) {
		this.caseBirthday = caseBirthday;
	}

	public Integer getCaseAge() {
		return caseAge;
	}

	public void setCaseAge(Integer caseAge) {
		this.caseAge = caseAge;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCaseHouseId() {
		return caseHouseId;
	}

	public void setCaseHouseId(String caseHouseId) {
		this.caseHouseId = caseHouseId;
	}

	public String getCaseCard() {
		return caseCard;
	}

	public void setCaseCard(String caseCard) {
		this.caseCard = caseCard;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseNumId() {
		return caseNumId;
	}

	public void setCaseNumId(String caseNumId) {
		this.caseNumId = caseNumId;
	}

	public String getCasePosition() {
		return casePosition;
	}

	public void setCasePosition(String casePosition) {
		this.casePosition = casePosition;
	}

	public String getCasePositionLevel() {
		return casePositionLevel;
	}

	public void setCasePositionLevel(String casePositionLevel) {
		this.casePositionLevel = casePositionLevel;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLendInstitution() {
		return lendInstitution;
	}

	public void setLendInstitution(String lendInstitution) {
		this.lendInstitution = lendInstitution;
	}

	public String getOldCollecRecord() {
		return oldCollecRecord;
	}

	public void setOldCollecRecord(String oldCollecRecord) {
		this.oldCollecRecord = oldCollecRecord;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(Double surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public BigDecimal getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(BigDecimal caseMoney) {
		this.caseMoney = caseMoney;
	}

	public String getLoanContract() {
		return loanContract;
	}

	public void setLoanContract(String loanContract) {
		this.loanContract = loanContract;
	}

	public Double getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(Double loanMoney) {
		this.loanMoney = loanMoney;
	}

	public Double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(Double loanRate) {
		this.loanRate = loanRate;
	}

	public Date getLoanStartdate() {
		return loanStartdate;
	}

	public void setLoanStartdate(Date loanStartdate) {
		this.loanStartdate = loanStartdate;
	}

	public Date getLoanEnddate() {
		return loanEnddate;
	}

	public void setLoanEnddate(Date loanEnddate) {
		this.loanEnddate = loanEnddate;
	}

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Double getOverdueMoney() {
		return overdueMoney;
	}

	public void setOverdueMoney(Double overdueMoney) {
		this.overdueMoney = overdueMoney;
	}

	public Double getOverduePrincipal() {
		return overduePrincipal;
	}

	public void setOverduePrincipal(Double overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}

	public Double getBreach() {
		return breach;
	}

	public void setBreach(Double breach) {
		this.breach = breach;
	}

	public Double getOverdueExpense() {
		return overdueExpense;
	}

	public void setOverdueExpense(Double overdueExpense) {
		this.overdueExpense = overdueExpense;
	}

	public String getOverdueAge() {
		return overdueAge;
	}

	public void setOverdueAge(String overdueAge) {
		this.overdueAge = overdueAge;
	}

	public Double getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(Double overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public Double getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(Double overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public Integer getOverduePeriods() {
		return overduePeriods;
	}

	public void setOverduePeriods(Integer overduePeriods) {
		this.overduePeriods = overduePeriods;
	}

	public Integer getOnceOverduePeriods() {
		return onceOverduePeriods;
	}

	public void setOnceOverduePeriods(Integer onceOverduePeriods) {
		this.onceOverduePeriods = onceOverduePeriods;
	}

	public Double getOverdueLoan() {
		return overdueLoan;
	}

	public void setOverdueLoan(Double overdueLoan) {
		this.overdueLoan = overdueLoan;
	}

	public Double getOverduePrincipalBalance() {
		return overduePrincipalBalance;
	}

	public void setOverduePrincipalBalance(Double overduePrincipalBalance) {
		this.overduePrincipalBalance = overduePrincipalBalance;
	}

	public Double getOverdueCompound() {
		return overdueCompound;
	}

	public void setOverdueCompound(Double overdueCompound) {
		this.overdueCompound = overdueCompound;
	}

	public String getSocialSecurityNo() {
		return socialSecurityNo;
	}

	public void setSocialSecurityNo(String socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
	}

	public Double getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(Double agentRate) {
		this.agentRate = agentRate;
	}

	public String getEntrustRate() {
		return entrustRate;
	}

	public void setEntrustRate(String entrustRate) {
		this.entrustRate = entrustRate;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public Integer getStayPeriods() {
		return stayPeriods;
	}

	public void setStayPeriods(Integer stayPeriods) {
		this.stayPeriods = stayPeriods;
	}

	public Double getMonthRepayment() {
		return monthRepayment;
	}

	public void setMonthRepayment(Double monthRepayment) {
		this.monthRepayment = monthRepayment;
	}

	public Double getLastRepayment() {
		return lastRepayment;
	}

	public void setLastRepayment(Double lastRepayment) {
		this.lastRepayment = lastRepayment;
	}

	public Double getStayPrincipal() {
		return stayPrincipal;
	}

	public void setStayPrincipal(Double stayPrincipal) {
		this.stayPrincipal = stayPrincipal;
	}

	public Double getStayExpense() {
		return stayExpense;
	}

	public void setStayExpense(Double stayExpense) {
		this.stayExpense = stayExpense;
	}

	public BigDecimal getStayInterest() {
		return stayInterest;
	}

	public void setStayInterest(BigDecimal stayInterest) {
		this.stayInterest = stayInterest;
	}

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Integer getRepaymentPeriods() {
		return repaymentPeriods;
	}

	public void setRepaymentPeriods(Integer repaymentPeriods) {
		this.repaymentPeriods = repaymentPeriods;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
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

	public String getCompanyZipcode() {
		return companyZipcode;
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
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

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getFamilyZipcode() {
		return familyZipcode;
	}

	public void setFamilyZipcode(String familyZipcode) {
		this.familyZipcode = familyZipcode;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getLoanArea() {
		return loanArea;
	}

	public void setLoanArea(String loanArea) {
		this.loanArea = loanArea;
	}

	public Double getActualLoanMoney() {
		return actualLoanMoney;
	}

	public void setActualLoanMoney(Double actualLoanMoney) {
		this.actualLoanMoney = actualLoanMoney;
	}

	public Double getInterestMoney() {
		return interestMoney;
	}

	public void setInterestMoney(Double interestMoney) {
		this.interestMoney = interestMoney;
	}

	public String getPenaltyReference() {
		return penaltyReference;
	}

	public void setPenaltyReference(String penaltyReference) {
		this.penaltyReference = penaltyReference;
	}

	public String getCompoundInterestReference() {
		return compoundInterestReference;
	}

	public void setCompoundInterestReference(String compoundInterestReference) {
		this.compoundInterestReference = compoundInterestReference;
	}

	public String getQuotaNo() {
		return quotaNo;
	}

	public void setQuotaNo(String quotaNo) {
		this.quotaNo = quotaNo;
	}

	public String getQuotaManager1() {
		return quotaManager1;
	}

	public void setQuotaManager1(String quotaManager1) {
		this.quotaManager1 = quotaManager1;
	}

	public String getQuotaManager2() {
		return quotaManager2;
	}

	public void setQuotaManager2(String quotaManager2) {
		this.quotaManager2 = quotaManager2;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getCollateralNo() {
		return collateralNo;
	}

	public void setCollateralNo(String collateralNo) {
		this.collateralNo = collateralNo;
	}

	public String getCollateralId() {
		return collateralId;
	}

	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
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

	public Integer getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
	}

	public String getTotalConstruc() {
		return totalConstruc;
	}

	public void setTotalConstruc(String totalConstruc) {
		this.totalConstruc = totalConstruc;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getSubCenter() {
		return subCenter;
	}

	public void setSubCenter(String subCenter) {
		this.subCenter = subCenter;
	}

	public String getSalesDep() {
		return salesDep;
	}

	public void setSalesDep(String salesDep) {
		this.salesDep = salesDep;
	}

	public Double getConsultFee() {
		return consultFee;
	}

	public void setConsultFee(Double consultFee) {
		this.consultFee = consultFee;
	}

	public Double getAuditFee() {
		return auditFee;
	}

	public void setAuditFee(Double auditFee) {
		this.auditFee = auditFee;
	}

	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Double getAdviserFee() {
		return adviserFee;
	}

	public void setAdviserFee(Double adviserFee) {
		this.adviserFee = adviserFee;
	}

	public Double getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(Double feeTotal) {
		this.feeTotal = feeTotal;
	}

	public Date getInitialRepay() {
		return initialRepay;
	}

	public void setInitialRepay(Date initialRepay) {
		this.initialRepay = initialRepay;
	}

	public Integer getDuePeriods() {
		return duePeriods;
	}

	public void setDuePeriods(Integer duePeriods) {
		this.duePeriods = duePeriods;
	}

	public Double getPenaltyDays() {
		return penaltyDays;
	}

	public void setPenaltyDays(Double penaltyDays) {
		this.penaltyDays = penaltyDays;
	}

	public String getRemainHistory() {
		return remainHistory;
	}

	public void setRemainHistory(String remainHistory) {
		this.remainHistory = remainHistory;
	}

	public String getDebitBankCode() {
		return debitBankCode;
	}

	public void setDebitBankCode(String debitBankCode) {
		this.debitBankCode = debitBankCode;
	}

	public String getDebitBankName() {
		return debitBankName;
	}

	public void setDebitBankName(String debitBankName) {
		this.debitBankName = debitBankName;
	}

	public String getReliefPolicy() {
		return reliefPolicy;
	}

	public void setReliefPolicy(String reliefPolicy) {
		this.reliefPolicy = reliefPolicy;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getCaseAssignedId() {
		return caseAssignedId;
	}

	public void setCaseAssignedId(String caseAssignedId) {
		this.caseAssignedId = caseAssignedId;
	}

	public String getCaseAssignId() {
		return caseAssignId;
	}

	public void setCaseAssignId(String caseAssignId) {
		this.caseAssignId = caseAssignId;
	}

	public String getCaseAssignName() {
		return caseAssignName;
	}

	public void setCaseAssignName(String caseAssignName) {
		this.caseAssignName = caseAssignName;
	}

	public Date getCaseAssignTime() {
		return caseAssignTime;
	}

	public void setCaseAssignTime(Date caseAssignTime) {
		this.caseAssignTime = caseAssignTime;
	}

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}

	public Double getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Double debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getNextFollowTime() {
		return nextFollowTime;
	}

	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}

	public String getNextFollowState() {
		return nextFollowState;
	}

	public void setNextFollowState(String nextFollowState) {
		this.nextFollowState = nextFollowState;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Double getInDerate() {
		return inDerate;
	}

	public void setInDerate(Double inDerate) {
		this.inDerate = inDerate;
	}

	public Double getOutDerate() {
		return outDerate;
	}

	public void setOutDerate(Double outDerate) {
		this.outDerate = outDerate;
	}

	public Double getAchieve() {
		return achieve;
	}

	public void setAchieve(Double achieve) {
		this.achieve = achieve;
	}

	public Date getStartCardDate() {
		return startCardDate;
	}

	public void setStartCardDate(Date startCardDate) {
		this.startCardDate = startCardDate;
	}

	public Date getStopCardDate() {
		return stopCardDate;
	}

	public void setStopCardDate(Date stopCardDate) {
		this.stopCardDate = stopCardDate;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getSocialCardNo() {
		return socialCardNo;
	}

	public void setSocialCardNo(String socialCardNo) {
		this.socialCardNo = socialCardNo;
	}

	public Date getPolicyExpire() {
		return policyExpire;
	}

	public void setPolicyExpire(Date policyExpire) {
		this.policyExpire = policyExpire;
	}

	public String getCaseDepartment() {
		return caseDepartment;
	}

	public void setCaseDepartment(String caseDepartment) {
		this.caseDepartment = caseDepartment;
	}

	public String getCollecRemark() {
		return collecRemark;
	}

	public void setCollecRemark(String collecRemark) {
		this.collecRemark = collecRemark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRepaymentTerm() {
		return repaymentTerm;
	}

	public void setRepaymentTerm(String repaymentTerm) {
		this.repaymentTerm = repaymentTerm;
	}

	public String getCollecType() {
		return collecType;
	}

	public void setCollecType(String collecType) {
		this.collecType = collecType;
	}

	public Double getLateFee() {
		return lateFee;
	}

	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}

	public String getDomicileZipcode() {
		return domicileZipcode;
	}

	public void setDomicileZipcode(String domicileZipcode) {
		this.domicileZipcode = domicileZipcode;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getBillZipcode() {
		return billZipcode;
	}

	public void setBillZipcode(String billZipcode) {
		this.billZipcode = billZipcode;
	}

	public String getCusIntroduc() {
		return cusIntroduc;
	}

	public void setCusIntroduc(String cusIntroduc) {
		this.cusIntroduc = cusIntroduc;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastPhoneTime() {
		return lastPhoneTime;
	}

	public void setLastPhoneTime(Date lastPhoneTime) {
		this.lastPhoneTime = lastPhoneTime;
	}

}