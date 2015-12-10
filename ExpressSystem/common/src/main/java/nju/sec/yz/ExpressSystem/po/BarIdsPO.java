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

	String fromAgency;//出发地名称
	
	String destinationId;//目的地id

	public BarIdsPO(List<String> barIds, String receiptId, String fromAgency, String destinationId) {
		super();
		this.barIds = barIds;
		this.receiptId = receiptId;
		this.fromAgency = fromAgency;
		this.destinationId = destinationId;
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

	public String getFromAgency() {
		return fromAgency;
	}

	public void setFromAgency(String fromAgency) {
		this.fromAgency = fromAgency;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	
	
	
}
