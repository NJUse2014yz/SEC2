package nju.sec.yz.ExpressSystem.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构列表
 * @author 周聪
 */
public class AgencyListVO {

	//中转中心列表
	public List<TransitVO> transits;
	
	//营业厅列表
	public List<PositionVO> positions;
	
	public AgencyListVO() {
		transits=new ArrayList<>();
		positions=new ArrayList<>();
	}
	public AgencyListVO(List<TransitVO> t,List<PositionVO> p) {
		transits=t;
		positions=p;
	}
}
