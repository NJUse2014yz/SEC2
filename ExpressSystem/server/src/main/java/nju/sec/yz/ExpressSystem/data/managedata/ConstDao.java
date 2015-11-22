package nju.sec.yz.ExpressSystem.data.managedata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

/**
 * 保存价格常量和所有距离常量
 * @author 周聪
 *
 */
public class ConstDao implements Serializable{

	private List<CityPO> distances;
	
	private PricePO price;

	public ConstDao() {
		distances=new ArrayList<>();
		price=new PricePO();
	}
	
	public List<CityPO> getDistances() {
		return distances;
	}
	
	public void setDistances(List<CityPO> distances) {
		this.distances = distances;
	}

	public PricePO getPrice() {
		return price;
	}

	public void setPrice(PricePO price) {
		this.price = price;
	}
	
	
}
