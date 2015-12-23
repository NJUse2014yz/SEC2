package nju.sec.yz.ExpressSystem.client.rmi;

import nju.sec.yz.ExpressSystem.client.ClientInitException;

public class InitRMI {
	public void initForever(RMIExceptionFrame frame){
		new InitThread(frame);
	}
	
	private class InitThread extends Thread{
		private RMIExceptionFrame frame;
		public InitThread(RMIExceptionFrame frame){
			this.start();
			this.frame=frame;
		}
		
		@Override
		public void run(){
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
			frame.exit();
			RMIExceptionHandler.isHandling=false;
		}
			
		
	}
	
}
