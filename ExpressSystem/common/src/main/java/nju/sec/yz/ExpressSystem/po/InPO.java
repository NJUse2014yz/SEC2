package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

/**
 * 
 * @author 周聪
 * 收款单信息：收款日期、收款金额、收款快递员
 */
public class InPO extends ReceiptPO implements Serializable{

	//收款日期
	private String date;
	
	//收款金额
	private int num;
	
	//收款快递员，用id标识
	private String deliver;
	
	//营业厅
	private String position;

	public InPO(String date, int num, String deliver,String position) {
		super();
		this.date = date;
		this.num = num;
		this.deliver = deliver;
		this.position=position;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDeliver() {
		return deliver;
	}

	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
	
}
