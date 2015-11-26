package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class AccountMainUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	
	public AccountMainUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.init();
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/main_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}