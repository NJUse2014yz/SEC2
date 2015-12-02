package nju.sec.yz.ExpressSystem.presentation.transitui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class TransitLoading extends JPanel{
	
	DeliverBlService deliverblservice=new DeliverController();
	private AgencyBlService manager=new ManagerController();
	
	private ClientControler maincontrol;
	private TransitButtonComponents tbc;
	
	private JButton confirm;
	private JComboBox agencyId;
	private JTextField carId;
	private JTextField officerId;
	private JTextField driverId;
	private JTable barId;
	private TableModel model;
	
	private JLabel warning=new JLabel();
	private JLabel transportId;
	private JLabel fare;
	
	
	public TransitLoading(ClientControler maincontrol,TransitButtonComponents tbc) {
		this.maincontrol=maincontrol;
		this.tbc=tbc;
		tbc.setNextPanel(this);
		tbc.iniTransit();
		
		initTransitLoading();
		
		
	}
	private void initTransitLoading() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		DateChooser date=new DateChooser(this, 220, 81);
		
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
		agencyId=new JComboBox(agency);
		agencyId.setBounds(202,56,125,20);
		add(agencyId);
		
		
		carId=new JTextField();
		carId.setBounds(213, 111, 93, 18);
		add(carId);
		
		officerId=new JTextField();
		officerId.setBounds(202, 136, 99, 18);
		add(officerId);
		
		driverId=new JTextField();
		driverId.setBounds(368, 136, 90, 18);
		add(driverId);
		
		
		
		String[][] tableData = {{"1",""}};
		String[] columnTitle = {"编号","订单条形码号"};  
			      //以二维数组和一维数组来创建一个JTable对象  
		
				model = new DefaultTableModel(tableData,columnTitle);
				barId = new JTable(model);

//			      barId = new JTable(tableData , columnTitle);  
//			      model=barId.getModel();
			      //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
			      JScrollPane jsc=new JScrollPane(barId);  
			      jsc.setVisible(true);
			      jsc.setBounds(141,218,321,191);
			      add(jsc);
			
			
			
			//使得表格大小随订单信息的填入而改变
			model.addTableModelListener(new TableModelListener(){
				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
					int num=model.getRowCount();
					String temp=(String) model.getValueAt(num-1, 1);
					if(temp!=""){
						String[] conponent={Integer.toString(num+1),""};
						((DefaultTableModel) model).addRow(conponent); 
					}
					repaint();
				}
			});
			
			
			/*
			 * 确定
			 */
			ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
			confirm = new JButton(cinfirmIcon);
			confirm.setBounds(388, 419, 76, 27);
			add(confirm);
			setVisible(true);

			confirm.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// 判断必填项是否填写完成
					if ((carId.getText().equals("")) || (driverId.getText().equals(""))
							|| (officerId.getText().equals("")) ) {
						warning.setText("尚未完成对必填项的填写");
						warning.setBounds(198, 490, 463 - 198, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);
						repaint();
					} else {
						ArrayList<String> BarIdArray=new ArrayList<String>();
						for(int i=0;i<barId.getRowCount()-1;i++){
							BarIdArray.add((String) barId.getValueAt(i, 1));
						}
						System.out.println(BarIdArray.get(3));
					TransitLoadSheetVO vo=new TransitLoadSheetVO();
						//destinationId项不存在，用null写入
					LoadInformation loadInf = new LoadInformation(date.getTime(), agencyId.getSelectedItem().toString(),
							null, carId.getText(), officerId.getText(), driverId.getText());	
					vo.setTransitLoadInformation(loadInf);
					vo.setBarIds(BarIdArray);
					ResultMessage result=deliverblservice.transitLoadingReceipt(vo);
					//成功
					if(result.getResult()==Result.SUCCESS){
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);
						
						fare = new JLabel();
						fare.setText(Double.toString(loadInf.getFare()) + "元");
						fare.setBounds(180, 192, 70, 30);
						fare.setForeground(Color.GRAY);
						fare.setFont(new Font("Dialog", 0, 18));
						fare.setVisible(true);
						add(fare);
						
						transportId = new JLabel();
						transportId.setText(loadInf.getTransportId());
						transportId.setBounds(290, 165, 140, 30);
						transportId.setForeground(Color.GRAY);
						transportId.setFont(new Font("Dialog", 0, 18));
						transportId.setVisible(true);
						add(transportId);
						
						
						repaint();
					}else{
						//失败
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
	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background02.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
