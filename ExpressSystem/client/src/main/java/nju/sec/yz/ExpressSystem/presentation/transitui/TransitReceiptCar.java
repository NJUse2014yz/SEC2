package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

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
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class TransitReceiptCar extends JPanel{
	DeliverBlService deliverblservice=new DeliverController();
	ClientControler maincontrol;
	TransitButtonComponents tbc;
	
	private AgencyBlService manager = new ManagerController();
	
	private JTextField carId;
	private JTextField driverId;
	private JTextField transiterId;
	
	private JComboBox departure;
	private JComboBox destination;
	private newTable barId;
	private JButton confirm;
	private JLabel carTransitId;
	private JLabel fare;
	private JLabel warning;
	
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private DateChooser date;
	public TransitReceiptCar(ClientControler maincontrol,TransitButtonComponents  tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
		tbc.change();
		iniTransitReceiptCar();
	}

	private void iniTransitReceiptCar() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);	
		date=new DateChooser(this, 212, 81);
		
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] transitAgency=new String[trans.size()];
		for(int i=0;i<trans.size();i++)
		{
			transitAgency[i]=trans.get(i).getName();
		}
		
		departure=new JComboBox(transitAgency);
		departure.setBounds(195,56,85,20);
		add(departure);
		
		destination=new JComboBox(transitAgency);
		destination.setBounds(343,56,85,20);
		add(destination);
		
		carId=new JTextField();
		carId.setBounds(192, 110, 184, 18);
		add(carId);
		
		driverId=new JTextField();
		driverId.setBounds(192, 138, 141, 18);
		add(driverId);
		
		transiterId = new JTextField();
		transiterId.setBounds(405, 138, 50, 18);
		add(transiterId);

		fare = new JLabel();
		fare.setBounds(180, 192, 70, 30);
		fare.setForeground(Color.GRAY);
		fare.setFont(new Font("Dialog", 0, 18));
		fare.setVisible(false);
		add(fare);
		
		carTransitId = new JLabel();
		carTransitId.setBounds(290, 165, 140, 30);
		carTransitId.setForeground(Color.GRAY);
		carTransitId.setFont(new Font("Dialog", 0, 18));
		carTransitId.setVisible(false);
		add(carTransitId);
		
		warning=new JLabel();
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
	
		name.add("订单条形码号");
		barId = new newTable(data,name,this,true);
		barId.setBounds(137, 218, 329, 191);
		barId.initialBlank(1);
		barId.join();

		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(388, 419, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((carId.getText().equals("")) || (driverId.getText().equals(""))
						|| (transiterId.getText().equals("")) ) {
					warning.setText("尚未完成对必填项的填写");
					warning.setVisible(true);
					repaint();
				} 
				else
				{
					ArrayList<String> BarIdArray=new ArrayList<String>();
					for(int i=0;i<barId.getRowCount()-1;i++){
						BarIdArray.add(barId.getValueAt(i,0,false));
				}
				TransitSheetVO vo=new TransitSheetVO();
				vo.setTransportType(TransportType.PLANE);
					//destinationId项不存在，用null写入
				TransitCarInformation carInf = new TransitCarInformation(date.getTime(),
						departure.getSelectedItem().toString(), destination.getSelectedItem().toString(),
						transiterId.getText().toString(), BarIdArray);
				carInf.setCarId(carId.getText());
				carInf.setDriverId(driverId.getText());
				vo.setTransitInformation(carInf);
				ResultMessage result=deliverblservice.transitCarReceipt(vo);
				//成功
				if(result.getResult()==Result.SUCCESS){
					
					warning.setText("提交成功");
					warning.setVisible(true);
				
					String[] message=result.getMessage().split(" ");
					fare.setText(message[0] + "元");
					fare.setVisible(true);
				
					carTransitId.setText(message[1]);
					carTransitId.setVisible(true);
				
					repaint();
				}
				else
				{
					//失败
					warning.setText(result.getMessage());
					warning.setVisible(true);
					repaint();
				}
			}
		}
	});	
}
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background05.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
