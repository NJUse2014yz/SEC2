package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;

public class TransitReceive extends JPanel{
	
	ClientControler maincontrol;
	DeliverBlService deliverBlService = new DeliverController();
	TransitButtonComponents tbc;
	
	private JButton confirm;
	private JTextField departure;
	private JComboBox state;
	private JTextField transitSheetId;
	private JTextField transitId;
	
	private JLabel warning=new JLabel();
	
	public TransitReceive(ClientControler maincontrol,TransitButtonComponents tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
		tbc.iniTransit();
		
		initTransitReceive();
	}
	
	private void initTransitReceive() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		departure=new JTextField();
		departure.setBounds(204, 58, 71, 15);
		add(departure);
		
		DateChooser date=new DateChooser(this,222,80);
		
		String[] sta={"完整","损坏","丢失"};
		state=new JComboBox(sta);
		state.setBounds(243, 109, 103, 20);
		add(state);
		
		transitId=new JTextField();
		transitId.setBounds(252, 136, 93, 15);
		add(transitId);
		
		transitSheetId=new JTextField();
		transitSheetId.setBounds(224, 162, 122, 15);
		add(transitSheetId);
		
		
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(391, 210, 76, 27);
		add(confirm);
		setVisible(true);
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if((departure.getText().equals(""))||(transitId.getText().equals(""))){
					warning.setText("尚未完成对项目的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				}else{
					ArriveInformation arrive=new ArriveInformation();
					arrive.setDeparture(departure.getText());
					arrive.setTime(date.getTime());
					arrive.setState(getState(state));
//					arrive.setTransitId(transitId.getText());
					
					TransitArriveSheetVO vo=new TransitArriveSheetVO();
					vo.setTransitArriveInformation(arrive);
					vo.setTransitId(transitId.getText());
					vo.setTransitSheetId(transitSheetId.getText());
					ResultMessage result=deliverBlService.transitReceiveReceipt(vo);
					if(result.getResult()==Result.SUCCESS){
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);

						repaint();
					}else{
						//失败的情况
						warning.setText(result.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					}
				}
				
				
			}
		});
	}
	
	private static ArriveState getState(JComboBox state){
		String temp=state.getSelectedItem().toString();
		switch (temp){
		case "完整":
			return ArriveState.PERFECT;
		case "损坏":
			return ArriveState.Broken;
		case "丢失":
			return ArriveState.LOST;
		default:
			return null;
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background06.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
