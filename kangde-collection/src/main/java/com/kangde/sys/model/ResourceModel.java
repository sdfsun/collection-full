package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;

/**
 * 资源菜单
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class ResourceModel extends BaseModel<String> {

	/** 类型 菜单(0) */
	public static final Integer TYPE_MENU = 0;
	/** 类型 功能(1) */
	public static final Integer TYPE_FUN = 1;

	/** 资源名称 */
	private String name;
	/** 资源编码 */
	private String code;
	/** 资源url地址 */
	private String url;
	/** 排序号 */
	private Integer orderNo;
	/** 图标样式 */
	private String iconCls;
	/** 父级id */
	private String parentId;
	/** 资源类型 菜单(0) 功能(1) */
	private Integer type = TYPE_FUN;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 兼容EasyUI
	 * @return
	 */
	 public String get_parentId() {
        return parentId;
    }
}