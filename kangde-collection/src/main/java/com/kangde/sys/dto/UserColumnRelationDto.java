package com.kangde.sys.dto;

import com.kangde.commons.dto.BaseDto;

/**
 * 用户-动态列-关系 Dto
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class UserColumnRelationDto extends BaseDto {
	/** 用户ID */
	private String userId;
	/** 列ID */
	private String columnId;

	public UserColumnRelationDto() {
		super();
	}

	public UserColumnRelationDto(String userId, String columnId) {
		super();
		this.userId = userId;
		this.columnId = columnId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

}
