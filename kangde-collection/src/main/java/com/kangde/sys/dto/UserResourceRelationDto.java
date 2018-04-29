package com.kangde.sys.dto;

import com.kangde.commons.dto.BaseDto;

/**
 * 用户-资源-关系 Dto
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class UserResourceRelationDto extends BaseDto {

	/** 用户ID */
	private String userId;
	/** 资源ID */
	private String resourceId;

	public UserResourceRelationDto() {
		super();
	}

	public UserResourceRelationDto(String userId, String resourceId) {
		super();
		this.userId = userId;
		this.resourceId = resourceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
