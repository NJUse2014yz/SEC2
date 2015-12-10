package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.componentui.MessageDeclare;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class InventoryMainUi extends JPanel {

	InventoryBlService inventory=new InventoryController();
	
	private ClientControler maincontroler;
	
	public InventoryMainUi(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryMainUi();
	}
	private void iniInventoryMainUi() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		MessageDeclare message=new MessageDeclare(this);
	}
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background01.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	
	
	}

}
