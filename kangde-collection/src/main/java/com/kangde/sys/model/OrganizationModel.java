package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;

/**
 * 组织机构Model
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class OrganizationModel extends BaseModel<String> {

	/** 组织机构名称 */
	private String name;
	/** 上级组织机构 */
	private String parentId;
	/** 地址 */
	private String address;
	/** 电话号码 */
	private String phone;
	/** 邮政编码 */
	private String postCode;
	/** 传真号 */
	private String fax;
	/** 机构类型 */
	private Integer type;
	/** 排序号 */
	private Integer orderNo;
	/** 机构编码 */
	private String code;
	/** 区域名称 */
	private String areaNames;
	/** 区域ID,逗号分割 */
	private String areaIds;
	/** 机构负责人ID */
	private String principalId;
	/** 机构负责人姓名 */
	private String principalName;
	/** 路径 */
	private String path;
	
	private String soName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	public String getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}

	/**
	 * 兼容EasyUI
	 * 
	 * @return
	 */
	public String get_parentId() {
		return parentId;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSoName() {
		return soName;
	}

	public void setSoName(String soName) {
		this.soName = soName;
	}

}
