package com.kangde.collection.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CaseDetailVo {
	private String batchCode;
	/** 案件序列号 */
	private String caseCode;
	/** 已还款 */
	private BigDecimal paidNum;
	/** PTP金额 */
	private BigDecimal ptpMoney;
	/** CP金额 */
	private BigDecimal cpMoney;
	/** 案件区域 省 */
	private String provinceName;
	/** 案件区域 市 */
	private String cityName;
	/** 最后通电 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastPhone;
	/** 最后外访 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastVisit;

	/** 姓名 */
	private String caseName;
	/** 本人手机1 */
	private String mobile1;
	/** 本人手机2 */
	private String mobile2;
	/** 证件类型 */
	private String caseNumId;
	/** 证件号 */
	private String caseNum;
	/** 性别 */
	private String caseSex;
	/** 年龄 */
	private Integer caseAge;
	/** 婚否 */
	private String caseIsMarriage;
	/** 开户行 */
	private String bankAccount;
	/** 卡号 */
	private String caseCard;
	/** 委案金额 */
	private BigDecimal caseMoney;
	/** 最新欠款 */
	private BigDecimal latestMoney;
	/** 委案日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date caseDate;
	/** 预计退案日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date caseBackdate;
	/** 委托方 */
	private String entrustName;
	/** 委托费率 */
	private String entrustRate;
	/** 代理费率 */
	private Double agentRate;
	/** 信贷分类 */
	private String creditId;
	/** 贷款金额 */
	private Double loanMoney;
	/** 还款日 */
	private Integer repayDay;
	/** 起始还款日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date initialRepay;
	/** 剩余本金 */
	private Double surplusPrincipal;
	/** 本金 */
	private Double principal;
	/** 贷款利率 */
	private Double loanRate;
	/** 欠息余额 */
	private Double interestMoney;
	/** 罚息参照 */
	private String penaltyReference;
	/** 复息参照 */
	private String compoundInterestReference;
	/** 签约金额 */
	private Double contractMoney;
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
	/** 实际-放款金额 */
	private Double actualLoanMoney;
	/** 违约金 */
	private Double breach;
	/** 日罚息 */
	private Double penaltyDays;
	/** 历史余留 */
	private String remainHistory;
	/** 贷款日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loanStartdate;
	/** 贷款截止日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loanEnddate;
	/** 每月还款金额 */
	private Double monthRepayment;
	/** 最后还款金额 */
	private Double lastRepayment;
	/** 应还期数 */
	private Integer duePeriods;
	/** 逾期金额 */
	private Double overdueMoney;
	/** 逾期开始日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date overdueDate;
	/** 逾期天数 */
	private Integer overdueDays;
	/** 逾期账龄 */
	private String overdueAge;
	/** 逾期期数 */
	private Integer overduePeriods;
	/** 逾期借款 */
	private Double overdueLoan;
	/** 逾期本金 */
	private Double overduePrincipal;
	/** 逾期本金余额 */
	private Double overduePrincipalBalance;
	/** 逾期管理费 */
	private Double overdueExpense;
	/** 逾期利息 */
	private Double overdueInterest;
	/** 逾期罚息 */
	private Double overduePenalty;
	/** 逾期复利 */
	private Double overdueCompound;
	/** 曾逾期次数 */
	private Integer onceOverduePeriods;
	/** 待还本金 */
	private Double stayPrincipal;
	/** 待还期数 */
	private Integer stayPeriods;
	/** 待还利息 */
	private BigDecimal stayInterest;
	/** 待还管理费 */
	private Double stayExpense;
	/** 账户余额 */
	private BigDecimal accountMoney;
	/** 账单日 */
	private Integer bill;
	/** 已还期数 */
	private Integer repaymentPeriods;
	/** 扣款银行代码 */
	private String debitBankCode;
	/** 扣款银行名称 */
	private String debitBankName;
	/** 扣款账号 */
	private Double debitAccount;
	/** 商品 */
	private String commodity;
	/** 案人房产类型 */
	private String caseHouseId;
	/** 职位 */
	private String casePosition;
	/** 职位级别 */
	private String casePositionLevel;
	/** 单位名称 */
	private String companyName;
	/** 单位地址 */
	private String companyAddress;
	/** 单位邮编 */
	private String companyZipcode;
	/** 单位号码 */
	private String companyPhone;
	/** 家庭地址 */
	private String familyAddress;
	/** 家庭邮编 */
	private String familyZipcode;
	/** 家庭号码 */
	private String familyPhone;
	/** 户籍地址 */
	private String domicile;
	/** 姓名 */
	private String linkManName;
	/** 证件号 */
	private String linkManCardNo;
	/** 关系 */
	private String linkManRelation;
	/** 手机 */
	private String linkManMobile;
	/** 家庭电话 */
	private String linkManFamilyPhone;
	/** 单位电话 */
	private String linkManUnitPhone;
	private String isVerify;
	/** 核销日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date verifyDate;
	/** 还款方式 */
	private String repaymentType;
	/** 协议编号 */
	private String protocolNo;
	/** 放款所属地 */
	private String loanArea;
	/** 催收区域名称 */
	private String areaName;
	/** 押品编号 */
	private String collateralNo;
	/** 押品类型 */
	private String collateralId;
	/** 押品名称 */
	private String collateralName;
	/** 押品地址 */
	private String collateralAddress;
	/** 建筑面积合计 */
	private String totalConstruc;
	/** 押品评估值 */
	private String collateralEvalua;
	/** 根额度编号 */
	private String quotaNo;
	/** 根额度主办人客户经理1 */
	private String quotaManager1;
	/** 客户经理电话 */
	private String managerPhone;
	/** 根额度协办人客户经理2 */
	private String quotaManager2;
	/** 大区名称 */
	private String regione;
	/** 分中心名称 */
	private String subCenter;
	/** 营业部名称 */
	private String salesDep;
	/** 账号 */
	private String accountNo;
	/** 标ID */
	private String markId;
	/** 用户名 */
	private String userName;
	/** 贷款合同号 */
	private String loanContract;
	/** 申请单号 */
	private String caseAppNo;
	/** 社保电脑号 */
	private String socialSecurityNo;
	/** 客户编号 */
	private String cusNo;
	/** 客户号 */
	private String userId;
	/** 档案号 */
	private String caseFileNo;
	/** 减免政策 */
	private String reliefPolicy;
	/** 下次跟进时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nextFollowTime;
	/** 外部减免 */
	private Double outDerate;
	/** 内部减免 */
	private Double inDerate;

	/** 案件颜色 */
	private String color;

	/** 备注1 */
	private String remark1;

	/** 备注2 */
	private String remark2;

	/** 客户简介 */
	private String cusIntroduc;
	/** 模板类型 */
	private String templateType;
	/** 开卡日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startCardDate;
	/** 停卡日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stopCardDate;
	/** 信用额度 */
	private BigDecimal creditLimit;

	/** 经销商 */
	private String dealer;
	/** 价格 */
	private BigDecimal price;
	/** 车牌号 */
	private String  liceNo;
	/** 品牌 */
	private String  brand;
	/** 型号 */
	private String number;
	/** 车架号 */
	private String  vinNo;
	/** 发动机号 */
	private String  engineNo;
	
	
	/** 生日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date caseBirthday;
	
	/** 社保卡号 */
	private String socialCardNo;
	
	/** 保单到期日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date policyExpire;

	/** 还款日 */
	private Integer repayDate;

	
	/** 案人部门 */
	private String caseDepartment;
	
	/** 催收小结 */
	private String collecRemark;
	/** 邮箱 */
	private String email;
	/** 币种 */
	private String currency;
	/** 原催收记录 */
	private String oldCollecRecord;
	/** 还款期限 */
	private String repaymentTerm;
	/** 催收分类 */
	private String collecType;
	/** 滞纳金 */
	private BigDecimal lateFee;
	
	/** 历史备注*/
	private String hisRemark;
	
	/** 最新欠款*/
	private BigDecimal caseLastMoney;
	/** 保单到期日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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

	public Date getCaseBirthday() {
		return caseBirthday;
	}

	public void setCaseBirthday(Date caseBirthday) {
		this.caseBirthday = caseBirthday;
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

	public Integer getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
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

	public String getOldCollecRecord() {
		return oldCollecRecord;
	}

	public void setOldCollecRecord(String oldCollecRecord) {
		this.oldCollecRecord = oldCollecRecord;
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

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getLiceNo() {
		return liceNo;
	}

	public void setLiceNo(String liceNo) {
		this.liceNo = liceNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getCusIntroduc() {
		return cusIntroduc;
	}

	public void setCusIntroduc(String cusIntroduc) {
		this.cusIntroduc = cusIntroduc;
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

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getOutDerate() {
		return outDerate;
	}

	public void setOutDerate(Double outDerate) {
		this.outDerate = outDerate;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public Double getInDerate() {
		return inDerate;
	}

	public void setInDerate(Double inDerate) {
		this.inDerate = inDerate;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
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

	public String getCaseNumId() {
		return caseNumId;
	}

	public void setCaseNumId(String caseNumId) {
		this.caseNumId = caseNumId;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCaseCard() {
		return caseCard;
	}

	public void setCaseCard(String caseCard) {
		this.caseCard = caseCard;
	}

	public BigDecimal getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(BigDecimal caseMoney) {
		this.caseMoney = caseMoney;
	}

	public BigDecimal getLatestMoney() {
		return latestMoney;
	}

	public void setLatestMoney(BigDecimal latestMoney) {
		this.latestMoney = latestMoney;
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

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}

	public String getEntrustRate() {
		return entrustRate;
	}

	public void setEntrustRate(String entrustRate) {
		this.entrustRate = entrustRate;
	}

	public Double getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(Double agentRate) {
		this.agentRate = agentRate;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public Double getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(Double loanMoney) {
		this.loanMoney = loanMoney;
	}

	public Integer getRepayDay() {
		return repayDay;
	}

	public void setRepayDay(Integer repayDate) {
		this.repayDay = repayDate;
	}

	public Date getInitialRepay() {
		return initialRepay;
	}

	public void setInitialRepay(Date initialRepay) {
		this.initialRepay = initialRepay;
	}

	public Double getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(Double surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(Double loanRate) {
		this.loanRate = loanRate;
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

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
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

	public Double getActualLoanMoney() {
		return actualLoanMoney;
	}

	public void setActualLoanMoney(Double actualLoanMoney) {
		this.actualLoanMoney = actualLoanMoney;
	}

	public Double getBreach() {
		return breach;
	}

	public void setBreach(Double breach) {
		this.breach = breach;
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

	public Integer getDuePeriods() {
		return duePeriods;
	}

	public void setDuePeriods(Integer duePeriods) {
		this.duePeriods = duePeriods;
	}

	public Double getOverdueMoney() {
		return overdueMoney;
	}

	public void setOverdueMoney(Double overdueMoney) {
		this.overdueMoney = overdueMoney;
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

	public String getOverdueAge() {
		return overdueAge;
	}

	public void setOverdueAge(String overdueAge) {
		this.overdueAge = overdueAge;
	}

	public Integer getOverduePeriods() {
		return overduePeriods;
	}

	public void setOverduePeriods(Integer overduePeriods) {
		this.overduePeriods = overduePeriods;
	}

	public Double getOverdueLoan() {
		return overdueLoan;
	}

	public void setOverdueLoan(Double overdueLoan) {
		this.overdueLoan = overdueLoan;
	}

	public Double getOverduePrincipal() {
		return overduePrincipal;
	}

	public void setOverduePrincipal(Double overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}

	public Double getOverduePrincipalBalance() {
		return overduePrincipalBalance;
	}

	public void setOverduePrincipalBalance(Double overduePrincipalBalance) {
		this.overduePrincipalBalance = overduePrincipalBalance;
	}

	public Double getOverdueExpense() {
		return overdueExpense;
	}

	public void setOverdueExpense(Double overdueExpense) {
		this.overdueExpense = overdueExpense;
	}

	public Double getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(Double overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public Double getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(Double overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public Double getOverdueCompound() {
		return overdueCompound;
	}

	public void setOverdueCompound(Double overdueCompound) {
		this.overdueCompound = overdueCompound;
	}

	public Integer getOnceOverduePeriods() {
		return onceOverduePeriods;
	}

	public void setOnceOverduePeriods(Integer onceOverduePeriods) {
		this.onceOverduePeriods = onceOverduePeriods;
	}

	public Double getStayPrincipal() {
		return stayPrincipal;
	}

	public void setStayPrincipal(Double stayPrincipal) {
		this.stayPrincipal = stayPrincipal;
	}

	public Integer getStayPeriods() {
		return stayPeriods;
	}

	public void setStayPeriods(Integer stayPeriods) {
		this.stayPeriods = stayPeriods;
	}

	public BigDecimal getStayInterest() {
		return stayInterest;
	}

	public void setStayInterest(BigDecimal stayInterest) {
		this.stayInterest = stayInterest;
	}

	public Double getStayExpense() {
		return stayExpense;
	}

	public void setStayExpense(Double stayExpense) {
		this.stayExpense = stayExpense;
	}

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
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

	public Double getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Double debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCaseHouseId() {
		return caseHouseId;
	}

	public void setCaseHouseId(String caseHouseId) {
		this.caseHouseId = caseHouseId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
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

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManCardNo() {
		return linkManCardNo;
	}

	public void setLinkManCardNo(String linkManCardNo) {
		this.linkManCardNo = linkManCardNo;
	}

	public String getLinkManRelation() {
		return linkManRelation;
	}

	public void setLinkManRelation(String linkManRelation) {
		this.linkManRelation = linkManRelation;
	}

	public String getLinkManMobile() {
		return linkManMobile;
	}

	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}

	public String getLinkManFamilyPhone() {
		return linkManFamilyPhone;
	}

	public void setLinkManFamilyPhone(String linkManFamilyPhone) {
		this.linkManFamilyPhone = linkManFamilyPhone;
	}

	public String getLinkManUnitPhone() {
		return linkManUnitPhone;
	}

	public void setLinkManUnitPhone(String linkManUnitPhone) {
		this.linkManUnitPhone = linkManUnitPhone;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getTotalConstruc() {
		return totalConstruc;
	}

	public void setTotalConstruc(String totalConstruc) {
		this.totalConstruc = totalConstruc;
	}

	public String getCollateralEvalua() {
		return collateralEvalua;
	}

	public void setCollateralEvalua(String collateralEvalua) {
		this.collateralEvalua = collateralEvalua;
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

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getQuotaManager2() {
		return quotaManager2;
	}

	public void setQuotaManager2(String quotaManager2) {
		this.quotaManager2 = quotaManager2;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getLoanContract() {
		return loanContract;
	}

	public void setLoanContract(String loanContract) {
		this.loanContract = loanContract;
	}

	public String getCaseAppNo() {
		return caseAppNo;
	}

	public void setCaseAppNo(String caseAppNo) {
		this.caseAppNo = caseAppNo;
	}

	public String getSocialSecurityNo() {
		return socialSecurityNo;
	}

	public void setSocialSecurityNo(String socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
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

	public String getCaseFileNo() {
		return caseFileNo;
	}

	public void setCaseFileNo(String caseFileNo) {
		this.caseFileNo = caseFileNo;
	}

	public String getReliefPolicy() {
		return reliefPolicy;
	}

	public void setReliefPolicy(String reliefPolicy) {
		this.reliefPolicy = reliefPolicy;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getNextFollowTime() {
		return nextFollowTime;
	}

	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}

	public BigDecimal getPaidNum() {
		return paidNum;
	}

	public void setPaidNum(BigDecimal paidNum) {
		this.paidNum = paidNum;
	}

	public BigDecimal getPtpMoney() {
		return ptpMoney;
	}

	public void setPtpMoney(BigDecimal ptpMoney) {
		this.ptpMoney = ptpMoney;
	}

	public BigDecimal getCpMoney() {
		return cpMoney;
	}

	public void setCpMoney(BigDecimal cpMoney) {
		this.cpMoney = cpMoney;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getLastPhone() {
		return lastPhone;
	}

	public void setLastPhone(Date lastPhone) {
		this.lastPhone = lastPhone;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}
