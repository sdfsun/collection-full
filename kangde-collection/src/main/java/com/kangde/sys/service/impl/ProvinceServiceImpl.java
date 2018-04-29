package com.kangde.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.ProvinceMapper;
import com.kangde.sys.model.ProvinceModel;
import com.kangde.sys.service.ProvinceService;

/**
 * 外访区域 Service实现类
 * @author wcy
 * @date 2016年6月23日11:13:32
 */
@Service("provinceService")
public class ProvinceServiceImpl extends AbstractService<ProvinceModel, String> implements ProvinceService {
	
	@Autowired
	private ProvinceMapper provinceMapper;

	/** 后台验证方法，主要判断外访区域是否重复 */
	private void prepareSaveOrUpdate(ProvinceModel model){
		if(StringUtils.isBlank(model.getProvinceName())){
			throw new ServiceException("外访区域名称不能为空。");
		}
		List<ProvinceModel> size= provinceMapper.sizeNum(model);
		if(size.size()>=1){
			throw new ServiceException("["+model.getProvinceName()+"]外访区域名称重复");
		}
	}
	
	/** 省份禁用/启用 */
	@Override
	public int updateForStatus(ProvinceModel model) {
		// 正常启用为1
		if (CoreConst.STATUS_NORMAL == model.getStatus()) {
			model.setStatus(0);// 设置状态， 0为禁用
			//禁用为0
		} else if (CoreConst.STATUS_DELETE == model.getStatus()) {
			model.setStatus(1);// 设置状态， 1为启用
		}
		return provinceMapper.updateForStatus(model);
	}
	
	/** 新增省份方法 */
	@Override
	public int saveProv(ProvinceModel model) {
		prepareSaveOrUpdate(model);
		model.setProvinceId(UUIDUtil.UUID32());//随机id
		model.setStatus(CoreConst.STATUS_NORMAL);//启用状态
		return provinceMapper.saveProv(model);
	}
	
	/** 编辑省份方法 */
	@Override
	public int updateProv(ProvinceModel model) {
		prepareSaveOrUpdate(model);
 		return  provinceMapper.updateProv(model);
	}

}
