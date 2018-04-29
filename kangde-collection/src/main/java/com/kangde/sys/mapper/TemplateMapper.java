package com.kangde.sys.mapper;


import java.util.List;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.model.TemplateModel;
/**
 * 模板mapper
 * @author wcy
 * @date 2016年6月14日17:44:44
 *
 */
public interface TemplateMapper extends BaseMapper<TemplateModel,String> {
	
	/**催记启用模板*/
	int updateForStatus(List<String> list);
	/**催记禁用板*/
	int updateForStatusStop(List<String> list);
	/**催记删除模板*/
	void delateIds(List<String> list);
	
	/**案件启用模板*/
	int updateForStart(List<String> list);
	/**案件停用模板*/
	int updateForStop(List<String> list);
	/**案件删除模板*/
	void deleteAll(List<String> list);
	
	
	/**新增催板*/
	public int saveTemplate(TemplateModel model);
	
	/**修改模板模板*/
	public int updateTemplate(TemplateModel model);
	
	/**查询所有催记模板*/
	List<TemplateModel> querycollection(ParamCondition condition);
	
	/**
	 * 通过组号查询模板信息*/
	public List<TemplateModel> findByGropNo(String gropNo);
	
	/**根据模板名称查询模板*/
	List<TemplateModel> findByName(String name,String gropNo);
	
}