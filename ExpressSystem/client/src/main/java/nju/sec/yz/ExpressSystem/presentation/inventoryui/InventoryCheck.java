package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryCheck extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	private newJBut toExcel;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private newJLabel time=new newJLabel();
	
	public InventoryCheck(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryCheck();
	}

	private void iniInventoryCheck() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("快递编号");
		name.add("入库日期");
		name.add("目的地");
		name.add("区号");
		name.add("排号");
		name.add("架号");
		name.add("位号");
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		InventoryListVO vo=inventoryservice.checkStock();

		table = new newTable(data,name,this,false);
		table.setBounds(136,62,325, 208);
		table.stopAutoRewidth();
		table.join();
		
		ArrayList<InventoryInSheetVO> involist=(ArrayList<InventoryInSheetVO>) inventoryservice.checkStock().inList;
		changeData(involist);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String temp=df.format(new Date());// new Date()为获取当前系统时间
		time.setText("当前时间： "+temp);
		time.setBounds(137, 278, 463 - 198, 30);
		time.setVisible(true);
		add(time);
		
		
//		ImageIcon excel = new ImageIcon("graphic/inventory/button/excel.png");
		toExcel = new newJBut("导出为Excel");
		toExcel.setBounds(366, 320, 100, 26);
		toExcel.setMargin(new java.awt.Insets(0,0,0,0)); 
		add(toExcel);
		setVisible(true);

		toExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				inventoryservice.exportToExcel();
			}
		});
	}
	private void changeData(ArrayList<InventoryInSheetVO> involist)
	{
		data.removeAllElements();
		if(involist!=null){
		for(int i=0;i<involist.size();i++){
			InventoryInInformation temp=involist.get(i).getInventoryInInformation();
			Vector<String> vector=new Vector<String>();
			vector.add(involist.get(i).getBarId());
			vector.add(temp.getTime());
			vector.add(temp.getDestination());
			vector.add(Integer.toString(temp.getBlock()));
			vector.add(Integer.toString(temp.getRow()));
			vector.add(Integer.toString(temp.getShelf()));
			vector.add(Integer.toString(temp.getPositon()));
			data.add(vector);
		}
		table.resetData();
		}
	}
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background04.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
