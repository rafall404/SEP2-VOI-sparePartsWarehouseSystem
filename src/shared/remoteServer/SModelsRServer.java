package shared.remoteServer;

import client.model.ScooterModels.ISModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SModelsRServer extends Remote {
    void addModel(ISModel model)throws RemoteException;
    void removeModel(ISModel model)throws RemoteException;
    ArrayList<ISModel> getAllModels() throws RemoteException;

}
