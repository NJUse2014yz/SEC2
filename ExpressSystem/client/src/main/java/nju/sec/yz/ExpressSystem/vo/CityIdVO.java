package nju.sec.yz.ExpressSystem.vo;
/**
 * 城市编号和名称
 * @author 周聪
 *
 */
public class CityIdVO {

	private String name;
	
	private String id;

	public CityIdVO(String name, String id) {
		super();
		this.name = name;
		this.id = id;
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
	
	
	
}
