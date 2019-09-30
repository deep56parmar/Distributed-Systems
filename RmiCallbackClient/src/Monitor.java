import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Monitor extends UnicastRemoteObject implements Listener {

    protected Monitor() throws RemoteException {
    }

    public static void main(String[] args) {
        try {

            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
            Remote lRemote = Naming.lookup(url);
            Server lRemoteServer = (Server) lRemote;


            System.out.println("Origin Value " + lRemoteServer.getValue());


            Monitor lMonitor = new Monitor();
            lRemoteServer.addValueListener(lMonitor);

        } catch (Exception aInE) {
            System.out.println(aInE);
        }
    }

    @Override
    public void valueChanged(double value) throws RemoteException {
        System.out.println("Value changed " + value);
    }
}