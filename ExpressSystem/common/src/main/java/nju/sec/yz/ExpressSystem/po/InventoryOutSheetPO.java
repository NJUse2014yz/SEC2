package nju.sec.yz.ExpressSystem.po;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;

public class InventoryOutSheetPO extends ReceiptPO{
	private InventoryOutInformation inventoryOutInformation;
	private String barId;
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
