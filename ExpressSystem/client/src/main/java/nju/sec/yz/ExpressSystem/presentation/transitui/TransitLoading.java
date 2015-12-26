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
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class TransitLoading extends JPanel{
	
	DeliverBlService deliverblservice=new DeliverController();
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontrol;
	private TransitButtonComponents tbc;
	
	private newJBut confirm;
	private newJCombo agencyId;
	private newJText carId;
	private newJText officerId;
	private newJText driverId;
	private newTable barId;
	private newJLabel warning=new newJLabel();
	private newJLabel transportId;
	private newJLabel fare;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private DateChooser date;
	
	private ImageIcon confirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");

	public TransitLoading(ClientControler maincontrol,TransitButtonComponents tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
		
		initTransitLoading();
	}
	private void initTransitLoading() {
		setLayout(null);
		setSize(490, 550);
		
		date=new DateChooser(this, 220, 81);
		
		int count=0;
		ArrayList<TransitVO> trans=manager.observeAllTransit();
		for(int i=0;i<trans.size();i++){
			count+=trans.get(i).getPositions().size();
		}
		String[] agency=new String[count];
		count=0;
		for(int i=0;i<trans.size();i++){
			ArrayList<PositionVO> temp=(ArrayList<PositionVO>) trans.get(i).getPositions();
			for(int k=0;k<temp.size();k++){
				agency[count]=temp.get(k).getName();
				count++;
			}
			
		}
		agencyId=new newJCombo(agency);
		agencyId.setBounds(202,56,160,20);
		add(agencyId);
		
		
		carId=new newJText();
		carId.setBounds(213, 111, 93, 18);
		add(carId);
		
		officerId=new newJText();
		officerId.setBounds(202, 136, 99, 18);
		add(officerId);
		
		driverId=new newJText();
		driverId.setBounds(368, 136, 90, 18);
		add(driverId);
		
		transportId = new newJLabel();
		transportId.setBounds(290, 167, 140, 18);
		transportId.setVisible(false);
		add(transportId);
		
		name.add("订单条形码号");
		
		barId = new newTable(data,name,this,true);
		barId.setBounds(141,218,321,191);
		barId.initialBlank(1);
		barId.join();
		
		fare = new newJLabel();
		fare.setBounds(181, 192, 70, 18);
		fare.setVisible(false);
		add(fare);
		
		
		confirm = new newJBut("确定");
		confirm.setBounds(388, 419, 76, 27);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((carId.getText().equals("")) || (driverId.getText().equals(""))
						|| (officerId.getText().equals("")) ) {
					warning.NotFilled();
					
				} else {
					ArrayList<String> BarIdArray=new ArrayList<String>();
					for(int i=0;i<barId.getRowCount()-1;i++){
						BarIdArray.add(barId.getValueAt(i,0,false));
					}
					TransitLoadSheetVO vo=new TransitLoadSheetVO();
					//destinationId项不存在，用null写入
					LoadInformation loadInf = new LoadInformation(
							date.getTime(), null, agencyId.getSelectedItem().toString(),
							carId.getText(), officerId.getText(),driverId.getText());
					vo.setTransitLoadInformation(loadInf);
					vo.setBarIds(BarIdArray);
					ResultMessage result=deliverblservice.transitLoadingReceipt(vo);
					warning.Reply(result);
					//成功
					if(result.getResult()==Result.SUCCESS){
						fare.setText(Double.toString(loadInf.getFare()) + "元");
						fare.setVisible(true);

						transportId.setText(loadInf.getTransportId());
						transportId.setVisible(true);
						confirm.setEnabled(false);
					}
				}
				add(warning);
				repaint();
			}
		});	
		add(confirm);
			
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Image img01 = new ImageIcon("graphic/transit/background/background02.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
