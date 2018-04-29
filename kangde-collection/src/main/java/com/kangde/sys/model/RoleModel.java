package com.kangde.sys.model;

import java.util.List;

import com.kangde.commons.model.BaseModel;

/**
 * 角色模型,用于给用户分配角色（资源打包）
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class RoleModel extends BaseModel<String> {

	/** 角色名称 */
	private String name;
	/** 角色描述 */
	private String description;
	/** 角色编码 */
	private String roleCode;
	
	/** 资源ID集合 */
	private List<String> resourceIds;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}
