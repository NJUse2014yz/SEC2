package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerAgencyModify extends JPanel{
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	public ManagerAgencyModify(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerAgencyModify();
	}

	private void iniManagerAgencyModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}

}
