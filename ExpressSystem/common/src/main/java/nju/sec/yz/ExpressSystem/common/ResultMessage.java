package nju.sec.yz.ExpressSystem.common;
/**
 * 返回信息
 * @author 周聪
 */
public class ResultMessage {

	private Result result;
	
	private String message;//备注
	
	public ResultMessage(Result result){
		this.result=result;
		message="success";
	}
	
	public ResultMessage(Result result,String message){
		this.result=result;
		this.message=message;
	}

	
	public Result getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
	
	
}
