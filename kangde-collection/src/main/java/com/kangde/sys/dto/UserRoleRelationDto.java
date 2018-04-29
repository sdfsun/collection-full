package com.kangde.sys.dto;

import com.kangde.commons.dto.BaseDto;

/**
 * 用户-角色-关系 Dto
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class UserRoleRelationDto extends BaseDto {

	/** 用户ID */
	private String userId;
	/** 角色ID */
	private String roleId;

	public UserRoleRelationDto() {
		super();
	}

	public UserRoleRelationDto(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
