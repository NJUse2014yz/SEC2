package nju.sec.yz.ExpressSystem.presentation.transitui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class TransitMainUiTest extends JPanel{
	ClientControler controler;
	JButton test;
	public TransitMainUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.test=new JButton("test transit");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new MainSwitchPanelListener(2,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}

}
