package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class GoodInformation  implements Serializable{
	private String total;
	private String weight;
	private String vloume;
	private String name;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVloume() {
		return vloume;
	}
	public void setVloume(String vloume) {
		this.vloume = vloume;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
