package nju.sec.yz.ExpressSystem.server;



public class Server {

    public static void main(String[] args) {
        try {
            
            RMIHelper.init();
            
        } catch (ServerInitException e) {
            e.printStackTrace();
        }
    }
}
