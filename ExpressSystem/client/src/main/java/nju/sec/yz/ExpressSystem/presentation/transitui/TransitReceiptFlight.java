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
import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
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

public class TransitReceiptFlight extends JPanel{
	
	DeliverBlService deliverblservice=new DeliverController();
	private AgencyBlService manager = new ManagerController();
	
	ClientControler maincontrol;
	TransitButtonComponents tbc;
	
	
	private newJText flightId;
	private newJText shelfId;
	private newJText transiterId;
	
	private newJCombo departure;
	private newJCombo destination;
	private newTable barId;
	private newJBut confirm;
	private newJLabel flightTransitId;
	private newJLabel fare;
	private newJLabel warning=new newJLabel();
	private DateChooser date;
	
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	public TransitReceiptFlight(ClientControler maincontrol,TransitButtonComponents tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
		tbc.change();
		iniTransitReceiptFlight();
	}

	private void iniTransitReceiptFlight() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		date=new DateChooser(this, 212, 81);

		ArrayList<TransitVO> trans=manager.observeAllTransit();
		String[] transitAgency=new String[trans.size()];
		for(int i=0;i<trans.size();i++){
			transitAgency[i]=trans.get(i).getName();
			}
		
		departure=new newJCombo(transitAgency);
		departure.setBounds(195,56,110,20);
		add(departure);
		
		destination=new newJCombo(transitAgency);
		destination.setBounds(368,56,110,20);
		add(destination);
		
		flightId=new newJText();
		flightId.setBounds(192, 108, 184, 18);
		add(flightId);
		
		shelfId=new newJText();
		shelfId.setBounds(192, 136, 141, 18);
		add(shelfId);
		
		transiterId = new newJText();
		transiterId.setBounds(405, 136, 50, 18);
		add(transiterId);

		fare = new newJLabel();
		fare.setBounds(180, 188, 70, 22);
		fare.setVisible(false);
		add(fare);
		
		flightTransitId = new newJLabel();
		flightTransitId.setBounds(290, 163, 140, 22);
		flightTransitId.setVisible(false);
		add(flightTransitId);
		
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setVisible(false);
		add(warning);
	
		name.add("订单条形码号");
		barId = new newTable(data,name,this,true);
		barId.setBounds(137, 218, 329, 191);
		barId.initialBlank(1);
		barId.join();
			
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(388, 419, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((flightId.getText().equals("")) || (shelfId.getText().equals(""))
						|| (transiterId.getText().equals("")) ) {
					warning.NotFilled();
				} else {
					ArrayList<String> BarIdArray=new ArrayList<String>();
					for(int i=0;i<barId.getRowCount()-1;i++){
						BarIdArray.add((String) barId.getValueAt(i,0,false));
					}
					TransitSheetVO vo=new TransitSheetVO();
					//destinationId项不存在，用null写入
					TransitFlightInformation flightInf = new TransitFlightInformation(date.getTime(),
							departure.getSelectedItem().toString(), destination.getSelectedItem().toString(),
							transiterId.getText().toString(), BarIdArray);
					flightInf.setFlightId(flightId.getText());
					flightInf.setShelfId(shelfId.getText());
				vo.setTransitInformation(flightInf);
				vo.setTransportType(TransportType.PLANE);
				ResultMessage result=deliverblservice.transitFlightReceipt(vo);
				//成功
				warning.Reply(result);
				if(result.getResult()==Result.SUCCESS){
					String[] message=result.getMessage().split(" ");
					fare.setText(message[0] + "元");
					fare.setVisible(true);
				
					flightTransitId.setText(message[1]);
					flightTransitId.setVisible(true);
					
					confirm.setEnabled(false);
				}
				}
				repaint();
			}
		});	

	}
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
