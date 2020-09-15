package shared;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PropertyChangeSubject extends Remote {
    void addListener(String names,PropertyChangeListener listener) throws RemoteException;
    void removeListener(String names,PropertyChangeListener listener)throws RemoteException;
}
