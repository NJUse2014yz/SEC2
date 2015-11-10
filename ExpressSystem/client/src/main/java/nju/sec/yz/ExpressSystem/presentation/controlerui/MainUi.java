package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;

public class MainUi extends JFrame{
	private JPanel now;
	public final static int WIDTH=491;
	public final static int HEIGHT=551;

	public MainUi(JPanel panel)
	{
		super();
		now=panel;
	}
	public void nextPanle(JPanel next)
	{
		remove(now);
		now=next;
		this.add(now);
		this.repaint();
	}
	public void showFrame()//crazy problem
	{
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		this.setResizable(false);
		this.setUndecorated(true);
		this.setSize(493,560);
		this.setLocation(screenSize.width/2-493/2,screenSize.height/2-560/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new DeliverOrderInUi());
		this.setVisible(true);
	}
}
