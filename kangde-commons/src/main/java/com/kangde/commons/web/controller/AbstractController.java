package com.kangde.commons.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;


/**
 * 作为DAO Controller基类,继承该类可以省略大量方法编写
 * @author lisuo
 *
 */
public abstract class AbstractController<T extends BaseModel<PK>,PK extends Serializable> extends SimpleController{
	
	/** 注入泛型Service */
	@Autowired
	protected BaseService<T,PK> singleService;
	/** 排序列名称 */
	protected Map<String,String> columnNames;

	/**
	 * 构造方法
	 */
	public AbstractController() {
		super();
		//初始化排序名称
		List<String> fieldNames = ReflectUtil.getFieldNames(ReflectUtil.getSuperGenericType(this.getClass()));
		columnNames = new HashMap<>(fieldNames.size());
		for(String fieldName:fieldNames){
			columnNames.put(fieldName, fieldName);
		}
	}
	
	@Override
	public String getColumnName(String sortTxt) {
		return columnNames.get(sortTxt);
	}
	
	/**
	 * 首页页面
	 * @return
	 */
	protected ModelAndView pageIndex() {
		return createBaseView(PAGE_INDEX);
	}
	
	/**
	 * 新增页面
	 * @return
	 */
	protected ModelAndView pageEditNew() {
		return createBaseView(PAGE_EDIT_NEW);
	}
	
	/**
	 * 修改页面
	 * @return
	 */
	protected ModelAndView pageEdit(PK id) {
		ModelAndView view = createBaseView(PAGE_EDIT);
		//查询对象,放入request
		T model = findById(id);
		view.addObject("model", model);
		return view;
	}
	
	/**
	 * 展示页面
	 * @return
	 */
	protected ModelAndView pageShow(PK id) {
		ModelAndView view = createBaseView(PAGE_SHOW);
		//查询对象,放入request
		T model = findById(id);
		view.addObject("model", model);
		return view;
	}
	
	/**
	 * 输入页面（增加，修改，展示都用这个页面）
	 * @param id 如果ID不为空,把ID放入request域
	 * @return
	 */
	protected ModelAndView pageInput(PK id) {
		ModelAndView view = createBaseView(PAGE_INPUT);
		//把ID传递到目标页面
		if(id !=null && StringUtils.isNotBlank(id.toString())){
			view.addObject("id", id);
		}
		return view;
	}
	
	/**
	 * 保存
	 * @return AjaxResult
	 */
	protected AjaxResult save(final T model) {
		singleService.save(model);
		return AjaxResult.success(getSuccessMessage());
	}
	
	/**
	 * 更新
	 * @return AjaxResult
	 */
	protected AjaxResult update(final T model) {
		singleService.update(model);
		return AjaxResult.success(getSuccessMessage());
	}
	
	/**
	 * 删除:根据<泛型>
	 * @return AjaxResult
	 */
	protected AjaxResult delete(final T model) {
		singleService.delete(model);
		return AjaxResult.success(getSuccessMessage());
	}
	
	/**
	 * ID删除
	 * @return AjaxResult
	 */
	protected AjaxResult deleteById(PK id) {
		singleService.deleteById(id);
		return AjaxResult.success(getSuccessMessage());
	}
	
	/**
	 * 批量：ID删除
	 * @return AjaxResult
	 */
	protected AjaxResult deleteByIds(List<PK> ids) {
		singleService.deleteByIds(ids);
		return AjaxResult.success(getSuccessMessage());
	}
	
	/**
	 * ID查询
	 * @param id
	 * @return 泛型
	 */
	protected T findById(PK id){
		return singleService.findById(id);
	}
 
	/**
	 * 批量：ID查询
	 * @param ids
	 * @return List<泛型>
	 */
	protected List<T> findByIds(List<PK> ids){
		return singleService.findByIds(ids);
	}
	
	/**
	 * 查询：根据参数条件
	 * @param condition
	 * @return List<泛型>
	 */
	protected List<T> query(ParamCondition condition){
		return singleService.query(condition);
	}
	
	
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	protected SearchResult<T> queryForPage(ParamCondition condition) {
		return singleService.queryForPage(condition);
	}
	
	
}
