package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;
/**
 * 省市 模型
 * 
 * @author wcy
 * @date 2016年6月23日10:51:22
 *
 */
@SuppressWarnings("serial")
public class AreaModel extends BaseModel<String>{
	
	/** id */
    private String areaId;
    /** 城市id */
    private String cityId;
    /** 县名称 */
    private String areaName;
    /**  */
    private String areaIsenabled;
    
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaIsenabled() {
		return areaIsenabled;
	}
	public void setAreaIsenabled(String areaIsenabled) {
		this.areaIsenabled = areaIsenabled;
	}
    

}