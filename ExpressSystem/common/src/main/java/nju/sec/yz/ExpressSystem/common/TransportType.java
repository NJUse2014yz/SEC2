package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum TransportType  implements Serializable{
	TRAIN(){
		@Override
		public String toString(){
			return "火车";
		}
	},PLANE(){
		@Override
		public String toString(){
			return "飞机";
		}
	},CAR(){
		@Override
		public String toString(){
			return "汽车";
		}
	};
}
