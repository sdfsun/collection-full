package com.kangde.commons.easyui;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * easyui动态列column模型.
 */
@SuppressWarnings("serial")
public class Column implements Serializable{
	
	public Column() {
	}
	
	/**
	 * 字段名称
	 */
	private String field;
	/**
	 * 显示标题
	 */
	private String title;
	/**
	 * 宽度
	 */
	private Integer width;
	/**
	 * 跨行数
	 */
	private Integer rowspan;
	/**
	 * 跨列数
	 */
	private Integer colspan;
	/**
	 * 是否选checkbox
	 */
	private Boolean checkbox;

	/**
	 * 如果为true，则允许列使用排序。
	 */
	private Boolean sortable;
	/**
	 * 是否隐藏
	 */
	private Boolean hidden = false;

    //对齐方式
	/** 左对齐 */
    public static final String ALIGN_LEFT = "left";
    /** 居中 */
    public static final String ALIGN_CENTER = "center";
    /** 右对齐 */
    public static final String ALIGN_RIGHT = "right";
    
    
	/**
	 * 对齐方式(默认：'left',可选值：'left'，'right'，'center' 默认左对齐)
	 */
	private String align = ALIGN_LEFT;

	public Column(String field, String title, Integer width,
			String align) {
		super();
		this.field = field;
		this.title = title;
		this.width = width;
		this.align = align;
	}

	/**
	 * 字段名称
	 */
	public String getField() {
		return field;
	}

	public Column setField(String field) {
		this.field = field;
        return this;
	}

	/**
	 * 显示标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置显示标题
	 */
	public Column setTitle(String title) {
		this.title = title;
        return this;
	}

	/**
	 * 宽度
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * 设置宽度
	 */
	public Column setWidth(Integer width) {
		this.width = width;
        return this;
	}

	/**
	 * 跨行数
	 */
	public Integer getRowspan() {
		return rowspan;
	}

	/**
	 * 设置跨行数
	 */
	public Column setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
        return this;
	}

	/**
	 * 跨列数
	 */
	public Integer getColspan() {
		return colspan;
	}

	/**
	 * 设置跨列数
	 */
	public Column setColspan(Integer colspan) {
		this.colspan = colspan;
        return this;
	}

	/**
	 * 是否选中checkbox
	 */
	public Boolean isCheckbox() {
		return checkbox;
	}

	/**
	 * 设置是否选中
	 */
	public Column setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
        return this;
	}

	/**
	 * 对齐方式(默认：'left',可选值：'left'，'right'，'center' 默认左对齐)
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * 设置对齐方式(可选值：'left'，'right'，'center' 默认左对齐)
	 */
	public Column setAlign(String align) {
		this.align = align;
        return this;
	}

	/**
	 * 是否可以排序
	 * @return
	 */
	public Boolean getSortable() {
		return sortable;
	}

	/**
	 * 设置是否可以排序
	 * @param sortable
	 */
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

}