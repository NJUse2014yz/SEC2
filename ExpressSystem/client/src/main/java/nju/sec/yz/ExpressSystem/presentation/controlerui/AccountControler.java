package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.accountui.AccountMainUiTest;

public class AccountControler {
	MainUi mainFrame;
	AccountControler(MainUi mainFrame)
	{
		this.mainFrame=mainFrame;
	}
	void accountChangePanel(ACCOUNT_CONTROL n)
	{
		switch(n)
		{	
		case ACCOUNT_MAIN:
			mainFrame.nextPanel(new AccountMainUiTest(this));
			break;
		case INCOME:
			break;
		case INITIAL:
			break;
		case COST:
			break;
		case COST_SUCCESS:
			break;
		case TABLE_OPERATE:
			break;
		case TABLE_COST:
			break;
		case ADD_ACCOUNT:
			break;
		case ADD_SUCCESS:
			break;
		case DELETE_ACCOUNT:
			break;
		case MODIFY_ACCOUNTLIST:
			break;
		case MODIFY:
			break;
		case MODIFY_SUCCESS:
			break;
		case ENQUIRY_ACCOUNT:
			break;
		case LOG_CHECK:
			break;
		}
		
	}
}
