package com.kangde.collection.dto;

import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.TelecomModel;

/**
 * 用于案件审批，xml解析
 * @author wcy
 * @date 2016年5月25日16:53:37
 *
 */
@SuppressWarnings("serial")
public class TelecomDto extends TelecomModel{
	
	/**
	 * 案件信息表
	 */
	private CaseModel caseModel;
	/**
	 * 协催申请表
	 */
	private CaseApplyModel caseApply;
	
	
	public CaseModel getCaseModel() {
		return caseModel;
	}
	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}
	public CaseApplyModel getCaseApply() {
		return caseApply;
	}
	public void setCaseApply(CaseApplyModel caseApply) {
		this.caseApply = caseApply;
	}

	
}
