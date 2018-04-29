package com.kangde.sys.model;

import com.kangde.commons.model.BizModel;

/**
 * 外访模板Model
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class VisitTemplateModel extends BizModel<String> {

	/** 是否启用 1是 */
	public static Integer STATE_ENABLE = 1;
	/** 是否启用 0否 */
	public static Integer STATE_DISABLE = 0;

	/** 模板名称 */
	private String name;
	/** 模板内容 */
	private String content;
	/** 模板类型 */
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
	
}
