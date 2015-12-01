package nju.sec.yz.ExpressSystem.bl.userbl;

import nju.sec.yz.ExpressSystem.vo.MessageVO;

/**
 * 发送消息接口
 * @author 周聪
 *
 */
public interface MessageSendService {

	public void send(MessageVO vo);
	
}
