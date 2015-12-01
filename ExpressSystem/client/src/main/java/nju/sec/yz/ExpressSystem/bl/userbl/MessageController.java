package nju.sec.yz.ExpressSystem.bl.userbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.userBlService.MessageBlService;
import nju.sec.yz.ExpressSystem.vo.MessageVO;
/**
 * 处理消息的获取
 * @author 周聪
 *
 */
public class MessageController implements MessageBlService{

	@Override
	public List<MessageVO> getNewMessages() {
		UserMessage message=new UserMessage();
		List<MessageVO> messages=message.getNewMessages();
		return messages;
	}

	@Override
	public void hasRead(String messageId) {
		UserMessage message=new UserMessage();
		message.hasRead(messageId);
	}

}
