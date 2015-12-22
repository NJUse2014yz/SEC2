package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class ManagerMainUi extends JPanel {
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	public ManagerMainUi(ClientControler maincontroler,ManagerButtonComponent mbc){
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		iniManagerMain();
	}
	
	private void iniManagerMain() {
//		ManagerButtonComponent mbc=new ManagerButtonComponent(maincontroler, this);
		mbc.changePanel(this,0);
		mbc.init();
		
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		
	}
	
	




@Override
public void paintComponent(Graphics g) {

	Image img01 = new ImageIcon("graphic/manager/background/background01.png").getImage();

	g.drawImage(img01, 0, 0, 490, 550, null);

}

	
}
