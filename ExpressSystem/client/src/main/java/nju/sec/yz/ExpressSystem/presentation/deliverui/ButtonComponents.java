package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;

public class ButtonComponents{

	
		private ClientControler maincontroler;
	
		// 侧边栏功能选择项
		private JButton OrderInButton;
		private JButton OrderSearchButton;
		private JButton ReceiveInButton;
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private JLabel leaveButton;
	
		public ButtonComponents(ClientControler maincontroler,JPanel panel){
			this.maincontroler=maincontroler;
			/*
			 * button OrderInButton,OrderSearchButton,ReceiveInButton
			 */
			ImageIcon OrderInIcon = new ImageIcon("graphic/deliver/button/buttonOrderIn.png");
			OrderInButton = new JButton(OrderInIcon);
			OrderInButton.setBounds(14, 50, 108, 41);
			panel.add(OrderInButton);
			panel.setVisible(true);

			ImageIcon OrderSearchIcon = new ImageIcon("graphic/deliver/button/buttonOrderSearch.png");
			OrderSearchButton = new JButton(OrderSearchIcon);
			OrderSearchButton.setBounds(14, 92, 108, 41);
			panel.add(OrderSearchButton);
			panel.setVisible(true);

			ImageIcon ReceiveInIcon = new ImageIcon("graphic/deliver/button/buttonReceiveIn.png");
			ReceiveInButton = new JButton(ReceiveInIcon);
			ReceiveInButton.setBounds(14, 134, 108, 41);
			panel.add(ReceiveInButton);
			panel.setVisible(true);

			OrderInButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.deliverControler.deliverChangePanel(DELIVER_CONTROL.ORDER_IN);
				}
			});
			OrderSearchButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.deliverControler.deliverChangePanel(DELIVER_CONTROL.DELIVERY_INQUIRY);
				}
			});
			ReceiveInButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.deliverControler.deliverChangePanel(DELIVER_CONTROL.RECEIVE_IN);
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
