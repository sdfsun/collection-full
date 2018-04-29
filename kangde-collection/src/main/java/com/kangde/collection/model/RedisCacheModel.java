package com.kangde.collection.model;

import java.util.List;

import com.google.common.collect.Lists;
/**
 * 
  * @Description: redis缓存对象
  * @author lidengwen
  * @date 2016年9月9日 上午11:51:01
  *
 */
public class RedisCacheModel{
	 /**缓存编码*/
	private String id;
	 /**缓存编码*/
	private String key;
	
	
	public RedisCacheModel() {
		super();
	}
	public RedisCacheModel(String id, String key) {
		super();
		this.id = id;
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public static List<RedisCacheModel> getCaches() {
		List<RedisCacheModel> list=Lists.newArrayList();
		RedisCacheModel r1=new RedisCacheModel("1", "resourceCache");
		RedisCacheModel r2=new RedisCacheModel("2", "dictionaryCache");
		RedisCacheModel r3=new RedisCacheModel("3", "columnCache");
		RedisCacheModel r4=new RedisCacheModel("4", "organizationCache");
		RedisCacheModel r5=new RedisCacheModel("5", "empCache");
		RedisCacheModel r6=new RedisCacheModel("6", "roleCache");
		RedisCacheModel r7=new RedisCacheModel("8", "regionCache");
		RedisCacheModel r8=new RedisCacheModel("9", "allProvince");
		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);
		list.add(r5);
		list.add(r6);
		list.add(r7);
		list.add(r8);
		return list;
	}
	
	
}