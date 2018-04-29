package com.kangde.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.ResourceMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.ResourceModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.ResourceService;

/**
 * 资源菜单Service实现类
 * 
 * @author lisuo
 *
 */
@Service("resourceService")
@CacheConfig(cacheNames = "resourceCache")
public class ResourceServiceImpl extends AbstractService<ResourceModel,String> implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;

	private void parepareSaveOrUpdate(ResourceModel model){
		//父节点ID无效
		if (StringUtils.isBlank(model.getParentId())) {
			model.setParentId(null);
		}else{
			if(model.getParentId().equals(model.getId())){
				throw new ServiceException("操作错误,上级资源与当前资源不能一样");
			}
		}
		if(StringUtils.isBlank(model.getCode())){
			model.setCode(null);
		}
		if(StringUtils.isBlank(model.getUrl())){
			model.setUrl(null);
		}
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public ResourceModel save(ResourceModel model) {
		parepareSaveOrUpdate(model);
		model.setId(UUIDUtil.UUID32());
		model.setCreateTime(new Date());
		model.setStatus(model.getStatus());
		model.setVersion(0);
		resourceMapper.save(model);
		return model;
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public ResourceModel update(ResourceModel model) {
		parepareSaveOrUpdate(model);
		return super.update(model);
	}
	
	/**
	 * 查询顶级资源
	 * @param userId 用户ID
	 * @param type 资源类型
	 * @return 
	 */
	private List<ResourceModel> findTops(EmployeeInfoModel currentUser,Integer type){
		List<ResourceModel> resources = null;
		//超级管理员用户
		if(SecurityUtil.isSuperAdmin(currentUser)){
			//查询所有,仅供超级管理员使用
			resources = resourceMapper.findTops(type);
		}else{
			//普通用户
			Map<String,Object> params = new HashMap<>(2);
			params.put("type", type);
			params.put("userId", currentUser.getId());
			resources = resourceMapper.findTopsByUser(params);
		}
		return resources;
	}
	
	//用户登录初始化菜单入口
	@Cacheable
	public List<TreeNode> findMenus(EmployeeInfoModel currentUser) {
		Integer type = ResourceModel.TYPE_MENU;
		List<TreeNode> nodes = Lists.newArrayList();
		List<ResourceModel> resources = this.findTops(currentUser,type);
		if(CollectionUtils.isNotEmpty(resources)){
			for(ResourceModel resource:resources){
				TreeNode node = resourceToTreeNode(resource, type, true, null, currentUser.getId());
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	/**
	 * 通过父节点,查询子节点
	 * 
	 * @param parentId
	 * @param resourceType
	 * @return
	 */
	private List<ResourceModel> findByParentId(String parentId, Integer resourceType,String userId) {
		Map<String,Object> params = new HashMap<>(3);
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		if(userId ==null || SecurityUtil.isSuperAdmin(currentUser)){
			params.put("parentId", parentId);
			params.put("type", resourceType);
			return resourceMapper.findParent(params);
		}else{
			params.put("parentId", parentId);
			params.put("userId", userId);
			params.put("type", resourceType);
			return resourceMapper.findParentByUser(params);
		}
	}

	/**
	 * 资源转换树节点模型
	 * @param resource 资源类型
	 * @return TreeNode
	 */
	private TreeNode resourceToTreeNode(ResourceModel resource){
		TreeNode treeNode = new TreeNode(resource.getId(), resource.getName(), resource.getIconCls());
		// 自定义属性 url
		Map<String, Object> attributes = new HashMap<>(3);
		attributes.put("url", resource.getUrl());
		attributes.put("code", resource.getCode());
		attributes.put("type", resource.getType());
		treeNode.setAttributes(attributes);
		return treeNode;
	}
	
	/**
	 * ResourceModel转TreeNode
	 * @param resource 资源
	 * @param resourceType 资源类型
	 * @param isCascade 是否级联,列出下级所有资源
	 * @param resourceId  排除的资源节点ID
	 * @param userId 用户ID
	 * @return
	 */
	private TreeNode resourceToTreeNode(ResourceModel resource, Integer resourceType, boolean isCascade,
			String resourceId,String userId) {
		if (resourceType != null) {
			if (!resourceType.equals(resource.getType())) {
				return null;
			}
		}
		//排除resourceId
		if(resourceId!=null && StringUtils.isNotBlank(resourceId.toString())){
			if(resourceId.equals(resource.getId())){
				return null;
			}
		}
		TreeNode treeNode = resourceToTreeNode(resource);
		if (isCascade) {
			List<TreeNode> childrenTreeNodes = Lists.newArrayList();
			// 查询子节点
			List<ResourceModel> children = this.findByParentId(resource.getId(), resourceType,userId);
			if(CollectionUtils.isNotEmpty(children)){
				for (ResourceModel subResource : children) {
					TreeNode node = resourceToTreeNode(subResource, resourceType, isCascade, resourceId,userId);
					if (node != null) {
						childrenTreeNodes.add(node);
					}
				}
				treeNode.setChildren(childrenTreeNodes);
				treeNode.setState(TreeNode.STATE_OPEN);
			}
		}
		return treeNode;
	}
	
	@Cacheable
	@Override
	public List<TreeNode> findResourcesToTreeNodeByUser(String resourceId,EmployeeInfoModel currentUser) {
		Integer type = null;
		List<ResourceModel> resources = this.findTops(currentUser, type);
		List<TreeNode> nodes = Lists.newArrayList();
		//限制资源类型
		Integer menu = null;
		for (ResourceModel resource : resources) {
			TreeNode node = this.resourceToTreeNode(resource, menu, true, resourceId,currentUser.getId());
			if (node != null) {
				nodes.add(node);
			}
		}
		return nodes;
	}

	/**
	 * 递归删除所有节点,不区分类型
	 */
	@CacheEvict(allEntries=true)
	@Override
	public void deleteById(String id) {
		// 根节点
		List<ResourceModel> children = findByParentId(id, null,null);
		if (CollectionUtils.isNotEmpty(children)) {
			for (ResourceModel resource : children) {
				deleteById(resource.getId());
			}
		}
		super.deleteById(id);
	}

	@Override
	public Integer getMaxSort() {
		return resourceMapper.findMaxSort();
	}
	
	@Cacheable
	@Override
	public List<ResourceModel> findResourceByUserId(String userId) {
		return resourceMapper.findByUserId(userId);
	}
	
	@Cacheable
	@Override
	public String getTitle(String field,String value){
		List<String> titles = new ArrayList<>(3);
		List<ResourceModel> list = findByField(field, value);
		ResourceModel one = CoreUtil.getOne(list);
		if(one!=null){
			titles.add(one.getName());
			if(StringUtils.isNotEmpty(one.getParentId())){
				ResourceModel p = findById(one.getParentId());
				while(p!=null){
					titles.add(p.getName());
					p = findById(p.getParentId());
				}
			}
		}
		Collections.reverse(titles);
		if(CollectionUtils.isNotEmpty(titles)){
			StringBuilder temp = new StringBuilder();
			for(String title:titles){
				temp.append(title+"-");
			}
			temp.delete(temp.length()-1,temp.length());
			return temp.toString();
		}
		return null;
	}
	
}
