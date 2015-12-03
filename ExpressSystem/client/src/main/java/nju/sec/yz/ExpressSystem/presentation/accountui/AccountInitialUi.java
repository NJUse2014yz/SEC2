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
	private AccountControler controler;
	private InitialBlService initialBl;
	
	private boolean P=false;
	private boolean T=false;
	private boolean S=false;
	private boolean C=false;
	private boolean A=false;
	private boolean I=false;
	
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
	
	private JButton confirm;
	private JLabel warning;
	private String[] power=new String[]{"总经理","高级财务人员","低级财务人员","中转中心业务员","中转中心仓库管理人员","营业厅业务员","快递员","管理员"};
	private String[] nameT=new String[]{"名称","编号","所在地"};
	private String[][] dataT=new String[][]{{"","","",""}};
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
	private static final int confirm_x=406;
	private static final int confirm_y=435;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
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
	
	public AccountInitialUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		initialBl=new InitialController();
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
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
		
		modelT=new DefaultTableModel(dataT,nameT);
		modelT.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelT.getRowCount();
				String temp=(String) modelT.getValueAt(num-1, 2);
				if(temp!=""){
					String[] conponent={"","",""};
					modelT.addRow(conponent); 
				}
				repaint();
			}
		});
		tableT=new JTable(modelT);
		tableT.setRowHeight(20);
		scrollT=new JScrollPane(tableT);
		scrollT.setBounds(scrollT_x, scrollT_y, scrollT_w, scrollT_h);
		scrollT.setVisible(false);
		add(scrollT);
		
		modelP=new DefaultTableModel(dataP,nameP);
		modelP.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelP.getRowCount();
				String temp=(String) modelP.getValueAt(num-1, 3);
				if(temp!=""){
					String[] conponent={"","","",""};
					modelP.addRow(conponent); 
				}
				repaint();
			}
		});
		tableP=new JTable(modelP);
		tableP.setRowHeight(20);
		scrollP=new JScrollPane(tableP);
		scrollP.setBounds(scrollP_x, scrollP_y, scrollP_w, scrollP_h);
		scrollP.setVisible(false);
		add(scrollP);
		
		modelS=new DefaultTableModel(dataS,nameS);
		modelS.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelS.getRowCount();
				String temp=(String) modelS.getValueAt(num-1, 3);
				if(temp!=""){
					String[] conponent={"","","",""};
					modelS.addRow(conponent); 
				}
				repaint();
			}
		});
		tableS=new JTable(modelS);
		tableS.setRowHeight(20);
		tableS.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox(power)));
		scrollS=new JScrollPane(tableS);
		scrollS.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollS.setVisible(false);
		add(scrollS);
		
		modelC=new DefaultTableModel(dataC,nameC);
		modelC.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelC.getRowCount();
				String temp=(String) modelC.getValueAt(num-1, 5);
				if(temp!=""){
					String[] conponent={"","","","","",""};
					modelC.addRow(conponent); 
				}
				repaint();
			}
		});
		tableC=new JTable(modelC);
		tableC.setRowHeight(20);
		tableC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollC=new JScrollPane(tableC);
		scrollC.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollC.setVisible(false);
		add(scrollC);
		
		modelA=new DefaultTableModel(dataA,nameA);
		modelA.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelA.getRowCount();
				String temp=(String) modelA.getValueAt(num-1, 1);
				if(temp!=""){
					String[] conponent={"",""};
					modelA.addRow(conponent); 
				}
				repaint();
			}
		});
		tableA=new JTable(modelA);
		tableA.setRowHeight(20);
		scrollA=new JScrollPane(tableA);
		scrollA.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		scrollA.setVisible(false);
		add(scrollA);
		
		modelI=new DefaultTableModel(dataI,nameI);
		modelI.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int num=modelI.getRowCount();
				String temp=(String) modelI.getValueAt(num-1, 7);
				if(temp!=""){
					String[] conponent={"","","","","","","",""};
					modelI.addRow(conponent); 
				}
				repaint();
			}
		});
		tableI=new JTable(modelI);
		tableI.setRowHeight(20);
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
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result;
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
				if(!P)
				{
					result=initialBl.addPosition(pvl);
					if(result.getResult()==Result.SUCCESS)
					{
						remove(scrollP);
	//					scrollP.setVisible(false);
						warning.setText("营业厅初始化成功");
						P=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				
				if(P&&!T)
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
						String a=tableT.getModel().getValueAt(i,0).toString();
						String b=tableT.getModel().getValueAt(i,1).toString();
						String d=tableT.getModel().getValueAt(i,2).toString();
						if(!a.equals("")&&!b.equals("")&&!d.equals(""))
						{
							TransitVO tv=new TransitVO(a,b,tpvl,d);
							tvl.add(tv);
						}
					}
					result=initialBl.addTransit(tvl);
					if(result.getResult()==Result.SUCCESS)
					{
						remove(scrollT);
	//					scrollP.setVisible(false);
						buttonAG.setVisible(false);
						warning.setText("中转中心初始化成功");
						T=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				
				if(!S)
				{
					List<StaffVO> svl=new ArrayList<StaffVO>();
					for(int i=0;i<tableS.getRowCount();i++)
					{
						Status pow=Status.ADMINISTRATOR;
						String p=(String) tableS.getCellEditor(i, 2).getCellEditorValue();
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
						String a=tableS.getModel().getValueAt(i,0).toString();
						String b=tableS.getModel().getValueAt(i,1).toString();
						String d=tableS.getModel().getValueAt(i,3).toString();
						if(!a.equals("")&&!b.equals("")&&!d.equals(""))
						{
							StaffVO sv=new StaffVO(a,b,pow,d);
							svl.add(sv);
						}
					}
					result=initialBl.addStaff(svl);
					if(result.getResult()==Result.SUCCESS)
					{
						remove(scrollS);
	//					scrollS.setVisible(false);
						buttonS.setVisible(false);
						warning.setText("人员初始化成功");
						S=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				
				if(!C)
				{
					List<CarVO> cvl=new ArrayList<CarVO>();
					for(int i=0;i<tableC.getRowCount();i++)
					{
						String a=tableC.getModel().getValueAt(i,0).toString();
						String b=tableC.getModel().getValueAt(i,1).toString();
						String c=tableC.getModel().getValueAt(i,2).toString();
						String d=tableC.getModel().getValueAt(i,3).toString();
						String e2=tableC.getModel().getValueAt(i,4).toString();
						if(!a.equals("")&&!b.equals("")&&!c.equals("")&&!d.equals("")&&!e2.equals(""))
						{
							CarVO cv=new CarVO(a,b,c,d,e2);
							cvl.add(cv);
						}
					}
					result=initialBl.addCar(cvl);
					if(result.getResult()==Result.SUCCESS)
					{
						remove(scrollC);
	//					scrollC.setVisible(false);
						buttonC.setVisible(false);
						warning.setText("车辆初始化成功");
						C=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				
				if(!I)
				{
					List<InventoryInSheetVO> iisvl=new ArrayList<InventoryInSheetVO>();
					for(int i=0;i<tableA.getRowCount();i++)
					{
						String a=tableI.getModel().getValueAt(i,2).toString();
						String b=tableI.getModel().getValueAt(i,3).toString();
						String c=tableI.getModel().getValueAt(i,4).toString();
						String d=tableI.getModel().getValueAt(i,5).toString();
						String e1=tableI.getModel().getValueAt(i,6).toString();
						String f=tableI.getModel().getValueAt(i,7).toString();
						String g=tableI.getModel().getValueAt(i,1).toString();
						String h=tableI.getModel().getValueAt(i,0).toString();
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
						remove(scrollI);
	//					scrollI.setVisible(false);
						buttonI.setVisible(false);
						warning.setText("库存初始化成功");
						I=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				if(!A)
				{
					List<AccountVO> acvl=new ArrayList<AccountVO>();
					for(int i=0;i<tableA.getRowCount();i++)
					{
						String a=tableA.getModel().getValueAt(i,0).toString();
						String b=tableA.getModel().getValueAt(i,1).toString();
						if(!a.equals("")&&!b.equals(""))
						{
							AccountVO acv=new AccountVO(a,Double.parseDouble(b));
							acvl.add(acv);
						}
					}
					result=initialBl.addAccount(acvl);
					if(result.getResult()==Result.SUCCESS)
					{
						remove(scrollA);
	//					scrollA.setVisible(false);
						buttonA.setVisible(false);
						warning.setText("账户初始化成功");
						A=true;
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
				if(P&&T&&S&&C&&A&&I)
				{
					result=initialBl.finish();
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("期初建账成功");
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
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
