package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * 库存的领域模型对象
 * @author 周聪
 *
 */
public class Inventory {
	
	private InventoryDataService data;
	
	public Inventory() {
		try {
			data=DatafactoryProxy.getInventoryDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 库存查看
	 * 设定一个时间段，查看此时间段内的出/入库数量/金额/存储位置
	 * 库存数量要有合计
	 */
	public ArrayList<InventoryVO> observeStock(String transit,String begin, String end) {
		ArrayList<InventoryVO> list=new ArrayList<InventoryVO>();
		try {
			ArrayList<InventoryPO> poList=data.findByTime(transit, begin, end);
			for(InventoryPO po:poList){
				InventoryVO vo=changePoToVo(po);
				list.add(vo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private InventoryVO changePoToVo(InventoryPO po) {
		InventoryInInformation inventoryInInformation=po.getInventoryInformation();
		InventoryOutInformation inventoryOutInformation=po.getInventoryOutInformation();
		String barId=po.getBarId();
		InventoryVO vo=new InventoryVO();
		vo.setInventoryInInformation(inventoryInInformation);
		vo.setInventoryOutInformation(inventoryOutInformation);
		vo.setBarId(barId);
		return vo;
	}


	/**
	 * 库存盘点
	 */
	public ArrayList<InventoryVO> checkStock() {
		ArrayList<InventoryVO> list=new ArrayList<InventoryVO>();
		try {
			ArrayList<InventoryPO> poList=data.findAll();
			for(InventoryPO po:poList){
				InventoryVO vo=changePoToVo(po);
				list.add(vo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @author cong
	 * 设置库存警报比例
	 * @param rate 库存警报比例，为0-1的double值
	 */
	public ResultMessage setAlertRate(double rate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @author sai
	 * 导出excel
	 * @return
	 */
	public ResultMessage exportToExcel() {
		
//		String filename = null;
//		String txt = null;
//		ExcelTool.exportExcel(filename, txt);
		return null;
		
		
	}

	public ResultMessage updateInReceipt(InventoryInSheetPO inPO) {
		InventoryInInformation imfo = inPO.getInventoryInInformation();
		ResultMessage message=null;
		InventoryPO inventoryPO=new InventoryPO(imfo, null, inPO.getBarId() );
		
		try {
			message=data.insert(inventoryPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public ResultMessage updateOutReceipt(InventoryOutSheetPO outPO) {
		InventoryOutInformation info = outPO.getInventoryOutInformation();
		ResultMessage message=null;
		InventoryPO inventoryPO=new InventoryPO( null,info, outPO.getBarId() );
		
		try {
			message=data.insert(inventoryPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}	
}
