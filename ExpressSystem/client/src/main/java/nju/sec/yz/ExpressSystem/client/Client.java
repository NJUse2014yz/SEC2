package nju.sec.yz.ExpressSystem.client;

import java.util.List;

import javax.swing.JOptionPane;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Driver;
import nju.sec.yz.ExpressSystem.bl.managerbl.AgencyInfo;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.client.rmi.RMIHelper;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;



public class Client {

    public static void main(String[] args) {
        try {
            RMIHelper.init();
            ClientControler control=new ClientControler();
    		control.showFrame();
        } catch (ClientInitException e) {
            e.printStackTrace();
            RMIExceptionHandler.handleInitRMIException();
        }

    }
}
