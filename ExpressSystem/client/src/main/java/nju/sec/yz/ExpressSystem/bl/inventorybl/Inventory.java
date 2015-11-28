package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.tool.ExcelTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

/**
 * 库存的领域模型对象
 * @author 周聪
 *
 */
public class Inventory {
	
	private InventoryDataService data;
	private double rate;

//	public Inventory() {
//		try {
//			data=DatafactoryProxy.getInventoryDataService();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 * 库存查看
	 * 设定一个时间段，查看此时间段内的出/入库数量/金额/存储位置
	 * 库存数量要有合计
	 */
	public ArrayList<InventoryListVO> observeStock(String begin, String end) {
		ArrayList<InventoryListVO> list=new ArrayList<InventoryListVO>();
		String transit=getTransit();
		try {
			ArrayList<InventoryPO> poList=data.findByTime(transit, begin, end);
			if(poList==null)
				return null;
			for(InventoryPO po:poList){
				InventoryListVO vo=changePoToVo(po);
				list.add(vo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private InventoryListVO changePoToVo(InventoryPO po) {
		InventoryInInformation inventoryInInformation=po.getInventoryInformation();
		InventoryOutInformation inventoryOutInformation=po.getInventoryOutInformation();
		String barId=po.getBarId();
		InventoryListVO vo=new InventoryListVO();
		vo.setInventoryInInformation(inventoryInInformation);
		vo.setInventoryOutInformation(inventoryOutInformation);
		vo.setBarId(barId);
		return vo;
	}


	/**
	 * 库存盘点
	 */
	public ArrayList<InventoryListVO> checkStock() {
		ArrayList<InventoryListVO> list=new ArrayList<InventoryListVO>();
		try {
			String transit=getTransit();
			ArrayList<InventoryPO> poList=data.findByTime(transit, TimeTool.getDate());
			if(poList==null)
				return null;
			for(InventoryPO po:poList){
				InventoryListVO vo=changePoToVo(po);
				list.add(vo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getTransit(){
		UserInfo user=new User();
		String userid=user.getCurrentID();
		String strs[]=userid.split("A");
		String transit=strs[1];
		return transit;
	}
	
	/**
	 * @author cong
	 * 设置库存警报比例
	 * @param rate 库存警报比例，为0-1的double值
	 */
	public ResultMessage setAlertRate(double rate) {
		if(rate>1.0||rate<0.0)
			return new ResultMessage(Result.FAIL,"库存警戒值输入错误，为0-1");
		this.rate=rate;
		return null;
	}
	
	/**
	 * @author sai
	 * 导出excel
	 * @return
	 */
	public ResultMessage exportToExcel(){
		ResultMessage message = new ResultMessage(Result.SUCCESS);
		String filename =getFileName();
		String txt = "22";
		ExcelTool.exportExcel(filename, txt);
		return message;
		
		
	}

	private String getFileName() {
		String result="";
		int count=getCurrentCounter()+1;
		saveCounter(count);
		result="xsl/"+TimeTool.getDate()+"库存盘点信息"+count+".xlsx";
		return result;
	}
	/**
	 * 保存当天文件计数的次数
	 */
	public void saveCounter(int count){
		File file=new File("File/count_excel");
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(count);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得当天文件计数的次数
	 */
	private int getCurrentCounter(){
		File file=new File("File/count_excel");
		int count=0;
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			count=(int) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return count;
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
	public static void main(String[] args) {
		Inventory i=new Inventory();
		i.exportToExcel();
		
	} 
}
