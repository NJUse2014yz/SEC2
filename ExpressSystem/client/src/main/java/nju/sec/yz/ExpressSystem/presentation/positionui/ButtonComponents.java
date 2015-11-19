package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;

public class ButtonComponents{

	
		private ClientControler maincontroler;
	
		// 侧边栏功能选择项
		private JButton arriveButton;
		private JButton deliveButton;
		private JButton payButton;
		private JButton carButton;
		private JButton driverButton;
		private JButton loadButton;
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private JLabel leaveButton;
	
		public ButtonComponents(ClientControler maincontroler,JPanel panel){
			this.maincontroler=maincontroler;
			/*
			 * button OrderInButton,OrderSearchButton,ReceiveInButton
			 */
			ImageIcon arriveIcon = new ImageIcon("graphic/position/button/button_arrive.png");
			arriveButton = new JButton(arriveIcon);
			arriveButton.setBounds(14, 50, 108, 41);
			panel.add(arriveButton);
			panel.setVisible(true);

			ImageIcon deliveIcon = new ImageIcon("graphic/position/button/button_delive.png");
			deliveButton = new JButton(deliveIcon);
			deliveButton.setBounds(14, 92, 108, 41);
			panel.add(deliveButton);
			panel.setVisible(true);

			ImageIcon payIcon = new ImageIcon("graphic/position/button/button_pay.png");
			payButton = new JButton(payIcon);
			payButton.setBounds(14, 134, 108, 41);
			panel.add(payButton);
			panel.setVisible(true);

			arriveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.ARRIVE);
				}
			});
			deliveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DELIVE);
				}
			});
			payButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.GETPAY);
				}
			});
			
			/*
			 * exit
			 */
			
			ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
			exitButton= new JButton(ExitIcon);
			exitButton.setOpaque(false);
			exitButton.setBorder(null);
			exitButton.setContentAreaFilled(false); 
			exitButton.setBounds(490-19,0,19,19);
			panel.add(exitButton);
			panel.setVisible(true);
			exitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			
			//离开当前账户
			leaveButton=new JLabel();
			leaveButton.setBounds(433, 21, 37, 20);
			leaveButton.setVisible(true);
			panel.add(leaveButton);
			leaveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.mainChangePanel(MAIN_CONTROL.LOGIN);
				}
			});
		}
}
