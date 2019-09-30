import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    Server() {
        try {
            Calculator c = new CalculatorImplementation();
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello123";
            Naming.rebind(url, (Remote) c);
            System.out.println("server is ready");
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void main(String arg[]) {
        new Server();
    }
}
