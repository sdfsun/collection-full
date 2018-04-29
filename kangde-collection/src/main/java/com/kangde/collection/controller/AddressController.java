package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.CaseApplyService;
import com.kangde.collection.service.VisitRecordService;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.SimpleController;

@Controller
@RequestMapping("collection/address")
public class AddressController extends SimpleController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private CaseApplyService caseApplyService;

	/**
	 * 获取区域下拉框
	 * 
	 * @param selectType
	 * @param incluedeIds
	 * @return
	 */
	@RequestMapping(value = "combobox")
	@ResponseBody
	public List<Combobox> combobox(@RequestParam(value = "selectType", required = false) String selectType,
			String caseId) {
		// 查询外访已经完成的地址
		return combobox(addressService.findFinishedVisitRecord(caseId), selectType);
	}

	private List<Combobox> combobox(List<AddressModel> entrusts, String selectType) {
		List<Combobox> resultList = new ArrayList<>();
		// 获取标题
		selectType = null;
		CoreUtil.getComboTitle(resultList, selectType);
		if (CollectionUtils.isNotEmpty(entrusts)) {
			for (AddressModel entrust : entrusts) {
				Combobox combobox = new Combobox(entrust.getId(), entrust.getName());
				resultList.add(combobox);
			}
		}
		return resultList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		return pageIndex();
	}

	@ResponseBody
	@RequestMapping(value = QUERY_LIST, method = RequestMethod.GET)
	public List<AddressModel> list(String caseId) {
		return addressService.queryByCaseId(caseId);
	}

	@ResponseBody
	@RequestMapping(value = "queryDedail", method = RequestMethod.GET)
	public SearchResult<AddressModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
		return addressService.queryDetail(condition);
	}

	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		HttpServletRequest request = this.getRequest();
		ModelAndView view = createBaseView(PAGE_INPUT);

		view.addObject("caseId", request.getParameter("caseId"));
		view.addObject("id", request.getParameter("id"));
		return view;
	}

	protected String getBaseName() {
		return "collection/address/";
	}

	// 首页页面,转发到getBaseName/index
	@RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
	public ModelAndView pageIndex() {
		ModelAndView view = createBaseView(PAGE_INDEX);
		view.addObject("caseId", this.getRequest().getParameter("caseId"));
		return view;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(value=PermissionConst.detail_address_save)
	public AjaxResult save(AddressModel model) {
		// '地址状态 0未知 1有效 2无效
		model.setStatus(1);
		model.setSource("1");
		addressService.save(model);
		return AjaxResult.success(getSuccessMessage());
	}

	@ResponseBody
	@RequestMapping(value = "saveCaseApplyInfo", method = RequestMethod.POST)
	public AjaxResult saveCaseApplyInfo(AddressModel model) {
		// '地址状态 0未知 1有效 2无效
		model.setStatus(1);
		model.setSource("3");
		ParamCondition condition = this.parseCondition("*");
		List<AddressModel> list = addressService.query(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			throw new ServiceException("地址已存在");
		}
		addressService.save(model);
		return AjaxResult.success(getSuccessMessage());
	}

	@RequestMapping(value = "update")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_address_update)
	public AjaxResult updateById(AddressModel model) {
		AddressModel admodel= addressService.findById(model.getId());//查询数据
		String address=admodel.getAddress();//获取修改前的地址
		if(address.indexOf("/") != -1)  //修改前的地址是否包含/  如果有就截取 / 前面的字符串  追加修改后的地址
		{  
			String subAddres=address.substring(0, address.indexOf("/"));
			String newAddress=new StringBuffer().append(subAddres).append("/").append(model.getAddress()).toString(); 
		     model.setAddress(newAddress);
		}  //如果不包含/ 就直接修改
		addressService.updateById(model);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@RequestMapping(value = "deleteById")
	@ResponseBody
	@RequiresPermissions(value=PermissionConst.detail_address_delete)
	public AjaxResult deleteById(String id) {
		addressService.deleteById(id);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	
	@RequestMapping(value = "enableAddress")
	@ResponseBody
	public AjaxResult enableAddress(String id) {
		AddressModel address = addressService.findById(id);
		address.setStatus(1);
		addressService.updateById(address);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}
	@RequestMapping(value = "disableAddress")
	@ResponseBody
	public AjaxResult disableAddress(String id) {
		AddressModel address = addressService.findById(id);
		address.setStatus(2);
		addressService.updateById(address);
		AjaxResult result = AjaxResult.success(getSuccessMessage());
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "checkAddress", method = RequestMethod.GET)
	public String checkAddress() {
		AddressModel addressInfo = addressService.findById(this.getRequest().getParameter("id"));
		JSONObject json = new JSONObject();
		try {
			if (addressInfo.getAreaId1() == null || addressInfo.getAreaId2() == null
					|| addressInfo.getAreaId3() == null) {
				throw new AddressException("请点击编辑按钮,完善地址信息后,再申请外访!");
			}
			String address = addressService.findFullAddress(addressInfo.getAreaId1(),
					addressInfo.getAreaId2(), addressInfo.getAreaId3())
					+ addressInfo.getAddress();
			json.put("state", 1);
			json.put("msg", "OK");
			json.put("address", address);
		} catch (AddressException e) {
			json.put("state", 0);
			json.put("msg", e.getMessage());
		}
		return json.toJSONString();
	}

	@RequestMapping(value = "visit", method = RequestMethod.GET)
	public ModelAndView visit() {
		String caseId = this.getRequest().getParameter("caseId");
		String addressId = this.getRequest().getParameter("id");
		AddressModel addressInfo = addressService.findById(addressId);
		ModelAndView view = createBaseView("visit");
		view.addObject("caseId", caseId);
		view.addObject("addressInfo", addressInfo);
		try {
			String address  = addressService.findFullAddress(addressInfo.getAreaId1(),
					addressInfo.getAreaId2(), addressInfo.getAreaId3())
					+ addressInfo.getAddress();
			view.addObject("addressDetail", address);
		} catch (AddressException e) {
			throw new ServiceException(e.getMessage());
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "applyToVisit", method = RequestMethod.POST)
	public AjaxResult applyToVisit(VisitRecordModel model) {
		visitRecordService.applyToVisit(model, model.getAddressId());
		return AjaxResult.success(getSuccessMessage());
	}

	@ResponseBody
	@RequestMapping(value = "applyToLetter", method = RequestMethod.POST)
	public AjaxResult applyToLetter(String id, String caseId) throws AddressException {
		caseApplyService.applyToLetter(id, caseId);
		return AjaxResult.success(getSuccessMessage());
	}
}
