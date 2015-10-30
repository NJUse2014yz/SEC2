package nju.sec.yz.ExpressSystem.vo;
import java.util.ArrayList;



/**
 * 
 * @author 周聪
 * 经营情况表，可以选择开始日期和结束日期，显示期间内所有的入款单和收款单信息。
 */
public class BussinessVO {

	private ArrayList<PaymentSheetVO> in;
	private ArrayList<OutVO> out;
	
	
	
	
	public BussinessVO() {
		super();
		this.in = new ArrayList<>();
		this.out =new ArrayList<>();
	}
	public ArrayList<PaymentSheetVO> getIn() {
		return in;
	}
	public ArrayList<OutVO> getOut() {
		return out;
	}
	
	public void addInVO(PaymentSheetVO inVO){
		in.add(inVO);
	}
	
	public void addOutVO(OutVO outVO){
		out.add(outVO);
	}
	
	
}
