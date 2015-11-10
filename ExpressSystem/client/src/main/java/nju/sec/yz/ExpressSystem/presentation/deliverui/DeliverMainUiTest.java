package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainUi;
import nju.sec.yz.ExpressSystem.presentation.controlerui.SwitchPanelListener;

public class DeliverMainUiTest extends JPanel{
	ClientControler controler;
	JButton test;
	public DeliverMainUiTest(ClientControler controler)
	{
		super();
		this.setLayout(null);
		this.controler=controler;
		this.test=new JButton("test deliver");
		test.setBounds(50,50,100,50);
		test.addMouseListener(new SwitchPanelListener(2,this.controler));
		add(test);
		setSize(493,560);
		setVisible(true);
	}
}
