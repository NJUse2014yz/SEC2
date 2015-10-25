package nju.sec.yz.ExpressSystem.common;
/**
 * 
 * @author YU Fan
 *
 */
import java.util.ArrayList;

public class OrderInformation {
	private SendInformation sendInformation;
	private DeliveryState deliveryState;
	private ArrayList<String> trail;
	
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
