package nju.sec.yz.ExpressSystem.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author 周聪
 * 经营情况表，可以选择开始日期和结束日期，显示期间内所有的入款单和收款单信息。
 */
public class BussinessVO {

	private List<PaymentSheetVO> in;
	private List<OutVO> out;
	
	
	
	
	public BussinessVO() {
		super();
		this.in = new ArrayList<>();
		this.out =new ArrayList<>();
	}
	public List<PaymentSheetVO> getIn() {
		return in;
	}
	public List<OutVO> getOut() {
		return out;
	}
	
	public void addInVO(PaymentSheetVO inVO){
		in.add(inVO);
	}
	
	public void addOutVO(OutVO outVO){
		out.add(outVO);
	}
	
	
}
