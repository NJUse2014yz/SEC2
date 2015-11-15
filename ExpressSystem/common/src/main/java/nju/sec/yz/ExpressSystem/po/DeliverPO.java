package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.ReceiveInformation;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.TransitInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class DeliverPO implements Serializable{
	private DeliveryState state;
	
	private String id;//标识快递的十位数字条形码号
	
	private SendInformation sendInfo; 
	private ArrayList<LoadInformation> officeLoads;
	private ArrayList<ArriveInformation> officeArrives;
	private ArrayList<ArriveInformation> transitArrives;
	private ArrayList<LoadInformation> transitLoads;
	private ArrayList<TransitInformation> transits;
	private ArrayList<InventoryPO> inventory;
	private DeliveryInformation delivery;//派送信息
	private ReceiveInformation receive;
	
	//填写寄件单以后开始生成PO
	public DeliverPO(SendInformation sendInfo){
		this.id=sendInfo.getBarId();
		this.state=DeliveryState.GATHER;
		this.sendInfo=sendInfo;
	}
	
	
	public ArrayList<LoadInformation> getOfficeLoad() {
		return officeLoads;
	}
	public void setOfficeLoad(ArrayList<LoadInformation> officeLoads) {
		this.officeLoads = officeLoads;
	}
	public ArrayList<ArriveInformation> getOfficeArrive() {
		return officeArrives;
	}
	public void setOfficeArrive(ArrayList<ArriveInformation> officeArrives) {
		this.officeArrives = officeArrives;
	}
	public ArrayList<ArriveInformation> getTransitArrive() {
		return transitArrives;
	}
	public void setTransitArrive(ArrayList<ArriveInformation> transitArrives) {
		this.transitArrives = transitArrives;
	}
	public ArrayList<LoadInformation> getTransitLoad() {
		return transitLoads;
	}
	public void setTransitLoad(ArrayList<LoadInformation> transitLoads) {
		this.transitLoads = transitLoads;
	}
	public ArrayList<TransitInformation> getTransit() {
		return transits;
	}
	public void setTransit(ArrayList<TransitInformation> transit) {
		this.transits = transit;
	}
	public ArrayList<InventoryPO> getInventoryIn() {
		return inventory;
	}
	public void setInventoryIn(ArrayList<InventoryPO> inventory) {
		this.inventory = inventory;
	}
	public DeliveryInformation getDelivery() {
		return delivery;
	}
	public void setDelivery(DeliveryInformation delivery) {
		this.delivery = delivery;
	}
	public ReceiveInformation getReceive() {
		return receive;
	}
	public void setReceive(ReceiveInformation receive) {
		this.receive = receive;
	}
	public SendInformation getSendImfo() {
		return sendInfo;
	}
	public void setSendImfo(SendInformation sendImfo) {
		this.sendInfo = sendImfo;
	}
	public DeliveryState getState() {
		return state;
	}
	public void setState(DeliveryState state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
