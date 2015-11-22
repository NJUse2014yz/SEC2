package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;
/**
 * 中转中心
 * @author 周聪
 *
 */
public class Transit {
	private AgencyDataService data;
	
	public Transit() {
		try {
			data=DatafactoryProxy.getAgencyDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultMessage addTransit(TransitVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage deleteTransit(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage updateTransit(TransitVO av) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ResultMessage addPosition(PositionVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage deletePosition(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TransitVO observeTransit(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<TransitVO> observeAllTransit() {
		// TODO Auto-generated method stub
		return null;
	}
}
