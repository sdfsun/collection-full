package com.kangde.sys.security.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.RoleService;

/**
 * 判断角色是否有资格查看退案的案件
 * @author wangcy
 * @date 2016年10月24日10:19:50
 *
 */
public class RoleQualifications {
	
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private RoleService roleService;
	
	
	public boolean Qualifications(){
		EmployeeInfoModel model=SecurityUtil.getCurrentUser();
		List<String> roleIds = employeeInfoService.findRoleIdsByUserId(model.getId());
		roleService.findByIds(roleIds);
		return true;
	}
	
}
