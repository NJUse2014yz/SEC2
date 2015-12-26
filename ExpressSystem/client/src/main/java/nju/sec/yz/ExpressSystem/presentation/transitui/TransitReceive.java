package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.BarIdsVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;

/*
 * 2015/11/29
 * zhangqi
 * 删除中转中心编号的填写
 */
public class TransitReceive extends JPanel {

	ClientControler maincontrol;
	DeliverBlService deliverBlService = new DeliverController();
	TransitButtonComponents tbc;

	private newJBut searchInf;
	private newJBut confirm;
	
	private newJLabel departure;
	private newJText transitSheetId;
	String[] sta={"完整","损坏","丢失"};
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private newJLabel warning = new newJLabel();

	private DateChooser date;

	public TransitReceive(ClientControler maincontrol, TransitButtonComponents tbc) {
		this.maincontrol = maincontrol;
		this.tbc = tbc;
		tbc.setNextPanel(this);

		initTransitReceive();
	}

	private void initTransitReceive() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		date=new DateChooser(this,222,80);
		
		name.add("快递单号");
		name.add("到达状态");
		
 		table = new newTable(data,name,this,false);
 		table.setJComboBox(new newJCombo(sta), 1);
 	    table.setBounds(141,138,321,191);
 	    table.join();
		
		transitSheetId=new newJText();
		transitSheetId.setBounds(222, 58, 122, 18);
		add(transitSheetId);
		
		searchInf=new newJBut("确定");
		searchInf.setBounds(364,54,76,27);
		add(searchInf);
		
		
		add(warning);
		
		//监听对订单信息的搜索
		searchInf.addMouseListener(new MouseAdapter(){ 
		      public void mousePressed(MouseEvent e)    
		      {    
		         if(deliverBlService.getBarIdList(transitSheetId.getText())==null){
		        	 warning.setText("中转单号错误");
						warning.setBounds(198, 490, 463 - 198, 30);
						warning.setForeground(Color.red);
						warning.setVisible(true);

						confirm.setVisible(false);
					} else {
		        	BarIdsVO vo= deliverBlService.getBarIdList(transitSheetId.getText());
		        	ArrayList<String> Ids=(ArrayList<String>) vo.barIds;
		        	
		        	departure=new newJLabel(vo.fromAgency);
		    		departure.setBounds(204, 109,140, 18);
		    		add(departure);
		    		
		     		//
		    		data.removeAllElements();
		     		for(int i=0;i<Ids.size();i++){
		     			Vector<String> vector=new Vector<String>();
		     			vector.add(Ids.get(i));
		     			vector.add("");
		     			data.add(vector);
		     		}
		     		table.resetData();
		     		//
		     		warning.setText("");
		     		
		     	    confirm.setVisible(true);
		     		
		         }
		         repaint();
		        } 
		    });
		
		confirm = new newJBut("确定");
		confirm.setBounds(389, 334, 76, 27);
		add(confirm);
		confirm.setVisible(false);
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if((transitSheetId.getText().equals(""))){
//						(departure.getText().equals(""))||
					warning.NotFilled();
				}else
				{
					ArriveInformation arrive=new ArriveInformation();
					arrive.setDeparture(departure.getText());
					arrive.setTime(date.getTime());
					arrive.setTransitSheetId(transitSheetId.getText());
				
					ArrayList<ArriveState> statelist=new ArrayList<ArriveState>();
					for(int i=0;i<table.getRowCount();i++){
						String temp=table.getValueAt(i, 1, true);
						statelist.add(getState(temp));
					}
					arrive.setState(statelist);
					
					
					TransitArriveSheetVO vo=new TransitArriveSheetVO();
					vo.setTransitArriveInformation(arrive);
//					vo.setTransitId(transitId.getText());
					
					ResultMessage result=deliverBlService.transitReceiveReceipt(vo);
					warning.Reply(result);
					repaint();
				}
				
				
			}
		});
	}

	private static ArriveState getState(String state) {
		switch (state) {
		case "完整":
			return ArriveState.PERFECT;
		case "损坏":
			return ArriveState.BROKEN;
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
