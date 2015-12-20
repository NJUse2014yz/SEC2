package nju.sec.yz.ExpressSystem.presentation.controlerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.accountui.AccountMainUi;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUi;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderSearchUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUi;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionMainUi;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUi;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterMainUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.userui.UserOrderSearchUi;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUi;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUiTest;

/**
 * 
 * @author YU Fan
 *
 */
public class ClientControler{
	public DeliverControler deliverControler=new DeliverControler(this);
	public AdminstraterControler adminstraterControler=new AdminstraterControler(this);
	public AccountControler accountControler=new AccountControler(this);
	public InventoryControler inventoryControler=new InventoryControler(this);
	public ManagerControler managerControler=new ManagerControler(this);
	public TransitControler transitControler=new TransitControler(this);
	public PositionControler positionControler=new PositionControler(this);
	
//	JPanel userMainPanel=new UserUi(this);
//	JPanel deliverMainPanel=new DeliverMainUi(this);
//	JPanel inventoryMainPanel=new InventoryMainUi(this);
//	JPanel accountMainPanel=new AccountMainUi(this,accountControler.bc);
//	JPanel managerMainPanel=new ManagerMainUi(this,managerControler.mbc);
//	JPanel positionMainPanel=new PositionMainUi(this,positionControler.bc);
//	JPanel transitMainPanel=new TransitMainUi(this,transitControler.tbc);
//	JPanel adminstraterMainPanel=new AdminstraterMainUi(this,adminstraterControler.bc);
	
	public MainUi mainFrame=new MainUi(new UserUi(this));

	public void showFrame()
	{
		mainFrame.showFrame();
	}
	public void mainChangePanel(MAIN_CONTROL n)
	{
		switch(n)
		{
		case LOGIN:
			System.out.println("login now");
			mainFrame.nextPanel(new UserUi(this));
			break;
//		case DELIVERY_ENQUIRY:
//			mainFrame.nextPanel(new UserOrderSearchUi(this));
//			break;
		case DELIVER:
			mainFrame.nextPanel(new DeliverMainUi(this));
			break;
		case POSITION:
			mainFrame.nextPanel(new PositionMainUi(this,positionControler.bc));
			break;
		case TRANSITER:
			mainFrame.nextPanel(new TransitMainUi(this,transitControler.tbc));
			break;
		case INVENTORY:
			mainFrame.nextPanel(new InventoryMainUi(this));
			break;
		case SENIOR_ACCOUNTANCY:
			mainFrame.nextPanel(new AccountMainUi(this,accountControler.bc,"senior"));
			break;
		case JUNIOR_ACCOUNTANCY:
			mainFrame.nextPanel(new AccountMainUi(this,accountControler.bc,"junior"));
			break;
		case MANAGER:
			mainFrame.nextPanel(new ManagerMainUi(this,managerControler.mbc));
			break;
		case ADMINSTRATER:
			mainFrame.nextPanel(new AdminstraterMainUi(this,adminstraterControler.bc));
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
	public static void main(String[] args)
	{
		ClientControler control=new ClientControler();
		control.showFrame();
	}
}
