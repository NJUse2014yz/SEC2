package nju.sec.yz.ExpressSystem.presentation.userui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ACCOUNT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class UserUiTest extends JPanel{
	ClientControler controler;
	JButton testDeliver;
	JButton testAccount;
	public UserUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.testDeliver=new JButton("to deliver's panel");
		this.testDeliver.setBounds(50,50,200,50);
		this.testDeliver.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.DELIVER,this.controler));

		this.testAccount=new JButton("to account's panel");
		this.testAccount.setBounds(50,100,200,50);
		this.testAccount.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.ACCOUNTER,this.controler));

		
		
		add(testDeliver);
		setSize(493,560);
		setVisible(true); 
	}
}
