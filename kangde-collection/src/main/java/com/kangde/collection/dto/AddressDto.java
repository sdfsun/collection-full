package com.kangde.collection.dto;
public class AddressDto {
	private String provinceName;
	private String cityName;
	private String areaName;

	
	public AddressDto() {
		super();
	}

	public AddressDto(String provinceName, String cityName, String areaName) {
		super();
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Override
	public String toString() {
		return provinceName +  cityName +  areaName ;
	}

}
