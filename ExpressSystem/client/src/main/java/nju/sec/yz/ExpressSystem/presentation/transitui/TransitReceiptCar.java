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
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class TransitReceiptCar extends JPanel{
	DeliverBlService deliverblservice=new DeliverController();
	ClientControler maincontrol;
	TransitButtonComponents tbc;
	
	private AgencyBlService manager = new ManagerController();
	
	private newJText carId;
	private newJText driverId;
	private newJText transiterId;
	
	private newJLabel departure;
	private newJCombo destination;
	private newTable barId;
	private newJBut confirm;
	private newJLabel carTransitId;
	private newJLabel fare;
	private newJLabel warning=new newJLabel();
	
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private DateChooser date;
	public TransitReceiptCar(ClientControler maincontrol,TransitButtonComponents  tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
//		tbc.change();
		iniTransitReceiptCar();
	}

	private void iniTransitReceiptCar() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);	
		date=new DateChooser(this, 216, 81);
		
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] transitAgency=new String[trans.size()];
		for(int i=0;i<trans.size();i++)
		{
			transitAgency[i]=trans.get(i).getName();
		}
		
		TransitVO transit=deliverblservice.getCurrentTransit();
		departure=new newJLabel();
		departure.setText(transit.name);
		departure.setBounds(195,56,110,20);
		departure.setVisible(true);
		add(departure);
		
		destination=new newJCombo(transitAgency);
		destination.setBounds(365,56,110,20);
		add(destination);
		
		carId=new newJText();
		carId.setBounds(192, 110, 184, 18);
		add(carId);
		
		driverId=new newJText();
		driverId.setBounds(192, 138, 141, 18);
		add(driverId);
		
		transiterId = new newJText();
		transiterId.setBounds(405, 138, 50, 18);
		add(transiterId);

		fare = new newJLabel();
		fare.setBounds(180, 191, 70, 22);
		fare.setVisible(false);
		add(fare);
		
		carTransitId = new newJLabel();
		carTransitId.setBounds(290, 165, 180, 22);
		carTransitId.setVisible(false);
		add(carTransitId);
		
		warning.setVisible(false);
		add(warning);
	
		name.add("订单条形码号");
		barId = new newTable(data,name,this,true);
		barId.setBounds(137, 218, 329, 191);
		barId.initialBlank(1);
		barId.join();

//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(388, 419, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((carId.getText().equals("")) || (driverId.getText().equals(""))
						|| (transiterId.getText().equals("")) ) {
					warning.NotFilled();
					
//					fare.setText("KKKKKKKK");
//					fare.setVisible(true);
				
//					carTransitId.setText("AAAAAAAAAAAAAAAA");
//					carTransitId.setVisible(true);
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
						departure.getText(), destination.getSelectedItem().toString(),
						transiterId.getText().toString(), BarIdArray);
				carInf.setCarId(carId.getText());
				carInf.setDriverId(driverId.getText());
				vo.setTransitInformation(carInf);
				ResultMessage result=deliverblservice.transitCarReceipt(vo);
				warning.Reply(result);
				//成功
				if(result.getResult()==Result.SUCCESS){
					
					String[] message=result.getMessage().split(" ");
					fare.setText(message[0].substring(0, message[0].indexOf('.')+2) + "元");
					fare.setVisible(true);
				
					carTransitId.setText(message[1]);
					carTransitId.setVisible(true);
				
					confirm.setEnabled(false);
				}
				
			}
				repaint();
		}
	});	
}
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background05.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
