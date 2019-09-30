import java.rmi.Remote;
import java.rmi.RemoteException;

interface Server extends Remote {

    void addValueListener(Listener addValueListener) throws RemoteException;

    void removeValueListener(Listener addValueListener) throws RemoteException;

    Double getValue() throws RemoteException;
}
