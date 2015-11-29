package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author 周聪    
 * @change YU Fan
 *	快递编号、入库日期、目的地、区号、排号、架号、位号；
 *	出库日期、目的地、装运形式（火车、飞机、汽车）、中转单编号或者汽运编号
 */
public class InventoryListPO implements Serializable{

	//入库信息
	public List<InventoryInSheetPO> inList;
	//出库信息
	public List<InventoryOutSheetPO> outList;
}
