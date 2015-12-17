package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitOutVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class InventoryOut extends JPanel {

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	private AgencyBlService manager=new ManagerController();
	
//	private JTextField barId;
	private newJCombo destination;
	
	private newJCombo transportType;
	private newJText transitSheetId;
	
	private newJBut searchBarId;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	
	private JTable table;
	private DefaultTableModel model;
	private newJScroll jsc;
	
	private DateChooser date ;
	
	String[][] TableData={};
	String[] Title={"快递单号"};
	
	public InventoryOut(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryOut();
	}

	private void iniInventoryOut() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		
//		barId=new JTextField();
//		barId.setBounds(213, 59, 182, 18);
//		add(barId);
		
		date =new DateChooser(this,213,82);
		
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] desti=new String[trans.size()];
		for(int i=0;i<trans.size();i++){
			desti[i]=trans.get(i).getName();
		}
		destination=new newJCombo(desti);
		destination.setBounds(202, 110, 120, 20);
		add(destination);
		
		String[] blo={"飞机","火车","汽车"};
		transportType=new newJCombo(blo);
		transportType.setBounds(225,139,58,19);
		add(transportType);
		
		transitSheetId=new newJText();
		transitSheetId.setBounds(323, 59, 134, 18);
		add(transitSheetId);
		
		model=new DefaultTableModel(TableData,Title);
		table=new JTable(model);
		jsc=new newJScroll(table);
		jsc.setBounds(143,127,321,196);
		
		
		/*
		 * 	生成货单表格
		 */
		searchBarId = new newJBut("确定");
		searchBarId.setBounds(363, 131, 76, 27);
		add(searchBarId);
		
		searchBarId.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				warning.setForeground(Color.red);
				warning.setBounds(138, 490, 463 - 138, 30);
				// 判断必填项是否填写完成
				if ((transitSheetId.getText().equals("")) || (transitSheetId.getText().equals(""))
						) {
					warning.setText("尚未填写中转单号");
				} else {
					// translate data
					TransitOutVO vo=inventoryservice.getBarIdList(transitSheetId.getText());
					if(vo==null){
						warning.setText("中转单号输入错误");
					} else {
						// 提交成功
						ArrayList<String> barIdList=(ArrayList<String>) vo.barIds;
						TableData=new String[barIdList.size()][1];
						for(int c=0;c<barIdList.size();c++){
							TableData[c][0]=barIdList.get(c);
						}
						model=new DefaultTableModel(TableData,Title);
						table.setModel(model);
						table.repaint();
						
					}
				}
				add(warning);
				repaint();
			}
		});
		
		
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(464, 199, 76, 27);
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
							destination.getSelectedItem().toString(),
							getType(transportType.getSelectedItem().toString()),
							transitSheetId.getText()
							);
					//为每个barId制作单子
					warning.setForeground(Color.red);
					warning.setText("提交成功");
					for(int c=model.getRowCount()-1;c>=0;c--){
						InventoryOutSheetVO vo = new InventoryOutSheetVO(invenOutInf,(String)table.getValueAt(c, 0));
						ResultMessage result = inventoryservice.out(vo);
						if(result.getResult()==Result.SUCCESS){
							model.removeRow(c);
							table.setModel(model);
							table.repaint();
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
	
	

	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background03.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
