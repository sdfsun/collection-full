package com.kangde.collection.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.call.CallCenter;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.collection.service.CaseLinkmanService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.security.util.SecurityUtil;


@Controller
@RequestMapping("collection/linkman")
public class CaseLinkmanController extends RestfulUrlController<CaseLinkmanModel,String> {
	
	@Autowired
	private CaseLinkmanService caseLinkmanService;
	@Override
	public List<CaseLinkmanModel> list() {
		ParamCondition condition = this.parseCondition("*");
		List<CaseLinkmanModel> list = super.query(condition);
		return list;
	}
	
	/**
	 * 带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<CaseLinkmanModel> queryForPage() {
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
	@RequiresPermissions(value=PermissionConst.detail_linkmanphone_save)
	public AjaxResult save(CaseLinkmanModel model) {
		model.setSource("1");
		// TODO Auto-generated method stub
		return super.save(model);
	}
	
	



	
	
	@RequestMapping(value = "deleteById")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_linkmanphone_delete)
	public AjaxResult deleteById(String id) {
		return super.deleteById(id);
	}
	
	

	@RequestMapping(value = "update")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_linkmanphone_update)
	public AjaxResult update(CaseLinkmanModel model) {
		caseLinkmanService.update(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	
	@RequestMapping(value="disableCaseLinkman")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_linkmanphone_markno)
	public AjaxResult disableCaseLinkman(CaseLinkmanModel model) {
		caseLinkmanService.updateStatus(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	@RequestMapping(value="enableCaseLinkman")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_linkmanphone_markyes)
	public AjaxResult enableCaseLinkman(CaseLinkmanModel model) {
		caseLinkmanService.updateForStatusinvalid(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "callOut", method = RequestMethod.POST)
	public String callOut(String tel,String caseId) {
		
		/** 根据电话号查询
		 *  如果在电话簿里查到了该电话，提示让用户从电话簿里操作
		 * */
		List<CaseLinkmanModel> model=caseLinkmanService.findPhone(tel,caseId);
		JSONObject json=new JSONObject();
		if(model.size()!=0){
			json.put("msg", "请从电话簿中拨打该电话，以便催记中完整记录");
			return json.toJSONString();
		}
		String ccPwd = SecurityUtil.getCurrentUser().getCcPwd();
		String ccPhone = SecurityUtil.getCurrentUser().getCcPhone();
		CallCenter callCenter=CallCenter.getInstance();
		callCenter.setPwd(ccPwd);
		callCenter.setCno(ccPhone);
		callCenter.setCustomerNumber(tel);
		String call = callCenter.call();
		json.put("msg", call);
		json.put("createDate", DateUtil.thisDateTime());
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "saveCaseApplyInfo", method = RequestMethod.POST)
	public AjaxResult saveCaseApplyInfo(CaseLinkmanModel model) {
		ParamCondition condition=this.parseCondition("*");
		model.setSource("3");
		List<CaseLinkmanModel> list = caseLinkmanService.query(condition);
		if(CollectionUtils.isNotEmpty(list)){
			throw new ServiceException("电话已存在");
		}
		AjaxResult result = super.save(model);
		return result;
	}
	
	@Override
	protected String getBaseName() {
		return "collection/linkman/";
	}
	
	
}
