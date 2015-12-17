package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class newJText extends JTextField{
	public newJText(){
		iniText();
	}
	
	public newJText(String str){
		super(str);
		iniText();
	}
	
	private void iniText(){
		//画线+去框
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		setForeground(Color.LIGHT_GRAY);
		
		Font font = new Font("Microsoft YaHei",Font.PLAIN,15);
		setFont(font);
		setOpaque(false);
		
	}
	
	
	
	
}
