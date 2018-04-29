package com.kangde.sys.service.impl;




import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.dto.AreaDto;
import com.kangde.sys.mapper.SysAreaMapper;
import com.kangde.sys.model.AreaModel;
import com.kangde.sys.service.SysAreaService;

/**
 * 外访区域 城市 Service实现类
 * @author wcy
 * @date 2016年6月23日11:13:32
 */
@Service("sysAreaService")
public class SysAreaServiceImpl extends AbstractService<AreaDto, String> implements SysAreaService {

	@Autowired
	private SysAreaMapper sysAreaMapper;
	
	/** 后台验证方法，主要判断外访区域是否重复 */
	private void prepareSaveOrUpdate(AreaModel model){
		if(StringUtils.isBlank(model.getAreaName())){
			throw new ServiceException("外访区域名称不能为空。");
		}
		List<AreaModel> size= sysAreaMapper.sizeNum(model);
		if(size.size()>=1){
			throw new ServiceException("["+model.getAreaName()+"]外访区域名称重复");
		}
		
	}
	
	/** 省份禁用/启用 */
	@Override
	public int updateForStatus(AreaModel model) {
		// 正常启用为1
		if (CoreConst.STATUS_NORMAL == model.getStatus()) {
			model.setStatus(0);// 设置状态， 0为禁用
			//禁用为0
		} else if (CoreConst.STATUS_DELETE == model.getStatus()) {
			model.setStatus(1);// 设置状态， 1为启用
		}
		return sysAreaMapper.updateForStatus(model);
	}
	
	/** 新增省份方法 */
	@Override
	public int saveArea(AreaModel model) {
		prepareSaveOrUpdate(model);
		model.setAreaId(UUIDUtil.UUID32());//随机id
		model.setStatus(CoreConst.STATUS_NORMAL);//启用状态
		model.setCityId(model.getCityId());
		return sysAreaMapper.saveArea(model);
	}
	
	/** 编辑省份方法 */
	@Override
	public int updateArea(AreaModel model) {
		model.setCityId(model.getCityId());
 		return  sysAreaMapper.updateArea(model);
	}


}
