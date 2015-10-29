package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *出库单
 */
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;

public class InventoryOutSheetVO extends ReceiptVO{
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
