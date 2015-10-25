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
	private LoadInformation officeLoad;
	private ArriveInformation officeArrive;
	private ArriveInformation transitArrive;
	private LoadInformation transitLoad;
	private TransitInformation transit;
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
	public LoadInformation getOfficeLoad() {
		return officeLoad;
	}
	public void setOfficeLoad(LoadInformation officeLoad) {
		this.officeLoad = officeLoad;
	}
	public ArriveInformation getOfficeArrive() {
		return officeArrive;
	}
	public void setOfficeArrive(ArriveInformation officeArrive) {
		this.officeArrive = officeArrive;
	}
	public ArriveInformation getTransitArrive() {
		return transitArrive;
	}
	public void setTransitArrive(ArriveInformation transitArrive) {
		this.transitArrive = transitArrive;
	}
	public LoadInformation getTransitLoad() {
		return transitLoad;
	}
	public void setTransitLoad(LoadInformation transitLoad) {
		this.transitLoad = transitLoad;
	}
	public TransitInformation getTransit() {
		return transit;
	}
	public void setTransit(TransitInformation transit) {
		this.transit = transit;
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
