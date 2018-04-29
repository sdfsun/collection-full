package com.kangde.commons.easyui;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.Lists;

/**
 * easyui分页组件datagrid、combogrid数据模型.
 *
 */
@SuppressWarnings("serial")
public class Datagrid<T> implements Serializable {
    /**
     * 总记录数
     */
    private long total;
    /**
     * 动态固定列
     */
    private List<Column> frozenColumns;
    /**
     * 动态列
     */
    private List<Column> columns;
    /**
     * 列表行
     */
    private List<T> rows;
    /**
     * 脚列表
     */
    private List<Map<String, Object>> footer;

    public Datagrid() {
        super();
    }

    /**
     * @param total 总记录数
     * @param rows  列表行
     */
    public Datagrid(long total, List<T> rows) {
        this(total, rows, null, null);
    }

    /**
     * @param total   总记录数
     * @param rows    列表行
     * @param footer  脚列表
     * @param columns 动态列
     */
    public Datagrid(long total, List<T> rows, List<Map<String, Object>> footer,
                    List<Column> columns) {
        super();
        this.total = total;
        this.rows = rows;
        this.footer = footer;
        this.columns = columns;
    }

    /**
     * 总记录数
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总记录数
     *
     * @param total 总记录数
     */
    public Datagrid<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * 列表行
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * 设置列表行
     *
     * @param rows 列表行
     */
    public Datagrid<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * 脚列表
     */
    public List<Map<String, Object>> getFooter() {
        return footer;
    }

    /**
     * 设置脚列表
     *
     * @param footer 脚列表
     */
    public Datagrid<T> setFooter(List<Map<String, Object>> footer) {
        this.footer = footer;
        return this;
    }

    /**
     * 动态列
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * 设置动态列
     *
     * @param columns 动态列
     */
    public Datagrid<T> setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * 获取固定列
     * @return
     */
    public List<Column> getFrozenColumns() {
		return frozenColumns;
	}
    
    /**
     * 设置固定列
     * @param frozenColumns
     */
    public void setFrozenColumns(List<Column> frozenColumns) {
		this.frozenColumns = frozenColumns;
	}
    
    /**
     * 添加动态列表
     *
     * @param column 动态列
     * @return
     */
    public Datagrid<T> addColumn(Column column) {
        if (this.columns == null) {
            this.columns = Lists.newArrayList();
        }
        this.columns.add(column);
        return this;
    }
    
    /**
     * 添加动态列表
     *
     * @param column 动态列
     * @return
     */
    public Datagrid<T> addFrozenColumns(Column column) {
    	if (this.frozenColumns == null) {
    		this.frozenColumns = Lists.newArrayList();
    	}
    	this.frozenColumns.add(column);
    	return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
