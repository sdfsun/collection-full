package com.kangde.collection.controller;

import java.util.List;	

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.VisitRecordVo;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/visitrecord")
public class VisitRecordController extends RestfulUrlController<VisitRecordModel, String> {
	@Autowired
	private VisitRecordService visitRecordService;
	
	
//	@ResponseBody
//	@RequestMapping(value ="repaire", method = RequestMethod.GET)
//	public void repair() {
//		List<VisitRecordVo> list = visitRecordService.repair();
//	}
//	
	
	@Override
	public List<VisitRecordModel> list() {
		ParamCondition condition = this.parseCondition("caseId");
		List<VisitRecordModel> list = super.query(condition);
		return list;
	}
	
	@Override
	public ModelAndView pageIndex() {
		ModelAndView view = createBaseView(PAGE_INDEX);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}
	
	@Override
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		HttpServletRequest request = this.getRequest();
		ModelAndView view = createBaseView(PAGE_INPUT);
		String caseId = request.getParameter("caseId");
		view.addObject("caseId", caseId);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<VisitRecordModel> queryForPage() {
		ParamCondition condition = parseCondition();
		condition.put("caseId", this.getRequest().getParameter("caseId"));
		SearchResult<VisitRecordModel> list = super.queryForPage(condition);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value ="queryVisitRecordVoForPage", method = RequestMethod.GET)
	public SearchResult<VisitRecordVo> queryVisitRecordVoForPage() {
		ParamCondition condition = parseCondition();
		condition.put("caseId", this.getRequest().getParameter("caseId"));
		SearchResult<VisitRecordVo> list = visitRecordService.queryVisitRecordVoForPage(condition);
		return list;
	}
	
	
	@Override
	protected String getBaseName() {
		return "collection/visitrecord/";
	}

}
