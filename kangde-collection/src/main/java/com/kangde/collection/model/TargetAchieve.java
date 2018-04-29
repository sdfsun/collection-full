package com.kangde.collection.model;

import java.math.BigDecimal;
import com.kangde.commons.model.BizModel;

/**
 * Model
 * @date 2016年12月13日 
 * @author zhangyj
 */
@SuppressWarnings("serial")
public class TargetAchieve extends BizModel<String>{


    private String orgId;

    private String empId;

    private String empName;

    private Double achieve;

    private Integer year;

    private Integer month;

    private String createName;


    private String modifyName;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Double getAchieve() {
        return achieve;
    }

    public void setAchieve(Double achieve) {
        this.achieve = achieve;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName == null ? null : modifyName.trim();
    }
}