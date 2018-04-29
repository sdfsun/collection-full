package com.kangde.collection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.service.IdCardWebService;

@Controller
@RequestMapping("collection/ws/idcard")
public class IdcardWebController {

	@Autowired
	private IdCardWebService idCardWebService;

	@RequestMapping(value = "{idno}", method = RequestMethod.GET)
	@ResponseBody
	public String pageEdit(@PathVariable("idno") String idno) {
		String idcardInfo = idCardWebService.parseIdcard(idno);
		return idcardInfo;
	}
}
