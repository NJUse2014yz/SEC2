package nju.sec.yz.ExpressSystem.po;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;

public class InventoryInSheetPO extends ReceiptPO{
	private InventoryInInformation inventoryInInformation;
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
	public void setBarId(String barId) {
		this.barId = barId;
	}
}
