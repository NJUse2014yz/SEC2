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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
import nju.sec.yz.ExpressSystem.common.TransitTrainInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

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
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		jsc = new JScrollPane(table);
		jsc.setBounds(134, 100, 333, 157);
		add(jsc);

		model = new DefaultTableModel(TableData, title);

		table.setModel(model);
//		table.setEnabled(false);

		type.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				volist = receipt.getByType(getType(type.getSelectedItem().toString()));
				System.out.println(volist.size());
				
				switch (type.getSelectedItem().toString()) {
				case "收款单":
					iniCollection();
					break;
				case "付款单":
					iniPayment();
					break;
				case "寄件单":
					iniDelReip();
					break;
				case "营业厅装车单":
					iniPosLoad();
					break;
				case "营业厅收件单":
					iniPosReseive();
				case "营业厅派送单":
					iniPosSend();
					break;
				case "汽车中转单":
					iniTransCar();
					break;
				case "火车中转单":
					iniTransTrain();
					break;
				case "飞机中转单":
					iniTranFlight();
					break;
				case "中转中心接收单":
					iniTransReceive();
					break;
				case "中转中心装车单":
					iniTransLoad();
					break;
				case "入库单":
					iniInvenIn();
					break;
				case "出库单":
					iniInvenOut();
					break;
				}
				model = new DefaultTableModel(TableData, title);
				table.setModel(model);
				table.repaint();
			}
		});


		ImageIcon passIcon = new ImageIcon("graphic/manager/button/pass.png");
		pass = new JButton(passIcon);
		pass.setBounds(467 - 75, 262, 75, 27);
		add(pass);

		pass.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int[] deletelines = table.getSelectedRows();
				for (int c = 0; c < deletelines.length; c++) {

					System.out.println(deletelines[0]);
					ReceiptVO newVO=modifyVO(deletelines[c], volist.get(deletelines[c]).getId());
					ResultMessage modifyresult=receipt.approve(newVO);
					ResultMessage approveresult = receipt.approve(newVO);
					if (modifyresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						System.out.println("modifysuccess!!!!!!!!!!!!!!!");
						warning.setText(modifyresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
						break;
					} else if(approveresult.getResult() == Result.FAIL){
						System.out.println("approvesuccess!!!!!!!!!!!!!!!");
						// 如果失败会跳出，显示失败
						warning.setText(approveresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
						break;
					}else{
						System.out.println("success!!!!!!!!!!!!!!!");
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
			public void mouseClicked(MouseEvent e) {
				for (int c = 0; c < volist.size(); c++) {
					
					ResultMessage tempresult = receipt
							.approve(modifyVO(c, volist.get(c).getId()));
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

	// 收款单
	private void iniCollection() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "收款时间", "收款人", "收款金额" };

		TableData = new String[volist.size()][6];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			CollectionRecordVO tempvo = (CollectionRecordVO) receipt.getSingle((tempId));

			// PaymentInformation tempInf = tempvo.getPaymentInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempvo.getTime();
			TableData[c][4] = tempvo.getDeliverId();
			TableData[c][5] = tempvo.getAmount();

		}

	}

	private ReceiptVO modifyCollection(int c, String Id) {
		CollectionRecordVO tempvo = (CollectionRecordVO) receipt.getSingle((Id));
		
		String temp =(String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setBarId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempvo.setTime((String) TableData[c][3]);
		tempvo.setDeliverId((String) TableData[c][4]);
		tempvo.setAmount((Double) TableData[c][5]);
		
		return tempvo;
	}

	// 付款单
	private void iniPayment() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "营业厅编号", "收款时间", "收款人", "收款金额" };

		TableData = new String[volist.size()][7];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((tempId));

			PaymentInformation tempInf = tempvo.getPaymentInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getPositionId();
			TableData[c][4] = tempInf.getTime();
			TableData[c][5] = tempInf.getInDeliverId();
			TableData[c][6] = tempInf.getAmount();

		}

	}

	private ReceiptVO modifyPayment(int c, String Id) {
		PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((Id));
		PaymentInformation tempInf = tempvo.getPaymentInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setInDeliverId((String) TableData[c][4]);
		tempInf.setAmount((Double) TableData[c][5]);

		tempvo.setPaymentInformation(tempInf);
		return tempvo;
	}

	// 寄件单
	private void iniDelReip() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "寄件人姓名", "寄件人地址", "寄件人手机", "收件人姓名", "收件人地址", "收件人手机", "件数", "重量",
				"品名", "快递费", "预计送达时间" };

		TableData = new Object[volist.size()][14];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			SendSheetVO tempvo = (SendSheetVO) receipt.getSingle((tempId));

			SendInformation tempInf = tempvo.getSendInformation();
			ToAndFromInformation fromPerson = tempInf.getFromPerson();
			ToAndFromInformation toPerson = tempInf.getToPerson();
			GoodInformation good = tempInf.getGood();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = fromPerson.getName();
			TableData[c][4] = fromPerson.getAddress();
			TableData[c][5] = fromPerson.getCellphone();
			TableData[c][6] = toPerson.getName();
			TableData[c][7] = toPerson.getAddress();
			TableData[c][8] = toPerson.getCellphone();
			TableData[c][9] = good.getTotal();
			TableData[c][10] = good.getWeight();
			TableData[c][11] = good.getName();
			TableData[c][12] = tempInf.getCostForAll();
			TableData[c][13] = tempInf.getPredictTime();

		
		}
	}

	private ReceiptVO modifyDelReip(int c, String Id) {

		SendSheetVO tempvo = (SendSheetVO) receipt.getSingle((Id));

		SendInformation tempInf = tempvo.getSendInformation();
		ToAndFromInformation fromPerson = tempInf.getFromPerson();
		ToAndFromInformation toPerson = tempInf.getToPerson();
		GoodInformation good = tempInf.getGood();

		String temp =(String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setCostForAll((Double) TableData[c][12]);
		tempInf.setPredictTime((int) TableData[c][13]);
		good.setTotal((String) TableData[c][9]);
		good.setWeight((String) TableData[c][10]);
		good.setName((String) TableData[c][11]);
		fromPerson.setName((String) TableData[c][3]);
		fromPerson.setAddress((String) TableData[c][4]);
		fromPerson.setCellphone((String) TableData[c][5]);
		toPerson.setName((String) TableData[c][6]);
		toPerson.setAddress((String) TableData[c][7]);
		toPerson.setCellphone((String) TableData[c][8]);

		tempInf.setFromPerson(fromPerson);
		tempInf.setToPerson(toPerson);
		tempvo.setSendInformation(tempInf);

		return tempvo;
	}

	// 营业厅装车单
	private void iniPosLoad() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "派件时间", "递送人" };

		TableData = new String[volist.size()][5];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((tempId));

			DeliveryInformation tempInf = tempvo.getDeliveryInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getOutDeliverId();

		}
	}
	
	private ReceiptVO modifyPosLoad(int c, String Id) {
		DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((Id));
		DeliveryInformation tempInf = tempvo.getDeliveryInformation();
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setOutDeliverId((String) TableData[c][4]);

		tempvo.setDeliveryInformation(tempInf);
		return tempvo;
		
	}

	// 营业厅收件单
	private void iniPosReseive() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "派件时间", "递送人" };

		TableData = new String[volist.size()][6];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			OfficeArriveSheetVO tempvo = (OfficeArriveSheetVO) receipt.getSingle((tempId));

			ArriveInformation tempInf = tempvo.getOfficeArrive();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getTransitSheetId();
			TableData[c][5] = tempInf.getDeparture();
		}
	}
	
	private ReceiptVO modifyPosReseive(int c, String Id) {
		OfficeArriveSheetVO tempvo = (OfficeArriveSheetVO) receipt.getSingle((Id));
		ArriveInformation tempInf = tempvo.getOfficeArrive();
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setTransitSheetId((String) TableData[c][4]);
		tempInf.setDeparture((String) TableData[c][5]);

		tempvo.setOfficeArrive(tempInf);
		return tempvo;
		
	}

	// 营业厅派送单
	private void iniPosSend() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "派件时间", "派件人" };

		TableData = new String[volist.size()][5];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((tempId));

			DeliveryInformation tempInf = tempvo.getDeliveryInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getOutDeliverId();

		}
	}
	

	private ReceiptVO modifyPosSend(int c, String Id) {
		DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((Id));
		DeliveryInformation tempInf = tempvo.getDeliveryInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setOutDeliverId((String) TableData[c][4]);

		tempvo.setDeliveryInformation(tempInf);
		return tempvo;
		
	}

	// 汽车中转单
	private void iniTransCar() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "中转时间", "中转中心汽运编号", "出发地", "到达地", "监装员", "押运员" };

		TableData = new String[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitCarInformation tempInf = (TransitCarInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getCarTransitId();
			TableData[c][5] = tempInf.getDeparture();
			TableData[c][6] = tempInf.getDestination();
			TableData[c][7] = tempInf.getTransiterId();
			TableData[c][8] = tempInf.getDriverId();
		}
	}
	

	private ReceiptVO modifyTransCar(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitCarInformation tempInf = (TransitCarInformation)tempvo.getTransitInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setCarTransitId((String) TableData[c][4]);
		tempInf.setDeparture((String) TableData[c][5]);
		tempInf.setDestination((String) TableData[c][6]);
		tempInf.setTransiterId((String) TableData[c][7]);
		tempInf.setDriverId((String) TableData[c][8]);

		tempvo.setTransitInformation(tempInf);
		return tempvo;
		
	}


	// 火车中转单
	private void iniTransTrain() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "中转时间", "中转中心货运编号", "出发地", "到达地", "车厢号", "押运员" };

		TableData = new String[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitTrainInformation tempInf = (TransitTrainInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getTrainTransitId();
			TableData[c][5] = tempInf.getDeparture();
			TableData[c][6] = tempInf.getDestination();
			TableData[c][7] = tempInf.getTransiterId();
			TableData[c][8] = tempInf.getCarriageId();
		}
	}
	
	private ReceiptVO modifyTransTrain(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitTrainInformation tempInf = (TransitTrainInformation)tempvo.getTransitInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setTrainTransitId((String) TableData[c][4]);
		tempInf.setDeparture((String) TableData[c][5]);
		tempInf.setDestination((String) TableData[c][6]);
		tempInf.setTransiterId((String) TableData[c][7]);
		tempInf.setCarriageId((String) TableData[c][8]);

		tempvo.setTransitInformation(tempInf);
		return tempvo;
		
	}


	// 飞机中转单
	private void iniTranFlight() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "中转时间", "中转中心汽运编号", "出发地", "到达地", "货柜号", "押运员" };

		TableData = new String[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitFlightInformation tempInf = (TransitFlightInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getFlightTransitId();
			TableData[c][5] = tempInf.getDeparture();
			TableData[c][6] = tempInf.getDestination();
			TableData[c][7] = tempInf.getTransiterId();
			TableData[c][8] = tempInf.getShelfId();
		}
	}

	private ReceiptVO modifyTranFlight(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitFlightInformation tempInf = (TransitFlightInformation)tempvo.getTransitInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setFlightTransitId((String) TableData[c][4]);
		tempInf.setDeparture((String) TableData[c][5]);
		tempInf.setDestination((String) TableData[c][6]);
		tempInf.setTransiterId((String) TableData[c][7]);
		tempInf.setShelfId((String) TableData[c][8]);

		tempvo.setTransitInformation(tempInf);
		return tempvo;
		
	}
	// 中转中心接收单
	private void iniTransReceive() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "接收时间", "中转单编号", "出发地" };

		TableData = new String[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			TransitArriveSheetVO tempvo = (TransitArriveSheetVO) receipt.getSingle((tempId));
			ArriveInformation tempInf = (ArriveInformation) tempvo.getTransitArriveInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getTransitSheetId();
			TableData[c][5] = tempInf.getDeparture();
		}

	}
	private ReceiptVO modifyTransReceive(int c, String Id) {
		TransitArriveSheetVO tempvo = (TransitArriveSheetVO) receipt.getSingle((Id));
		ArriveInformation tempInf = (ArriveInformation)tempvo.getTransitArriveInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setTransitSheetId((String) TableData[c][4]);
		tempInf.setDeparture((String) TableData[c][5]);
		
		tempvo.setTransitArriveInformation(tempInf);
		return tempvo;
		
	}
	// 中转中心装车单
	private void iniTransLoad() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "装车时间", "装车单编号", "目的地", "车辆代号", "监装员", "押运员", "运费" };

		TableData = new String[volist.size()][10];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			TransitLoadSheetVO tempvo = (TransitLoadSheetVO) receipt.getSingle((tempId));
			LoadInformation tempInf = (LoadInformation) tempvo.getTransitLoadInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getTransportId();
			TableData[c][5] = tempInf.getDestinationId();
			TableData[c][6] = tempInf.getCarId();
			TableData[c][7] = tempInf.getOfficerId();
			TableData[c][8] = tempInf.getDriverId();
			TableData[c][9] = tempInf.getFare();
		}

	}
	private ReceiptVO modifyTransLoad(int c, String Id) {
		TransitLoadSheetVO tempvo = (TransitLoadSheetVO) receipt.getSingle((Id));
		LoadInformation tempInf = tempvo.getTransitLoadInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setTransportId((String) TableData[c][4]);
		tempInf.setDestinationId((String) TableData[c][5]);
		tempInf.setCarId((String) TableData[c][6]);
		tempInf.setOfficerId((String) TableData[c][7]);
		tempInf.setDriverId((String) TableData[c][8]);
		tempInf.setFare((Double) TableData[c][9]);
		
		tempvo.setTransitLoadInformation(tempInf);
		return tempvo;
		
	}
	// 入库单
	private void iniInvenIn() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "入库时间", "目的地", "区号", "排号", "架号", "位号" };

		TableData = new Object[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			InventoryInSheetVO tempvo = (InventoryInSheetVO) receipt.getSingle((tempId));
			InventoryInInformation tempInf = (InventoryInInformation) tempvo.getInventoryInInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getDestination();
			TableData[c][5] = tempInf.getBlock();
			TableData[c][6] = tempInf.getRow();
			TableData[c][7] = tempInf.getShelf();
			TableData[c][8] = tempInf.getPositon();

		}
	}
	private ReceiptVO modifyInvenIn(int c, String Id) {
		InventoryInSheetVO tempvo = (InventoryInSheetVO) receipt.getSingle((Id));
		InventoryInInformation tempInf = tempvo.getInventoryInInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setDestination((String) TableData[c][4]);
		tempInf.setBlock((int) TableData[c][5]);
		tempInf.setRow((int) TableData[c][6]);
		tempInf.setShelf((int) TableData[c][7]);
		tempInf.setPositon((int) TableData[c][8]);
		
		tempvo.setInventoryInInformation(tempInf);
		return tempvo;
	}

	// 出库单
	private void iniInvenOut() {

		title = new String[] { "填写日期", "表单号", "填表人", "入库时间", "目的地", "区号", "排号", "架号", "位号" };

		TableData = new String[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			InventoryOutSheetVO tempvo = (InventoryOutSheetVO) receipt.getSingle((tempId));
			InventoryOutInformation tempInf = (InventoryOutInformation) tempvo.getInventoryOutInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getDestination();
			TableData[c][5] = tempInf.getTransitId();
			TableData[c][6] = tempInf.getTransportType();
		}
	}
	private ReceiptVO modifyInvenOut(int c, String Id) {
		InventoryOutSheetVO tempvo = (InventoryOutSheetVO) receipt.getSingle((Id));
		InventoryOutInformation tempInf = tempvo.getInventoryOutInformation();
		
		String temp = (String) TableData[c][0];
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) TableData[c][1]);
		tempvo.setMakePerson((String) TableData[c][2]);
		tempInf.setTime((String) TableData[c][3]);
		tempInf.setDestination((String) TableData[c][4]);
		tempInf.setTransitId((String) TableData[c][5]);
//		tempInf.setTransportType((String) TableData[c][6]);
		
		
		tempvo.setInventoryOutInformation(tempInf);
		return tempvo;
		
	}
	// 依据类型对表单信息进行更新
				private ReceiptVO modifyVO(int count, String id) {
					// TODO Auto-generated method stub
					switch (type.getSelectedItem().toString()) {
					case "收款单":
						return modifyCollection(count, id);
					case "付款单":
						return modifyPayment(count, id);
					case "寄件单":
						return modifyDelReip(count, id);
					case "营业厅装车单":
						return modifyPosLoad(count, id);
					case "营业厅收件单":
						return modifyPosReseive(count, id);
					case "营业厅派送单":
						return modifyPosSend(count, id);
					case "汽车中转单":
						return modifyTransCar(count, id);
					case "火车中转单":
						return modifyTransTrain(count, id);
					case "飞机中转单":
						return modifyTranFlight(count, id);
					case "中转中心接收单":
						return modifyTransReceive(count, id);
					case "中转中心装车单":
						return modifyTransLoad(count, id);
					case "入库单":
						return modifyInvenIn(count, id);
					case "出库单":
						return modifyInvenOut(count, id);
					default:
						return null;
					}
				}
}
