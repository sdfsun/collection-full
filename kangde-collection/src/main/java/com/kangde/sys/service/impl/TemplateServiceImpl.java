package com.kangde.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.mapper.ColumnMapper;
import com.kangde.sys.mapper.TemplateMapper;
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.TemplateModel;
import com.kangde.sys.security.util.SecurityUtil;
import com.kangde.sys.service.TemplateService;

/**
 * 模板 Service实现类
 * 
 * @author wcy
 * @date 2016年6月14日17:47:34
 */
@Service("templateService")
public class TemplateServiceImpl extends AbstractService<TemplateModel, String> implements TemplateService {

	@Autowired
	private TemplateMapper templateMapper;

	@Autowired
	private ColumnMapper columnMapper;
	/** 催记启用模板 */
	@Override
	public int updateForStatusCollection(List<String> list) {
		// TODO Auto-generated method stub
		return templateMapper.updateForStatus(list);
	}
	
	/** 催记启用模板 */
	@Override
	public int updateForStatusStop(List<String> list) {
		// TODO Auto-generated method stub
		return templateMapper.updateForStatusStop(list);
	}

	/** 催记删除模板 */
	@Override
	public void delateIds(List<String> list){
		templateMapper.delateIds(list);
	}
	
	/** 案件启用模板 */
	@Override
	public int updateForStart(List<String> list) {
		return templateMapper.updateForStart(list);
	}
	/** 案件停用模板 */
	@Override
	public int updateForStop(List<String> list) {
		return templateMapper.updateForStop(list);
	}
	
	/** 催记删除模板 */
	@Override
	public void deleteAll(List<String> list){
		templateMapper.deleteAll(list);
	}
	
	/** 新添案件模板 */
	@Override
	public int saveTemplate(String name, String[] list) {
		// TODO Auto-generated method stub
		String gropNo="case_info";
		List<TemplateModel> temModel=templateMapper.findByName(name,gropNo);
		if(temModel.size()>=1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		if(StringUtils.isBlank(name)){
			throw new ServiceException("模板名称不能为空.");
		}
		if(list==null){
			throw new ServiceException("请勾选字段（至少一个）");
		}
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();// 获取当前用户信息
		String id = UUIDUtil.UUID32(); // 操作记录表id
		String createEmpId = currentUser.getLoginName(); // 操作人
		TemplateModel model = new TemplateModel();
		model.setSysColumnIds(StringUtils.join(list,","));// 处理后的值
		model.setId(id);
		model.setGropNo("case_info");
		model.setName(name);
		model.setState(1);// 新增模板是启用状态
		model.setCreateEmpId(createEmpId);
		model.setCreateTime(new Date());
		
		return templateMapper.saveTemplate(model);
	}
	
	/** 新添催记模板 */
	@Override
	public int saveTemplatecollection(String name, String[] list) {
		// TODO Auto-generated method stub
		String gropNo="phone_record";
		List<TemplateModel> temModel=templateMapper.findByName(name,gropNo);
		if(temModel.size()>=1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		if(StringUtils.isBlank(name)){
			throw new ServiceException("模板名称不能为空");
		}
		if(list==null){
			throw new ServiceException("请勾选字段（至少一个。）");
		}
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();// 获取当前用户信息
		String id = UUIDUtil.UUID32(); // 操作记录表id
		String createEmpId = currentUser.getLoginName(); // 操作人
		TemplateModel model = new TemplateModel();
		model.setSysColumnIds(StringUtils.join(list,","));// 处理后的值
		model.setId(id);
		model.setGropNo("phone_record");
		model.setName(name);
		model.setState(1);// 新增模板是启用状态
		model.setCreateEmpId(createEmpId);
		model.setCreateTime(new Date());
		
		return templateMapper.saveTemplate(model);
	}

	/** 修改案件模板 */
	@Override
	public int updateTemplate(String name, String[] list, String id,String type) {
		// TODO Auto-generated method stub
		String gropNo="";
		if(type.equals("case_info")){
			 gropNo="indicate";
		}else if(type.equals("cj")){
			gropNo="phone_record";
		}
		TemplateModel tempModel= templateMapper.findById(id);
		List<TemplateModel> listModel=templateMapper.findByName(name, gropNo);
		if(tempModel.getName().equals(name) && listModel.size()>1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		if(!tempModel.getName().equals(name) && listModel.size()>=1){
			throw new ServiceException("以有模板名称，模板名称不能重复.");
		}
		if(StringUtils.isBlank(name)){
			throw new ServiceException("模板名称不能为空");
		}
		if(list==null){
			throw new ServiceException("请勾选字段（至少一个。）");
		}
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();// 获取当前用户信息
		String modifyEmpId = currentUser.getLoginName(); // 得到修改人
		TemplateModel model = new TemplateModel();
		model.setId(id);// 前台传过来的id
		model.setName(name);// 模板名称
		model.setSysColumnIds(StringUtils.join(list,","));// 修改后的id拼接
		model.setModifyTime(new Date());// 修改时间
		model.setModifyEmpId(modifyEmpId);// 修改人
		return templateMapper.updateTemplate(model);
	}
	
	/** 案件查询字段表 */
	@Override
	public List<ColumnModel> findCheckbox() {
		// TODO Auto-generated method stub
		List<ColumnModel> caseColumns = columnMapper.findCaseColumns();
		return caseColumns;
	}
	/** 外放查询字段表 */
	@Override
	public List<ColumnModel> findVisitRecord() {
		// TODO Auto-generated method stub
		List<ColumnModel> caseColumns = columnMapper.findVisitRecord();
		return caseColumns;
	}

	/** 风控查询字段表 */
	@Override
	public List<ColumnModel> findCheckboxcollection() {
		// TODO Auto-generated method stub
		List<ColumnModel> caseColumns = columnMapper.findCheckboxcollection();
		return caseColumns;
	}

	@Override
	public List<ColumnModel> findOk(String SysColumnIds) {
		List<ColumnModel> caseColumnsOk = columnMapper.findOk(SysColumnIds);
		return caseColumnsOk;
	}
	
	@Override
	public List<ColumnModel> findOkCollection(String SysColumnIds) {
		List<ColumnModel> caseColumnsOk = columnMapper.findOkCollection(SysColumnIds);
		return caseColumnsOk;
	}


	@Override
	public SearchResult<TemplateModel> querycollection(ParamCondition condition) {
		List<TemplateModel> list = templateMapper.querycollection(condition);
		SearchResult<TemplateModel> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public List<TemplateModel> findByGropNo(String gropNo) {
		return templateMapper.findByGropNo(gropNo);
	}

	
}
