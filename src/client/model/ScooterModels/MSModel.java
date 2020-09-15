package client.model.ScooterModels;

import shared.remoteServer.SModelsRServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MSModel implements IMSModel {
    private ISModel scooterModel;
    private SModelsRServer server;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public MSModel() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server = (SModelsRServer) reg.lookup("server");
        System.out.println("Connected to Server");
    }

    ;

    @Override
    public void addModel(String modelName) throws RemoteException {
        scooterModel = new SModel(modelName);
        server.addModel(scooterModel);
        support.firePropertyChange("addedModel", null, modelName);
    }

    @Override
    public void removeModel(SModel scooterModel) {
        try {
            server.removeModel(scooterModel);
            support.firePropertyChange("deletedModel", null, scooterModel.getModelName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ISModel> getAllModels() throws RemoteException {
        return server.getAllModels();
    }


    @Override
    public void addListener(String names, PropertyChangeListener listener) {
        support.addPropertyChangeListener(names, listener);
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) throws RemoteException {

    }
}
