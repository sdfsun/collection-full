package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.mapper.EntrustMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EntrustService;

/**
 * 委托方 Controller
 * @author zhangyj
 * @date 2016.6.1
 */
@Controller
@RequestMapping("sys/entrust")
public class EntrustController extends RestfulUrlController<EntrustModel,String> {
	// 权限码前缀
	private static final String PERMISION_PREFIX = "entrust";
	@Autowired
	private EntrustService entrustService;
	@Autowired
	private EntrustMapper entrustMapper;
	
	/*@RequestMapping(value = "updateForState")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForState(@RequestParam("state") Integer state,EntrustModel model){
		model.setState(state);
		model.setModifyTime(new Date());
		entrustService.updateForState(model); 
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}*/
	@RequiresPermissions(value = { PERMISION_PREFIX + ":view" })
	@Override
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return super.index();
	}
	
	@RequiresPermissions(value = { PERMISION_PREFIX + ":save" })
	@Override
	public AjaxResult save(EntrustModel model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":update" })
	@Override
	public AjaxResult update(EntrustModel model) {
		// TODO Auto-generated method stub
		return super.update(model);
	}
	
	/**
	 * 启用 停用
	 * @param status
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { PERMISION_PREFIX + ":start" })
	@RequestMapping(value = "updateForStatus")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForStatus(HttpServletRequest request){
		String[] ids=request.getParameterValues("ids[]");
		entrustService.updateForStatus(ids);
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	@RequiresPermissions(value = { PERMISION_PREFIX + ":stop" })
	@RequestMapping(value = "updateForStatusNo")
	@ResponseBody
	//修改状态  
	public AjaxResult updateForStatusNo(HttpServletRequest request){
		String[] ids=request.getParameterValues("ids[]");
		entrustService.updateForStatusNo(ids);
	    AjaxResult result = AjaxResult.success(getSuccessMessage());
	    return result;
	}
	
	@Override
	public SearchResult<EntrustModel> queryForPage() {
		ParamCondition condition = parseCondition("*");
//		String cooperateDate = getRequest().getParameter("cooperateDate");
//		condition.put("cooperateDate", cooperateDate);
		return super.queryForPage(condition);
	}
	
    @RequestMapping(value = "combobox")
    @ResponseBody
    public List<Combobox> combobox(@RequestParam(value="selectType",required=false) 
    String selectType,String incluedeId){
    	EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
    	String entrustId = currentUser.getAttachEntrustId();
    	List<EntrustModel> 	entrusts;
    	if(StringUtils.isNotBlank(entrustId)){
    		String[] ids = StringUtils.split(entrustId, ",");
    		entrusts = entrustService.findByIds(Arrays.asList(ids));
    	}else{
    		entrusts = entrustService.findAll();
    	}
		return combobox(entrusts, selectType);
    }
    @RequestMapping(value = "entrustlist")
    @ResponseBody
    public List<Combobox> entrustlist(@RequestParam(value="selectType",required=false) 
    String selectType,String incluedeId){
    		List<EntrustModel> entrustlist = entrustService.findAll();
    	return combobox(entrustlist, selectType);
    }
    @RequestMapping(value = "combobox2")
    @ResponseBody
    public List<Combobox> combobox2(@RequestParam(value="selectType",required=false) 
    String selectType,String incluedeId){
    	EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
    	String entrustId = currentUser.getAttachEntrustId();
    	List<EntrustModel> 	entrusts;
    	if(StringUtils.isNotBlank(entrustId)){
    		String[] ids = StringUtils.split(entrustId, ",");
    		entrusts = entrustMapper.findByIds2(Arrays.asList(ids));
    	}else{
    		entrusts = entrustMapper.findAll2();
    	}
    	return combobox(entrusts, selectType);
    }
    
    /**
     * 生成combobox
     * @param entrusts
     * @param selectType
     * @return
     */
    private  List<Combobox> combobox(List<EntrustModel> entrusts,String selectType){
    	List<Combobox> resultList = new ArrayList<>();
    	//获取标题
    	CoreUtil.getComboTitle(resultList, selectType);
    	if(CollectionUtils.isNotEmpty(entrusts)){
    		for (EntrustModel entrust:entrusts) {
    			Combobox combobox = new Combobox(entrust.getId(),entrust.getName());
    			resultList.add(combobox);
    		}
    	}
		return resultList;
    }
    
	@Override
	protected String getBaseName() {
		return "sys/entrust/";
	}
	
	
	
}
