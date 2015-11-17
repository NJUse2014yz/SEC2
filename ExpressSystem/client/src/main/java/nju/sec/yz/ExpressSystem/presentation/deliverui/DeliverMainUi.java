package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverSwitchPanelListener;

public class DeliverMainUi extends JPanel {

	private ClientControler mainControler;
	private DeliverControler controler;

	// 侧边栏功能选择项
	private JButton OrderInButton;
	private JButton OrderSearchButton;
	private JButton ReceiveInButton;

	//exit;
	private JButton exitButton;
	public DeliverMainUi(ClientControler mainControler){
		super();
		this.mainControler=mainControler;
		controler=mainControler.deliverControler;
		
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		/*
		 * button OrderInButton,OrderSearchButton,ReceiveInButton
		 */
		ImageIcon OrderInIcon = new ImageIcon("graphic/deliver/button/buttonOrderIn.png");
		OrderInButton = new JButton(OrderInIcon);
		OrderInButton.setBounds(14, 50, 108, 41);
		add(OrderInButton);
		setVisible(true);

		ImageIcon OrderSearchIcon = new ImageIcon("graphic/deliver/button/buttonOrderSearch.png");
		OrderSearchButton = new JButton(OrderSearchIcon);
		OrderSearchButton.setBounds(14, 92, 108, 41);
		add(OrderSearchButton);
		setVisible(true);

		ImageIcon ReceiveInIcon = new ImageIcon("graphic/deliver/button/buttonReceiveIn.png");
		ReceiveInButton = new JButton(ReceiveInIcon);
		ReceiveInButton.setBounds(14, 134, 108, 41);
		add(ReceiveInButton);
		setVisible(true);

		//切换界面
		OrderInButton.addMouseListener(new DeliverSwitchPanelListener(DELIVER_CONTROL.ORDER_IN, this.controler));
		OrderSearchButton.addMouseListener(new DeliverSwitchPanelListener(DELIVER_CONTROL.DELIVERY_INQUIRY, this.controler));
		ReceiveInButton.addMouseListener(new DeliverSwitchPanelListener(DELIVER_CONTROL.RECEIVE_IN, this.controler));
	
		/*
		 * exit
		 */
		
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		exitButton= new JButton(ExitIcon);
		exitButton.setOpaque(false);
		exitButton.setBorder(null);
		exitButton.setContentAreaFilled(false); 
		exitButton.setBounds(490-19,0,19,19);
		add(exitButton);
		setVisible(true);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	
	
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/deliver/background/background01.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
