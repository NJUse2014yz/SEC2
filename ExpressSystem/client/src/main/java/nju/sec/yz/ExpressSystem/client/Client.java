package nju.sec.yz.ExpressSystem.client;

import javax.swing.JOptionPane;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.UserVO;


public class Client {

    public static void main(String[] args) {
        try {
            RMIHelper.init();
//            new ClientFrame();
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
