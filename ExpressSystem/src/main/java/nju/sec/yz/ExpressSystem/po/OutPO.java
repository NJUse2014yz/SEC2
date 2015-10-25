package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

/**
 * 
 * @author 周聪
 * 付款单信息：付款日期、付款金额、付款人、付款账号、条目（租金（按年收）
 * 运费（按次计算）人员工资（按月统计）奖励（一次性）），备注（租金年份、运单号、标注工资月份）。
 * （快递员提成、司机计次、业务员月薪）
 */
public class OutPO implements Serializable{

	//付款日期
	private String date;
	
	//付款金额
	private int num;
	
	//付款人
	private String person;
	
	//付款账号
	private String account;
	
	//条目（租金（按年收) 运费（按次计算）人员工资（按月统计）奖励（一次性））
	private String reason;
	
	//备注（租金年份、运单号、标注工资月份）
	private String comments;

	public OutPO(String date, int num, String person, String account, String reason, String comments) {
		super();
		this.date = date;
		this.num = num;
		this.person = person;
		this.account = account;
		this.reason = reason;
		this.comments = comments;
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

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
