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
	
	private String id;//标识快递的十位数字条形码号
	
	private List<String> trails;//物流轨迹
	
	//填写寄件单以后开始生成PO
	public DeliverPO(String id){
		this.id=id;
		this.state=DeliveryState.GATHER;
		this.trails=new ArrayList<>();
	}
	
	public void addTrail(String trail){
		trails.add(trail);
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
