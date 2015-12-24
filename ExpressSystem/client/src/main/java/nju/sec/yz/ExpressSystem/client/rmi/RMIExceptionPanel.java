package nju.sec.yz.ExpressSystem.client.rmi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;

public class RMIExceptionPanel extends JPanel{
	
	private static final int IMAGE_SIZE=2;
	
	static final int WIDTH_OF_BUTTON=75;
	
	static final int HEIGHT_OF_BUTTON=30;
	
	private static final Image DOT=new ImageIcon("graphic/RMI/1.gif").getImage();
	
	
	
	private JButton connect;
	
	private JButton exit;
	
	private final RMIExceptionFrame frame;
	
	private int connectCounts=-1;
	
	private boolean isConnecting=false;
	
	private CountThread counter;
	
	public RMIExceptionPanel(final RMIExceptionFrame frame) {
		
		
		
		this.frame=frame;
		
		this.setLayout(null);
		connect=new newJBut("重连",Color.BLACK);
		connect.setBounds(RMIExceptionFrame.WIDTH/2-100,205,WIDTH_OF_BUTTON,HEIGHT_OF_BUTTON);
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isConnecting)
					return;
				isConnecting=true;
				InitRMI model=new InitRMI();
				model.initForever(frame);
				
				connectCounts=0;
				counter=new CountThread();
				RMIExceptionPanel.this.repaint();
			}
		});
		add(connect);
		
		exit=new newJBut("退出",Color.BLACK);
		exit.setBounds(RMIExceptionFrame.WIDTH/2+25,205,WIDTH_OF_BUTTON,HEIGHT_OF_BUTTON);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(exit);
	}
	
	
	public void destruct(){
		if(counter!=null){
			counter.stop();
			counter=null;
		}
			
	}
	

	@Override
	public void paintComponent(Graphics g){
		Image exceptionIcon=new ImageIcon("graphic/RMI/RMIException.jpg").getImage();
		
		Image connecting=new ImageIcon("graphic/RMI/connecting.gif").getImage();
		
		
		g.drawImage(exceptionIcon, 0, 0,RMIExceptionFrame.WIDTH,RMIExceptionFrame.HEIGHT, null);
		
		if(connectCounts==-1)
			return;
		g.drawImage(connecting ,85, 170,160,30, null);
		int first=175;
		for(int i=0;i<connectCounts;i++){
			g.drawImage(DOT ,first, 170, null);
			first+=10;
		}
		
	}
	
	private class CountThread extends Thread{
		
		public CountThread(){
			this.start();
		}
		
		@Override
		public void run(){
			while(true){
				try {
					Thread.sleep(1000);
					connectCounts=(connectCounts+1)%5;
					RMIExceptionPanel.this.repaint();
				}  catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
			
		
	}
	
	
}
