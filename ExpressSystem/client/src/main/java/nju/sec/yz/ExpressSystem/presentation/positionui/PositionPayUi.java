package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.accountbl.Collection;
import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;

public class PositionPayUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private FinanceController financeController;
	private DeliverBlService deliverBl;
	private ReceiptService receiptBl;
	
	private ButtonComponents bc;
	
	private static final int scroll_x=131;
	private static final int scroll_y=70;
	private static final int scroll_w=339;
	private static final int scroll_h=187;
	private static final int button_x=401;
	private static final int button_y=268;
	private static final int button_w=72;
	private static final int button_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");

	private JTable payTable;
	private JScrollPane scroll;
	private JButton confirm;
	private JLabel warning;
	
	String[] columnName={"收款日期","收款金额","收款快递员","快递单条形码号"};
	String[][] data={{"20151017","426.5","2352616","354678998764"},{"20140403","43.5","11bgfs","fgea452q"},{"rea","hes","rea","245367776"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"},{"20151017","426.5","2352616","354678998764"}};
	
	public PositionPayUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		deliverBl=new DeliverController();
		receiptBl=new Collection();
		List<CollectionRecordVO> payList=deliverBl.getCollectionRecords();
		data=new String[payList.size()][4];
		for(int i=0;i<payList.size();i++)
		{
			data[i][0]=payList.get(i).getTime();
			data[i][1]=Double.toString(payList.get(i).getAmount());
			data[i][2]=payList.get(i).getDeliverId();
			data[i][3]=payList.get(i).getBarId();
		}
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
	
		payTable=new JTable(data,columnName);
		payTable.setRowHeight(20);
//		payTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll=new JScrollPane(payTable);
		scroll.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		add(scroll);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(button_x, button_y, button_w, button_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(){
				PaymentSheetVO payvo=new PaymentSheetVO();
				PaymentInformation pi=new PaymentInformation();
				pi.setTime(data[payTable.getSelectedRow()][0]);
				pi.setAmount(Double.parseDouble(data[payTable.getSelectedRow()][1]));
				pi.setInDeliverId((data[payTable.getSelectedRow()][2]));
				payvo.setPaymentInformation(pi);
				payvo.setBarIds(data[payTable.getSelectedRow()][3]);
				ResultMessage result=receiptBl.make(payvo);
				
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
		});
		
		setVisible(true);
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/pay_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
