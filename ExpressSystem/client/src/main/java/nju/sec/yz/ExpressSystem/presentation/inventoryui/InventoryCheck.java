package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

public class InventoryCheck extends JPanel{

	private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	
	private JTable table;
	
	public InventoryCheck(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryCheck();
	}

	private void iniInventoryCheck() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		
		InventoryVO vo=inventoryservice.checkStock();
		
		Object[][] tableData = {};
		String[] columnTitle = { "快递编号", "入库日期", "目的地", "区号", "排号", "架号", "位号" };
		table = new JTable(tableData, columnTitle);
		// 将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
		JScrollPane jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(144, 105, 315, 177);
		add(jsc);
	}
}
