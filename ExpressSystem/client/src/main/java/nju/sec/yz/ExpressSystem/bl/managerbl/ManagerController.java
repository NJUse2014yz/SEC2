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
	public List<String> getCities() {
		City city=new City();
		return city.getCities();
	}

	@Override
	public ResultMessage addTransit(TransitVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteTransit(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyTransit(TransitVO av) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deletePosition(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
