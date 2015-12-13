package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	private String barId;//标识快递的十位数字条形码号
	
	private List<String> trails;//物流轨迹
	
	private String next;//物流轨迹下个机构的id
	
	/**
	 * 填写寄件单以后开始生成PO
	 * 处于待审批状态
	 */
	public DeliverPO(String id){
		this.barId=id;
		this.state=DeliveryState.SUBMIT;
		this.trails=new ArrayList<>();
	}
	
	

	public DeliverPO(DeliveryState state, String barId, List<String> trails, String next) {
		super();
		this.state = state;
		this.barId = barId;
		this.trails=new ArrayList<>();
		this.trails.addAll(trails);
		this.next = next;
	}



	public void addTrail(String trail){
		trails.add(trail);
	}
	
	public List<String> getTrails() {
		return trails;
	}

	public void setTrails(List<String> trails) {
		this.trails.addAll(trails);
	}

	public DeliveryState getState() {
		return state;
	}
	public void setState(DeliveryState state) {
		this.state = state;
	}
	public String getBarId() {
		return barId;
	}
	public void setBarId(String id) {
		this.barId = id;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	
}
