package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerReceiptModify  extends JPanel{
	
	private ReceiptBlService receipt=new ReceiptController();
	
	private ClientControler maincontroler;
private  ManagerButtonComponent mbc;
	
	public ManagerReceiptModify(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerReceiptModify();
	}

	private void iniManagerReceiptModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}

}