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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;

public class InventoryObserve extends JPanel{

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	private newJLabel total;
	private newTable table1;
	private newTable table2;
	private Vector<Vector<String>> data1=new Vector<Vector<String>>();
	private Vector<Vector<String>> data2=new Vector<Vector<String>>();
	private Vector<String> name1=new Vector<String>();
	private Vector<String> name2=new Vector<String>();
	private ArrayList<InventoryInSheetVO> involist;
	private ArrayList<InventoryOutSheetVO> outvolist;
	
	private DateChooser date1;
	private DateChooser date2;
	
//	ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
	

	public InventoryObserve(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryObserve();
	}

	private void iniInventoryObserve() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		name1.add("快递编号");
		name1.add("入库日期");
		name1.add("目的地");
		name1.add("区号");
		name1.add("排号");
		name1.add("架号");
		name1.add("位号");
		
		name2.add("快递编号");
		name2.add("出库日期");
		name2.add("目的地");
		
		
		date1=new DateChooser(this,214,60);
		date2=new DateChooser(this,214,87);
		
		table1 = new newTable(data1,name1,this,false);
		table1.setBounds(140,117,319, 148);
		table1.join();
		
		table2 = new newTable(data2,name2,this,false);
		table2.setBounds(140,275,319, 148);
		table2.join();
		
		total=new newJLabel();
		total.setBounds(229, 432, 62, 23);
		add(total);
		
		warning.setBounds(138, 490, 463 - 138, 30);
		warning.setForeground(Color.red);
		
		confirm = new newJBut("确定");
		confirm.setBounds(140+319-76, 87, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int daystart = Integer.parseInt(date1.getTime());
				int dayend = Integer.parseInt(date2.getTime());

				if (daystart > dayend) {
					// 失败
					warning.setText("日期数据有问题");
					warning.setVisible(true);
				} else {
					// 成功
					InventoryListVO vo = inventoryservice.observeStock(
							date1.getTime(), date2.getTime());
					involist=(ArrayList<InventoryInSheetVO>) vo.inList;
					outvolist=(ArrayList<InventoryOutSheetVO>) vo.outList;
					if((involist!=null)||(outvolist!=null)){
//					involist = (ArrayList<InventoryInSheetVO>) inventoryservice
//							.checkStock().inList;
					changeData(involist,outvolist);
					table1.resetData();
					table2.resetData();
					// 需要得到一个当前数目的值
					total.setText(Integer.toString(involist.size()-outvolist.size()));
					total.setVisible(true);
					warning.setText("");
				}else{
					warning.setText("不存在相关数据");
				}
					
				}
				add(warning);
				repaint();
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background05.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
	private void changeData(ArrayList<InventoryInSheetVO> involist,ArrayList<InventoryOutSheetVO> outvolist)
	{
		data1.removeAllElements();
		data2.removeAllElements();
		for(int i=0;i<involist.size();i++){
			InventoryInInformation temp=involist.get(i).getInventoryInInformation();
			Vector<String> vector=new Vector<String>();
			vector.add(involist.get(i).getBarId());
			vector.add(temp.getTime());
			vector.add(temp.getDestination());
			vector.add(Integer.toString(temp.getBlock()));
			vector.add(Integer.toString(temp.getShelf()));
			vector.add(Integer.toString(temp.getRow()));
			vector.add(Integer.toString(temp.getPositon()));
			data1.add(vector);
		}
		for(int i=0;i<outvolist.size();i++){
			InventoryOutInformation temp=outvolist.get(i).getInventoryOutInformation();
			Vector<String> vector=new Vector<String>();
			vector.add(involist.get(i).getBarId());
			vector.add(temp.getTime());
			vector.add(temp.getDestination());
			
			data2.add(vector);
		}
	}
}
