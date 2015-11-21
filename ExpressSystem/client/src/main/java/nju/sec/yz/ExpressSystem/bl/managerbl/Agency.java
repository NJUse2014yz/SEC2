package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;

/**
 * 机构的领域模型对象
 * TODO 营业厅和中转中心分开
 * @author 周聪
 *
 */
public class Agency {
	private AgencyDataService agencyData;
	
	public Agency() {
		try {
			agencyData=DatafactoryProxy.getAgencyDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultMessage addAgency(AgencyVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage deleteAgency(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modifyAgency(AgencyVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public AgencyVO observeAgency(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<AgencyVO> observeAllAgency() {
		// TODO Auto-generated method stub
		return null;
	}
}
