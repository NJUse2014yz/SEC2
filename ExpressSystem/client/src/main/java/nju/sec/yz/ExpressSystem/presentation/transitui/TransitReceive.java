package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
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

	private JButton confirm;
	
//	private JTextField departure;
	private JLabel departure;
	private JComboBox state;
	private JTextField transitSheetId;
	// private JTextField transitId;

	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	private JLabel warning = new JLabel();

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
		
		name.add("快递单号");
		name.add("到达状态");
		table = new newTable(data, name, this, true);
		table.setBounds(141, 138, 321, 191);
		String[] sta = { "完整", "损坏", "丢失" };
		table.setJComboBox(new JComboBox(sta),1);
		table.join();
//		transitId=new JTextField();
//		transitId.setBounds(252, 136, 93, 15);
//		add(transitId);
		
		transitSheetId=new JTextField();
		transitSheetId.setBounds(222, 58, 122, 18);
		add(transitSheetId);
		
		// 监听回车
		transitSheetId.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) { // 按回车键执行相应操作KeyEvent.VK_ENTER;
				// if(transitSheetId.getText().toString().length()==11)
					System.out.println("!!!!!!!!!!!!!!!!!");
					System.out.println(transitSheetId.getText().toString());
					if (deliverBlService.getBarIdList(transitSheetId.getText()) == null) {
						warning.setText("中转单号错误");
						warning.setBounds(198, 490, 463 - 198, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);
						repaint();
<<<<<<< HEAD
					} else {
						BarIdsVO vo = deliverBlService
								.getBarIdList(transitSheetId.getText());
						ArrayList<String> Ids = (ArrayList<String>) vo.barIds;
						changeData(Ids);
						
						departure = new JLabel();
						departure.setText(vo.fromAgency);
						departure.setBounds(204, 106, 71, 18);
						departure.setFont(new Font("Dialog", 1, 15));
						departure.setForeground(Color.LIGHT_GRAY);
						add(departure);

						repaint();
					}
				}
			}
		});	
=======
		         }else{
		        	BarIdsVO vo= deliverBlService.getBarIdList(transitSheetId.getText());
		        	ArrayList Ids=(ArrayList) vo.barIds;
		        	
		        	departure=new JLabel();
		        	departure.setText(vo.fromAgency);
		    		departure.setBounds(204, 106, 71, 18);
		    		departure.setFont(new Font("Dialog", 1, 15));
		    		departure.setForeground(Color.LIGHT_GRAY);
		    		add(departure);
		    		
		        	String[] sta={"完整","损坏","丢失"};
		     		state=new JComboBox(sta);
		     		
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
		     		
		     	      repaint();
		         }
		        } 
		      } 
		    });
		
		
		
		//使得表格大小随订单信息的填入而改变
//		model.addTableModelListener(new TableModelListener(){
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				// TODO Auto-generated method stub
//				int num=model.getRowCount();
//				String temp=(String) model.getValueAt(num-1, 1);
//				if(temp!=""){
//					Object[] conponent={Integer.toString(num+1),""};
//					((DefaultTableModel) model).addRow(conponent); 
//				}
//				repaint();
//			}
//		});
		
		
>>>>>>> 438a2039b2b076475fc75653bdd4452f430ddd06
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(389, 334, 76, 27);
		add(confirm);
		confirm.setVisible(false);
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if((transitSheetId.getText().equals(""))){
//						(departure.getText().equals(""))||
					warning.setText("尚未完成对项目的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				}else
				{
					ArriveInformation arrive=new ArriveInformation();
//					arrive.setDeparture(departure.getText());
					arrive.setTime(date.getTime());
//					arrive.setTransitId(transitId.getText());
					
					ArrayList<ArriveState> statelist=new ArrayList<ArriveState>();
					for(int i=0;i<table.getRowCount()-1;i++){
						String temp=table.getValueAt(i, 1, true);
						statelist.add(getState(temp));
					}
					arrive.setState(statelist);
					
					
					TransitArriveSheetVO vo=new TransitArriveSheetVO();
					vo.setTransitArriveInformation(arrive);
//					vo.setTransitId(transitId.getText());
					vo.setTransitId(transitSheetId.getText());
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
	private void changeData(List<String> bl)
	{
		data.removeAllElements();
		Vector<String> vector=new Vector<String>();
		for (int i = 0; i < bl.size(); i++) {
			vector.add(bl.get(i));
			vector.add("");
		}
		data.add(vector);
		
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
