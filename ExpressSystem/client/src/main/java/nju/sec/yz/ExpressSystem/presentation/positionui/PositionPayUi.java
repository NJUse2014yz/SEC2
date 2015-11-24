package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;

public class PositionPayUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private DeliverBlService deliverBl;
//	private ?
	
	private ButtonComponents bc;
	
	private static final int scroll_x=131;
	private static final int scroll_y=70;
	private static final int scroll_w=339;
	private static final int scroll_h=187;
	private static final int button_x=401;
	private static final int button_y=268;
	private static final int button_w=72;
	private static final int button_h=24;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");

	private JTable payTable;
	private JScrollPane scroll;
	private JButton confirm;
	
	String[] columnName={"收款日期","收款金额","收款快递员","快递单条形码号"};
	String[][] data={{"20151017","426.5","2352616","354678998764"},{"20140403","43.5","11bgfs","fgea452q"},{"rea","hes","rea","245367776"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"}};
	
	public PositionPayUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
	
		payTable=new JTable(data,columnName);
//		payTable.getColumnModel().getColumn(0).setPreferredWidth(2);
//		payTable.getColumnModel().getColumn(1).setPreferredWidth(2);
//		payTable.getColumnModel().getColumn(2).setPreferredWidth(2);
//		payTable.getColumnModel().getColumn(3).setPreferredWidth(3);
		payTable.setRowHeight(20);
//		payTable.setRowMargin(5);
//		payTable.setRowSelectionAllowed(true);
//		payTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll=new JScrollPane(payTable);
		scroll.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		add(scroll);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(button_x, button_y, button_w, button_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(){
				//TODO 
			}
		});
		
		setVisible(true);
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/pay_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
