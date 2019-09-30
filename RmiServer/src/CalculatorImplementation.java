import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    protected CalculatorImplementation() throws RemoteException {
        super();
    }

    @Override
    public long addition(long a, long b) throws RemoteException {
        return a + b;
    }

    @Override
    public long subtraction(long a, long b) throws RemoteException {
        return a - b;
    }

    @Override
    public long multiplication(long a, long b) throws RemoteException {
        return a * b;
    }

    @Override
    public long division(long a, long b) throws RemoteException {
        return a / b;
    }
}
