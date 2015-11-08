package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitTrainInformation extends TransitInformation implements Serializable{
	private String trainTransitId;
	private String trainId;
	private String carriageId;
	public String getTrainTransitId() {
		return trainTransitId;
	}
	public void setTrainTransitId(String trainTransitId) {
		this.trainTransitId = trainTransitId;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getCarriageId() {
		return carriageId;
	}
	public void setCarriageId(String carriageId) {
		this.carriageId = carriageId;
	}
	
}
