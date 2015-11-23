package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 中转中心的数据
 * @author 周聪
 *
 */
public class TransitPO implements Serializable{

	//
	private String name;
	
	//中转中心编号规则：025城市编码+0一位数字
	private String id;
	
	//下属的营业厅
	private List<PositionPO> positions;
	
	//所在地
	private String location;

	public TransitPO(String name, String id, List<PositionPO> positions, String location) {
		super();
		this.name = name;
		this.id = id;
		this.positions = positions;
		this.location = location;
	}
	
	public TransitPO(String name, String id, String location) {
		super();
		this.name = name;
		this.id = id;
		this.positions = new ArrayList<>();
		this.location = location;
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

	public List<PositionPO> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionPO> positions) {
		this.positions = positions;
	}
	
	public void addPositions(PositionPO po){
		positions.add(po);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
