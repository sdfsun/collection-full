package com.kangde.sys.mapper;




import java.util.List;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.ProvinceModel;
/**
 * 外访区域mapper
 * @author wcy
 * @date 2016年6月23日11:15:32
 *
 */
public interface ProvinceMapper extends BaseMapper<ProvinceModel,String> {
	
	 /** 新增判断是否重复  */
	 List<ProvinceModel> sizeNum(ProvinceModel model);
	/** 省份  禁用/启用*/
	 int updateForStatus(ProvinceModel model);
	 /** 添加省份  */
	 int saveProv(ProvinceModel model);
	 /** 编辑省份  */
	 int updateProv(ProvinceModel model);
	 
	 ProvinceModel findId(String provinceId);

	
}