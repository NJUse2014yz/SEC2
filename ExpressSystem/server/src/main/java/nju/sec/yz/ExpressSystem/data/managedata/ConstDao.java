package nju.sec.yz.ExpressSystem.data.managedata;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.RemarshalException;

import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

/**
 * 保存价格常量和所有距离常量
 * @author 周聪
 *
 */
public class ConstDao extends UnicastRemoteObject implements Serializable{

	private List<CityPO> distances;
	
	private PricePO price;

	public ConstDao() throws RemoteException{
		
		//添加现有的距离常量
		distances=new ArrayList<>();
		CityPO po1 = new CityPO(new CityInformation("南京", "025", "上海", "021", 266));
		CityPO po2 = new CityPO(new CityInformation("上海", "021", "南京", "025", 266));
		CityPO po3 = new CityPO(new CityInformation("南京", "025", "广州", "020", 1132));
		CityPO po4 = new CityPO(new CityInformation("广州", "020", "南京", "025", 1132));
		CityPO po5 = new CityPO(new CityInformation("南京", "025", "北京", "010", 900));
		CityPO po6 = new CityPO(new CityInformation("北京", "010", "南京", "025", 900));
		CityPO po7 = new CityPO(new CityInformation("上海", "021", "广州", "020", 1213));
		CityPO po8 = new CityPO(new CityInformation("广州", "020", "上海", "021", 1213));
		CityPO po9 = new CityPO(new CityInformation("上海", "021", "北京", "010", 1064.7));
		CityPO po10 = new CityPO(new CityInformation("北京", "010", "上海", "021", 1064.7));
		CityPO po11 = new CityPO(new CityInformation("北京", "010", "广州", "020", 1888.8));
		CityPO po12 = new CityPO(new CityInformation("广州", "020", "北京", "010", 1888.8));
		
		distances.add(po1);
		distances.add(po2);
		distances.add(po3);
		distances.add(po4);
		distances.add(po5);
		distances.add(po6);
		distances.add(po7);
		distances.add(po8);
		distances.add(po9);
		distances.add(po10);
		distances.add(po11);
		distances.add(po12);
		
		price=new PricePO();
	}
	
	public List<CityPO> getDistances() {
		return distances;
	}
	
	public void setDistances(List<CityPO> distances) {
		this.distances = distances;
	}

	public PricePO getPrice() {
		return price;
	}

	public void setPrice(PricePO price) {
		this.price = price;
	}
	
	
}
