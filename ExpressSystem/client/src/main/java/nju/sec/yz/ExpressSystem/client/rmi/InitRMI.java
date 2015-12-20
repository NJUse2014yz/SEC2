package nju.sec.yz.ExpressSystem.client.rmi;

import nju.sec.yz.ExpressSystem.client.ClientInitException;

public class InitRMI {
	public void initForever(){
		while(true){
			try {
				System.out.println("trying to connect again...");
				Thread.sleep(5000);
				RMIHelper.init();
				System.out.println("reconnect...");
				break;
			} catch (ClientInitException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
