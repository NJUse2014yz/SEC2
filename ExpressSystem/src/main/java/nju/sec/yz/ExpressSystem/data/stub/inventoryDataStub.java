package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
/**
 * 
 * @author zhangqi
 *
 */
public class inventoryDataStub implements InventoryDataService{
	
	@Override
	public ResultMessage insert(InventoryPO ipo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(InventoryPO ipo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage init( ) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<InventoryPO> findByTime(String timeIn,String timeOut)throws RemoteException{
		ArrayList<InventoryPO> array=new ArrayList<InventoryPO>();
		array.add(new InventoryPO("0000000001","20151011","杭州",1,1,1,1,"20151023",TransportType.CAR,"0000000002"));
		array.add(new InventoryPO("000000B004","20150927","苏州",2,1,1,1,"20150929",TransportType.PLANE,"0000000012"));
		return array;
	}

	@Override
	public ArrayList<InventoryPO> findByPosition(String position) throws RemoteException{
		ArrayList<InventoryPO> array=new ArrayList<InventoryPO>();
		array.add(new InventoryPO("0000000001","20151011","杭州",1,1,1,1,"20151023",TransportType.CAR,"0000000002"));
		array.add(new InventoryPO("000000B004","20150927","苏州",2,1,1,1,"20150929",TransportType.PLANE,"0000000012"));
		return array;
	}

	@Override
	public ArrayList<InventoryPO> findAll( ) throws RemoteException{
		ArrayList<InventoryPO> array=new ArrayList<InventoryPO>();
		array.add(new InventoryPO("0000000001","20151011","杭州",1,1,1,1,"20151023",TransportType.CAR,"0000000002"));
		array.add(new InventoryPO("000000B004","20150927","苏州",2,1,1,1,"20150929",TransportType.PLANE,"0000000012"));
		return array;
	}
}
