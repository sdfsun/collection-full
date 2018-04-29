package com.kangde.sys.service;


import com.kangde.commons.service.BaseService;
import com.kangde.sys.dto.AreaDto;
import com.kangde.sys.model.AreaModel;
import com.kangde.sys.model.CityModel;

/**
 *  外访区域 城市Service
 * @author wcy
 * @date 2016年6月23日11:07:22
 */
public interface SysAreaService extends BaseService<AreaDto,String>{
	
	/** 省份  禁用/启用*/
	 int updateForStatus(AreaModel model);
	 /** 新增省份  */
	 int saveArea(AreaModel model);
	 /** 编辑  */
	 int updateArea(AreaModel model);
}
