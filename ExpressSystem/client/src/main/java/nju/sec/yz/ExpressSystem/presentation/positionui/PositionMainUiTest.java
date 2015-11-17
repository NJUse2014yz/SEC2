package nju.sec.yz.ExpressSystem.presentation.positionui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionSwitchPanelListener;

public class PositionMainUiTest extends JPanel{
	ClientControler mainControler;
	PositionControler controler;
	JButton test;
	public PositionMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		this.controler=mainControler.positionControler;
		this.test=new JButton("test position");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new PositionSwitchPanelListener(POSITION_CONTROL.ARRIVE,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
