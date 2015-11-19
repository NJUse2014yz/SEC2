package nju.sec.yz.ExpressSystem.presentation.deliverui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverSwitchPanelListener;

public class DeliverMainUiTest extends JPanel{
	private ClientControler mainControler;
	private DeliverControler controler;
	private JButton test;
	public DeliverMainUiTest(ClientControler mainControler)
	{
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.setLayout(null);
		this.test=new JButton("to fill the order");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new DeliverSwitchPanelListener(DELIVER_CONTROL.ORDER_IN,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
