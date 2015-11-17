package nju.sec.yz.ExpressSystem.presentation.transitui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.TRANSIT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.TransitControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.TransitSwitchPanelListener;

public class TransitMainUiTest extends JPanel{
	ClientControler mainControler;
	TransitControler controler;
	JButton test;
	public TransitMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		this.controler=mainControler.transitControler;
		this.test=new JButton("test transit");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new TransitSwitchPanelListener(TRANSIT_CONTROL.ARRIVE,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}

}
