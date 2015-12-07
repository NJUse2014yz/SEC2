package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.InitialController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ACCOUNT_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class AccountCheckInitialUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private InitialBlService initialBl;
	private InitialVO iv;
	
	private JButton buttonAG;
	private JButton buttonS;
	private JButton buttonC;
	private JButton buttonA;
	private JButton buttonI;
	
	private JTable tableT;
	private JTable tableP;
	private JTable tableS;
	private JTable tableC;
	private JTable tableA;
	private JTable tableI;
	private DefaultTableModel modelT;
	private DefaultTableModel modelP;
	private DefaultTableModel modelS;
	private DefaultTableModel modelC;
	private DefaultTableModel modelA;
	private DefaultTableModel modelI;
	private JScrollPane scrollT;
	private JScrollPane scrollP;
	private JScrollPane scrollS;
	private JScrollPane scrollC;
	private JScrollPane scrollA;
	private JScrollPane scrollI;
	
	private JComboBox<String> choose;
	private JButton backI;
	private JButton confirm;
	private JLabel warningT;
	private JLabel warningS;
	private JLabel warningC;
	private JLabel warningA;
	private JLabel warningI;
	private JLabel warning;
	
	private String[] power=new String[]{"总经理","高级财务人员","低级财务人员","中转中心业务员","中转中心仓库管理人员","营业厅业务员","快递员","管理员"};
	private String[] nameT=new String[]{"名称","编号","所在地"};
	private String[][] dataT=new String[][]{{"","",""}};
	private String[] nameP=new String[]{"名称","编号","所在地","所属中转中心"};
	private String[][] dataP=new String[][]{{"","","",""}};
	private String[] nameS=new String[]{"姓名","编号","职位","所属机构"};
	private String[][] dataS=new String[][]{{"","","",""}};
	private String[] nameC=new String[]{"车辆代号","车牌号","服役时间","发动机号","购买时间","底盘号"};
	private String[][] dataC=new String[][]{{"","","","","",""}};
	private String[] nameA=new String[]{"名称","余额"};
	private String[][] dataA=new String[][]{{"",""}};
	private String[] nameI=new String[]{"id","中转中心编号","入库时间","目的地","区号","排号","架号","位号"};
	private String[][] dataI=new String[][]{{"","","","","","","",""}};
	private String num;
	private String[] years;
	
	private static final int backI_x=418;
	private static final int backI_y=59;
	private static final int backI_w=50;
	private static final int backI_h=22;
	private static final int year_x=153;
	private static final int year_y=83;
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
	private static final int button_y=50;
	private static final int button_w=45;
	private static final int button_h=34;
	
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
		controler=mainControler.accountControler;
		this.bc=bc;
		initialBl=new InitialController();
		List<String> yearT=initialBl.getDates();
		int n=yearT.size();
		years=new String[n];
		for(int i=0;i<n;i++)
		{
			years[i]=yearT.get(i);
		}
		choose=new JComboBox<String>(years);
		tableT=new JTable(dataT,nameT);
		tableP=new JTable(dataP,nameP);
		tableS=new JTable(dataS,nameS);
		tableC=new JTable(dataC,nameC);
		tableA=new JTable(dataA,nameA);
		tableI=new JTable(dataI,nameI);
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		choose.setBounds(year_x,year_y,year_w,year_h);
		choose.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				num=years[choose.getSelectedIndex()];
				iv=initialBl.observeInitial(num);
				
				dataT=new String[iv.transits.size()][3];
				int l=0;
				for(int i=0;i<iv.transits.size();i++)
				{
					dataT[i][0]=iv.transits.get(i).name;
					dataT[i][1]=iv.transits.get(i).id;
					dataT[i][2]=iv.transits.get(i).location;
					l+=iv.transits.get(i).positions.size();
				}
				
				dataP=new String[l][4];
				for(int i=0;i<iv.transits.size();i++)
				{
					for(int j=0;j<iv.transits.get(i).positions.size();i++)
					{
						dataP[i+j][0]=iv.transits.get(i).positions.get(j).name;
						dataP[i+j][1]=iv.transits.get(i).positions.get(j).id;
						dataP[i+j][2]=iv.transits.get(i).positions.get(j).location;
						dataP[i+j][3]=iv.transits.get(i).positions.get(j).transitId;
					}
				}
				
				dataS=new String[iv.staffs.size()][4];
				for(int i=0;i<iv.staffs.size();i++)
				{
					dataS[i][0]=iv.staffs.get(i).getName();
					dataS[i][1]=iv.staffs.get(i).getId();
					dataS[i][2]=power(iv.staffs.get(i).getPower());
					dataS[i][3]=iv.staffs.get(i).getAgency();
				}
				
				dataC=new String[iv.cars.size()][6];
				for(int i=0;i<iv.staffs.size();i++)
				{
					dataC[i][0]=iv.cars.get(i).getId();
					dataC[i][1]=iv.cars.get(i).getNumber();
					dataC[i][2]=iv.cars.get(i).getBuytime();
					dataC[i][3]=iv.cars.get(i).getMechine();
					dataC[i][4]=iv.cars.get(i).getDipan();
					dataC[i][5]=Integer.toString(iv.cars.get(i).getWorktime());
				}
				
				dataI=new String[iv.inventories.size()][8];
				for(int i=0;i<iv.accounts.size();i++)
				{
					dataI[i][0]=iv.inventories.get(i).getId();
					dataI[i][1]=iv.inventories.get(i).getInventoryInInformation().getTransit();
					dataI[i][2]=iv.inventories.get(i).getInventoryInInformation().getTime();
					dataI[i][3]=iv.inventories.get(i).getInventoryInInformation().getDestination();
					dataI[i][4]=Integer.toString(iv.inventories.get(i).getInventoryInInformation().getBlock());
					dataI[i][5]=Integer.toString(iv.inventories.get(i).getInventoryInInformation().getRow());
					dataI[i][6]=Integer.toString(iv.inventories.get(i).getInventoryInInformation().getShelf());
					dataI[i][7]=Integer.toString(iv.inventories.get(i).getInventoryInInformation().getPositon());
				}
				
				tableT=new JTable(dataT,nameT);
				tableP=new JTable(dataP,nameP);
				tableS=new JTable(dataS,nameS);
				tableC=new JTable(dataC,nameC);
				tableA=new JTable(dataA,nameA);
				tableI=new JTable(dataI,nameI);
			}
		});
		add(choose);
		
		backI=new JButton(backIcon);
		backI.setBounds(backI_x, backI_y, backI_w, backI_h);
		backI.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				controler.accountChangePanel(ACCOUNT_CONTROL.INITIAL);
			}
		});
		add(backI);
		
		buttonAG=new JButton(AGIcon);
		buttonAG.setBounds(button_x, button_y,button_w,button_h);
		buttonAG.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				scrollT.setVisible(true);
				scrollP.setVisible(true);
				scrollS.setVisible(false);
				scrollC.setVisible(false);
				scrollA.setVisible(false);
				scrollI.setVisible(false);
			}
		});
		add(buttonAG);
		
		buttonS=new JButton(SIcon);
		buttonS.setBounds(button_x+(button_w+5), button_y,button_w,button_h);
		buttonS.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				scrollT.setVisible(false);
				scrollP.setVisible(false);
				scrollS.setVisible(true);
				scrollC.setVisible(false);
				scrollA.setVisible(false);
				scrollI.setVisible(false);
			}
		});
		add(buttonS);
		
		buttonC=new JButton(CIcon);
		buttonC.setBounds(button_x+2*(button_w+5), button_y,button_w,button_h);
		buttonC.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				scrollT.setVisible(false);
				scrollP.setVisible(false);
				scrollS.setVisible(false);
				scrollC.setVisible(true);
				scrollA.setVisible(false);
				scrollI.setVisible(false);
			}
		});
		add(buttonC);
		
		buttonA=new JButton(ACIcon);
		buttonA.setBounds(button_x+3*(button_w+5), button_y,button_w,button_h);
		buttonA.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				scrollT.setVisible(false);
				scrollP.setVisible(false);
				scrollS.setVisible(false);
				scrollC.setVisible(false);
				scrollA.setVisible(true);
				scrollI.setVisible(false);
			}
		});
		add(buttonA);
		
		buttonI=new JButton(IIcon);
		buttonI.setBounds(button_x+4*(button_w+5), button_y,button_w,button_h);
		buttonI.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				scrollT.setVisible(false);
				scrollP.setVisible(false);
				scrollS.setVisible(false);
				scrollC.setVisible(false);
				scrollA.setVisible(false);
				scrollI.setVisible(true);
			}
		});
		add(buttonI);
		
		scrollT=new JScrollPane(tableT);
		scrollT.setBounds(scrollT_x, scrollT_y, scrollT_w, scrollT_h);
		scrollT.setVisible(false);
		add(scrollT);
		
		scrollP=new JScrollPane(tableP);
		scrollP.setBounds(scrollP_x, scrollP_y, scrollP_w, scrollP_h);
		scrollP.setVisible(false);
		add(scrollP);
		
		tableS.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox(power)));
		scrollS=new JScrollPane(tableS);
		scrollS.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollS.setVisible(false);
		add(scrollS);
		
		tableC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollC=new JScrollPane(tableC);
		scrollC.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollC.setVisible(false);
		add(scrollC);
		
		scrollA=new JScrollPane(tableA);
		scrollA.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollA.setVisible(false);
		add(scrollA);
		
		tableI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollI=new JScrollPane(tableI);
		scrollI.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollI.setVisible(false);
		add(scrollI);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
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
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/initial_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}

