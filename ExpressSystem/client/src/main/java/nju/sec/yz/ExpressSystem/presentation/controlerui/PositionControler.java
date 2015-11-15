package nju.sec.yz.ExpressSystem.presentation.controlerui;

public class PositionControler {
	private ClientControler mainControler;
	
	public PositionControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
	}
	public void positionChangePanel(POSITION_CONTROL n)
	{
		switch(n)
		{
		case POSITION_MAIN:
			break;
		case ARRIVE:
			break;
		case ARRIVE_SUCCESS:
			break;
		case DELIVE:
			break;
		case DELIVE_SUCCESS:
			break;
		case GETPAY:
			break;
		case CAR_ADD:
			break;
		case CAR_ADD_SUCCESS:
			break;
		case CAR_DELETE:
			break;
		case CAR_MODIFY:
			break;
		case CAR_MODIFY_SUCCESS:
			break;
		case CAR_INQUIRY:
			break;
		case DRIVER_ADD:
			break;
		case DRIVER_ADD_SUCCESS:
			break;
		case DRIVER_DELETE:
			break;
		case DRIVER_MODIFY:
			break;
		case DRIVER_MODIFY_SUCCESS:
			break;
		case DRIVER_INQUIRY:
			break;
		case LOAD:
			break;
		case LOAD_SUCCESS:
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
