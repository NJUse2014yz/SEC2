package nju.sec.yz.ExpressSystem.presentation.managerui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class ManagerMainUiTest extends JPanel{
	ClientControler controler;
	JButton test;
	public ManagerMainUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.test=new JButton("test manager");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new MainSwitchPanelListener(2,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
