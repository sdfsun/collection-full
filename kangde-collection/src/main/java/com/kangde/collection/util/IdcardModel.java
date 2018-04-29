package com.kangde.collection.util;

/**
 * 身份证信息model
 */
public class IdcardModel {
	/** 户籍地址 */
	private String place;
	/** 出生年月日 */
	private String birthday;
	/** 性别 */
	private String gender;
	/** 身份证号,如果是15位身份证,记录转换后的身份证号 */
	private String idcardNum;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdcardNum() {
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}

	@Override
	public String toString() {
		return "IdcardModel [place:" + place + ", birthday:" + birthday + ", gender:" + gender + ", idcardNum="
				+ idcardNum + "]";
	}

}
