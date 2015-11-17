package nju.sec.yz.ExpressSystem.po;
import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.Status;;
/**
 * 
 * @author xiaosaisai
 *
 */
public class UserPO implements Serializable{
	private String id;
	private String name;
	private String password;
	private Status  power;
	public UserPO(String id,String name,String password,Status power){
		this.id=id;
		this.name=name;
		this.password=password;
		//power初始化
		this.power=power;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Status getPower() {
		return power;
	}
	public void setPower(Status power) {
		this.power = power;
	}
}
