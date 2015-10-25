package nju.sec.yz.ExpressSystem.po;
import java.io.Serializable;


/**
 * @author 周聪
 * 所在地、编号、名称
 */

public class AgencyPO implements Serializable{

	//所在地
	private String location;
	
	//编号
	private String id;
	
	//名称
	private String name;

	public AgencyPO(String location, String id, String name) {
		super();
		this.location = location;
		this.id = id;
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
	
	
}
