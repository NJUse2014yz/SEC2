package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InitialVO;

public class AccountInitialUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private FinanceBlSevice financeBl;
	
	private JTable table;
	private JScrollPane scroll;
	private JButton confirm;
	private JLabel warning;
	private String[] name=new String[]{"机构","人员","车辆","库存","银行账户名称","账户余额"};
	private String[][] data=new String[][]{{"","","","","",""}};
	
	private static final int scroll_x=137;
	private static final int scroll_y=74;
	private static final int scroll_w=318;
	private static final int scroll_h=223;
	private static final int confirm_x=382;
	private static final int confirm_y=311;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/confirm_button.jpg");
	
	public AccountInitialUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		financeBl=new FinanceController();
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new JTable(data,name);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll=new JScrollPane(table);
		scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		add(scroll);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
//				InitialVO abv=new InitialVO();
//				abv.
//				ResultMessage result=financeBl.initial(abv);
			}
		});
		add(confirm);
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/initial_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
