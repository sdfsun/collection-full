package com.kangde.collection.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.model.NoticeModel;
import com.kangde.collection.service.NoticeService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("/sys/notice")
public class NoticeController extends RestfulUrlController<NoticeModel,String>  {
	@Autowired
	private NoticeService noticeService;
	
	
	/**
	 * 带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<NoticeModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		return super.queryForPage(condition);
	}
	
	
	
	@Override
	public AjaxResult save(NoticeModel model) {
		String content = model.getContent();
		String[] split = content.split("\n");
		String con=StringUtils.join(split, "<br/>");
		model.setContent(con);
		return super.save(model);
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
	
	
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult updateById(NoticeModel model) {
		//NoticeModel m= noticeService.findById(model.getId());//查询数据
		//model.setTitle(model.getTitle());
		//model.setContent(model.getContent());
		noticeService.update(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public AjaxResult deleteById(String id) {
		noticeService.deleteById(id);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	
	protected String getBaseName() {
		return "sys/notice/";
	}
}
