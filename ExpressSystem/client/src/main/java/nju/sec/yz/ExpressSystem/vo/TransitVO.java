package nju.sec.yz.ExpressSystem.vo;

import java.util.List;

import nju.sec.yz.ExpressSystem.po.PositionPO;

/**
 * 中转中心
 * @author 周聪
 *
 */
public class TransitVO {

	//
	private String name;
	
	//中转中心编号规则：025城市编码+0一位数字
	private String id;
	
	//下属的营业厅
	private List<PositionVO> positions;
	
	//所在地
	private String location;

	public TransitVO(String name, String id,  String location) {
		
		this.name = name;
		this.id = id;
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

	public List<PositionVO> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionVO> positions) {
		this.positions = positions;
	}
	
	public void addPositions(PositionVO vo){
		positions.add(vo);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
