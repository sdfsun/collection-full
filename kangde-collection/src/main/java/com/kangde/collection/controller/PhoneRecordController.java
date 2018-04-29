package com.kangde.collection.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.PhoneRecordModel;
import com.kangde.collection.service.CaseDetailService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.HolidayService;
import com.kangde.collection.service.PhoneRecordService;
import com.kangde.collection.vo.PhoneRecordInputModel;
import com.kangde.collection.vo.PhoneRecordVo;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.SimpleController;
import com.kangde.sys.security.util.SecurityUtil;

@Controller
@RequestMapping("collection/phonerecord")
public class PhoneRecordController extends SimpleController {
	@Autowired
	private PhoneRecordService phoneRecordService;

	@Autowired
	private CaseDetailService caseDetailService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private HolidayService holidayService;

	@ResponseBody
	@RequestMapping(value = QUERY_LIST, method = RequestMethod.GET)
	public List<PhoneRecordModel> list(String caseId) {
		List<PhoneRecordModel> list = phoneRecordService.query(caseId);
		return list;
	}

	// Excel导出选查案件
	@RequestMapping(value = "/exportSelectedExcel")
	public ModelAndView exportExcel(String caseId) {
		List<PhoneRecordModel> list = phoneRecordService.queryByCaseId(caseId);
		return super.createExcelView("phoneRecord", list, "风控记录", null, null);
	}

	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<PhoneRecordVo> queryForPage() {
		ParamCondition condition = parseCondition("*");
		SearchResult<PhoneRecordVo> queryForPage = phoneRecordService.queryForPage(condition);
		return queryForPage;
	}

	@RequestMapping(value = "queryByCollector")
	@ResponseBody
	public SearchResult<PhoneRecordVo> queryByCollector() {
		ParamCondition condition = parseCondition("*");
		condition.put("createEmpId", SecurityUtil.getCurrentUser().getId());
		condition.put("prCat", "0");
		SearchResult<PhoneRecordVo> queryForPage = phoneRecordService.queryForPage(condition);
		return queryForPage;
	}

	@RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
	public ModelAndView pageIndex() {
		ModelAndView view = new ModelAndView("collection/phonerecord/index");
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView input() {
		ModelAndView view = new ModelAndView("collection/phonerecord/input");
		String caseId = this.getRequest().getParameter("caseId");
		CaseModel caseModle = caseService.findById(caseId);
		view.addObject("caseModle", caseModle);
		view.addObject("createTime", DateUtil.thisDateTime());
		String next = new SimpleDateFormat("yyyy-MM-dd").format(holidayService.addDateByWorkDay(new Date(), 3).getTime());
		view.addObject("nextFollowTime", next);
		view.addObject("caseId", caseId);
		return view;
	}

	
	@RequestMapping(value = "phoneRecordAction")
	@ResponseBody
	public AjaxResult phoneRecordAction(PhoneRecordInputModel model) {
		
		caseDetailService.saveRecord(model.getContact(), model.getCreateTime(), model.getLinkmanId(), model.getPrCat(), model.getTypeId(),
				model.getCollecStateId(), model.getPtpTime(), model.getPtpMoney(), model.getContent(),
				model.getNextFollowTime(), model.getCaseId());
		String caseId = model.getCaseId();
		if(caseId!=null){
			caseMapper.updateLastPhoneForId(caseId,model.getCreateTime());
		}
		return AjaxResult.success(getSuccessMessage());
	}

	@ResponseBody
	@RequestMapping(value = "/getSysDateTime", method = RequestMethod.GET)
	public String getSysDateTime(String id) {
		JSONObject json = new JSONObject();
		json.put("createDate", DateUtil.thisDateTime());
		return json.toJSONString();
	}

}
