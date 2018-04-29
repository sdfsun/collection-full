package com.kangde.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.commons.CoreConst;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.PositionMapper;
import com.kangde.sys.model.PositionModel;
import com.kangde.sys.service.PositionService;

/**
 *  Service实现类
 * @author lisuo
 *
 */
@Service("positionService")
//@CacheConfig(cacheNames = "positionCache")
public class PositionServiceImpl extends AbstractService<PositionModel,String> implements PositionService {

	@Autowired
	private PositionMapper positionMapper;
	
	
	/**
	 * 初始化保存或新增逻辑,主要用于设置路径
	 * @param model
	 */
	private void initSaveOrUpdate(PositionModel model){
		//父节点ID无效
		if (StringUtils.isBlank(model.getParentId())) {
			model.setParentId(null);
		}
		//机构名称校验
		if(super.hasDuplicate(model.getId(), "name", model.getName())){
			throw new ServiceException("名称["+model.getName()+"]已存在");
		}		
		if(StringUtils.isBlank(model.getId())){
			//新增:初始化ID
			model.setId(UUIDUtil.UUID32());//ID生成
			model.setCreateTime(new Date());//创建时间
			model.setStatus(CoreConst.STATUS_NORMAL);//系统状态,默认正常
			model.setVersion(0);
		}
		if(StringUtils.isBlank(model.getParentId())){
			model.setPath(model.getId());
		}else{
			PositionModel p = findById(model.getParentId());
			if(p==null){
				throw new ServiceException("指定的上级["+model.getParentId()+"]不存在");
			}
			StringBuilder path = new StringBuilder(p.getPath());
			if(!path.toString().endsWith("/")){
				path.append("/");
			}
			path.append(model.getId());
			model.setPath(path.toString());
		}
		
	}
	
	//@CacheEvict(allEntries=true)
	@Override
	public PositionModel save(PositionModel model) {
		initSaveOrUpdate(model);
		positionMapper.save(model);
		return model;
	}
	
	//@CacheEvict(allEntries=true)
	@Override
	public PositionModel update(PositionModel model) {
		initSaveOrUpdate(model);
		super.update(model);
		return model;
	}
	
	//@Cacheable
	@Override
	public List<TreeNode> getPositionTree(String excludePositionId) {
		List<TreeNode> nodes = Lists.newArrayList();
		List<PositionModel> organizations = positionMapper.findTops();
		for (PositionModel org : organizations) {
			TreeNode node = this.organizationToTreeNode(org,excludePositionId,true);
			if (node != null) {
				nodes.add(node);
			}
		}
		return nodes;
	}
	

	/**
	 * 织机机构 
	 * @param org 组织信息
	 * @param excludePositionId 排除的ID
	 * @param isCascade 是否级联,列出下级所有组织机构
	 * @return
	 */
	private TreeNode organizationToTreeNode(PositionModel org,String excludePositionId,boolean isCascade) {
		//排除orgId
		if(excludePositionId!=null && StringUtils.isNotBlank(excludePositionId.toString())){
			if(excludePositionId.equals(org.getId())){
				return null;
			}
		}
		TreeNode treeNode = new TreeNode(org.getId(), org.getName(), null);
		if (isCascade) {
			// 递归查询子节点
			List<TreeNode> childrenTreeNodes = Lists.newArrayList();
			//查询子集节点
			List<PositionModel> children = positionMapper.findByParentId(org.getId());
			for (PositionModel o : children) {
				TreeNode node = organizationToTreeNode(o, excludePositionId, isCascade);
				if (node != null) {
					childrenTreeNodes.add(node);
				}
			}
			treeNode.setChildren(childrenTreeNodes);
		}
		
		return treeNode;
	}
	
	//@CacheEvict(allEntries=true)
	@Override
	public void deleteById(String id) {
		//级联递归删除
		List<PositionModel> children = positionMapper.findByParentId(id);
		if (CollectionUtils.isNotEmpty(children)) {
			for (PositionModel org : children) {
				deleteById(org.getId());
			}
		}
		super.deleteById(id);
	}

	@Override
	public Integer findMaxSort() {
		return positionMapper.findMaxSort();
	}
	


}
