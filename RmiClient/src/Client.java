import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String arg[]) {
        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello123";
            Remote lRemote = Naming.lookup(url);
            Calculator c = (Calculator) lRemote;
            System.out.println("subtraction:" + c.addition(10, 5));
            System.out.println("subtraction:" + c.subtraction(10, 5));
            System.out.println("Multiplication:" + c.multiplication(10, 5));
            System.out.println("Division:" + c.division(10, 5));
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}
