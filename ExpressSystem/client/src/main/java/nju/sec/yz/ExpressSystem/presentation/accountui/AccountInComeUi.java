package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class AccountInComeUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private ManagerController managerController;
	private FinanceBlSevice finance;
	
	private JLabel total;
	private String[] positions;
	private DateChooser date;
	private JComboBox choose;
	private newTable table;
//	private JButton back;
	private JButton confirm;
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	
	private static final int total_x=192;
	private static final int total_y=416;
	private static final int total_w=90;
	private static final int total_h=30;
	private static int date_x=183;
	private static int date_y=67;
	private static int choose_x=240;
	private static int choose_y=94;
	private static int choose_w=170;
	private static int choose_h=20;
	private static int scroll_x=139;
	private static int scroll_y=122;
	private static int scroll_w=317;
	private static int scroll_h=267;
	private static int confirm_x=412;
	private static int confirm_y=95;
	private static int confirm_w=72;
	private static int confirm_h=24;
	
	private ImageIcon backIcon=new ImageIcon("graphic/account/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	
	public AccountInComeUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		finance=new FinanceController();
		managerController=new ManagerController();
		this.bc=bc;
		managerController=new ManagerController();
		
		ArrayList<PositionVO> positionlist=new ArrayList<PositionVO>();
		ArrayList<TransitVO> transitvo=managerController.observeAllTransit();
		if(transitvo!=null)
		{
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
		}
		
		name.add("收款日期");
		name.add("收款单位");
		name.add("收款人");
		name.add("收款金额");
		name.add("收款地点");
		
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		date=new DateChooser(this,date_x,date_y);
		choose=new JComboBox(positions);
		choose.setBounds(choose_x, choose_y, choose_w, choose_h);
		add(choose);
		
		table=new newTable(data,name,this,false);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.stopAutoRewidth();
		table.join();
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				PaymentVO pv=finance.checkReceipt(date.getTime(), positions[choose.getSelectedIndex()]);
				if(pv!=null)
				{
					List<PaymentSheetVO> sheetlist=pv.paymentList;
					changeData(sheetlist);
				}
				else
				{
					changeData(new ArrayList<PaymentSheetVO>());
				}
			}
		});
		
		total=new JLabel();
		total.setBounds(total_x, total_y, total_w, total_h);
		total.setFont(new Font("Dialog", 1, 15));
		total.setForeground(Color.white);
		add(total);
		total.setVisible(false);
		
		setVisible(true);
	}
	private void changeData(List<PaymentSheetVO> sheetlist)
	{
		data.removeAllElements();
		if(sheetlist!=null)
		{
			PaymentInformation psvoi;
			for(int i=0;i<sheetlist.size();i++)
			{
				psvoi=sheetlist.get(i).getPaymentInformation();
				Vector<String> vector=new Vector<String>();
				vector.add(psvoi.getTime());
				vector.add(psvoi.getPositionId());
				vector.add(psvoi.getInDeliverId());
				vector.add(Double.toString(psvoi.getAmount()));
				vector.add("");
				data.add(vector);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/income_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
