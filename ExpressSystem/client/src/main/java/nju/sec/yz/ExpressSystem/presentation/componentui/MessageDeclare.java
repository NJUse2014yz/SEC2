package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
		announce.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
		
		announce.setForeground(Color.LIGHT_GRAY);
		
		Font font = new Font("Microsoft YaHei",Font.PLAIN,15);
		announce.setFont(font);
		announce.setOpaque(false);
		for(int c=0;c<volist.size();c++){
			announce.append(volist.get(c).message);
		}
		
		jsc=new JScrollPane(announce);
		jsc.setBounds(154,84,292,180);
		jsc.setOpaque(false);
		jsc.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
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
