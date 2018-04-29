package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;

/**
 * 职位 Model
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class PositionModel extends BaseModel<String> {
	/** 职位名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 上级ID */
	private String parentId;
	/** 排序号 */
	private Integer orderNo;
	/** 职位类型 */
	private String type;
	/** 路径 */
	private String path;
	
	/**
	 * 兼容EasyUI
	 * @return
	 */
	public String get_parentId() {
		return parentId;
	}

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
