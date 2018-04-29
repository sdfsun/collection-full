package com.kangde.sys.dto;

import com.kangde.sys.model.CityModel;
import com.kangde.sys.model.ProvinceModel;

/**
 * 外访区域 Dto
 * 
 * @author wcy
 * @date 2016年6月23日10:54:45
 *
 */
@SuppressWarnings("serial")
public class CityDto extends CityModel{
	
	/** 省份表*/
	private ProvinceModel provinceModel;

	public ProvinceModel getProvinceModel() {
		return provinceModel;
	}

	public void setProvinceModel(ProvinceModel provinceModel) {
		this.provinceModel = provinceModel;
	}
	
	
	
	

}
