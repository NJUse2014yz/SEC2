package nju.sec.yz.ExpressSystem.blservice.receiptBlService;

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
	 * 前置条件	用户确定已读消息
	 * 后置条件	删除消息
	 */
	public void hasRead(String messageId);
	
}
