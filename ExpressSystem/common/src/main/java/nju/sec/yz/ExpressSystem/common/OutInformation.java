package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class OutInformation  implements Serializable{
		//付款日期
		private String date;
		
		//付款金额
		private double num;
		
		//付款人
		private String person;
		
		//付款账号
		private String account;
		
		//条目（租金（按年收) 运费（按次计算）人员工资（按月统计）奖励（一次性））
		private String reason;
		
		//备注（租金年份、运单号、标注工资月份）
		private String comments;
		
		public OutInformation() {
			// TODO Auto-generated constructor stub
		}
		
		
		
		public OutInformation(OutInformation info) {
			super();
			this.date = info.getDate();
			this.num = info.getNum();
			this.person = info.getPerson();
			this.account = info.getAccount();
			this.reason = info.getReason();
			this.comments = info.getComments();
		}



		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public double getNum() {
			return num;
		}

		public void setNum(double num) {
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
