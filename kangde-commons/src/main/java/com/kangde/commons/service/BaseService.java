package com.kangde.commons.service;

import java.io.Serializable;
import java.util.List;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * Service基类
 * @author lisuo
 *
 * @param <T> (数据库表Model)泛型
 * @param <PK> 主键类型
 */
public interface BaseService<T extends BaseModel<PK>,PK extends Serializable> {

	/**
	 * 保存
	 * @param model
	 * @return 泛型对象
	 */
	T save(T model);
	
	/**
	 * 批量保存
	 * @param models
	 * @return 响应行数
	 */
	int batchSave(List<? extends T> models);

	/**
	 * 更新
	 * @param model
	 * @return 泛型对象
	 */
	T update(T model);
	
	/**
	 * 删除:根据<泛型>
	 * @param model
	 */
	void delete(T model);

	/**
	 * ID删除
	 * @param id
	 */
	void deleteById(PK id);

	/**
	 * 批量：ID删除
	 * @param ids
	 */
	void deleteByIds(List<PK> ids);

	/**
	 * ID查询
	 * @param id
	 * @return 泛型
	 */
	T findById(PK id);

	/**
	 * 批量：ID查询
	 * @param ids
	 * @return List<泛型>
	 */
	List<T> findByIds(List<PK> ids);
	
	/**
	 * 查询全部,不带任何条件
	 * 
	 * @return List<泛型>
	 */
	List<T> findAll();

	/**
	 * 查询：根据参数条件
	 * @param condition
	 * @return List<泛型>
	 */
	List<T> query(ParamCondition condition);

	/**
	 * 查询：根据参数条件
	 * @param condition
	 * @return SearchResult
	 */
	SearchResult<T> queryForPage(ParamCondition condition);

}
