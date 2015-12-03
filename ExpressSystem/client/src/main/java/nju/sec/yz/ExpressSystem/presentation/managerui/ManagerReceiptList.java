package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerReceiptList extends JPanel {

	private ReceiptBlService receipt = new ReceiptController();

	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private JComboBox type;
	
	private JTable table;
	private TableModel model;
	private JScrollPane jsc;
	private Object[][] TableData={};
	private String[] title={};

	public ManagerReceiptList(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerReceiptList();
		
		String[] reitype={"收款单","付款单","寄件单","营业厅装车单",
				"营业厅收件单","营业厅派送单","汽车中转单","火车中转单","飞机中转单",
				"中转中心接收单","中转中心装车单","入库单","出库单"};
		type=new JComboBox(reitype);
		type.setBounds(246,67,121,20);
		add(type);
		
		model=new DefaultTableModel(TableData,title);
		table=new JTable(model);
		jsc=new JScrollPane(table);
		jsc.setBounds(134,100,333,257);
		add(jsc);
		
		TableData=new String[3][2];
		title=new String[2];
		model=new DefaultTableModel(TableData,title);
		
		table.setModel(model);
//		JCheckBox check=new JCheckBox();
//		table.getColumnModel().getColumn(2).setCellEditor(table.getDefaultEditor(Boolean.class));
		
		
		
		TableColumn   aColumn   =   table.getColumnModel().getColumn(0);   
		aColumn.setCellEditor(table.getDefaultEditor(Boolean.class));  
		aColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
//		table.setValueAt(Boolean.FALSE, 1, 0);
//		table.setValueAt(Boolean.TRUE, 2, 0);
		

	}

	private void iniManagerReceiptList() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background17.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
