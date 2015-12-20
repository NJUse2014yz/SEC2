package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ACCOUNT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;

public class AccountButtonComponents{
		public int a=0;
		private int n=0;
		private static JPanel panel;
		private JLabel warning;
	
		private static final int Button_x=14;
		private static final int Button_w=108;
		private static final int Button_h=41;
		private static final int income_y=52;
		private static final int initial_y=95;
		private static final int cost_y=138;
		private static final int table_y=181;
		private static final int account_y1=224;
		private static final int account_y2=342;
		private static final int account_y3=291;
		private static final int log_y1=264;
		private static final int log_y2=384;
		private static final int log_y3=334;
		private static final int button_w=108;
		private static final int button_h1=35;
		private static final int button_h2=28;
		private static final int warning_x=198;
		private static final int warning_y=490;
		private static final int warning_w=275;
		private static final int warning_h=30;
		private ClientControler maincontroler;
	
		// 侧边栏功能选择项
		private static JButton incomeButton;
		private static JButton initialButton;
		private static JButton costButton;
		private static JButton tableButton;
		private static JButton accountButton;
		private static JButton operateButton;
		private static JButton chengbenButton;
		private static JButton logButton;
		private static JButton addButton;
		private static JButton modifyButton;
		private static JButton deleteButton;
		private static JButton inquiryButton;
		
		ImageIcon inquiryIcon = new ImageIcon("graphic/account/button/inquiry_button.jpg");
		ImageIcon incomeIcon = new ImageIcon("graphic/account/button/income_button.jpg");
		ImageIcon initialIcon = new ImageIcon("graphic/account/button/initial_button.jpg");
		ImageIcon costIcon = new ImageIcon("graphic/account/button/cost_button.jpg");
		ImageIcon tableIcon1 = new ImageIcon("graphic/account/button/table_button1.jpg");
		ImageIcon tableIcon2 = new ImageIcon("graphic/account/button/table_button2.jpg");
		ImageIcon accountIcon1 = new ImageIcon("graphic/account/button/account_button1.jpg");
		ImageIcon accountIcon2 = new ImageIcon("graphic/account/button/account_button2.jpg");
		ImageIcon logIcon = new ImageIcon("graphic/account/button/log_button.jpg");
		ImageIcon addIcon = new ImageIcon("graphic/account/button/add_button.jpg");
		ImageIcon deleteIcon = new ImageIcon("graphic/account/button/delete_button.jpg");
		ImageIcon modifyIcon = new ImageIcon("graphic/account/button/modify_button.jpg");
		ImageIcon operateIcon = new ImageIcon("graphic/account/button/operate_button.jpg");
		ImageIcon chengbenIcon = new ImageIcon("graphic/account/button/chengben_button.jpg");
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private JLabel leaveButton;
	
		public AccountButtonComponents(ClientControler maincontroler,int n){
			this.n=n;
			this.maincontroler=maincontroler;
		}
		public AccountButtonComponents(ClientControler maincontroler,JPanel panel,int n){
			this.maincontroler=maincontroler;
			this.panel=panel;
			this.n=n;
			
			init();
		}
		public void changePanel(JPanel panel)
		{
			this.panel=panel;
		}
		public void init(){
			
			incomeButton = new JButton(incomeIcon);
			incomeButton.setBounds(Button_x, income_y, Button_w, Button_h);
			
			initialButton = new JButton(initialIcon);
			initialButton.setBounds(Button_x, initial_y, Button_w, Button_h);

			
			costButton = new JButton(costIcon);
			costButton.setBounds(Button_x, cost_y, Button_w, Button_h);
			
			
			tableButton = new JButton(tableIcon1);
			tableButton.setBounds(Button_x, table_y, Button_w, Button_h);

			
			accountButton = new JButton(accountIcon1);
			accountButton.setBounds(Button_x, account_y1, Button_w, Button_h);

			
			logButton = new JButton(logIcon);
			logButton.setBounds(Button_x, log_y1, Button_w, Button_h);

			
			addButton = new JButton(addIcon);
			addButton.setSize(button_w, button_h2);
//			addButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			addButton.setVisible(false);

			
			deleteButton = new JButton(deleteIcon);
			deleteButton.setSize(button_w, button_h2);
//			deleteButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			deleteButton.setVisible(false);

			
			modifyButton = new JButton(modifyIcon);
			modifyButton.setSize(button_w, button_h2);
//			modifyButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			modifyButton.setVisible(false);

			
			inquiryButton = new JButton(inquiryIcon);
			inquiryButton.setSize(button_w, button_h2);
//			inquiryButton.setBounds(Button_x, load_y1, Button_w, Button_h);
			inquiryButton.setVisible(false);
			
			operateButton=new JButton(operateIcon);
			operateButton.setSize(button_w, button_h1);
			operateButton.setVisible(false);
			
			chengbenButton=new JButton(chengbenIcon);
			chengbenButton.setSize(button_w, button_h1);
			chengbenButton.setVisible(false);
			//离开当前账户
			leaveButton=new JLabel();
			leaveButton.setBounds(433, 21, 37, 20);
			leaveButton.setVisible(true);
			
			exitButton= new JButton(ExitIcon);
			exitButton.setOpaque(false);
			exitButton.setBorder(null);
			exitButton.setContentAreaFilled(false); 
			exitButton.setBounds(490-19,0,19,19);
			
			warning=new JLabel();
			warning.setText("对不起，您的权限不够");
			warning.setBounds(warning_x, warning_y, warning_w, warning_h);
			warning.setFont(new Font("Dialog", 1, 15));
			warning.setForeground(Color.red);
			warning.setVisible(false);
			
			change();
		}
		public void change(){
			incomeButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
					chengbenButton.setVisible(false);
					operateButton.setVisible(false);
					tableButton.setIcon(tableIcon1);
					accountButton.setIcon(accountIcon1);
					accountButton.setLocation(Button_x, account_y1);
					logButton.setLocation(Button_x,log_y1);
					if(n==0)
					{
						warning.setVisible(false);
						maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.INCOME);
					}
					else
					{
						warning.setVisible(false);
						maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.INCOME);
					}
				}
			});
			initialButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
					chengbenButton.setVisible(false);
					operateButton.setVisible(false);
					tableButton.setIcon(tableIcon1);
					accountButton.setIcon(accountIcon1);
					accountButton.setLocation(Button_x, account_y1);
					logButton.setLocation(Button_x,log_y1);
					if(n==0)
					{
						warning.setVisible(false);
						maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.INITIAL);
					}
					else
					{
						warning.setVisible(false);
						maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.INITIAL);
					}
				}
			});
			costButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
					chengbenButton.setVisible(false);
					operateButton.setVisible(false);
					tableButton.setIcon(tableIcon1);
					accountButton.setIcon(accountIcon1);
					accountButton.setLocation(Button_x, account_y1);
					logButton.setLocation(Button_x,log_y1);
					if(n==0)
					{
						warning.setVisible(false);
						maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.COST);
					}
					else
					{
						warning.setVisible(false);
						maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.COST);
					}
				}
			});
			tableButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
//					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.ARRIVE);
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
					tableButton.setIcon(tableIcon2);
					accountButton.setIcon(accountIcon1);
					accountButton.setLocation(Button_x, account_y3);
					logButton.setLocation(Button_x,log_y3);
					operateButton.setLocation(Button_x, account_y1);
					operateButton.setVisible(true);
					operateButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.TABLE_OPERATE);
							}
							else
							{
								warning.setVisible(false);
								maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.TABLE_OPERATE);
							}
						}
					});
					chengbenButton.setLocation(Button_x,account_y1+button_h1);
					chengbenButton.setVisible(true);
					chengbenButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.TABLE_COST);
							}
							else
							{
								warning.setVisible(false);
								maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.TABLE_COST);
							}
						}
					});
					panel.repaint();
				}
			});
			accountButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
//					maincontroler.positionControler.positionChangePanel(POSITION_CONTROL.DELIVE);
					chengbenButton.setVisible(false);
					operateButton.setVisible(false);
					tableButton.setIcon(tableIcon1);
					accountButton.setIcon(accountIcon2);
					accountButton.setLocation(Button_x, account_y1);
					logButton.setLocation(Button_x,log_y2);
					addButton.setLocation(Button_x,log_y1);
					addButton.setVisible(true);
					addButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.ADD_ACCOUNT);
							}
							else
							{
								warning.setVisible(true);
							}
						}
					});
					deleteButton.setLocation(Button_x,log_y1+button_h2);
					deleteButton.setVisible(true);
					deleteButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.DELETE_ACCOUNT);
							}
							else
							{
								warning.setVisible(true);
							}
						}
					});
					modifyButton.setLocation(Button_x,log_y1+button_h2*2);
					modifyButton.setVisible(true);
					modifyButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.MODIFY_ACCOUNTLIST);
							}
							else
							{
								warning.setVisible(true);
							}
						}
					});
					inquiryButton.setLocation(Button_x,log_y1+button_h2*3);
					inquiryButton.setVisible(true);
					inquiryButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e1)
						{
							if(n==0)
							{
								warning.setVisible(false);
								maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.ENQUIRY_ACCOUNT);
							}
							else
							{
								warning.setVisible(false);
								maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.ENQUIRY_ACCOUNT);
							}
						}
					});
					panel.repaint();
				}
			});
			logButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addButton.setVisible(false);
					modifyButton.setVisible(false);
					deleteButton.setVisible(false);
					inquiryButton.setVisible(false);
					tableButton.setIcon(tableIcon1);
					accountButton.setIcon(accountIcon1);
					accountButton.setLocation(Button_x, account_y1);
					logButton.setLocation(Button_x,log_y1);
					if(n==0)
					{
						warning.setVisible(false);
						maincontroler.accountControler0.accountChangePanel(ACCOUNT_CONTROL.LOG_CHECK);
					}
					else
					{
						warning.setVisible(false);
						maincontroler.accountControler1.accountChangePanel(ACCOUNT_CONTROL.LOG_CHECK);
					}
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
			panel.add(incomeButton);
			panel.add(initialButton);
			panel.add(costButton);
			panel.add(tableButton);
			panel.add(accountButton);
			panel.add(logButton);
			panel.add(addButton);
			panel.add(deleteButton);
			panel.add(modifyButton);
			panel.add(inquiryButton);
			panel.add(exitButton);
			panel.add(leaveButton);
			panel.add(operateButton);
			panel.add(chengbenButton);
			panel.add(warning);
		}
}
