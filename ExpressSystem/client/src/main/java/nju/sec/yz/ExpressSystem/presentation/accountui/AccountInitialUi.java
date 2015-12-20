package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.InitialController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ACCOUNT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class AccountInitialUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
//	private AccountControler controler;
	private InitialBlService initialBl;
	
	private boolean P=false;
	private boolean T=false;
	private boolean S=false;
	private boolean C=false;
	private boolean A=false;
	private boolean I=false;
	
//	private JButton check;
	private newJBut check;
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
	
	private newJBut confirm;
	private newJLabel warningT;
	private newJLabel warningS;
	private newJLabel warningC;
	private newJLabel warningA;
	private newJLabel warningI;
	private newJLabel warning;
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
	
	private String[] power=new String[]{"总经理","高级财务人员","低级财务人员","中转中心业务员","中转中心仓库管理人员","营业厅业务员","快递员","管理员"};
	private String num;
	
	private static final int check_x=418;
	private static final int check_y=59;
	private static final int check_w=50;
	private static final int check_h=22;
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
	private static final int confirm_x=390;
	private static final int confirm_y=435;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warningT_x=198;
	private static final int warningT_y=450;
	private static final int warningT_w=275;
	private static final int warningT_h=20;
	private static final int warningS_x=198;
	private static final int warningS_y=470;
	private static final int warningS_w=275;
	private static final int warningS_h=20;
	private static final int warningC_x=198;
	private static final int warningC_y=490;
	private static final int warningC_w=275;
	private static final int warningC_h=20;
	private static final int warningI_x=198;
	private static final int warningI_y=510;
	private static final int warningI_w=275;
	private static final int warningI_h=20;
	private static final int warningA_x=198;
	private static final int warningA_y=530;
	private static final int warningA_w=275;
	private static final int warningA_h=20;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=20;
	private static final int button_x=170;
	private static final int button_y=60;
	private static final int button_w=45;
	private static final int button_h=24;
	
//	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
//	private ImageIcon AGIcon=new ImageIcon("graphic/account/button/button_AG.jpg");
//	private ImageIcon SIcon=new ImageIcon("graphic/account/button/button_S.jpg");
//	private ImageIcon CIcon=new ImageIcon("graphic/account/button/button_C.jpg");
//	private ImageIcon ACIcon=new ImageIcon("graphic/account/button/button_AC.jpg");
//	private ImageIcon IIcon=new ImageIcon("graphic/account/button/button_I.jpg");
	private ImageIcon checkIcon=new ImageIcon("graphic/account/button/check_button.gif");
	
	public AccountInitialUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
//		controler=mainControler.accountControler;
		this.bc=bc;
		initialBl=new InitialController();

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
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		
		buttonAG=new newJBut("机构",Color.white);
		buttonAG.setBounds(button_x, button_y,button_w,button_h);
		buttonAG.setMargin(new java.awt.Insets(0,0,0,0));
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
		
//		check=new JButton(checkIcon);
//		check.setBounds(check_x, check_y, check_w, check_h);
		check=new newJBut("查看");
		check.setBounds(confirm_x-90, confirm_y, confirm_w, confirm_h);
		check.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				mainControler.accountControler.accountChangePanel(ACCOUNT_CONTROL.CHECK_INITIAL);
				System.out.println("checkInitial");
			}
		});
		add(check);
		
		tableT=new newTable(dataT,nameT,this,true);
		tableT.setBounds(scrollT_x, scrollT_y, scrollT_w, scrollT_h);
		tableT.setVisible(false);
		tableT.initialBlank(nameT.size());
		tableT.join();
		
		
		tableP=new newTable(dataP,nameP,this,true);
		tableP.setBounds(scrollP_x, scrollP_y, scrollP_w, scrollP_h);
		tableP.setVisible(false);
		tableP.initialBlank(nameP.size());
		tableP.join();
		
		tableS=new newTable(dataS,nameS,this,true);
		tableS.setJComboBox(new JComboBox(power), 2);
		tableS.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		tableS.setVisible(false);
		tableS.initialBlank(nameS.size());
		tableS.join();
		
		tableC=new newTable(dataC,nameC,this,true);
		tableC.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableC.stopAutoRewidth();
		tableC.setVisible(false);
		tableC.initialBlank(nameC.size());
		tableC.join();
		
		tableA=new newTable(dataA,nameA,this,true);
		tableA.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableA.setVisible(false);
		tableA.initialBlank(nameA.size());
		tableA.join();
		
		tableI=new newTable(dataI,nameI,this,true);
		tableI.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		tableI.stopAutoRewidth();
		tableI.setVisible(false);
		tableI.initialBlank(nameI.size());
		tableI.join();
		
		
		warningT=new newJLabel();
		warningT.setBounds(warningT_x, warningT_y, warningT_w, warningT_h);
		warningT.setForeground(Color.red);
		add(warningT);
		warningT.setVisible(false);
		
		warningS=new newJLabel();
		warningS.setBounds(warningS_x, warningS_y, warningS_w, warningS_h);
		warningS.setForeground(Color.red);
		add(warningS);
		warningS.setVisible(false);
		
		warningC=new newJLabel();
		warningC.setBounds(warningC_x, warningC_y, warningC_w, warningC_h);
		warningC.setForeground(Color.red);
		add(warningC);
		warningC.setVisible(false);
		
		warningI=new newJLabel();
		warningI.setBounds(warningI_x, warningI_y, warningI_w, warningI_h);
		warningI.setForeground(Color.red);
		add(warningI);
		warningI.setVisible(false);
		
		warningA=new newJLabel();
		warningA.setBounds(warningA_x, warningA_y, warningA_w, warningA_h);
		warningA.setForeground(Color.red);
		add(warningA);
		warningA.setVisible(false);
		
		warning=new newJLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result;
				warningT.setVisible(false);
				warningS.setVisible(false);
				warningC.setVisible(false);
				warningI.setVisible(false);
				warningA.setVisible(false);
				warning.setVisible(false);
				
				List<PositionVO> pvl=new ArrayList<PositionVO>();
				for(int i=0;i<tableP.getRowCount();i++)
				{	String a=tableP.getModel().getValueAt(i,0).toString();
					String b=tableP.getModel().getValueAt(i,1).toString();
					String c=tableP.getModel().getValueAt(i,3).toString();
					String d=tableP.getModel().getValueAt(i,2).toString();
					if(!a.equals("")&&!b.equals("")&&!c.equals("")&&!d.equals(""))
					{
						PositionVO pv=new PositionVO(a,b,c,d);
						pvl.add(pv);
					}
				}
				if(!T)
				{
					List<TransitVO> tvl=new ArrayList<TransitVO>();
					for(int i=0;i<tableT.getRowCount();i++)
					{
						List<PositionVO> tpvl=new ArrayList<PositionVO>();
						for(int j=0;j<pvl.size();j++)
						{
							if(pvl.get(j).getTransitId().equals(tableT.getModel().getValueAt(i, 1)))
							{
								tpvl.add(pvl.get(j));
							}
						}
						String a=tableT.getValueAt(i,0,false).toString();
						String b=tableT.getValueAt(i,1,false).toString();
						String d=tableT.getValueAt(i,2,false).toString();
						if(!a.equals("")&&!b.equals("")&&!d.equals(""))
						{
							TransitVO tv=new TransitVO(a,b,tpvl,d);
							tvl.add(tv);
						}
					}
						result=initialBl.addTransit(tvl);
						if(result.getResult()==Result.SUCCESS)
						{
							tableT.setVisible(false);
							tableP.setVisible(false);
							buttonAG.setVisible(false);
							warningT.setText("中转中心初始化成功");
							T=true;
						}
						else
						{
							warningT.setText(result.getMessage());
						}
					warningT.setVisible(true);
					repaint();
				}
				
				if(!S)
				{
					List<StaffVO> svl=new ArrayList<StaffVO>();
					for(int i=0;i<tableS.getRowCount();i++)
					{
						Status pow=Status.ADMINISTRATOR;
						String p=tableS.getValueAt(i, 2, true);
						if(p.equals(power[0]))
						{
							pow=Status.MANAGER;
						}
						else if(p.equals(power[1]))
						{
							pow=Status.SENIOR_ACCOUNTANCY;
						}
						else if(p.equals(power[2]))
						{
							pow=Status.JUNIOR_ACCOUNTANCY;
						}
						else if(p.equals(power[3]))
						{
							pow=Status.TRANSIT;
						}
						else if(p.equals(power[4]))
						{
							pow=Status.INVENTORY;
						}
						else if(p.equals(power[5]))
						{
							pow=Status.POSITION;
						}
						else if(p.equals(power[6]))
						{
							pow=Status.DELIVER;
						}
						else if(p.equals(power[7]))
						{
							pow=Status.ADMINISTRATOR;
						}
						String a=tableS.getValueAt(i,0,false).toString();
						String b=tableS.getValueAt(i,1,false).toString();
						String d=tableS.getValueAt(i,3,false).toString();
						if(!a.equals("")&&!b.equals("")&&!d.equals(""))
						{
							StaffVO sv=new StaffVO(a,b,pow,d,null);
							svl.add(sv);
						}
					}
						result=initialBl.addStaff(svl);
						if(result.getResult()==Result.SUCCESS)
						{
							tableS.setVisible(false);
							buttonS.setVisible(false);
							warningS.setText("人员初始化成功");
							S=true;
						}
						else
						{
							warningS.setText(result.getMessage());
						}
					warningS.setVisible(true);
					repaint();
				}
				
				if(!C)
				{
					List<CarVO> cvl=new ArrayList<CarVO>();
					for(int i=0;i<tableC.getRowCount();i++)
					{
						String a=tableC.getValueAt(i,0,false).toString();
						String b=tableC.getValueAt(i,1,false).toString();
						String c=tableC.getValueAt(i,2,false).toString();
						String d=tableC.getValueAt(i,3,false).toString();
						String e2=tableC.getValueAt(i,4,false).toString();
						if(!a.equals("")&&!b.equals("")&&!c.equals("")&&!d.equals("")&&!e2.equals(""))
						{
							CarVO cv=new CarVO(a,b,c,d,e2);
							cvl.add(cv);
						}
					}
						result=initialBl.addCar(cvl);
						if(result.getResult()==Result.SUCCESS)
						{
							tableC.setVisible(false);
							buttonC.setVisible(false);
							warningC.setText("车辆初始化成功");
							C=true;
						}
						else
						{
							warningC.setText(result.getMessage());
						}
					warningC.setVisible(true);
					repaint();
				}
				
				if(!I)
				{
					List<InventoryInSheetVO> iisvl=new ArrayList<InventoryInSheetVO>();
					for(int i=0;i<tableI.getRowCount();i++)
					{
						String a=tableI.getValueAt(i,2,false).toString();
						String b=tableI.getValueAt(i,3,false).toString();
						String c=tableI.getValueAt(i,4,false).toString();
						String d=tableI.getValueAt(i,5,false).toString();
						String e1=tableI.getValueAt(i,6,false).toString();
						String f=tableI.getValueAt(i,7,false).toString();
						String g=tableI.getValueAt(i,1,false).toString();
						String h=tableI.getValueAt(i,0,false).toString();
						if(!a.equals("")&&!b.equals("")&&!c.equals("")&&!d.equals("")&&!e1.equals("")&&!f.equals("")&&!g.equals("")&&!h.equals(""))
						{
							InventoryInInformation iii=new InventoryInInformation(a,b,Integer.parseInt(c),Integer.parseInt(d),Integer.parseInt(e1),Integer.parseInt(f),g);
							InventoryInSheetVO iisv=new InventoryInSheetVO(iii,h);
							iisvl.add(iisv);
						}
					}
						result=initialBl.addStock(iisvl);
						if(result.getResult()==Result.SUCCESS)
						{
							tableI.setVisible(false);
							buttonI.setVisible(false);
							warningI.setText("库存初始化成功");
							I=true;
						}
						else
						{
							warningI.setText(result.getMessage());
						}
					warningI.setVisible(true);
					repaint();
				}
				if(!A)
				{
					List<AccountVO> acvl=new ArrayList<AccountVO>();
					for(int i=0;i<tableA.getRowCount();i++)
					{
						String a=tableA.getValueAt(i,0,false).toString();
						String b=tableA.getValueAt(i,1,false).toString();
						if(!a.equals("")&&!b.equals(""))
						{
							AccountVO acv=new AccountVO(a,Double.parseDouble(b));
							acvl.add(acv);
						}
					}
						result=initialBl.addAccount(acvl);
						if(result.getResult()==Result.SUCCESS)
						{
							tableA.setVisible(false);
							buttonA.setVisible(false);
							warningA.setText("账户初始化成功");
							A=true;
						}
						else
						{
							warningA.setText(result.getMessage());
						}
					}
					warningA.setVisible(true);
					repaint();
				if(T&&S&&C&&A&&I)
				{
					result=initialBl.finish();
					warningT.setVisible(false);
					warningS.setVisible(false);
					warningC.setVisible(false);
					warningI.setVisible(false);
					warningA.setVisible(false);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("期初建账成功");
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
					repaint();
				}
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
