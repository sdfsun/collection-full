package com.kangde.sys.service.impl;



import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.dto.CityDto;
import com.kangde.sys.mapper.CityMapper;
import com.kangde.sys.model.CityModel;
import com.kangde.sys.service.CityService;

/**
 * 外访区域 城市 Service实现类
 * @author wcy
 * @date 2016年6月23日11:13:32
 */
@Service("cityService")
public class CityServiceImpl extends AbstractService<CityDto, String> implements CityService {

	@Autowired
	private CityMapper cityMapper;
	
	/** 后台验证方法，主要判断外访区域是否重复 */
	private void prepareSaveOrUpdate(CityModel model){
		if(StringUtils.isBlank(model.getCityName())){
			throw new ServiceException("外访区域省份名称不能为空。");
		}
		List<CityModel> size= cityMapper.sizeNum(model);
		if(size.size()>=1){
			throw new ServiceException("["+model.getCityName()+"]外访区域名称重复");
		}
		if(model.getProvinceId()==""){
			throw new ServiceException("请选择所属省份");
		}
	}
	
	/** 省份禁用/启用 */
	@Override
	public int updateForStatus(CityModel model) {
		// 正常启用为1
		if (CoreConst.STATUS_NORMAL == model.getStatus()) {
			model.setStatus(0);// 设置状态， 0为禁用
			//禁用为0
		} else if (CoreConst.STATUS_DELETE == model.getStatus()) {
			model.setStatus(1);// 设置状态， 1为启用
		}
		return cityMapper.updateForStatus(model);
	}
	
	/** 新增省份方法 */
	@Override
	public int saveCity(CityModel model) {
		prepareSaveOrUpdate(model);
		model.setCityId(UUIDUtil.UUID32());//随机id
		model.setStatus(CoreConst.STATUS_NORMAL);//启用状态
		model.setProvinceId(model.getProvinceId());
		return cityMapper.saveCity(model);
	}
	
	/** 编辑省份方法 */
	@Override
	public int updateCity(CityModel model) {
		model.setProvinceId(model.getProvinceId());
 		return  cityMapper.updateCity(model);
	}

	@Override
	public List<CityModel> findAllCity() {
		return cityMapper.findAllCity();
	}
}
