package com.kangde.sys.service;


import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.ProvinceModel;

/**
 *  外访区域Service
 * @author wcy
 * @date 2016年6月23日11:07:22
 */
public interface ProvinceService extends BaseService<ProvinceModel,String>{
	
	/** 省份  禁用/启用*/
	 int updateForStatus(ProvinceModel model);
	 /** 新增省份  */
	 int saveProv(ProvinceModel model);
	 /** 编辑  */
	 int updateProv(ProvinceModel model);
	 
	

}
