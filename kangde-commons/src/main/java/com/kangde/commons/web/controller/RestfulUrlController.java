package com.kangde.commons.web.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * 该类用于Restful风格请求路径（以及定义好的几个页面规则名称）
 * 既POST请求为新增，DELETE请求为删除，PUT请求为修改，GET请求为查询，映射的list路径为默认的分页查询
 * index或GET请求首页,edit为新增（跳转到editNew.jsp）,edit/id 为修改（跳转到edit.jsp），
 * show为展示（跳转到show.jsp），input为（新增or修改or展示）,都会（跳转到input.jsp），
 * input/id（会跳转到input.jsp同时把id放入当前request作用域）
 * 
 * @author lisuo
 *
 * @param <T>
 */
public abstract class RestfulUrlController<T extends BaseModel<PK>,PK extends Serializable> extends AbstractController<T,PK> {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		return super.pageIndex();
	}

	//首页页面,转发到getBaseName/index
	@RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
	@Override
	public ModelAndView pageIndex() {
		return super.pageIndex();
	}
	
	//新增页面,转发到getBaseName/editNew
	@RequestMapping(value = PAGE_EDIT, method = RequestMethod.GET)
	@Override
	public ModelAndView pageEditNew() {
		return super.pageEditNew();
	}

	//修改页面,转发到getBaseName/edit
	@RequestMapping(value = PAGE_EDIT + "/{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageEdit(@PathVariable("id") PK id) {
		return super.pageEdit(id);
	}

	//展示页面,转发到getBaseName/show
	@RequestMapping(value = PAGE_SHOW, method = RequestMethod.GET)
	@Override
	public ModelAndView pageShow(@RequestParam("id") PK id) {
		return super.pageShow(id);
	}
	
	//输入页面（增加，修改，展示都用这个页面）,带ID把id放入请求域,不从数据库查询数据,转发到getBaseName/input
	@RequestMapping(value = PAGE_INPUT + "/{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView pageInput(@PathVariable("id") PK id) {
		return super.pageInput(id);
	}
	
	//输入页面（增加，修改，展示都用这个页面）,转发到getBaseName/input
	@RequestMapping(value = PAGE_INPUT, method = RequestMethod.GET)
	public ModelAndView pageInput() {
		return super.pageInput(null);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public AjaxResult save(T model) {
		return super.save(model);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public AjaxResult update(T model) {
		return super.update(model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public AjaxResult deleteById(@PathVariable("id") PK id) {
		return super.deleteById(id);
	}
	
	/**
	 * ID批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE)
	public AjaxResult deleteByIds(@RequestParam(value="ids") PK[] ids) {
		return super.deleteByIds(Arrays.asList(ids));
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Override
	public T findById(@PathVariable("id") PK id) {
		return super.findById(id);
	}
	
	/**
	 * 不带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_LIST, method = RequestMethod.GET)
	public List<T> list() {
		ParamCondition condition = parseCondition();
		return super.query(condition);
	}

	/**
	 * 带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_PAGE, method = RequestMethod.GET)
	public SearchResult<T> queryForPage() {
		ParamCondition condition = parseCondition();
		return super.queryForPage(condition);
	}
	

}
