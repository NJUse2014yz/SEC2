package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
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

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class InventoryIn extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	
	
	private JTextField barId;
	private JComboBox destination;
	
	private JComboBox block;
	private JTextField row;
	private JTextField shelf;
	private JTextField position;
	
	private JButton confirm;
	private JLabel warning=new JLabel();
	
	
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
		
		position=new JTextField();
		position.setBounds(339, 168, 58, 19);
		add(position);

		
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(363, 199, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((barId.getText().equals("")) || (row.getText().equals(""))
						|| (shelf.getText().equals("")) || (position.getText().equals(""))
						) {
					warning.setText("尚未完成对带*必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
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
					if (result.getResult() == Result.FAIL) {

						warning.setText(result.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					} else {
						// 提交成功
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);

						repaint();
					}
				}
				
				
			
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
