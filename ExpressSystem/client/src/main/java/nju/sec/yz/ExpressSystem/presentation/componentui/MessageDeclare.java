package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import nju.sec.yz.ExpressSystem.bl.receiptbl.MessageController;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.MessageBlService;
import nju.sec.yz.ExpressSystem.vo.MessageVO;

public class MessageDeclare {

	private JTextArea announce=new JTextArea();
	private JLabel confirm;
	private JScrollPane jsc;
	
	private MessageBlService message=new MessageController();
	
	private ArrayList<MessageVO> volist;
			
	public MessageDeclare(JPanel panel){
		
		initTextArea();
		
		
		jsc=new newJScroll(announce);
		jsc.setBounds(154,84,292,180);
		jsc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jsc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(jsc);
		
		announce.setVisible(true);
		jsc.setVisible(true);
		
		ImageIcon IknowIcon=new ImageIcon("graphic/common/Iknow.png");
		confirm=new newJLabel("我知道了",Color.YELLOW,Color.RED);
		confirm.setBounds(364,270,100,24);
		panel.add(confirm);
		
		confirm.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				for(int c=0;c<volist.size();c++){
					message.hasRead(volist.get(c).messageId);
				}
				initTextArea();
			}
		});
	}
	
	private void initTextArea(){
		volist=(ArrayList<MessageVO>) message.getNewMessages();
		
		announce.setText("");
		announce.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
		
		announce.setForeground(Color.LIGHT_GRAY);
		
		Font font = new Font("Microsoft YaHei",Font.PLAIN,15);
		announce.setFont(font);
		announce.setOpaque(false);
		for(int c=0;c<volist.size();c++){
			announce.append(volist.get(c).message);
		}
		
		announce.repaint();
		
	}
}
