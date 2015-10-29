package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;
/**
 * 
 * @author 周聪    
 * @change YU Fan
 *	快递编号、入库日期、目的地、区号、排号、架号、位号；
 *	出库日期、目的地、装运形式（火车、飞机、汽车）、中转单编号或者汽运编号
 */
public class InventoryPO implements Serializable{

	private InventoryInInformation inventoryInformation;
	private InventoryOutInformation inventoryOutInformation;
	private String barId;

	

	public InventoryInInformation getInventoryInformation() {
		return inventoryInformation;
	}

	public void setInventoryInformation(InventoryInInformation inventoryInformation) {
		this.inventoryInformation = inventoryInformation;
	}

	public InventoryOutInformation getInventoryOutInformation() {
		return inventoryOutInformation;
	}

	public void setInventoryOutInformation(
			InventoryOutInformation inventoryOutInformation) {
		this.inventoryOutInformation = inventoryOutInformation;
	}

	public String getBarId() {
		return barId;
	}

	public void setBarId(String barId) {
		this.barId = barId;
	}
	
}
