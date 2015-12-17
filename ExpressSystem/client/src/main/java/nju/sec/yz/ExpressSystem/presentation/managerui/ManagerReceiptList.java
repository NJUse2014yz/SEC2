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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
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
import nju.sec.yz.ExpressSystem.common.OutInformation;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
import nju.sec.yz.ExpressSystem.common.TransitTrainInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
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

	private newJBut pass;
	private newJBut passAll;
	private newJBut modify;

	private ArrayList<ReceiptVO> volist;

	private newJLabel warning = new newJLabel();

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

		warning.setForeground(Color.red);

		String[] reitype = { "收款单", "付款单", "寄件单", "营业厅装车单", "营业厅收件单", "营业厅派送单", "汽车中转单", "火车中转单", "飞机中转单", "中转中心接收单",
				"中转中心装车单", "入库单", "出库单" };
		type = new newJCombo(reitype);
		type.setBounds(246, 67, 121, 20);
		add(type);

		model = new DefaultTableModel(TableData, title);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		jsc = new JScrollPane(table);
		// 水平滚动条
		jsc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

		jsc.setBounds(134, 100, 333, 157);
		add(jsc);

		model = new DefaultTableModel(TableData, title);

		table.setModel(model);
		// table.setEnabled(false);

		type.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				volist = receipt.getByType(getType(type.getSelectedItem().toString()));
				System.out.println(volist.size());

				switch (type.getSelectedItem().toString()) {
				case "收款单":
					iniPayment();
					break;
				case "付款单":
					iniOut();
					break;
				case "寄件单":
					iniDelReip();
					break;
				case "营业厅装车单":
					iniPosLoad();
					break;
				case "营业厅收件单":
					iniPosReseive();
					break;
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

		// ImageIcon passIcon = new
		// ImageIcon("graphic/manager/button/pass.png");
		pass = new newJBut("通过");
		pass.setBounds(467 - 75, 262, 75, 27);
		add(pass);

		pass.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				int[] deletelines = table.getSelectedRows();
				for (int c = 0; c < deletelines.length; c++) {
					ResultMessage tempresult = receipt
							.approve(modifyVO(deletelines[c], volist.get(deletelines[c]).getId()));

					if (tempresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						warning.setText(tempresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						add(warning);
						repaint();
						break;
					} else {
						// 如果成功最后才显示成功
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						add(warning);

						repaint();
					}
				}
			}

		});

		// ImageIcon passAllIcon = new
		// ImageIcon("graphic/manager/button/passAll.png");
		passAll = new newJBut("全部通过");
		passAll.setBounds(467 - 160, 262, 75, 27);
		add(passAll);

		passAll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("BBBBBBBBBBBBBBBBBBBBBB");
				int[] changedlines = table.getSelectedRows();
				for (int c = 0; c < volist.size(); c++) {

					ResultMessage tempresult = receipt.approve(modifyVO(c, volist.get(c).getId()));
					if (tempresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						warning.setText(tempresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						add(warning);
						repaint();
						break;
					} else {// 如果成功最后才显示成功
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						add(warning);

						repaint();
					}
				}
			}
		});

		modify = new newJBut("修改");
		modify.setBounds(467 - 245, 262, 75, 27);
		add(modify);

		modify.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("CCCCCCCCCCCCCCCCCCCCCCCC");
				int[] changedlines = table.getSelectedRows();
				warning.setText("提交成功");
				warning.setBounds(270, 490, 70, 30);
				add(warning);
				warning.setVisible(true);
				for (int c = 0; c < changedlines.length; c++) {
					ResultMessage tempresult = receipt
							.modify(modifyVO(changedlines[c], volist.get(changedlines[c]).getId()));
					System.out.println("KKKKKKKKKKKKKKKKK");
					if (tempresult.getResult() == Result.FAIL) {
						// 如果失败会跳出，显示失败
						warning.setText(tempresult.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						add(warning);
						break;
					}
				}
				repaint();
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

	// 付款单
	private void iniOut() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "付款时间", "收款金额", "收款人", "付款账号", "条目", "账户" };

		TableData = new Object[volist.size()][9];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			OutVO tempvo = (OutVO) receipt.getSingle((tempId));
			OutInformation tempInf = tempvo.getOutInformation();
			// PaymentInformation tempInf = tempvo.getPaymentInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getDate();
			TableData[c][4] = ((Double) tempInf.getNum()).toString();
			TableData[c][5] = tempInf.getPerson();
			TableData[c][6] = tempInf.getAccount();
			TableData[c][7] = tempInf.getReason();
			TableData[c][8] = tempInf.getComments();

		}

	}

	private ReceiptVO modifyOut(int c, String Id) {
		// PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((Id));
		OutVO tempvo = (OutVO) receipt.getSingle((Id));
		OutInformation tempInf = tempvo.getOutInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setDate((String) table.getValueAt(c, 3));
		tempInf.setNum(Double.parseDouble((String) table.getValueAt(c, 4)));
		tempInf.setPerson((String) table.getValueAt(c, 5));
		tempInf.setAccount((String) table.getValueAt(c, 6));
		tempInf.setReason((String) table.getValueAt(c, 7));
		tempInf.setComments((String) table.getValueAt(c, 8));

		return tempvo;
	}

	// 收款单
	private void iniPayment() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "营业厅编号", "收款时间", "收款人", "收款金额", "收款账户" };

		TableData = new Object[volist.size()][8];
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
			TableData[c][6] = ((Double) tempInf.getAmount()).toString();
			TableData[c][7] = tempInf.getAccount();

		}

	}

	private ReceiptVO modifyPayment(int c, String Id) {
		PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((Id));
		PaymentInformation tempInf = tempvo.getPaymentInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setPositionId((String) table.getValueAt(c, 3));
		tempInf.setTime((String) table.getValueAt(c, 4));
		tempInf.setInDeliverId((String) table.getValueAt(c, 5));
		tempInf.setAmount(Double.parseDouble((String) table.getValueAt(c, 6)));
		tempInf.setAccount((String) table.getValueAt(c, 7));

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
			TableData[c][12] = ((Double) tempInf.getCostForAll()).toString();
			TableData[c][13] = ((Integer) tempInf.getPredictTime()).toString();

		}
	}

	private ReceiptVO modifyDelReip(int c, String Id) {

		SendSheetVO tempvo = (SendSheetVO) receipt.getSingle((Id));

		SendInformation tempInf = tempvo.getSendInformation();
		ToAndFromInformation fromPerson = tempInf.getFromPerson();
		ToAndFromInformation toPerson = tempInf.getToPerson();
		GoodInformation good = tempInf.getGood();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setCostForAll(Double.parseDouble((String) table.getValueAt(c, 12)));
		// tempInf.setCostForAll((Double)table.getValueAt(c, 12));
		tempInf.setPredictTime(Integer.parseInt((String) table.getValueAt(c, 13)));
		// tempInf.setPredictTime((int)table.getValueAt(c, 13));
		good.setTotal((String) table.getValueAt(c, 9));
		good.setWeight((String) table.getValueAt(c, 10));
		good.setName((String) table.getValueAt(c, 11));
		fromPerson.setName((String) table.getValueAt(c, 3));
		fromPerson.setAddress((String) table.getValueAt(c, 4));
		fromPerson.setCellphone((String) table.getValueAt(c, 5));
		toPerson.setName((String) table.getValueAt(c, 6));
		toPerson.setAddress((String) table.getValueAt(c, 7));
		toPerson.setCellphone((String) table.getValueAt(c, 8));

		tempInf.setFromPerson(fromPerson);
		tempInf.setToPerson(toPerson);
		tempvo.setSendInformation(tempInf);

		return tempvo;
	}

	// 营业厅装车单
	private void iniPosLoad() {
		// TODO Auto-generated method stub

		title = new String[] { "填写日期", "表单号", "填表人", "装车时间", "装车机构", "汽运编号", "目的地", "车辆代号", "监装员", "装运员", "运费" };

		TableData = new Object[volist.size()][11];
		for (int c = 0; c < volist.size(); c++) {
			String tempId = volist.get(c).getId();
			OfficeLoadSheetVO tempvo = (OfficeLoadSheetVO) receipt.getSingle((tempId));

			LoadInformation tempInf = tempvo.getOfficeLoadInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			TableData[c][0] = temp;
			TableData[c][1] = tempvo.getId();
			TableData[c][2] = tempvo.getMakePerson();
			TableData[c][3] = tempInf.getTime();
			TableData[c][4] = tempInf.getAgencyId();
			TableData[c][5] = tempInf.getTransportId();
			TableData[c][6] = tempInf.getDestinationId();
			TableData[c][7] = tempInf.getCarId();
			TableData[c][8] = tempInf.getOfficerId();
			TableData[c][9] = tempInf.getDriverId();
			TableData[c][10] = ((Double) tempInf.getFare()).toString();

		}
	}

	private ReceiptVO modifyPosLoad(int c, String Id) {
		OfficeLoadSheetVO tempvo = (OfficeLoadSheetVO) receipt.getSingle((Id));
		LoadInformation tempInf = tempvo.getOfficeLoadInformation();
		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setAgencyId((String) table.getValueAt(c, 4));
		tempInf.setTransportId((String) table.getValueAt(c, 5));
		tempInf.setDestinationId((String) table.getValueAt(c, 6));
		tempInf.setCarId((String) table.getValueAt(c, 7));
		tempInf.setOfficerId((String) table.getValueAt(c, 8));
		tempInf.setDriverId((String) table.getValueAt(c, 9));
		tempInf.setFare(Double.parseDouble((String) table.getValueAt(c, 10)));

		tempvo.setOfficeLoadInformation(tempInf);
		return tempvo;

	}

	// 营业厅收件单
	private void iniPosReseive() {
		// TODO Auto-generated method stub
		title = new String[] { "填写日期", "表单号", "填表人", "收件时间", "出发地" };

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
		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setTransitSheetId((String) table.getValueAt(c, 4));
		tempInf.setDeparture((String) table.getValueAt(c, 5));

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

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setOutDeliverId((String) table.getValueAt(c, 4));

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
		TransitCarInformation tempInf = (TransitCarInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setCarTransitId((String) table.getValueAt(c, 4));
		tempInf.setDeparture((String) table.getValueAt(c, 5));
		tempInf.setDestination((String) table.getValueAt(c, 6));
		tempInf.setTransiterId((String) table.getValueAt(c, 7));
		tempInf.setDriverId((String) table.getValueAt(c, 8));

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
		TransitTrainInformation tempInf = (TransitTrainInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setTrainTransitId((String) table.getValueAt(c, 4));
		tempInf.setDeparture((String) table.getValueAt(c, 5));
		tempInf.setDestination((String) table.getValueAt(c, 6));
		tempInf.setTransiterId((String) table.getValueAt(c, 7));
		tempInf.setCarriageId((String) table.getValueAt(c, 8));

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
		TransitFlightInformation tempInf = (TransitFlightInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setFlightTransitId((String) table.getValueAt(c, 4));
		tempInf.setDeparture((String) table.getValueAt(c, 5));
		tempInf.setDestination((String) table.getValueAt(c, 6));
		tempInf.setTransiterId((String) table.getValueAt(c, 7));
		tempInf.setShelfId((String) table.getValueAt(c, 8));

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
		ArriveInformation tempInf = (ArriveInformation) tempvo.getTransitArriveInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setTransitSheetId((String) table.getValueAt(c, 4));
		tempInf.setDeparture((String) table.getValueAt(c, 5));

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
			TableData[c][9] = ((Double) tempInf.getFare()).toString();
		}

	}

	private ReceiptVO modifyTransLoad(int c, String Id) {
		TransitLoadSheetVO tempvo = (TransitLoadSheetVO) receipt.getSingle((Id));
		LoadInformation tempInf = tempvo.getTransitLoadInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setTransportId((String) table.getValueAt(c, 4));
		tempInf.setDestinationId((String) table.getValueAt(c, 5));
		tempInf.setCarId((String) table.getValueAt(c, 6));
		tempInf.setOfficerId((String) table.getValueAt(c, 7));
		tempInf.setDriverId((String) table.getValueAt(c, 8));
		tempInf.setFare(Double.parseDouble((String) table.getValueAt(c, 9)));

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
			TableData[c][5] = ((Integer) tempInf.getBlock()).toString();
			TableData[c][6] = ((Integer) tempInf.getRow()).toString();
			TableData[c][7] = ((Integer) tempInf.getShelf()).toString();
			TableData[c][8] = ((Integer) tempInf.getPositon()).toString();

		}
	}

	private ReceiptVO modifyInvenIn(int c, String Id) {
		InventoryInSheetVO tempvo = (InventoryInSheetVO) receipt.getSingle((Id));
		InventoryInInformation tempInf = tempvo.getInventoryInInformation();

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setDestination((String) table.getValueAt(c, 4));
		tempInf.setBlock(Integer.parseInt((String) table.getValueAt(c, 5)));
		tempInf.setRow(Integer.parseInt((String) table.getValueAt(c, 6)));
		tempInf.setShelf(Integer.parseInt((String) table.getValueAt(c, 7)));
		tempInf.setPositon(Integer.parseInt((String) table.getValueAt(c, 8)));

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

		String temp = (String) table.getValueAt(c, 0);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1));
		tempvo.setMakePerson((String) table.getValueAt(c, 2));
		tempInf.setTime((String) table.getValueAt(c, 3));
		tempInf.setDestination((String) table.getValueAt(c, 4));
		tempInf.setTransitId((String) table.getValueAt(c, 5));
		// tempInf.setTransportType((String) TableData[c][6]);

		tempvo.setInventoryOutInformation(tempInf);
		return tempvo;

	}

	// 依据类型对表单信息进行更新
	private ReceiptVO modifyVO(int count, String id) {
		// TODO Auto-generated method stub
		switch (type.getSelectedItem().toString()) {
		case "收款单":
			return modifyPayment(count, id);
		case "付款单":
			return modifyOut(count, id);
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
