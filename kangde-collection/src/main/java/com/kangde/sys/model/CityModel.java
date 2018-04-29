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
public class CityModel extends BaseModel<String>{
	
	/** id */
    private String cityId;
	/** */
    private String provinceId;
	/**  */
    private String cityName;
    /**  */
    private String cityIsenabled;
    
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityIsenabled() {
		return cityIsenabled;
	}
	public void setCityIsenabled(String cityIsenabled) {
		this.cityIsenabled = cityIsenabled;
	}
    

}