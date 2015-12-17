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
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
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
	
	private JButton confirm;
	private JComboBox agencyId;
	private JTextField carId;
	private JTextField officerId;
	private JTextField driverId;
	private newTable barId;
	private JLabel warning;
	private JLabel transportId;
	private JLabel fare;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private DateChooser date;
	
	private ImageIcon confirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");

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
		
		transportId = new JLabel();
		transportId.setBounds(290, 165, 140, 30);
		transportId.setForeground(Color.GRAY);
		transportId.setFont(new Font("Dialog", 0, 18));
		transportId.setVisible(false);
		add(transportId);
		
		name.add("订单条形码号");
		
		barId = new newTable(data,name,this,true);
		barId.setBounds(141,218,321,191);
		barId.initialBlank(2);
		barId.join();
		
		fare = new JLabel();
		fare.setBounds(180, 192, 70, 30);
		fare.setForeground(Color.GRAY);
		fare.setFont(new Font("Dialog", 0, 18));
		fare.setVisible(false);
		add(fare);
		
		warning=new JLabel();
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
		
		confirm = new JButton(confirmIcon);
		confirm.setBounds(388, 419, 76, 27);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((carId.getText().equals("")) || (driverId.getText().equals(""))
						|| (officerId.getText().equals("")) ) {
					warning.setText("尚未完成对必填项的填写");
					warning.setVisible(false);
					repaint();
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
					//成功
					if(result.getResult()==Result.SUCCESS){
						warning.setText("提交成功");
						warning.setVisible(true);
						
						fare.setText(Double.toString(loadInf.getFare()) + "元");
						fare.setVisible(true);

						transportId.setText(loadInf.getTransportId());
						transportId.setVisible(false);

						repaint();
					}else{
						//失败
						warning.setText(result.getMessage());
						warning.setVisible(true);
						repaint();
					}
				}
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
