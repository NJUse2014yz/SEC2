package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.Collection;
import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
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
	
//	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");

	private newTable table;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	private newJCombo account;
	private String[] accounts={"a"};
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private List<CollectionRecordVO> payList;
	
	public PositionPayUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		deliverBl=new DeliverController();
		receiptBl=new Collection();
		payList=deliverBl.getCollectionRecords();
		name.add("收款日期");
		name.add("收款金额");
		name.add("收款快递员");
		name.add("快递单条形码号");
		name.add("账户");
		changeData(payList);
		
		List<String> accountList=deliverBl.getAccounts();
		if(accountList.size()!=0)
		{
			accounts=new String[accountList.size()+1];
			accounts[0]=null;
			for(int i=1;i<=accountList.size();i++)
			{
				accounts[i]=accountList.get(i-1);
			}
		}
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new newTable(data,name,this,false);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.setJComboBox(new newJCombo(accounts), 4);
		table.join();
		
		warning.setForeground(Color.red);
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(button_x, button_y, button_w, button_h);
		add(confirm);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				PaymentSheetVO payvo=new PaymentSheetVO();
				PaymentInformation pi=new PaymentInformation();
				pi.setTime(table.getValueAt(table.getSelectedRow(), 0, false));
				pi.setAmount(Double.parseDouble(table.getValueAt(table.getSelectedRow(),1,false)));
				pi.setInDeliverId(table.getValueAt(table.getSelectedRow(),2,false));
				pi.setAccount(table.getValueAt(table.getSelectedRow(), 4,true));
				payvo.setPaymentInformation(pi);
				payvo.setBarIds(table.getValueAt(table.getSelectedRow(),3,false));
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
	private void changeData(List<CollectionRecordVO> paylist)
	{
//		if(payList.size()!=0)
//		{
			data.removeAllElements();
			for(int i=0;i<payList.size();i++)
			{
				Vector<String> vector=new Vector<String>();
				vector.add(payList.get(i).getTime());
				vector.add(Double.toString(payList.get(i).getAmount()));
				vector.add(payList.get(i).getDeliverId());
				vector.add(payList.get(i).getBarId());
				vector.add("");
				data.add(vector);
			}
			
//		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/pay_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
