package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.INVENTORY_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.InventoryControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.InventroySwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class InventoryMainUiTest extends JPanel{
	ClientControler mainControler;
	InventoryControler controler;
	JButton test;
	public InventoryMainUiTest(ClientControler mainControler)
	{
		super();
		this.setLayout(null);
		this.mainControler=mainControler;
		this.controler=mainControler.inventoryControler;
		this.test=new JButton("to …… panel");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new InventroySwitchPanelListener(INVENTORY_CONTROL.IN_SUCCESS,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
