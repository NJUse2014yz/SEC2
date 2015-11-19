package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class ReceiveInformation  implements Serializable{
	private String barID;//订单条形码号
	private String receiver;
	private String time;
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBarID() {
		return barID;
	}
	public void setBarID(String barID) {
		this.barID = barID;
	}
}
