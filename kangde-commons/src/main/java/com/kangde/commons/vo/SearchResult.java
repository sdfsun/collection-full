package com.kangde.commons.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 检索结果,分页信息
 * @author lisuo
 * @param <T>
 */
@SuppressWarnings("serial")
public class SearchResult<T> implements Serializable{
	
	/** 页面总数 */
	private int total;
	/** 数据集合 */
	private List<T> rows;
	
	private List<T> footer;

	

	

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<T> getRows() {
		return (List<T>)rows;
	}
	

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	public List<T> getFooter() {
		return (List<T>)footer;
	}
	

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	

}
