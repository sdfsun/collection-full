package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.RegionModel;
import com.kangde.commons.mapper.BaseMapper;

/**
 * 三级地址区域表 模型
 * 
 * @author lisuo
 *
 */
public interface RegionMapper extends BaseMapper<RegionModel, Integer> {
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

	RegionModel findId(String id);

	/** 查询所有省 */
	List<RegionModel> findProvince();

	/** 查询所有市 */
	List<RegionModel> findCityByProvinceId(String provinceId);

	/** 查询所有县 */
	List<RegionModel> findAreaByCityId(String cityId);
}