package com.kangde.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;

/**
 * 委托方-联系人Model
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class EntrustLinkman extends BizModel<String> {

	/** 产品ID */
	private String entrustProductId;
	/** 联系人姓名 */
	private String name;
	/** qq */
	private String qq;
	/** 微信 */
	private String wechat;
	/** 手机 */
	private String phone;
	/** 职位 */
	private String position;
	/** 邮件 */
	private String email;
	/** 备注 */
	private String remark;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getEntrustProductId() {
		return entrustProductId;
	}

	public void setEntrustProductId(String entrustProductId) {
		this.entrustProductId = entrustProductId;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
}