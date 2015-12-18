package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;

public class DeliverButtonComponents{

	
	private ClientControler mainControler;

	// 侧边栏功能选择项
	private JButton OrderInButton;
	private JButton OrderSearchButton;
	private JButton ReceiveInButton;
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private JLabel leaveButton;

	public DeliverButtonComponents(ClientControler maincontroler,JPanel panel){
		
		
		this.mainControler=maincontroler;
		/*
		 * button OrderInButton,OrderSearchButton,ReceiveInButton
		 */
//		ImageIcon OrderInIcon = new ImageIcon("graphic/deliver/button/buttonOrderIn.png");
		OrderInButton = new newJBut("订单输入");
		OrderInButton.setBounds(13, 50, 108, 41);
		panel.add(OrderInButton);
		panel.setVisible(true);

//		ImageIcon OrderSearchIcon = new ImageIcon("graphic/deliver/button/buttonOrderSearch.png");
		OrderSearchButton = new newJBut("订单查询");
		OrderSearchButton.setBounds(13, 92, 108, 41);
		panel.add(OrderSearchButton);
		panel.setVisible(true);

//		ImageIcon ReceiveInIcon = new ImageIcon("graphic/deliver/button/buttonReceiveIn.png");
		ReceiveInButton = new newJBut("收件信息输入");
		ReceiveInButton.setBounds(13, 134, 108, 41);
		ReceiveInButton.setMargin(new java.awt.Insets(0,0,0,0)); 
		panel.add(ReceiveInButton);
		panel.setVisible(true);

		OrderInButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainControler.deliverControler.deliverChangePanel(DELIVER_CONTROL.ORDER_IN);
			}
		});
		OrderSearchButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainControler.deliverControler.deliverChangePanel(DELIVER_CONTROL.DELIVERY_INQUIRY);
			}
		});
		ReceiveInButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainControler.deliverControler.deliverChangePanel(DELIVER_CONTROL.RECEIVE_IN);
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
		leaveButton=new newJLabel("注销",Color.WHITE,Color.YELLOW);
		leaveButton.setBounds(433, 21, 37, 20);
		leaveButton.setVisible(true);
		panel.add(leaveButton);
		leaveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainControler.mainChangePanel(MAIN_CONTROL.LOGIN);
			}
		});
	}
}
