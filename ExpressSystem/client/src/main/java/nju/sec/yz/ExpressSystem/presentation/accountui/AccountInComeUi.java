package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class AccountInComeUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	
	private DateChooser date;
	private JComboBox choose;
	private JTable table;
	private JScrollPane scroll;
	private JButton back;
	
	private static int date_x=184;
	private static int date_y=67;
	private static int choose_x=145;
	private static int choose_y=95;
	private static int choose_w=265;
	private static int choose_h=20;
	private static int scroll_x=145;
	private static int scroll_y=95;
	private static int scroll_w=265;
	private static int scroll_h=20;
	
	public AccountInComeUi(ClientControler mainControler,ButtonComponents bc){
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

		Image img01 = new ImageIcon("graphic/account/background/income_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
