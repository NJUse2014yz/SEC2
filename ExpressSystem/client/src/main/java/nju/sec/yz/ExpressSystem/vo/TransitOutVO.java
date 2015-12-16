package nju.sec.yz.ExpressSystem.vo;

import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.TransportType;

/**
 * 中转中心转出信息
 * @author 周聪
 *
 */
public class TransitOutVO {

	public TransportType type;//运输方式
	
	public String destination;//目的地
	
	public List<String> barIds;//条形码号列表
	
	public TransitOutVO() {
		barIds=new ArrayList<>();
	}
	
}
