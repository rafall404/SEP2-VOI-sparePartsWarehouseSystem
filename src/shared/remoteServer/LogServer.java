package shared.remoteServer;

import shared.RemotePropertyChangeListener;
import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LogServer extends Remote {
    ArrayList<String> getLogList(ISparePart part, SModel model) throws RemoteException;
    void logToDatabase(String log) throws RemoteException;
    void wrappLogListener(String names, RemotePropertyChangeListener listener) throws RemoteException;


}
