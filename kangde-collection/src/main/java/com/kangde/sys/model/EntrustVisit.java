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
public class EntrustVisit extends BizModel<String> {

	/** 产品ID */
	private String entrustProductId;
	/** 联系人 */
    private String contactName;
    /** 联系时间 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date contactTime;
	/** 沟通方式 */
    private String communicateMode;
    /** 下次跟进时间 */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private Date nextFollowTime;
    /** 洽淡目标 */
    private String negotiationTarget;
    /** 洽淡进度 */
    private String negotiationSchedule;
    

    public String getEntrustProductId() {
		return entrustProductId;
	}

	public void setEntrustProductId(String entrustProductId) {
		this.entrustProductId = entrustProductId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getContactTime() {
		return contactTime;
	}

	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}

	public String getCommunicateMode() {
		return communicateMode;
	}

	public void setCommunicateMode(String communicateMode) {
		this.communicateMode = communicateMode;
	}

	public Date getNextFollowTime() {
		return nextFollowTime;
	}

	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}

	public String getNegotiationTarget() {
		return negotiationTarget;
	}

	public void setNegotiationTarget(String negotiationTarget) {
		this.negotiationTarget = negotiationTarget;
	}

	public String getNegotiationSchedule() {
		return negotiationSchedule;
	}

	public void setNegotiationSchedule(String negotiationSchedule) {
		this.negotiationSchedule = negotiationSchedule;
	}
}