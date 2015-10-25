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
	private int balance;

	public AccountVO(String name, int balance) {
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
