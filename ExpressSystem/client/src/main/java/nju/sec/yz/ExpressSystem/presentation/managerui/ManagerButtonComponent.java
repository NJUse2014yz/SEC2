package nju.sec.yz.ExpressSystem.presentation.managerui;

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
import nju.sec.yz.ExpressSystem.presentation.controlerui.MANAGER_CONTROL;

public class ManagerButtonComponent {

	private ClientControler maincontroler;
	private JPanel panel;
	
	private newJBut agency;
	private newJBut staff;
	private newJBut salary;
	private newJBut city;
	private newJBut receiptApprove;
	private newJBut accountCheck;
	private newJBut logCheck;
	private newJBut add;
	private newJBut delete;
	private newJBut modify;
	private newJBut observe;
	
	private newJBut[] buttons = { agency, staff, salary, city, receiptApprove, accountCheck, logCheck };
	
	private int width=109;
	private int height1=43;
	private int height2=24;
	
	private Color framecolor=new Color(110,119,237);
	
//	ImageIcon agencyIcon1 = new ImageIcon("graphic/manager/button/agency1.png");
//	ImageIcon agencyIcon2 = new ImageIcon("graphic/manager/button/agency2.png");
//	ImageIcon staffIcon1 = new ImageIcon("graphic/manager/button/staff1.png");
//	ImageIcon staffIcon2 = new ImageIcon("graphic/manager/button/staff2.png");
//	ImageIcon salaryIcon1 = new ImageIcon("graphic/manager/button/salary1.png");
//	ImageIcon salaryIcon2 = new ImageIcon("graphic/manager/button/salary2.png");
//	ImageIcon constIcon1 = new ImageIcon("graphic/manager/button/const1.png");
//	ImageIcon constIcon2 = new ImageIcon("graphic/manager/button/const2.png");
//	ImageIcon receiptIcon = new ImageIcon("graphic/manager/button/receipt.png");
//	ImageIcon accountIcon = new ImageIcon("graphic/manager/button/account.png");
//	ImageIcon logIcon = new ImageIcon("graphic/manager/button/log.png");
//	ImageIcon addIcon = new ImageIcon("graphic/manager/button/add.png");
//	ImageIcon deleteIcon = new ImageIcon("graphic/manager/button/delete.png");
//	ImageIcon modifyIcon = new ImageIcon("graphic/manager/button/modify.png");
//	ImageIcon observeIcon = new ImageIcon("graphic/manager/button/observe.png");
	ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
	
//	private ImageIcon[] imgs = { agencyIcon1, agencyIcon2, staffIcon1, staffIcon2, salaryIcon1, salaryIcon2, constIcon1,
//			constIcon2, receiptIcon,null,accountIcon,null,logIcon,null };
	
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private newJLabel leaveButton;
	
	public ManagerButtonComponent(ClientControler maincontroler) {
		this.maincontroler=maincontroler;
		
	}
	
	public ManagerButtonComponent(ClientControler maincontroler,JPanel panel){
		this.maincontroler=maincontroler;
		this.panel=panel;
		init();
	}
	
	public void init() {
		
		setOriginal();
		
		//离开当前账户
		leaveButton=new newJLabel("注销",Color.white,Color.yellow);
		leaveButton.setBounds(433, 21, 37, 20);
		leaveButton.setVisible(true);
		
		exitButton= new JButton(ExitIcon);
		exitButton.setOpaque(false);
		exitButton.setBorder(null);
		exitButton.setContentAreaFilled(false); 
		exitButton.setBounds(490-19,0,19,19);
		change();
	}

	public void change() {
		
		buttons[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				changeForButtons(1);
				
				add.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.AGENCY_ADD);
					}
				});
				delete.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.AGENCY_DELETE);
					}
				});
				modify.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.AGENCY_LIST);
					}
				});
				observe.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.AGENCY_INQUIRY);
					}
				});
			}
		});
		
		buttons[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				changeForButtons(2);
				
				add.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.STAFF_ADD);
					}
				});
				delete.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.STAFF_DELETE);
					}
				});
				modify.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.STAFF_LIST);
					}
				});
				observe.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.STAFF_INQUIRY);
					}
				});
			}
		});
		
		buttons[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
				for(int count=1;count<=3;count++){
					buttons[count-1].setLocation(13,50+height1*(count-1));
//					buttons[count-1].setIcon(imgs[2*count-2]);
				}
				add.setVisible(false);
				
				modify.setLocation(13,50+height1*3+height2*0);
				modify.setVisible(true);
				
				delete.setVisible(false);
				
				observe.setLocation(13,50+height1*3+height2*1);
				observe.setVisible(true);
				
				for(int count=4;count<=buttons.length;count++){
					buttons[count-1].setLocation(13,50+height1*(count-1)+height2*2);
//					buttons[count-1].setIcon(imgs[2*count-2]);
				}
				
//				buttons[2].setIcon(imgs[5]);
				
				
				modify.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.SALARY_MODIFY);
					}
				});
				observe.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.SALARY_INQUIRY);
					}
				});
				
			}
		});
		
		buttons[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
				
				
				for(int count=1;count<=4;count++){
					buttons[count-1].setLocation(13,50+height1*(count-1));
//					buttons[count-1].setIcon(imgs[2*count-2]);
				}
				add.setLocation(13,50+height1*4);
				add.setVisible(true);
				
//				delete.setLocation(14,50+height1*4+height2*1);
//				delete.setVisible(true);
				
				modify.setLocation(13,50+height1*4+height2*1);
				modify.setVisible(true);
				
				observe.setLocation(13,50+height1*4+height2*2);
				observe.setVisible(true);
				
				for(int count=5;count<=buttons.length;count++){
					buttons[count-1].setLocation(13,50+height1*(count-1)+height2*3);
//					buttons[count-1].setIcon(imgs[2*count-2]);
				}
				
//				buttons[3].setIcon(imgs[7]);
				
				
				add.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.CONST_ADD);
					}
				});
//				delete.addMouseListener(new MouseAdapter(){
//					public void mouseClicked(MouseEvent e1)
//					{
//						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.C);
//					}
//				});
				modify.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.CONST_MODIFY);
					}
				});
				observe.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e1)
					{
						maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.CONST_INQUIRY);
					}
				});
				
			}
		});
		
		buttons[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				setOriginal();
				maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.RECEIPT_LIST);
			}
		});
		
		buttons[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				setOriginal();
				maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.ACCOUNT_CHECK);
			
			}
		});
		
		buttons[6].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				setOriginal();
				maincontroler.managerControler.managerChangePanel(MANAGER_CONTROL.LOG_CHECK);
			
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
//		panel.repaint();
		
	}



	public void add() {
		for(int i=0;i<buttons.length;i++){
			panel.add(buttons[i]);
		}
		panel.add(exitButton);
		panel.add(leaveButton);
		
		panel.add(add);
		panel.add(delete);
		panel.add(modify);
		panel.add(observe);
		
		
//		panel.add(buttons[0]);
//		panel.add(staff);
//		panel.add(salary);
//		panel.add(city);
//		panel.add(receiptApprove);
//		panel.add(accountCheck);
//		panel.add(logCheck);
//		panel.add(add);
//		panel.add(delete);
//		panel.add(modify);
//		panel.add(observe);
//		panel.add(exitButton);
//		panel.add(leaveButton);
		
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
	
	public void setOriginal(){
		buttons[0] = new newJBut("机构管理");
		buttons[0].setBounds(13, 50, width, height1);
		
		buttons[1] = new newJBut("人员管理");
		buttons[1].setBounds(13, 50+height1*1, width, height1);
		
		buttons[2] = new newJBut("薪水策略");
		buttons[2].setBounds(13, 50+height1*2, width, height1);
		
		buttons[3] = new newJBut("常量管理");
		buttons[3].setBounds(13, 50+height1*3, width, height1);
		
		buttons[4] = new newJBut("单据审批");
		buttons[4].setBounds(13, 50+height1*4, width, height1);
		
		buttons[5] = new newJBut("账目查询");
		buttons[5].setBounds(13, 50+height1*5, width, height1);
		
		buttons[6] = new newJBut("日志查看");
		buttons[6].setBounds(13, 50+height1*6, width, height1);
		
		add = new newJBut("增加",framecolor);
		add.setSize(width, height2);
		add.setVisible(false);
		
		delete = new newJBut("删除",framecolor);
		delete.setSize(width, height2);
		delete.setVisible(false);
		
		modify = new newJBut("修改",framecolor);
		modify.setSize(width, height2);
		modify.setVisible(false);
		
		observe = new newJBut("查询",framecolor);
		observe.setSize(width, height2);
		observe.setVisible(false);
		
	}
	
	public void changeForButtons(int i){
		for(int count=1;count<=i;count++){
			buttons[count-1].setLocation(13,50+height1*(count-1));
//			buttons[count-1].setIcon(imgs[2*count-2]);
		}
		
		for(int count=i+1;count<=buttons.length;count++){
			buttons[count-1].setLocation(13,50+height1*(count-1)+height2*4);
//			buttons[count-1].setIcon(imgs[2*count-2]);
		}
		
		add.setLocation(13,50+height1*i);
		add.setVisible(true);
		
		delete.setLocation(13,50+height1*i+height2*1);
		delete.setVisible(true);
		
		modify.setLocation(13,50+height1*i+height2*2);
		modify.setVisible(true);
		
		observe.setLocation(13,50+height1*i+height2*3);
		observe.setVisible(true);
		
		
//		buttons[i-1].setIcon(imgs[2*i-1]);
		
	}
	
	
}
