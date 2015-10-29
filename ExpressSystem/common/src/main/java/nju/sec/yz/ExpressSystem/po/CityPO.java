package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.CityInformation;

public class CityPO implements Serializable{
	private CityInformation cityInformation;

	public CityInformation getCityInformation() {
		return cityInformation;
	}

	public void setCityInformation(CityInformation cityInformation) {
		this.cityInformation = cityInformation;
	}
}
