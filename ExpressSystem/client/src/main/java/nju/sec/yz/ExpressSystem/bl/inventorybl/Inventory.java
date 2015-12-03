package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.tool.ExcelTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;

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
	public InventoryListVO observeStock(String begin, String end) {
		InventoryListVO voList=new InventoryListVO();
		String transit=getTransit();
		InventoryInSheet insheet=new InventoryInSheet();
		InventoryOutSheet outsheet=new InventoryOutSheet();
		try {
			List<InventoryInSheetPO> poInList=insheet.data.findByTime(transit, begin, end);
			List<InventoryOutSheetPO> poOutList=outsheet.data.findByTime(transit, begin, end);
			if(poInList==null&&poOutList==null)
				return null;
			List<InventoryInSheetVO> voInList=new ArrayList<InventoryInSheetVO>();
			List<InventoryOutSheetVO> voOutList=new ArrayList<InventoryOutSheetVO>();
			for(InventoryInSheetPO po:poInList){
				InventoryInSheetVO vo=changeInPotoVo(po);
				voInList.add(vo);
			}
			for(InventoryOutSheetPO po:poOutList){
				InventoryOutSheetVO vo=changeOutPotoVo(po);
				voOutList.add(vo);
			}
			voList.inList=voInList;
			voList.outList=voOutList;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		return voList;
	}

	private InventoryOutSheetVO changeOutPotoVo(InventoryOutSheetPO po) {
		InventoryOutSheet outsheet=new InventoryOutSheet();
		InventoryOutSheetVO vo=(InventoryOutSheetVO)outsheet.show(po);
		return vo;
	}


	private InventoryInSheetVO changeInPotoVo(InventoryInSheetPO inpo) {
		InventoryInSheet insheet=new InventoryInSheet();
		InventoryInSheetVO vo=(InventoryInSheetVO) insheet.show(inpo);
		return vo;
	}


	/**
	 * 库存盘点
	 */
	public InventoryListVO checkStock() {
		InventoryListVO voList=new InventoryListVO();
		String transit=getTransit();
		try {
			List<InventoryInSheetPO> poInList=data.findAll(transit);
			if(poInList==null)
				return null;
			List<InventoryInSheetVO> voInList=new ArrayList<InventoryInSheetVO>();
			for(InventoryInSheetPO po:poInList){
				InventoryInSheetVO vo=changeInPotoVo(po);
				voInList.add(vo);
			}
			voList.inList=voInList;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		return voList;
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
	 * 入库时添加库存
	 */
	public ResultMessage updateIn(InventoryInSheetPO po){
		ResultMessage message=new ResultMessage(Result.FAIL);
		try {
			message=data.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 出库时删除库存
	 */
	public ResultMessage updateOut(String transit,String barId){
		ResultMessage message=new ResultMessage(Result.FAIL);
		try {
			message=data.delete(transit, barId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * @author sai
	 * 导出excel
	 * @return
	 */
	public ResultMessage exportToExcel(){
		InventoryListVO vo =checkStock();
		ResultMessage message = new ResultMessage(Result.SUCCESS);
		String filename =getFileName();
		String txt =getTxtPath(vo);
		ExcelTool.exportExcel(filename, txt);
		return message;				
	}
	
	private String getTxtPath(InventoryListVO vo){
		List<InventoryInSheetVO> inlist=vo.inList;
		int count=Integer.parseInt(getCurrentCounter())+1;
		String[] title={"快递单号 ","入库日期 ","到达地 ","区号 ","排号 ","架号 ","位号 ","中转中心编号"};
		File file=new File("File/"+TimeTool.getDate()+"InventoryList"+count);
		try {
			FileWriter fw=new FileWriter(file);
			for(String str:title){
				fw.write(str);
			}
			for(int i=0;i<inlist.size();i++){
				fw.write("\n");
				InventoryInSheetVO invo=inlist.get(i);
				InventoryInInformation ii=invo.getInventoryInInformation();
				fw.write(invo.getBarId()+" "+ii.getTime()+" "+ii.getDestination()+" "+ii.getBlock()+" "
						+ii.getRow()+" "+ii.getShelf()+" "+ii.getPositon()+" "+ii.getTransit());
			}
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//System.out.println(file.getPath());
		return file.getPath();
	}
	
//	public static void main(String[] args) {
//		Inventory in=new Inventory();
//		InventoryListVO vo=new InventoryListVO();
//		InventoryInInformation inventoryInInformation=new InventoryInInformation("20150202", "南京", 10, 10, 10, 10,"025");
//		InventoryInSheetVO invo=new InventoryInSheetVO(inventoryInInformation, "123");
//		List<InventoryInSheetVO> involist=new ArrayList<InventoryInSheetVO>();
//		involist.add(invo);
//		vo.inList=involist;
//		in.getTxtPath(vo);
//	}

	/**
	 * 获得中转中心编号
	 * @return
	 */
	private String getTransit(){
		UserInfo user=new User();
		String userid=user.getCurrentID();
		String strs[]=userid.split("A");
		String transit=strs[1];
		return transit;
	}
	
	private String getFileName() {
		String result="";
		int count=Integer.parseInt(getCurrentCounter())+1;
		//System.out.println(count);
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
		}catch(IOException e) {
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
//		
//	public static void main(String[] args) {
//		Inventory i=new Inventory();
//		i.exportToExcel();
//		
//	} 
}
