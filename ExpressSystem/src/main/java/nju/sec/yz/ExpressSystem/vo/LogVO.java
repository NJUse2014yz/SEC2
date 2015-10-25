package nju.sec.yz.ExpressSystem.vo;

/**
 * 
 * @author 周聪
 *
 *         日志信息
 */
public class LogVO {

	// 操作时间（年月日时分 2015 10 25 16 24）
	private String time;

	// 操作名称
	private String operation;

	// 操作人，用id标识
	private String person;

	public LogVO(String time, String operation, String person) {
			super();
			this.time = time;
			this.operation = operation;
			this.person = person;
		}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
}
