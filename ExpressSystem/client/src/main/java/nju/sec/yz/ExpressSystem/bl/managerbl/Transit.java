package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.accountbl.Initialable;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.po.TransitPO;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 机构的领域模型对象 TODO 营业厅和中转中心分开
 * 
 * @author 周聪
 *
 */
public class Transit implements AgencyInfo, Initialable<TransitVO, TransitPO> {

	private AgencyDataService data;

	public Transit() {
		try {
			data = DatafactoryProxy.getAgencyDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	/**
	 * 新建时无营业厅
	 */
	public ResultMessage addTransit(TransitVO av) {

		if (!isValidTransit(av.getId()))
			return new ResultMessage(Result.FAIL, "亲，咱们的中转中心编号是城市编码加一位数字哟~");

		TransitPO po = new TransitPO(av.getName(), av.getId(), av.getLocation());
		ResultMessage message = new ResultMessage(Result.FAIL);

		try {
			message = data.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}

		return message;
	}

	public ResultMessage deleteTransit(String id) {
		ResultMessage message = new ResultMessage(Result.FAIL);

		try {
			message = data.delete(id);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}

		return message;
	}

	public ResultMessage updateTransit(TransitVO av) {

		ResultMessage message = new ResultMessage(Result.FAIL);

		List<PositionVO> vos = av.getPositions();
		List<PositionPO> positions = new ArrayList<>();

		// 复制营业厅
		for (PositionVO vo : vos) {
			PositionPO po = new PositionPO(vo.getName(), vo.getId(), vo.getTransitId(), vo.getLocation());
			positions.add(po);
		}

		if(!isValidTransit(av.getId()))
			return new ResultMessage(Result.FAIL,"亲，咱们的中转中心编号是城市编码加一位数字哟~");
		
		TransitPO po = new TransitPO(av.getName(), av.getId(), positions, av.getLocation());

		try {
			message = data.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}

		return message;
	}

	public TransitVO observeTransit(String id) {

		TransitPO po = null;
		try {
			po = data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (po == null)
			return null;

		TransitVO transitVO = this.show(po);

		return transitVO;
	}

	/**
	 * 按名字查找机构
	 */
	public AgencyListVO observeTransitByName(String name) {
		List<TransitVO> transits = this.observeAllTransit();

		AgencyListVO list = new AgencyListVO();
		for (TransitVO transit : transits) {
			// 匹配中转中心
			if (transit.getName().contains(name))
				list.transits.add(transit);
			// 查找中转中心中的营业厅
			for (PositionVO position : transit.getPositions()) {
				// 匹配营业厅
				if (position.getName().contains(name))
					list.positions.add(position);
			}

		}
		return list;
	}

	public ArrayList<TransitVO> observeAllTransit() {
		List<TransitPO> pos = null;
		try {
			pos = data.findAll();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}

		ArrayList<TransitVO> vos = new ArrayList<>();

		for (TransitPO po : pos) {
			TransitVO vo = this.show(po);
			vos.add(vo);
		}

		return vos;
	}

	@Override
	public TransitVO show(TransitPO po) {
		List<PositionPO> pos = po.getPositions();
		List<PositionVO> positions = new ArrayList<>();

		for (PositionPO p : pos) {
			PositionVO vo = new PositionVO(p.getName(), p.getId(), p.getTransitId(), p.getLocation());
			positions.add(vo);
		}

		TransitVO vo = new TransitVO(po.getName(), po.getId(), positions, po.getLocation());

		return vo;
	}

	private boolean isValidTransit(String id) {
		if (!ValidHelper.isNumber(id))
			return false;
		if (id.length() != 4 && id.length() != 5)
			return false;

		return true;
	}

	@Override
	public String getTrancitLocation(String name) {
		String location = null;
		try {
			TransitPO po = data.findByName(name);
			if (po == null)
				return null;
			location = po.getLocation();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return location;
	}

	/**
	 * 按名字精确查找
	 * 
	 * @param name
	 * @return
	 */
	private TransitPO findTransit(String name) {

		TransitPO po = null;
		try {
			po = data.findByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (po == null)
			return null;

		return po;
	}

	@Override
	public ResultMessage init(List<TransitVO> transits) {
		ResultMessage message = new ResultMessage(Result.FAIL);
		Position p=new Position();
		
		List<TransitPO> pos = new ArrayList<>();
		for (TransitVO transit : transits) {
			boolean validResult = isValidTransit(transit.id);
			if (!validResult)
				return new ResultMessage(Result.FAIL, "第"+(transits.indexOf(transit)+1) + "个中转中心的" + "中转中心编号不符合格式");

			for(PositionVO position:transit.getPositions()){
				boolean isValid=p.isValidPosition(position.id);
				if(!isValid)
					return new ResultMessage(Result.FAIL,"有营业厅的id不符合格式哦~");
			}
			
			
		}
		//验证后添加
		for (TransitVO transit : transits) {
			TransitPO po = this.changeVOToPO(transit);
			pos.add(po);
		}

		try {
			message = data.init(pos);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	@Override
	/**
	 * 期初建帐专用
	 */
	public TransitPO changeVOToPO(TransitVO vo) {
		TransitPO po = new TransitPO(vo.name, vo.id, vo.location);
		
		//复制营业厅
		List<PositionVO> vos = vo.getPositions();
		List<PositionPO> positions = new ArrayList<>();

		for (PositionVO positionVO : vos) {
			PositionPO positionPO = new PositionPO(positionVO.getName(), positionVO.getId(), positionVO.getTransitId(), positionVO.getLocation());
			positions.add(positionPO);
		}
		
		po.setPositions(positions);
		
		return po;
	}

	@Override
	public String getName(String id) {
		//找中转中心
		TransitVO vo=this.observeTransit(id);
		if(vo!=null)
			return vo.name;
		
		//找营业厅
		Position position=new Position();
		PositionVO vo2=position.findPosition(id);
		
		if(vo2==null)
			return null;
		
		return vo2.name;
	}

	@Override
	public String getId(String name) {
		List<TransitVO> transits = this.observeAllTransit();

		for (TransitVO transit : transits) {
			// 匹配中转中心
			if (transit.getName().equals(name))
				return transit.getId();
			// 查找中转中心中的营业厅
			for (PositionVO position : transit.getPositions()) {
				// 匹配营业厅
				if (position.getName().equals(name))
					return position.getId();
			}

		}
		return null;
	}

	/*
	 * public void test(){ this.addTransit(new TransitVO("广州中转中心", "0201","广州"
	 * )); this.addPosition(new PositionVO("北京朝阳营业厅", "010001", "0101", "北京"));
	 * TransitVO vo=this.observeTransit("0101");
	 * System.out.println(vo.getName());
	 * System.out.println(vo.getPositions().get(0).getName()); List<TransitVO>
	 * vos=this.observeAllTransit(); for(TransitVO transitVO:vos){
	 * System.out.println(transitVO.getName()); } }
	 */
}
