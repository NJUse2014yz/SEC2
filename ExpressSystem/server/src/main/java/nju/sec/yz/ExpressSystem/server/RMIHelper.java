package nju.sec.yz.ExpressSystem.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nju.sec.yz.ExpressSystem.data.datafactory.DataFactorySerializableImpl;



public class RMIHelper {

    private static Map<String, Class<? extends UnicastRemoteObject>> NAMING_MAP =
            new HashMap<>();

    private static final int PORT = 1099;

    private static boolean inited = false;

    static {
        NAMING_MAP.put("DataFactorySerializableImpl", DataFactorySerializableImpl.class);
    }

    public synchronized static void init() throws ServerInitException {
        if (inited) {
            return;
        }
        try {
        	System.out.println("server is running...");
            LocateRegistry.createRegistry(PORT);
            for (Entry<String, Class<? extends UnicastRemoteObject>> entry : NAMING_MAP.entrySet()) {
                String name = entry.getKey();
                Class<? extends UnicastRemoteObject> clazz = entry.getValue();
                UnicastRemoteObject proxy = clazz.newInstance();
                Naming.rebind(name, proxy);
            }
            inited = true;
            System.out.println("connecting...");
        } catch (Exception e) {
            throw new ServerInitException(e);
        }
    }
}
