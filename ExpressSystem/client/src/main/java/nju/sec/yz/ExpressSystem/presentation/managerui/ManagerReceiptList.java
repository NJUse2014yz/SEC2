package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

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
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
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

	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();

	private newJBut pass;
	private newJBut passAll;
	private newJBut modify;

	private ArrayList<ReceiptVO> volist;

	private newJLabel warning = new newJLabel();

	public ManagerReceiptList(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
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

		table=new newTable(data,name,this,false);
		table.setBounds(134, 100, 333, 157);
		table.stopAutoRewidth();
		table.join();

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
			}
		});

		// ImageIcon passIcon = new
		// ImageIcon("graphic/manager/button/pass.png");
		pass = new newJBut("通过");
		pass.setBounds(467 - 75, 262, 75, 24);
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
		passAll.setMargin(new java.awt.Insets(0,0,0,0));
		passAll.setBounds(467 - 160, 262, 75, 24);
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
		modify.setBounds(467 - 245, 262, 75, 24);
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
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("付款时间");
		name.add("收款金额");
		name.add("收款人");
		name.add("付款账号");
		name.add("条目");
		name.add("账户");

		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			OutVO tempvo = (OutVO) receipt.getSingle((tempId));
			OutInformation tempInf = tempvo.getOutInformation();
			// PaymentInformation tempInf = tempvo.getPaymentInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getDate());
			vector.add(new DecimalFormat(".00").format(tempInf.getNum()));
			vector.add(tempInf.getPerson());
			vector.add(tempInf.getAccount());
			vector.add(tempInf.getReason());
			vector.add(tempInf.getComments());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyOut(int c, String Id) {
		OutVO tempvo = (OutVO) receipt.getSingle((Id));
		OutInformation tempInf = tempvo.getOutInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setDate((String) table.getValueAt(c, 3,false));
		tempInf.setNum(Double.parseDouble((String) table.getValueAt(c, 4,false)));
		tempInf.setPerson((String) table.getValueAt(c, 5,false));
		tempInf.setAccount((String) table.getValueAt(c, 6,false));
		tempInf.setReason((String) table.getValueAt(c, 7,false));
		tempInf.setComments((String) table.getValueAt(c, 8,false));

		return tempvo;
	}

	// 收款单
	private void iniPayment() {
		// TODO Auto-generated method stub
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("营业厅编号");
		name.add("收款时间");
		name.add("收款人");
		name.add("收款金额");
		name.add("收款账户");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((tempId));

			PaymentInformation tempInf = tempvo.getPaymentInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getPositionId());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getInDeliverId());
			vector.add(new DecimalFormat(".00").format(tempInf.getAmount()).toString());
			vector.add(tempInf.getAccount());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyPayment(int c, String Id) {
		PaymentSheetVO tempvo = (PaymentSheetVO) receipt.getSingle((Id));
		PaymentInformation tempInf = tempvo.getPaymentInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setPositionId((String) table.getValueAt(c, 3,false));
		tempInf.setTime((String) table.getValueAt(c, 4,false));
		tempInf.setInDeliverId((String) table.getValueAt(c, 5,false));
		tempInf.setAmount(Double.parseDouble((String) table.getValueAt(c, 6,false)));
		tempInf.setAccount((String) table.getValueAt(c, 7,false));

		tempvo.setPaymentInformation(tempInf);
		return tempvo;
	}

	// 寄件单
	private void iniDelReip() {
		// TODO Auto-generated method stub
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("寄件人姓名");
		name.add("寄件人地址");
		name.add("寄件人手机");
		name.add("收件人姓名");
		name.add("收件人地址");
		name.add("收件人手机");
		name.add("件数");
		name.add("重量");
		name.add("品名");
		name.add("快递费");
		name.add("预计送达时间");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			SendSheetVO tempvo = (SendSheetVO) receipt.getSingle((tempId));

			SendInformation tempInf = tempvo.getSendInformation();
			ToAndFromInformation fromPerson = tempInf.getFromPerson();
			ToAndFromInformation toPerson = tempInf.getToPerson();
			GoodInformation good = tempInf.getGood();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(fromPerson.getName());
			vector.add(fromPerson.getAddress());
			vector.add(fromPerson.getCellphone());
			vector.add(toPerson.getName());
			vector.add(toPerson.getAddress());
			vector.add(toPerson.getCellphone());
			vector.add(good.getTotal());
			vector.add(good.getWeight());
			vector.add(good.getName());
			vector.add(new DecimalFormat(".00").format(tempInf.getCostForAll()).toString());
			vector.add(((Integer) tempInf.getPredictTime()).toString());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyDelReip(int c, String Id) {

		SendSheetVO tempvo = (SendSheetVO) receipt.getSingle((Id));

		SendInformation tempInf = tempvo.getSendInformation();
		ToAndFromInformation fromPerson = tempInf.getFromPerson();
		ToAndFromInformation toPerson = tempInf.getToPerson();
		GoodInformation good = tempInf.getGood();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setCostForAll(Double.parseDouble((String) table.getValueAt(c, 12,false)));
		// tempInf.setCostForAll((Double)table.getValueAt(c, 12));
		tempInf.setPredictTime(Integer.parseInt((String) table.getValueAt(c, 13,false)));
		// tempInf.setPredictTime((int)table.getValueAt(c, 13));
		good.setTotal((String) table.getValueAt(c, 9,false));
		good.setWeight((String) table.getValueAt(c, 10,false));
		good.setName((String) table.getValueAt(c, 11,false));
		fromPerson.setName((String) table.getValueAt(c, 3,false));
		fromPerson.setAddress((String) table.getValueAt(c, 4,false));
		fromPerson.setCellphone((String) table.getValueAt(c, 5,false));
		toPerson.setName((String) table.getValueAt(c, 6,false));
		toPerson.setAddress((String) table.getValueAt(c, 7,false));
		toPerson.setCellphone((String) table.getValueAt(c, 8,false));

		tempInf.setFromPerson(fromPerson);
		tempInf.setToPerson(toPerson);
		tempvo.setSendInformation(tempInf);

		return tempvo;
	}

	// 营业厅装车单
	private void iniPosLoad() {
		// TODO Auto-generated method stub
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("装车时间");
		name.add("装车机构");
		name.add("汽运编号");
		name.add("目的地");
		name.add("车辆代号");
		name.add("监装员");
		name.add("装运员");
		name.add("运费");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			OfficeLoadSheetVO tempvo = (OfficeLoadSheetVO) receipt.getSingle((tempId));

			LoadInformation tempInf = tempvo.getOfficeLoadInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getAgencyId());
			vector.add(tempInf.getTransportId());
			vector.add(tempInf.getDestinationId());
			vector.add(tempInf.getCarId());
			vector.add(tempInf.getOfficerId());
			vector.add(tempInf.getDriverId());
			vector.add(new DecimalFormat(".00").format(tempInf.getFare()).toString());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyPosLoad(int c, String Id) {
		OfficeLoadSheetVO tempvo = (OfficeLoadSheetVO) receipt.getSingle((Id));
		LoadInformation tempInf = tempvo.getOfficeLoadInformation();
		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setAgencyId((String) table.getValueAt(c, 4,false));
		tempInf.setTransportId((String) table.getValueAt(c, 5,false));
		tempInf.setDestinationId((String) table.getValueAt(c, 6,false));
		tempInf.setCarId((String) table.getValueAt(c, 7,false));
		tempInf.setOfficerId((String) table.getValueAt(c, 8,false));
		tempInf.setDriverId((String) table.getValueAt(c, 9,false));
		tempInf.setFare(Double.parseDouble((String) table.getValueAt(c, 10,false)));

		tempvo.setOfficeLoadInformation(tempInf);
		return tempvo;

	}

	// 营业厅收件单
	private void iniPosReseive() {
		// TODO Auto-generated method stub
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("收件时间");
		name.add("中转单编号");
		name.add("出发地");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			OfficeArriveSheetVO tempvo = (OfficeArriveSheetVO) receipt.getSingle((tempId));

			ArriveInformation tempInf = tempvo.getOfficeArrive();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getTransitSheetId());
			vector.add(tempInf.getDeparture());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyPosReseive(int c, String Id) {
		OfficeArriveSheetVO tempvo = (OfficeArriveSheetVO) receipt.getSingle((Id));
		ArriveInformation tempInf = tempvo.getOfficeArrive();
		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setTransitSheetId((String) table.getValueAt(c, 4,false));
		tempInf.setDeparture((String) table.getValueAt(c, 5,false));

		tempvo.setOfficeArrive(tempInf);
		return tempvo;

	}

	// 营业厅派送单
	private void iniPosSend() {
		// TODO Auto-generated method stub
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("派件时间");
		name.add("派件人");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((tempId));

			DeliveryInformation tempInf = tempvo.getDeliveryInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getOutDeliverId());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyPosSend(int c, String Id) {
		DeliverySheetVO tempvo = (DeliverySheetVO) receipt.getSingle((Id));
		DeliveryInformation tempInf = tempvo.getDeliveryInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setOutDeliverId((String) table.getValueAt(c, 4,false));

		tempvo.setDeliveryInformation(tempInf);
		return tempvo;

	}

	// 汽车中转单
	private void iniTransCar() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("中转时间");
		name.add("中转中心汽运编号");
		name.add("出发地");
		name.add("到达地");
		name.add("监装员");
		name.add("押运员");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitCarInformation tempInf = (TransitCarInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getCarTransitId());
			vector.add(tempInf.getDeparture());
			vector.add(tempInf.getDestination());
			vector.add(tempInf.getTransiterId());
			vector.add(tempInf.getDriverId());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyTransCar(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitCarInformation tempInf = (TransitCarInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setCarTransitId((String) table.getValueAt(c, 4,false));
		tempInf.setDeparture((String) table.getValueAt(c, 5,false));
		tempInf.setDestination((String) table.getValueAt(c, 6,false));
		tempInf.setTransiterId((String) table.getValueAt(c, 7,false));
		tempInf.setDriverId((String) table.getValueAt(c, 8,false));

		tempvo.setTransitInformation(tempInf);
		return tempvo;

	}

	// 火车中转单
	private void iniTransTrain() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("中转时间");
		name.add("中转中心汽运编号");
		name.add("出发地");
		name.add("到达地");
		name.add("车厢号");
		name.add("押运员");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitTrainInformation tempInf = (TransitTrainInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getTrainTransitId());
			vector.add(tempInf.getDeparture());
			vector.add(tempInf.getDestination());
			vector.add(tempInf.getTransiterId());
			vector.add(tempInf.getCarriageId());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyTransTrain(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitTrainInformation tempInf = (TransitTrainInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setTrainTransitId((String) table.getValueAt(c, 4,false));
		tempInf.setDeparture((String) table.getValueAt(c, 5,false));
		tempInf.setDestination((String) table.getValueAt(c, 6,false));
		tempInf.setTransiterId((String) table.getValueAt(c, 7,false));
		tempInf.setCarriageId((String) table.getValueAt(c, 8,false));

		tempvo.setTransitInformation(tempInf);
		return tempvo;

	}

	// 飞机中转单
	private void iniTranFlight() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("中转时间");
		name.add("中转中心汽运编号");
		name.add("出发地");
		name.add("到达地");
		name.add("货柜号");
		name.add("押运员");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((tempId));

			TransitFlightInformation tempInf = (TransitFlightInformation) tempvo.getTransitInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getFlightTransitId());
			vector.add(tempInf.getDeparture());
			vector.add(tempInf.getDestination());
			vector.add(tempInf.getTransiterId());
			vector.add(tempInf.getShelfId());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyTranFlight(int c, String Id) {
		TransitSheetVO tempvo = (TransitSheetVO) receipt.getSingle((Id));
		TransitFlightInformation tempInf = (TransitFlightInformation) tempvo.getTransitInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setFlightTransitId((String) table.getValueAt(c, 4,false));
		tempInf.setDeparture((String) table.getValueAt(c, 5,false));
		tempInf.setDestination((String) table.getValueAt(c, 6,false));
		tempInf.setTransiterId((String) table.getValueAt(c, 7,false));
		tempInf.setShelfId((String) table.getValueAt(c, 8,false));

		tempvo.setTransitInformation(tempInf);
		return tempvo;

	}

	// 中转中心接收单
	private void iniTransReceive() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("接收时间");
		name.add("中转单编号");
		name.add("出发地");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		System.out.println(volist.size());
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			TransitArriveSheetVO tempvo = (TransitArriveSheetVO) receipt.getSingle((tempId));
			ArriveInformation tempInf = (ArriveInformation) tempvo.getTransitArriveInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getTransitSheetId());
			vector.add(tempInf.getDeparture());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();

	}

	private ReceiptVO modifyTransReceive(int c, String Id) {
		TransitArriveSheetVO tempvo = (TransitArriveSheetVO) receipt.getSingle((Id));
		ArriveInformation tempInf = (ArriveInformation) tempvo.getTransitArriveInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setTransitSheetId((String) table.getValueAt(c, 4,false));
		tempInf.setDeparture((String) table.getValueAt(c, 5,false));

		tempvo.setTransitArriveInformation(tempInf);
		return tempvo;

	}

	// 中转中心装车单
	private void iniTransLoad() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("装车时间");
		name.add("装车单编号");
		name.add("目的地");
		name.add("车辆代号");
		name.add("监装员");
		name.add("押运员");
		name.add("运费");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			TransitLoadSheetVO tempvo = (TransitLoadSheetVO) receipt.getSingle((tempId));
			LoadInformation tempInf = (LoadInformation) tempvo.getTransitLoadInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getTransportId());
			vector.add(tempInf.getDestinationId());
			vector.add(tempInf.getCarId());
			vector.add(tempInf.getOfficerId());
			vector.add(tempInf.getDriverId());
			vector.add(new DecimalFormat(".00").format(tempInf.getFare()).toString());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyTransLoad(int c, String Id) {
		TransitLoadSheetVO tempvo = (TransitLoadSheetVO) receipt.getSingle((Id));
		LoadInformation tempInf = tempvo.getTransitLoadInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setTransportId((String) table.getValueAt(c, 4,false));
		tempInf.setDestinationId((String) table.getValueAt(c, 5,false));
		tempInf.setCarId((String) table.getValueAt(c, 6,false));
		tempInf.setOfficerId((String) table.getValueAt(c, 7,false));
		tempInf.setDriverId((String) table.getValueAt(c, 8,false));
		tempInf.setFare(Double.parseDouble((String) table.getValueAt(c, 9,false)));

		tempvo.setTransitLoadInformation(tempInf);
		return tempvo;

	}

	// 入库单
	private void iniInvenIn() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("入库时间");
		name.add("目的地");
		name.add("区号");
		name.add("排号");
		name.add("架号");
		name.add("位号");
		name.add("中转中心");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			InventoryInSheetVO tempvo = (InventoryInSheetVO) receipt.getSingle((tempId));
			InventoryInInformation tempInf = (InventoryInInformation) tempvo.getInventoryInInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getDestination());
			vector.add(((Integer) tempInf.getBlock()).toString());
			vector.add(((Integer) tempInf.getRow()).toString());
			vector.add(((Integer) tempInf.getShelf()).toString());
			vector.add(((Integer) tempInf.getPositon()).toString());
			vector.add(tempInf.getTransit());
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}

	private ReceiptVO modifyInvenIn(int c, String Id) {
		InventoryInSheetVO tempvo = (InventoryInSheetVO) receipt.getSingle((Id));
		InventoryInInformation tempInf = tempvo.getInventoryInInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setDestination((String) table.getValueAt(c, 4,false));
		tempInf.setBlock(Integer.parseInt((String) table.getValueAt(c, 5,false)));
		tempInf.setRow(Integer.parseInt((String) table.getValueAt(c, 6,false)));
		tempInf.setShelf(Integer.parseInt((String) table.getValueAt(c, 7,false)));
		tempInf.setPositon(Integer.parseInt((String) table.getValueAt(c, 8,false)));
		tempInf.setTransit((String) table.getValueAt(c, 9,false));
		
		tempvo.setInventoryInInformation(tempInf);
		return tempvo;
	}

	// 出库单
	private void iniInvenOut() {
		name.removeAllElements();
		data.removeAllElements();
		name.add("填写日期");
		name.add("表单号");
		name.add("填表人");
		name.add("入库时间");
		name.add("目的地");
		name.add("区号");
		name.add("排号");
		name.add("架号");
		name.add("位号");
//		table=new newTable(data,name,this,false);
//		table.setBounds(134, 100, 333, 157);
//		table.stopAutoRewidth();
//		table.join();
		for (int c = 0; c < volist.size(); c++) {
			Vector<String> vector=new Vector<String>();
			String tempId = volist.get(c).getId();
			InventoryOutSheetVO tempvo = (InventoryOutSheetVO) receipt.getSingle((tempId));
			InventoryOutInformation tempInf = (InventoryOutInformation) tempvo.getInventoryOutInformation();
			String temp = tempvo.getMakeTime();
			temp = temp.substring(0, 4) + "/" + temp.substring(4, 6) + "/" + temp.substring(6, 8);
			vector.add(temp);
			vector.add(tempvo.getId());
			vector.add(tempvo.getMakePerson());
			vector.add(tempInf.getTime());
			vector.add(tempInf.getDestination());
			vector.add(tempInf.getTransitId());
			vector.add(getType(tempInf.getTransportType()));
			data.add(vector);
		}
//		table.resetData();
		table.getModel().setDataVector(data, name);
		repaint();
	}
	public String getType(TransportType str){
		switch(str){
		case CAR:
			return "汽车";
		case PLANE:
			return "飞机";
		case TRAIN:	
			return "火车";
		default:
			return "";
		}
	}

	private ReceiptVO modifyInvenOut(int c, String Id) {
		InventoryOutSheetVO tempvo = (InventoryOutSheetVO) receipt.getSingle((Id));
		InventoryOutInformation tempInf = tempvo.getInventoryOutInformation();

		String temp = (String) table.getValueAt(c, 0,false);
		temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10);
		tempvo.setMakeTime(temp);
		tempvo.setId((String) table.getValueAt(c, 1,false));
		tempvo.setMakePerson((String) table.getValueAt(c, 2,false));
		tempInf.setTime((String) table.getValueAt(c, 3,false));
		tempInf.setDestination((String) table.getValueAt(c, 4,false));
		tempInf.setTransitId((String) table.getValueAt(c, 5,false));
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
