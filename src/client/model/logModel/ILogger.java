package client.model.logModel;

import shared.PropertyChangeSubject;
import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ILogger extends Remote, PropertyChangeSubject
{
  void  setUsername(String username) throws RemoteException;
    String getUsername()  throws RemoteException;
    ArrayList<String> getLogList(ISparePart part, SModel model)  throws RemoteException;
    void log(String event)  throws RemoteException;
}
