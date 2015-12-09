package componentui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import nju.sec.yz.ExpressSystem.bl.receiptbl.MessageController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.MessageBlService;
import nju.sec.yz.ExpressSystem.vo.MessageVO;

public class MessageDeclare {

	private JTextArea announce;
	private JButton confirm;
	private JScrollPane jsc;
	
	private MessageBlService message=new MessageController();
	
	private ArrayList<MessageVO> volist;
			
	public MessageDeclare(JPanel panel){
		
		volist=(ArrayList<MessageVO>) message.getNewMessages();
		
		announce=new JTextArea(volist.size(),21);
		
		for(int c=0;c<volist.size();c++){
			announce.append("单据"+volist.get(c).messageId+"已经通过审批\r\n");
		}
		
//		announce=new JTextArea(21,21);
//		announce.append("aaaaaa\r\n");
//		announce.append("bbbbbb\r\n");
//		announce.append("cccccc\r\n");
//		announce.append("dddddd\r\n");
//		announce.append("eeeeee\r\n");
//		
		
		
		
//		announce.setLineWrap(true);// 激活自动换行功能
//		announce.setWrapStyleWord(true);// 激活断行不断字功能
		
		jsc=new JScrollPane(announce);
		jsc.setBounds(154,84,292,180);
		panel.add(jsc);
		
		ImageIcon IknowIcon=new ImageIcon("graphic/common/Iknow.png");
		confirm=new JButton(IknowIcon);
		confirm.setBounds(374,270,72,24);
		panel.add(confirm);
		
		confirm.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				for(int c=0;c<volist.size();c++){
					message.hasRead(volist.get(c).messageId);
				}
			}
		});
	}
}
