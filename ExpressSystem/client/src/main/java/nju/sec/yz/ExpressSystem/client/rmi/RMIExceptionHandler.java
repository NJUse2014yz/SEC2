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
	 */
	static public void handleRMIException(){
		RMIExceptionFrame view=new RMIExceptionFrame();
	}
	
	
	
	
	
}
