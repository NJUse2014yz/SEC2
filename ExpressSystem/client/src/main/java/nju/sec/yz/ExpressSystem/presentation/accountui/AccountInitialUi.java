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
import javax.swing.table.DefaultTableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
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
import nju.sec.yz.ExpressSystem.vo.StaffVO;

public class AccountInitialUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private FinanceBlSevice financeBl;
	
	private JTable tableAG;
	private JTable tableS;
	private JTable tableC;
	private JTable tableAC;
	private JTable tableI;
	private JScrollPane scrollAG;
	private JScrollPane scrollS;
	private JScrollPane scrollC;
	private JScrollPane scrollAC;
	private JScrollPane scrollI;
	
	private JButton confirm;
	private JLabel warning;
	private String[] power=new String[]{"总经理","高级财务人员","低级财务人员","中转中心业务员","中转中心仓库管理人员","营业厅业务员","快递员","管理员"};
	private String[] nameAgency=new String[]{"所在地","编号","名称"};
	private String[][] dataAgency=new String[][]{{"","",""}};
	private String[] nameStaff=new String[]{"姓名","编号","职位","所属机构"};
	private String[][] dataStaff=new String[][]{{"","","",""}};
	private String[] nameCar=new String[]{"车辆代号","车牌号","服役时间","发动机号","购买时间","底盘号"};
	private String[][] dataCar=new String[][]{{"","","","","",""}};
	private String[] nameAccount=new String[]{"名称","余额"};
	private String[][] dataAccount=new String[][]{{"",""}};
	private String[] nameI=new String[]{"id","中转中心编号","入库时间","目的地","区号","排号","架号","位号"};
	private String[][] dataI=new String[][]{{"","","","","","","",""}};
	private String num;
	
	
	private static final int scrollAG_x=137;
	private static final int scrollAG_y=74;
	private static final int scrollAG_w=318;
	private static final int scrollAG_h=223;
	private static final int scrollS_x=137;
	private static final int scrollS_y=74;
	private static final int scrollS_w=318;
	private static final int scrollS_h=223;
	private static final int scrollC_x=137;
	private static final int scrollC_y=74;
	private static final int scrollC_w=318;
	private static final int scrollC_h=223;
	private static final int scrollAC_x=137;
	private static final int scrollAC_y=74;
	private static final int scrollAC_w=318;
	private static final int scrollAC_h=223;
	private static final int scrollI_x=137;
	private static final int scrollI_y=74;
	private static final int scrollI_w=318;
	private static final int scrollI_h=223;
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
		
		tableAG=new JTable(dataAgency,nameAgency);
		scrollAG=new JScrollPane(tableAG);
		scrollAG.setBounds(scrollAG_x, scrollAG_y, scrollAG_w, scrollAG_h);
//		add(scrollAG);
		
		tableS=new JTable(dataStaff,nameStaff);
		tableS.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox(power)));
		scrollS=new JScrollPane(tableS);
		scrollS.setBounds(scrollS_x, scrollS_y, scrollS_w, scrollS_h);
//		add(scrollS);
		
		tableC=new JTable(dataCar,nameCar);
		scrollC=new JScrollPane(tableC);
		scrollC.setBounds(scrollC_x, scrollC_y, scrollC_w, scrollC_h);
//		add(scrollC);
		
		tableAC=new JTable(dataAccount,nameAccount);
		scrollAC=new JScrollPane(tableAC);
		scrollAC.setBounds(scrollAC_x, scrollAC_y, scrollAC_w, scrollAC_h);
//		add(scrollAC);
		
		tableI=new JTable(dataI,nameI);
		scrollI=new JScrollPane(tableI);
		scrollI.setBounds(scrollI_x, scrollI_y, scrollI_w, scrollI_h);
//		add(scrollI);
		
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
				
				List<AgencyVO> agvl=new ArrayList<AgencyVO>();
				for(int i=0;i<tableAG.getRowCount();i++)
				{
					AgencyVO agv=new AgencyVO(tableAG.getModel().getValueAt(i,0).toString(),tableAG.getModel().getValueAt(i,1).toString(),tableAG.getModel().getValueAt(i,2).toString());
					agvl.add(agv);
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
					Status pow;
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
				for(int i=0;i<tableAC.getRowCount();i++)
				{
					AccountVO acv=new AccountVO(tableAC.getModel().getValueAt(i,0).toString(),Double.parseDouble(tableAC.getModel().getValueAt(i,1).toString()));
					acvl.add(acv);
				}
				List<InventoryInSheetVO> iisvl=new ArrayList<InventoryInSheetVO>();
				for(int i=0;i<tableAC.getRowCount();i++)
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
				InitialVO iv=new InitialVO(agvl,svl,cvl,acvl,iisvl);
				financeBl.initial(iv);
				
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
