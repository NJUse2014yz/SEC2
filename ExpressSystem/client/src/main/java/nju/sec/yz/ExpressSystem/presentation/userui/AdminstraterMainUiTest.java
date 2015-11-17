package nju.sec.yz.ExpressSystem.presentation.userui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ADMINSTRATER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class AdminstraterMainUiTest extends JPanel{
	private ClientControler mainControler;
	private AdminstraterControler controler;
	JButton test;
	public AdminstraterMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		controler=mainControler.adminstraterControler;
		this.test=new JButton("test adminstrater");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new AdminstraterSwitchPanelListener(ADMINSTRATER_CONTROL.ADD_SUCCESS,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
