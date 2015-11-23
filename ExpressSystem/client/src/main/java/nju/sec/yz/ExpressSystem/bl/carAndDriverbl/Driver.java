package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

/**
 * 司机信息的领域模型对象
 * @author 周聪
 *
 */
public class Driver {
	
	private DriverDataService data;
	
	public Driver() {
		try {
			data=DatafactoryProxy.getDriverDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<DriverVO> getAll() {
		ArrayList<DriverPO> listPO = null;
		ArrayList<DriverVO> listVO = new ArrayList<DriverVO>();
		//获取数据库中的userpo列表
		try {
			listPO=data.findAll();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			DriverVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	public DriverVO getSingle(String id) {
		DriverVO vo=null;
		try {
			DriverPO	po=data.find(id);
			vo=changePoToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo; 
	}

	
	public ResultMessage add(DriverVO vo) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success")){
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		try {
			DriverPO po=changeVoToPo(vo);
			message=data.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}
	
	public ResultMessage del(String id) {
		ResultMessage result=null;
		//调用data层方法,验证id是否存在
		try {
			result=data.delete(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return result;
	}
	
	public ResultMessage modify(DriverVO vo) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(vo);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		DriverPO po=changeVoToPo(vo);
		try {
			message=data.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	private String isValid(DriverVO vo) {
		String id=vo.getId();
		String birthDate=vo.getBirthDate();
		String personID=vo.getPersonID();
		String phoneNumber=vo.getPhoneNumber();
		String licenseDeadLine=vo.getLicenseDeadLine();
		if(!isId(id))
			return "看看ID输对了没";
		if(!isBeforeDate(birthDate))
			return "出生日期不符合日期格式啊";
		if(!isPersonId(personID))
			return "身份证输错了哦";
		if(!isPhoneNumber(phoneNumber))
			return "手机号码不是11位数字吗，你这";
		if(!isLaterDate(licenseDeadLine))
			return "行驶证期限不符合日期格式哦";
		return "success";
	}

	private boolean isPhoneNumber(String phoneNumber) {
		if(phoneNumber.length()!=11)
			return false;
		char array[]=phoneNumber.toCharArray();
		for(char a:array)
			if(a<'0'||a>'9')
				return false;
		return true;
	}

	private boolean isPersonId(String personID) {
		if(personID.length()!=18)
			return false;
		char array[]=personID.toCharArray();
		for(int i=0;i<17;i++)
			if(array[i]<'0'||array[i]>'9')
				return false;
		if((array[17]<'0'||array[17]>'9')&&(array[17]!='x'||array[17]!='X'))
				return false;
		return true;
	}

	private boolean isBeforeDate(String date) {
		if(date.length()!=8)
			return false;
		char array[]=date.toCharArray();
		for(char a:array)
			if(a<'0'||a>'9')
				return false;
		int dateToInt=Integer.parseInt(date);
		String now=TimeTool.getDate();
		int nowToInt=Integer.parseInt(now);
		//超过今天
		if(dateToInt>nowToInt)
			return false;
		return true;
	}
	private boolean isLaterDate(String date) {
		if(date.length()!=8)
			return false;
		char array[]=date.toCharArray();
		for(char a:array)
			if(a<'0'||a>'9')
				return false;
		int dateToInt=Integer.parseInt(date);
		String now=TimeTool.getDate();
		int nowToInt=Integer.parseInt(now);
		
		
		//超过今天
		if(dateToInt<nowToInt){
			return false;
		}
			
		return true;
	}

	private boolean isId(String id) {
		if(id.length()!=9&&id.length()!=10)
			return false;
		char numbers[]=id.toCharArray();
		for(char a:numbers){
			if(a<'0'||a>'9')
				return false;
		}
			
		
		return true;
	}

	private DriverVO changePoToVo(DriverPO po) {
		String id=po.getId();
		String name=po.getName();
		String birthDate=po.getBirthDate();
		String personID=po.getPersonID();
		String phoneNumber=po.getPhoneNumber();
		Sex sex=po.getSex();
		String licenseDeadLine=po.getLicenseDeadLine();
		DriverVO vo=new DriverVO(id, name, birthDate, personID, phoneNumber, sex, licenseDeadLine);
		return vo;
	}

	private DriverPO changeVoToPo(DriverVO vo) {
		String id=vo.getId();
		String name=vo.getName();
		String birthDate=vo.getBirthDate();
		String personID=vo.getPersonID();
		String phoneNumber=vo.getPhoneNumber();
		Sex sex=vo.getSex();
		String licenseDeadLine=vo.getLicenseDeadLine();
		DriverPO po=new DriverPO(id, name, birthDate, personID, phoneNumber, sex, licenseDeadLine);
		return po;
	}
	
	public void test(){
		this.add(new DriverVO("0251234565", "mike", "19020123", "450333190201232345", "13456789023", Sex.MALE, "20161223"));
		DriverVO vo=this.getSingle("0251234565");
		System.out.println(vo.getName());
	}
	
}