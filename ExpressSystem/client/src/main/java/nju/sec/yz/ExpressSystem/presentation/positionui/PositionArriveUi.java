package nju.sec.yz.ExpressSystem.presentation.positionui;

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
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.BarIdsVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;

public class PositionArriveUi extends JPanel{
	private DeliverBlService deliverBl;
	private ConstBlService constBl;
	private ManagerController manageControler;
	private ClientControler mainControler;
	private PositionControler controler;
	private ButtonComponents bc;
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data;
	private int n=0;
	
	private newJLabel departure;
	private newJText JTtranferId;
	private DateChooser date;
	private newTable table;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	private newJBut yes;
	
	public static final int departure_x=192;
	public static final int departure_y=115;
	public static final int departure_w=100;
	public static final int h=20;
	public static final int tranferId_x=220;
	public static final int tranferId_y=89;
	public static final int tranferId_w=182;
	public static final int date_x=207;
	public static final int date_y=63;
	public static final int scroll_x=138;
	public static final int scroll_y=175;
	public static final int scroll_w=315;
	public static final int scroll_h=244;
	public static final int confirm_x=355;
	public static final int confirm_y=443;
	public static final int confirm_w=72;
	public static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;

	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	private ArriveState arriveState;
	
	private String[] city;
	public PositionArriveUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		deliverBl=new DeliverController();
		manageControler=new ManagerController();
		List<String> citys=deliverBl.getValidAgency();
		city=new String[citys.size()];
		name.add("条形码号");
		name.add("货物到达状态");
		initDeliverMainUi();
	}
	private void initDeliverMainUi() {
		bc.changePanel(this);
//		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new newTable(data,name,this,false);
		table.setJComboBox(new JComboBox(new String[]{"完整","丢失","损坏"}), 1);
		table.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		table.join();
		
		departure=new newJLabel();
		departure.setBounds(departure_x, departure_y, departure_w, h);
		add(departure);
		departure.setVisible(false);
		
		JTtranferId=new newJText();
		JTtranferId.setBounds(tranferId_x,tranferId_y,tranferId_w,h);
		add(JTtranferId);
		
		yes=new newJBut("Yes");
		yes.setBounds(tranferId_x+110, tranferId_y+24,72,24);
		yes.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				BarIdsVO vo=deliverBl.getBarIdList(JTtranferId.getText());
				/*
				 * 2015/12/18
				 *  章琦 
				 *  添加了return之前的操作
				 */
				if(vo==null){
					warning.setForeground(Color.red);
					warning.setBounds(warning_x,warning_y,warning_w,warning_h);
					warning.setText("中转单编号不存在，请检查");
					warning.setVisible(true);
					add(warning);
					repaint();
					return;
				}
					
				List<String> bars=vo.barIds;
				departure.setText(vo.fromAgency);
				departure.setVisible(true);
				System.out.println("here");
				if(bars!=null)
				{	for(int i=0;i<bars.size();i++)
					{
						Vector<String> vector=new Vector<String>();
						vector.add(bars.get(i));
						vector.add("");
						data.add(vector);
					}
					table.resetData();
				}
				else{
					warning.setForeground(Color.red);
					warning.setBounds(warning_x,warning_y,warning_w,warning_h);
					warning.setText("中转单编号不存在，请检查");
					warning.setVisible(true);
					add(warning);
					repaint();
				}
			}
		});
		add(yes);
		
		date=new DateChooser(this,date_x,date_y);
		
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				 
				OfficeArriveSheetVO sheet=new OfficeArriveSheetVO();
				ArriveInformation ai=new ArriveInformation();
				for(int i=0;i<n;i++)
				{
					if(table.getValueAt(i, 1, true).equals("完整"))
						arriveState=ArriveState.PERFECT;
					else if(table.getValueAt(i, 1, true).equals("损坏"))
						arriveState=ArriveState.BROKEN;
					else if(table.getValueAt(i, 1, true).equals("丢失"))
						arriveState=ArriveState.LOST;
					ai.addState(arriveState);
				}
				ai.setTime(date.getTime());
				ai.setTransitSheetId(JTtranferId.getText());
				sheet.setOfficeArrive(ai);
				sheet.setType(ReceiptType.POSITION_SEND_RECEIPT);
				
				if(JTtranferId.getText().equals(""))
				{
					warning.NotFilled();
				}
				else
				{
					ResultMessage result=deliverBl.positionReceiveReceipt(sheet);
					warning.Reply(result);
				}
				add(warning);
				repaint();
			}		
		});
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/arrive_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
