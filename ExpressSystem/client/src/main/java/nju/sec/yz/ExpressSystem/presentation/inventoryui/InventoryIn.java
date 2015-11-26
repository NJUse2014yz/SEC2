package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class InventoryIn extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	
	
	private JTextField barId;
	private JComboBox destination;
	
	private JComboBox block;
	private JTextField row;
	private JTextField shelf;
	private JTextField positon;
	
	private JButton confirm;
	private JLabel warning;
	
	
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
		
		
		barId=new JTextField();
		barId.setBounds(213, 59, 182, 18);
		add(barId);
		
		DateChooser date =new DateChooser(this,213,82);
		
		String[] desti={};
		destination=new JComboBox(desti);
		destination.setBounds(202, 110, 98, 20);
		add(destination);
		
		String[] blo={"航运区","铁运区","汽运区","机动区"};
		block=new JComboBox(blo);
		block.setBounds(193,142,58,19);
		add(block);
		
		row=new JTextField();
		row.setBounds(339, 142, 58, 19);
		add(row);
		
		shelf=new JTextField();
		shelf.setBounds(193, 168, 58, 19);
		add(shelf);
		
		positon=new JTextField();
		positon.setBounds(339, 168, 58, 19);
		add(positon);
		
	}
	
	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background02.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
