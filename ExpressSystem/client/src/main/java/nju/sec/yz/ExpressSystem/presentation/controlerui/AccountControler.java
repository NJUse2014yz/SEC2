package nju.sec.yz.ExpressSystem.presentation.controlerui;

public class AccountControler {
	private ClientControler mainControler;
	AccountControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	void accountChangePanel(ACCOUNT_CONTROL n)
	{
		switch(n)
		{	
		case ACCOUNT_MAIN:
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
