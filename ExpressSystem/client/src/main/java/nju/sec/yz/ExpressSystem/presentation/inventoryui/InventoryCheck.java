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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryCheck extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	private JButton toExcel;
//	private JLabel warning=new JLabel();
	private JTable table;
	
	private JLabel time=new JLabel();
	
	public InventoryCheck(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryCheck();
	}

	private void iniInventoryCheck() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		InventoryListVO vo=inventoryservice.checkStock();

		ArrayList<InventoryInSheetVO> involist=(ArrayList<InventoryInSheetVO>) inventoryservice.checkStock().inList;
		
		Object[][] tableData = new Object[involist.size()][7];
		for(int i=0;i<involist.size();i++){
			InventoryInInformation temp=involist.get(i).getInventoryInInformation();
			tableData[i][0]=involist.get(i).getBarId();
			tableData[i][1]=temp.getTime();
			tableData[i][2]=temp.getDestination();
			tableData[i][3]=temp.getBlock();
			tableData[i][4]=temp.getPositon();
			tableData[i][5]=involist.get(i).getBarId();
			tableData[i][6]=involist.get(i).getBarId();
		}
		String[] columnTitle = { "快递编号", "入库日期", "目的地", "区号", "排号", "架号", "位号" };
		table = new JTable(tableData, columnTitle);
		
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(5).setMinWidth(60);
		table.getColumnModel().getColumn(6).setMinWidth(60);
		
		 
		
		// 将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
		JScrollPane jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(136,62,325, 208);
		add(jsc);
		
		// 水平滚动条
		jsc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jsc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String temp=df.format(new Date());// new Date()为获取当前系统时间
		time.setText("当前时间： "+temp);
		time.setBounds(137, 296, 463 - 198, 30);
		time.setFont(new Font("Dialog", 1, 15));
		time.setForeground(Color.LIGHT_GRAY);
		time.setVisible(true);
		add(time);
		
		
		ImageIcon excel = new ImageIcon("graphic/inventory/button/excel.png");
		toExcel = new JButton(excel);
		toExcel.setBounds(380, 302, 82, 26);
		add(toExcel);
		setVisible(true);

		toExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				inventoryservice.exportToExcel();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background04.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
