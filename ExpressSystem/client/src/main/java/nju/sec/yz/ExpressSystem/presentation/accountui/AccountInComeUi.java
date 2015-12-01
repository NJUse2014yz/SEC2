package nju.sec.yz.ExpressSystem.presentation.accountui;

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

import nju.sec.yz.ExpressSystem.bl.accountbl.Finance;
import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class AccountInComeUi extends JPanel{
	private ButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private ManagerController managerController;
	private FinanceBlSevice finance;
	private String[] positions;
	private DateChooser date;
	private JComboBox choose;
	private JTable table;
	private JScrollPane scroll;
	private JButton back;
	private String[] name={"收款日期","收款单位","收款人","收款方","收款金额","收款地点"};
	private String[][] data={{"20151017","426.5","2352616","354678998764","2352616","2352616"},{"20140403","43.5","11bgfs","fgea452q","2352616","2352616"},{"rea","2352616","2352616","hes","rea","245367776"},{"20151017","2352616","2352616","426.5","2352616","354678998764"},{"20151017","426.5","2352616","2352616","2352616","354678998764"},{"20151017","426.5","2352616","2352616","2352616","354678998764"},{"20151017","426.5","2352616","354678998764","2352616","2352616"},{"20151017","426.5","2352616","2352616","2352616","354678998764"}};
	
	
	private static int date_x=184;
	private static int date_y=67;
	private static int choose_x=145;
	private static int choose_y=95;
	private static int choose_w=265;
	private static int choose_h=20;
	private static int scroll_x=139;
	private static int scroll_y=122;
	private static int scroll_w=317;
	private static int scroll_h=267;
	private static int confirm_x=377;
	private static int confirm_y=404;
	private static int confirm_w=80;
	private static int confirm_h=25;
	
	public AccountInComeUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		finance=new FinanceController();
		managerController=new ManagerController();
		this.bc=bc;
		ArrayList<PositionVO> positionlist=new ArrayList<PositionVO>();
		ArrayList<TransitVO> transitvo=managerController.observeAllTransit();
		List<PositionVO> pvo;
		for(int i=0;i<transitvo.size();i++)
		{
			pvo=transitvo.get(i).getPositions();
			for(int j=0;j<pvo.size();j++)
			{
				positionlist.add(pvo.get(j));
			}
		}
		positions=new String[positionlist.size()];
		for(int k=0;k<positionlist.size();k++)
		{
			positions[k]=positionlist.get(k).getName();
		}
		
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		date=new DateChooser(this,date_x,date_y);
		date.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				table=new JTable(data,name);
				
			}
		});
		
		choose=new JComboBox(positions);
		choose.setBounds(choose_x, choose_y, choose_w, choose_h);
		add(choose);
		
//		List<PaymentSheetVO> sheetlist=finance.checkReceipt(date.getTime(), positions[choose.getSelectedIndex()]);
//		data=new String 
//		for(int i=0;i<sheetlist.size();i++)
//		{
//		
//		}
//		
//		back;
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/income_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
