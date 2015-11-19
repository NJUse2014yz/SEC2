package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

public class DeliverReceiveInUi extends JPanel{

	DeliverBlService deliverservice=new DeliverController();
	
	private JTextField id;
	private JTextField receiver;
	
	// 确定选项
	private JButton confirmButton;
	
	public DeliverReceiveInUi(ClientControler controler) {
		iniDeliverReceiveIn();
		ButtonComponents bc=new ButtonComponents(controler, this);
		
	}

	private void iniDeliverReceiveIn() {
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		/*
		 * textfield
		 */

		id = new JTextField();
		id.setBounds(244, 61, 182, 20);
		add(id);
		
		receiver = new JTextField();
		receiver.setBounds(200, 113, 101, 20);
		add(receiver);
		
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirmButton = new JButton(cinfirmIcon);
		confirmButton.setBounds(355, 190, 76, 27);
		add(confirmButton);
		setVisible(true);

		confirmButton.addMouseListener(new MouseAdapter() {
			
				
				
				
			
			});
		
		
	}
	
	

	@Override
	public void paintComponent(Graphics g) {
		Image img01 = new ImageIcon("graphic/deliver/background/background04.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

	
	
	
}
