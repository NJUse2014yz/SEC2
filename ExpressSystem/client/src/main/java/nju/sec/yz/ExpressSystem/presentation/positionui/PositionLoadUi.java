package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;

public class PositionLoadUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private ConstBlService constBl;
	private DeliverBlService deliverBl;
	private ButtonComponents bc;
	private DateChooser date;
	private ArrayList<String> bars;

	private JComboBox JCdestination;
//	private JTextField JTtransportId;
	private JLabel JLtransportId;
	private JTextField JTPositionId;
	private JTextField JTCarId;
	private JComboBox JCloadtime;
	private JTextField JTsuperviserId;
	private JTextField JTdriverId;
	private JLabel fare;
	private JScrollPane scroller;
	private JTable barIds;
	private JButton confirm;
	private JLabel warning;
	private String[] name={"订单条形码号"};
	private String[][] data={{""}};
	private DefaultTableModel model;

	private static final int destination_x=193;
	private static final int destination_y=63;
	private static final int destination_w=102;
	private static final int destination_h=20;
	private static final int transportId_x=206;
	private static final int transportId_y=90;
	private static final int transportId_w=182;
	private static final int transportId_h=20;
	private static final int positionId_x=240;
	private static final int positionId_y=115;
	private static final int positionId_w=182;
	private static final int positionId_h=20;
	private static final int carId_x=222;
	private static final int carId_y=143;
	private static final int carId_w=90;
	private static final int carId_h=20;
	private static final int loadtime_x=208;
	private static final int loadtime_y=168;
	private static final int loadtime_w=95;
	private static final int loadtime_h=20;
	private static final int driverId_x=194;
	private static final int driverId_y=195;
	private static final int driverId_w=67;
	private static final int driverId_h=20;
	private static final int superviserId_x=325;
	private static final int superviserId_y=195;
	private static final int superviserId_w=67;
	private static final int superviserId_h=20;
	private static final int fare_x=185;
	private static final int fare_y=223;
	private static final int fare_w=90;
	private static final int fare_h=20;
	private static final int bars_x=135;
	private static final int bars_y=247;
	private static final int bars_w=330;
	private static final int bars_h=135;
	private static final int confirm_x=396;
	private static final int confirm_y=392;
	private static final int confirm_w=65;
	private static final int confirm_h=20;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private static final int scroll_x=136;
	private static final int scroll_y=249;
	private static final int scroll_w=330;
	private static final int scroll_h=134;
	
	
	ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	
	private static String[] des;
	public PositionLoadUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		deliverBl=new DeliverController();
		this.bc=bc;
		bars=new ArrayList<String>();
		date=new DateChooser(this,207,170);
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		List<String> destination=deliverBl.getValidAgency();
		int n=destination.size();
		des=new String[n];
		for(int i=0;i<n;i++)
		{
			des[i]=destination.get(i);
		}
		JCdestination=new JComboBox(des);
		JCdestination.setBounds(destination_x, destination_y, destination_w, destination_h);
		add(JCdestination);
		
		
		JTPositionId = new JTextField();
		JTPositionId.setText(deliverBl.getCurrentPosition().id);
		JTPositionId.setBounds(positionId_x,positionId_y,positionId_w,positionId_h);
		add(JTPositionId);
		
//		JTtransportId = new JTextField();
//		JTtransportId.setBounds(transportId_x,transportId_y,transportId_w,transportId_h);
//		add(JTtransportId);
		JLtransportId=new JLabel();
		JLtransportId.setBounds(transportId_x, transportId_y, transportId_w, transportId_h);
		JLtransportId.setFont(new Font("Dialog", 1, 15));
		JLtransportId.setForeground(Color.white);
		add(JLtransportId);
		JLtransportId.setVisible(false);
		
		JTCarId = new JTextField();
		JTCarId.setBounds(carId_x,carId_y,carId_w,carId_h);
		add(JTCarId);
		
		JTdriverId = new JTextField();
		JTdriverId.setBounds(driverId_x,driverId_y,driverId_w,driverId_h);
		add(JTdriverId);
		
		JTsuperviserId = new JTextField();
		JTsuperviserId.setBounds(superviserId_x,superviserId_y,superviserId_w,superviserId_h);
		add(JTsuperviserId);
		
		/*model=new DefaultTableModel(data,name);
		barIds=new JTable(model);
		model.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int num=model.getRowCount();
				String temp=(String) model.getValueAt(num-1, 0);
				if(temp!=""){
					String[] conponent={""};
					((DefaultTableModel) model).addRow(conponent); 
				}
				repaint();
			}
		});
		scroller=new JScrollPane(barIds);
		scroller.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		add(scroller);*/
		newTable table=new newTable(data,name,this,true);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		//上面用来实验newTable
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		fare=new JLabel();
		fare.setBounds(fare_x,fare_y,fare_w,fare_h);
		fare.setFont(new Font("Dialog", 1, 15));
		fare.setForeground(Color.white);
		add(fare);
		fare.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OfficeLoadSheetVO sheet=new OfficeLoadSheetVO();
				LoadInformation li=new LoadInformation();
				li.setAgencyId(JTPositionId.getText());
				li.setCarId(JTCarId.getText());
				li.setDriverId(JTdriverId.getText());
//				li.setTransportId(JTtransportId.getText());
				li.setOfficerId(JTsuperviserId.getText());
				li.setDestinationId((String)JCdestination.getSelectedItem());
				sheet.setOfficeLoadInformation(li);
				for(int i=0;i<model.getRowCount();i++)
				{
					if(!((String) model.getValueAt(i, 0)).equals(""))
					{
						bars.add((String) model.getValueAt(i, 0));
					}
				}
				sheet.setBarIds(bars);
				sheet.setType(ReceiptType.POSITION_LOADING_RECEIPT);
				
				if(JTPositionId.getText().equals("")||JTCarId.getText().equals("")||JTdriverId.getText().equals("")||JTPositionId.getText().equals("")||JTsuperviserId.getText().equals(""))
				{
					warning.setText("有必填项未填");
					warning.setVisible(true);
					repaint();
				}
				else
				{
					ResultMessage result=deliverBl.positionLoadingReceipt(sheet);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
						warning.setVisible(true);
						String[] message=result.getMessage().split(" ");
						fare.setText(message[0]);
						JLtransportId.setText(message[1]);
						fare.setVisible(true);
						JLtransportId.setVisible(true);
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

		Image img01 = new ImageIcon("graphic/position/background/load_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
