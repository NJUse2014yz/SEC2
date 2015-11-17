package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MANAGER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ManagerControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ManagerSwitchPanelListener;

public class ManagerMainUiTest extends JPanel{
	ClientControler mainControler;
	ManagerControler controler;
	JButton test;
	public ManagerMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		this.controler=mainControler.managerControler;
		this.test=new JButton("test manager");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new ManagerSwitchPanelListener(MANAGER_CONTROL.AGENCY_LIST,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
