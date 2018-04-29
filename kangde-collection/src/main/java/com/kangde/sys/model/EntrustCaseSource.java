package com.kangde.sys.model;
import com.kangde.commons.model.BizModel;
/**
 * 案源地Model
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class EntrustCaseSource extends BizModel<String> {
	
	/** 委托方ID */
    private String entrustId;
    /** 案源地名称 */
    private String name;
    /** 简码 */
    private String code;
    /** 修改人 */
    private String modifyName;
    private String createEmpName;
    
    private String contract_name;
    private String en_code;
    private String en_name;
    private String ecs_name;
    private String user_name;
  

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getCreateEmpName() {
		return createEmpName;
	}

	public void setCreateEmpName(String createEmpName) {
		this.createEmpName = createEmpName;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getEn_code() {
		return en_code;
	}

	public void setEn_code(String en_code) {
		this.en_code = en_code;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public String getEcs_name() {
		return ecs_name;
	}

	public void setEcs_name(String ecs_name) {
		this.ecs_name = ecs_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}