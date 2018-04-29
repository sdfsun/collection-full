package com.kangde.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.EntrustMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.security.util.MD5Util;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EntrustService;

/**
 * 委托方 Service实现类
 * 
 * @author zhangyj
 */
@Service("enstrustService")
public class EntrustServiceImpl extends AbstractService<EntrustModel, String> implements EntrustService {
	@Autowired
	private EntrustMapper entrustmapper;
	/*
	 * @Override public int updateForState(EntrustModel model) { return
	 * entrustmapper.updateForState(model); }
	 */

	@Override
	public EntrustModel update(EntrustModel model) {
		// 重复校验---name
		String name = model.getName();
		String code = model.getCode();

		EntrustModel entrust = entrustmapper.findByName(name);
		EntrustModel entrust1 = entrustmapper.findByCode(code);
		if (entrust == null && entrust1 == null) {
				super.update(model);
				return model;
			}else if (entrust == null && entrust1 != null) {
				if(!model.getId().equals(entrust1.getId())){
					throw new ServiceException("简码重复");
				}else {
					super.update(model);
					return model;
				}
			}else if (entrust != null && entrust1 == null) {
				if(!model.getId().equals(entrust.getId())){
					throw new ServiceException("委托方重复");
				}else {
					super.update(model);
					return model;
				}
			} else {
				if(!model.getId().equals(entrust.getId())&&!model.getId().equals(entrust1.getId())){
					throw new ServiceException("委托方和简码重复");
				}else if(model.getId().equals(entrust.getId())&&!model.getId().equals(entrust1.getId())){
					throw new ServiceException("简码重复");
				}else if(!model.getId().equals(entrust.getId())&&model.getId().equals(entrust1.getId())){
					throw new ServiceException("委托方重复");
				}else {
					super.update(model);
					return model;
				}
				
			}
	}

	@Override
	public EntrustModel save(EntrustModel model) {
		// 重复校验---name
		String name = model.getName();
		String code = model.getCode();

		EntrustModel entrust = entrustmapper.findByName(name);
		EntrustModel entrust1 = entrustmapper.findByCode(code);
		if (entrust == null && entrust1 == null) {

			String userId = SecurityUtil.getCurrentUser().getId();
			String userName = SecurityUtil.getCurrentUser().getUserName();
			model.setCreateEmpName(userName);
			model.setCreateEmpId(userId);
			if (model.getState() == null) {
				model.setState(0);
			}
			super.save(model);
			return model;
		} else if (entrust == null && entrust1 != null) {
			throw new ServiceException("简码重复");
		} else if (entrust != null && entrust1 == null) {
			throw new ServiceException("委托方重复");
		} else{
			throw new ServiceException("委托方和简码重复");
		}
	}

	@CacheEvict(allEntries = true, cacheNames = { "organizationCache" })
	@Override
	public int updateForStatus(String[] ids) {
		EntrustModel model = new EntrustModel();
		model.setState(0);
		model.setModifyTime(new Date());

		Map<String, Object> params = new HashMap<>(2);
		params.put("entrust", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return entrustmapper.updateForStatus(params);
	}

	@CacheEvict(allEntries = true, cacheNames = { "organizationCache" })
	@Override
	public int updateForStatusNo(String[] ids) {
		EntrustModel model = new EntrustModel();
		model.setState(1);
		model.setModifyTime(new Date());

		Map<String, Object> params = new HashMap<>(2);
		params.put("entrust", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return entrustmapper.updateForStatus(params);

	}

	/*
	 * @Override public EntrustModel save(EntrustModel model) { if
	 * (StringUtils.isBlank(model.getName())) { //后台验证 验证职位名称是否为空 如果是空抛出异常
	 * 前台alert 弹窗提示。 throw new ServiceException("登录名称不能为空");
	 * }if(0==model.getLevelId()){ throw new ServiceException("请选择职级等级"); }
	 * return super.save(model);
	 * 
	 * }
	 */

}
