package nju.sec.yz.ExpressSystem.presentation.userui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.SwitchPanelListener;

public class UserUiTest extends JPanel{
	ClientControler controler;
	JButton test;
	public UserUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.test=new JButton("test user");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new SwitchPanelListener(1,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true); 
	}
}
