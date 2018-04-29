package com.kangde.collection.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;
/**
 * 案件批次管理Model
 * @author wcy
 */
@SuppressWarnings("serial")
public class CaseBatchModel extends BizModel<String>{
	
	/** 批次号*/
	private String batchCode;
	/** 委托方id*/
    private String entrustId;
	/** 总金额 */
    private Double totalMoney;
	/** 总户数*/
    private Integer totalNumber;
	/** 案件类型*/
    private String typeId;
	/** 委案开始日期 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date beginDate;
	/** 风控地区id */
    private Long areaId;
	/** 委案截止日期 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date endDate;
    /** 实际退案日期    */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualEndDate;
	/** 备注 */
    private String remark;
	/** 回款率 */
    private Double target;
    /** 风控方 */
    private int collectionId;
	/** 文件路径    */
	private String batchXls;
	/** 批次日志   */
	private String batchLog;
	/** 分配提示   */
	private String bathTips;
	/** 撤案说明   */
	private String wdcDesc;
	
	/** 是否上传 0未上传 1已上传 */
	private Integer isUpload = 0;
	
	/** 模板类型 */
	private String templateType;
	
	/** 上传时间  */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date uploadTime;
	
	/** 委托方名称   */
	private String sourceName;
	/** 委托方名称   */
	private String entrustName;
	/** 委托方名称   */
	private String entrustProductId;
	
	/** 业绩率  */
	private Double achieve;
	/** 手别  */
	private Integer handle;
	
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getTarget() {
		return target;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public String getBatchXls() {
		return batchXls;
	}
	public void setBatchXls(String batchXls) {
		this.batchXls = batchXls;
	}
	public String getBatchLog() {
		return batchLog;
	}
	public void setBatchLog(String batchLog) {
		this.batchLog = batchLog;
	}
	public String getBathTips() {
		return bathTips;
	}
	public void setBathTips(String bathTips) {
		this.bathTips = bathTips;
	}
	public String getWdcDesc() {
		return wdcDesc;
	}
	public void setWdcDesc(String wdcDesc) {
		this.wdcDesc = wdcDesc;
	}
	public String getEntrustName() {
		return entrustName;
	}
	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public Double getAchieve() {
		return achieve;
	}
	public void setAchieve(Double achieve) {
		this.achieve = achieve;
	}
	public Integer getHandle() {
		return handle;
	}
	public void setHandle(Integer handle) {
		this.handle = handle;
	}
	public String getEntrustProductId() {
		return entrustProductId;
	}
	public void setEntrustProductId(String entrustProductId) {
		this.entrustProductId = entrustProductId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	
}