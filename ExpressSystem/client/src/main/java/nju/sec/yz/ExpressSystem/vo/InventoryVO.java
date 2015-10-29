package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;

/**
 * 
 * @author 周聪
 *
 */
public class InventoryVO {

	//出库信息
	private InventoryOutInformation inventoryOutInformation;
	
	//入库信息
	private InventoryInInformation inventoryInInformation;
    
	//订单条形码号
	private String barId;
	
	public InventoryInInformation getInventoryInInformation() {
		return inventoryInInformation;
	}
	public void setInventoryInInformation(
			InventoryInInformation inventoryInInformation) {
		this.inventoryInInformation = inventoryInInformation;
	}
	public String getBarId() {
		return barId;
	}
	
	public InventoryOutInformation getInventoryOutInformation() {
		return inventoryOutInformation;
	}
	public void setInventoryOutInformation(InventoryOutInformation inventoryOutInformation) {
		this.inventoryOutInformation = inventoryOutInformation;
	}
	
}
