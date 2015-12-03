package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.LogController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerLogCheck extends JPanel{

	private LogBlService log=new LogController();
	private ClientControler maincontroler;
private  ManagerButtonComponent mbc;

private DateChooser date;
	
	public ManagerLogCheck(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();	
		iniManagerLogCheck();
	}
	

	private void iniManagerLogCheck() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		date=new DateChooser(this,259,63);
		
	}
}
