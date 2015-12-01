package nju.sec.yz.ExpressSystem.blservice.userBlService;

import java.util.List;
import nju.sec.yz.ExpressSystem.vo.MessageVO;

/**
 * 从数据层获得消息
 * @author 周聪
 *
 */
public interface MessageBlService {

	/**
	 * 获得当前用户接收的信息
	 */
	public List<MessageVO> getNewMessages();
	
	/**
	 * 标记已读消息
	 */
	public void hasRead(String messageId);
	
}
