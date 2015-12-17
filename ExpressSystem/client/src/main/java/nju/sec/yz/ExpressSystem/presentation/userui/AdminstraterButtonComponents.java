package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ADMINSTRATER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;

public class AdminstraterButtonComponents{
	public int a=0;
		private static JPanel panel;
	
		private static final int Button_x=12;
		private static final int manage_y1=51;
		private static final int Button_w=108;
		private static final int Button_h=41;
		private static final int button_w=110;
		private static final int button_h=31;
		private ClientControler maincontroler;
	
		// 侧边栏功能选择项
		private static newJBut manageButton;
		private static newJBut addButton;
		private static newJBut modifyButton;
		private static newJBut deleteButton;
		private static newJBut inquiryButton;
		
//		ImageIcon manageIcon1 = new ImageIcon("graphic/adminstrater/button/manage_button1.jpg");
//		ImageIcon manageIcon2 = new ImageIcon("graphic/adminstrater/button/manage_button2.jpg");
//		ImageIcon addIcon = new ImageIcon("graphic/adminstrater/button/add_button.jpg");
//		ImageIcon deleteIcon = new ImageIcon("graphic/adminstrater/button/delete_button.jpg");
//		ImageIcon modifyIcon = new ImageIcon("graphic/adminstrater/button/modify_button.jpg");
//		ImageIcon inquiryIcon = new ImageIcon("graphic/adminstrater/button/inquiry_button.jpg");
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private JLabel leaveButton;
		
		private Color framecolor=new Color(110,119,237);
	
		public AdminstraterButtonComponents(ClientControler maincontroler){
			this.maincontroler=maincontroler;
		}
		public AdminstraterButtonComponents(ClientControler maincontroler,JPanel panel){
			this.maincontroler=maincontroler;
			this.panel=panel;
			init();
		}
		public void changePanel(JPanel panel)
		{
			this.panel=panel;
		}
		public void init(){
			manageButton = new newJBut("用户管理");
			manageButton.setBounds(Button_x, manage_y1, Button_w, Button_h);

			
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
			leaveButton=new newJLabel("注销",Color.WHITE,Color.YELLOW);
			leaveButton.setBounds(433, 15, 37, 20);
			leaveButton.setVisible(true);
			
			exitButton= new JButton(ExitIcon);
			exitButton.setOpaque(false);
			exitButton.setBorder(null);
			exitButton.setContentAreaFilled(false); 
			exitButton.setBounds(490-19,0,19,19);
			change();
		}
		public void change(){
			manageButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
//					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.ARRIVE);
//					manageButton.setIcon(manageIcon2);
					addButton.setLocation(Button_x, manage_y1+Button_h);
					addButton.setVisible(true);
					addButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							maincontroler.adminstraterControler.adminstraterChangePanel(ADMINSTRATER_CONTROL.USER_ADD);
						}
					});
					deleteButton.setLocation(Button_x,manage_y1+Button_h+button_h);
					deleteButton.setVisible(true);
					deleteButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							maincontroler.adminstraterControler.adminstraterChangePanel(ADMINSTRATER_CONTROL.USER_DELETE);
						}
					});
					modifyButton.setLocation(Button_x,manage_y1+Button_h+button_h*2);
					modifyButton.setVisible(true);
					modifyButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							maincontroler.adminstraterControler.adminstraterChangePanel(ADMINSTRATER_CONTROL.USER_LIST);
						}
					});
					inquiryButton.setLocation(Button_x,manage_y1+Button_h+button_h*3);
					inquiryButton.setVisible(true);
					inquiryButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							maincontroler.adminstraterControler.adminstraterChangePanel(ADMINSTRATER_CONTROL.USER_INQUIRY);
						}
					});
					panel.repaint();
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
			panel.add(manageButton);
			panel.add(addButton);
			panel.add(deleteButton);
			panel.add(modifyButton);
			panel.add(inquiryButton);
			panel.add(exitButton);
			panel.add(leaveButton);
		}
}
