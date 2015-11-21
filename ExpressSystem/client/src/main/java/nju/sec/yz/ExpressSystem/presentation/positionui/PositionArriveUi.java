package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
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
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
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
	private ClientControler mainControler;
	private PositionControler controler;
	private ButtonComponents bc;
	
	private JComboBox JCdeparture;
	private JTextField JTtranferId;
	private DateChooser date;
	private JComboBox state;
	private JButton confirm;
	private JLabel warning;
	
	public static final int departure_x=192;
	public static final int departure_y=63;
	public static final int departure_w=100;
	public static final int h=20;
	public static final int tranferId_x=228;
	public static final int tranferId_y=89;
	public static final int tranferId_w=182;
	public static final int date_x=207;
	public static final int date_y=115;
	public static final int state_x=261;
	public static final int state_y=140;
	public static final int state_w=165;
	public static final int confirm_x=355;
	public static final int confirm_y=178;
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
		//constBl=new ConstBl
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		city=new String[]{"南京","北京","上海"};//(String[])constBl.getCities().toArray();
		JCdeparture=new JComboBox(city);
		JCdeparture.setBounds(departure_x, departure_y, departure_w, h);
		add(JCdeparture);
		
		JTtranferId=new JTextField();
		JTtranferId.setBounds(tranferId_x,tranferId_y,tranferId_w,h);
		add(JTtranferId);
		
		date=new DateChooser(this,date_x,date_y);
		
		state=new JComboBox(new String[]{"完整","丢失","损坏"});
		state.setBounds(state_x, state_y, state_w, h);
		add(state);
		
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
				switch((String)state.getSelectedItem())
				{
				case "损坏":
					arriveState=ArriveState.Broken;
					break;
				case "丢失":
					arriveState=ArriveState.LOST;
					break;
				case "完整":
					arriveState=ArriveState.PERFECT;
					break;
				}
				ai.setDeparture((String)JCdeparture.getSelectedItem());
				ai.setState(arriveState);
				sheet.setOfficeArrive(ai);
				sheet.setType(ReceiptType.POSITION_LOADING_RECEIPT);
				
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
