package nju.sec.yz.ExpressSystem.presentation.controlerui;

import javax.swing.JPanel;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountMainUi;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverMainUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUi;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionMainUi;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterMainUi;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUi;

/**
 * 主控制器，控制各用户的界面跳转
 * @author YU Fan
 *
 */
public class ClientControler {
	JPanel temp = new UserUi(this);

	public DeliverControler deliverControler = new DeliverControler(this);
	public AdminstraterControler adminstraterControler = new AdminstraterControler(this);
	public AccountControler accountControler = new AccountControler(this);
	public InventoryControler inventoryControler = new InventoryControler(this);
	public ManagerControler managerControler = new ManagerControler(this);
	public TransitControler transitControler = new TransitControler(this);
	public PositionControler positionControler = new PositionControler(this);

	// JPanel userMainPanel=new UserUi(this);
	// JPanel deliverMainPanel=new DeliverMainUi(this);
	// JPanel inventoryMainPanel=new InventoryMainUi(this);
	// JPanel accountMainPanel=new AccountMainUi(this,accountControler.bc);
	// JPanel managerMainPanel=new ManagerMainUi(this,managerControler.mbc);
	// JPanel positionMainPanel=new PositionMainUi(this,positionControler.bc);
	// JPanel transitMainPanel=new TransitMainUi(this,transitControler.tbc);
	// JPanel adminstraterMainPanel=new
	// AdminstraterMainUi(this,adminstraterControler.bc);

	public MainUi mainFrame = new MainUi(new UserUi(this));

	public void showFrame() {
		mainFrame.showFrame();
	}
	public void mainChangePanel(MainControl n)
	{
		switch(n)
		{
		case LOGIN:
			temp = new UserUi(this);
			break;
		case DELIVER:
			temp = new DeliverMainUi(this);
			break;
		case POSITION:
			temp = new PositionMainUi(this, positionControler.bc);
			break;
		case TRANSITER:
			temp = new TransitMainUi(this, transitControler.tbc);
			break;
		case INVENTORY:
			temp = new InventoryMainUi(this);
			break;
		case SENIOR_ACCOUNTANCY:
			temp = new AccountMainUi(this, accountControler.bc, "senior");
			break;
		case JUNIOR_ACCOUNTANCY:
			temp = new AccountMainUi(this, accountControler.bc, "junior");
			break;
		case MANAGER:
			temp = new ManagerMainUi(this, managerControler.mbc);
			break;
		case ADMINSTRATER:
			temp = new AdminstraterMainUi(this, adminstraterControler.bc);
			break;
		default:
			System.out.println("wrong state");
			break;
		}
		mainFrame.nextPanel(temp);
		mainFrame.setVisible(true);
	}

}
