package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;
import nju.sec.yz.ExpressSystem.presentation.userui.UserUiTest;

/*
 * zhangqi
 * decide the transforming of the deliver's ui;
 */
public class DeliverControler {
	private ClientControler mainControler;
	
	public DeliverControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	public void deliverChangePanel(DELIVER_CONTROL n)
	{
		switch(n)
		{
		case DELIVER_MAIN:
			break;
		case ORDER_IN:
			mainControler.mainFrame.nextPanel(new DeliverOrderInUi(mainControler));
			break;
		case ORDER_IN_SUCCESS:
			break;
		case DELIVERY_INQUIRY:
			break;
		case RECEIVE_IN:
			break;
		case RECEIVE_IN_SUCCESS:
			break;
		default:
			System.out.println("wrong control");
			break;	
		}
	}

}
