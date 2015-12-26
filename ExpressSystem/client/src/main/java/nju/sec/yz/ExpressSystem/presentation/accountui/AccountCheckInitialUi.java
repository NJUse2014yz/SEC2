package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.InitialController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControl;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InitialVO;

public class AccountCheckInitialUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
//	private AccountControler controler;
	private InitialBlService initialBl;
	private InitialVO iv;
	
	private newJBut buttonAG;
	private newJBut buttonS;
	private newJBut buttonC;
	private newJBut buttonA;
	private newJBut buttonI;
	
	private newTable tableT;
	private newTable tableP;
	private newTable tableS;
	private newTable tableC;
	private newTable tableA;
	private newTable tableI;
	
	private newJCombo choose;
	private newJBut backI;
	private newJBut confirm;
	private newJLabel warningT;
	private newJLabel warningS;
	private newJLabel warningC;
	private newJLabel warningA;
	private newJLabel warningI;
	private newJLabel warning;
	
	private String[] power=new String[]{"总经理","高级财务人员","低级财务人员","中转中心业务员","中转中心仓库管理人员","营业厅业务员","快递员","管理员"};
	private Vector<Vector<String>> dataT=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataP=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataS=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataC=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataA=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataI=new Vector<Vector<String>>();
	private Vector<String> nameT=new Vector<String>();
	private Vector<String> nameP=new Vector<String>();
	private Vector<String> nameS=new Vector<String>();
	private Vector<String> nameC=new Vector<String>();
	private Vector<String> nameA=new Vector<String>();
	private Vector<String> nameI=new Vector<String>();
	private String num;
	private String[] years;
	
	private static final int backI_x=390;
	private static final int backI_y=435;
	private static final int backI_w=72;
	private static final int backI_h=24;
	private static final int year_x=170;
	private static final int year_y=55;
	private static final int year_w=90;
	private static final int year_h=20;
	private static final int scroll_x=146;
	private static final int scroll_y=113;
	private static final int scroll_w=300;
	private static final int scroll_h=220;
	private static final int scrollT_x=146;
	private static final int scrollT_y=113;
	private static final int scrollT_w=300;
	private static final int scrollT_h=110;
	private static final int scrollP_x=146;
	private static final int scrollP_y=223;
	private static final int scrollP_w=300;
	private static final int scrollP_h=110;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=20;
	private static final int button_x=170;
	private static final int button_y=80;
	private static final int button_w=45;
	private static final int button_h=24;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	private ImageIcon AGIcon=new ImageIcon("graphic/account/button/button_AG.jpg");
	private ImageIcon SIcon=new ImageIcon("graphic/account/button/button_S.jpg");
	private ImageIcon CIcon=new ImageIcon("graphic/account/button/button_C.jpg");
	private ImageIcon ACIcon=new ImageIcon("graphic/account/button/button_AC.jpg");
	private ImageIcon IIcon=new ImageIcon("graphic/account/button/button_I.jpg");
	private ImageIcon backIcon=new ImageIcon("graphic/account/button/backI_button.gif");
	
	public AccountCheckInitialUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
//		controler=mainControler.accountControler;
		this.bc=bc;
		initialBl=new InitialController();
		List<String> yearT=initialBl.getDates();
		int n=yearT.size();
		System.out.println(n);
		years=new String[n];
		for(int i=0;i<n;i++)
		{
			years[i]=yearT.get(i);
		}
		choose=new newJCombo(years);
		
		nameT.add("名称");
		nameT.add("编号");
		nameT.add("所在地");
		nameP.add("名称");
		nameP.add("编号");
		nameP.add("所在地");
		nameP.add("所属中转中心");
		nameS.add("姓名");
		nameS.add("编号");
		nameS.add("职位");
		nameS.add("所属机构");
		nameC.add("车辆代号");
		nameC.add("车牌号");
		nameC.add("服役时间");
		nameC.add("发动机号");
		nameC.add("购买时间");
		nameC.add("底盘号");
		nameA.add("名称");
		nameA.add("余额");
		nameI.add("id");
		nameI.add("中转中心编号");
		nameI.add("入库时间");
		nameI.add("目的地");
		nameI.add("区号");
		nameI.add("排号");
		nameI.add("架号");
		nameI.add("位号");
		
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
//		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		tableT=new newTable(dataT,nameT,this,false);
		tableT.setBounds(scrollT_x, scrollT_y, scrollT_w, scrollT_h);
		tableT.setVisible(false);
		tableT.join();
		
		tableP=new newTable(dataP,nameP,this,false);
		tableP.setBounds(scrollP_x, scrollP_y, scrollP_w, scrollP_h);
		tableP.setVisible(false);
		tableP.join();
		
		tableS=new newTable(dataS,nameS,this,false);
		tableS.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableS.setVisible(false);
		tableS.join();
		
		tableC=new newTable(dataC,nameC,this,false);
		tableC.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableC.stopAutoRewidth();
		tableC.setVisible(false);
		tableC.join();
		
		tableA=new newTable(dataA,nameA,this,false);
		tableA.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableA.setVisible(false);
		tableA.join();
		
		tableI=new newTable(dataI,nameI,this,false);
		tableI.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableI.stopAutoRewidth();
		tableI.setVisible(false);
		tableI.join();
		
		choose.setBounds(year_x,year_y,year_w,year_h);
		choose.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				num=years[choose.getSelectedIndex()];
				iv=initialBl.observeInitial(num);
				changeData(iv);
				tableT.resetData();
				tableP.resetData();
				tableS.resetData();
				tableC.resetData();
				tableA.resetData();
				tableI.resetData();
			}
			
		});
		add(choose);
		
		backI=new newJBut("返回");
		backI.setBounds(backI_x, backI_y, backI_w, backI_h);
		backI.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				mainControler.accountControler.accountChangePanel(AccountControl.INITIAL);
			}
		});
		add(backI);
		
		buttonAG=new newJBut("机构",Color.white);
		buttonAG.setMargin(new java.awt.Insets(0,0,0,0));
		buttonAG.setBounds(button_x, button_y,button_w,button_h);
		buttonAG.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				tableT.setVisible(true);
				tableP.setVisible(true);
				tableS.setVisible(false);
				tableC.setVisible(false);
				tableA.setVisible(false);
				tableI.setVisible(false);
			}
		});
		add(buttonAG);
		
		buttonS=new newJBut("人员",Color.white);
		buttonS.setBounds(button_x+(button_w+5), button_y,button_w,button_h);
		buttonS.setMargin(new java.awt.Insets(0,0,0,0));
		buttonS.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				tableT.setVisible(false);
				tableP.setVisible(false);
				tableS.setVisible(true);
				tableC.setVisible(false);
				tableA.setVisible(false);
				tableI.setVisible(false);
			}
		});
		add(buttonS);
		
		buttonC=new newJBut("车辆",Color.white);
		buttonC.setMargin(new java.awt.Insets(0,0,0,0));
		buttonC.setBounds(button_x+2*(button_w+5), button_y,button_w,button_h);
		buttonC.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				tableT.setVisible(false);
				tableP.setVisible(false);
				tableS.setVisible(false);
				tableC.setVisible(true);
				tableA.setVisible(false);
				tableI.setVisible(false);
			}
		});
		add(buttonC);
		
		buttonA=new newJBut("账户",Color.white);
		buttonA.setMargin(new java.awt.Insets(0,0,0,0));
		buttonA.setBounds(button_x+3*(button_w+5), button_y,button_w,button_h);
		buttonA.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				tableT.setVisible(false);
				tableP.setVisible(false);
				tableS.setVisible(false);
				tableC.setVisible(false);
				tableA.setVisible(true);
				tableI.setVisible(false);
			}
		});
		add(buttonA);
		
		buttonI=new newJBut("库存",Color.white);
		buttonI.setMargin(new java.awt.Insets(0,0,0,0));
		buttonI.setBounds(button_x+4*(button_w+5), button_y,button_w,button_h);
		buttonI.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				tableT.setVisible(false);
				tableP.setVisible(false);
				tableS.setVisible(false);
				tableC.setVisible(false);
				tableA.setVisible(false);
				tableI.setVisible(true);
			}
		});
		add(buttonI);
		
		warning=new newJLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		setVisible(true);
	}
	public String power(Status p){
		String pow="管理员";
		for(int i=0;i<tableS.getRowCount();i++)
		{
			if(p.equals(Status.MANAGER))
			{
				pow="总经理";
			}
			else if(p.equals(Status.SENIOR_ACCOUNTANCY))
			{
				pow="高级财务人员";
			}
			else if(p.equals(Status.JUNIOR_ACCOUNTANCY))
			{
				pow="低级财务人员";
			}
			else if(p.equals(Status.TRANSIT))
			{
				pow="中转中心业务员";
			}
			else if(p.equals(Status.INVENTORY))
			{
				pow="中转中心仓库管理人员";
			}
			else if(p.equals(Status.POSITION))
			{
				pow="营业厅业务员";
			}
			else if(p.equals(Status.DELIVER))
			{
				pow="快递员";
			}
			else if(p.equals(Status.ADMINISTRATOR))
			{
				pow="管理员";
			}
		}
		return pow;
	}
	private void changeData(InitialVO iv)
	{
		dataA.removeAllElements();
		dataC.removeAllElements();
		dataI.removeAllElements();
		dataP.removeAllElements();
		dataS.removeAllElements();
		dataT.removeAllElements();
		for(int i=0;i<iv.transits.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(iv.transits.get(i).name);
			vector.add(iv.transits.get(i).id);
			vector.add(iv.transits.get(i).location);
			dataT.add(vector);
		}
		
		for(int i=0;i<iv.transits.size();i++)
		{
			for(int j=0;j<iv.transits.get(i).positions.size();i++)
			{
				Vector<String> vector=new Vector<String>();
				vector.add(iv.transits.get(i).positions.get(j).name);
				vector.add(iv.transits.get(i).positions.get(j).id);
				vector.add(iv.transits.get(i).positions.get(j).location);
				vector.add(iv.transits.get(i).positions.get(j).transitId);
				dataP.add(vector);
			}
		}
		
		for(int i=0;i<iv.staffs.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(iv.staffs.get(i).getName());
			vector.add(iv.staffs.get(i).getId());
			vector.add(power(iv.staffs.get(i).getPower()));
			vector.add(iv.staffs.get(i).getAgency());
			dataS.add(vector);
		}
		
		for(int i=0;i<iv.staffs.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(iv.cars.get(i).getId());
			vector.add(iv.cars.get(i).getNumber());
			vector.add(iv.cars.get(i).getBuytime());
			vector.add(iv.cars.get(i).getMechine());
			vector.add(iv.cars.get(i).getDipan());
			vector.add(Integer.toString(iv.cars.get(i).getWorktime()));
			dataC.add(vector);
		}
		
		for(int i=0;i<iv.accounts.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(iv.inventories.get(i).getId());
			vector.add(iv.inventories.get(i).getInventoryInInformation().getTransit());
			vector.add(iv.inventories.get(i).getInventoryInInformation().getTime());
			vector.add(iv.inventories.get(i).getInventoryInInformation().getDestination());
			vector.add(Integer.toString(iv.inventories.get(i).getInventoryInInformation().getBlock()));
			vector.add(Integer.toString(iv.inventories.get(i).getInventoryInInformation().getRow()));
			vector.add(Integer.toString(iv.inventories.get(i).getInventoryInInformation().getShelf()));
			vector.add(Integer.toString(iv.inventories.get(i).getInventoryInInformation().getPositon()));
		}
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/initial_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}

