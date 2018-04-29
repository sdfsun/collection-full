package com.kangde.collection.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.service.VisitRecordMessageReminderService;

/**
 * 
 * @Description: 外放消息提醒类
 * @author lidengwen
 * @date 2016年8月13日 下午4:33:11
 *
 */
@Service
public class VisitRecordMessageReminderServiceImpl implements VisitRecordMessageReminderService {

	@Autowired
	private MessageReminderService messageReminderService;
	@Autowired
	private CaseMapper caseMapper;
	@Override
	public void saveMessageMinders(String[] visitrecordIds, String message) {
		List<CaseModel> caselist = caseMapper.findcaseByVisitrecordIds(visitrecordIds);
		if (CollectionUtils.isNotEmpty(caselist)) {
			for (CaseModel caseModel : caselist) {
				messageReminderService.saveReminder("案件["+caseModel.getCaseCode()+"]"+message, 0, caseModel.getCaseAssignedId(), "/collection/casedetail?caseId="+caseModel.getId(),"案件详情");
			}
		}
	}
	
}
