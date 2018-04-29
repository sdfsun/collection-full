package com.kangde.collection.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.kangde.collection.model.CasePhoneModel;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.web.controller.RestfulUrlController;
@Controller
@RequestMapping("collection/casephone")
public class CasePhoneController extends RestfulUrlController<CasePhoneModel, String> {

	@Override
	public List<CasePhoneModel> list() {
		ParamCondition condition = this.parseCondition("caseId");
		List<CasePhoneModel> list = super.query(condition);
		return list;
	}

	@Override
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		String caseId = this.getRequest().getParameter("caseId");
		ModelAndView view = createBaseView(PAGE_INPUT);
		view.addObject("caseId", caseId);
		return view;
	}

	@Override
	protected String getBaseName() {
		return "collection/casephone/";
	}

}
