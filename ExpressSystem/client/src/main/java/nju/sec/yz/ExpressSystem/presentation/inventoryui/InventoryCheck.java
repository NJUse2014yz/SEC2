package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryCheck extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	private JButton toExcel;
//	private JLabel warning=new JLabel();
	private JTable table;
	
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
		
		Object[][] tableData = {};
		String[] columnTitle = { "快递编号", "入库日期", "目的地", "区号", "排号", "架号", "位号" };
		table = new JTable(tableData, columnTitle);
		// 将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
		JScrollPane jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(136,62,325, 208);
		add(jsc);
		
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
