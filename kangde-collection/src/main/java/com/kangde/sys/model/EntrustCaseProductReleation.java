package com.kangde.sys.model;
import com.kangde.commons.model.BizModel;
/**
 * 案源地Model
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class EntrustCaseProductReleation extends BizModel<String> {
	
	/** 委托方ID */
    private String entrustId;
    /** 简码 */
    private String code;

    private String productId;

    private String caseSourceId;

    /** 案源地名称 */
    private String caseSourceName;

    private String caseTypeId;

    private String caseTypeName;

    private String handleId;

    private String handleName;

    private String entrustName;
    
    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getCaseSourceId() {
        return caseSourceId;
    }

    public void setCaseSourceId(String caseSourceId) {
        this.caseSourceId = caseSourceId == null ? null : caseSourceId.trim();
    }

    public String getCaseSourceName() {
        return caseSourceName;
    }

    public void setCaseSourceName(String caseSourceName) {
        this.caseSourceName = caseSourceName == null ? null : caseSourceName.trim();
    }

    public String getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId == null ? null : caseTypeId.trim();
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName == null ? null : caseTypeName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId == null ? null : handleId.trim();
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName == null ? null : handleName.trim();
    }

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
    
}