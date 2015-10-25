package nju.sec.yz.ExpressSystem.po;
import nju.sec.yz.ExpressSystem.Power;

public class UserPO {
	private String id;
	private String name;
	private String password;
	private Power  power;
	public UserPO(String id,String name,String password,Power power){
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
	public Power getPower() {
		return power;
	}
	public void setPower(Power power) {
		this.power = power;
	}
}
