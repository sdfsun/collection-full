package com.kangde.collection.model;
import com.kangde.commons.model.BaseModel;
/**
 * 
  * @Description: 案件联系人电话Model
  * @author lidengwen
  * @date 2016年6月8日 下午5:42:01
  *
 */
public class CasePhoneModel extends BaseModel<String> {
	private static final long serialVersionUID = 4044790978859843319L;

	/**电话状态 0未知 1有效 2无效 */
	private Integer phoneStatus;

	/** 联系人 */
	private String name;

	
	/** 电话号码 */
	private String number;

	
	private String caseId;

	/** 电话类别 对应字典表ID*/
	private Long typeId;

	/** 电话数*/
	private Integer count;

	/** 电话批注*/
	private String remark;

	/** 处理状态*/
	private Integer isdel;

	/** 是否为新*/
	private Integer isnew;

	/** 关系*/
	private String relation;
	/** 创建人*/
	private String createEmpId;
	/** 修改人*/
	private String modifyEmpId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	
	public Integer getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(Integer phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId == null ? null : caseId.trim();
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation == null ? null : relation.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
	

}