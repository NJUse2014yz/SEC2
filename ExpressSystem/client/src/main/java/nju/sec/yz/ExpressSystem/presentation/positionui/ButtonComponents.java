package nju.sec.yz.ExpressSystem.presentation.positionui;

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
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;

public class ButtonComponents{
	public int a=0;
		private static JPanel panel;
	
		private static final int Button_x=14;
		private static final int Button_w=108;
		private static final int Button_h=41;
		private static final int arrive_y=50;
		private static final int delive_y=92;
		private static final int pay_y=134;
		private static final int car_y=176;
		private static final int driver_y1=218;
		private static final int driver_y2=342;
		private static final int load_y1=260;
		private static final int load_y2=384;
		private static final int button_w=110;
		private static final int button_h=31;
		private ClientControler maincontroler;
	
		// 侧边栏功能选择项
		private static newJBut arriveButton;
		private static newJBut deliveButton;
		private static newJBut payButton;
		private static newJBut carButton;
		private static newJBut driverButton;
		private static newJBut loadButton;
		private static newJBut addButton;
		private static newJBut modifyButton;
		private static newJBut deleteButton;
		private static newJBut inquiryButton;
		
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private newJLabel leaveButton;
	
		private Color framecolor=new Color(110,119,237);
		
		public ButtonComponents(ClientControler maincontroler){
			this.maincontroler=maincontroler;
		}
		public ButtonComponents(ClientControler maincontroler,JPanel panel){
			this.maincontroler=maincontroler;
			this.panel=panel;
			init();
		}
		public void changePanel(JPanel panel,int n)
		{
			this.panel=panel;
		}
		public void changePanel(JPanel panel)
		{
			this.panel=panel;
			add();
		}
		public void init(){
			
			arriveButton = new newJBut("接收货物");
			arriveButton.setBounds(Button_x, arrive_y, Button_w, Button_h);
			
			deliveButton = new newJBut("派件");
			deliveButton.setBounds(Button_x, delive_y, Button_w, Button_h);

			
			payButton = new newJBut("收款记录");
			payButton.setBounds(Button_x, pay_y, Button_w, Button_h);
			
			
			carButton =new newJBut("车辆信息");
			carButton.setBounds(Button_x, car_y, Button_w, Button_h);

			
			driverButton = new newJBut("司机信息");
			driverButton.setBounds(Button_x, driver_y1, Button_w, Button_h);

			
			loadButton = new newJBut("装车管理");
			loadButton.setBounds(Button_x, load_y1, Button_w, Button_h);

			
			addButton = new newJBut("增加",framecolor);
			addButton.setSize(button_w, button_h);
//			addButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			addButton.setVisible(false);

			
			deleteButton = new newJBut("删除",framecolor);
			deleteButton.setSize(button_w, button_h);
//			deleteButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			deleteButton.setVisible(false);

			
			modifyButton = new newJBut("修改",framecolor);
			modifyButton.setSize(button_w, button_h);
//			modifyButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			modifyButton.setVisible(false);

			
			inquiryButton = new newJBut("查询",framecolor);
			inquiryButton.setSize(button_w, button_h);
//			inquiryButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			inquiryButton.setVisible(false);
			//离开当前账户
			leaveButton=new newJLabel("注销",Color.white,Color.YELLOW);
			leaveButton.setBounds(433, 21, 37, 20);
			leaveButton.setVisible(true);
			
			exitButton= new JButton(ExitIcon);
			exitButton.setOpaque(false);
			exitButton.setBorder(null);
			exitButton.setContentAreaFilled(false); 
			exitButton.setBounds(490-19,0,19,19);
			change();
		}
		public void change(){
			
			arriveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
//					carButton.setIcon(carIcon1);
//					driverButton.setIcon(driverIcon1);
					driverButton.setLocation(Button_x, driver_y1);
					loadButton.setLocation(Button_x,load_y1);
					System.out.println("1");
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.ARRIVE);
				}
			});
			deliveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
//					carButton.setIcon(carIcon1);
//					driverButton.setIcon(driverIcon1);
					driverButton.setLocation(Button_x, driver_y1);
					loadButton.setLocation(Button_x,load_y1);
					System.out.println("2");
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DELIVE);
				}
			});
			payButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
//					carButton.setIcon(carIcon1);
//					driverButton.setIcon(driverIcon1);
					driverButton.setLocation(Button_x, driver_y1);
					loadButton.setLocation(Button_x,load_y1);
					System.out.println("4");
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.GETPAY);
				}
			});
			carButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
//					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.ARRIVE);
//					carButton.setIcon(carIcon2);
					addButton.setLocation(Button_x, driver_y1);
					addButton.setVisible(true);
					addButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("7");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.CAR_ADD);
						}
					});
					deleteButton.setLocation(Button_x,driver_y1+button_h);
					deleteButton.setVisible(true);
					deleteButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("8");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.CAR_DELETE);
						}
					});
					modifyButton.setLocation(Button_x,driver_y1+button_h*2);
					modifyButton.setVisible(true);
					modifyButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("12");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.CAR_MODIFY);
						}
					});
					inquiryButton.setLocation(Button_x,driver_y1+button_h*3);
					inquiryButton.setVisible(true);
					inquiryButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("8");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.CAR_INQUIRY);
						}
					});
//					driverButton.setIcon(driverIcon1);
					driverButton.setLocation(Button_x, driver_y2);
					loadButton.setLocation(Button_x,load_y2);
					panel.repaint();
				}
			});
			driverButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
//					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DELIVE);
//					carButton.setIcon(carIcon1);
//					driverButton.setIcon(driverIcon2);
					addButton.setLocation(Button_x,load_y1);
					addButton.setVisible(true);
					addButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("5");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DRIVER_ADD);
						}
					});
					deleteButton.setLocation(Button_x,load_y1+button_h);
					deleteButton.setVisible(true);
					deleteButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("11");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DRIVER_DELETE);
						}
					});
					modifyButton.setLocation(Button_x,load_y1+button_h*2);
					modifyButton.setVisible(true);
					modifyButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("6");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DRIVER_MODIFY);
						}
					});
					inquiryButton.setLocation(Button_x,load_y1+button_h*3);
					inquiryButton.setVisible(true);
					inquiryButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							System.out.println("10");
							maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DRIVER_INQUIRY);
						}
					});
					driverButton.setLocation(Button_x, driver_y1);
					loadButton.setLocation(Button_x,load_y2);
					panel.repaint();
				}
			});
			loadButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
//					carButton.setIcon(carIcon1);
//					driverButton.setIcon(driverIcon1);
					driverButton.setLocation(Button_x, driver_y1);
					loadButton.setLocation(Button_x,load_y1);
					System.out.println("3");
					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.LOAD);
				}
			});
			/*
			 * exit
			 */
			
			
			exitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			
			
			leaveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					maincontroler.mainChangePanel(MAIN_CONTROL.LOGIN);
				}
			});
			
			add();
			panel.setVisible(true);
		}
		public void add()
		{
			panel.add(arriveButton);
			panel.add(deliveButton);
			panel.add(payButton);
			panel.add(carButton);
			panel.add(driverButton);
			panel.add(loadButton);
			panel.add(addButton);
			panel.add(deleteButton);
			panel.add(modifyButton);
			panel.add(inquiryButton);
			panel.add(exitButton);
			panel.add(leaveButton);
		}
}
