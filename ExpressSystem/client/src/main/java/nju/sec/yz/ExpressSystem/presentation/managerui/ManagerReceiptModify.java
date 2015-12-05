package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.deliverui.OrderIn;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class ManagerReceiptModify extends JPanel {

	private ReceiptBlService receipt = new ReceiptController();

	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private ReceiptVO vo;
	private String type;

	public ManagerReceiptModify(ClientControler maincontroler, ManagerButtonComponent mbc, ReceiptVO vo, String type) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		this.vo = vo;
		this.type = type;
		mbc.changePanel(this);
		mbc.change();
		iniManagerReceiptModify();
		// setLayout(null);
		// setSize(490, 550);
		// setVisible(true);
		//
		// OrderIn orderIn=new OrderIn(this);
		System.out.println("GGGGGGGGGGGGGGG");
	}

	private void iniManagerReceiptModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		switch (type) {
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

	// 收款单
	private void iniCollection() {
		// TODO Auto-generated method stub

	}

	// 付款单
	private void iniPayment() {
		// TODO Auto-generated method stub

	}

	// 寄件单
	private void iniDelReip() {
		// TODO Auto-generated method stub
		OrderIn orderIn = new OrderIn(this);
		System.out.println("LLLLLLLLLLLLL");
	}

	// 营业厅装车单
	private void iniPosLoad() {
		// TODO Auto-generated method stub

	}

	// 营业厅收件单
	private void iniPosReseive() {
		// TODO Auto-generated method stub

	}

	// 营业厅派送单
	private void iniPosSend() {
		// TODO Auto-generated method stub

	}

	// 汽车中转单
	private void iniTransCar() {
		// TODO Auto-generated method stub

	}

	// 火车中转单
	private void iniTransTrain() {
		// TODO Auto-generated method stub

	}

	// 飞机中转单
	private void iniTranFlight() {
		// TODO Auto-generated method stub

	}

	// 中转中心接收单
	private void iniTransReceive() {
		// TODO Auto-generated method stub

	}

	// 中转中心装车单
	private void iniTransLoad() {
		// TODO Auto-generated method stub

	}

	// 入库单
	private void iniInvenIn() {
		// TODO Auto-generated method stub

	}

	// 出库单
	private void iniInvenOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g) {
		switch (type) {
		case "收款单":
			iniCollection();
			break;
		case "付款单":
			iniPayment();
			break;
		case "寄件单":
			Image img01 = new ImageIcon("graphic/deliver/background/background02.png").getImage();
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

//		g.drawImage(img01, 0, 0, 490, 550, this);
	}
}