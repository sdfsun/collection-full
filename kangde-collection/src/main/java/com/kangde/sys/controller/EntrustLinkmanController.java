package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.dto.EntrustLinKmanDto;
import com.kangde.sys.service.EntrustLinkmanService;

/**
 * 委托方 Controller
 * @author zhangyj
 * @date 2016.6.1
 */
@Controller
@RequestMapping("sys/entrustLinkman")
public class EntrustLinkmanController extends RestfulUrlController<EntrustLinKmanDto,String> {
	// 权限码前缀
	private static final String PERMISION_PREFIX = "entrustLinkman";
	
	@Autowired
	private EntrustLinkmanService entrustLinkmanService;
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return super.index();
	}
	
	
	//输入页面（增加，修改，展示都用这个页面）,带ID把id放入请求域,不从数据库查询数据,转发到getBaseName/input
		@RequestMapping(value = "input" + "/{id}", method = RequestMethod.GET)
		@Override
		public ModelAndView pageInput(@PathVariable("id") String id) {
			ModelAndView view =createBaseView("input");
			return view;
		}
		
		
	@RequiresPermissions(value = { PERMISION_PREFIX + ":save" })
	@Override
	public AjaxResult save(EntrustLinKmanDto model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":update" })
	@Override
	public AjaxResult update(EntrustLinKmanDto model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	
	
	
	@Override
	public SearchResult<EntrustLinKmanDto> queryForPage() {
		ParamCondition condition = parseCondition("*");
		 SearchResult<EntrustLinKmanDto> list=super.queryForPage(condition);
		return list;
	}
	
	
	 	@RequestMapping(value = "combobox")
	    @ResponseBody
	    public List<Combobox> combobox(@RequestParam(value="entrustNameValue") String entrustNameValue){
	 		return entrustLinkmanService.combobox(entrustNameValue);
	    }
	 
	@Override
	protected String getBaseName() {
		return "sys/entrustLinkman/";
	}
	
	
	
}
