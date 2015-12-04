package nju.sec.yz.ExpressSystem.vo;
/**
 * 营业厅
 * @author 周聪
 *
 */
public class PositionVO {
	public String name;
	
	public String id;
	
	public String transitId;
	
	public String location;
	
	
	public PositionVO(String name, String id, String transitId, String location) {
		super();
		this.name = name;
		this.id = id;
		this.transitId = transitId;
		this.location = location;
	}
	
	public PositionVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransitId() {
		return transitId;
	}

	public void setTransitId(String transitId) {
		this.transitId = transitId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
