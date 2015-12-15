package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;

public class newJLabel extends JLabel {

	public newJLabel() {
		super();
		init();
		// TODO Auto-generated constructor stub
	}

	public newJLabel(String text) {
		super(text);
		init();
		// TODO Auto-generated constructor stub
	}
	
	
	Color forecolor;
	Color aftercolor;
	
	public newJLabel (String text, Color fore, Color after) {
	// 设置按钮无框但有下划线，写入名称和前后颜色
//		public newJBut
			this.forecolor = fore;
			this.aftercolor = after;
			// 设为透明
//			setContentAreaFilled(false);
//			setBorderPainted(false);
			Font font = new Font("Microsoft YaHei", Font.PLAIN, 15);
			setFont(font);

			setText("<html><u>" + text + "</u></html>");
//			setFocusPainted(false);
			setForeground(forecolor);
			// 设置横线

			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// forecolor=aftercolor;
					setForeground(aftercolor);
//					setBorderPainted(false);
					repaint();
				}
			});
		}
	
	
	private void init(){
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		setForeground(Color.LIGHT_GRAY);
	}
	
	
	//针对warning的2个方法
	public void NotFilled(){
		setText("尚未完成对必填项的填写");
		setBounds(198, 490, 463 - 198, 30);
		setForeground(Color.red);
		setVisible(true);
	}
	
	public void Reply(ResultMessage result){
		// 失败
		setForeground(Color.red);
		if (result.getResult() == Result.FAIL) {

			setText(result.getMessage());
			setBounds(138, 490, 463 - 138, 30);
		} else {
			// 提交成功
			setText("提交成功");
			setBounds(270, 490, 70, 30);
		}
		setVisible(true);
	}
}
