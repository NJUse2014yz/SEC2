package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.tool.LogTool;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;
/**
 * 负责实现人员机构等管理业务所需要的服务
 * @author 周聪
 */
public class ManagerController implements AgencyBlService,ConstBlService,SalaryBlService,StaffBlService{

	@Override
	public ResultMessage addStaff(StaffVO sv) {
		Staff staff=new Staff();
		ResultMessage message=staff.addStaff(sv);
		LogTool.setLog("新增职员");
		return message;
	}

	@Override
	public ResultMessage deleteStaff(String id) {
		Staff staff=new Staff();
		ResultMessage message=staff.deleteStaff(id);
		LogTool.setLog("删除职员");
		return message;
	}

	@Override
	public ResultMessage modifyStaff(StaffVO sv) {
		Staff staff=new Staff();
		ResultMessage message=staff.modifyStaff(sv);
		LogTool.setLog("修改职员");
		return message;
	}

	@Override
	public StaffVO observeStaff(String id) {
		Staff staff=new Staff();
		return staff.observeStaff(id);
	}

	@Override
	public ArrayList<StaffVO> observeStaff() {
		
		Staff staff=new Staff();
		ArrayList<StaffVO> list=staff.observeStaff();
		return list;
	}

	@Override
	public ResultMessage modifySalary(SalaryVO sv) {
		Salary salary=new Salary();
		ResultMessage message=salary.modifySalary(sv);
		LogTool.setLog("修改薪水策略");
		return message;
	}

	@Override
	public ArrayList<SalaryVO> observeSalary() {
		Salary salary=new Salary();
		ArrayList<SalaryVO> list=salary.observeSalary();
		return list;
	}

	@Override
	public ResultMessage modifyCity(CityVO cv){
		CityConst city=new CityConst();
		ResultMessage message=city.modifyCity(cv);
		LogTool.setLog("修改城市常量");
		return message;
	}

	@Override
	public CityVO observeCity(String beginPlace, String endPlace) {
		CityConst city=new CityConst();
		CityVO vo=city.observeCity(beginPlace, endPlace);
		return vo;
	}
	
	@Override
	public List<CityVO> observeAllCity() {
		CityConst city=new CityConst();
		List<CityVO> list=city.getAllCity();
		return list;
	}

	@Override
	public ResultMessage addCity(CityVO cp) {
		CityConst city=new CityConst();
		ResultMessage message=city.addCity(cp);
		LogTool.setLog("新增城市常量");
		return message;
	}

	@Override
	public ResultMessage deleteCity(String beginPlace, String endPlace) {
		CityConst city=new CityConst();
		ResultMessage message=city.deleteCity(beginPlace, endPlace);
		LogTool.setLog("删除城市常量");
		return message;
	}

	@Override
	public ResultMessage modifyPrice(PriceVO pp){
		Price price=new Price();
		ResultMessage message =new ResultMessage(Result.SUCCESS);
		try {
			message = price.modifyPrice(pp);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		LogTool.setLog("修改价格常量");
		return message;
	}

	@Override
	public PriceVO observePrize(){
		Price price=new Price();
		PriceVO vo=price.observePrice();
		return vo;
	}


	@Override
	public List<String> getCities() {
		City city=new City();
		return city.getCities();
	}

	@Override
	public ResultMessage addTransit(TransitVO av) {
		Agency agency=new Agency();
		ResultMessage message=agency.addTransit(av);
		LogTool.setLog("增加中转中心");
		return message;
	}

	@Override
	public ResultMessage deleteTransit(String id) {
		Agency agency=new Agency();
		ResultMessage message=agency.deleteTransit(id);
		LogTool.setLog("删除中转中心");
		return message;
	}

	@Override
	public ResultMessage modifyTransit(TransitVO av) {
		Agency agency=new Agency();
		ResultMessage message=agency.updateTransit(av);
		LogTool.setLog("修改中转中心资料");
		return message;
	}

	@Override
	/**
	 * 通过id获得中转中心信息
	 */
	public TransitVO observeTransit(String id) {
		Agency agency=new Agency();
		TransitVO vo=agency.observeTransit(id);
		return vo;
	}

	@Override
	public ArrayList<TransitVO> observeAllTransit() {
		Agency agency=new Agency();
		ArrayList<TransitVO> vos=agency.observeAllTransit();
		return vos;
	}

	@Override
	public ResultMessage addPosition(PositionVO av) {
		Agency agency=new Agency();
		ResultMessage message=agency.addPosition(av);
		LogTool.setLog("增加营业厅");
		return message;
	}

	@Override
	public ResultMessage deletePosition(String transitId,String id) {
		Agency agency=new Agency();
		ResultMessage message=agency.deletePosition(transitId,id);
		LogTool.setLog("删除营业厅");
		return message;
	}

	@Override
	/**
	 * 关键字查找机构
	 */
	public AgencyListVO observeTransitByName(String name) {
		Agency agency=new Agency();
		AgencyListVO vo=agency.observeTransitByName(name);
		return vo;
	}

	

}
