package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum PackType  implements Serializable{
	WOOD(10){
	
	},PAPER(5){
		
	},BAG(1){
	
	},OTHER(0){
		
	};
	private double price; 
	
	private PackType(double i){
		this.price=i;
	}
	/**
	 * 获得价格
	 */
	public double getPrice(){
		return price;
	}
	
	public static void main(String[] args) {
		System.out.println(WOOD.getPrice());
	}
}
