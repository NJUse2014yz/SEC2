package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerSalaryObserve extends JPanel{
	private SalaryBlService manager=new ManagerController();
	private ClientControler maincontroler;
	
private  ManagerButtonComponent mbc;
	
	public ManagerSalaryObserve(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();	
		iniManagerSalaryObserve();
	}

	private void iniManagerSalaryObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}

}
