package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
/**
 * 记录每个人已制作的单据的数量
 * @author 周聪
 *
 */

import nju.sec.yz.ExpressSystem.common.ReceiptType;
public class ReceiptCountPO implements Serializable{

	//相关人员的id
	private String id;
	

	//日期
	private String date;
	
	//表单类型
	private ReceiptType receiptType;
	
	//数量
	private int count;

	public ReceiptCountPO(String id, String date, ReceiptType receiptType) {
		super();
		this.id = id;
		this.date = date;
		this.receiptType = receiptType;
		count=1;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(ReceiptType receiptType) {
		this.receiptType = receiptType;
	}

	public void addCount(){
		count++;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
