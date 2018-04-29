package com.kangde.sys.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kangde.DictionaryConst;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.web.filter.SpringUtil;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;

/**
 * 字典工具类
 * 
 * @author lisuo
 *
 */
public abstract class DictUtil {
	
	/** 字典缓存Map */
	private static Map<String,String> cacheDictMap = new ConcurrentHashMap<>();

	private static DictionaryService dictionaryService = SpringUtil.getBean(DictionaryService.class);
	
	/**
	 * 通过字典路径查询字典
	 * @param path 路径
	 * @return 字典
	 */
	public static DictionaryModel findDictByPath(String path){
		DictionaryModel dict = dictionaryService.findDictByPath(path);
//		if(dict==null){
//			throw new RuntimeException("没有找到对应的字典:"+path);
//		}
		return dict;
	}
	/**
	 * 通过字典路径查询字典（下一级）字典
	 * @param path 路径
	 * @return 字典
	 */
	public static List<DictionaryModel> findSubsByPath(String path){
		return dictionaryService.findSubsByPath(path);
	}
	
	/**
	 * 获取字典在DictionaryConst中常量对应的value
	 * @param constName
	 * @return
	 */
	public static String getPathValue(String constName) {
		String path = cacheDictMap .get(constName);
    	if(path == null){
    		path = ReflectUtil.getConstValue(DictionaryConst.class, constName);
    		if(path==null){
        		throw new RuntimeException("在【"+DictionaryConst.class.getName()+"】常量配置中,没有找到字段名称为【"+constName+"】的常量信息");
        	}
    		cacheDictMap.put(constName, path);
    	}
		return path;
	}
	
}
