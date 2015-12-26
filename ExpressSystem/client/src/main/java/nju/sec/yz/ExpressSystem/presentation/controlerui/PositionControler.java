package nju.sec.yz.ExpressSystem.presentation.controlerui;

import nju.sec.yz.ExpressSystem.presentation.positionui.ButtonComponents;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionArriveUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionCarAddUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionCarDeleteUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionCarInquiryUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionCarModifyUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionDeliveUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionDriverAddUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionDriverDeleteUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionDriverInquiryUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionDriverModifyUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionLoadUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionMainUi;
import nju.sec.yz.ExpressSystem.presentation.positionui.PositionPayUi;

public class PositionControler {
	private ClientControler mainControler;
	public ButtonComponents bc;
	
	public PositionControler(ClientControler mainControler)
	{
		this.mainControler=mainControler;
		this.bc=new ButtonComponents(mainControler);
	}
	public void positionChangePanel(PositionControl n)
	{
		switch(n)
		{
		case POSITION_MAIN:
			break;
		case ARRIVE:
			mainControler.mainFrame.nextPanel(new PositionArriveUi(this.mainControler,bc));
			break;
		case DELIVE:
			mainControler.mainFrame.nextPanel(new PositionDeliveUi(this.mainControler,bc));
			break;
		case GETPAY:
			mainControler.mainFrame.nextPanel(new PositionPayUi(this.mainControler,bc));
			break;
		case CAR_ADD:
			mainControler.mainFrame.nextPanel(new PositionCarAddUi(this.mainControler,bc));
			break;
		case CAR_DELETE:
			mainControler.mainFrame.nextPanel(new PositionCarDeleteUi(this.mainControler,bc));
			break;
		case CAR_MODIFY:
			mainControler.mainFrame.nextPanel(new PositionCarModifyUi(this.mainControler,bc));
			break;
		case CAR_INQUIRY:
			mainControler.mainFrame.nextPanel(new PositionCarInquiryUi(this.mainControler,bc));
			break;
		case DRIVER_ADD:
			mainControler.mainFrame.nextPanel(new PositionDriverAddUi(this.mainControler,bc));
			break;
		case DRIVER_DELETE:
			mainControler.mainFrame.nextPanel(new PositionDriverDeleteUi(this.mainControler,bc));
			break;
		case DRIVER_MODIFY:
			mainControler.mainFrame.nextPanel(new PositionDriverModifyUi(this.mainControler,bc));
			break;
		case DRIVER_INQUIRY:
			mainControler.mainFrame.nextPanel(new PositionDriverInquiryUi(this.mainControler,bc));
			break;
		case LOAD:
			mainControler.mainFrame.nextPanel(new PositionLoadUi(this.mainControler,bc));
			break;
		default:
			System.out.println("wrong state");
			break;
		}
	}
}
