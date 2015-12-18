package nju.sec.yz.ExpressSystem.presentation.managerui;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionArriveUi;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyList extends JPanel{
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	
	private JTextField searchnum;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private JButton search;
	private JButton back;
	
	private JLabel warning=new JLabel();
	
	private ArrayList<TransitVO> transits;
	private ArrayList<PositionVO> positions;
	
	private ArrayList<String> reference;
	
	//判断是modify还是observe
	private String type;
	
	public ManagerAgencyList(ClientControler maincontroler,ManagerButtonComponent mbc,String str) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		this.type=str;
		mbc.changePanel(this);
		mbc.change();
		iniManagerAgencyList();
	}

	private void iniManagerAgencyList() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("所在地");
		name.add("编号");
		name.add("名称");
		
		table=new newTable(data,name,this,false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setTableSelect();
	    table.setBounds(137,94,318,181);
		table.join();
		
		original();
		
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
		
		searchnum=new JTextField();
		searchnum.setBounds(216, 62, 220, 21);
		add(searchnum);
		
		search=new JButton();
		search.setBackground(new Color(0,0,255));  
		search.setOpaque(false); //设置背景透明
		search.setBounds(436,62,21,21);
		add(search);
	    
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if (searchnum.getText().equals("")) {
					warning.setText("信息未填写");
					warning.setVisible(true);
					repaint();
				}else{
					AgencyListVO agency=manager.observeTransitByName(searchnum.getText());
					if(agency==null)
					{
						warning.setText("不存在该机构");
						warning.setVisible(true);
						repaint();
					}
					else
					{
						warning.setVisible(false);
						changeData(agency);
						table.resetData();
					}
				}
			}
		});
			
		table.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 int num=table.getSelectedRow();
				 System.out.println(num);
				 if(num<transits.size()){
					 reference=new ArrayList<String>();
					 reference.add(transits.get(num).getId());
					 System.out.println(reference.size());
				 }else{
					 reference=new ArrayList<String>();
					 reference.add(positions.get(num-transits.size()).getTransitId());
					 reference.add(positions.get(num-transits.size()).getId());
				 }
					 if(type.equals("modify")){
						 maincontroler.mainFrame.nextPanel(new ManagerAgencyModify(maincontroler,mbc,reference));
					 }else{
						 maincontroler.mainFrame.nextPanel(new ManagerAgencyObserve(maincontroler,mbc,reference));
					 }
				 }
			});
			
			
			ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
			back = new JButton(backIcon);
			back.setBounds(370, 286, 81, 20);
			add(back);
			
			back.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					original();
					table.resetData();
				}
			});	
	}
	private void original(){
		ArrayList<TransitVO> t=manager.observeAllTransit();
		ArrayList<PositionVO> p=new ArrayList<PositionVO>();
		for(int i=0;i<t.size();i++)
		{
			ArrayList<PositionVO> remp=(ArrayList<PositionVO>) t.get(i).positions;
			for(int j=0;j<remp.size();j++)
			{
				p.add(remp.get(j));
			}
		}
		changeData(new AgencyListVO(t,p));
		table.resetData();
	}
	private	void changeData(AgencyListVO agency)
	{
		System.out.println(data.size());
		data.removeAllElements();
		transits=(ArrayList<TransitVO>) agency.transits;
		positions=(ArrayList<PositionVO>) agency.positions;
		for(int i=0;i<transits.size();i++){
			Vector<String> vector=new Vector<String>();
			TransitVO temp=transits.get(i);
			vector.add(temp.getLocation());
			vector.add(temp.getId());
			vector.add(temp.getName());
			data.add(vector);
		}
		for(int j=0;j<positions.size();j++){
			Vector<String> vector=new Vector<String>();
			PositionVO temp2=positions.get(j);
			vector.add(temp2.getLocation());
			vector.add(temp2.getId());
			vector.add(temp2.getName());
			data.add(vector);
		}
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background04.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
