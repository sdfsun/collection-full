package com.kangde.collection.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.model.CensusRegisterModel;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 户籍 Controller
 * 
 * @author wcy
 * @date 2016年7月21日13:52:54
 */
@Controller
@RequestMapping("collection/censusregister")
public class CensusRegisterController extends RestfulUrlController<CensusRegisterModel, String> {
	
	@Override
	protected String getBaseName() {
		return "collection/censusregister/";
	}

}
