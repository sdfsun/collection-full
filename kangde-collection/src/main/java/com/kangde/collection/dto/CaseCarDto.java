package com.kangde.collection.dto;

import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CaseCarLoanModel;

/**
 * 案件模板Excel Dto
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class CaseCarDto extends CaseModel {
	/** 案件id*/
	private String caseId;
	/** 经销商*/
	private String dealer;
	/** 价格*/
	private String price;
	/** 车牌号*/
	private String liceNo;
	/** 品牌*/
	private String brand;
	/** 型号*/
	private String number;
	/** 车架号*/
	private String vinNo;
	/** 发动机号*/
	private String engineNo;
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	
	
	
	
}
