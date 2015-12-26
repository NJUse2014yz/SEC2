package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterAddUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterButtonComponents;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterDeleteUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterInquiryUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterModifyFillUi;
import nju.sec.yz.ExpressSystem.presentation.userui.AdminstraterModifyUi;

public class AdminstraterControler {
	private ClientControler mainControler;
	public AdminstraterButtonComponents bc;
	
	public AdminstraterControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
		bc=new AdminstraterButtonComponents(mainControler);
	}
	public void adminstraterChangePanel(AdminstraterControl n)
	{
		switch(n)
		{
		case USER_ADD:
			mainControler.mainFrame.nextPanel(new AdminstraterAddUi(mainControler,bc));
			break;
		case USER_DELETE:
			mainControler.mainFrame.nextPanel(new AdminstraterDeleteUi(mainControler,bc));			
			break;
		case USER_LIST:
			mainControler.mainFrame.nextPanel(new AdminstraterModifyUi(mainControler,bc));
			break;
		case USER_INQUIRY:
			mainControler.mainFrame.nextPanel(new AdminstraterInquiryUi(mainControler,bc));
			break;
		default:
			System.out.println("wrong control");
			break;	
		}
	}

}
