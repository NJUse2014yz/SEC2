package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyAdd extends JPanel {
	private AgencyBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	
	private JComboBox AgencyType;
	private JTextField Location;
	private JTextField TransitId;
	private JTextField name;
	private JTextField Id;
	
//	private JTable positions; 
	private JScrollPane jsc;
	private JTable positions;
	private TableModel positionId;
	
	private JButton confirm;
	
	JLabel warning=new JLabel();
	
	public ManagerAgencyAdd(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();

		iniManagerAgencyAdd();
	}

	private void iniManagerAgencyAdd() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		String[] type={"营业厅","中转中心"};
		AgencyType=new JComboBox(type);
		AgencyType.setBounds(214, 57, 85, 20);
		add(AgencyType);
		confirmPosition();
//		this.removeAll();
		original();
		jsc.setVisible(false);
		
		AgencyType.addActionListener(new ActionListener(){           
			public void actionPerformed(ActionEvent e) {
				if(AgencyType.getSelectedItem().equals("营业厅")){
					removeAll();
					original();
					jsc.setVisible(false);
					add(AgencyType);
					confirmPosition();
					mbc.add();
					repaint();
				}else{
					//中转中心
					removeAll();
					original();
					TransitId.setVisible(false);
					add(AgencyType);
					confirmTransit();
					mbc.add();
					repaint();
				}
              }

			  
		});
		
	}
		
	private void confirmTransit(){
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(382, 417, 72, 24);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((Location.getText().equals("")) || (name.getText().equals(""))
						|| (Id.getText().equals("")) ) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					ArrayList<PositionVO> Ids=new ArrayList<PositionVO>();
					for(int i=0;i<positionId.getRowCount()-1;i++){
						PositionVO pvo=new PositionVO();
						pvo.setTransitId(Id.getText());
						pvo.setId((String) positionId.getValueAt(i, 1));
//						Ids.add((String) positionId.getValueAt(i, 1));
						Ids.add(pvo);
						
					}
					TransitVO vo=new TransitVO(name.getText().toString(),Id.getText(),Ids,Location.getText());
				ResultMessage result=manager.addTransit(vo);
				//成功
				if(result.getResult()==Result.SUCCESS){
					warning.setText("提交成功");
					warning.setBounds(270, 490, 70, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					
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
	
	private void confirmPosition(){
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(382, 417, 72, 24);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((Id.getText().equals("")) || (name.getText().equals(""))
						|| (TransitId.getText().equals("")) || (Location.getText().equals(""))) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
				PositionVO vo=new PositionVO();
				vo.setName(name.getText());
				vo.setId(Id.getText());
				vo.setLocation(Location.getText());
				vo.setTransitId(TransitId.getText());
				
				ResultMessage result=manager.addPosition(vo);
				//成功
				if(result.getResult()==Result.SUCCESS){
					warning.setText("提交成功");
					warning.setBounds(270, 490, 70, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					
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

	private void original() {
		// TODO Auto-generated method stub
		
		Location=new JTextField();
		Location.setBounds(204, 89, 78, 18);
		add(Location);
		
		Id=new JTextField();
		Id.setBounds(190, 118, 73, 18);
		add(Id);
		
		name=new JTextField();
		name.setBounds(190, 147, 140, 18);
		add(name);
		
		TransitId=new JTextField();
		TransitId.setBounds(247, 179, 140, 18);
		add(TransitId);
		
		String[][] tableData = {{"1",""}};
		String[] columnTitle = {"编号","所有营业厅编号"};  
			      //以二维数组和一维数组来创建一个JTable对象  
		
		positionId = new DefaultTableModel(tableData,columnTitle);
		positions = new JTable(positionId);

//			      barId = new JTable(tableData , columnTitle);  
//			      model=barId.getModel();
			      //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
//			      JScrollPane
			      jsc=new JScrollPane(positions);  
			      jsc.setVisible(true);
			      jsc.setBounds(141,176,321,191);
			      add(jsc);
			
			
			
			//使得表格大小随订单信息的填入而改变
			      positionId.addTableModelListener(new TableModelListener(){
				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
					int num=positionId.getRowCount();
					String temp=(String) positionId.getValueAt(num-1, 1);
					if(temp!=""){
						String[] conponent={Integer.toString(num+1),""};
						((DefaultTableModel) positionId).addRow(conponent); 
					}
					repaint();
				}
			});
		
		repaint();
		
	}    

	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background02.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
