package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum ArriveState  implements Serializable{
	PERFECT(){

		@Override
		public String toString() {
			
			return "";
		}
		
	},BROKEN(){

		@Override
		public String toString() {
			
			return "您的快递已损坏。";
		}
	
	},LOST(){

		@Override
		public String toString() {
			
			return "您的快递已丢失。";
		}
		
	};
	
	public abstract String toString();
	
}
