package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class InventoryOut extends JPanel {

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	private AgencyBlService manager=new ManagerController();
	
	private JTextField barId;
	private JComboBox destination;
	
	private JComboBox transportType;
	private JTextField transitSheetId;
	
	
	private JButton confirm;
	private JLabel warning=new JLabel();
	
	
	private DateChooser date ;
	
	public InventoryOut(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryOut();
	}

	private void iniInventoryOut() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		
		barId=new JTextField();
		barId.setBounds(213, 59, 182, 18);
		add(barId);
		
		date =new DateChooser(this,213,82);
		
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] desti=new String[trans.size()];
		for(int i=0;i<trans.size();i++){
			desti[i]=trans.get(i).getName();
		}
		destination=new JComboBox(desti);
		destination.setBounds(202, 110, 98, 20);
		add(destination);
		
		String[] blo={"飞机","火车","汽车"};
		transportType=new JComboBox(blo);
		transportType.setBounds(225,139,58,19);
		add(transportType);
		
		transitSheetId=new JTextField();
		transitSheetId.setBounds(323, 168, 134, 18);
		add(transitSheetId);
		

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
				if ((barId.getText().equals("")) || (transitSheetId.getText().equals(""))
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
					InventoryOutInformation invenOutInf = new InventoryOutInformation(date.getTime(),
							destination.getSelectedItem().toString(),
							getType(transportType.getSelectedItem().toString()),
							transitSheetId.getText()
							);
					
					InventoryOutSheetVO vo = new InventoryOutSheetVO(invenOutInf,barId.getText().toString());
					// 判断输入的信息是否正确
					ResultMessage result = inventoryservice.out(vo);
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
	
	
	public TransportType getType(String str){
		switch(str){
		case "汽车":
			return TransportType.CAR;
		case "飞机":
			return TransportType.PLANE;
		case "火车":	
			return TransportType.TRAIN;
		default:
			return null;
		}
	}
	
	

	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background03.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
