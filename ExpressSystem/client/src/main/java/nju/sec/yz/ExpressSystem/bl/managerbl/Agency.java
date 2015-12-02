package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.po.TransitPO;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 机构的领域模型对象 TODO 营业厅和中转中心分开
 * 
 * @author 周聪
 *
 */
public class Agency implements AgencyInfo {

	private AgencyDataService data;

	public Agency() {
		try {
			data = DatafactoryProxy.getAgencyDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultMessage addTransit(TransitVO av) {

		if (!isValidTransit(av.getId()))
			return new ResultMessage(Result.FAIL, "亲，咱们的中转中心编号是城市编码加一位数字哟~");

		TransitPO po = new TransitPO(av.getName(), av.getId(), av.getLocation());
		ResultMessage message = new ResultMessage(Result.FAIL);

		try {
			message = data.insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	public ResultMessage deleteTransit(String id) {
		ResultMessage message = new ResultMessage(Result.FAIL);

		try {
			message = data.delete(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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

		TransitPO po = new TransitPO(av.getName(), av.getId(), positions, av.getLocation());

		try {
			message = data.insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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

		TransitVO transitVO = this.poToVO(po);

		return transitVO;
	}
	
	/**
	 * 按名字查找机构
	 */
	public AgencyListVO observeTransitByName(String name) {
		List<TransitVO> transits=this.observeAllTransit();
		
		AgencyListVO list=new AgencyListVO();
		for(TransitVO transit:transits){
			//匹配中转中心
			if(transit.getName().contains(name))
				list.transits.add(transit);
			//查找中转中心中的营业厅
			for(PositionVO position:transit.getPositions()){
				//匹配营业厅
				if(position.getName().contains(name))
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<TransitVO> vos = new ArrayList<>();

		for (TransitPO po : pos) {
			TransitVO vo = this.poToVO(po);
			vos.add(vo);
		}

		return vos;
	}

	private TransitVO poToVO(TransitPO po) {
		List<PositionPO> pos = po.getPositions();
		List<PositionVO> positions = new ArrayList<>();

		for (PositionPO p : pos) {
			PositionVO vo = new PositionVO(p.getName(), p.getId(), p.getTransitId(), p.getLocation());
			positions.add(vo);
		}

		TransitVO vo = new TransitVO(po.getName(), po.getId(), positions, po.getLocation());

		return vo;
	}

	/**
	 * 向所属中转中心中添加营业厅
	 */
	public ResultMessage addPosition(PositionVO av) {

		if (!isValidPosition(av.getId()))
			return new ResultMessage(Result.FAIL, "亲，咱们的营业厅编号是城市编码加三位数字哟~");

		PositionPO po = new PositionPO(av.getName(), av.getId(), av.getTransitId(), av.getLocation());

		List<TransitPO> pos = null;
		TransitPO transitPO = null;
		try {
			pos = data.findAll();
			for (TransitPO tpo : pos) {
				if (tpo.getId().equals(av.getTransitId())) {
					transitPO = tpo;
					break;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (transitPO == null)
			return new ResultMessage(Result.FAIL, "找不到所属的中转中心~");

		List<PositionPO> positions = transitPO.getPositions();
		for (PositionPO position : positions) {
			if (position.getId().equals(po.getId()))
				return new ResultMessage(Result.FAIL, "营业厅已存在~");
		}

		// 更新中转中心
		transitPO.addPositions(po);

		ResultMessage message = new ResultMessage(Result.FAIL);
		try {
			message = data.update(transitPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}
	
	public PositionVO findPosition(String id){
		List<TransitVO> transits=this.observeAllTransit();
		
		for(TransitVO transit:transits){
			for(PositionVO position:transit.getPositions()){
				if(position.getId().equals(id))
					return position;
			}
		}
		
		return null;
	}

	public ResultMessage deletePosition(String transitId, String id) {
		List<TransitPO> pos = null;
		TransitPO transitPO = null;
		try {
			pos = data.findAll();
			for (TransitPO tpo : pos) {
				if (tpo.getId().equals(transitId)) {
					transitPO = tpo;
					break;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (transitPO == null)
			return new ResultMessage(Result.FAIL, "找不到所属的中转中心~");

		List<PositionPO> positions = transitPO.getPositions();
		for (int i = 0; i < positions.size(); i++) {
			PositionPO position = positions.get(i);
			if (position.getId().equals(id)) {
				positions.remove(i);
				break;
			}
		}

		ResultMessage message = new ResultMessage(Result.FAIL);
		try {
			message = data.update(transitPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	private boolean isValidPosition(String id) {
		if (!ValidHelper.isNumber(id))
			return false;
		if (id.length() != 6 && id.length() != 7)
			return false;

		return true;
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
		String location=null;
		try {
			TransitPO po=data.findByName(name);
			if(po==null)
				return null;
			location=po.getLocation();
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return location;
	}

	/**
	 * 按名字精确查找
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
