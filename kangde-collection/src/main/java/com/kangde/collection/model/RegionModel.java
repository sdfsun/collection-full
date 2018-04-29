package com.kangde.collection.model;

import com.kangde.commons.model.BaseModel;

/**
 * 三级地址区域表 模型
 * 
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class RegionModel extends BaseModel<Integer> {

	/** 区域名称 */
	private String name;
	/** 所属机构 */
	private Integer administrationCode;
	/** 所属机构 */
	private String fatherName;
	/** 所属市代码 */
	private String cityCode;
	/** 区域等级 */
	private Integer areaGrade;
	/** 父级id */
	private Integer parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAdministrationCode() {
		return administrationCode;
	}

	public void setAdministrationCode(Integer administrationCode) {
		this.administrationCode = administrationCode;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getAreaGrade() {
		return areaGrade;
	}

	public void setAreaGrade(Integer areaGrade) {
		this.areaGrade = areaGrade;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
