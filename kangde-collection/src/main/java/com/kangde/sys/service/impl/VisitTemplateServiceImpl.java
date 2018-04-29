
package com.kangde.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.sys.mapper.VisitTemplateMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.VisitTemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.VisitTemplateService;

/**
 * 外访模板Service实现类
 * @author lisuo
 *
 */
@Service("visitTemplateService")
public class VisitTemplateServiceImpl extends AbstractService<VisitTemplateModel,String> implements VisitTemplateService{
	
	@Autowired
	private VisitTemplateMapper visitTemplateMapper;
	
	@Override
	public VisitTemplateModel save(VisitTemplateModel model) {
		List<VisitTemplateModel> tempModel=visitTemplateMapper.findByName(model);
		if(tempModel.size()>=1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		model.setState(VisitTemplateModel.STATE_ENABLE);
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		model.setCreateEmpId(currentUser.getId());
		return super.save(model);
	}
	
	@Override
	public VisitTemplateModel update(VisitTemplateModel model) {
		VisitTemplateModel tempModel= visitTemplateMapper.findById(model.getId());
		List<VisitTemplateModel> listModel=visitTemplateMapper.findByName(model);
		if(tempModel.getName().equals(model.getName()) && listModel.size()>1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		if(!tempModel.getName().equals(model.getName()) && listModel.size()>=1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		model.setModifyEmpId(currentUser.getId());
		return super.update(model);
	}
	
	/** 外访启用模板 */
	@Override
	public int updateForStatus(List<String> list) {
		return visitTemplateMapper.updateForStatus(list);
	}
	
	/** 外访禁用模板 */
	@Override
	public int updateForStop(List<String> list) {
		return visitTemplateMapper.updateForStop(list);
	}

	@Override
	public List<VisitTemplateModel> findByType(Integer type) {
		return visitTemplateMapper.findByType(type);
	}
}
