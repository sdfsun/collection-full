package com.kangde.collection.model;

import com.kangde.commons.model.BaseModel;

/**
 * 区域表 模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class AreaModel extends BaseModel<String> {

	/** 区域名称 */
	private String name;
	/** 所属机构 */
	private String orgId;
	
	private String areaName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
