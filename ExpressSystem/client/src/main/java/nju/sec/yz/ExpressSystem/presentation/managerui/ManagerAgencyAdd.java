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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyAdd extends JPanel {
	private AgencyBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	
	private newJCombo AgencyType;
	private newJText Location;
	private newJText TransitId;
	private newJText name;
	private newJText Id;
	private newJLabel t;
	private newTable positions;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> title=new Vector<String>();
	
	private newJBut confirm;
	
	private newJLabel warning=new newJLabel();
	
	public ManagerAgencyAdd(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
		title.add("下属营业厅编号");
		iniManagerAgencyAdd();
	}

	private void iniManagerAgencyAdd() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		String[] type={"营业厅","中转中心"};
		AgencyType=new newJCombo(type);
		AgencyType.setBounds(214, 57, 85, 20);
		original(1);
		
		AgencyType.addActionListener(new ActionListener(){           
			public void actionPerformed(ActionEvent e) {
				if(((String)AgencyType.getSelectedItem()).equals("营业厅")){
					original(1);
				}else{
					//中转中心
					original(2);
				}
              }
		});
	}
		
	private void confirmTransit(){
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(382, 417, 72, 24);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((Location.getText().equals("")) || (name.getText().equals(""))
						|| (Id.getText().equals("")) ) {
					warning.NotFilled();
				} else {
					ArrayList<PositionVO> Ids=new ArrayList<PositionVO>();
					for(int i=0;i<positions.getRowCount()-1;i++){
						if(positions.getValueAt(i,0,false)!=null)
						{
							PositionVO pvo=new PositionVO();
							pvo.setTransitId(Id.getText());
							pvo.setId(positions.getValueAt(i,0,false));
							Ids.add(pvo);
						}
					}
					TransitVO vo=new TransitVO(name.getText().toString(),Id.getText(),Ids,Location.getText());
				ResultMessage result=manager.addTransit(vo);
				warning.Reply(result);
				}
				add(warning);
				repaint();
			}
		});	

	}
	
	private void confirmPosition(){
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(382, 417, 72, 24);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((Id.getText().equals("")) || (name.getText().equals(""))
						|| (TransitId.getText().equals("")) || (Location.getText().equals(""))) {
					warning.NotFilled();
				} else {
				PositionVO vo=new PositionVO();
				vo.setName(name.getText());
				vo.setId(Id.getText());
				vo.setLocation(Location.getText());
				vo.setTransitId(TransitId.getText());
				
				ResultMessage result=manager.addPosition(vo);
				//成功
				warning.Reply(result);
				}
				add(warning);
				repaint();
			}
		});	

	}

	private void original(int n) {
		removeAll();
		mbc.add();
		add(AgencyType);
		
		Location=new newJText();
		Location.setBounds(204, 89, 78, 18);
		add(Location);
		
		Id=new newJText();
		Id.setBounds(190, 118, 73, 18);
		add(Id);
		
		name=new newJText();
		name.setBounds(190, 147, 140, 18);
		add(name);
		if(n==1)
		{
			TransitId=new newJText();
			TransitId.setBounds(250, 173, 140, 18);
			add(TransitId);
			
			t=new newJLabel("所属中转中心");
			t.setBounds(155,170,100,25);
			t.setFont(new Font("Dialog", 1, 15));
			t.setForeground(Color.white);
			add(t);

			confirmPosition();
		}
		else
		{
			data.removeAllElements();
			positions = new newTable(data,title,this,true);
			positions.setBounds(141,200,321,191);
			positions.initialBlank(1);
			positions.join();
			positions.setVisible(true);
			
			confirmTransit();
		}
		repaint();
		
	}    

	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background02.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
