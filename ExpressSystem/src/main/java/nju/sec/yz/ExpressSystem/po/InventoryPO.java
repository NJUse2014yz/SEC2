package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
/**
 * 
 * @author 周聪
 *	快递编号、入库日期、目的地、区号、排号、架号、位号；
 *	出库日期、目的地、装运形式（火车、飞机、汽车）、中转单编号或者汽运编号
 */
public class InventoryPO implements Serializable{

	//快递编号
	private String id;
	
	//入库日期
	private String inDate;
	
	//目的地
	private String destination;
	
	//区号（两位数）
	private int area;
	
	//排号
	private int row;
	
	//架号
	private int shelf;
	
	//位号
	private int location;
	
	//出库日期
	private String outDate;
	
	//装运方式（火车，汽车，飞机）
	private String loadingWay;
	
	//中转单编号或者汽运编号
	private String loadingNum;

	public InventoryPO(String id, String inDate, String destination, int area, int row, int shelf, int location,
			String outDate, String loadingWay, String loadingNum) {
		super();
		this.id = id;
		this.inDate = inDate;
		this.destination = destination;
		this.area = area;
		this.row = row;
		this.shelf = shelf;
		this.location = location;
		this.outDate = outDate;
		this.loadingWay = loadingWay;
		this.loadingNum = loadingNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getLoadingWay() {
		return loadingWay;
	}

	public void setLoadingWay(String loadingWay) {
		this.loadingWay = loadingWay;
	}

	public String getLoadingNum() {
		return loadingNum;
	}

	public void setLoadingNum(String loadingNum) {
		this.loadingNum = loadingNum;
	}
	
	
	
	
}
