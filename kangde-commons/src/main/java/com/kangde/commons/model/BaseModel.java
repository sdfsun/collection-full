package com.kangde.commons.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.kangde.commons.CoreConst;

/**
 * Model模型基类
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public abstract class BaseModel<PK> implements Serializable {

	/** ID */
	protected PK id;
	/** 创建时间 */
	protected Date createTime;
	/** 最后一次修改时间 */
	protected Date modifyTime;
	/** 版本,用于控制乐观锁 */
	protected Integer version = 0;
	/** 系统状态 */
	protected Integer status = CoreConst.STATUS_NORMAL;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/*========================== 重写 equals hashCode toString ==========================*/
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		boolean result = false;
        if (obj instanceof BaseModel) {
            BaseModel objModel = (BaseModel) obj;
            if(this.getId()==null || objModel.getId() == null || 
            	(this.getId() instanceof String && StringUtils.isBlank(this.getId().toString()))||
            	(objModel.getId() instanceof String && StringUtils.isBlank(objModel.getId().toString()))){
            	return false;
            }
            result = new EqualsBuilder()
                    .append(this.getId(), objModel.getId())
                    .isEquals();
        }
        return result; 
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
			.append(getId()).toHashCode();
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

}