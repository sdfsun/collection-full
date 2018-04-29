package com.kangde.sys.dto;

import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustLinkman;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.model.EntrustProduct;
import com.kangde.sys.model.EntrustVisit;

/**
 * 营业 Dto
 * 
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class EntrustDto extends EntrustProduct{
	
	/** 委托方表*/
	private EntrustModel entrustModel;
	/** 案源地表*/
	private EntrustCaseSource entrustCaseSource;
	/** 联系人表*/
	private EntrustLinkman entrustLinkman;
	/** 拜访记录表*/
	private EntrustVisit entrustVisit;
	
	
	public EntrustModel getEntrustModel() {
		return entrustModel;
	}
	public void setEntrustModel(EntrustModel entrustModel) {
		this.entrustModel = entrustModel;
	}
	public EntrustCaseSource getEntrustCaseSource() {
		return entrustCaseSource;
	}
	public void setEntrustCaseSource(EntrustCaseSource entrustCaseSource) {
		this.entrustCaseSource = entrustCaseSource;
	}
	public EntrustLinkman getEntrustLinkman() {
		return entrustLinkman;
	}
	public void setEntrustLinkman(EntrustLinkman entrustLinkman) {
		this.entrustLinkman = entrustLinkman;
	}
	public EntrustVisit getEntrustVisit() {
		return entrustVisit;
	}
	public void setEntrustVisit(EntrustVisit entrustVisit) {
		this.entrustVisit = entrustVisit;
	}

}
