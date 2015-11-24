package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

/**
 * 
 * @author 周聪
 *
 */
public class CarPO implements Serializable{

	// 车辆代号
	private String id;
	// 车牌号（苏A 00000）
	private String number;
	// 服役时间(年)
	private int worktime=0;
	//购买时间
	private String buytime;
	//发动机号
	private String mechine;
	//底盘号
	private String dipan;
	
	public CarPO(String id, String number, String buytime, String mechine,
			String dipan) {
		super();
		this.id = id;
		this.number = number;
		this.buytime = buytime;
		this.mechine = mechine;
		this.dipan = dipan;
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
	public int getWorktime() {
		return worktime;
	}
	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}
	public String getBuytime() {
		return buytime;
	}
	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	public String getMechine() {
		return mechine;
	}
	public void setMechine(String mechine) {
		this.mechine = mechine;
	}
	public String getDipan() {
		return dipan;
	}
	public void setDipan(String dipan) {
		this.dipan = dipan;
	}
	
	
		
}
