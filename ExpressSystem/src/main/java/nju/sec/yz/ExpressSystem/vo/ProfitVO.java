package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author 周聪
 * 成本收益表
 */
public class ProfitVO {
	//总收入
	private int in;
	
	//总支出
	private int out;
	
	//总利润
	private int profit;

	public ProfitVO(int in, int out, int profit) {
		super();
		this.in = in;
		this.out = out;
		this.profit = profit;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getOut() {
		return out;
	}

	public void setOut(int out) {
		this.out = out;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	
	
	
	
	
}
