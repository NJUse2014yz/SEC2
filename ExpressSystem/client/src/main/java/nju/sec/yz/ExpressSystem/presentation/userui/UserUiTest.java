package nju.sec.yz.ExpressSystem.presentation.userui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class UserUiTest extends JPanel{
	ClientControler controler;
	JButton test;
	public UserUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.test=new JButton("to deliver's panel");
		test.setBounds(50,50,200,50);
		test.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.DELIVER,this.controler));

		add(test);
		setSize(493,560);
		setVisible(true); 
	}
}
