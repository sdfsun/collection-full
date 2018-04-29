package com.kangde.collection.controller;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.collection.dto.StatisticsEmployeeDto;
import com.kangde.collection.dto.TodoDto;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.MessageReminderModel;
import com.kangde.collection.model.NoticeModel;
import com.kangde.collection.model.TargetAchieve;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.CasePaidService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.HelpMeService;
import com.kangde.collection.service.MessageReminderService;
import com.kangde.collection.service.NoticeService;
import com.kangde.collection.service.StatisticsEmployeeService;
import com.kangde.collection.service.TargetAchieveService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.security.util.SecurityUtil;

@Controller
@RequestMapping("collection/targetAchieve")
public class TargetAchieveController extends RestfulUrlController<TargetAchieve,String> {
	@Autowired
	private TargetAchieveService targetAchieveService;
	@Autowired
	private StatisticsEmployeeService statisticsService;
	@Autowired
	private MessageReminderService messageReminderService;
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private HelpMeService helpMeService;
	@Autowired
	private CasePaidService casePaidService;
	@Autowired
	private NoticeService noticeService;
	@Override
	public SearchResult<TargetAchieve> queryForPage() {
		ParamCondition condition = parseCondition("*");
		return super.queryForPage(condition);
	}
	
	@Override
	public AjaxResult update(TargetAchieve model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public AjaxResult create(String id, 
							 String empName, 
							 Double achieve) {
		// '地址状态 0未知 1有效 2无效
		//model.setStatus(1);
		TargetAchieve targetAchieve = targetAchieveService.findById(id);
		targetAchieve.setAchieve(achieve);
		targetAchieveService.update(targetAchieve);
		return AjaxResult.success(getSuccessMessage());
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		//EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String userId = SecurityUtil.getCurrentUser().getId();
		if(userId.equals("root")){
			SecurityUtil.getCurrentUser().setCpAchieve("0.00");
			SecurityUtil.getCurrentUser().setPaidAchieve("0.00");
			SecurityUtil.getCurrentUser().setRate("0.00%");
			SecurityUtil.getCurrentUser().setDistance("0.00");
			SecurityUtil.getCurrentUser().setAchieve("0.00");
		}
		int year = DateUtil.getYear(new Date());
		int month = DateUtil.getMonth(new Date());
		ParamCondition condition = parseCondition("*");
		
		String firstDayOfMonth=DateUtil.thisDate().substring(0, 8)+"01";
		
		String lastDayOfMonth = DateUtil.thisDate().substring(0, 8)+DateUtil.getLastDayOfMonth(new Date());
		condition.put("emId", userId);
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		condition.put("normalDate", firstDayOfMonth);
		condition.put("normalDate1", lastDayOfMonth);
		
		SearchResult<StatisticsEmployeeDto> employee = statisticsService.queryEmployee(condition);
		List<StatisticsEmployeeDto> rows = employee.getRows();
		NumberFormat nf = new DecimalFormat("#,##0.00");
		for (StatisticsEmployeeDto employeeDto : rows) {
			Double cp_back_paid = employeeDto.getCp_back_paid();//未确认业绩
			Double back_paid = employeeDto.getBack_paid();//已确认业绩
			
			SecurityUtil.getCurrentUser().setCpAchieve(nf.format(cp_back_paid));
			
			SecurityUtil.getCurrentUser().setPaidAchieve(nf.format(back_paid));
		
			TargetAchieve targetAchieve = targetAchieveService.findByEmpId(userId,year,month);
			if(targetAchieve!=null){
				Double achieve = targetAchieve.getAchieve();
				if(achieve!=null){
					if (achieve==0){
						Double rate=0.00;
						String achRate=nf.format(rate)+"%";
						SecurityUtil.getCurrentUser().setRate(achRate);
					}else{
						Double rate = back_paid*100/achieve;
						String achRate=nf.format(rate)+"%";
						SecurityUtil.getCurrentUser().setRate(achRate);
					}
					Double distance = achieve-back_paid;
					SecurityUtil.getCurrentUser().setDistance(nf.format(distance));
						
					SecurityUtil.getCurrentUser().setAchieve(nf.format(achieve));
				}else{
					SecurityUtil.getCurrentUser().setDistance("0.00");
					SecurityUtil.getCurrentUser().setRate("0.00%");
					SecurityUtil.getCurrentUser().setAchieve("0.00");
				}
			}else{
				SecurityUtil.getCurrentUser().setAchieve("0.00");
				SecurityUtil.getCurrentUser().setDistance("0.00");
				SecurityUtil.getCurrentUser().setRate("0.00%");
			}
		}
		ModelAndView view = createBaseView("add");
		
		List<NoticeModel> noticeList = noticeService.queryTop();
		view.addObject("noticeList",noticeList);
		
		
		String userName = SecurityUtil.getCurrentUser().getUserName();
		if("超级管理员".equals(userName)){
			return view;
		}
		List<TodoDto> todoList=Lists.newArrayList();	//外访员
		if(SecurityUtil.hasRole("wfy")){
			int tomorrowVisitCount = visitRecordService.queryTomorrowVisitCount();
			todoList.add(new TodoDto("拟定于明日进行外访总数",tomorrowVisitCount,"/collection/visitrecordappointed","已排程外访"));
			int todayVisitCount = visitRecordService.queryTodayVisitCount();
			todoList.add(new TodoDto("拟定于今日进行外访总数",todayVisitCount,"/collection/visitrecordappointed","已排程外访"));
		}
		//外访主任
		if(SecurityUtil.hasRole("wfzr")){
			int queryToApproveORCount= visitRecordService.queryToApproveORToAppointCount(0);
			todoList.add(new TodoDto("待审批外访总数",queryToApproveORCount,"/collection/visitrecordtoapprove","待审批外访"));
			
			int queryToAppointCount= visitRecordService.queryToApproveORToAppointCount(2);
			todoList.add(new TodoDto("待排程外访总数",queryToAppointCount,"/collection/visitrecordtoappoint","待排程外访"));
			
		}
		
		
		//风控员
		if(SecurityUtil.hasRole("CSY")){
			List<CaseModel> nextList=caseService.queryCaseTotoFollow();
			for (CaseModel caseModel : nextList) {
				todoList.add(new TodoDto("案件"+caseModel.getCaseCode()+"拟定于今日进行跟进",1,"/collection/casedetail?caseId="+caseModel.getId(),"案件详情"));
			}
			
			
			int day = 7;
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String backDateBegin=sdf.format(now);
			Date endDate = DateUtil.addDays(now, day);
			String backDateEnd=sdf.format(endDate);
//			view.addObject("backDateBegin", backDateBegin);
//			view.addObject("backDateEnd", backDateEnd);
			int reminderCaseCount = caseService.queryReminderCaseCount(day);
			todoList.add(new TodoDto("将于7日内退案案件总数",reminderCaseCount,"/collection/casecollect/assigned?backDateBegin="+backDateBegin+"&backDateEnd="+backDateEnd,"我的案件"));
		
		}
		//风控经理
		if(SecurityUtil.hasRole("csjl")){
			
			int queryTargetArchive = targetAchieveService.queryTargetArchive();
			todoList.add(new TodoDto("目标业绩录入",queryTargetArchive,"/collection/targetAchieve","目标业绩录入"));
			int divisionCaseCount=caseService.queryDivisionCaseCount();
			todoList.add(new TodoDto("待分案案件总数",divisionCaseCount,"/collection/case","分案管理"));
			
		}
		//客服
		if(SecurityUtil.hasRole("kf")){
			
			int letterToApproveCount = caseApplyService.queryLetterCount(-2);
			todoList.add(new TodoDto("待审批信函总数",letterToApproveCount,"/collection/caseLetter/approvalPending","待审批信函"));
			int letterToPostCount= caseApplyService.queryLetterCount(0);
			todoList.add(new TodoDto("待邮寄信函总数",letterToPostCount,"/collection/caseLetter/pending","待处理信函"));
			int toConfirmPayCount=casePaidService.queryToConfirmPayCount();
			todoList.add(new TodoDto("待确认还款总数",toConfirmPayCount,"/collection/casepaidshow","还款管理"));
			
		}
		if(SecurityUtil.hasRole("kf") || SecurityUtil.hasRole("csjl")){
			int queryXiecuiTasklistCount=caseApplyService.queryXiecuiTasklistCount();
			todoList.add(new TodoDto("待回复协催总数",queryXiecuiTasklistCount,"/collection/xiecui/task","待处理协催"));
			
			int chaziToApproveCount=helpMeService.queryChaziToApproveCount();
			todoList.add(new TodoDto("待审批查资总数",chaziToApproveCount,"/collection/helpme","待审批查资"));
			
			int querydealCount=helpMeService.querydealCount();
			todoList.add(new TodoDto("待回复查资总数",querydealCount,"/collection/helpmedeal","待处理查资"));
		}
		view.addObject("todoList",todoList);
		//提醒
		if(SecurityUtil.hasRole("CSY")){
			condition.put("remindTarget", SecurityUtil.getCurrentUser().getId());
			List<MessageReminderModel> reminderList = messageReminderService.query(condition);
			view.addObject("reminderList", reminderList);
		}
		return view;
	}

	protected String getBaseName() {
		return "collection/targetAchieve/";
	}

}
