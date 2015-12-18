package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class InventoryIn extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	private AgencyBlService manager=new ManagerController();
	
	private ClientControler maincontroler;
	
	
	private newJText barId;
	private JComboBox destination;
	
	private newJCombo block;
	private newJText row;
	private newJText shelf;
	private newJText position;
	
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	
	private DateChooser date;
	
	
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
		
		
		barId=new newJText();
		barId.setBounds(213, 59, 182, 18);
		add(barId);
		
		date =new DateChooser(this,216,82);
		
		List<String> destinations=inventoryservice.getValidDestination();
		String[] desti=new String[destinations.size()];
		destinations.toArray(desti);
		
		destination=new newJCombo(desti);
		destination.setBounds(202, 110, 110, 20);
		add(destination);
		
		String[] blo={"航运区","铁运区","汽运区","机动区"};
		block=new newJCombo(blo);
		block.setBounds(193,141,70,19);
		add(block);
		
		row=new newJText();
		row.setBounds(339, 141, 70, 19);
		add(row);
		
		shelf=new newJText();
		shelf.setBounds(193, 166, 70, 19);
		add(shelf);
		
		position=new newJText();
		position.setBounds(339, 166, 70, 19);
		add(position);

		
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(363, 199, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((barId.getText().equals("")) || (row.getText().equals(""))
						|| (shelf.getText().equals("")) || (position.getText().equals(""))
						) {
					warning.NotFilled();
				} else {
					// translate data
					InventoryInInformation invenInInf = new InventoryInInformation(date.getTime(),
							destination.getSelectedItem().toString(),
							getBlock(block.getSelectedItem().toString()),
							Integer.parseInt(row.getText()),
							Integer.parseInt(shelf.getText()),
							Integer.parseInt(position.getText()),null
							);
					
					InventoryInSheetVO vo = new InventoryInSheetVO(invenInInf,barId.getText());
					// 判断输入的信息是否正确
					ResultMessage result = inventoryservice.in(vo);
					// 失败
					warning.Reply(result);
				}
				add(warning);
				repaint();
			
			}
		});
	}
	
	public int getBlock(String str){
		switch(str){
		case "航运区":
			return 1;
		case "铁运区":
			return 2;
		case "汽运区":
			return 3;
		case "机动区":
			return 4;
		default:
			return 0;
	}
}	
	
	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background02.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
