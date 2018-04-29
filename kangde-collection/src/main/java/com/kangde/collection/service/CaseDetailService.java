package com.kangde.collection.service;

import java.util.Date;

import com.kangde.collection.dto.CaseDetailDto;

/**
 * 案件详细信息Service
 * 
 * @author wcy
 * @date 2016年5月30日14:17:22
 */
public interface CaseDetailService {

	public CaseDetailDto queryDetailByCaseId(String id);

	public void saveRecord(String contact, Date createTime, String linkmanId, String prCat, String typeId, String collecStateId,
			Date ptpTime, Double ptpMoney, String content, Date nextFollowTime, String caseId);

}
