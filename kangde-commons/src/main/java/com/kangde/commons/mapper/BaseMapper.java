package com.kangde.commons.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.kangde.commons.model.BaseModel;
import com.kangde.commons.vo.ParamCondition;

/**
 * 所有Mapper基类
 * @author lisuo
 *
 * @param <T> (数据库表Model)泛型
 * @param <PK>
 */
public interface BaseMapper<T extends BaseModel<PK>,PK extends Serializable> {

	/**
	 * 保存
	 * @param model
	 * @return 响应行数
	 */
	int save(T model);

	/**
	 * 更新
	 * @param model
	 * @return 响应行数
	 */
	int update(T model);
	
	/**
	 * 删除:根据<泛型>
	 * @param model
	 * @return 响应行数
	 */
	int delete(T model);
	
	/**
	 * ID删除
	 * @param id
	 * @return 响应行数
	 */
	int deleteById(PK id);
	
	/**
	 * 批量：ID删除
	 * @param ids
	 * @return 响应行数
	 */
	int deleteByIds(List<PK> ids);

	/**
	 * ID查询
	 * 
	 * @param id
	 * @return 泛型
	 */
	T findById(PK id);

	/**
	 * 批量：ID查询
	 * 
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
	 * 通过字段名称查找
	 * @param fieldMap<fieldName字段名称,fieldValue字段值>
	 * @return 查找到的结果
	 */
	List<T> findByField(Map<String,Object> fieldMap);
}
