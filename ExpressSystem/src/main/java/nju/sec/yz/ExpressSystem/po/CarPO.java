package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

/**
 * 
 * @author 周聪
 *
 */
public class CarPO implements Serializable{

	//车辆代号、车牌号（苏A 00000）、服役时间	
	
	//车辆代号
	private String id;
	
	
	//车牌号（苏A 00000）
	private String number;
	
	//TODO 服役时间(年)
	private int time;
	
	//TODO 车辆图片待定
	
	
	public CarPO(String id, String number, int time) {
		super();
		this.id = id;
		this.number = number;
		this.time = time;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	
	
}
