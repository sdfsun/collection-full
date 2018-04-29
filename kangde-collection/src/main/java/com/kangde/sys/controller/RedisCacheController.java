package com.kangde.sys.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.web.controller.SimpleController;

@Controller
@RequestMapping("sys/redisCache")
public class RedisCacheController extends SimpleController {
	
	@Autowired
	private CacheManager cacheManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		return createBaseView(PAGE_INDEX);
	}
	
	/**
	 * 不带分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = QUERY_LIST, method = RequestMethod.GET)
	public List<Map<String,String>> list() {
		List<Map<String,String>> list = new ArrayList<>();
		Collection<String> keys = cacheManager.getCacheNames();
		if(CollectionUtils.isNotEmpty(keys)){
			for(String key:keys){
				Map<String,String> map = new HashMap<>(1);
				map.put("key", key);
				list.add(map);
			}
		}
		return list;
	}
	
	//清空指定缓存
	@ResponseBody
	@RequestMapping(value = "clearCache", method = RequestMethod.POST)
	public AjaxResult clearCache(@RequestParam("keys[]") String [] keys) {
		if(ArrayUtils.isNotEmpty(keys)){
			for(String key:keys){
				Cache cache = cacheManager.getCache(key);
				if(cache!=null){
					cache.clear();
				}
			}
		}
		return AjaxResult.success(getSuccessMessage());
	}

	//清空缓存
	@ResponseBody
	@RequestMapping(value = "flushAll", method = RequestMethod.POST)
	public AjaxResult flushAll() {
		Collection<String> keys = cacheManager.getCacheNames();
		if(CollectionUtils.isNotEmpty(keys)){
			for(String key:keys){
				Cache cache = cacheManager.getCache(key);
				if(cache!=null){
					cache.clear();
				}
			}
		}
		return AjaxResult.success(getSuccessMessage());
	}
	
	
	
	@Override
	protected String getBaseName() {
		return "sys/redisCache/";
	}
	
	
	
}
