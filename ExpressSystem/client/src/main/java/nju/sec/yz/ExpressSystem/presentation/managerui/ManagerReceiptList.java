package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.deliverui.OrderIn;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class ManagerReceiptList extends JPanel {

	private ReceiptBlService receipt = new ReceiptController();

	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private JComboBox type;

	private JTable table;
	private TableModel model;
	private JScrollPane jsc;
	private Object[][] TableData = {};
	private String[] title;

	private JButton pass;
	private JButton passAll;

	private ArrayList<ReceiptVO> volist;

	private JLabel warning = new JLabel();

	public ManagerReceiptList(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerReceiptList();
		//test the modify
		
				maincontroler.mainFrame.nextPanel(new
						 ManagerReceiptModify(maincontroler,mbc,new ReceiptVO(),"寄件单"));

//				  OrderIn orderIn=new OrderIn(this);
	}

	private void iniManagerReceiptList() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		

		String[] reitype = { "收款单", "付款单", "寄件单", "营业厅装车单", "营业厅收件单", "营业厅派送单", "汽车中转单", "火车中转单", "飞机中转单", "中转中心接收单",
				"中转中心装车单", "入库单", "出库单" };
		type = new JComboBox(reitype);
		type.setBounds(246, 67, 121, 20);
		add(type);

		model = new DefaultTableModel(TableData, title);
		table = new JTable(model);
		jsc = new JScrollPane(table);
		jsc.setBounds(134, 100, 333, 157);
		add(jsc);

		model = new DefaultTableModel(TableData, title);

		table.setModel(model);
		// table.setEnabled(false);
		// JCheckBox check=new JCheckBox();
		// table.getColumnModel().getColumn(2).setCellEditor(table.getDefaultEditor(Boolean.class));

		// TableColumn aColumn = table.getColumnModel().getColumn(0);
		// aColumn.setCellEditor(table.getDefaultEditor(Boolean.class));
		// aColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		// table.setValueAt(Boolean.FALSE, 1, 0);
		// table.setValueAt(Boolean.TRUE, 2, 0);

		type.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				volist = receipt.getByType(getType(type.getSelectedItem().toString()));
				 TableData=new Object[volist.size()][2];
				 for(int i=0;i<volist.size();i++){
				 String temp=volist.get(i).getMakeTime();
				 temp=temp.substring(0,
				 4)+"/"+temp.substring(4,6)+"/"+temp.substring(6,8);
				 TableData[i][0]=temp;
				 TableData[i][1]=volist.get(i).getId();
				 }
//				switch (type.getSelectedItem().toString()) {
//				case "收款单":
//					iniCollection();
//				case "付款单":
//					iniPayment();
//				case "寄件单":
//					iniDelReip();
//				case "营业厅装车单":
//					iniPosLoad();
//				case "营业厅收件单":
//					iniPosReseive();
//				case "营业厅派送单":
//					iniPosSend();
//				case "汽车中转单":
//					iniTransCar();
//				case "火车中转单":
//					iniTransTrain();
//				case "飞机中转单":
//					iniTranFlight();
//				case "中转中心接收单":
//					iniTransReceive();
//				case "中转中心装车单":
//					iniTransLoad();
//				case "入库单":
//					iniInvenIn();
//				case "出库单":
//					iniInvenOut();
//				}
				model = new DefaultTableModel(TableData, title);
				table.setModel(model);
				table.repaint();
			}
		});

		 table.addMouseListener(new MouseAdapter() {
		 public void MouseClicked(MouseEvent e){
		 if(e.getClickCount()==2){
		 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
		// int col=((JTable)e.getSource()).columnAtPoint(e.getPoint());
		 //获得列位置 String
		
		 maincontroler.mainFrame.nextPanel(new
		 ManagerReceiptModify(maincontroler,mbc,volist.get(row),type.getSelectedItem().toString()));
		
		 }
		 }
		 });

		ImageIcon passIcon = new ImageIcon("graphic/manager/button/pass.png");
		pass = new JButton(passIcon);
		pass.setBounds(467 - 75, 262, 75, 27);
		add(pass);

		pass.addMouseListener(new MouseAdapter() {
			public void MouseClicked(MouseEvent e) {
				int[] deletelines = table.getSelectedRows();
				for (int c = 0; c < deletelines.length; c++) {
					ResultMessage tempresult = receipt.approve(volist.get(deletelines[c]));
					if (tempresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						warning.setText(tempresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
						break;
					} else {
						// 如果成功最后才显示成功
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

		ImageIcon passAllIcon = new ImageIcon("graphic/manager/button/passAll.png");
		passAll = new JButton(passAllIcon);
		passAll.setBounds(467 - 160, 262, 75, 27);
		add(passAll);

		passAll.addMouseListener(new MouseAdapter() {
			public void MouseClicked(MouseEvent e) {
				for (int c = 0; c < volist.size(); c++) {
					ResultMessage tempresult = receipt.approve(volist.get(c));
					if (tempresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						warning.setText(tempresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
						break;
					} else {
						// 如果成功最后才显示成功
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

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background17.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

	public ReceiptType getType(String str) {
		switch (str) {
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

//	// 收款单
//	private void iniCollection() {
//		// TODO Auto-generated method stub
//
//		TableData = new String[volist.size()][];
//		title = new String[] { "填写日期", "表单号" };
//	ArrayList<CollectionRecordVO> newlist = (ArrayList<CollectionRecordVO>) volist;
//		for (int c = 0; c < volist.size(); c++) {
//			String temp = volist.get(c).getMakeTime();
//			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
//			TableData[c][0] = temp;
//			TableData[c][1] = volist.get(c).getId();
//			TableData[c][2] = volist.get(c).getMakePerson();
//			TableData[c][3] = volist.get(c);
//			TableData[c][4] = TableData[c][5] = volist.get(c).getId();
//		}
//
//	}
//
//	// 付款单
//	private void iniPayment() {
//		// TODO Auto-generated method stub
//
//		title = new String[] { "填写日期", "表单号", "填表人", "营业厅编号", "收款时间", "收款人", "收款金额" };
//
//		TableData = new String[volist.size()][7];
//		for (int c = 0; c < volist.size(); c++) {
//			String tempId = volist.get(c).getId();
//			PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle(Integer.parseInt(tempId));
//
//			PaymentInformation tempInf = tempvo.getPaymentInformation();
//			String temp = tempvo.getMakeTime();
//			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
//			TableData[c][0] = temp;
//			TableData[c][1] = tempvo.getBarIds();
//			TableData[c][2] = tempvo.getMakePerson();
//			TableData[c][3] = tempInf.getPositionId();
//			TableData[c][4] = tempInf.getTime();
//			TableData[c][5] = tempInf.getInDeliverId();
//			TableData[c][6] = tempInf.getAmount();
//
//		}
//
//	}
//
//	// 寄件单
//	private void iniDelReip() {
//		// TODO Auto-generated method stub
//
//		title=new String[]{"填写日期","表单号","填表人","派件时间","递送人"};
//		
//		TableData = new String[volist.size()][5];
//		for(int c=0;c<volist.size();c++){
//			String tempId=volist.get(c).getId();
//			SendSheetVO tempvo=(SendSheetVO) receipt.getSingle(Integer.parseInt(tempId));
//			
//			SendInformation tempInf=tempvo.getSendInformation();
//			String temp=tempvo.getMakeTime();
//			 temp=temp.substring(0,4)+"/"+temp.substring(4,6)+"/"+temp.substring(6,8);
//			TableData[c][0]=temp;
//			TableData[c][1]=tempvo.getId();
//			TableData[c][2]=tempvo.getMakePerson();
//			TableData[c][3]=tempInf.getTime();
//			TableData[c][4]=tempInf.getOutDeliverId();
//			
//		}
//	}
//
//	// 营业厅装车单
//	private void iniPosLoad() {
//		// TODO Auto-generated method stub
//
//		title=new String[]{"填写日期","表单号","填表人","派件时间","递送人"};
//		
//		TableData = new String[volist.size()][5];
//		for(int c=0;c<volist.size();c++){
//			String tempId=volist.get(c).getId();
//			DeliverySheetVO tempvo=(DeliverySheetVO) receipt.getSingle(Integer.parseInt(tempId));
//			
//			DeliveryInformation tempInf=tempvo.getDeliveryInformation();
//			String temp=tempvo.getMakeTime();
//			 temp=temp.substring(0,4)+"/"+temp.substring(4,6)+"/"+temp.substring(6,8);
//			TableData[c][0]=temp;
//			TableData[c][1]=tempvo.getId();
//			TableData[c][2]=tempvo.getMakePerson();
//			TableData[c][3]=tempInf.getTime();
//			TableData[c][4]=tempInf.getOutDeliverId();
//			
//		}
//	}
//
//	// 营业厅收件单
//	private void iniPosReseive() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 营业厅派送单
//	private void iniPosSend() {
//		// TODO Auto-generated method stub
//
//
//		title=new String[]{"填写日期","表单号","填表人","派件时间","派件人"};
//		
//		TableData = new String[volist.size()][5];
//		for(int c=0;c<volist.size();c++){
//			String tempId=volist.get(c).getId();
//			DeliverySheetVO tempvo=(DeliverySheetVO) receipt.getSingle(Integer.parseInt(tempId));
//			
//			DeliveryInformation tempInf=tempvo.getDeliveryInformation();
//			String temp=tempvo.getMakeTime();
//			 temp=temp.substring(0,4)+"/"+temp.substring(4,6)+"/"+temp.substring(6,8);
//			TableData[c][0]=temp;
//			TableData[c][1]=tempvo.getId();
//			TableData[c][2]=tempvo.getMakePerson();
//			TableData[c][3]=tempInf.getTime();
//			TableData[c][4]=tempInf.getOutDeliverId();
//			
//		}
//	}
//
//	// 汽车中转单
//	private void iniTransCar() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 火车中转单
//	private void iniTransTrain() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 飞机中转单
//	private void iniTranFlight() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 中转中心接收单
//	private void iniTransReceive() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 中转中心装车单
//	private void iniTransLoad() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 入库单
//	private void iniInvenIn() {
//		// TODO Auto-generated method stub
//
//	}
//
//	// 出库单
//	private void iniInvenOut() {
//		// TODO Auto-generated method stub
//
//	}

}
