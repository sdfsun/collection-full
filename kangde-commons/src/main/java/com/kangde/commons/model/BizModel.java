package com.kangde.commons.model;

/**
 * 业务模型
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public abstract class BizModel<PK> extends BaseModel<PK> {

	/** 最后一次修改人 ID */
	protected String modifyEmpId;
	/** 创建人ID */
	protected String createEmpId;
	/** 业务状态 */
	protected Integer state;

	public String getModifyEmpId() {
		return modifyEmpId;
	}

	public void setModifyEmpId(String modifyEmpId) {
		this.modifyEmpId = modifyEmpId;
	}

	public String getCreateEmpId() {
		return createEmpId;
	}

	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
