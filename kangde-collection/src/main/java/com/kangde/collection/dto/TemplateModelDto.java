package com.kangde.collection.dto;

import com.kangde.sys.model.ColumnModel;
import com.kangde.sys.model.TemplateModel;

/**
 * 模板，xml解析
 * @author wcy
 * @date 22016年6月15日16:54:46
 *
 */
@SuppressWarnings("serial")
public class TemplateModelDto extends TemplateModel{
	
	private ColumnModel columnModel;

	public ColumnModel getColumnModel() {
		return columnModel;
	}

	public void setColumnModel(ColumnModel columnModel) {
		this.columnModel = columnModel;
	}

	
}
