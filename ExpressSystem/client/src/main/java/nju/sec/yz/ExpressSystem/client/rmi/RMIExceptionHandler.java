package nju.sec.yz.ExpressSystem.client.rmi;


/**
 * 处理RMI异常
 * @author 周聪
 *
 */
public class RMIExceptionHandler {

	/**
	 * 解决异常时设为true
	 * 解决异常后在initRMI设为false
	 */
	static boolean isHandling=false;
	
	/**
	 * 断网时提示出错
	 */
	static public void handleRMIException(){
		//正在解决异常
		if(isHandling){
			System.out.println("is handling...");
			return;
		}
			
		isHandling=true;
		RMIExceptionFrame view=new RMIExceptionFrame();
	}
	
	
	
	
	
}
