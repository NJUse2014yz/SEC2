package nju.sec.yz.ExpressSystem.vo;

/**
 * 
 * @author 周聪
 *
 */
public class CarVO {

	// 车辆代号
	private String id;

	// 车牌号（苏A 00000）
	private String number;

//	 TODO 服役时间(年)
	private int time;

	private String buytime;
	
	private String mechine;
	
	private String dipan;
	
	private String worktime;

	public CarVO(String id, String number, String buytime,String mechine,String dipan) {
		super();
		this.id = id;
		this.number = number;
		this.mechine=mechine;
		this.dipan=dipan;
		this.buytime=buytime;
	}
	public CarVO(String id,String number,int time)
	{
		super();
		this.id = id;
		this.number = number;
		this.time=time;
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
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

}
