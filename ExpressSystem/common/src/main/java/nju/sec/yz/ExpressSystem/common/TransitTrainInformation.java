package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitTrainInformation extends TransitInformation implements Serializable{
	
	private String trainTransitId;
	private String trainId;
	private String carriageId;
	
	protected TransitTrainInformation(String time, String departure, String destination, String transiterId,
			ArrayList<String> barIds) {
		super(time, departure, destination, transiterId, barIds);
	}
	
	
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
