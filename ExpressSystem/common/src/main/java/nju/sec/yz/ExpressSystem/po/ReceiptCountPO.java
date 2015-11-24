package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
/**
 * 记录每个人已制作的单据的数量
 * @author 周聪
 *
 */

import nju.sec.yz.ExpressSystem.common.IdType;
public class ReceiptCountPO implements Serializable{

	//相关人员的id
	private String id;
	

	//日期
	private String date;
	
	//id类型
	private IdType idType;
	
	//数量
	private int count;


	

	public ReceiptCountPO(String id, String date, IdType idType) {
		super();
		this.id = id;
		this.date = date;
		this.idType = idType;
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

	public IdType getReceiptType() {
		return idType;
	}

	public void setReceiptType(IdType idType) {
		this.idType = idType;
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
