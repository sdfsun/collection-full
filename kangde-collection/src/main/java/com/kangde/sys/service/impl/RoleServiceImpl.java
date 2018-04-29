package com.kangde.sys.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.dto.RoleResourceRelationDto;
import com.kangde.sys.mapper.RoleMapper;
import com.kangde.sys.model.OrganizationModel;
import com.kangde.sys.model.RoleModel;
import com.kangde.sys.service.RoleService;

/**
 * 角色Service实现类
 * @author lisuo
 *
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractService<RoleModel,String> implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	private void prepareSaveOrUpdate(RoleModel model){
		if(StringUtils.isBlank(model.getName())){
			throw new ServiceException("角色名称不能为空");
		}
		//角色名称校验
		if(super.hasDuplicate(model.getId(), "name", model.getName())){
			throw new ServiceException("角色名称["+model.getName()+"]已存在");
		}	
		/*if(StringUtils.isNotBlank(model.getRoleCode())){
			//角色名称校验
			if(super.hasDuplicate(model.getId(), "role_code", model.getRoleCode())){
				throw new ServiceException("角色编码["+model.getRoleCode()+"]已存在");
			}
		}*/
	}
	
	@CacheEvict(allEntries=true,cacheNames={"resourceCache"})
	@Override
	public RoleModel save(RoleModel model) {
		prepareSaveOrUpdate(model);
		super.save(model);
		List<String> resourceIds = model.getResourceIds();
		if(CollectionUtils.isNotEmpty(resourceIds)){
			RoleResourceRelationDto dto = null;
			//增加资源关联关系
			for (String resourceId:resourceIds) {
				dto = new RoleResourceRelationDto(model.getId(), resourceId);
				roleMapper.saveRoleResourceRelation(dto);
			}
		}
		return model;
	}
	
	@CacheEvict(allEntries=true,cacheNames={"resourceCache"})
	@Override
	public RoleModel update(RoleModel model) {
		prepareSaveOrUpdate(model);
		//先删除所有关系
		roleMapper.deleteRoleResourceRelation(model.getId());
		List<String> resourceIds = model.getResourceIds();
		if(CollectionUtils.isNotEmpty(resourceIds)){
			RoleResourceRelationDto dto = null;
			//增加资源关联关系
			for (String resourceId:resourceIds) {
				dto = new RoleResourceRelationDto(model.getId(), resourceId);
				roleMapper.saveRoleResourceRelation(dto);
			}
		}
		return super.update(model);
	}
	
	@CacheEvict(allEntries=true,cacheNames={"resourceCache"})
	@Override
	public void deleteById(String id) {
		this.deleteByIds(Arrays.asList(id));
	}
	
	@CacheEvict(allEntries=true,cacheNames={"resourceCache"})
	@Override
	public void deleteByIds(List<String> ids) {
		if(CollectionUtils.isNotEmpty(ids)){
			for(String roleId:ids){
				roleMapper.deleteRoleResourceRelation(roleId);
			}
			super.deleteByIds(ids);
		}
	}
	
	@Override
	public List<String> findResourceIdsByRoleId(String roleId){
		return roleMapper.findResourceIdsByRoleId(roleId);
	}
	
	@Cacheable(cacheNames="organizationCache")
	@Override
	public List<RoleModel> findRolesByUserId(String userId){
		return roleMapper.findRolesByUserId(userId);
	}
	
	
	/**
	 * 获取角色编码
	 * @param userId：用户id  condition：过滤条件
	 */
	@Override
	public ParamCondition getOrgCode(String userId,ParamCondition condition){
		//根据用户id 获取角色信息,参看该用户的角色编码是否有查看退案案件的资格， 如果有给过滤条件添加退案的条件，如果没有直接返回过滤条件
		List<RoleModel> roleId=roleMapper.findRolesByUserId(userId);
		//没有角色（比如root）
		if(roleId.size()!=0){
		//拿到角色编码
		List<RoleModel> list=roleMapper.getRolCode(roleId);
		for (int i = 0; i < list.size(); i++) {
			if(StringUtils.isNotBlank(list.get(i).getRoleCode())){
				if("ta".equals(list.get(i).getRoleCode())){
					 condition.put("caseState", 3);
					 return condition;
				}else{
					//如果资源编码有值，不是ta   那就给caseState 设置个null  部门案件查询时候没有办法转换。
					 condition.put("caseState", null);
				}
			}else{
				//角色根本就没有资源编码的时候   也同样设置
				condition.put("caseState", null);
			}
		} 
		}	
		return condition;
	}
	
	
	/**
	 * 获取角色编码
	 * @param userId：用户id  condition：过滤条件
	 */
	@Override
	public boolean getOrgCodeTF(String userId ){
		//根据用户id 获取角色信息,参看该用户的角色编码是否有查看退案案件的资格， 如果有给过滤条件添加退案的条件，如果没有直接返回过滤条件
		List<RoleModel> roleId=roleMapper.findRolesByUserId(userId);
		//没有角色（比如root）
		if(roleId.size()!=0){
		//拿到角色编码
		List<RoleModel> list=roleMapper.getRolCode(roleId);
		for (int i = 0; i < list.size(); i++) {
			if(StringUtils.isNotBlank(list.get(i).getRoleCode())){
				if("ta".equals(list.get(i).getRoleCode())){
					return true;
				}
			}
		}
		}
		return false;
}
}