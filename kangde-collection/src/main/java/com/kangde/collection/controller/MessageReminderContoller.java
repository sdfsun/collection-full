package com.kangde.collection.controller;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.MessageReminderModel;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.CasePaidService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.HelpMeService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.service.TargetAchieveService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 消息提醒Controller

 */
@Controller
@RequestMapping("collection/messageReminder")
public class MessageReminderContoller extends RestfulUrlController<MessageReminderModel, String> {
	
	@Autowired
	private MessageReminderService messageReminderService;
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private TargetAchieveService targetAchieveService;
	@Autowired
	private HelpMeService helpMeService;
	@Autowired
	private CasePaidService casePaidService;
	
	
	@Override
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.POST)
	public SearchResult<MessageReminderModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		SearchResult<MessageReminderModel> queryForPage = super.queryForPage(condition);
		return queryForPage;
	}

	@ResponseBody
	@RequestMapping(value = "readMessageReminder", method = RequestMethod.POST)
	public AjaxResult readMessageReminder(String id) throws AddressException {
		messageReminderService.readMessageReminderService(id);
		return AjaxResult.success(getSuccessMessage());
	}
	
	
	@Override
	public ModelAndView pageIndex() {
		ModelAndView index = super.index();
		int tomorrowVisitCount = visitRecordService.queryTomorrowVisitCount();
		index.addObject("tomorrowVisitCount", tomorrowVisitCount);
		List<CaseModel> nextList=caseService.queryCaseTotoFollow();
		index.addObject("caseToFollow", nextList);
		int queryTargetArchive = targetAchieveService.queryTargetArchive();
		index.addObject("targetArchive", queryTargetArchive);
		List<VisitRecordDto> caseToUploadReport = visitRecordService.queryCaseToUploadReport();
		if(CollectionUtils.isNotEmpty(caseToUploadReport)){
			List<CaseModel> caseList=Lists.newArrayList();
			for (VisitRecordDto visitRecordDto : caseToUploadReport) {
				caseList.add(visitRecordDto.getCaseModel());
			}
			index.addObject("caseToUploadReport", caseList);
		}
		int divisionCaseCount=caseService.queryDivisionCaseCount();
		index.addObject("divisionCase", divisionCaseCount);
		
		int queryToApproveORToAppointCount= visitRecordService.queryToApproveORToAppointCount(0);
		index.addObject("visitreportToApprove", queryToApproveORToAppointCount);
		int visitreportToAppointCount= visitRecordService.queryToApproveORToAppointCount(2);
		index.addObject("visitreportToAppoint", visitreportToAppointCount);
		
		int letterToApproveCount = caseApplyService.queryLetterCount(-2);
		index.addObject("letterToApprove", letterToApproveCount);
		
		int letterToPostCount= caseApplyService.queryLetterCount(0);
		index.addObject("letterToPost", letterToPostCount);
		
		int xiecuiTasklistCount=caseApplyService.queryXiecuiTasklistCount();
		index.addObject("xiecuiTasklistCount", xiecuiTasklistCount);
		
		int chaziToApproveCount=helpMeService.queryChaziToApproveCount();
		index.addObject("chaziToApproveCount", chaziToApproveCount);
		int querydealCount=helpMeService.querydealCount();
		index.addObject("querydealCount", querydealCount);
		int toConfirmPayCount=casePaidService.queryToConfirmPayCount();
		index.addObject("toConfirmPayCount", toConfirmPayCount);
		return index;
	}

	@Override
	protected String getBaseName() {
		return "collection/messagereminder/";
	}

}
