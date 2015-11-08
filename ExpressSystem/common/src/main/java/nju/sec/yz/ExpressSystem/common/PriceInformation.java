package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class PriceInformation  implements Serializable{
	private double priceForCar=2;
	private double priceForTrain=0.2;
	private double priceForPlane=20;
	private double standard=23000;
	public double getPriceForCar() {
		return priceForCar;
	}
	public void setPriceForCar(double priceForCar) {
		this.priceForCar = priceForCar;
	}
	public double getPriceForTrain() {
		return priceForTrain;
	}
	public void setPriceForTrain(double priceForTrain) {
		this.priceForTrain = priceForTrain;
	}
	public double getPriceForPlane() {
		return priceForPlane;
	}
	public void setPriceForPlane(double priceForPlane) {
		this.priceForPlane = priceForPlane;
	}
	public double getStandard() {
		return standard;
	}
	public void setStandard(double standard) {
		this.standard = standard;
	}
	
	
}
