package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;

public class PositionMainUi extends JPanel{
	ClientControler mainControler;
	PositionControler controler;

	public PositionMainUi(ClientControler mainControler){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		
		initDeliverMainUi();
		ButtonComponents bc=new ButtonComponents(mainControler, this);
	}

	private void initDeliverMainUi() {
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/main_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}


