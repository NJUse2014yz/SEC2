package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.accountbl.LogController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.LogVO;

public class AccountChechLogUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private LogBlService logBl;
	
	private DateChooser date;
	private JTable table;
	private JScrollPane scroll;
	private JButton all;
	private String[] name={"时间","人员","操作"};
	private String[][] data={{"","",""}};
	
	private static final int date_x=259;
	private static final int date_y=73;
	private static final int scroll_x=142;
	private static final int scroll_y=109;
	private static final int scroll_w=316;
	private static final int scroll_h=184;
	private static final int all_x=358;
	private static final int all_y=310;
	private static final int all_w=91;
	private static final int all_h=24;
	
	private ImageIcon allIcon=new ImageIcon("graphic/account/button/showall_button.jpg");
	
	public AccountChechLogUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		logBl=new LogController();
		ArrayList<LogVO> logs=logBl.getAll();
		if(logs!=null)
		{
			int l=logs.size();
			data=new String[l][3];
			for(int i=0;i<l;i++)
			{
				data[i][0]=logs.get(i).getTime();
				data[i][1]=logs.get(i).getPerson();
				data[i][2]=logs.get(i).getOperation();
			}
		}
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		date=new DateChooser(this,date_x,date_y);
		date.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				ArrayList<LogVO> logs=logBl.getAll();
				if(logs!=null)
				{
					int l=logs.size();
					data=new String[l][3];
					for(int i=0;i<l;i++)
					{
						data[i][0]=logs.get(i).getTime();
						data[i][1]=logs.get(i).getPerson();
						data[i][2]=logs.get(i).getOperation();
					}
				}
				remove(scroll);
				table=new JTable(data,name);
				table.setRowHeight(20);
				scroll=new JScrollPane(table);
				scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
				add(scroll);
			}
		});
		table=new JTable(data,name);
		table.setRowHeight(20);
		scroll=new JScrollPane(table);
		scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		add(scroll);
		
		all=new JButton(allIcon);
		all.setBounds(all_x, all_y, all_w, all_h);
		all.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				//需要两个DateChooser
				ArrayList<LogVO> logs=logBl.getByTime(date.getTime());
				if(logs!=null)
				{
					int l=logs.size();
					data=new String[l][3];
					for(int i=0;i<l;i++)
					{
						data[i][0]=logs.get(i).getTime();
						data[i][1]=logs.get(i).getPerson();
						data[i][2]=logs.get(i).getOperation();
					}
				}
				remove(scroll);
				table=new JTable(data,name);
				table.setRowHeight(20);
				scroll=new JScrollPane(table);
				scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
				add(scroll);
			}
		});
		add(all);
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/log_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
