package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class DeliverReceiveInUi extends JPanel{

	DeliverBlService deliverservice=new DeliverController();
	
	
	
	public DeliverReceiveInUi(ClientControler controler) {
		iniDeliverReceiveIn();
		ButtonComponents bc=new ButtonComponents(controler, this);
		
	}

	private void iniDeliverReceiveIn() {
		
	}
	
	

	@Override
	public void paintComponent(Graphics g) {
		Image img01 = new ImageIcon("graphic/deliver/background/background04.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

	
	
	
}
