package nju.sec.yz.ExpressSystem.presentation.accountui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ACCOUNT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class AccountMainUiTest extends JPanel{
	ClientControler mainControler;
	AccountControler controler;
	JButton test;
	public AccountMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		this.controler=mainControler.accountControler;
		this.test=new JButton("test account");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new AccountSwitchPanelListener(ACCOUNT_CONTROL.ADD_ACCOUNT,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
