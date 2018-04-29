package com.kangde.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;
/**
 * 委托方Model
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class EntrustProduct extends BizModel<String> {

	/** 案源地Id */
	private String caseSourceId;
	
    /** 案件类型 */
    private String caseTypeId;
    /** 手次 */
    private String handle;
    /** 简码 */
    private String code;
    /** 客户类型 */
    private String cusType;
    /** 费率 */
    private Double rate;
    /** 合同签约日期 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date contractAwardDate;
    /** 合同周期 */
    private String contractCycle;
    /** 客服总窗 */
    private String customServiceTotal;
    /** 服务项目 */
    private String serviceProject;
    /** 省 */
    private String areaId1;
    /** 市 */
    private String areaId2;
    /** 区县 */
    private String areaId3;
    /** 地址 */
    private String address;
    /** 营业员 */
    private String staff;
    /** 创建人 */
    private String createEmpName;
    /** 修改人 */
    private String modifyName;
    //2016.12.19新增字段
    /** 委托方简称 */
    private String entrustAbbrevia;
    /** 主体 */
    private String productCategoryId;
    /** 担保方式 */
    private String guaranteeWayId;
    /** 用途 */
    private String purposeId;
   
	private String ecs_name;
    private String en_name;
    private String entrustId;
    private String ecsId;
    private String enId;
    private String user_name;
    private String userCus_name;
    private String staffName;
    private String caseTypeName;
    private String handleName;
    
    public String getEntrustAbbrevia() {
		return entrustAbbrevia;
	}

	public void setEntrustAbbrevia(String entrustAbbrevia) {
		this.entrustAbbrevia = entrustAbbrevia;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getGuaranteeWayId() {
		return guaranteeWayId;
	}

	public void setGuaranteeWayId(String guaranteeWayId) {
		this.guaranteeWayId = guaranteeWayId;
	}

	public String getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}

    public String getCaseSourceId() {
        return caseSourceId;
    }

    public void setCaseSourceId(String caseSourceId) {
        this.caseSourceId = caseSourceId == null ? null : caseSourceId.trim();
    }

    public String getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId == null ? null : caseTypeId.trim();
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle == null ? null : handle.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType == null ? null : cusType.trim();
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getContractAwardDate() {
        return contractAwardDate;
    }

    public void setContractAwardDate(Date contractAwardDate) {
        this.contractAwardDate = contractAwardDate;
    }

    public String getContractCycle() {
        return contractCycle;
    }

    public void setContractCycle(String contractCycle) {
        this.contractCycle = contractCycle == null ? null : contractCycle.trim();
    }

    public String getCustomServiceTotal() {
        return customServiceTotal;
    }

    public void setCustomServiceTotal(String customServiceTotal) {
        this.customServiceTotal = customServiceTotal == null ? null : customServiceTotal.trim();
    }

    public String getServiceProject() {
        return serviceProject;
    }

    public void setServiceProject(String serviceProject) {
        this.serviceProject = serviceProject == null ? null : serviceProject.trim();
    }

    public String getAreaId1() {
        return areaId1;
    }

    public void setAreaId1(String areaId1) {
        this.areaId1 = areaId1 == null ? null : areaId1.trim();
    }

    public String getAreaId2() {
        return areaId2;
    }

    public void setAreaId2(String areaId2) {
        this.areaId2 = areaId2 == null ? null : areaId2.trim();
    }

    public String getAreaId3() {
        return areaId3;
    }

    public void setAreaId3(String areaId3) {
        this.areaId3 = areaId3 == null ? null : areaId3.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff == null ? null : staff.trim();
    }

    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName == null ? null : createEmpName.trim();
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName == null ? null : modifyName.trim();
    }

	public String getEcs_name() {
		return ecs_name;
	}

	public void setEcs_name(String ecs_name) {
		this.ecs_name = ecs_name;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getEcsId() {
		return ecsId;
	}

	public void setEcsId(String ecsId) {
		this.ecsId = ecsId;
	}

	public String getEnId() {
		return enId;
	}

	public void setEnId(String enId) {
		this.enId = enId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUserCus_name() {
		return userCus_name;
	}

	public void setUserCus_name(String userCus_name) {
		this.userCus_name = userCus_name;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
}