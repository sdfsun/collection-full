package com.kangde.sys.model;

import com.kangde.commons.model.BaseModel;
/**
 * 模板模型
 * @author wcy
 * @date 2016年6月14日17:36:30
 *
 */
@SuppressWarnings("serial")
public class TemplateModel extends BaseModel<String>{
	/** 模板名称 */
    private String name;
    /** 所属模块 */
    private String gropNo;
    /** 是否启用 0否 1是 */
    private Integer state;
    /** 创建人*/
    private String createEmpId;
    /** 修改人 */
    private String modifyEmpId;
    /** 字段名称 对应sys_column主键*/
    private String sysColumnIds;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGropNo() {
		return gropNo;
	}
	public void setGropNo(String gropNo) {
		this.gropNo = gropNo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateEmpId() {
		return createEmpId;
	}
	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}
	public String getModifyEmpId() {
		return modifyEmpId;
	}
	public void setModifyEmpId(String modifyEmpId) {
		this.modifyEmpId = modifyEmpId;
	}
	public String getSysColumnIds() {
		return sysColumnIds;
	}
	public void setSysColumnIds(String sysColumnIds) {
		this.sysColumnIds = sysColumnIds;
	}
    

   
}