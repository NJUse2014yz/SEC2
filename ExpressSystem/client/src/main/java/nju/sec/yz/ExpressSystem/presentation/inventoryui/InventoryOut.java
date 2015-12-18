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
	private newJCombo destination;
	
	private newJCombo transportType;
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
		
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] desti=new String[trans.size()];
		for(int i=0;i<trans.size();i++){
			desti[i]=trans.get(i).getName();
		}
		destination=new newJCombo(desti);
		destination.setBounds(202, 110, 120, 20);
		add(destination);
		
		warning=new newJLabel();
		warning.setForeground(Color.red);
		warning.setBounds(138, 490, 463 - 138, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		add(warning);
		
		String[] blo={"飞机","火车","汽车"};
		transportType=new newJCombo(blo);
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
						warning.setVisible(false);
						ArrayList<String> barIdList = (ArrayList<String>) vo.barIds;
						changeData(barIdList);
						table.resetData();
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
		for(int c=0;c<bars.size();c++){
			Vector<String> vector=new Vector<String>();
			vector.add(bars.get(c));
			data.add(vector);
		}
	}
	

	@Override 
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background03.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
