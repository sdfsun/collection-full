package com.kangde.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.LockUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.util.ValidateUtil;
import com.kangde.sys.dto.UserResourceRelationDto;
import com.kangde.sys.dto.UserRoleRelationDto;
import com.kangde.sys.mapper.EmployeeInfoMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.MD5Util;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EmployeeInfoService;
import com.kangde.sys.service.OrganizationService;

/**
 * 员工信息 Service实现类
 * 
 * @author zhangyj
 * @date 2016.5.5
 */
@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends AbstractService<EmployeeInfoModel,String> implements EmployeeInfoService {

	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	@Autowired
	private OrganizationService organizationService;

	@Override
	public EmployeeInfoModel findByLoginName(String loginName) {
		return employeeInfoMapper.findByLoginName(loginName);
	}

	//保存| 更新前置方法
	private void prepareSaveOrUpdate(EmployeeInfoModel model){
		if(StringUtils.isBlank(model.getOrgId())){
			model.setOrgId(null);
		}
		if(StringUtils.isBlank(model.getPositionId())){
			model.setPositionId(null);
		}
		if (StringUtils.isBlank(model.getLoginName())) {
			throw new ServiceException("登录名称不能为空");
		} else if (SecurityUtil.isSuperAdminLoginName(model.getLoginName()) || !ValidateUtil.validateUserName(model.getLoginName())) {
			throw new ServiceException("不符合规范的登录名");
		}
	}
	
	//保存| 更新后置方法
	private void afterSaveOrUpdate(EmployeeInfoModel model){
		if(StringUtils.isNotBlank(model.getId())){
			if(ArrayUtils.isEmpty(model.getRoleIds())){
				editRole(model.getId(), null);
			}else{
				editRole(model.getId(), Arrays.asList(model.getRoleIds()));
			}
		}
	}
	
	@CacheEvict(allEntries=true,cacheNames={"organizationCache","resourceCache"})
	@Override
	public EmployeeInfoModel save(EmployeeInfoModel model) {
		prepareSaveOrUpdate(model);
		if (StringUtils.isBlank(model.getPassword())) {
			throw new ServiceException("密码不能为空");
		}
		String loginName = model.getLoginName();
		Lock lock = LockUtil.get(loginName);
		try {
			lock.lock();
			// 重复校验
			EmployeeInfoModel emp = findByLoginName(loginName);
			if (emp == null) {
				// MD5生成
				String salt = UUIDUtil.UUID32();
				String pwd = MD5Util.md5(model.getPassword(), salt);
				if(StringUtils.isNotBlank(model.getCcPwd())){
					String ccPwd = MD5Util.md5(model.getCcPwd());
					model.setCcPwd(ccPwd);
				}
				model.setSalt(salt);
				model.setPassword(pwd);
				//model.setJoinTime(new Date());
				super.save(model);
				afterSaveOrUpdate(model);
				return model;
			}else{
				throw new ServiceException("登录名称重复");
			}
		} finally {
			lock.unlock();
		}
	}
	
	@CacheEvict(allEntries=true,cacheNames={"organizationCache","resourceCache"})
	@Override
	public EmployeeInfoModel update(EmployeeInfoModel model) {
		prepareSaveOrUpdate(model);
		String loginName = model.getLoginName();
		if(("******").equals(model.getCcPwd())){
			//String ccPwd =model.getCcPwd();
			model.setCcPwd(null);
		}else{
			String ccPwd = MD5Util.md5(model.getCcPwd());
			model.setCcPwd(ccPwd);
		}
		if(("******").equals(model.getPassword())){
			//String ccPwd =model.getCcPwd();
			model.setPassword(null);
		}else{
			String salt = UUIDUtil.UUID32();
			String password = MD5Util.md5(model.getPassword(), salt);
			model.setPassword(password);
			model.setSalt(salt);
		}
		Lock lock = LockUtil.get(loginName);
		try {
			lock.lock();
			// 重复校验
			EmployeeInfoModel emp = findByLoginName(loginName);
			if (emp == null) {
				super.update(model);
				afterSaveOrUpdate(model);
				return model;
			}else{
				//更新的用户与数据库的用户不是同一个
				if(!model.getId().equals(emp.getId())){
					throw new ServiceException("登录名称重复");
				}else{
					super.update(model);
					afterSaveOrUpdate(model);
					return model;
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	@CacheEvict(allEntries=true,cacheNames="resourceCache")
	@Override
	public void editRole(String userId, List<String> roleIds) {
		employeeInfoMapper.deleteUserRoleRelation(userId);
		if(CollectionUtils.isNotEmpty(roleIds)){
			for(String roleId:roleIds){
				UserRoleRelationDto userRole = new UserRoleRelationDto(userId, roleId);
				employeeInfoMapper.saveUserRoleRelation(userRole);
			}
		}
	}

	@Override
	public List<String> findRoleIdsByUserId(String userId) {
		return employeeInfoMapper.findRoleIdsByUserId(userId);
	}
	
	@CacheEvict(allEntries=true,cacheNames="resourceCache")
	@Override
	public void editResource(String userId, List<String> resourceIds) {
		employeeInfoMapper.deleteUserResourceRelation(userId);
		if(CollectionUtils.isNotEmpty(resourceIds)){
			for(String resourceId:resourceIds){
				UserResourceRelationDto userResource = new UserResourceRelationDto(userId, resourceId);
				employeeInfoMapper.saveUserResourceRelation(userResource);
			}
		}
	}
	
	@Cacheable(cacheNames="resourceCache")
	@Override
	public List<String> findResourceIdsByUserId(String userId) {
		return employeeInfoMapper.findResourceIdsByUserId(userId);
	}
	
	@Override
	public List<EmployeeInfoModel> findEmpsForOrg(String userId,EmployeeInfoModel employee) {
		Map<String,Object> params = new HashMap<String, Object>(2);
		params.put("userId", userId);
		if(SecurityUtil.isSuperAdmin(employee)){
			return employeeInfoMapper.findEmpsForOrg(params);
		}
		params.put("orgId", employee.getOrgId());
		return employeeInfoMapper.findEmpsForOrg(params);
	}
	
	@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<EmployeeInfoModel> findEmpsByOrg(EmployeeInfoModel employeeInfo) {
		Map<String,Object> params = new HashMap<String, Object>(1);
		if(SecurityUtil.isSuperAdmin(employeeInfo)){
			return employeeInfoMapper.findEmpsByOrg(params);
		}
		params.put("orgId", employeeInfo.getOrgId());
		return employeeInfoMapper.findEmpsByOrg(params);
	}
	
	
	@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<EmployeeInfoModel> findEmpsByOrg(String orgId) {
		Map<String,Object> params = new HashMap<String, Object>(1);
		params.put("orgId", orgId);
		return employeeInfoMapper.findEmpsByOrg(params);
	}
	
	//@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<EmployeeInfoModel> findEmpsByPos(EmployeeInfoModel employeeInfo) {
		Map<String,Object> params = new HashMap<String, Object>(1);
		if(SecurityUtil.isSuperAdmin(employeeInfo)){
			return employeeInfoMapper.findEmpsByPos(params);
		}
		params.put("posId", employeeInfo.getPositionId());
		return employeeInfoMapper.findEmpsByPos(params);
	}
	//@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<EmployeeInfoModel> findEmpsByPos(String posId) {
		Map<String,Object> params = new HashMap<String, Object>(1);
		params.put("posId", posId);
		return employeeInfoMapper.findEmpsByPos(params);
	} 
	
	@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<TreeNode> orgUserCombotree(EmployeeInfoModel employee) {
		List<TreeNode> trees = organizationService.getOrganizationTreeNoCache(null,employee);
		for(TreeNode tree:trees){
			loadOrgUsers(tree);
		}
		return trees;
	}
	
	
	/**
	 * 加载机构用户
	 * @param root
	 */
	private void loadOrgUsers(TreeNode root){
		List<TreeNode> children = root.getChildren();
		if(CollectionUtils.isNotEmpty(children)){
			for(TreeNode tree:children){
				loadOrgUsers(tree);
			}
		}
		if(root.getId()!=null){
			List<EmployeeInfoModel> emps = super.findByField("org_id", root.getId());
			for(EmployeeInfoModel emp:emps){
				root.addChild(empToTreeNode(emp));
			}
		}
		
	}
	
	//员工转换树节点
	private TreeNode empToTreeNode(EmployeeInfoModel emp) {
		TreeNode node = new TreeNode(emp.getId(), emp.getUserName());
		Map<String, Object> attributes = new HashMap<>(2);
		attributes.put("isUser", true);
		attributes.put("number", emp.getNumber());
		node.setIconCls("eu-icon-user");
		node.setAttributes(attributes);
		return node;
	}
	
	@CacheEvict(allEntries=true,cacheNames={"organizationCache"})
	@Override
	public int updateForStatus(String[] ids) {
		EmployeeInfoModel model=new EmployeeInfoModel();
		model.setStatus(1);
		model.setModifyTime(new Date());
		
		Map<String, Object> params = new HashMap<>(2);
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return employeeInfoMapper.updateForStatus(params);
	}
	
	@CacheEvict(allEntries=true,cacheNames={"organizationCache"})
	@Override
	public int updateForStatusNo(String[] ids) {
		EmployeeInfoModel model=new EmployeeInfoModel();
		model.setStatus(0);
		model.setModifyTime(new Date());
		
		Map<String, Object> params = new HashMap<>(2);
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return employeeInfoMapper.updateForStatus(params);
	}

	@Override
	public void updateUserPassword(EmployeeInfoModel currentUser, String password,String newPassword,String ccPwd) {
		if(StringUtils.isBlank(password)){
			throw new ServiceException("请填写密码！");
		}
		EmployeeInfoModel oldUser = this.findByLoginName(currentUser.getLoginName());
		if(oldUser==null){
			throw new ServiceException("用户不存在,请检查当前用户登录名称");
		}
		String oldPwd = MD5Util.md5(password, oldUser.getSalt());
		if(oldPwd.equals(oldUser.getPassword())){
			EmployeeInfoModel model = new EmployeeInfoModel();
			model.setId(currentUser.getId());
			String salt = UUIDUtil.UUID32();
			if(StringUtils.isNotBlank(newPassword)){
				String newPwd = MD5Util.md5(newPassword, salt);
				model.setSalt(salt);
				model.setPassword(newPwd);
			}
			if(StringUtils.isNotBlank(ccPwd)){
				model.setCcPwd(MD5Util.md5(ccPwd));
			}
			model.setModifyTime(new Date());
			employeeInfoMapper.updateUserPassword(model);
		}else{
			throw new ServiceException("错误的原始密码");
		}
	}

	
	
}
