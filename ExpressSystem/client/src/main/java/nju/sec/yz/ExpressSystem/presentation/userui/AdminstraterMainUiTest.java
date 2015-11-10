package nju.sec.yz.ExpressSystem.presentation.userui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverControler;
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
		controler=mainControler.
		this.test=new JButton("test adminstrater");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new MainSwitchPanelListener(2,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
