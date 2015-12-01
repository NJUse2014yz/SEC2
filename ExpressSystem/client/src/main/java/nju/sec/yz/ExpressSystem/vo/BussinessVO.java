package nju.sec.yz.ExpressSystem.vo;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author 周聪
 * 经营情况表，可以选择开始日期和结束日期，显示期间内所有的入款单和收款单信息。
 */
public class BussinessVO {

	public List<PaymentSheetVO> in;
	public List<OutVO> out;
	
	public BussinessVO() {
		super();
		this.in = new ArrayList<>();
		this.out =new ArrayList<>();
	}
	
	
	
}
