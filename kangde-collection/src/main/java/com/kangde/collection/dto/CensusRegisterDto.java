package com.kangde.collection.dto;

import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.collection.model.AreaModel;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.CasePaid;
import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;

/**
 * 用于待处理协催---导入，xml解析
 * @author zhangyj
 * @date 2016年7月21日
 *
 */
@SuppressWarnings("serial")
public class CensusRegisterDto extends CensusRegisterModel{
	
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
