package com.kangde.sys.mapper;

import java.util.List;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.model.DictionaryModel;

/**
 * 字典 Mapper
 * @author lisuo
 *
 */
public interface DictionaryMapper extends BaseMapper<DictionaryModel,String>{
	
	/**
	 * 查询所有顶级节点
	 * @param condition 检索条件
	 * @return 
	 */
	List<DictionaryModel> findTops(ParamCondition condition);
	/**
	 * 通过父节点查询子节点
	 * @param parentId 父节点
	 * @return 
	 */
	List<DictionaryModel> findByParentId(String parentId);
	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();

}
