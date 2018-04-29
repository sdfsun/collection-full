package com.kangde.collection.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kangde.collection.service.ColumnService;
import com.kangde.commons.easyui.Column;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.sys.mapper.ColumnMapper;
import com.kangde.sys.model.ColumnModel;

/**
 * 动态列service实现类
 * @author lisuo
 *
 */
@Service("columnService")
@CacheConfig(cacheNames = "columnCache")
public class ColumnServiceImpl extends AbstractService<ColumnModel,String> implements ColumnService {

	@Autowired
	private ColumnMapper columnMapper;
	
	@Cacheable
	@Override
	public List<Column> findCaseColumnsByUserId(String userId) {
		//查询所有案件列
		List<ColumnModel> caseColumns = columnMapper.findCaseColumnsNotDC();
		//查询用户配置的列
		List<ColumnModel> incColumns = columnMapper.findCaseColumnsByUserId(userId);
		List<Column> columns = new ArrayList<>(caseColumns.size());
		if(CollectionUtils.isNotEmpty(incColumns)){
			List<ColumnModel> ignoColumns =  new ArrayList<>(caseColumns.size()-incColumns.size());
			for(ColumnModel model:incColumns){
				Iterator<ColumnModel> it = caseColumns.iterator();
				//统一把不想的列放到最后最后
				while(it.hasNext()){
					ColumnModel m = it.next();
					if(m.getId().equals(model.getId())){
						m.setHidden(true);
						it.remove();//从列中移除
						ignoColumns.add(m);//添加到忽略队列
						break;
					}
				}
			}
			//把用户忽略的列放入最后
			caseColumns.addAll(ignoColumns);
		}
		for(ColumnModel model:caseColumns){
			Column column = new Column();
			ReflectUtil.copyProps(model, column,"sortable");
			column.setSortable(model.getSortable()!=null && model.getSortable() == 1);
			columns.add(column);
		}
		return columns;
	}
	

	
	
}
