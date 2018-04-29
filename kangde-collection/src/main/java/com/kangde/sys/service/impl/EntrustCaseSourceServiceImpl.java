package com.kangde.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.sys.mapper.EntrustCaseSourceMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EntrustCaseSourceService;

/**
 * 委托方 Service实现类
 * 
 * @author zhangyj
 */
@Service("entrustCaseSourceService")
public class EntrustCaseSourceServiceImpl extends AbstractService<EntrustCaseSource,String> implements EntrustCaseSourceService {
	@Autowired
	private EntrustCaseSourceMapper entrustmapper;
	/*@Override
	public int updateForState(EntrustModel model) {
		return entrustmapper.updateForState(model);
	}*/

	@Override
	public EntrustCaseSource update(EntrustCaseSource model) {
		// 重复校验---name
				String name = model.getName();
				String code = model.getCode();

				EntrustCaseSource entrust = entrustmapper.findByName(name);
				EntrustCaseSource entrust1 = entrustmapper.findByCode(code);
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
							throw new ServiceException("案源地重复");
						}else {
							super.update(model);
							return model;
						}
					} else {
						if(!model.getId().equals(entrust.getId())&&!model.getId().equals(entrust1.getId())){
							throw new ServiceException("案源地和简码重复");
						}else if(model.getId().equals(entrust.getId())&&!model.getId().equals(entrust1.getId())){
							throw new ServiceException("简码重复");
						}else if(!model.getId().equals(entrust.getId())&&model.getId().equals(entrust1.getId())){
							throw new ServiceException("案源地重复");
						}else {
							super.update(model);
							return model;
						}
						
					}
	}
	
	@Override
	public EntrustCaseSource save(EntrustCaseSource model) {
		
		// 重复校验---name
				String name = model.getName();
				String code = model.getCode();

				EntrustCaseSource entrust = entrustmapper.findByName(name);
				EntrustCaseSource entrust1 = entrustmapper.findByCode(code);
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
					throw new ServiceException("案源地重复");
				} else{
					throw new ServiceException("案源地和简码重复");
				}

	
	}

@CacheEvict(allEntries=true,cacheNames={"organizationCache"})
	@Override
	public int updateForStatus(String[] ids) {
		EntrustModel model=new EntrustModel();
		model.setState(0);
		model.setModifyTime(new Date());
		
		Map<String, Object> params = new HashMap<>(2);
		params.put("entrust", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return entrustmapper.updateForStatus(params);
	}
	
	@CacheEvict(allEntries=true,cacheNames={"organizationCache"})
	@Override
	public int updateForStatusNo(String[] ids) {
		EntrustModel model=new EntrustModel();
		model.setState(1);
		model.setModifyTime(new Date());
		
		Map<String, Object> params = new HashMap<>(2);
		params.put("entrust", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		return entrustmapper.updateForStatus(params);

	}

	
	@Cacheable(cacheNames = {"organizationCache"})
	@Override
	public List<EntrustCaseSource> findSourceByEnId(EntrustCaseSource entrustCaseSource) {
		Map<String,Object> params = new HashMap<String, Object>(1);
		params.put("entrustId", entrustCaseSource.getEntrustId());
		return entrustmapper.findSourcesByEnId(params);
	}


		//@Cacheable(cacheNames = {"organizationCache"})
		@Override
		public List<EntrustCaseSource> findSourceByEnId(String entrustId) {
			Map<String,Object> params = new HashMap<String, Object>(1);
			params.put("entrustId", entrustId);
			List<EntrustCaseSource> findSourcesByEnId = entrustmapper.findSourcesByEnId(params);
			return findSourcesByEnId;
		}
		
	/*@Override
	public EntrustModel save(EntrustModel model) {
		if (StringUtils.isBlank(model.getName())) {
			//后台验证  验证职位名称是否为空   如果是空抛出异常  前台alert 弹窗提示。
			throw new ServiceException("登录名称不能为空");
		}if(0==model.getLevelId()){
			throw new ServiceException("请选择职级等级");
		}
		return super.save(model);

	}*/

}
