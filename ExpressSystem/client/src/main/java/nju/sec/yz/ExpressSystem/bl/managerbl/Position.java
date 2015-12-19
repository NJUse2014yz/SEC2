package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.accountbl.Initialable;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class Position implements Initialable<PositionVO, PositionPO>{

	/**
	 * 向所属中转中心中添加营业厅
	 */
	public ResultMessage addPosition(PositionVO av) {

		if (!isValidPosition(av.getId()))
			return new ResultMessage(Result.FAIL, "亲，咱们营业厅编号是城市编码加三位数哟~");
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
		
		if(!isValidPositionAndTransit(av.id,av.transitId))
			return new ResultMessage(Result.FAIL, "亲，营业厅编号跟中转中心id不对应哟");
		
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

	boolean isValidPosition(String id) {
		if (!ValidHelper.isNumber(id))
			return false;
		if (id.length() != 6 && id.length() != 7)
			return false;

		return true;
	}
	
	private boolean isValidPositionAndTransit(String id, String transitId) {
		if(!id.substring(0, 3).equals(transitId.substring(0, 3)))
			return false;
		return true;
	}

	@Override
	public ResultMessage init(List<PositionVO> vos) {
		ResultMessage message=new ResultMessage(Result.SUCCESS);
		for(int i=0;i<vos.size();i++){
			PositionVO position=vos.get(i);
			message=this.addPosition(position);
			if(message.getResult()==Result.FAIL)
				System.out.println(message.getMessage());
				return new ResultMessage(Result.FAIL,i+" "+message.getMessage());
		}
		
		return message;
	}

	@Override
	public PositionVO show(PositionPO po) {
		PositionVO vo=new PositionVO(po.getName(), po.getId(), po.getTransitId(), po.getLocation());
		return vo;
	}

	@Override
	public PositionPO changeVOToPO(PositionVO vo) {
		PositionPO po=new PositionPO(vo.name, vo.id, vo.transitId, vo.location);
		return po;
	}
	
	
}
