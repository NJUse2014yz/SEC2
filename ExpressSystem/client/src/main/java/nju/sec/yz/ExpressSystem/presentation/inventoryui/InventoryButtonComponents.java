package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.INVENTORY_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;

public class InventoryButtonComponents {

	private JButton in;
	private JButton out;
	private JButton checkstock;
	private JButton observestock;
	private JButton setAlertRate;
	
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private JLabel leaveButton;
	
	private ClientControler maincontroler;
	private JPanel panel;
	
	
	public InventoryButtonComponents(ClientControler maincontroler,JPanel panel){
		this.panel=panel;
		this.maincontroler=maincontroler;
		
		iniIBC();
		
	}

	private void iniIBC() {
		
		ImageIcon inIcon = new ImageIcon("graphic/inventory/button/in.png");
		in = new JButton(inIcon);
		in.setBounds(13, 50, 108, 41);
		panel.add(in);
		
		ImageIcon outIcon = new ImageIcon("graphic/inventory/button/out.png");
		out = new JButton(outIcon);
		out.setBounds(13, 92, 108, 41);
		panel.add(out);

		ImageIcon checkIcon = new ImageIcon("graphic/inventory/button/checkstock.png");
		checkstock = new JButton(checkIcon);
		checkstock.setBounds(13, 134, 108, 41);
		panel.add(checkstock);
		
		ImageIcon observeIcon = new ImageIcon("graphic/inventory/button/observestock.png");
		observestock = new JButton(observeIcon);
		observestock.setBounds(13, 176, 108, 41);
		panel.add(observestock);
		
		ImageIcon setIcon = new ImageIcon("graphic/inventory/button/setAlertRate.png");
		setAlertRate = new JButton(setIcon);
		setAlertRate.setBounds(13, 218, 108, 41);
		panel.add(setAlertRate);

		
		in.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(INVENTORY_CONTROL.INVENTORY_IN);
			}
		});
		out.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(INVENTORY_CONTROL.INVENTORY_OUT);
			}
		});
		checkstock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(INVENTORY_CONTROL.INVENTORY_CHECK);
			}
		});
		observestock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(INVENTORY_CONTROL.INVENTORY_OBSERVE);
			}
		});
		setAlertRate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(INVENTORY_CONTROL.INVENTORY_SET);
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
