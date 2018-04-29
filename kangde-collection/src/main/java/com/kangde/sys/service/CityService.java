package com.kangde.sys.service;


import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.sys.dto.CityDto;
import com.kangde.sys.model.CityModel;

/**
 *  外访区域 城市Service
 * @author wcy
 * @date 2016年6月23日11:07:22
 */
public interface CityService extends BaseService<CityDto,String>{
	
	/** 省份  禁用/启用*/
	 int updateForStatus(CityModel model);
	/** 新增省份  */
	 int saveCity(CityModel model);
	 
	 /** 编辑  */
	 int updateCity(CityModel model);
	 
	 List<CityModel> findAllCity();
	

}
