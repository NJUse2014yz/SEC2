package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAccountCheck;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAgencyAdd;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAgencyDelete;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAgencyList;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAgencyModify;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerAgencyObserve;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerButtonComponent;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerConstAdd;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerConstModify;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerConstObserve;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerLogCheck;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerMainUi;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerReceiptList;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerReceiptModify;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerSalaryModify;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerSalaryObserve;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerStaffAdd;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerStaffDelete;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerStaffList;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerStaffModify;
import nju.sec.yz.ExpressSystem.presentation.managerui.ManagerStaffObserve;

public class ManagerControler {
private ClientControler mainControler;
public ManagerButtonComponent mbc;

	
	public ManagerControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
		this.mbc=new ManagerButtonComponent(mainControler);
	}
	public void managerChangePanel(MANAGER_CONTROL n)
	{
		switch(n)
		{
		case MANAGER_MAIN:
			mainControler.mainFrame.nextPanel(new ManagerMainUi(mainControler,mbc));
			break;
		case AGENCY_ADD:
			mainControler.mainFrame.nextPanel(new ManagerAgencyAdd(mainControler,mbc));
			break;
		case AGENCY_ADD_SUCCESS:
			break;
		case AGENCY_DELETE:
			mainControler.mainFrame.nextPanel(new ManagerAgencyDelete(mainControler,mbc));
			break;
		case AGENCY_MODIFY:
//			mainControler.mainFrame.nextPanel(new ManagerAgencyModify(mainControler,mbc));
			break;
		case AGENCY_LIST:
			mainControler.mainFrame.nextPanel(new ManagerAgencyList(mainControler,mbc,"modify"));
			break;
		case AGENCY_MODIFY_SUCCESS:
			break;
		case AGENCY_INQUIRY:
			mainControler.mainFrame.nextPanel(new ManagerAgencyList(mainControler,mbc,"observe"));
			break;
		case STAFF_ADD:
			mainControler.mainFrame.nextPanel(new ManagerStaffAdd(mainControler,mbc));
			break;
		case STAFF_ADD_SUCCESS:
			break;
		case STAFF_DELETE:
			mainControler.mainFrame.nextPanel(new ManagerStaffDelete(mainControler,mbc));
			break;
		case STAFF_MODIFY:
//			mainControler.mainFrame.nextPanel(new ManagerStaffModify(mainControler,mbc));
			break;
		case STAFF_LIST:
			mainControler.mainFrame.nextPanel(new ManagerStaffList(mainControler,mbc));
			break;
		case STAFF_MODIFY_SUCCESS:
			break;
		case STAFF_INQUIRY:
			mainControler.mainFrame.nextPanel(new ManagerStaffObserve(mainControler,mbc));
			break;
		case SALARY_MODIFY:
			mainControler.mainFrame.nextPanel(new ManagerSalaryModify(mainControler,mbc));
			break;
		case SALARY_INQUIRY:
			mainControler.mainFrame.nextPanel(new ManagerSalaryObserve(mainControler,mbc));
			break;
		case CONST_ADD:
			mainControler.mainFrame.nextPanel(new ManagerConstAdd(mainControler,mbc));
			break;
		case CONST_ADD_SUCCESS:
			break;
		case CONST_MODIFY:
			mainControler.mainFrame.nextPanel(new ManagerConstModify(mainControler,mbc));
			break;
		case CONST_MODIFY_SUCCESS:
			break;
		case CONST_INQUIRY:
			mainControler.mainFrame.nextPanel(new ManagerConstObserve(mainControler,mbc));
			break;
		case RECEIPT_LIST:
			mainControler.mainFrame.nextPanel(new ManagerReceiptList(mainControler,mbc));
			break;
		case RECEIPT_MODIFY:
			mainControler.mainFrame.nextPanel(new ManagerReceiptModify(mainControler,mbc));
			break;
		case ACCOUNT_CHECK:
			mainControler.mainFrame.nextPanel(new ManagerAccountCheck(mainControler,mbc));
			break;
		case COST_CHECK:
			break;
		case OPERATE_CHECK:
			break;
		case LOG_CHECK:
			mainControler.mainFrame.nextPanel(new ManagerLogCheck(mainControler,mbc));
			break;
		default:
			System.out.println("wrong control");
			break;	
		}
	}

}
