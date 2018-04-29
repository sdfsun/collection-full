package com.kangde.commons.easyui;

/**
 * 
 * combobox、combotree功能类型 全部、请选择  枚举类型.
 * <br>全部("all") 请选择("select")
 * 
 */
public enum SelectType {
	/** 全部("all") */
	all("all", "全部"),
	/** 请选择("select") */
	select("select", "请选择...");

	/**
	 * 值 String型
	 */
	private final String value;
	/**
	 * 描述 String型
	 */
	private final String description;

	SelectType(String value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取值
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
     * 获取描述信息
     * @return description
     */
	public String getDescription() {
		return description;
	}

	public static SelectType getSelectTypeValue(String value) {
		if (null == value)
			return null;
		for (SelectType _enum : SelectType.values()) {
			if (value.equals(_enum.getValue()))
				return _enum;
		}
		return select;
	}
	
	public static SelectType getSelectTypeDescription(String description) {
		if (null == description)
			return null;
		for (SelectType _enum : SelectType.values()) {
			if (description.equals(_enum.getDescription()))
				return _enum;
		}
		return null;
	}

}