package nju.sec.yz.ExpressSystem.vo;

/**
 * 
 * @author 周聪
 *
 */
public class AccountVO {

	// 账号
	private String name;

	// 余额
	private double balance;

	public AccountVO(String name, double balance) {
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

}
