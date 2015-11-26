package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class InventoryIn extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	
	
	private JTextField barId;
	private JTextField destination;
	
	
	public InventoryIn(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryIn();
	}

	private void iniInventoryIn() {
		// TODO Auto-generated method stub

		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
	}
	
	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background02.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
