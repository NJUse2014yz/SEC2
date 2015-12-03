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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.bl.accountbl.InitialController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class AccountInitialUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private InitialBlService initialBl;
	
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
	
	public AccountInitialUi(ClientControler mainControler,ButtonComponents bc){
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				initialBl.start();
				
				List<PositionVO> pvl=new ArrayList<PositionVO>();
				for(int i=0;i<tableP.getRowCount();i++)
				{
					PositionVO pv=new PositionVO(tableP.getModel().getValueAt(i,0).toString(),tableP.getModel().getValueAt(i,1).toString(),tableP.getModel().getValueAt(i,3).toString(),tableP.getModel().getValueAt(i,2).toString());
					pvl.add(pv);
				}
				List<TransitVO> tvl=new ArrayList<TransitVO>();
				for(int i=0;i<tableT.getRowCount();i++)
				{
					List<PositionVO> tpvl=new ArrayList<PositionVO>();
					for(int j=0;j<tableP.getRowCount();j++)
					{
						if(tableP.getModel().getValueAt(j, 3).equals(tableT.getModel().getValueAt(i, 1)))
						{
							tpvl.add(pvl.get(j));
						}
					}
					TransitVO tv=new TransitVO(tableT.getModel().getValueAt(i,0).toString(),tableT.getModel().getValueAt(i,1).toString(),tpvl,tableT.getModel().getValueAt(i,2).toString());
					tvl.add(tv);
				}
				List<CarVO> cvl=new ArrayList<CarVO>();
				for(int i=0;i<tableC.getRowCount();i++)
				{
					CarVO cv=new CarVO(tableC.getModel().getValueAt(i,0).toString(),tableC.getModel().getValueAt(i,1).toString(),tableC.getModel().getValueAt(i,2).toString(),tableC.getModel().getValueAt(i,3).toString(),tableC.getModel().getValueAt(i,4).toString());
					cvl.add(cv);
				}
				List<StaffVO> svl=new ArrayList<StaffVO>();
				for(int i=0;i<tableS.getRowCount();i++)
				{
					Status pow=Status.ADMINISTRATOR;
					if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[0]))
					{
						pow=Status.MANAGER;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[1]))
					{
						pow=Status.SENIOR_ACCOUNTANCY;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[2]))
					{
						pow=Status.JUNIOR_ACCOUNTANCY;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[3]))
					{
						pow=Status.TRANSIT;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[4]))
					{
						pow=Status.INVENTORY;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[5]))
					{
						pow=Status.POSITION;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[6]))
					{
						pow=Status.DELIVER;
					}
					else if(tableS.getCellEditor(i, 2).getCellEditorValue().equals(power[7]))
					{
						pow=Status.ADMINISTRATOR;
					}
					StaffVO sv=new StaffVO(tableS.getModel().getValueAt(i,0).toString(),tableS.getModel().getValueAt(i,1).toString(),pow,tableS.getModel().getValueAt(i,3).toString());
					svl.add(sv);
				}
				List<AccountVO> acvl=new ArrayList<AccountVO>();
				for(int i=0;i<tableA.getRowCount();i++)
				{
					AccountVO acv=new AccountVO(tableA.getModel().getValueAt(i,0).toString(),Double.parseDouble(tableA.getModel().getValueAt(i,1).toString()));
					acvl.add(acv);
				}
				List<InventoryInSheetVO> iisvl=new ArrayList<InventoryInSheetVO>();
				for(int i=0;i<tableA.getRowCount();i++)
				{
					InventoryInInformation iii=new InventoryInInformation(
							tableI.getModel().getValueAt(i,2).toString(),
							tableI.getModel().getValueAt(i,3).toString(),
							Integer.parseInt(tableI.getModel().getValueAt(i,4).toString()),
							Integer.parseInt(tableI.getModel().getValueAt(i,5).toString()),							
							Integer.parseInt(tableI.getModel().getValueAt(i,6).toString()),
							Integer.parseInt(tableI.getModel().getValueAt(i,7).toString()),
							tableI.getModel().getValueAt(i,1).toString());
					InventoryInSheetVO iisv=new InventoryInSheetVO(iii,tableI.getModel().getValueAt(i,0).toString());
					iisvl.add(iisv);
				}
				
				initialBl.finish();	
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
