package nju.sec.yz.ExpressSystem.client.rmi;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import nju.sec.yz.ExpressSystem.client.ClientInitException;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

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
		
		try {
			System.out.println("trying to connect again...");
			Thread.sleep(5000);
			RMIHelper.init();
			System.out.println("reconnect...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  catch (ClientInitException e) {
			e.printStackTrace();
			handleRMIException();
		}
	}
	
	static public void handleInitRMIException(){
		
		try {
			System.out.println("trying to get datafactory...");
			Thread.sleep(5000);
			RMIHelper.init();
			ClientControler control = new ClientControler();
			control.showFrame();
			System.out.println("recnnected!");
		
			
		} catch (ClientInitException e) {
			handleInitRMIException();
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static private void showWarning(){
		JOptionPane.showMessageDialog(
                null,
                ""
                + "网络不稳定，请稍后重试! " ,
                "Fatal Error",
                JOptionPane.ERROR_MESSAGE
        );
	}
	
	
	
}
