package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.LogController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.LogVO;

public class AccountChechLogUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private LogBlService logBl;
	
	private DateChooser date;
	private newTable table;
	private newJBut all;
	private ArrayList<LogVO> logs;
	private Vector<Vector<String>> data=new Vector<Vector<String>>(); 
	private Vector<String> name=new Vector<String>();
 	
	
	private static final int date_x=259;
	private static final int date_y=73;
	private static final int scroll_x=142;
	private static final int scroll_y=109;
	private static final int scroll_w=316;
	private static final int scroll_h=184;
	private static final int all_x=390;
	private static final int all_y=71;
	private static final int all_w=72;
	private static final int all_h=24;
	
	private ImageIcon allIcon=new ImageIcon("graphic/account/button/showall_button.jpg");
	
	public AccountChechLogUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		logBl=new LogController();
		name.add("时间");
		name.add("人员");
		name.add("操作");
		logs=logBl.getAll();
		
		table=new newTable(data,name,this,false);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		
		if(logs!=null)
		{
			changeData(logs);
		}
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
//		bc.change();
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
					changeData(logs);
				}
			}
		});

		
		all=new newJBut("确定");
		all.setBounds(all_x, all_y, all_w, all_h);
		all.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ArrayList<LogVO> logs=logBl.getByTime(date.getTime());
				if(logs!=null)
				{
					changeData(logs);
				}
			}
		});
		add(all);
		
		setVisible(true);
	}
	private void changeData(List<LogVO> ll)
	{
		data.removeAllElements();
		for(int i=0;i<ll.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(ll.get(i).getTime());
			vector.add(ll.get(i).getPerson());
			vector.add(ll.get(i).getOperation());
			data.add(vector);
		}
		table.resetData();
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/log_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
