package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitReceiptHelper;
import nju.sec.yz.ExpressSystem.presentation.positionui.ButtonComponents;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionArriveUi;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitButtonComponents;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitLoading;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitMainUi;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitReceiptCar;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitReceiptFlight;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitReceiptTrain;
import nju.sec.yz.ExpressSystem.presentation.transitui.TransitReceive;

public class TransitControler {
private ClientControler mainControler;
public TransitButtonComponents tbc;
	
	public TransitControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
		this.tbc=new TransitButtonComponents(mainControler);
	}
	public void transitChangePanel(TRANSIT_CONTROL n)
	{
		switch(n)
		{
		case TRANSIT_MAIN:
			mainControler.mainFrame.nextPanel(new TransitMainUi(this.mainControler,tbc));
			break;
		case FLIGHT_LOAD:
			mainControler.mainFrame.nextPanel(new TransitReceiptFlight(this.mainControler,tbc));
			break;
		case FLIGHT_LOAD_SUCCESS:
			break;
		case TRAIN_LOAD:
			mainControler.mainFrame.nextPanel(new TransitReceiptTrain(this.mainControler,tbc));
			break;
		case TRAON_LOAD_SUCCESS:
			break;
		case CAR_LOAD:
			mainControler.mainFrame.nextPanel(new TransitReceiptCar(this.mainControler,tbc));
			break;
		case CAR_LOAD_SUCCESS:
			break;
		case TRANSIT:
			mainControler.mainFrame.nextPanel(new TransitLoading(this.mainControler,tbc));
			break;
		case TRANSIT_SUCCESS:
			break;
		case ARRIVE:
			mainControler.mainFrame.nextPanel(new TransitReceive(this.mainControler,tbc));
			break;
		case ARRIVE_SUCCESS:
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
