package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import componentui.MessageDeclare;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;

public class PositionMainUi extends JPanel{
	ClientControler mainControler;
	PositionControler controler;
	ButtonComponents bc;

	public PositionMainUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		initPositionMainUi();
	}

	private void initPositionMainUi() {
		bc.changePanel(this);
		bc.init();
		setLayout(null);
		setSize(490, 550);
		setVisible(true);


		//显示消息列表
		MessageDeclare message=new MessageDeclare(this);
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/main_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}


