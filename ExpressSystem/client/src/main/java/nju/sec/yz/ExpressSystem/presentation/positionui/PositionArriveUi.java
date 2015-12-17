package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.script.Bindings;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.BarIdsVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

public class PositionArriveUi extends JPanel{
	private DeliverBlService deliverBl;
	private ConstBlService constBl;
	private ManagerController manageControler;
	private ClientControler mainControler;
	private PositionControler controler;
	private ButtonComponents bc;
	private Vector<String> name;
	private Vector<Vector<String>> data;
	private int n=0;
	
	private JLabel departure;
	private JTextField JTtranferId;
	private DateChooser date;
	private newTable table;
	private JButton confirm;
	private JLabel warning;
	private JButton yes;
	
	public static final int departure_x=192;
	public static final int departure_y=63;
	public static final int departure_w=100;
	public static final int h=20;
	public static final int tranferId_x=228;
	public static final int tranferId_y=89;
	public static final int tranferId_w=182;
	public static final int date_x=207;
	public static final int date_y=115;
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
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new newTable(data,name,this,false);
		table.setJComboBox(new JComboBox(new String[]{"完整","丢失","损坏"}), 1);
		table.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		table.join();
		
		departure=new JLabel();
		departure.setBounds(departure_x, departure_y, departure_w, h);
		departure.setFont(new Font("Dialog", 1, 15));
		departure.setForeground(Color.white);
		add(departure);
		departure.setVisible(false);
		
		JTtranferId=new JTextField();
		JTtranferId.setBounds(tranferId_x,tranferId_y,tranferId_w,h);
		add(JTtranferId);
		
		yes=new JButton();
		yes.setBounds(tranferId_x+100, tranferId_y,72,24);
		yes.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				BarIdsVO vo=deliverBl.getBarIdList(JTtranferId.getText());
				
				if(vo==null)
					return;
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
					warning.setText("中转单编号不存在，请检查");
					warning.setVisible(true);
					repaint();
				}
			}
		});
		add(yes);
		
		date=new DateChooser(this,date_x,date_y);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
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
					warning.setText("有必填项未填");
					warning.setVisible(true);
					repaint();
				}
				else
				{
					ResultMessage result=deliverBl.positionReceiveReceipt(sheet);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
						warning.setVisible(true);
						repaint();
					}
					else{
						warning.setText(result.getMessage());
						warning.setVisible(true);
						repaint();
					}
				}
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
