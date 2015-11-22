package nju.sec.yz.ExpressSystem.vo;

/**
 * 
 * @author 周聪
 *  机构信息
 *
 */
public class AgencyVO {

	// 所在地
	private String location;

	// 编号
	private String id;

	// 名称
	private String name;

	public AgencyVO(String location, String id, String name) {
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
