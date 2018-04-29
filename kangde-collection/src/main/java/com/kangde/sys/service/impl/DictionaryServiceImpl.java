package com.kangde.sys.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.mapper.DictionaryMapper;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;

/**
 * 字典 Service实现类
 * @author lisuo
 *
 */
@Service("dictionaryService")
@CacheConfig(cacheNames = "dictionaryCache")
public class DictionaryServiceImpl extends AbstractService<DictionaryModel,String> implements DictionaryService{

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	/**
	 * 初始化父节点ID和名称
	 * @param model
	 */
	private void initModel(DictionaryModel model){
		if(StringUtils.isBlank(model.getDictKey())){
			throw new ServiceException("字典键不能为空");
		}
		//去除前后空格
		ReflectUtil.trimFields(model, "name","dictKey","value","remark");
		//父节点ID无效
		if (StringUtils.isBlank(model.getParentId())) {
			model.setParentId(null);
			//检查键是否重复
			if(hasDuplicate(model.getId(), "path", model.getDictKey())){
				throw new ServiceException("字典键重复,同一级字典的键不能重复");
			}
			//设置字典路径
			model.setPath(model.getDictKey());
		}else{
			//有父节点处理
			DictionaryModel parent = super.findById(model.getParentId());
			if(parent==null){
				throw new ServiceException("指定的上级字典["+model.getParentId()+"]不存在");
			}
			String parentPath = parent.getPath();
			if(!parentPath.endsWith("/")){
				parentPath = parentPath+"/";
			}
			//生成路径
			String path = parentPath+model.getDictKey();
			//检查当前路径是否重复
			if(hasDuplicate(model.getId(), "path", path)){
				throw new ServiceException("字典键重复,同一级字典的键不能重复");
			}
			model.setPath(path);
		}
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public DictionaryModel save(DictionaryModel model) {
		initModel(model);
		return super.save(model);
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public DictionaryModel update(DictionaryModel model) {
		//从数据库检索出之前的数据
		DictionaryModel oldModel = super.findById(model.getId());
		if(oldModel==null){
			throw new ServiceException("更新["+model.getName()+"]字典不存在");
		}
		initModel(model);
		if(!oldModel.getDictKey().equals(model.getDictKey())){
			//字典Key有变更,通过以前的路径查询出之前所属所有子节点,更新新的路径规则
			List<DictionaryModel> childrens = findByField("path", oldModel.getPath()+"/%","like");
			if(CollectionUtils.isNotEmpty(childrens)){
				for(DictionaryModel dict:childrens){
					String newPath = dict.getPath().replace(oldModel.getPath(), model.getPath());
					dict.setPath(newPath);
					//更新子节点路径
					super.update(dict);
				}
			}
		}
		return super.update(model);
	}
	
	@Cacheable
	@Override
	public DictionaryModel findDictByPath(String path){
		Assert.hasText(path, "必须指定字典路径");
		if(path.endsWith("/")){
			path = path.substring(0, path.length()-1);
		}
		List<DictionaryModel> dicts = findByField("path", path);
		if(CollectionUtils.isNotEmpty(dicts)){
			return dicts.get(0);
		}
		return null;
	}
	
	@Cacheable
	@SuppressWarnings("unchecked")
	@Override
	public List<DictionaryModel> findSubsByPath(String path){
		DictionaryModel dict = findDictByPath(path);
		if(dict!=null){
			return findByField("parent_id", dict.getId());
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public List<TreeNode> getDictionaryTree(String excludeOrgId,boolean excludeChildren) {
		List<TreeNode> nodes = Lists.newArrayList();
		//查询顶级字典
		List<DictionaryModel> dictionarys = dictionaryMapper.findTops(null);
		for (DictionaryModel dict : dictionarys) {
			TreeNode node = this.dictionaryToTreeNode(dict,excludeOrgId,true);
			if (node != null) {
				nodes.add(node);
			}
		}
		if(excludeChildren){
			if(CollectionUtils.isNotEmpty(nodes)){
				Iterator<TreeNode> it = nodes.iterator();
				while(it.hasNext()){
					TreeNode node = it.next();
					removeEmptyNode(node,it);
				}
			}
		}
		return nodes;
	}
	
	//移除不包含子集的节点
	private void removeEmptyNode(TreeNode node,Iterator<TreeNode> parents){
		if(node!=null){
			List<TreeNode> children = node.getChildren();
			if(CollectionUtils.isNotEmpty(children)){
				Iterator<TreeNode> it = children.iterator();
				while(it.hasNext()){
					TreeNode n = it.next();
					removeEmptyNode(n,it);
				}
			}else{
				if(StringUtils.isNotBlank(node.getpId())){
					parents.remove();
				}
			}
		}
	}
	

	/**
	 * 字典转TreeNode 
	 * @param dict 字典信息
	 * @param excludeId 排除的ID
	 * @param isCascade 是否级联,列出下级所有字典
	 * @return
	 */
	private TreeNode dictionaryToTreeNode(DictionaryModel dict,String excludeId,boolean isCascade) {
		//排除字典ID
		if(excludeId!=null && StringUtils.isNotBlank(excludeId.toString())){
			if(excludeId.equals(dict.getId())){
				return null;
			}
		}
		TreeNode treeNode = new TreeNode(dict.getId(), dict.getName(), null);
		Map<String,Object> attributes = new HashMap<>(2);
		attributes.put("path", dict.getPath());
		attributes.put("dictKey", dict.getDictKey());
		treeNode.setAttributes(attributes);
		if (isCascade) {
			// 递归查询子节点
			List<TreeNode> childrenTreeNodes = Lists.newArrayList();
			//查询子集节点
			List<DictionaryModel> children = dictionaryMapper.findByParentId(dict.getId());
			for (DictionaryModel o : children) {
				TreeNode node = dictionaryToTreeNode(o, excludeId, isCascade);
				if (node != null) {
					node.setpId(dict.getId());
					childrenTreeNodes.add(node);
				}
			}
			treeNode.setChildren(childrenTreeNodes);
		}
		return treeNode;
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public void deleteById(String id) {
		//级联递归删除
		List<DictionaryModel> children = dictionaryMapper.findByParentId(id);
		if (CollectionUtils.isNotEmpty(children)) {
			for (DictionaryModel org : children) {
				deleteById(org.getId());
			}
		}
		super.deleteById(id);
	}

	@Override
	public Integer findMaxSort() {
		return dictionaryMapper.findMaxSort();
	}
	
	@Override
	public List<DictionaryModel> query(ParamCondition condition) {
		return super.query(condition);
	}

}
