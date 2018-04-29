package com.kangde.collection.dto;

/**
 * 用于贴案时候新增地址
 * @author wangcy
 *
 */
public class AddressModelDto  {

	/** 联系人家庭住址 */
	private String addressJtzz;
	/** 联系人户籍住址 */
	private String addressHjzz;
	/** 联系人单位名称 */
	private String addressDwmc;
	/** 联系人单位地址 */
	private String addressDwdz;
	public String getAddressJtzz() {
		return addressJtzz;
	}
	public void setAddressJtzz(String addressJtzz) {
		this.addressJtzz = addressJtzz;
	}
	public String getAddressHjzz() {
		return addressHjzz;
	}
	public void setAddressHjzz(String addressHjzz) {
		this.addressHjzz = addressHjzz;
	}
	public String getAddressDwmc() {
		return addressDwmc;
	}
	public void setAddressDwmc(String addressDwmc) {
		this.addressDwmc = addressDwmc;
	}
	public String getAddressDwdz() {
		return addressDwdz;
	}
	public void setAddressDwdz(String addressDwdz) {
		this.addressDwdz = addressDwdz;
	}
	
}
