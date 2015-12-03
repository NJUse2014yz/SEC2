package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;

public class AccountOperateTableUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private FinanceBlSevice financeBl;
	
	private DateChooser begin;
	private DateChooser end;
	private JTable inTable;
	private JTable outTable;
	private JScrollPane inScroll;
	private JScrollPane outScroll;
	private JButton confirm;
	private JButton excle;
	private JLabel warning;
	private String[] nameIn=new String[]{"收款日期","收款金额","收款人","快递条形码号"};
	private String[] nameOut=new String[]{"付款日期 ","付款金额","付款人","付款账号","条目","备注"};
	private String[][] dataIn=new String[][]{{"","","",""}};
	private String[][] dataOut=new String[][]{{"","","","","",""}};
	
	private static final int begin_x=225;
	private static final int begin_y=59;
	private static final int end_x=225;
	private static final int end_y=86;
	private static final int in_x=144;
	private static final int in_y=117;
	private static final int out_x=144;
	private static final int out_y=274;
	private static final int w=320;
	private static final int h=144;
	private static final int confirm_x=404;
	private static final int confirm_y=84;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int excle_x=404;
	private static final int excle_y=84;
	private static final int excle_w=72;
	private static final int excle_h=24;	
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	private ImageIcon excleIcon=new ImageIcon("graphic/account/button/excle_button.jpg");
	public AccountOperateTableUi(ClientControler mainControler,
			AccountButtonComponents bc) {
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		financeBl=new FinanceController();

		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		begin=new DateChooser(this,begin_x,begin_y);
		end=new DateChooser(this,end_x,end_y);
		
		inTable=new JTable(dataIn,nameIn);
		inScroll=new JScrollPane(inTable);
		inScroll.setBounds(in_x, in_y, w, h);
		add(inScroll);
		outTable=new JTable(dataOut,nameOut);
		outScroll=new JScrollPane(outTable);
		outScroll.setBounds(out_x, out_y, w, h);
		add(outScroll);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x,confirm_y,confirm_w,confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				BussinessVO bussinessvo=financeBl.checkBusinessCircumstance(begin.getTime(), end.getTime());
				if(bussinessvo!=null){
					warning.setVisible(false);
					remove(inScroll);
					remove(outScroll);
					
					
					List<PaymentSheetVO> invo=bussinessvo.in;
					List<OutVO> outvo=bussinessvo.out;
					int sizeIn=invo.size();
					int sizeOut=outvo.size();
					dataIn=new String[sizeIn][4];
					for(int i=0;i<sizeIn;i++)
					{
						dataIn[i][0]=invo.get(i).getPaymentInformation().getTime();
						dataIn[i][1]=Double.toString(invo.get(i).getPaymentInformation().getAmount());
						dataIn[i][0]=invo.get(i).getPaymentInformation().getInDeliverId();
						dataIn[i][0]=invo.get(i).getBarIds();
					}
					inTable=new JTable(dataIn,nameIn);
					dataOut=new String[sizeOut][6];
					for(int i=0;i<sizeOut;i++)
					{
						dataOut[i][0]=outvo.get(i).getOutInformation().getDate();
						dataOut[i][1]=Double.toString(outvo.get(i).getOutInformation().getNum());
						dataOut[i][2]=outvo.get(i).getOutInformation().getPerson();
						dataOut[i][3]=outvo.get(i).getOutInformation().getAccount();
						dataOut[i][4]=outvo.get(i).getOutInformation().getReason();
						dataOut[i][5]=outvo.get(i).getOutInformation().getComments();
						
					}				
					outTable=new JTable(dataOut,nameOut);
	
	//				dataIn=new String[][]{{"1","2","3","4"},{"d","g","g","f"}};
	//				inTable=new JTable(dataIn,nameIn);
					inScroll=new JScrollPane(inTable);
					inScroll.setBounds(in_x, in_y, w, h);
					add(inScroll);
	//				dataOut=new String[][]{{"12","gf","r","h","f","w"},{"d","d","g","r","r","5"}};
	//				outTable=new JTable(dataOut,nameOut);
					outScroll=new JScrollPane(outTable);
					outScroll.setBounds(out_x, out_y, w, h);
					add(outScroll);
					repaint();
				}
				else{
					warning.setText("日期选择有误，请重新选择");
					warning.setVisible(true);
				}
			}
		});
		add(confirm);
		
		excle=new JButton(excleIcon);
		excle.setBounds(excle_x, excle_y, excle_w, excle_h);
		excle.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
//				exportCostToExcel();
			}
		});
		add(excle);
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/operate_table_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
