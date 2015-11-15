package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;

public class AdminstraterControler {
	private ClientControler mainControler;
	
	public AdminstraterControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	public void adminstraterChangePanel(ADMINSTRATER_CONTROL n)
	{
		switch(n)
		{
		case USER_ADD:
			break;
		case ADD_SUCCESS:
			break;
		case USER_DELETE:
			break;
		case USER_LIST:
			break;
		case USER_MODIFY:
			break;
		case MODIFY_SUCCESS:
			break;
		case USER_INQUIRY:
			break;
		default:
			System.out.println("wrong control");
			break;	
		}
	}

}
