package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;

/**
 * 省份 模型
 * 
 * @author wcy
 * @date 2016年6月23日10:51:22
 *
 */

@SuppressWarnings("serial")
public class ProvinceModel extends BaseModel<String>{
	/** id */
    private String provinceId;
    /**  */
    private String provinceName;
    /**  */
    private String provinceIsenabled;
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceIsenabled() {
		return provinceIsenabled;
	}
	public void setProvinceIsenabled(String provinceIsenabled) {
		this.provinceIsenabled = provinceIsenabled;
	}
    
    
    
}