package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.accountui.AccountAddUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountChechLogUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountCostTableUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountCostUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountDeleteUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountInComeUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountInitialUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountInquiryUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountModifyUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountOperateTableUi;
import nju.sec.yz.ExpressSystem.presentation.accountui.AccountButtonComponents;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionArriveUi;


public class AccountControler {
	private ClientControler mainControler;
	public static AccountButtonComponents bc;
	public AccountControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
		this.bc=new AccountButtonComponents(mainControler);
	}
	public void accountChangePanel(ACCOUNT_CONTROL n)
	{
		switch(n)
		{	
		case ACCOUNT_MAIN:
			break;
		case INCOME:
			mainControler.mainFrame.nextPanel(new AccountInComeUi(mainControler,bc));
			break;
		case INITIAL:
			mainControler.mainFrame.nextPanel(new AccountInitialUi(mainControler,bc));
			break;
		case COST:
			mainControler.mainFrame.nextPanel(new AccountCostUi(mainControler,bc));
			break;
		case TABLE_OPERATE:
			mainControler.mainFrame.nextPanel(new AccountOperateTableUi(mainControler,bc));
			break;
		case TABLE_COST:
			mainControler.mainFrame.nextPanel(new AccountCostTableUi(mainControler,bc));
			break;
		case ADD_ACCOUNT:
			mainControler.mainFrame.nextPanel(new AccountAddUi(mainControler,bc));
			break;
		case DELETE_ACCOUNT:
			mainControler.mainFrame.nextPanel(new AccountDeleteUi(mainControler,bc));
			break;
		case MODIFY_ACCOUNTLIST:
			mainControler.mainFrame.nextPanel(new AccountModifyUi(mainControler,bc));
			break;
		case MODIFY:
			break;
		case ENQUIRY_ACCOUNT:
			mainControler.mainFrame.nextPanel(new AccountInquiryUi(mainControler,bc));
			break;
		case LOG_CHECK:
			mainControler.mainFrame.nextPanel(new AccountChechLogUi(mainControler,bc));
			break;
		}
		
	}
}
