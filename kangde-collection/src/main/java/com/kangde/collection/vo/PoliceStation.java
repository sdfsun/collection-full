package com.kangde.collection.vo;
/**
 * 派出所
 * 用于解析jsoin数据
 * @author wangcy
 */
public class PoliceStation {
	/** 派出所名称*/
	public String name;
	/** 区名*/
	public String adname;
	/** 详细地址*/
	public String address;
	/** 城市名称*/
	public String cityname;
	/** 电话*/
	public String tel;
	/** GPS坐标*/
	public String location;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
