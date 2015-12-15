package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class newJBut extends JButton {
Color forecolor;
Color aftercolor;
Color generalcolor;
	protected newJBut() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected newJBut(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	//设置按钮无框但有下划线，写入名称和前后颜色
	public newJBut(String text,Color fore,Color after){
		this.forecolor=fore;
		this.aftercolor=after;
		//设为透明
		setContentAreaFilled(false);
		setBorderPainted(false);
		Font font=new Font("Microsoft YaHei",Font.PLAIN,15);
		setFont(font);
		
		setText("<html><u>"+text+"</u></html>");
		setFocusPainted(false);
		setForeground(forecolor);
		//设置横线
		
		 addMouseListener(new MouseAdapter() {  
	            
	            @Override  
	            public void mouseClicked(MouseEvent e) {  
//	            	forecolor=aftercolor;
	            	setForeground(aftercolor);
	            	setBorderPainted(false);
	                repaint();  
	            }  
	        });  
	}

}
