package nju.sec.yz.ExpressSystem.bl.userbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.userBlService.MessageBlService;
import nju.sec.yz.ExpressSystem.vo.MessageVO;
/**
 * 界面获取消息
 * @author 周聪
 *
 */
public class MessageController implements MessageBlService{

	@Override
	public List<MessageVO> getNewMessages() {
		Message message=new Message();
		List<MessageVO> messages=message.getNewMessages();
		return messages;
	}

	@Override
	public void hasRead(String messageId) {
		Message message=new Message();
		message.hasRead(messageId);
	}

}
