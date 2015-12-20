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
	JButton testPosition;
	JButton testInventory;
	JButton testManager;
	JButton testTransit;
	JButton testAdminstrater;
	
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

		this.testPosition=new JButton("to position's panel");
		this.testPosition.setBounds(50,150,200,50);
		this.testPosition.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.POSITION,this.controler));
		
		this.testInventory=new JButton("to inventory's panel");
		this.testInventory.setBounds(50,200,200,50);
		this.testInventory.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.INVENTORY,this.controler));
		
		this.testManager=new JButton("to manager's panel");
		this.testManager.setBounds(50,250,200,50);
		this.testManager.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.MANAGER,this.controler));
		
		this.testTransit=new JButton("to transit's panel");
		this.testTransit.setBounds(50,300,200,50);
		this.testTransit.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.TRANSITER,this.controler));
		
		this.testAdminstrater=new JButton("to adminstrater's panel");
		this.testAdminstrater.setBounds(50, 350, 200, 50);
		this.testAdminstrater.addMouseListener(new MainSwitchPanelListener(MAIN_CONTROL.ADMINSTRATER,this.controler));
		
		add(this.testDeliver);
		add(this.testAccount);
		add(this.testPosition);
		add(this.testInventory);
		add(this.testManager);
		add(this.testTransit);
		add(this.testAdminstrater);
		
		setSize(493,560);
		setVisible(true); 
	}
}
