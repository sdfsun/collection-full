package com.kangde.sys.model;

import java.util.List;

import com.kangde.commons.model.BaseModel;

/**
 * 数据字典模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class DictionaryModel extends BaseModel<String> {

	/** 字典名称 */
	private String name;
	/** 字典键 */
	private String dictKey;
	/** 字典值 */
	private String value;
	/** 备注 */
	private String remark;
	/** 排序号 */
	private Integer orderNo;
	/** 父节点ID */
	private String parentId;
	/** 字典路径 */
	private String path;
	
	//兼容easyui
	private String parent_id;
	
	//help
	/** 子节点 */
	private List<DictionaryModel> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
		this.parent_id = parentId;
	}

	/**
	 * 兼容EasyUI
	 * 
	 * @return
	 */
	public String get_parentId() {
		return parent_id;
	}
	
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<DictionaryModel> getChildren() {
		return children;
	}

	public void setChildren(List<DictionaryModel> children) {
		this.children = children;
	}
}
