package nju.sec.yz.ExpressSystem.client;

import javax.swing.JOptionPane;

/**
 * 处理RMI异常
 * @author 周聪
 *
 */
public class RMIExceptionHandler {

	
	/**
	 * 断网时提示出错
	 * 然后退出系统
	 */
	static public void handleRMIException(){
		JOptionPane.showMessageDialog(
                null,
                ""
                + "网络不稳定，请稍后重试! " ,
                "Fatal Error",
                JOptionPane.ERROR_MESSAGE
        );
		System.exit(1);
	}
	
	
}
