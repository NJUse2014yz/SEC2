package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.po.InPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class InDataStub implements InDataService{

	@Override
	public ResultMessage insert(InPO inpo) throws RemoteException {
		return null;
	}

	
	public InPO find(String id) throws RemoteException {
		return new InPO("20151023",12,"025001D001","f");
	}


	public ResultMessage delete(String id) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage update(InPO inpo) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<InPO> findAll() throws RemoteException {
		ArrayList<InPO> array=new ArrayList<InPO>();
		array.add(new InPO("20151023",12,"025001D001"," "));
		array.add(new InPO("20151024",30,"025002D004"," "));
		return array;
	}

	@Override
	public List<InPO> findByPosition(String date, String positionId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InPO> findByTime(String begin, String end) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
