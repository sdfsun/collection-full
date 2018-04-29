package com.kangde.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.kangde.collection.dto.CasePaidDto;
import com.kangde.collection.util.SnUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.dto.EntrustDto;
import com.kangde.sys.mapper.DictionaryMapper;
import com.kangde.sys.mapper.EmployeeInfoMapper;
import com.kangde.sys.mapper.EntrustCaseProductReleationMapper;
import com.kangde.sys.mapper.EntrustCaseSourceMapper;
import com.kangde.sys.mapper.EntrustMapper;
import com.kangde.sys.mapper.EntrustProductMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustCaseProductReleation;
import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.model.EntrustProduct;
import com.kangde.sys.security.util.MD5Util;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.EntrustProductService;

/**
 * 委托方 Service实现类
 * 
 * @author zhangyj
 */
@Service("entrustProductService")
public class EntrustProductServiceImpl extends AbstractService<EntrustProduct,String> implements EntrustProductService {
	
	@Autowired
	private EntrustProductMapper entrustmapper;
	@Autowired
	private EntrustMapper enmapper;
	@Autowired
	private EntrustCaseSourceMapper caseSourceMapper;
	@Autowired
	private EntrustCaseProductReleationMapper caseProductReleationMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
//	@Autowired
//	private EntrustCaseProductReleationService caseProductReleationservice;
	

	@Override
	public SearchResult<EntrustProduct> queryProduct(ParamCondition condition) {
		/*String getid = condition.get("caseAssignedId");
		String attachEntrustId = SecurityUtil.getCurrentUser().getAttachEntrustId();
		if(StringUtils.isNotBlank(attachEntrustId)){
			condition.put("attachEntrustId", SQLUtil.warpSql(attachEntrustId));
		}
		
		if (StringUtils.isNotBlank(getid)) {
			String caseAssignedIds = SQLUtil.warpSql(getid);
			condition.put("caseAssignedIds", caseAssignedIds);
		}
		*/
		List<EntrustProduct> list =entrustmapper.queryProduct(condition);
		SearchResult<EntrustProduct> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	
	
	/*@Override
	public int updateForState(EntrustModel model) {
		return entrustmapper.updateForState(model);
	}*/

	@Override
	public EntrustProduct save(EntrustProduct model) {
	
		// 重复校验
		String code = model.getCode();
		EntrustProduct product = entrustmapper.findByCode(code);
		if (product == null) {
			
			String userId = SecurityUtil.getCurrentUser().getId();
			String userName = SecurityUtil.getCurrentUser().getUserName();
			model.setCreateEmpName(userName);
			model.setCreateEmpId(userId);
			
			if (model.getState() == null) {
				model.setState(0);
			}
			super.save(model);
			EntrustCaseProductReleation releation = new EntrustCaseProductReleation();
			releation.setEntrustId(model.getEntrustId());
			releation.setCaseSourceId(model.getCaseSourceId());
			EntrustCaseSource source = caseSourceMapper.findById(model.getCaseSourceId());
			releation.setCaseSourceName(source.getName());
			releation.setCaseTypeId(model.getCaseTypeId());
			releation.setHandleId(model.getHandle());
			releation.setProductId(model.getId());
			String id = UUIDUtil.UUID32();
			releation.setId(id);
			releation.setCaseTypeName(model.getCaseTypeName());
			releation.setHandleName(model.getHandleName());
			EntrustModel entrustModel = enmapper.findById(model.getEntrustId());
			releation.setEntrustName(entrustModel.getName());
			releation.setCode(code);
			
			caseProductReleationMapper.save(releation);
			
			
			return model;
		}else{
			throw new ServiceException("全简码重复");
		}
		
	}
	
	@Override
	public EntrustProduct update(EntrustProduct model) {
		super.update(model);
		return model;
	}



	@Override
	public String createWholeCode(EntrustProduct model) {
		String entrustCode = enmapper.findCodeById(model.getEntrustId());
		String caseSourceCode = caseSourceMapper.findCodeById(model.getCaseSourceId());
		String codeSn = SnUtil.getWholeSn(entrustCode, model.getCaseTypeId(),model.getHandle(),caseSourceCode);
		// model.setBatchCode(batchSn);
		return codeSn;
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
