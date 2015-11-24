package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;

public class PositionPayUi extends JPanel{
	ClientControler mainControler;
	PositionControler controler;
	
	ButtonComponents bc;

	JTable payTable;
	JScrollPane scroll;
	
	String[] columnName={"收款日期","收款金额","收款快递员","快递单条形码号"};
	String[][] data;
	
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
