package nju.sec.yz.ExpressSystem.client;

import java.util.List;

import javax.swing.JOptionPane;

import nju.sec.yz.ExpressSystem.bl.managerbl.Agency;
import nju.sec.yz.ExpressSystem.bl.managerbl.City;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
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
            JOptionPane.showMessageDialog(
                    null,
                    "Client boots fail!\nCause: " + e.getMessage(),
                    "Fatal Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
