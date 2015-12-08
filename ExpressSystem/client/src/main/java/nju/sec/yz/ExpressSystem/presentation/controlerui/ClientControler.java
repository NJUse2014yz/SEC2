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
	
	JPanel userMainPanel=new UserUi(this);
	JPanel deliverMainPanel=new DeliverMainUi(this);
	JPanel inventoryMainPanel=new InventoryMainUi(this);
	JPanel accountMainPanel=new AccountMainUi(this,accountControler.bc);
	JPanel managerMainPanel=new ManagerMainUi(this,managerControler.mbc);
	JPanel positionMainPanel=new PositionMainUi(this,positionControler.bc);
	JPanel transitMainPanel=new TransitMainUi(this,transitControler.tbc);
	JPanel adminstraterMainPanel=new AdminstraterMainUi(this,adminstraterControler.bc);
	
	public MainUi mainFrame=new MainUi(userMainPanel);

	public void showFrame()
	{
		mainFrame.showFrame();
	}
	public void mainChangePanel(MAIN_CONTROL n)
	{
		switch(n)
		{
		case LOGIN:
			mainFrame.nextPanel(userMainPanel);
			break;
		case DELIVERY_ENQUIRY:
			mainFrame.nextPanel(new DeliverOrderSearchUi(this));
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
		control.showFrame();
	}
}
