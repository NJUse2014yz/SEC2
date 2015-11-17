package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverOrderInUi;

public class ManagerControler {
private ClientControler mainControler;
	
	public ManagerControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	public void managerChangePanel(MANAGER_CONTROL n)
	{
		switch(n)
		{
		case MANAGER_MAIN:
			break;
		case AGENCY_ADD:
			break;
		case AGENCY_ADD_SUCCESS:
			break;
		case AGENCY_DELETE:
			break;
		case AGENCY_MODIFY:
			break;
		case AGENCY_LIST:
			break;
		case AGENCY_MODIFY_SUCCESS:
			break;
		case AGENCY_INQUIRY:
			break;
		case STAFF_ADD:
			break;
		case STAFF_ADD_SUCCESS:
			break;
		case STAFF_DELETE:
			break;
		case STAFF_MODIFY:
			break;
		case STAFF_LIST:
			break;
		case STAFF_MODIFY_SUCCESS:
			break;
		case STAFF_INQUIRY:
			break;
		case SALARY_MODIFY:
			break;
		case SALARY_INQUIRY:
			break;
		case CONST_ADD:
			break;
		case CONST_ADD_SUCCESS:
			break;
		case CONST_MODIFY:
			break;
		case CONST_MODIFY_SUCCESS:
			break;
		case CONST_INQUIRY:
			break;
		case RECEIPT_LIST:
			break;
		case RECEIPT_MODIFY:
			break;
		case ACCOUNT_CHECK:
			break;
		case COST_CHECK:
			break;
		case OPERATE_CHECK:
			break;
		case LOG_CHECK:
			break;
		default:
			System.out.println("wrong control");
			break;	
		}
	}

}
