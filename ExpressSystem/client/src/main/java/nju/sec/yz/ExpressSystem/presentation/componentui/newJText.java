package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class newJText extends JTextField{
	public newJText(){
		iniText();
	}
	
	public newJText(String str){
		iniText();
	}
	
	private void iniText(){
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setForeground(Color.WHITE);
		setOpaque(false);
		
		addMouseListener(new MouseAdapter(){

			

//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				setBorder(BorderFactory.createLineBorder(Color.WHITE));
//			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			
		});
	}
	
	
	
	
}
