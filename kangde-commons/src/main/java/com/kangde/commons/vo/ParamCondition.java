package com.kangde.commons.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 参数条件
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class ParamCondition implements Serializable{

	/** 分页起始位置 */
	private int offset;
	/** 分页结束位置 */
	private int limit;
	/** 排序列方式asc desc */
	private String order;
	/** 排序列名称 */
	private String sort;
	/** 条件参数 */
	private Map<String, Object> params = new HashMap<String, Object>();
	
	/** 经过PageInterceptor拦截器检索结果总数 */
	private int total;
	
	/**
	 * 清空分页信息.
	 */
	public void clearPager() {
		this.offset = -1;
	}

	/**
	 * @return 是否含有分页
	 */
	public boolean hashPager() {
		return this.offset >= 0 && this.limit > 0;
	}

	/**
	 * @return 是否含有排序
	 */
	public boolean hasOrder() {
		return StringUtils.isNotBlank(order) && StringUtils.isNotBlank(sort);
	}

	/**
	 * 放入参数
	 * 
	 * @param key
	 * @param value
	 */
	public ParamCondition put(String key, Object value) {
		this.params.put(key, value);
		return this;
	}

	/**
	 * 获取params参数
	 * @param key
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) this.params.get(key);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<String, Object> getParams() {
		return params;
	}
	
	/**
	 * 经过PageInterceptor拦截器计算出数据库中本次检索的记录总数
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 移除参数
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T remove(String key){
		return (T) this.params.remove(key);
	}
	
	
	//---Equals AND hashCode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
		result = prime * result + offset;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParamCondition other = (ParamCondition) obj;
		if (limit != other.limit)
			return false;
		if (offset != other.offset)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (total != other.total)
			return false;
		return true;
	}
	
	
	
}
