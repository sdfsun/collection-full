package com.kangde.sys.dto;

import com.kangde.commons.dto.BaseDto;

/**
 * 角色-资源-关系 Dto
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class RoleResourceRelationDto extends BaseDto {
	/** 角色ID */
	private String roleId;
	/** 资源ID */
	private String resourceId;
	
	public RoleResourceRelationDto() {
		super();
	}

	public RoleResourceRelationDto(String roleId, String resourceId) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
