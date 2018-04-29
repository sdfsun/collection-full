package com.kangde.sys.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.RoleModel;
import com.kangde.sys.service.RoleService;

/**
 * 角色Controller
 * @author lisuo
 *
 */
@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends RestfulUrlController<RoleModel,String>{
	
	//权限码前缀
	private static final String PERMISION_PREFIX = "role";
	
	@Autowired
	private RoleService roleService;
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView index() {
		return super.pageIndex();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public SearchResult<RoleModel> queryForPage() {
		ParamCondition condition = parseCondition("name");
		return super.queryForPage(condition);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":view"})
	@Override
	public ModelAndView pageIndex() {
		return super.pageIndex();
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":save"})
	@Override
	public AjaxResult save(RoleModel model) {
		return super.save(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":update"})
	@Override
	public AjaxResult update(RoleModel model) {
		return super.update(model);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	@Override
	public AjaxResult deleteById(@PathVariable("id") String id) {
		return super.deleteById(id);
	}
	
	@RequiresPermissions({PERMISION_PREFIX+":delete"})
	@Override
	public AjaxResult deleteByIds(@RequestParam(value="ids") String[] ids) {
		return super.deleteByIds(ids);
	}
	
	@Override
	protected String getBaseName() {
		return "sys/role/";
	}
	
	@Override
	public ModelAndView pageInput() {
		ModelAndView view = super.pageInput();
		return view;
	}
	
	@Override
	public ModelAndView pageInput(@PathVariable("id") String id) {
		ModelAndView view = super.pageInput(id);
		return view;
	}
	
	/**
	 * 获取角色关联的资源ID
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "getResourceIds")
	@ResponseBody
	public AjaxResult getResourceIds(@RequestParam String roleId){
		List<String> resourceIds = roleService.findResourceIdsByRoleId(roleId);
		return AjaxResult.success(null, resourceIds);
	}
	
	 /**
     * 获取角色下拉框列表.
     */
    @RequestMapping(value = "combobox")
    @ResponseBody
    public List<Combobox> combobox(String selectType) throws Exception {
        List<RoleModel> list = roleService.query(parseCondition());
        List<Combobox> cList = Lists.newArrayList();
        //为combobox添加  "---全部---"、"---请选择---"
        if (!StringUtils.isBlank(selectType)) {
            SelectType s = SelectType.getSelectTypeValue(selectType);
            if (s != null) {
                Combobox selectCombobox = new Combobox("", s.getDescription());
                cList.add(selectCombobox);
            }
        }
        for (RoleModel r : list) {
            Combobox combobox = new Combobox(r.getId() + "", r.getName());
            cList.add(combobox);
        }
        return cList;
    }

	
	
}
