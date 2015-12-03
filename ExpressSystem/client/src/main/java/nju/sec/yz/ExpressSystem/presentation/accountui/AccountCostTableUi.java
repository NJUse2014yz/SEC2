package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;

public class AccountCostTableUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private FinanceBlSevice financeBl;
	
	private JLabel in;
	private JLabel out;
	private JLabel get;
	private JButton excle;
	
	private static final int x=189;
	private static final int in_y=75;
	private static final int out_y=99;
	private static final int get_y=126;
	private static final int w=90;
	private static final int h=20;
	private static final int excle_x=362;
	private static final int excle_y=169;
	private static final int excle_w=79;
	private static final int excle_h=24;
	
	private ImageIcon excleIcon=new ImageIcon("graphic/account/button/excle_button.jpg");
	public AccountCostTableUi(ClientControler mainControler,AccountButtonComponents bc){
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
		
		in=new JLabel();
		in.setBounds(x, in_y, w, h);
		in.setFont(new Font("Dialog", 1, 15));
		in.setForeground(Color.white);
		add(in);
		
		out=new JLabel();
		out.setBounds(x, out_y, w, h);
		out.setFont(new Font("Dialog", 1, 15));
		out.setForeground(Color.white);
		add(out);
		
		get=new JLabel();
		get.setBounds(x, get_y, w, h);
		get.setFont(new Font("Dialog", 1, 15));
		get.setForeground(Color.white);
		add(get);
		
		excle=new JButton(excleIcon);
		excle.setBounds(excle_x, excle_y, excle_w, excle_h);
		add(excle);
		
		ProfitVO pvo=financeBl.makeCostReceipt();
		if(pvo!=null){
			in.setText(Double.toString(pvo.in));
			out.setText(Double.toString(pvo.out));
			get.setText(Double.toString(pvo.profit));
			excle.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e)
				{
//					financeBl.exportCostToExcel();
				}
			});
		
		}
		else
		{

		}
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/cost_table_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
