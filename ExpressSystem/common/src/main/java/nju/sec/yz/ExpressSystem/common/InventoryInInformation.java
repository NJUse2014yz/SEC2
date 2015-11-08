package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class InventoryInInformation  implements Serializable{
	private String time;
	private String destination;
	private int block;
	private int row;
	private int shelf;
	private int positon;
	
	public InventoryInInformation(String time, String destination, int block,
			int row, int shelf, int positon) {
		super();
		this.time = time;
		this.destination = destination;
		this.block = block;
		this.row = row;
		this.shelf = shelf;
		this.positon = positon;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
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
	public int getPositon() {
		return positon;
	}
	public void setPositon(int positon) {
		this.positon = positon;
	}
	
	
}
