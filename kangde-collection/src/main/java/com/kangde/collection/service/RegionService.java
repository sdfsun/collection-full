package com.kangde.collection.service;

import java.util.List;

import com.kangde.collection.model.RegionModel;
import com.kangde.commons.service.BaseService;

/**
 * 区域service
 * 
 * @author lisuo
 *
 */
public interface RegionService extends BaseService<RegionModel, Integer> {

	/**
	 * 通过城市名称
	 * 
	 * @param cityName
	 * @return
	 */
	List<RegionModel> findByCityName(String cityName);

	/**
	 * 通过省份名称
	 * 
	 * @param cityName
	 * @return
	 */
	List<RegionModel> findByProvinceName(String provinceName);

	/** 查询所有省 */
	List<RegionModel> findProvince();

	/** 查询所有市 */
	List<RegionModel> findCityByProvinceId(String provinceId);

	/** 查询所有县 */
	List<RegionModel> findAreaByCityId(String cityId);

}
