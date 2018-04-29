package com.kangde.sys.dto;

import com.kangde.sys.model.EntrustVisit;

/**
 * 拜访Dto
 * 
 * @author wcy
 * @date 2016年11月9日10:31:40
 *
 */
@SuppressWarnings("serial")
public class EntrustVisitDto extends EntrustVisit{
	
	private String entrustName;
	private String caseSourceName;
	private String caseTypeName;
	private String handleName;
	private String entrustId;
	public String getEntrustName() {
		return entrustName;
	}
	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
	public String getCaseSourceName() {
		return caseSourceName;
	}
	public void setCaseSourceName(String caseSourceName) {
		this.caseSourceName = caseSourceName;
	}
	public String getCaseTypeName() {
		return caseTypeName;
	}
	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}
	public String getHandleName() {
		return handleName;
	}
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	
	
	

}
