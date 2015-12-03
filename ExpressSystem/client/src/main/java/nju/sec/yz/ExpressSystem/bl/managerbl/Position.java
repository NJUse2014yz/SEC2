package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.po.TransitPO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class Position {

	/**
	 * 向所属中转中心中添加营业厅
	 */
	public ResultMessage addPosition(PositionVO av) {

		if (!isValidPosition(av.getId()))
			return new ResultMessage(Result.FAIL, "亲，咱们的营业厅编号是城市编码加三位数字哟~");

		Transit transit=new Transit();

		TransitVO transitVO=null;
		
		List<TransitVO> transits = transit.observeAllTransit();
		for (TransitVO tvo : transits) {
			if (tvo.getId().equals(av.getTransitId())) {
				transitVO = tvo;
				break;
			}
		}

		if (transitVO == null)
			return new ResultMessage(Result.FAIL, "找不到所属的中转中心~");

		List<PositionVO> positions = transitVO.getPositions();
		for (PositionVO position : positions) {
			if (position.getId().equals(av.getId()))
				return new ResultMessage(Result.FAIL, "营业厅已存在~");
		}

		// 更新中转中心
		transitVO.addPositions(av);

		ResultMessage message = transit.updateTransit(transitVO);
		return message;
	}
	
	public PositionVO findPosition(String id){
		Transit transitHelper=new Transit();
		List<TransitVO> transits=transitHelper.observeAllTransit();
		
		for(TransitVO transit:transits){
			for(PositionVO position:transit.getPositions()){
				if(position.getId().equals(id))
					return position;
			}
		}
		
		return null;
	}

	public ResultMessage deletePosition(String transitId, String id) {
		Transit transitHelper=new Transit();
		List<TransitVO> transits=transitHelper.observeAllTransit();
		
		TransitVO transitVO=null;
			
		for (TransitVO transit : transits) {
			if (transit.getId().equals(transitId)) {
				transitVO = transit;
				break;
			}
		}
		

		if (transitVO == null)
			return new ResultMessage(Result.FAIL, "找不到所属的中转中心~");

		List<PositionVO> positions = transitVO.getPositions();
		for (int i = 0; i < positions.size(); i++) {
			PositionVO position = positions.get(i);
			if (position.getId().equals(id)) {
				positions.remove(i);
				break;
			}
		}

		ResultMessage message = transitHelper.updateTransit(transitVO);
		

		return message;
	}

	private boolean isValidPosition(String id) {
		if (!ValidHelper.isNumber(id))
			return false;
		if (id.length() != 6 && id.length() != 7)
			return false;

		return true;
	}
	
	
}
