package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.OutInformation;

/**
 * 
 * @author 周聪
 * @change YU Fan
 * 付款单信息：付款日期、付款金额、付款人、付款账号、条目（租金（按年收）
 * 运费（按次计算）人员工资（按月统计）奖励（一次性）），备注（租金年份、运单号、标注工资月份）。
 * （快递员提成、司机计次、业务员月薪）
 */
public class OutPO extends ReceiptPO implements Serializable{
	private OutInformation outInformation;

	public OutPO(String date, double num, String person, String account, String reason, String comments) {
		super();
		outInformation=new OutInformation();
		outInformation.setDate(date);
		outInformation.setNum(num);
		outInformation.setPerson(person);
		outInformation.setAccount(account);
		outInformation.setReason(reason);
		outInformation.setComments(comments);
	}
	
	public OutPO() {
		// TODO Auto-generated constructor stub
	}

	public OutInformation getOutInformation() {
		return outInformation;
	}

	public void setOutInformation(OutInformation outInformation) {
		this.outInformation = outInformation;
	}	
}
