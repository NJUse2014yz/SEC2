package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ReceiveInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;

public class DeliverReceiveInUi extends JPanel{

	DeliverBlService deliverservice=new DeliverController();
	
	private JTextField id;
	private JTextField receiver;
	private DateChooser date;
	
	// 确定选项
	private JButton confirmButton;
	
	private newJLabel warning=new newJLabel();
	
	public DeliverReceiveInUi(ClientControler controler) {
		iniDeliverReceiveIn();
		DeliverButtonComponents bc=new DeliverButtonComponents(controler, this);
		this.date=new DateChooser(this,215,85);
		
	}

	private void iniDeliverReceiveIn() {
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		/*
		 * textfield
		 */

		id = new newJText();
		id.setBounds(244, 59, 182, 20);
		add(id);
		
		receiver = new newJText();
		receiver.setBounds(200, 112, 101, 20);
		add(receiver);
		
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirmButton = new newJBut("确定");
		confirmButton.setBounds(355, 190, 76, 27);
		add(confirmButton);
		setVisible(true);

		confirmButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			//判断必填项是否填写完成
			
			if((id.getText().equals(""))||(receiver.getText().equals(""))){
				warning.NotFilled();
				
			}else{
				ReceiveInformation reInfor=new ReceiveInformation(id.getText(), receiver.getText(), date.getTime());
				ReceiveVO reVO=new ReceiveVO();
				reVO.setReceiveInformation(reInfor);
				ResultMessage result=deliverservice.recieveReceipt(reVO);
				System.out.println(reVO.getReceiveInformation().getId());
				// 失败
				System.out.println(result== null);
				warning.Reply(result);
				
				}	
			add(warning);
			repaint();
			
			}
			
		});
		
		
	}
	
	

	@Override
	public void paintComponent(Graphics g) {
		Image img01 = new ImageIcon("graphic/deliver/background/background04.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

	
	
	
}
