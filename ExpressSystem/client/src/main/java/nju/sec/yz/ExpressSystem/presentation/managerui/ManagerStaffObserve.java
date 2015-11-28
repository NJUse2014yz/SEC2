package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerStaffObserve extends JPanel{
	private StaffBlService manager=new ManagerController();
	private ClientControler maincontroler;
private  ManagerButtonComponent mbc;
	
	public ManagerStaffObserve(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerStaffObserve();
	}

	private void iniManagerStaffObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}

}