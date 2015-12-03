package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.accountbl.AccountController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

public class AccountDeleteUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private AccountBlService accountBl;
	
	private JTextField JTsearch;
	private JButton JBsearch;
	private JTable table;
	private JScrollPane scroll;
	private JButton back;
	private JButton confirm;
	private JLabel warning;
	
	private static final int jt_x=224;
	private static final int jt_y=61;
	private static final int jt_w=221;
	private static final int jt_h=22;
	private static final int search_x=444;
	private static final int search_y=61;
	private static final int search_w=22;
	private static final int search_h=21;
	private static final int scroll_x=154;
	private static final int scroll_y=94;
	private static final int scroll_w=315;
	private static final int scroll_h=125;
	private static final int back_x=304;
	private static final int back_y=229;
	private static final int back_w=80;
	private static final int back_h=24;
	private static final int confirm_x=398;
	private static final int confirm_y=229;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private String[] name={"名称","金额"};
	private String[][] data={{"",""}};
	
	private ImageIcon searchIcon=new ImageIcon("graphic/account/button/search_button.jpg");
	private ImageIcon backIcon=new ImageIcon("graphic/account/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	
	
	public AccountDeleteUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		accountBl=new AccountController();
		List<AccountVO> avo=accountBl.observeList();
		if(avo!=null){
			int l=avo.size();
			data=new String[l][2];
			for(int i=0;i<l;i++)
			{
				data[i][0]=avo.get(i).getName();
				data[i][1]=Double.toString(avo.get(i).getBalance());
			}
		}
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		JTsearch=new JTextField();
		JTsearch.setBounds(jt_x, jt_y, jt_w, jt_h);
		add(JTsearch);
		JBsearch=new JButton(searchIcon);
		JBsearch.setBounds(search_x,search_y,search_w,search_h);
		JBsearch.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				AccountVO av=accountBl.observeAccount(JTsearch.getText());
				if(av!=null)
				{
					remove(scroll);
					data=new String[][]{{av.getName(),Double.toString(av.getBalance())}};
					table=new JTable(data,name);
					table.setRowHeight(20);
					scroll=new JScrollPane(table);
					scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
					add(scroll);
				}
				else
				{
					warning.setText("不存在该名称的账户");
					warning.setVisible(true);
				}
				repaint();
			}
		});
		add(JBsearch);
				
		JBsearch=new JButton(searchIcon);
		JBsearch.setBounds(search_x, search_y, search_w, search_h);
		add(JBsearch);
		
		table=new JTable(data,name);
		table.setRowHeight(20);
		scroll=new JScrollPane(table);
		scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		add(scroll);
		
		back=new JButton(backIcon);
		back.setBounds(back_x, back_y, back_w, back_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				List<AccountVO> avo=accountBl.observeList();
				if(avo!=null)
				{
					int l=avo.size();
					data=new String[l][2];
					for(int i=0;i<l;i++)
					{
						data[i][0]=avo.get(i).getName();
						data[i][1]=Double.toString(avo.get(i).getBalance());
					}
				}
				else
					data=new String[][]{{"",""}};
				remove(scroll);
				table=new JTable(data,name);
				table.setRowHeight(20);
				scroll=new JScrollPane(table);
				scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
				add(scroll);
				repaint();
			}
		});
		add(back);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=accountBl.deleteAccount(data[table.getSelectedRow()][0]);
				if(result.getResult()==Result.SUCCESS)
				{
					warning.setText("删除成功");
				}
				else
				{
					warning.setText(result.getMessage());
				}
				warning.setVisible(true);
				repaint();
			}
		});
		add(confirm);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/delete_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
