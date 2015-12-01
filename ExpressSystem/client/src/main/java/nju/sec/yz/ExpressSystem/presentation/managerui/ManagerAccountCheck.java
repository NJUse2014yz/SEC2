package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.AccountController;
import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerAccountCheck extends JPanel{

	//账户信息
	private AccountBlService account=new AccountController();
	//经营情况表和成本收益表
	private FinanceBlSevice finance=new FinanceController();
	
	private ClientControler maincontroler;
	private  ManagerButtonComponent mbc;
	
	public ManagerAccountCheck(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		iniManagerAccountCheck();
	}

	private void iniManagerAccountCheck() {
		mbc.changePanel(this);
		mbc.change();
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}
}
