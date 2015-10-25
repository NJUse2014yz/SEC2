package nju.sec.yz.ExpressSystem.vo;



import nju.sec.yz.ExpressSystem.Power;

public class UserVO {
	private String id;
	private String name;
	private String password;
	private Power  power;
	public UserVO(String id,String name,String password,Power power){
		this.id=id;
		this.name=name;
		this.password=password;
		//power初始化
		this.power=power;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Power getPower() {
		return power;
	}
	
}

