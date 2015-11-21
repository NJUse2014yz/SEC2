package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
/**
 * 负责实现人员机构等管理业务所需要的服务
 * @author 周聪
 */
public class ManagerController implements AgencyBlService,ConstBlService,SalaryBlService,StaffBlService{

	@Override
	public ResultMessage addStaff(StaffVO sv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteStaff(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyStaff(StaffVO sv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffVO observeStaff(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StaffVO> observeStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifySalary(SalaryVO sv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SalaryVO> observeSalary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyCity(CityVO cv){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityVO observeCity(String beginPlace, String endPlace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addCity(CityVO cp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteCity(String beginPlace, String endPlace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyPrice(PriceVO pp){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceVO observePrize(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addAgency(AgencyVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteAgency(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyAgency(AgencyVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyVO observeAgency(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AgencyVO> observeAllAgency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCities() {
		City city=new City();
		return city.getCities();
	}

}
