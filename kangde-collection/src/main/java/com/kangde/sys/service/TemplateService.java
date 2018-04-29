package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.TemplateModel;

/**
 *  模板Service
 * @author wcy
 * @date 2016年6月14日17:42:51
 */
public interface TemplateService extends BaseService<TemplateModel,String>{
	/** 催记启用模板*/
	public int updateForStatusCollection(List<String> list);
	/** 催记停用模板*/
	public int updateForStatusStop(List<String> list);
	/** 催记删除模板*/
	void delateIds(List<String> list); 
	
	/** 案件启用模板*/
	public int updateForStart(List<String> list);
	/** 案件停用模板*/
	public int updateForStop(List<String> list);
	/** 案件删除模板*/
	void deleteAll(List<String> list); 
	
	/** 新添模板*/
	public int saveTemplate(String model,String [] list);
	/** 新添风控模板*/
	public int saveTemplatecollection(String model,String [] list);
	/** 编辑案件模板*/
	public int updateTemplate(String name,String [] list,String id,String type);
	/**  案件所有动态列*/
	List<ColumnModel> findCheckbox();
	/**  外放所有动态列*/
	List<ColumnModel> findVisitRecord();
	/** 风控动态列*/
	List<ColumnModel> findCheckboxcollection();
	/** 案件已勾选列*/
	List<ColumnModel> findOk(String SysColumnIds);
	/** 已勾选列*/
	List<ColumnModel> findOkCollection(String SysColumnIds);
	/** 查询所有协催*/
	SearchResult<TemplateModel> querycollection(ParamCondition condition);
	
	/**
	 * 通过组号查询模板信息
	 * @param gropNo
	 * @return
	 */
	public List<TemplateModel> findByGropNo(String gropNo);
	

}
