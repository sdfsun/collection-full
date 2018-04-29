package com.kangde.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kangde.collection.mapper.AreaMapper;
import com.kangde.collection.model.AreaModel;
import com.kangde.commons.CoreConst;
import com.kangde.commons.easyui.EasyUITreeNode;
import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.util.PinyinUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.OrganizationMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.OrganizationModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.OrganizationService;

/**
 * 织机机构 Service实现类
 * @author lisuo
 *
 */
@Service("organizationService")
@CacheConfig(cacheNames = "organizationCache")
public class OrganizationServiceImpl extends AbstractService<OrganizationModel,String> implements OrganizationService{

	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private AreaMapper areaMapper;
	
	
	/**
	 * 保存或新增初始化
	 * @param model
	 */
	private void initParent(OrganizationModel model){
		//父节点ID无效
		if (StringUtils.isBlank(model.getParentId())) {
			model.setParentId(null);
		}
	}
	
	/**
	 * 初始化区域
	 * @param model
	 */
	private void initAreas(OrganizationModel model,boolean isUpdate){
		Map<String,Object> params = new HashMap<>(2);
		if(isUpdate){
			//如果是更新操作,找出之前的机构所属区域,把区域关系设置为null
			List<AreaModel> areas = areaMapper.findByOrganizationId(model.getId());
			if(CollectionUtils.isNotEmpty(areas)){
				List<String> list = new ArrayList<>(areas.size());
				for(AreaModel area:areas){
					list.add(area.getId());
				} 
				params.put("areaIds", list);
				params.put("organizationId", null);
				areaMapper.updateOrganization(params);
			}
		}
		if(StringUtils.isNotBlank(model.getAreaIds())){
			String[] areaId = StringUtils.split(model.getAreaIds(),",");
			params.put("areaIds", Arrays.asList(areaId));
			params.put("organizationId", model.getId());
			areaMapper.updateOrganization(params);
		}
	}
	
	/**
	 * 初始化保存或新增逻辑,主要用于设置机构路径
	 * @param model
	 */
	private void initSaveOrUpdate(OrganizationModel model){
		//机构名称校验
		if(super.hasDuplicate(model.getId(), "name", model.getName())){
			throw new ServiceException("机构名称["+model.getName()+"]已存在");
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
			OrganizationModel p = findById(model.getParentId());
			if(p==null){
				throw new ServiceException("指定的上级机构["+model.getParentId()+"]不存在");
			}
			StringBuilder path = new StringBuilder(p.getPath());
			if(!path.toString().endsWith("/")){
				path.append("/");
			}
			path.append(model.getId());
			model.setPath(path.toString());
		}
		String code = PinyinUtil.spell(model.getName(), true,true);
		model.setCode(code);
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public OrganizationModel save(OrganizationModel model) {
		initParent(model);
		initSaveOrUpdate(model);
		organizationMapper.save(model);
		initAreas(model, false);
		return model;
	}
	
	@CacheEvict(allEntries=true)
	@Override
	public OrganizationModel update(OrganizationModel model) {
		initParent(model);
		initSaveOrUpdate(model);
		super.update(model);
		initAreas(model, true);
		return model;
	}
	
	public List<TreeNode> getOrganizationTreeNoCache(String excludeOrgId,EmployeeInfoModel employee){
		List<TreeNode> nodes = Lists.newArrayList();
		List<OrganizationModel> organizations = null;
		//判断用户是否是超级管理员
		if(SecurityUtil.isSuperAdmin(employee)){
			//查询顶级组织机构
			organizations = organizationMapper.findTops(null);
		}else{
			//根据当前用户信息查询机构
			OrganizationModel root = super.findById(employee.getOrgId());
			OrganizationModel root1=null;
			organizations = Lists.newArrayList(root);
			//根据当前j
			//附加机构的也查询
			String attachOrgId = employee.getAttachOrgId();
			if(StringUtils.isNotBlank(attachOrgId)){
				//判断是多选还是单选附加机构
				if(attachOrgId.contains(",")){
					//如果组织机构的id不是康德集团就去添加附加机构（如果是康德集团的话可以省去次步奏，因为康德集团包括所有的机构）
					if(!employee.getOrgId().equals("O20100000-1")){
						//添加附加机构（可能是多个附加机构）
						  String[] as = attachOrgId.split(",");
						  for (int i = 0; i < as.length; i++) {
							  root1= super.findById(as[i]);
							  organizations.add(root1);
						  }
					}
				}else{
					 root1= super.findById(attachOrgId);
					 organizations.add(root1);
				}
			}
		}
		for (OrganizationModel org : organizations) {
			TreeNode node = this.organizationToTreeNode(org,excludeOrgId,true);
			if (node != null) {
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	
	@Override
	public List<TreeNode> getOrganizationTree(String excludeOrgId,EmployeeInfoModel employee) {
		return getOrganizationTreeNoCache(excludeOrgId,employee);
	}
	
	@Cacheable
	@Override
	public List<TreeNode> getOrganizationTreeJoinAttachedOrgs(EmployeeInfoModel employee) {
		//1.获取当前用户的机构列表
		List<TreeNode> treeNode = getOrganizationTreeNoCache(null,employee);
		//没有附加区域,返回用户的机构列表
		if(StringUtils.isBlank(employee.getAttachOrgId())){
			return treeNode;
		}
		List<TreeNode> mergeList = new ArrayList<>();
		//2.获取用户附加的机构列表
		List<TreeNode> nodes = Lists.newArrayList();
		String[] attchOrgs = StringUtils.split(employee.getAttachOrgId(), ",");
		if(ArrayUtils.isNotEmpty(attchOrgs)){
			for(String orgId:attchOrgs){
				OrganizationModel root = super.findById(orgId);
				TreeNode node = this.organizationToTreeNode(root,null,true);
				if (node != null) {
					if(!CoreUtil.treeContains(nodes, node.getId())){
						nodes.add(node);
					}
				}
			}
		}
		mergeList.addAll(treeNode);
		mergeList.addAll(nodes);
		//3.合并1，2步骤
		return mergeList;
	}
	
	/**
	 * 织机机构 
	 * @param org 组织信息
	 * @param excludeOrgId 排除的ID
	 * @param isCascade 是否级联,列出下级所有组织机构
	 * @return
	 */
	private TreeNode organizationToTreeNode(OrganizationModel org,String excludeOrgId,boolean isCascade) {
		if(org==null){
			return null;
		}
		//排除orgId
		if(excludeOrgId!=null && StringUtils.isNotBlank(excludeOrgId.toString())){
			if(excludeOrgId.equals(org.getId())){
				return null;
			}
		}
		TreeNode treeNode = new TreeNode(org.getId(), org.getName(), null);
		if (isCascade) {
			// 递归查询子节点
			List<TreeNode> childrenTreeNodes = Lists.newArrayList();
			//查询子集节点
			List<OrganizationModel> children = organizationMapper.findByParentId(org.getId());
			for (OrganizationModel o : children) {
				TreeNode node = organizationToTreeNode(o, excludeOrgId, isCascade);
				if (node != null) {
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
		List<OrganizationModel> children = organizationMapper.findByParentId(id);
		if (CollectionUtils.isNotEmpty(children)) {
			for (OrganizationModel org : children) {
				deleteById(org.getId());
			}
		}
		super.deleteById(id);
	}

	@Override
	public Integer findMaxSort() {
		return organizationMapper.findMaxSort();
	}

	@Cacheable
	@Override
	public List<EasyUITreeNode> getOrganizationList(EmployeeInfoModel currentUser) {
		//1.获取当前用户的机构列表
				//Set<OrganizationModel> orgs =Sets.newHashSet();
				//判断用户是否是超级管理员
//				if(SecurityUtil.isSuperAdmin(currentUser)){
//					//查询顶级组织机构
//					orgs.addAll(organizationMapper.findTops(null)) ;
//				}else{
//					//根据当前用户信息查询机构
//					OrganizationModel org = super.findById(currentUser.getOrgId());
//					String path=org.getPath();
//					orgs.add(org);
//					
//					while(StringUtils.isNotBlank(org.get_parentId())){
//						org = super.findById(org.get_parentId());
//						orgs.add(org);
//					}
//					
//					List<OrganizationModel> descendants = organizationMapper.findDescendantsByPath(path);
//					if(CollectionUtils.isNotEmpty(descendants)){
//						orgs.addAll(descendants);
//					}
//					String attachOrgId = currentUser.getAttachOrgId();
//					if(StringUtils.isNotBlank(attachOrgId)){
//						//判断是多选还是单选附加机构
//						if(attachOrgId.contains(",")){
//							//如果组织机构的id不是康德集团就去添加附加机构（如果是康德集团的话可以省去次步奏，因为康德集团包括所有的机构）
//							if(!currentUser.getOrgId().equals("O20100000-1")){
//								//添加附加机构（可能是多个附加机构）
//								  String[] as = attachOrgId.split(",");
//								  for (int i = 0; i < as.length; i++) {
//									  OrganizationModel findById = super.findById(as[i]);
//									orgs.add(findById);
//								  }
//							}
//						}else{
//							 OrganizationModel item = super.findById(attachOrgId);
//							 if(item!=null){
//								 orgs.add(item);
//							 }
//						}
//					}
//				}
//				
				List<OrganizationModel> list=this.findAll();
				List<EasyUITreeNode> nodes=Lists.newArrayList();
				if(CollectionUtils.isNotEmpty(list)){
					for (OrganizationModel org : list) {
						EasyUITreeNode treeNode = new EasyUITreeNode(org.getId(), org.getName(), org.get_parentId());
						nodes.add(treeNode);
					}
				}
				return nodes;
			
	}
	
}
