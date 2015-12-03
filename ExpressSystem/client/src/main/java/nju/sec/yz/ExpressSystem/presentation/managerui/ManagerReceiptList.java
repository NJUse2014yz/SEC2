package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

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
	
	private JButton pass;

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
		jsc.setBounds(134,100,333,157);
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
		table.setValueAt(Boolean.FALSE, 1, 0);
		table.setValueAt(Boolean.TRUE, 2, 0);
		

		type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<ReceiptVO> volist=receipt.getByType(getType(type.getSelectedItem().toString()));
			TableData=new Object[volist.size()][2];
			for(int i=0;i<volist.size();i++){
				TableData[i][1]=volist.get(i).getId();
			}
			model=new DefaultTableModel(TableData,title);
			table.setModel(model);
			table.repaint();
			}
		});
		
		
		ImageIcon passIcon=new ImageIcon("graphic/manager/button/pass.png");
		pass=new JButton(passIcon);
		pass.setBounds(467-75,262,75,27);
		add(pass);
		
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
	public ReceiptType getType(String str){
		switch(str){
		case "收款单":
			return ReceiptType.COLLECTION;
		case "付款单":
			return ReceiptType.PAYMENT;
		case "寄件单":
			return ReceiptType.DELIVER_RECEIPT;
		case "营业厅装车单":
			return ReceiptType.POSITION_LOADING_RECEIPT;
		case "营业厅收件单":
			return ReceiptType.POSITION_RECEIVE_RECEIPT;
		case "营业厅派送单":
			return ReceiptType.POSITION_SEND_RECEIPT;
		case "汽车中转单":
			return ReceiptType.TRANSIT_CAR_RECEIPT;
		case "火车中转单":
			return ReceiptType.TRANSIT_TRAIN_RECEIPT;
		case "飞机中转单":
			return ReceiptType.TRANSIT_FLIGHT_RECEIPT;
		case "中转中心接收单":
			return ReceiptType.TRANSIT_RECEIVE_RECEIPT;
		case "中转中心装车单":
			return ReceiptType.TRANSIT_LOADING_RECEIPT;
		case "入库单":
			return ReceiptType.INVENTORY_IN;
		case "出库单":
			return ReceiptType.INVENTORY_OUT;
		default:
				return null;
		}
	}
}
