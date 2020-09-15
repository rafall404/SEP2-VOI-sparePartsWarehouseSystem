package shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePropertyChangeListener extends Remote {
    void propertyChange(PropertyChangeEvent evt) throws RemoteException;
}
