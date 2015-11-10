package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ui.FrameGame;
import ui.Panel2;

public class DeliverOrderInUi extends JPanel {
	//侧边栏功能选择项
	private JButton OrderInButton;
	private JButton OrderSearchButton;
	private JButton ReceiveInButton;
	//确定选项
	private JButton confirmButton;
	
	//寄件人信息
	private JTextField nameSender;
	private JTextField addressSender;
	private JTextField organizaionSender;
	private JTextField telephoneSender;
	private JTextField cellphoneSender;
	
	//收件人信息
	private JTextField nameConsignee;
	private JTextField addressConsignee;
	private JTextField organizaionConsignee;
	private JTextField telephoneConsignee;
	private JTextField cellphoneConsignee;
	
	//货物信息
	private JTextField nameGood;
	private JTextField totalGood;
	private JTextField weightGood;
	private JTextField vloumeGood;
	private JTextField sizeGood;
	
	//订单条码号
	private JTextField barId;
	
	//包装费，快递种类
	private JComboBox packType;
	private JComboBox deliveryType;
	

	public DeliverOrderInUi() {

		initDeliverOrderIn();

	}

	private void initDeliverOrderIn() {
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

		OrderInButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//调用delivercontroler的方法
			}
		});
		OrderSearchButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//调用delivercontroler的方法
			}
		});
		ReceiveInButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//调用delivercontroler的方法
			}
		});
		

		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirmButton = new JButton(cinfirmIcon);
		confirmButton.setBounds(378, 456, 76, 27);
		add(confirmButton);
		setVisible(true);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//translate data
			}
		});

		/*
		 * textfield
		 */

		nameSender = new JTextField();
		nameSender.setBounds(185, 87, 58, 15);
		add(nameSender);
		
		addressSender = new JTextField();
		addressSender.setBounds(295, 87, 116, 15);
		add(addressSender);
		
		organizaionSender = new JTextField();
		organizaionSender.setBounds(185, 113, 85, 15);
		add(organizaionSender);
		
		telephoneSender = new JTextField();
		telephoneSender.setBounds(319, 113, 140, 15);
		add(telephoneSender);
		
		cellphoneSender = new JTextField();
		cellphoneSender.setBounds(185, 140, 140, 15);
		add(cellphoneSender);
		
		nameConsignee = new JTextField();
		nameConsignee.setBounds(185, 198, 58, 15);
		add(nameConsignee);
		
		addressConsignee = new JTextField();
		addressConsignee.setBounds(295, 198, 116, 15);
		add(addressConsignee);
		
		organizaionConsignee = new JTextField();
		organizaionConsignee.setBounds(185, 224, 85, 15);
		add(organizaionConsignee);
		
		telephoneConsignee = new JTextField();
		telephoneConsignee.setBounds(319, 224, 140, 15);
		add(telephoneConsignee);
		
		cellphoneConsignee = new JTextField();
		cellphoneConsignee.setBounds(185, 249, 140, 15);
		add(cellphoneConsignee);
		
		totalGood=new JTextField();
		totalGood.setBounds(185, 279, 58, 15);
		add(totalGood);
		
		weightGood=new JTextField();
		weightGood.setBounds(330, 279, 58, 15);
		add(weightGood);
		
		vloumeGood=new JTextField();
		vloumeGood.setBounds(185, 303,58, 15);
		add(vloumeGood);
		
		nameGood=new JTextField();
		nameGood.setBounds(332, 303, 58, 15);
		add(nameGood);
		
		sizeGood=new JTextField();
		sizeGood.setBounds(185, 327, 85, 15);
		add(sizeGood);
		
		barId=new JTextField();
		barId.setBounds(252, 354, 140, 15);
		add(barId);
		
		packType=new JComboBox();
		packType.addItem("纸箱");
		packType.addItem("木箱");
		packType.addItem("快递袋");
		packType.addItem("其他");
		packType.setBounds(198, 378, 85, 20);
		add(packType);
		
		deliveryType=packType=new JComboBox();
		deliveryType.addItem("经济快递");
		deliveryType.addItem("标准快递");
		deliveryType.addItem("特快");
		deliveryType.setBounds(225,407,85,20);
		add(deliveryType);
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/deliver/background/background02.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
