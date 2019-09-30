
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Listener extends Remote {
    void valueChanged(double temperature) throws RemoteException;
}