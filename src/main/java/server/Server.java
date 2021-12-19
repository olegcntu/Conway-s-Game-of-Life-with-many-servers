package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public  void startServer(String serverName, int port) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(port);
        Naming.rebind(serverName,  new MatrixWork());

    }

}
