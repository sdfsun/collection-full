package com.kangde.collection.model;


import com.kangde.commons.model.BaseModel;
/**
 * 协催Model
 * 
 * @author wcy
 *@date 2016年5月31日16:34:59
 */
@SuppressWarnings("serial")
public class HurryRecordModel extends BaseModel<String>{
    /** 协催业务类型 */
    private String hurCat;
    /** 案件表id */
    private String caseId;
    /** 协催操作内容 */
    private String content;
    /** 协催记录id 对应各业务表 */
    private String hurryRecordId;
    /** 创建人 ID*/
    private String createEmpId;
    private String operatorName;
	public String getHurCat() {
		return hurCat;
	}
	public void setHurCat(String hurCat) {
		this.hurCat = hurCat;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHurryRecordId() {
		return hurryRecordId;
	}
	public void setHurryRecordId(String hurryRecordId) {
		this.hurryRecordId = hurryRecordId;
	}
	public String getCreateEmpId() {
		return createEmpId;
	}
	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}