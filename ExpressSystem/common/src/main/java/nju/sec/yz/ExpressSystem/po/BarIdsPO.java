package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.List;
/**
 * 中转单的所有条形码号
 * @author 周聪
 *
 */
public class BarIdsPO implements Serializable{

	List<String> barIds;
	
	String receiptId;//中转单id

	public BarIdsPO(List<String> barIds, String receiptId) {
		super();
		this.barIds = barIds;
		this.receiptId = receiptId;
	}

	public List<String> getBarIds() {
		return barIds;
	}

	public void setBarIds(List<String> barIds) {
		this.barIds = barIds;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	
	
	
}
