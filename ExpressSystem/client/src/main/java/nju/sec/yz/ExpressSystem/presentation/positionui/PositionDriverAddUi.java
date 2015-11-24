package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Driver;
import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.DriverController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class PositionDriverAddUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private DriverBlService driverBl;
	private ButtonComponents bc;
	
	private JTextField driverId;
	private JTextField name;
	private JComboBox sex;
	private DateChooser date;
	private JTextField id;
	private JTextField cellphone;
	private JTextField agency;
	private DateChooser limit;
	private JButton confirm;
	private JLabel warning;
	
	private String[] sArr=new String[]{"男","女"};
	private Sex seX;
	
	private static final int driverId_x=252;
	private static final int driverId_y=65;
	private static final int driverId_w=191;
	private static final int name_x=194;
	private static final int name_y=92;
	private static final int name_w=60;
	private static final int sex_x=331;
	private static final int sex_y=92;
	private static final int sex_w=67;
	private static final int date_x=233;
	private static final int date_y=119;
	private static final int id_x=224;
	private static final int id_y=144;
	private static final int id_w=193;
	private static final int cellphone_x=192;
	private static final int cellphone_y=175;
	private static final int cellphone_w=194;
	private static final int agency_x=217;
	private static final int agency_y=200;
	private static final int agency_w=148;
	private static final int limit_x=234;
	private static final int limit_y=224;
	private static final int confirm_x=390;
	private static final int confirm_y=257;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=197;
	private static final int warning_y=487;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	
	
	private static final int h=20;
	
	public PositionDriverAddUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		driverBl=new DriverController();
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		driverId=new JTextField();
		driverId.setBounds(driverId_x, driverId_y, driverId_w, h);
		add(driverId);
		
		name=new JTextField();
		name.setBounds(name_x, name_y, name_w, h);
		add(name);
		
		sex=new JComboBox(sArr);
		sex.setBounds(sex_x, sex_y, sex_w, h);
		add(sex);
		
		date=new DateChooser(this,date_x,date_y);
		
		id=new JTextField();
		id.setBounds(id_x, id_y, id_w, h);
		add(id);
		
		cellphone=new JTextField();
		cellphone.setBounds(cellphone_x, cellphone_y, cellphone_w, h);
		add(cellphone);
		
		agency=new JTextField();
		agency.setBounds(agency_x, agency_y, agency_w, h);
		add(agency);
		
		limit=new DateChooser(this,limit_x,limit_y);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x,confirm_y,confirm_w,confirm_h);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				if(driverId.getText().equals("")||name.getText().equals("")||id.getText().equals("")||cellphone.getText().equals("")||agency.getText().equals(""))
				{
					warning.setText("有必填项未填");
					warning.setVisible(true);
					repaint();
				}
				else
				{
					if(((String)sex.getSelectedItem()).equals("男"))
						seX=Sex.MALE;
					else
						seX=Sex.FEMALE;
					
					DriverVO drivervo=new DriverVO(driverId.getText(),name.getText(),date.getTime(),id.getText(),cellphone.getText(),seX,agency.getText(),limit.getTime());
					ResultMessage result=driverBl.add(drivervo);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
						warning.setVisible(true);
						repaint();
					}
					else{
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

		Image img01 = new ImageIcon("graphic/position/background/add_driver_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
