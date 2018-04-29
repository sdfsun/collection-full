package com.kangde.sys.mapper;

import java.util.List;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.PositionModel;

/**
 * 职位 Mapper
 * @author lisuo
 *
 */
public interface PositionMapper extends BaseMapper<PositionModel,String>{
	
	/**
	 * 查询所有顶级节点
	 * @param condition 检索条件
	 * @return 
	 */
	List<PositionModel> findTops();
	/**
	 * 通过父节点查询子节点
	 * @param parentId 父节点
	 * @return 
	 */
	List<PositionModel> findByParentId(String parentId);
	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();

}
