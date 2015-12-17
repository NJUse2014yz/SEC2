package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
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
	
//	private JTextField departure;
	private newJLabel departure;
	private newJCombo state;
	private newJText transitSheetId;
	// private JTextField transitId;

	private JTable table;
	private TableModel model;
	private JScrollPane jsc;

	private newJLabel warning = new newJLabel();

	private DateChooser date;

	public TransitReceive(ClientControler maincontrol, TransitButtonComponents tbc) {
		this.maincontrol = maincontrol;
		this.tbc = tbc;
		tbc.setNextPanel(this);
		tbc.iniTransit();

		initTransitReceive();
	}

	private void initTransitReceive() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		date=new DateChooser(this,222,80);
		
		
//		transitId=new JTextField();
//		transitId.setBounds(252, 136, 93, 15);
//		add(transitId);
		
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
		        	ArrayList Ids=(ArrayList) vo.barIds;
		        	
		        	departure=new newJLabel(vo.fromAgency);
		    		departure.setBounds(204, 106, 71, 18);
		    		add(departure);
		    		
		        	String[] sta={"完整","损坏","丢失"};
		     		state=new newJCombo(sta);
		     		
		     		Object[][] TableData=new Object[Ids.size()][3];
		     		String[] columnTitle={"编号","快递单号","到达状态"};
		     		for(int i=0;i<Ids.size();i++){
		     			TableData[i][0]=i+1;
		     			TableData[i][1]=Ids.get(i);
		     		}
		     		
		     		model = new DefaultTableModel(TableData,columnTitle);
		     		table = new JTable(model);
		     		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(state));
		     		
		     		table.getColumnModel().getColumn(0).setMaxWidth(30);
		     		table.getColumnModel().getColumn(2).setMinWidth(80);
		     		
		     	      //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		     	      JScrollPane jsc=new JScrollPane(table);  
		     	      jsc.setVisible(true);
		     	      jsc.setBounds(141,138,321,191);
		     	      add(jsc);
		     	      
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
//					arrive.setDeparture(departure.getText());
					arrive.setTime(date.getTime());
//					arrive.setTransitId(transitId.getText());
					
					ArrayList<ArriveState> statelist=new ArrayList<ArriveState>();
					for(int i=0;i<model.getRowCount()-1;i++){
						String temp=(String) table.getCellEditor(i,2).getCellEditorValue();
						statelist.add(getState(temp));
					}
					arrive.setState(statelist);
					
					
					TransitArriveSheetVO vo=new TransitArriveSheetVO();
					vo.setTransitArriveInformation(arrive);
//					vo.setTransitId(transitId.getText());
					vo.setTransitId(transitSheetId.getText());
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
