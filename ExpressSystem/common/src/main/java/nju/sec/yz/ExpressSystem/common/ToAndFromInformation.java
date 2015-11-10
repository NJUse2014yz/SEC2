package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *	寄件人和收件人信息
 */
public class ToAndFromInformation  implements Serializable{
	public ToAndFromInformation(String name, String address, String organizaion, String telephone, String cellphone) {
		super();
		this.name = name;
		this.address = address;
		this.organizaion = organizaion;
		this.telephone = telephone;
		this.cellphone = cellphone;
	}
	private String name;
	private String address;
	private String organizaion;
	private String telephone;
	private String cellphone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrganizaion() {
		return organizaion;
	}
	public void setOrganizaion(String organizaion) {
		this.organizaion = organizaion;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
