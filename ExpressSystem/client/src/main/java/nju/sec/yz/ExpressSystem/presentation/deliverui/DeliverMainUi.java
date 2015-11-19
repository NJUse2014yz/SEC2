package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverSwitchPanelListener;

public class DeliverMainUi extends JPanel {

	private ClientControler mainControler;
	private DeliverControler controler;

	
	public DeliverMainUi(ClientControler mainControler){
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

		Image img01 = new ImageIcon("graphic/deliver/background/background01.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
