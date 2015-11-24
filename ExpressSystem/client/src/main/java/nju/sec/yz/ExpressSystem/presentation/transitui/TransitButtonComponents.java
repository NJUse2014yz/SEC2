package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.TRANSIT_CONTROL;

public class TransitButtonComponents {
	private ClientControler maincontroler;
	private static JPanel panel;
	
	// 侧边栏功能选择项
	private JButton TransitReceipt;
	private JButton TransitLoading;
	private JButton TransitReceive;
	private JButton car;
	private JButton train;
	private JButton fight;
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private JLabel leaveButton;
	
	ImageIcon loadingIcon = new ImageIcon("graphic/transit/button/loading.png");
	ImageIcon receiptIcon01 = new ImageIcon("graphic/transit/button/receipt01.png");
	ImageIcon receiptIcon02 = new ImageIcon("graphic/transit/button/receipt02.png");
	ImageIcon receiveIcon = new ImageIcon("graphic/transit/button/receive.png");
	ImageIcon fightIcon = new ImageIcon("graphic/transit/button/fight.png");
	ImageIcon trainIcon = new ImageIcon("graphic/transit/button/train.png");
	ImageIcon carIcon = new ImageIcon("graphic/transit/button/car.png");
	
	
	
	public TransitButtonComponents(ClientControler maincontroler){
		this.maincontroler=maincontroler;
	}
	
	
	public TransitButtonComponents(ClientControler maincontroler,JPanel panel){
		this.maincontroler=maincontroler;
		this.panel=panel;
		
		
		
		
		iniTransit();
	}

	
	public void setNextPanel(JPanel panel){
		this.panel=panel;
	}

	private void addComponent(){
		panel.add(TransitLoading);
		panel.add(TransitReceive);
		panel.add(TransitReceipt);
		panel.add(fight);
		panel.add(train);
		panel.add(car);
		panel.add(exitButton);
		panel.add(leaveButton);
	}
	
	public void change() {
		TransitLoading.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fight.setVisible(false);
				train.setVisible(false);
				car.setVisible(false);
				TransitReceive.setBounds(12, 134, 108, 41);
				TransitReceipt.setIcon(receiptIcon01);
				maincontroler.transitControler.transitChangePanel(TRANSIT_CONTROL.TRANSIT);
			}
		});
		
		TransitReceive.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fight.setVisible(false);
				train.setVisible(false);
				car.setVisible(false);
				TransitReceive.setBounds(12, 134, 108, 41);
				TransitReceipt.setIcon(receiptIcon01);
				maincontroler.transitControler.transitChangePanel(TRANSIT_CONTROL.ARRIVE);
			}
		});
		
		TransitReceipt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TransitReceipt.setIcon(receiptIcon02);
				TransitReceive.setBounds(12, 134+27*3, 108, 41);
				fight.setLocation(12, 134);
				train.setLocation(12, 134+27);
				car.setLocation(12, 134+27*2);
				panel.add(fight);
				panel.add(train);
				panel.add(car);
				fight.setVisible(true);
				train.setVisible(true);
				car.setVisible(true);
				
				//三个子选项
				fight.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						maincontroler.transitControler.transitChangePanel(TRANSIT_CONTROL.FLIGHT_LOAD);
					}
				});
				
				train.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						maincontroler.transitControler.transitChangePanel(TRANSIT_CONTROL.TRAIN_LOAD);
					}
				});
				
				car.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						maincontroler.transitControler.transitChangePanel(TRANSIT_CONTROL.CAR_LOAD);
					}
				});
				
				
				
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
		
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//离开当前账户
		leaveButton=new JLabel();
		leaveButton.setBounds(433, 21, 37, 20);
		leaveButton.setVisible(true);
		
		leaveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.mainChangePanel(MAIN_CONTROL.LOGIN);
			}
		});
		
		addComponent();
		
	}


	public void iniTransit() {
		/*
		 * 侧边栏功能选择项
		 */
		
		TransitLoading= new JButton(loadingIcon);
		TransitLoading.setBounds(12, 50, 108, 41);
//		panel.add(TransitLoading);
		
		TransitReceipt= new JButton(receiptIcon01);
		TransitReceipt.setBounds(12, 92, 108, 41);
//		panel.add(TransitReceipt);
		
		
		TransitReceive= new JButton(receiveIcon);
		TransitReceive.setBounds(12, 134, 108, 41);
//		panel.add(TransitReceive);
		
		
		fight= new JButton(fightIcon);
		fight.setSize(109,27);
		fight.setVisible(false);
		
		
		train= new JButton(trainIcon);
		train.setSize(109,27);
		train.setVisible(false);
		
		
		car= new JButton(carIcon);
		car.setSize(109,27);
		car.setVisible(false);
		
		change();
	}
}