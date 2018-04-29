package com.kangde.sys.mapper;




import java.util.List;

import com.kangde.collection.model.AreaModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.dto.CityDto;
import com.kangde.sys.model.CityModel;
/**
 * 外访区域  城市  mapper
 * @author wcy
 * @date 2016年6月23日11:15:32
 *
 */
public interface CityMapper extends BaseMapper<CityDto,String> {

	 /** 新增判断是否重复  */
	 List<CityModel> sizeNum(CityModel model);
	/** 省份  禁用/启用*/
	 int updateForStatus(CityModel model);
	/** 新增省份  */
	 int saveCity(CityModel model);
	 /** 编辑省份  */
	 int updateCity(CityModel model);
	 /** 删除省份 附带删除城市表数据  */
	 int daleteCity(String id);
	 
	 List<CityModel> findAllCity();
	 
	 CityModel findId(String id);
	 
}