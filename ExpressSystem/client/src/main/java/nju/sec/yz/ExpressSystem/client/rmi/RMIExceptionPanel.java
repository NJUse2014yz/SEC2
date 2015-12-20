package nju.sec.yz.ExpressSystem.client.rmi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;

public class RMIExceptionPanel extends JPanel{
	
	static final int WIDTH_OF_BUTTON=75;
	
	static final int HEIGHT_OF_BUTTON=30;
	
	private JButton connect;
	
	private JButton exit;
	
	private JFrame frame;
	
	public RMIExceptionPanel(JFrame frame) {
		this.frame=frame;
		
		this.setLayout(null);
		connect=new newJBut("重连",Color.BLACK);
		connect.setBounds(RMIExceptionFrame.WIDTH/2-100,205,WIDTH_OF_BUTTON,HEIGHT_OF_BUTTON);
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InitRMI model=new InitRMI();
				model.initForever();
				frame.setVisible(false);
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
	
	

	@Override
	public void paintComponent(Graphics g){
		Image exceptionIcon=new ImageIcon("graphic/RMI/RMIException.jpg").getImage();
		g.drawImage(exceptionIcon, 0, 0,RMIExceptionFrame.WIDTH,RMIExceptionFrame.HEIGHT, null);
	}
}
