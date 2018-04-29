package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.easyui.TreeNode;
import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.DictionaryModel;

/**
 * 字典 Service
 * @author lisuo
 *
 */
public interface DictionaryService extends BaseService<DictionaryModel,String>{
	
	/**
	 * 通过字典路径查询字典
	 * @param path 路径
	 * @return 字典
	 */
	DictionaryModel findDictByPath(String path);
	/**
	 * 通过字典路径查询字典（下一级）字典
	 * @param path 路径
	 * @return 字典
	 */
	List<DictionaryModel> findSubsByPath(String path);
	
	/**
	 * 获取字典的
	 * @param dictId 排除的字典ID
	 * @param excludeChildren 是否排除子节点
	 * @return
	 */
	List<TreeNode> getDictionaryTree(String dictId,boolean excludeChildren);

	/**
	 * 获取最大排序号
	 * @return
	 */
	Integer findMaxSort();

}
