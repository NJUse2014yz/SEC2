package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;

public class newJLabel extends JLabel {

	protected newJLabel() {
		super();
		init();
		// TODO Auto-generated constructor stub
	}

	protected newJLabel(String text) {
		super(text);
		init();
		// TODO Auto-generated constructor stub
	}
	
	private void init(){
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		setForeground(Color.LIGHT_GRAY);
	}
	
	
	//针对warning的2个方法
	private void NotFilled(){
		setText("尚未完成对必填项的填写");
		setBounds(198, 490, 463 - 198, 30);
		setForeground(Color.red);
		setVisible(true);
	}
	
	private void Reply(ResultMessage result){
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
