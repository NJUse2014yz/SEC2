package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;

public class PositionPayUi extends JPanel{
	ClientControler mainControler;
	PositionControler controler;
	ButtonComponents bc;

	public PositionPayUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/pay_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}