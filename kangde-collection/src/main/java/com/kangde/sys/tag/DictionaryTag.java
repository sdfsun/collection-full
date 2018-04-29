package com.kangde.sys.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.kangde.sys.util.DictUtil;

/**
 * 数据字典标签
 * 
 * @author lisuo
 */
@SuppressWarnings("serial")
public class DictionaryTag extends TagSupport {

	/** 字典生成combobox的方法路径 */
	private static final String TYPE_COMBOBOX = "combobox";
	/** 字典生成combotree的方法路径 */
	private static final String TYPE_COMBOTREE = "combotree";

	/** 元素id */
	private String id = "";
	/** 元素name */
	private String name = "";
	/** 宽度 */
	private Integer width;

	/** 要生成字典的类型，默认下拉列表："combobox" 、下拉树："combotree" */
	private String type = TYPE_COMBOBOX;
	/** 选择类型 "select" 、"all" */
	private String selectType;

	/** 是否必选 */
	private boolean required = false;
	/** 是否只读 */
	private boolean readonly = false;
	/** 为空提示信息 */
	private String missingMessage;
	/** 是否多选 默认值:否 */
	private boolean multiple = false;
	/** 是否可以编辑 默认值false: */
	private boolean editable = false;
	/** 默认值 */
	private String value = "";

	/** 常量名称,映射字典路径 */
	private String constName = "";

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(createTagHtml());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	/**
	 * 下拉框
	 * 
	 * @return
	 */
	private String createTagHtml() {
		String contextPath = pageContext.getServletContext().getContextPath(); // 上下文路径
		String method = null;
		StringBuilder builder = new StringBuilder("<input ");
		if (TYPE_COMBOBOX.equals(type)) {
			builder.append(" class=\"easyui-combobox\"");
			method = TYPE_COMBOBOX;
		} else if (TYPE_COMBOTREE.equals(type)) {
			builder.append(" class=\"easyui-combotree\"");
			method = TYPE_COMBOTREE;
		}
		if (!"".equals(this.id)) {
			builder.append(" id=\"" + this.id + "\" ");
		}

		if (this.name != null && !"".equals(this.name)) {
			builder.append(" name=\"" + this.name + "\" ");
		}
		if (this.width != null) {
			builder.append("style=\"width:"+this.width+"px;\"");
		}

		builder.append(" data-options=\"url:'").append(contextPath).append("/sys/dictionary/").append(method)
				.append("?path=").append(DictUtil.getPathValue(constName));
		if (StringUtils.isNotBlank(this.selectType)) {
			builder.append("&selectType=").append(this.selectType);
		}
		builder.append("'");
		if (this.multiple) {
			builder.append(",multiple:").append(multiple);
		}
		if (this.required) {
			builder.append(",required:").append(required);
			if (TYPE_COMBOBOX.equals(type)) {
				builder.append(",validType:['comboboxRequired']");
			} else if (TYPE_COMBOTREE.equals(type)) {
				builder.append(",validType:['combotreeRequired']");
			}
		}
		if (this.readonly) {
			builder.append(",readonly:").append(readonly);
		}
		builder.append(",editable:").append(editable);
		if (StringUtils.isNotBlank(this.value)) {
			builder.append(",onLoadSuccess:function(){$(this)."+this.type+"('setValue','"+this.value+"');}");
			//builder.append(",value:'").append(this.value).append("'");
		}
		builder.append(",valueField:'value',textField:'text'").append("\"");

		builder.append(" />");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getMissingMessage() {
		return missingMessage;
	}

	public void setMissingMessage(String missingMessage) {
		this.missingMessage = missingMessage;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConstName() {
		return constName;
	}

	public void setConstName(String constName) {
		this.constName = constName;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

}