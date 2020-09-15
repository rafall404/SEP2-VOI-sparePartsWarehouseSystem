package client;

import shared.RemotePropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RPCLImpl implements RemotePropertyChangeListener {
    private PropertyChangeListener propertyChangeListener;

    public RPCLImpl(PropertyChangeListener propertyChangeListener) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.propertyChangeListener = propertyChangeListener;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RuntimeException {
        propertyChangeListener.propertyChange(evt);
    }
}
