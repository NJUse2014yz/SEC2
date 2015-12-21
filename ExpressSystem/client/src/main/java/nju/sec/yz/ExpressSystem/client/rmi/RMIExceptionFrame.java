package nju.sec.yz.ExpressSystem.client.rmi;

import javax.swing.JFrame;



public class RMIExceptionFrame {
	/**
	 * frame大小
	 */
	static final int HEIGHT=250;
	static final int WIDTH=320;
	
	private JFrame frame;
	
	private RMIExceptionPanel panel;
	
	
	public  RMIExceptionFrame() {
		frame=new JFrame("Internet Exception");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(frame);
		
		panel=new RMIExceptionPanel(frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	
	
	public void exit(){
		frame.setVisible(false);
	}
	
	public static void main(String[] args) {
		RMIExceptionFrame frame=new RMIExceptionFrame();
	}
	
	
}
