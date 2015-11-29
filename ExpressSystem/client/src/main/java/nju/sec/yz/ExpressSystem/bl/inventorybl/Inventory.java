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
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

/**
 * 库存的领域模型对象
 * @author 周聪
 *
 */
public class Inventory {
	
	private InventoryDataService data;
	private double rate;

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
	public ArrayList<InventoryListVO> observeStock(String begin, String end) {
		ArrayList<InventoryListVO> list=new ArrayList<InventoryListVO>();
		String transit=getTransit();
		try {
			ArrayList<InventoryListPO> poList=data.findByTime(transit, begin, end);
			if(poList==null)
				return null;
			for(InventoryListPO po:poList){
				InventoryListVO vo=changePoToVo(po);
				list.add(vo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private InventoryListVO changePoToVo(InventoryListPO po) {
		
		return null;
	}


	/**
	 * 库存盘点
	 */
	public ArrayList<InventoryListVO> checkStock() {
		ArrayList<InventoryListVO> list=new ArrayList<InventoryListVO>();
		try {
			String transit=getTransit();
			ArrayList<InventoryListPO> poList=data.findByTime(transit, TimeTool.getDate());
			if(poList==null)
				return null;
			for(InventoryListPO po:poList){
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
	public ResultMessage exportToExcel(InventoryListPO po){
		ResultMessage message = new ResultMessage(Result.SUCCESS);
		String filename =getFileName();
		String txt ="22";
		ExcelTool.exportExcel(filename, txt);
		return message;
		
		
	}

	private String getFileName() {
		String result="";
		int count=Integer.parseInt(getCurrentCounter())+1;
		System.out.println(count);
		saveCounter(TimeTool.getDate()+count);
		result="xsl/"+TimeTool.getDate()+"库存盘点信息"+count+".xls";
		return result;
	}
	/**
	 * 保存当天文件日期和计数的次数
	 */
	private void saveCounter(String str){
		File file=new File("File/count_excel");
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(str);
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 获得文件计数的次数
	 */
	private String getCurrentCounter(){
		File file=new File("File/count_excel");
		String str="";
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			str=(String) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(str.length()<9){
			System.out.println("---");
			return 0+"";
		}
		if(!str.substring(0, 8).equals(TimeTool.getDate())){
			//saveCounter(TimeTool.getDate()+0);
			return 0+"";
		}	
		return str.charAt(8)+"";	
	}
		
	public static void main(String[] args) {
		Inventory i=new Inventory();
		i.exportToExcel();
		
	} 
}
