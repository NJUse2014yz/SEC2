package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.MessageDeclare;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.TransitControler;
import nju.sec.yz.ExpressSystem.presentation.deliverui.DeliverButtonComponents;

/*
 * zhangqi 2015.11.21
 */
public class TransitMainUi extends JPanel{
	ClientControler mainControler;
	TransitControler controler;
	TransitButtonComponents tbc;
		public TransitMainUi(ClientControler mainControler,TransitButtonComponents tbc) {
			super();
			this.mainControler=mainControler;
			controler=mainControler.transitControler;
			this.tbc=tbc;
			tbc.setNextPanel(this);
			tbc.iniTransit();
			initTransitMainUi();
			
			
		}
		private void initTransitMainUi() {
			setLayout(null);
			setSize(490, 550);
			setVisible(true);

			//显示消息列表
			MessageDeclare message=new MessageDeclare(this);
		}
		
		@Override
		public void paintComponent(Graphics g) {

			Image img01 = new ImageIcon("graphic/transit/background/background01.png").getImage();

			g.drawImage(img01, 0, 0, 490, 550, null);

		}

		
}
