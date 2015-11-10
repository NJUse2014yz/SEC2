package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUiTest;

/**
 * 
 * @author YU Fan
 *
 */
public class ClientControler{
	JPanel userMainPanel=new UserUiTest(this);
	JPanel deliverMainPanel=new DeliverMainUiTest(this);
	
	MainUi mainFrame=new MainUi(userMainPanel);

	public void showFrame(JPanel showPanel)
	{
		mainFrame.showFrame();
	}
	public void changePanel(int n)
	{
		switch(n)
		{
		case 1:
			mainFrame.nextPanle(deliverMainPanel);
			break;
		case 2:
			mainFrame.nextPanle(userMainPanel);
			break;
		}
	}
	public static void main(String[] args)
	{
		ClientControler control=new ClientControler();
		control.showFrame(control.userMainPanel);
	}
}
