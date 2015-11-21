package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.CityInformation;
/**
 * 
 * @author 周聪
 *
 *
 * 城市距离常量
 */
public class CityVO {

	private CityInformation cityInformation;
	
	public CityVO(CityInformation cityInformation) {
		super();
		this.cityInformation = cityInformation;
	}

	public CityInformation getCityInformation() {
		return cityInformation;
	}

	public void setCityInformation(CityInformation cityInformation) {
		this.cityInformation = cityInformation;
	}
}
