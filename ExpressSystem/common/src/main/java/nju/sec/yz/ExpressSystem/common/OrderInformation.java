package nju.sec.yz.ExpressSystem.common;
import java.io.Serializable;
/**
 * 
 * @author YU Fan
 *
 */
import java.util.ArrayList;

public class OrderInformation  implements Serializable{
	private SendInformation sendInformation;
	private DeliveryState deliveryState;
	private ArrayList<String> trail=null;
	
	public SendInformation getSendInformation() {
		return sendInformation;
	}
	public void setSendInformation(SendInformation sendInformation) {
		this.sendInformation = sendInformation;
	}
	public ArrayList<String> getTrail() {
		return trail;
	}
	public void setTrail(ArrayList<String> trail) {
		this.trail = trail;
	}
	public DeliveryState getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(DeliveryState deliveryState) {
		this.deliveryState = deliveryState;
	}
	
}
