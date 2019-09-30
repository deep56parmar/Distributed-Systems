import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ServerImplementation extends UnicastRemoteObject implements Server, Runnable {

    private List<Listener> listeners = new ArrayList<>();
    private volatile double val;

    protected ServerImplementation() throws RemoteException {
        val = 88.00;
    }

    @Override
    public void run() {
        Random lRandom = new Random();
        for (; ; ) {
            int duration = lRandom.nextInt() % 10000 + 2000;
            if (duration < 0) {
                duration = duration * -1;
            }
            try {
                Thread.sleep(duration);
            } catch (InterruptedException aInE) {
                System.out.println(aInE.getMessage());
            }
            //Take a number to see if up or down
            int num = lRandom.nextInt();

            if (num < 0) {
                val -= 0.5;
            } else {
                val += 0.5;
            }
            notifyValueListeners(val);
        }
    }

    private void notifyValueListeners(double aInTemp) {
        for (Listener lListener : listeners) {
            try {
                lListener.valueChanged(aInTemp);
            } catch (RemoteException aInE) {
                listeners.remove(lListener);
            }
        }
    }

    @Override
    public void addValueListener(Listener Listener) throws RemoteException {
        listeners.add(Listener);
    }

    @Override
    public void removeValueListener(Listener Listener) throws RemoteException {
        listeners.remove(Listener);
    }

    @Override
    public Double getValue() throws RemoteException {
        return val;
    }

    public static void main(String[] args) {
        try {
            ServerImplementation lServer = new ServerImplementation();
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";

            Naming.rebind(url, lServer);

            Thread lThread = new Thread(lServer);
            lThread.start();

        } catch (Exception aInE) {
            System.out.println("Remote error- " + aInE);
        }
    }
}