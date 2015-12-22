package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJScroll;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitOutVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class InventoryOut extends JPanel {

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	private AgencyBlService manager=new ManagerController();
	
//	private JTextField barId;
	private newJLabel destination;
	
	private newJLabel transportType;
	private newJText transitSheetId;
	
	private newJBut searchBarId;
	private newJBut confirm;
	private newJLabel warning;
	
	private newTable table;
	
	private DateChooser date ;
	
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	public InventoryOut(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryOut();
	}

	private void iniInventoryOut() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		name.add("快递单号");
		
//		barId=new JTextField();
//		barId.setBounds(213, 59, 182, 18);
//		add(barId);
		
		date =new DateChooser(this,213,82);
		
		
		destination=new newJLabel();
		destination.setBounds(202, 110, 120, 20);
		add(destination);
		
		warning=new newJLabel();
		warning.setForeground(Color.red);
		warning.setBounds(138, 490, 463 - 138, 30);
		add(warning);
		
		
		transportType=new newJLabel();
		transportType.setBounds(225,139,58,19);
		add(transportType);
		
		transitSheetId=new newJText();
		transitSheetId.setBounds(310, 57, 154, 18);
		add(transitSheetId);
		
		table=new newTable(data,name,this,false);
		table.setBounds(143,177,321,196);
		table.join();
		
		searchBarId = new newJBut("确定");
		searchBarId.setBounds(363, 131, 76, 27);
		add(searchBarId);
		
		searchBarId.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// 判断必填项是否填写完成
				if ((transitSheetId.getText().equals(""))
						|| (transitSheetId.getText().equals(""))) {
					warning.setText("尚未填写中转单号");
					warning.setVisible(true);
				} else {
					// translate data
					TransitOutVO vo = inventoryservice
							.getBarIdList(transitSheetId.getText());
					if (vo == null) {
						warning.setText("中转单号输入错误");
						warning.setVisible(true);
					} else {
						// 提交成功
						warning.setText("");
						/**
						 * 自动显示目的地与运输方式
						 */
						transportType.setText(vo.type+"");
						transportType.setVisible(true);
						destination.setText(vo.destination);
						destination.setVisible(true);
						ArrayList<String> barIdList = (ArrayList<String>) vo.barIds;
						changeData(barIdList);
						table.resetData();
						confirm.setVisible(true);
					}
				}
				repaint();
			}
		});
		
		
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(463-72, 380, 72, 24);
		add(confirm);
		confirm.setVisible(false);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((transitSheetId.getText().equals(""))
						) {
					warning.NotFilled();
				} else {
					// translate data
					InventoryOutInformation invenOutInf = new InventoryOutInformation(date.getTime(),
							destination.getText(),
							getType(transportType.getText()),
							transitSheetId.getText()
							);
					//为每个barId制作单子
					warning.setForeground(Color.red);
					warning.setText("提交成功");
					for(int c=table.getRowCount()-1;c>=0;c--){
						InventoryOutSheetVO vo = new InventoryOutSheetVO(invenOutInf,table.getValueAt(c, 0,false));
						ResultMessage result = inventoryservice.out(vo);
						if(result.getResult()==Result.SUCCESS){
							table.getModel().removeRow(c);
						}else{
							warning.setText("提交失败");
							break;
						}
					}
					
				}
				add(warning);
				repaint();
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
	private void changeData(ArrayList<String> bars)
	{
		data.removeAllElements();
		for(int c=0;c<bars.size();c++){
			Vector<String> vector=new Vector<String>();
			vector.add(bars.get(c));
			data.add(vector);
		}
		table.resetData();
	}
	

	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background03.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
