package com.kangde.collection.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.collection.mapper.AreaMapper;
import com.kangde.collection.model.AreaModel;
import com.kangde.collection.service.AreaService;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.sys.mapper.OrganizationMapper;
import com.kangde.sys.model.OrganizationModel;

/**
 * 区域service实现类
 * 
 * @author lisuo
 *
 */
@Service("areaService")
public class AreaServiceImpl extends AbstractService<AreaModel, String> implements AreaService {

	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private OrganizationMapper organizationMapper;

	private void prepareSaveOrUpdate(AreaModel model) {
		if (StringUtils.isBlank(model.getName())) {
			throw new ServiceException("区域名称不能为空");
		}
		List<AreaModel> areas = super.findByField("name", model.getName());
		if (CollectionUtils.isNotEmpty(areas)) {
			throw new ServiceException("[" + model.getName() + "]区域名称重复");
		}
	}

	@Override
	public List<AreaModel> findAreas(List<String> includeIds) {
		return areaMapper.findAreas(includeIds);
	}

	/** 新增催收区域 */
	@Override
	public AreaModel save(AreaModel model) {
		prepareSaveOrUpdate(model);
		super.save(model);
		return model;
	}

	@Override
	public AreaModel update(AreaModel model) {
		AreaModel size = areaMapper.findSize(model);
		List<AreaModel> size1 = areaMapper.findSize1(model.getName());
		if (size.getName().equals(model.getName())) {// 说明没有修改催收区域的名字，只改了状态
			if (size1.size() >= 2) {
				throw new ServiceException("[" + model.getName() + "]区域重复");
			}
		} else if (!size.getName().equals(model.getName())) {// 说明改了名字
			if (size1.size() >= 1) {
				throw new ServiceException("[" + model.getName() + "]区域重复");
			}
		}
		return super.update(model);
	}

	@Override
	public List<AreaModel> findByOrganizationId(String orgId) {
		return areaMapper.findByOrganizationId(orgId);
	}

	@Override
	public List<String> queryAreaByOrgId(String orgId) {
		List<AreaModel> list = null;
		OrganizationModel org = organizationMapper.findById(orgId);
		if (org != null && org.getParentId() == null) { // 集团用户
			/** 查询集团下属所有分公司所有催收区域 */
			list = this.findAllArea(org.getId());
		} else {

			while (org != null && org.getType() != null && 2 != org.getType()) {
				org = organizationMapper.findById(org.getParentId());
			}
			if (org != null) {
				list = this.findByOrganizationId(org.getId());
			}
		}
		List<String> ids = Lists.newArrayList("");
		if (CollectionUtils.isNotEmpty(list)) {
			for (AreaModel model : list) {
				ids.add(model.getId());
			}
		}
		return ids;
	}

	@Override
	public List<AreaModel> findAllArea(String id) {
		return areaMapper.findAllArea(id);
	}
}
