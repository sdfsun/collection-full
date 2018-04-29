package com.kangde.commons.easyui;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.Maps;


public class EasyUITreeNode implements Serializable{
	

	
	/**  */
	
	private static final long serialVersionUID = -5504349873935555402L;
	/**
	 * 静态变量 展开节点
	 */
	public static final String STATE_OPEN = "open";
	/**
	 * 静态变量 关闭节点
	 */
	public static final String STATE_CLOASED = "closed";

	/**
	 * 节点id
	 */
	private String id;
	/** 上级组织机构 */
	private String parentId;
	/**
	 * 树节点名称
	 */
	private String text;
	/**
	 * 前面的小图标样式
	 */
	private String iconCls = "";
	/**
	 * 是否勾选状态（默认：否false）
	 */
	private Boolean checked = false;
	/**
	 * 自定义属性
	 */
	private Map<String, Object> attributes = Maps.newHashMap();
	
	/**
	 * 是否展开 (open,closed)-(默认值:open)
	 */
	private String state = STATE_OPEN;

	


	public EasyUITreeNode() {
		super();
	}

	public EasyUITreeNode(String id, String text, String parentId) {
		this.id=id;
		this.text=text;
		if(parentId==null){
			parentId="";
		}
		this.parentId=parentId;
	}
	
	
	/**
	 * 节点id
	 */
	public String getId() {
		return id;
	}

	public EasyUITreeNode setId(String id) {
		this.id = id;
        return this;
	}

  
    /**
	 * 树节点名称
	 */
	public String getText() {
		return text;
	}

	public EasyUITreeNode setText(String text) {
		this.text = text;
        return this;
	}

	/**
	 * 是否勾选状态（默认：否）
	 */
	public Boolean getChecked() {
		return checked;
	}

	public EasyUITreeNode setChecked(Boolean checked) {
		this.checked = checked;
        return this;
	}

	/**
	 * 自定义属性
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public EasyUITreeNode setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
        return this;
	}



	/**
	 * 是否展开
	 */
	public String getState() {
		return state;
	}

	public EasyUITreeNode setState(String state) {
		this.state = state;
        return this;
	}

	


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String get_parentId() {
		return parentId;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EasyUITreeNode other = (EasyUITreeNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
