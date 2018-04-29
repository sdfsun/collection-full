package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;

/**
 * 动态列 模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class ColumnModel extends BaseModel<String> {
	/** 字段名称 */
	private String field;
	/** 标题名称 */
	private String title;
	/** 宽度 */
	private Integer width;
	/** 是否可以排序 */
	private Integer sortable;
	/** 对其方式 */
	private String align;
	/** 所属模块 */
	private String gropNo;
	/** 排序号 */
	private Integer orderNo;
	/** 表示 */
	private String indicate;
	
	//help
	/** 是否可见 */
	private boolean hidden = false;
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getSortable() {
		return sortable;
	}

	public void setSortable(Integer sortable) {
		this.sortable = sortable;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getGropNo() {
		return gropNo;
	}

	public void setGropNo(String gropNo) {
		this.gropNo = gropNo;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getIndicate() {
		return indicate;
	}

	public void setIndicate(String indicate) {
		this.indicate = indicate;
	}

}
