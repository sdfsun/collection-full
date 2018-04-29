package com.kangde.collection.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.model.CommentModel;
import com.kangde.collection.service.CommentService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;


@Controller
@RequestMapping("collection/comment")
public class CommentController extends RestfulUrlController<CommentModel,String> {
	@Autowired
	private CommentService commentService;
	/**
	 * 带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<CommentModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		return super.queryForPage(condition);
	}
	
	@Override
	public ModelAndView pageIndex() {
		ModelAndView view = createBaseView(PAGE_INDEX);
		String caseId=getRequest().getParameter("caseId");
		view.addObject("caseId",caseId );
		return view;
	}
	
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		String id =getRequest().getParameter("id");
		view.addObject("id",id );
		return view;
	}
	
	
	@Override
	protected String getBaseName() {
		return "collection/comment/";
	}
	
	
}
