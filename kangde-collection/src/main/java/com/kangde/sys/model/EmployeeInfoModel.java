package com.kangde.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kangde.commons.model.BizModel;
import com.kangde.commons.util.DateUtil;

/**
 * 员工信息类
 * 
 * @author zhangyj
 * @date 2016.5.5
 */
@SuppressWarnings("serial")
public class EmployeeInfoModel extends BizModel<String> {
	/** 员工姓名 */
	private String userName;
	/** 身份证号 */
	private String personalId;
	/** 性别 */
	private Integer sex;
	/** 生日 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date birthday;
	/** 民族 */
	private String nation;
	/** 婚姻状况 */
	private Integer married;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 邮箱 */
	private String email;
	/** 入职时间 */
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date joinTime;
	/** 岗位号 */
	private String positionId;
	/** 机构号 */
	private String orgId;
	/** 登录名称（账户） */
	private String loginName;
	/** 登录密码 */
	private String password;
	/** 密码盐(用于MD5计算) */
	private String salt;
	/** 员工编号 */
	private String number;
	/** 机构名称 */
	private String orgName;
	/** 坐席用户 */
	private String ccLogin;
	/** 坐席密码 */
	private String ccPwd;
	/** 服务地址 */
	private String ccServer;
	/** 坐席号 */
	private String ccPhone;
	/** 职位信息 */
	PositionModel positionModel;
	/** 联系方式 */
	private String contactMode;
	/** 附加区域 */
	private String attachOrgId;
	/** 附加区域 */
	private String attachEntrustId;
	
	/** 岗位名称 */
	private String posiName;
	private String orgAttachName;
	/** 区域名称 */
	private String orgNames;
	/** 区域ID,逗号分割 */
	private String orgIds;
	
	private String einame;
	private String cpName;
	private String achieve;
	private String paidAchieve;
	private String cpAchieve;
	private String distance;
	private String rate;
	
	
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPaidAchieve() {
		return paidAchieve;
	}

	public void setPaidAchieve(String paidAchieve) {
		this.paidAchieve = paidAchieve;
	}

	public String getCpAchieve() {
		return cpAchieve;
	}

	public void setCpAchieve(String cpAchieve) {
		this.cpAchieve = cpAchieve;
	}

	public String getEiname() {
		return einame;
	}

	public void setEiname(String einame) {
		this.einame = einame;
	}

	//help
	/** 角色ID */
	@JsonIgnore
	private String [] roleIds;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getMarried() {
		return married;
	}

	public void setMarried(Integer married) {
		this.married = married;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCcLogin() {
		return ccLogin;
	}

	public void setCcLogin(String ccLogin) {
		this.ccLogin = ccLogin;
	}

	public String getCcPwd() {
		return ccPwd;
	}

	public void setCcPwd(String ccPwd) {
		this.ccPwd = ccPwd;
	}

	public String getCcServer() {
		return ccServer;
	}

	public void setCcServer(String ccServer) {
		this.ccServer = ccServer;
	}

	public String getCcPhone() {
		return ccPhone;
	}

	public void setCcPhone(String ccPhone) {
		this.ccPhone = ccPhone;
	}

	public PositionModel getPositionModel() {
		return positionModel;
	}

	public void setPositionModel(PositionModel positionModel) {
		this.positionModel = positionModel;
	}

	public String getContactMode() {
		return contactMode;
	}

	public void setContactMode(String contactMode) {
		this.contactMode = contactMode;
	}

	public String getAttachOrgId() {
		return attachOrgId;
	}

	public void setAttachOrgId(String attachOrgId) {
		this.attachOrgId = attachOrgId;
	}

	public String getPosiName() {
		return posiName;
	}

	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}

	public String getOrgAttachName() {
		return orgAttachName;
	}

	public void setOrgAttachName(String orgAttachName) {
		this.orgAttachName = orgAttachName;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String [] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String [] roleIds) {
		this.roleIds = roleIds;
	}

	public String getAttachEntrustId() {
		return attachEntrustId;
	}

	public void setAttachEntrustId(String attachEntrustId) {
		this.attachEntrustId = attachEntrustId;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeInfoModel other = (EmployeeInfoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getAchieve() {
		return achieve;
	}

	public void setAchieve(String achieve) {
		this.achieve = achieve;
	}

}