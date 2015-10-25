package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

/**
 * 
 * @author 周聪
 *
 */
public class AccountPO implements Serializable{

	//账号
	private String name;
	
	//余额
	private int balance;

	public AccountPO(String name, int balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
}
