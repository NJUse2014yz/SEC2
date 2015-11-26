package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryCheck;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryIn;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryMainUi;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryObserve;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventoryOut;
import nju.sec.yz.ExpressSystem.presentation.inventoryui.InventorySetRate;

public class InventoryControler {
private ClientControler mainControler;
	
	public InventoryControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	
	public void deliverChangePanel(INVENTORY_CONTROL n)
	{
		switch(n)
		{
		case INVENTORY_MAIN:
			mainControler.mainFrame.nextPanel(new InventoryMainUi(mainControler));
			break;
		case INVENTORY_IN:
			mainControler.mainFrame.nextPanel(new InventoryIn(mainControler));
			break;
		case IN_SUCCESS:
			break;
		case INVENTORY_OUT:
			mainControler.mainFrame.nextPanel(new InventoryOut(mainControler));
			break;
		case OUT_SUCCESS:
			break;
		case INVENTORY_OBSERVE:
			mainControler.mainFrame.nextPanel(new InventoryObserve(mainControler));
			break;
		case INVENTORY_CHECK:
			mainControler.mainFrame.nextPanel(new InventoryCheck(mainControler));
			break;
		case INVENTORY_SET:
			mainControler.mainFrame.nextPanel(new InventorySetRate(mainControler));
			break;
		case SET_SUCCESS:
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
