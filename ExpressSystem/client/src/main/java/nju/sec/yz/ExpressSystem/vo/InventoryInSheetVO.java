package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *入库单
 */
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;

public class InventoryInSheetVO extends ReceiptVO{
	private InventoryInInformation inventoryInInformation;
	private String barId;
	
	public InventoryInSheetVO(InventoryInInformation inventoryInInformation,
			String barId) {
		super();
		this.inventoryInInformation = inventoryInInformation;
		this.barId = barId;
	}
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
