package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.accountui.AccountMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUiTest;

/**
 * 
 * @author YU Fan
 *
 */
public class ClientControler{
	JPanel userMainPanel=new UserUiTest(this);
	JPanel deliverMainPanel=new DeliverMainUiTest(this);
	JPanel inventoryMainPanel=new InventoryMainUiTest(this);
	JPanel accountMainPanel=new AccountMainUiTest(this);
	JPanel managerMainPanel=new ManagerMainUiTest(this);
	JPanel positionMainPanel=new PositionMainUiTest(this);
	JPanel transitMainPanel=new TransitMainUiTest(this);
	JPanel adminstraterMainPanel=new AdminstraterMainUiTest(this);
	
	MainUi mainFrame=new MainUi(userMainPanel);

	public void showFrame(JPanel showPanel)
	{
		mainFrame.showFrame();
	}
	public void mainChangePanel(CONTROL_CONTROL n)
	{
		switch(n)
		{
//		case LOGIN:
//			break;
		case DELIVERY_ENQUIRY:
			break;
		case DELIVER:
			mainFrame.nextPanel(deliverMainPanel);
			break;
		case POSITION:
			mainFrame.nextPanel(positionMainPanel);
			break;
		case TRANSITER:
			mainFrame.nextPanel(transitMainPanel);
			break;
		case INVENTORY:
			mainFrame.nextPanel(inventoryMainPanel);
			break;
		case ACCOUNTER:
			mainFrame.nextPanel(accountMainPanel);
			break;
		case MANAGER:
			mainFrame.nextPanel(managerMainPanel);
			break;
		case ADMINSTRATER:
			mainFrame.nextPanel(adminstraterMainPanel);
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
	public static void main(String[] args)
	{
		ClientControler control=new ClientControler();
		control.showFrame(control.userMainPanel);
	}
}