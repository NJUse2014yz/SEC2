package nju.sec.yz.ExpressSystem.presentation.controlerui;

public class TransitControler {
private ClientControler mainControler;
	
	public TransitControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	public void transitChangePanel(TRANSIT_CONTROL n)
	{
		switch(n)
		{
		case TRANSIT_MAIN:
			break;
		case FLIGHT_LOAD:
			break;
		case FLIGHT_LOAD_SUCCESS:
			break;
		case TRAIN_LOAD:
			break;
		case TRAON_LOAD_SUCCESS:
			break;
		case CAR_LOAD:
			break;
		case CAR_LOAD_SUCCESS:
			break;
		case TRANSIT:
			break;
		case TRANSIT_SUCCESS:
			break;
		case ARRIVE:
			break;
		case ARRIVE_SUCCESS:
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
