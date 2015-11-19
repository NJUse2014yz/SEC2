package nju.sec.yz.ExpressSystem.presentation.controlerui;

import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.accountui.AccountMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUi;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderSearchUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionMainUiTest;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUiTest;
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
	JPanel inventoryMainPanel=new InventoryMainUiTest(this);
	JPanel accountMainPanel=new AccountMainUiTest(this);
	JPanel managerMainPanel=new ManagerMainUiTest(this);
	JPanel positionMainPanel=new PositionMainUiTest(this);
	JPanel transitMainPanel=new TransitMainUiTest(this);
	JPanel adminstraterMainPanel=new AdminstraterMainUiTest(this);
	
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
