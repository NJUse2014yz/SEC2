package nju.sec.yz.ExpressSystem.presentation.controlerui;

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
			break;
		case INVENTORY_IN:
			break;
		case IN_SUCCESS:
			break;
		case INVENTORY_OUT:
			break;
		case OUT_SUCCESS:
			break;
		case INVENTORY_OBSERVE:
			break;
		case INVENTORY_CHECK:
			break;
		case INVENTORY_SET:
			break;
		case SET_SUCCESS:
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
