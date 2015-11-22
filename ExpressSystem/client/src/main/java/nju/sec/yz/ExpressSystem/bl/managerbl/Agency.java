package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 机构的领域模型对象
 * TODO 营业厅和中转中心分开
 * @author 周聪
 *
 */
public class Agency {
	
	private AgencyDataService data;
	
	public Agency() {
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
	
	
	
	public TransitVO observeTransit(String id) {
		//stub
		TransitVO vo=new TransitVO("南京中转中心", "0251", "南京");
		vo.addPositions(new PositionVO("南京亚东仙林营业厅", "025001", "0251", "南京"));
		return vo;
	}

	
	public ArrayList<TransitVO> observeAllTransit() {
		//stub
		TransitVO vo=new TransitVO("南京中转中心", "0251", "南京");
		vo.addPositions(new PositionVO("南京亚东仙林营业厅", "025001", "0251", "南京"));
		ArrayList<TransitVO> vos=new ArrayList<>();
		vos.add(vo);
		return vos;
	}
	
	public ResultMessage addPosition(PositionVO av) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ResultMessage deletePosition(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
