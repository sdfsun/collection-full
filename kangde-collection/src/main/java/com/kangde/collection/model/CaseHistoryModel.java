package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BaseModel;
/**
 * 案件历史表Model
 * @date 2016年6月6日09:20:38 
 * @author wcy
 */
@SuppressWarnings("serial")
public class CaseHistoryModel extends BaseModel<String>{

	    private String caseId;

	    private String caseCode;

	    private Integer state;

	    private String collecStateId;

	    private Integer visitStateId;

	    private String batchId;

	    private Date caseDate;

	    private Date caseBackdate;

	    private Date endDate;

	    private Integer version;

	    private String cusNo;

	    private String userid;

	    private String accountNo;

	    private String caseAppNo;

	    private String caseFileNo;

	    private String caseName;

	    private String caseSex;

	    private String caseIsMarriage;

	    private Date caseBirthday;

	    private Integer caseAge;

	    private String provinceId;

	    private String provinceName;

	    private String cityId;

	    private String cityName;

	    private String districtId;

	    private String districtName;

	    private String areaId;

	    private String areaName;

	    private String bankAccount;

	    private String caseHouseId;

	    private String caseCard;

	    private String caseNum;

	    private String caseNumId;

	    private String casePosition;

	    private String caseDepartment;

	    private String casePositionLevel;

	    private String accountName;

	    private String lendInstitution;

	    private Double principal;

	    private Double surplusPrincipal;

	    private Double breach;

	    private Double caseMoney;

	    private Double caseLastMoney;

	    private Date caseLastDate;

	    private Double accountMoney;

	    private String loanContract;

	    private Double loanMoney;

	    private Double loanRate;

	    private Date loanStartdate;

	    private Date loanEnddate;

	    private Double contractMoney;

	    private Double overdueMoney;

	    private Double overduePrincipal;

	    private Double overdueExpense;

	    private String overdueAge;

	    private Double overduePenalty;

	    private Double overdueInterest;

	    private Integer overdueDays;

	    private Date overdueDate;

	    private Integer overduePeriods;

	    private Integer onceOverduePeriods;

	    private Double overdueLoan;

	    private Double overduePrincipalBalance;

	    private Double overdueCompound;

	    private String socialSecurityNo;

	    private String socialCardNo;

	    private Double agentRate;

	    private Double entrustRate;

	    private String creditId;

	    private Integer stayPeriods;

	    private Double monthRepayment;

	    private Double lastRepayment;

	    private Double stayPrincipal;

	    private Double stayExpense;

	    private Double stayInterest;

	    private Integer bill;

	    private Integer repaymentPeriods;

	    private String commodity;

	    private String companyName;

	    private String companyPhone;

	    private String companyAddress;

	    private String companyZipcode;

	    private String mobile1;

	    private String mobile2;

	    private String familyPhone;

	    private String familyAddress;

	    private String familyZipcode;

	    private String domicile;

	    private String markId;

	    private String userName;

	    private String isVerify;

	    private Date verifyDate;

	    private String repaymentType;

	    private String protocolNo;

	    private String loanArea;

	    private Double actualLoanMoney;

	    private Double interestMoney;

	    private String penaltyReference;

	    private String compoundInterestReference;

	    private String quotaNo;

	    private String quotaManager1;

	    private String quotaManager2;

	    private String managerPhone;

	    private String collateralNo;

	    private String collateralId;

	    private String collateralName;

	    private String collateralAddress;

	    private String collateralEvalua;

	    private Integer repayDate;

	    private String totalConstruc;

	    private String regione;

	    private String subCenter;

	    private String salesDep;

	    private Double consultFee;

	    private Double auditFee;

	    private Double serviceFee;

	    private Double adviserFee;

	    private Double feeTotal;

	    private Date initialRepay;

	    private Integer duePeriods;

	    private Double penaltyDays;

	    private String remainHistory;

	    private String debitBankCode;

	    private String debitBankName;

	    private String debitAccount;

	    private String reliefPolicy;

	    private String caseAssignedId;

	    private String caseAssignId;

	    private String caseAssignName;

	    private Date caseAssignTime;

	    private String createEmpId;

	    private String modifyEmpId;

	    private Date modifyTime;

	    private Date createTime;

	    private Integer status;

	    private String orgId;

	    private String orgName;

	    private Date nextFollowTime;

	    private String nextFollowState;

	    private Date lastPhoneTime;

	    private String isUpdate;

	    private String handle;

	    private Double inDerate;

	    private Double outDerate;

	    private Integer color;

	    private String caseWarn;

	    private Date startCardDate;

	    private Date stopCardDate;

	    private Double creditLimit;

	    private Date policyExpire;

	    private String collecType;

	    private Double lateFee;

	    private String domicileZipcode;

	    private String billAddress;

	    private String billZipcode;

	    private String email;

	    private String currency;

	    private String repaymentTerm;

	    private String entrustId;

	    private Date operateTime;
	    
	    private String oldCollecRecord;

	    private String remark1;

	    private String remark2;

	    private String collecRemark;

	    private String cusIntroduc;

	    private String hisRemark;

	    
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

		public String getCollecRemark() {
			return collecRemark;
		}

		public void setCollecRemark(String collecRemark) {
			this.collecRemark = collecRemark;
		}

		public String getCusIntroduc() {
			return cusIntroduc;
		}

		public void setCusIntroduc(String cusIntroduc) {
			this.cusIntroduc = cusIntroduc;
		}

		public String getHisRemark() {
			return hisRemark;
		}

		public void setHisRemark(String hisRemark) {
			this.hisRemark = hisRemark;
		}

		public String getOldCollecRecord() {
			return oldCollecRecord;
		}

		public void setOldCollecRecord(String oldCollecRecord) {
			this.oldCollecRecord = oldCollecRecord;
		}

		public String getCaseId() {
			return caseId;
		}

		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}

		public String getCaseCode() {
			return caseCode;
		}

		public void setCaseCode(String caseCode) {
			this.caseCode = caseCode;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public String getCollecStateId() {
			return collecStateId;
		}

		public void setCollecStateId(String collecStateId) {
			this.collecStateId = collecStateId;
		}

		public Integer getVisitStateId() {
			return visitStateId;
		}

		public void setVisitStateId(Integer visitStateId) {
			this.visitStateId = visitStateId;
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

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public String getCusNo() {
			return cusNo;
		}

		public void setCusNo(String cusNo) {
			this.cusNo = cusNo;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
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

		public String getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}

		public String getProvinceName() {
			return provinceName;
		}

		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}

		public String getCityId() {
			return cityId;
		}

		public void setCityId(String cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getDistrictId() {
			return districtId;
		}

		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}

		public String getDistrictName() {
			return districtName;
		}

		public void setDistrictName(String districtName) {
			this.districtName = districtName;
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

		public String getCaseDepartment() {
			return caseDepartment;
		}

		public void setCaseDepartment(String caseDepartment) {
			this.caseDepartment = caseDepartment;
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

		public Double getBreach() {
			return breach;
		}

		public void setBreach(Double breach) {
			this.breach = breach;
		}

		public Double getCaseMoney() {
			return caseMoney;
		}

		public void setCaseMoney(Double caseMoney) {
			this.caseMoney = caseMoney;
		}

		public Double getCaseLastMoney() {
			return caseLastMoney;
		}

		public void setCaseLastMoney(Double caseLastMoney) {
			this.caseLastMoney = caseLastMoney;
		}

		public Date getCaseLastDate() {
			return caseLastDate;
		}

		public void setCaseLastDate(Date caseLastDate) {
			this.caseLastDate = caseLastDate;
		}

		public Double getAccountMoney() {
			return accountMoney;
		}

		public void setAccountMoney(Double accountMoney) {
			this.accountMoney = accountMoney;
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

		public String getSocialCardNo() {
			return socialCardNo;
		}

		public void setSocialCardNo(String socialCardNo) {
			this.socialCardNo = socialCardNo;
		}

		public Double getAgentRate() {
			return agentRate;
		}

		public void setAgentRate(Double agentRate) {
			this.agentRate = agentRate;
		}

		public Double getEntrustRate() {
			return entrustRate;
		}

		public void setEntrustRate(Double entrustRate) {
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

		public Double getStayInterest() {
			return stayInterest;
		}

		public void setStayInterest(Double stayInterest) {
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

		public String getIsVerify() {
			return isVerify;
		}

		public void setIsVerify(String isVerify) {
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

		public String getDebitAccount() {
			return debitAccount;
		}

		public void setDebitAccount(String debitAccount) {
			this.debitAccount = debitAccount;
		}

		public String getReliefPolicy() {
			return reliefPolicy;
		}

		public void setReliefPolicy(String reliefPolicy) {
			this.reliefPolicy = reliefPolicy;
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

		public String getCreateEmpId() {
			return createEmpId;
		}

		public void setCreateEmpId(String createEmpId) {
			this.createEmpId = createEmpId;
		}

		public String getModifyEmpId() {
			return modifyEmpId;
		}

		public void setModifyEmpId(String modifyEmpId) {
			this.modifyEmpId = modifyEmpId;
		}

		public Date getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
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

		public Date getLastPhoneTime() {
			return lastPhoneTime;
		}

		public void setLastPhoneTime(Date lastPhoneTime) {
			this.lastPhoneTime = lastPhoneTime;
		}

		public String getIsUpdate() {
			return isUpdate;
		}

		public void setIsUpdate(String isUpdate) {
			this.isUpdate = isUpdate;
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

		public Integer getColor() {
			return color;
		}

		public void setColor(Integer color) {
			this.color = color;
		}

		public String getCaseWarn() {
			return caseWarn;
		}

		public void setCaseWarn(String caseWarn) {
			this.caseWarn = caseWarn;
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

		public Date getPolicyExpire() {
			return policyExpire;
		}

		public void setPolicyExpire(Date policyExpire) {
			this.policyExpire = policyExpire;
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

		public String getEntrustId() {
			return entrustId;
		}

		public void setEntrustId(String entrustId) {
			this.entrustId = entrustId;
		}

		public Date getOperateTime() {
			return operateTime;
		}

		public void setOperateTime(Date operateTime) {
			this.operateTime = operateTime;
		}

}