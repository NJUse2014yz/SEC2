package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class OutInformation  implements Serializable{
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
