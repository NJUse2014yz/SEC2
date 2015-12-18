package nju.sec.yz.ExpressSystem.presentation.inventoryui;

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

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class InventorySetRate extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	
	private newJText ratevalue;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	
	
	public InventorySetRate(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventorySetRate();
	}


	private void iniInventorySetRate() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		ratevalue=new newJText();
		ratevalue.setBounds(250, 70, 72, 18);
		add(ratevalue);
		
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(380, 122, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if (ratevalue.getText().equals("")){
					warning.NotFilled();
				}else {
					ResultMessage result=inventoryservice.setAlertRate(Double.parseDouble(ratevalue.getText()));
					warning.Reply(result);
				}
				add(warning);
				repaint();
			}
		});
	}
	
	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background06.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
