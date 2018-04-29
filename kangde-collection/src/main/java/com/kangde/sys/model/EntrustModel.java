package com.kangde.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;
/**
 * 委托方Model
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class EntrustModel extends BizModel<String> {
	
	/** 委拖方名称 */
    private String name;
    /** 委拖方名称 */
    private String ename;
    /** 委托方简码 */
    private String code;
    /** 合作日期 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date cooperateDate;
    /** 委托方联系地址 */
    private String address;
    /** 委托方客务热线 */
    private String serviceHotline;
    /** 委托方对接人 */
    private String abutmentPerson;
    /** 对接邮箱 */
    private String abutmentEmail;
    /** 对接电话/传真 */
    private String abutmentPhone;
    /** 委案频率 1周 2月 3季 */
    private String caseFrequency;
    /** 有效还款期段 */
    private String effecRepayPeriod;
    /** 风控名义 */
    private String collecNominal;
    /** 风控期限 */
    private String collecTerm;
    /** 留案要求 */
    private String stayRequire;
    /** 退案要求 */
    private String backRequire;
    /** 报告周期 1周 2月 3季 */
    private String reportPeriod;
    /** 结算周期 1周 2月 3季 */
    private String settlementPeriod;
    /** 还款方式 */
    private String repaymentMethod;
    /** 折扣减免 */
    private String discountReduction;
    /** 分期还款要求 */
    private String periodRepaymentRequire;
    /** 如何销卡/销户 */
    private String cancelAccount;
    /** 源资取调要求 */
    private String resourceRetrieveRequire;
    /** 催记提交要求 */
    private String collecSubmitRequire;
    /** 投诉处理要求 */
    private String complaintHandleRequire;
    /** 录音提交要求 */
    private String tapeSubmitRequire;
    /** 资料销毁要求 */
    private String dataDestrucRequire;
    /** 信函回收要求 */
    private String letterRecoveryRequire;
    /** 报告/报表要求 */
    private String reportRequire;
    /** 客户特别指示 */
    private String cusSpecialInstruc;
    /** 案件类型 */
    private String caseTypeId;
    /** 产品名称 */
    private String productName;
    
    /** 合同名称 */
    private String contractName;
    /** 备注 */
    private String remark;
    private String createEmpName;
   
    private String en_name;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCooperateDate() {
		return cooperateDate;
	}
	public void setCooperateDate(Date cooperateDate) {
		this.cooperateDate = cooperateDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getServiceHotline() {
		return serviceHotline;
	}
	public void setServiceHotline(String serviceHotline) {
		this.serviceHotline = serviceHotline;
	}
	public String getAbutmentPerson() {
		return abutmentPerson;
	}
	public void setAbutmentPerson(String abutmentPerson) {
		this.abutmentPerson = abutmentPerson;
	}
	public String getAbutmentEmail() {
		return abutmentEmail;
	}
	public void setAbutmentEmail(String abutmentEmail) {
		this.abutmentEmail = abutmentEmail;
	}
	public String getAbutmentPhone() {
		return abutmentPhone;
	}
	public void setAbutmentPhone(String abutmentPhone) {
		this.abutmentPhone = abutmentPhone;
	}
	public String getCaseFrequency() {
		return caseFrequency;
	}
	public void setCaseFrequency(String caseFrequency) {
		this.caseFrequency = caseFrequency;
	}
	public String getEffecRepayPeriod() {
		return effecRepayPeriod;
	}
	public void setEffecRepayPeriod(String effecRepayPeriod) {
		this.effecRepayPeriod = effecRepayPeriod;
	}
	public String getCollecNominal() {
		return collecNominal;
	}
	public void setCollecNominal(String collecNominal) {
		this.collecNominal = collecNominal;
	}
	public String getCollecTerm() {
		return collecTerm;
	}
	public void setCollecTerm(String collecTerm) {
		this.collecTerm = collecTerm;
	}
	public String getStayRequire() {
		return stayRequire;
	}
	public void setStayRequire(String stayRequire) {
		this.stayRequire = stayRequire;
	}
	public String getBackRequire() {
		return backRequire;
	}
	public void setBackRequire(String backRequire) {
		this.backRequire = backRequire;
	}
	public String getReportPeriod() {
		return reportPeriod;
	}
	public void setReportPeriod(String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}
	public String getSettlementPeriod() {
		return settlementPeriod;
	}
	public void setSettlementPeriod(String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}
	public String getRepaymentMethod() {
		return repaymentMethod;
	}
	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	public String getDiscountReduction() {
		return discountReduction;
	}
	public void setDiscountReduction(String discountReduction) {
		this.discountReduction = discountReduction;
	}
	public String getPeriodRepaymentRequire() {
		return periodRepaymentRequire;
	}
	public void setPeriodRepaymentRequire(String periodRepaymentRequire) {
		this.periodRepaymentRequire = periodRepaymentRequire;
	}
	public String getCancelAccount() {
		return cancelAccount;
	}
	public void setCancelAccount(String cancelAccount) {
		this.cancelAccount = cancelAccount;
	}
	public String getResourceRetrieveRequire() {
		return resourceRetrieveRequire;
	}
	public void setResourceRetrieveRequire(String resourceRetrieveRequire) {
		this.resourceRetrieveRequire = resourceRetrieveRequire;
	}
	public String getCollecSubmitRequire() {
		return collecSubmitRequire;
	}
	public void setCollecSubmitRequire(String collecSubmitRequire) {
		this.collecSubmitRequire = collecSubmitRequire;
	}
	public String getComplaintHandleRequire() {
		return complaintHandleRequire;
	}
	public void setComplaintHandleRequire(String complaintHandleRequire) {
		this.complaintHandleRequire = complaintHandleRequire;
	}
	public String getTapeSubmitRequire() {
		return tapeSubmitRequire;
	}
	public void setTapeSubmitRequire(String tapeSubmitRequire) {
		this.tapeSubmitRequire = tapeSubmitRequire;
	}
	public String getDataDestrucRequire() {
		return dataDestrucRequire;
	}
	public void setDataDestrucRequire(String dataDestrucRequire) {
		this.dataDestrucRequire = dataDestrucRequire;
	}
	public String getLetterRecoveryRequire() {
		return letterRecoveryRequire;
	}
	public void setLetterRecoveryRequire(String letterRecoveryRequire) {
		this.letterRecoveryRequire = letterRecoveryRequire;
	}
	public String getReportRequire() {
		return reportRequire;
	}
	public void setReportRequire(String reportRequire) {
		this.reportRequire = reportRequire;
	}
	public String getCusSpecialInstruc() {
		return cusSpecialInstruc;
	}
	public void setCusSpecialInstruc(String cusSpecialInstruc) {
		this.cusSpecialInstruc = cusSpecialInstruc;
	}
	public String getCaseTypeId() {
		return caseTypeId;
	}
	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getCreateEmpName() {
		return createEmpName;
	}
	public void setCreateEmpName(String createEmpName) {
		this.createEmpName = createEmpName;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

    
}