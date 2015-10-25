package nju.sec.yz.ExpressSystem.po;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiveInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.common.TransitInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class DeliverPO {
	private OrderInformation ordermation; 
	private ArrayList<LoadInformation> officeLoads;
	private ArrayList<ArriveInformation> officeArrives;
	private ArrayList<ArriveInformation> transitArrives;
	private ArrayList<LoadInformation> transitLoads;
	private ArrayList<TransitInformation> transits;
	private InventoryInInformation inventoryIn;
	private InventoryOutInformation inventoryOut;
	private DeliveryInformation delivery;
	private PaymentInformation payment;
	private ReceiveInformation receive;
	
	public OrderInformation getOrdermation() {
		return ordermation;
	}
	public void setOrdermation(OrderInformation ordermation) {
		this.ordermation = ordermation;
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
	public InventoryInInformation getInventoryIn() {
		return inventoryIn;
	}
	public void setInventoryIn(InventoryInInformation inventoryIn) {
		this.inventoryIn = inventoryIn;
	}
	public InventoryOutInformation getInventoryOut() {
		return inventoryOut;
	}
	public void setInventoryOut(InventoryOutInformation inventoryOut) {
		this.inventoryOut = inventoryOut;
	}
	public DeliveryInformation getDelivery() {
		return delivery;
	}
	public void setDelivery(DeliveryInformation delivery) {
		this.delivery = delivery;
	}
	public PaymentInformation getPayment() {
		return payment;
	}
	public void setPayment(PaymentInformation payment) {
		this.payment = payment;
	}
	public ReceiveInformation getReceive() {
		return receive;
	}
	public void setReceive(ReceiveInformation receive) {
		this.receive = receive;
	}
	
}
